package com.kaltura.client.test.utils;


import org.awaitility.core.ConditionTimeoutException;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.search.SearchTerm;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;

public class EmailUtils extends BaseUtils {

    private static final String username = "ottbeqa@gmail.com";
    private static final String password = "ottbeqa2018";

    public static boolean isEmailReceived(SearchTerm term, boolean deleteEmails) {
        AtomicBoolean isReceived = new AtomicBoolean(false);

        // server setting
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");

        Session session = Session.getDefaultInstance(props);
        Folder inbox = null;
        Folder trash = null;
        Store store = null;

        try {
            // connects to the message store
            store = session.getStore("imaps");
            store.connect("imap.gmail.com", username, password);

            // opens the inbox folder
            inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);

            // check if email found for 30 sec
            Folder finalInbox = inbox;

            await()
                    .atMost(40, TimeUnit.SECONDS)
                    .pollInterval(5, TimeUnit.SECONDS)
                    .until(() -> {
                        Message[] foundMessages = finalInbox.search(term);
                        if (foundMessages.length > 0) {
                            isReceived.set(true);
                        }
                        return isReceived.get();
                    });

            // delete emails
            if (deleteEmails) {
                // delete inbox
                Message inboxMessages[] = inbox.getMessages();
                for (Message m : inboxMessages) {
                    m.setFlag(Flags.Flag.DELETED, true);
                }

                // delete trash
                trash = store.getFolder("[Gmail]/Trash");
                trash.open(Folder.READ_WRITE);
                Message trashMessages[] = trash.getMessages();
                for (Message m : trashMessages) {
                    m.setFlag(Flags.Flag.DELETED, true);
                }
            }
        } catch (Exception e) {
            if (e.getClass() != ConditionTimeoutException.class) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (inbox != null) {
                    inbox.close(true);
                }
                if (trash != null) {
                    trash.close(true);
                }
                if (store != null) {
                    store.close();
                }
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        return isReceived.get();
    }

    // custom search terms
    public static class SentDateSearchTerm extends SearchTerm {
        private Date afterDate;

        public SentDateSearchTerm(Date afterDate) {
            this.afterDate = afterDate;
        }

        @Override
        public boolean match(Message message) {
            try {
                if (message.getSentDate().after(afterDate)) {
                    return true;
                }
            } catch (MessagingException ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }

    public static class SubjectSearchTerm extends SearchTerm {
        private String subject;

        public SubjectSearchTerm(String subject) {
            this.subject = subject;
        }

        @Override
        public boolean match(Message message) {
            try {
                if (message.getSubject().contains(subject)) {
                    return true;
                }
            } catch (MessagingException ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }

    public static class FromFieldSearchTerm extends SearchTerm {
        private String fromEmail;

        public FromFieldSearchTerm(String fromEmail) {
            this.fromEmail = fromEmail;
        }

        @Override
        public boolean match(Message message) {
            try {
                Address[] fromAddress = message.getFrom();
                if (fromAddress != null && fromAddress.length > 0) {
                    if (fromAddress[0].toString().contains(fromEmail)) {
                        return true;
                    }
                }
            } catch (MessagingException ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }

    public static class ContentSearchTerm extends SearchTerm {
        private String content;

        public ContentSearchTerm(String content) {
            this.content = content;
        }

        @Override
        public boolean match(Message message) {
            try {
                String contentType = message.getContentType().toLowerCase();

                if (contentType.contains("text/plain") || contentType.contains("text/html")) {
                    String messageContent = message.getContent().toString();
                    if (messageContent.contains(content)) {
                        return true;
                    }
                }

                if (contentType.contains("multipart")) {
                    Multipart multiPart = (Multipart) message.getContent();
                    int numberOfParts = multiPart.getCount();
                    for (int partCount = 0; partCount < numberOfParts; partCount++) {
                        MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
                        String messageContent = part.getContent().toString();
                        if (messageContent.contains(content)) {
                            return true;
                        }
                    }
                }
            } catch (MessagingException | IOException ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }
}
