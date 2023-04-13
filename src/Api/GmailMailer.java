package Api;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GmailMailer {

    public String sendEmail(String toEmailAddress, String emailMessage) throws AddressException, MessagingException    {
       
        String from = "contact.fithealth23@gmail.com";
        String password = "qavkrnciihzjmtkp";
        String emailSubject = "Utilisateur";
        String host = "smtp.gmail.com";
        

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getDefaultInstance(properties, null);
           // Create email message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            String[] recipientList = toEmailAddress.split(",");
            InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
            int counter = 0;
            for (String recipient: recipientList) {
                recipientAddress[counter] = new InternetAddress(recipient.trim());
                counter++;
            }
            message.setRecipients(Message.RecipientType.TO, recipientAddress);
            message.setSubject(emailSubject);
            message.setContent(emailMessage, "text/html");
            // Send smtp message
            Transport tr = session.getTransport("smtp");
            tr.connect("smtp.gmail.com", 587, from, password);
            message.saveChanges();
            tr.sendMessage(message, message.getAllRecipients());
            tr.close();
                System.out.println("Email Sent Successfully to : "+recipientAddress.toString());
            return emailMessage;
            } 
        }

