package users;

import java.io.Serializable;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

import course.Course;
import mark.AttestationType;
import mark.Mark;
import other.Time;
import other.Complaint;
import other.UrgencyLevel;

public class Teacher extends Employee implements Serializable, Comparable<Object> {
	
	private static final long serialVersionUID = 1L;
	
	private DegreeTeacher degree;
	private FacultyType department;
	private double rating;
	private int ratedCnt;
	private Time officeHour;
	
	public Teacher() {
	}
	public Teacher(String fullName, String username, String password, int salary, DegreeTeacher degree, FacultyType department) {
		super(fullName, username, password, salary);
		this.degree = degree;
		this.department = department;		
		this.rating = 0;
		this.ratedCnt = 0;
	}

    public void sendComplaint(Student student, String text, UrgencyLevel urgency, User dean) {
        Complaint c = new Complaint(text, this, dean, new java.util.Date().toString(), student, urgency);
        dean.update("NEW COMPLAINT from " + this.getFullName() + " about student " + student.getFullName());
        // In a real system, you'd add this to some message vector in Database
        System.out.println("Complaint sent successfully.");
    }

	
	public boolean putMark(Course course, Student student, double point, AttestationType period) {
		if (student.getMarks().containsKey(course)) {
			Mark mark = new Mark(point, period);
			if (mark.getPeriod() == AttestationType.FIRST) {
				mark.setMark(student.getMarks().get(course).getFirstAttestation());
				student.getMarks().put(course, mark);
			}
			else if (mark.getPeriod() == AttestationType.SECOND){
				student.getMarks().get(course).setSecondAttestation(student.getMarks().get(course).getSecondAttestation() + point);
				student.getMarks().put(course, student.getMarks().get(course));
			}
			else {
				student.getMarks().get(course).setExamMark(student.getMarks().get(course).getExamMark() + point);
				student.getMarks().put(course, student.getMarks().get(course));
			}
			Database.marks.add(mark);
			return true;
		}
		return false;
	}
	
	public String viewCourses() {
		String courses = "";
		for (Course c : Database.courses) {
			if (c.getTeachers().contains(this)) courses += c.getCourseName() + " ";
		}
		return courses;
	}
	
	public String viewStudents() {
		String st = "";
		for (Student s : Database.students) {
			for (Course c : Database.courses) {
				if (c.getTeachers().contains(this) && s.viewCourse().contains(c)) {
					st += s.toString();
				}
			}
		}
		return st;
	}
	
	
	public DegreeTeacher getDegree() {
		return degree;
	}
	public void setDegree(DegreeTeacher degree) {
		this.degree = degree;
	}
	public FacultyType getDepartment() {
		return department;
	}
	public void setDepartment(FacultyType department) {
		this.department = department;
	}
	public double getRating() {
		return (ratedCnt == 0) ? 0 : rating / ratedCnt;
	}
	public void setRating(double rating) {
		this.rating += rating;
		this.ratedCnt += 1;
	}
	public Time getOfficeHour() {
		return officeHour;
	}
	public void setOfficeHour(Time officeHour) {
		this.officeHour = officeHour;
	}

    public String generateReport(Course course) {
        if (!course.getTeachers().contains(this)) return "You don't teach this course.";
        
        double totalSum = 0;
        double max = 0;
        double min = 100;
        int count = 0;
        
        for (Student s : Database.students) {
            if (s.getMarks().containsKey(course)) {
                double mark = s.getMarks().get(course).getFinalAttestation();
                totalSum += mark;
                if (mark > max) max = mark;
                if (mark < min) min = mark;
                count++;
            }
        }
        
        if (count == 0) return "No students registered for " + course.getCourseName();
        
        return "Report for " + course.getCourseName() + ":\n" +
               "Average: " + (totalSum / count) + "\n" +
               "Max: " + max + "\n" +
               "Min: " + min + "\n" +
               "Total students: " + count;
    }
	
	@Override
	public String toString() {
		return super.toString() + "Teacher [degree=" + degree + ", department=" + department + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(degree, department, officeHour, rating);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Teacher other = (Teacher) obj;
		return degree == other.degree && department == other.department && Objects.equals(officeHour, other.officeHour)
				&& Double.doubleToLongBits(rating) == Double.doubleToLongBits(other.rating);
	}
	
	@Override
	public int compareTo(Object o) {
		if (super.compareTo(o) == 0) {
			Teacher t = (Teacher)o;
			if (getRating() < t.getRating()) return -1;
			else return 1;
		}
		return 0;
	}
	
	
	
}
