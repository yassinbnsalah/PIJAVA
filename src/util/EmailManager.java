/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



/**
 *
 * @author yacin
 */
public class EmailManager {

     public String sendEmail(String toEmailAddress, String emailMessage) {
                
          String userName = "contact.fithealth23@gmail.com";
        String password = "qavkrnciihzjmtkp";
            String emailSubject = "Utilisateur";
            try {
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");
            Session session = Session.getDefaultInstance(props, null);
            // Create email message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userName));
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
            tr.connect("smtp.gmail.com", 587, userName, password);
            message.saveChanges();
            tr.sendMessage(message, message.getAllRecipients());
            tr.close();
                System.out.println("Email Sent Successfully to : "+recipientAddress.toString());
            return emailMessage;
            } catch (MessagingException e) {
                return "Error in method sendEmailNotification: " + e;
            }
        }
    public void SendMail(String SendTo, String MessageBody, String SubjectMail) {
        String to = SendTo;
        String from = "contact.fithealth23@gmail.com";
        String password = "qavkrnciihzjmtkp";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(SubjectMail);

            // Set the message content here
            message.setContent(MessageBody, "text/html");
            Transport.send(message);

            // Create a multipar message
            /*   Multipart multipart = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource("/util/test.html");//add file path
            messageBodyPart.setDataHandler(new DataHandler(source));
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);*/
            System.out.println("Message sent successfully");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public void SendMailWithFile(String SendTo, String SubjectMail, String fileSource) {
        String to = SendTo;
        String from = "contact.fithealth23@gmail.com";
        String password = "qavkrnciihzjmtkp";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(SubjectMail);
            message.setText("hello we are testing file");
            // Set the message content here
            /* message.setContent(MessageBody, "text/html");
            Transport.send(message);*/
            // Create a multipar message
            System.out.println(fileSource);
            Multipart multipart = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(fileSource);//add file path
            System.out.println(source.getName());
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(source.getName());
           
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
           Transport.send(message);
            System.out.println("Message sent successfully");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
