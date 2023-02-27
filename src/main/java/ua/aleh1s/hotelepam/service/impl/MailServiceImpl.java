package ua.aleh1s.hotelepam.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.aleh1s.hotelepam.service.MailService;
import ua.aleh1s.hotelepam.utils.Mail;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
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
            email.setSubject(MimeUtility.encodeText(mail.getSubject(), "utf-8", "B"));
            email.setText(mail.getMessage(), "utf-8", "html");
            Transport.send(email);
        } catch (MessagingException | UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
