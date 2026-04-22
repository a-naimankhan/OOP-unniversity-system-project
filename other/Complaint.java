package other;

import java.io.Serializable;
import users.User;
import users.Student;

public class Complaint extends Message implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Student student;
    private UrgencyLevel urgency;

    public Complaint(String text, User sender, User receiver, String date, Student student, UrgencyLevel urgency) {
        super("COMPLAINT: " + student.getFullName(), text, sender, receiver, date);
        this.student = student;
        this.urgency = urgency;
    }

    public Student getStudent() {
        return student;
    }

    public UrgencyLevel getUrgency() {
        return urgency;
    }

    @Override
    public String toString() {
        return super.toString() + "\nUrgency: " + urgency + "\nTarget Student: " + student.getFullName();
    }
}
