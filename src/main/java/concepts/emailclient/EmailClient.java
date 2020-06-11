package concepts.emailclient;

import org.testng.annotations.Test;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailClient {
    Properties prop = new Properties();

    @Test(enabled = false)
    public void myTest() {

        Properties props = System.getProperties();
        props.put("mail.imaps.auth.plain.disable", "true");
        props.put("mail.smtp.ssl.enable", "false");
        props.put("mail.imap.ssl.enable", "false");
        try {
            Session session = Session.getInstance(props, null);
            session.setDebug(true);
            Store store = session.getStore("imaps");

            store.connect("outlook.office365.com", 993, "subrahmanyam.rentala@ba.com", "Pashi@1234");

            System.out.println(store);
            Folder inbox = store.getFolder("Inbox");
            inbox.open(Folder.READ_ONLY);
            Message messages[] = inbox.getMessages();
            for (Message message : messages) {
                //System.out.println(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(2);
        }

        }


        @Test
        private void myTest2() throws MessagingException {

            final String username = "subrahmanyam.rentala@ba.com";
            final String passwd = "Pashi@1234";
            Properties props = new Properties();
            props.put("mail.host", "outlook.office365.com");
            props.put("mail.store.protocol", "pop3s");
            props.put("mail.pop3s.auth", "true");
            props.put("mail.pop3s.port", "995");


            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, passwd);
                }
            });

            Store store = session.getStore("pop3s");
            store.connect();
            Folder emailFolder = store.getFolder("INBOX");
        }


    }

