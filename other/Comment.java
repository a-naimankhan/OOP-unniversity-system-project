package other;

import java.io.Serializable;
import users.User;

public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String text;
    private User author;
    private String date;

    public Comment(String text, User author, String date) {
        this.text = text;
        this.author = author;
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public User getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return author.getFullName() + " (" + date + "): " + text;
    }
}
