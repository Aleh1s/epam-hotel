package ua.aleh1s.hotelepam.mail;

import java.util.Objects;

public class Mail {

    private final String subject;
    private final String message;
    private final String toAddress;

    private Mail(String subject, String message, String toAddress) {
        this.subject = Objects.requireNonNull(subject);
        this.message = Objects.requireNonNull(message);
        this.toAddress = Objects.requireNonNull(toAddress);
    }

    public static Mail construct(String toAddress, String subject, String message) {
        return new Mail(subject, message, toAddress);
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public String getToAddress() {
        return toAddress;
    }
}
