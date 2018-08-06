package com.kaltura.client.test.utils;


import org.awaitility.core.ConditionTimeoutException;

import javax.mail.*;
import javax.mail.search.SearchTerm;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;

public class EmailUtils extends BaseUtils {

    private static final String username = "ottbeqa@gmail.com";
    private static final String password = "ottbeqa2018";

    public static boolean isEmailReceived(final String keyword, boolean deleteEmails) {
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

            // creates a search criterion
            SearchTerm searchCondition = new SearchTerm() {
                @Override
                public boolean match(Message message) {
                    try {
                        if (message.getSubject().contains(keyword)) {
                            return true;
                        }
                    } catch (MessagingException ex) {
                        ex.printStackTrace();
                    }
                    return false;
                }
            };

            // check if email found for 30 sec
            Folder finalInbox = inbox;
            await()
                    .atMost(40, TimeUnit.SECONDS)
                    .pollInterval(5, TimeUnit.SECONDS)
                    .until(() -> {
                        System.out.println("until " + getLocalTimeFormatted(0));
                        Message[] foundMessages = finalInbox.search(searchCondition);
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
}
