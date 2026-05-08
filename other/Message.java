package other;

import java.io.Serializable;
import users.User;

public class Message implements Serializable, IMessage {
    private static final long serialVersionUID = 1L;
    
    private String subject;
    private String text;
    private User sender;
    private User receiver;
    private String date;

    public Message() {}

    public Message(String subject, String text, User sender, User receiver, String date) {
        this.subject = subject;
        this.text = text;
        this.sender = sender;
        this.receiver = receiver;
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String getFormattedContent() {
        return toString();
    }

    @Override
    public String toString() {
        return "From: " + sender.getFullName() + "\nSubject: " + subject + "\nDate: " + date + "\n" + text;
    }
}