package com.kaltura.client.test.utils;


import javax.mail.*;
import javax.mail.search.SearchTerm;
import java.io.IOException;
import java.util.Properties;

public class EmailUtils extends BaseUtils {

    private static final String username = "ottbeqa@gmail.com";
    private static final String password = "ottbeqa2018";

    public static boolean isEmailReceived(final String keyword, boolean deleteEmails) {
        boolean isReceived = false;

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

            // check if email found
            Message[] foundMessages = inbox.search(searchCondition);
            if (foundMessages.length > 0) {
                isReceived = true;
            }

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
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inbox != null) { inbox.close(true); }
                if (trash != null) { trash.close(true); }
                if (store != null) { store.close(); }
            } catch (MessagingException e) { e.printStackTrace(); }
        }
        return isReceived;
    }

    public static void searchEmail(final String keyword) {
        Properties properties = new Properties();

//        props.setProperty("mail.imaps.host", "imap.gmail.com");
//        props.setProperty("mail.imaps.user", username);
//        props.setProperty("mail.imaps.password", password);
//        props.setProperty("mail.imaps.port", "993");
//        props.setProperty("mail.imaps.auth", "true");
//        props.setProperty("mail.debug", "true");

        // server setting
        properties.put("mail.imap.host", "imap.gmail.com");
        properties.put("mail.imap.port", "993");

        // SSL setting
        properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.imap.socketFactory.fallback", "false");
        properties.setProperty("mail.imap.socketFactory.port", "993");
        properties.setProperty("mail.store.protocol", "imaps");
        properties.setProperty("mail.debug", "true");

//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.socketFactory.port","465");
//        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.auth","true");
//        props.put("mail.smtp.port","465");
//        props.setProperty("mail.debug", "true");

        Store store = null;
        Folder inbox = null;
        Folder trash = null;

        try {
            Session session = Session.getInstance(properties);
            store = session.getStore("imaps");
            store.connect("imap.gmail.com", username, password);
            System.out.println("Connections is opened");

            trash = store.getFolder("[Gmail]/Trash");
            trash.open(Folder.READ_WRITE);

            inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);

            int messageCount = inbox.getMessageCount();
            //log.info("Total Messages: " + messageCount);
            Message[] messages = inbox.getMessages();
            Message[] messages2Remove = new Message[1];

            boolean isMessageFound = false;
            for (int i = 0; i < messageCount; i++) {

                if (messages[i].getContentType().toLowerCase().startsWith("text")) {
                    if (messages[i].getContent().toString().contains(keyword)) {
                        isMessageFound = true;
                        messages2Remove[0] = messages[i];
                        break;
                    }
                } else if (messages[i].getContentType().toLowerCase().startsWith("multipart")) {
                    Multipart multipart = (Multipart) messages[i].getContent();
                    for (int j = 0; j < multipart.getCount(); j++) {
                        if (((Multipart) messages[i].getContent()).getBodyPart(j).getContent().toString().contains(keyword)) {
                            isMessageFound = true;
                            messages2Remove[0] = messages[i];
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {
            try {
                inbox.close(true); // to close folder
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            try {
                trash.close(true); // to close folder
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            try {
                store.close(); // to close connection
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            //log.info("Connections is closed")
        }
    }
}
