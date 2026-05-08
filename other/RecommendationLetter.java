package other;

import java.io.Serializable;
import java.util.Date;

import users.Student;
import users.Teacher;

public class RecommendationLetter implements Serializable {

    private static final long serialVersionUID = 1L;

    private Student student;
    private Teacher author;
    private String content;
    private Date issued;

    public RecommendationLetter(Student student, Teacher author, String content) {
        this.student = student;
        this.author = author;
        this.content = content;
        this.issued = new Date();
    }

    public Student getStudent() { return student; }
    public Teacher getAuthor() { return author; }
    public String getContent() { return content; }
    public Date getIssued() { return issued; }

    @Override
    public String toString() {
        return "=== Recommendation Letter ===\n"
                + "Date   : " + issued + "\n"
                + "Author : " + (author != null ? author.getFullName() : "unknown") + "\n"
                + "Student: " + (student != null ? student.getFullName() : "unknown") + "\n"
                + "---\n"
                + content;
    }
}
