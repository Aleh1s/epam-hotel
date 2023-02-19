package ua.aleh1s.hotelepam.mail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailServiceImpl implements MailService {

    private static final Logger logger = LogManager.getLogger(MailServiceImpl.class);
    private final Properties properties;

    {
        Properties properties = new Properties();
        try (InputStream inputStream = MailServiceImpl.class.getClassLoader().getResourceAsStream("mail.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.properties = properties;
    }

    @Override
    public void send(Mail mail) {
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        Session session = Session.getInstance(properties, auth);

        try {
            MimeMessage email = new MimeMessage(session);
            email.setFrom(new InternetAddress(username));
            email.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getToAddress()));
            email.setSubject(mail.getSubject());
            Multipart multipart = new MimeMultipart();
            BodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(mail.getMessage(), "text/html");
            multipart.addBodyPart(htmlPart);
            email.setContent(multipart);
            Transport.send(email);
        } catch (MessagingException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
