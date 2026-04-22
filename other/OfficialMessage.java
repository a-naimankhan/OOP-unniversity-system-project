package other;

import java.io.Serializable;
import users.User;

public class OfficialMessage extends Message implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String purpose;

    public OfficialMessage(String subject, String text, User sender, User receiver, String date, String purpose) {
        super(subject, text, sender, receiver, date);
        this.purpose = purpose;
    }

    public String getPurpose() {
        return purpose;
    }

    @Override
    public String toString() {
        return super.toString() + "\nPurpose: " + purpose;
    }
}
