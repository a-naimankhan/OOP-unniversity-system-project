package course;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import users.Student;

public class Attendance implements Serializable {
    private static final long serialVersionUID = 1L;

    private Student student;
    private Course course;
    private Date date;
    private boolean isPresent;

    public Attendance(Student student, Course course, Date date, boolean isPresent) {
        this.student = student;
        this.course = course;
        this.date = date;
        this.isPresent = isPresent;
    }

    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public Date getDate() { return date; }
    public boolean isPresent() { return isPresent; }
    public void setPresent(boolean present) { isPresent = present; }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s: %s", 
            new java.text.SimpleDateFormat("yyyy-MM-dd").format(date),
            course.getCourseName(),
            student.getFullName(),
            isPresent ? "PRESENT" : "ABSENT");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendance that = (Attendance) o;
        return Objects.equals(student, that.student) &&
               Objects.equals(course, that.course) &&
               Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, course, date);
    }
}
