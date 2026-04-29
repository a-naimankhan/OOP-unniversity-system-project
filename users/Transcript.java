package users;

import java.io.Serializable;
import java.util.Map;
import course.Course;
import mark.Mark;

// official academic transcript — aggregates a student's courses, grades and GPA in one view
public class Transcript implements Serializable {

    private static final long serialVersionUID = 1L;

    private Student student;

    public Transcript() {}

    public Transcript(Student student) {
        this.student = student;
    }

    // builds a formatted academic record string for the student
    public String getTranscript() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== OFFICIAL TRANSCRIPT ===\n");
        sb.append("Student : ").append(student.getFullName()).append("\n");
        sb.append("ID      : ").append(student.getId()).append("\n");
        sb.append("Faculty : ").append(student.getFaculty()).append("\n");
        sb.append("Degree  : ").append(student.getDegree()).append("\n");
        sb.append("Year    : ").append(student.getStudyYear()).append("\n");
        sb.append("GPA     : ").append(String.format("%.2f", student.getGpa())).append("\n");
        sb.append("Credits : ").append(student.getTotalCredit()).append("\n");
        sb.append("---------------------------\n");
        for (Map.Entry<Course, Mark> entry : student.getMarks().entrySet()) {
            Mark m = entry.getValue();
            sb.append(String.format("  %-30s  %s  (%.1f)\n",
                entry.getKey().getCourseName(),
                m.convertToLetterMark(),
                m.getFinalAttestation()));
        }
        sb.append("===========================\n");
        return sb.toString();
    }

    // returns current GPA of the student
    public double getGPA() {
        return student.getGpa();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return getTranscript();
    }
}
