package users;

import java.io.Serializable;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

import course.Course;
import mark.AttestationType;
import mark.Mark;
import other.Complaint;
import other.Request;
import other.Time;
import other.UrgencyLevel;

public class Teacher extends Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	private DegreeTeacher degree;
	private FacultyType department;
	private double rating;
	private int ratedCnt;
	private Time officeHour;

	public Teacher() {}

	public Teacher(String fullName, String username, String password, int salary,
			DegreeTeacher degree, FacultyType department) {
		super(fullName, username, password, salary);
		this.degree = degree;
		this.department = department;
		this.rating = 0;
		this.ratedCnt = 0;
	}

	public void sendComplaint(Student student, String text, UrgencyLevel urgency, User dean) {
		Complaint c = new Complaint(text, this, dean, new java.util.Date().toString(), student, urgency);
		// Notify the dean via the Observer mechanism
		dean.update("NEW COMPLAINT [" + urgency + "] from " + this.getFullName()
				+ " about student " + student.getFullName());
		// Store in Database as a Request so TechSupport and Admin can see it
		Request req = new Request("Complaint about " + student.getFullName() + ": " + text,
				this, new java.util.Date().toString(), urgency);
		Database.requests.add(req);
		Database.log("Complaint filed by " + this.getFullName() + " against student " + student.getFullName());
		System.out.println("Complaint sent and logged.");
	}

	public boolean putMark(Course course, Student student, double point, AttestationType period) {
		if (!student.getMarks().containsKey(course)) return false;

		Mark existing = student.getMarks().get(course);

		if (period == AttestationType.FIRST) {
			// cap at 30 and accumulate — mutate existing mark, do NOT replace it
			double newFirst = existing.getFirstAttestation() + point;
			if (newFirst > 30) newFirst = 30;
			existing.setFirstAttestation(newFirst);
		} else if (period == AttestationType.SECOND) {
			double newSecond = existing.getSecondAttestation() + point;
			if (newSecond > 30) newSecond = 30;
			existing.setSecondAttestation(newSecond);
		} else {
			// EXAM — cap at 40
			double newExam = existing.getExamMark() + point;
			if (newExam > 40) newExam = 40;
			existing.setExamMark(newExam);
		}

		// Record fail if final < 50 and attestations are complete (exam was just set)
		if (period == AttestationType.EXAM && existing.getFinalAttestation() < 50) {
			student.recordFail(course);
		}

		Database.marks.add(existing);
		return true;
	}

	public String generateReport(Course course) {
		List<Double> scores = new ArrayList<>();
		for (Student s : Database.students) {
			if (s.getMarks().containsKey(course)) {
				double score = s.getMarks().get(course).getFinalAttestation();
				scores.add(score);
			}
		}
		if (scores.isEmpty()) return "No students enrolled in " + course.getCourseName();

		double sum = 0, min = Double.MAX_VALUE, max = Double.MIN_VALUE;
		int passing = 0;
		for (double sc : scores) {
			sum += sc;
			if (sc < min) min = sc;
			if (sc > max) max = sc;
			if (sc >= 50) passing++;
		}
		double avg = sum / scores.size();
		return String.format("=== Report: %s ===%n  Students: %d%n  Avg: %.1f%n  Min: %.1f  Max: %.1f%n  Passing: %d/%d",
				course.getCourseName(), scores.size(), avg, min, max, passing, scores.size());
	}

	public String viewCourses() {
		StringBuilder sb = new StringBuilder();
		for (Course c : Database.courses) {
			if (c.getTeachers().contains(this)) sb.append(c.getCourseName()).append(" ");
		}
		return sb.toString();
	}

	public String viewStudents() {
		StringBuilder sb = new StringBuilder();
		for (Student s : Database.students) {
			for (Course c : Database.courses) {
				if (c.getTeachers().contains(this) && s.viewCourse().contains(c)) {
					sb.append(s.toString()).append("\n");
				}
			}
		}
		return sb.toString();
	}

	public DegreeTeacher getDegree() { return degree; }
	public void setDegree(DegreeTeacher degree) { this.degree = degree; }
	public FacultyType getDepartment() { return department; }
	public void setDepartment(FacultyType department) { this.department = department; }

	public double getRating() {
		return (ratedCnt == 0) ? 0 : rating / ratedCnt;
	}

	public void setRating(double newRating) {
		// accumulate the raw sum; getRating() divides on demand
		this.rating += newRating;
		this.ratedCnt++;
	}

	public Time getOfficeHour() { return officeHour; }
	public void setOfficeHour(Time officeHour) { this.officeHour = officeHour; }

	@Override
	public String toString() {
		return super.toString() + " Teacher [degree=" + degree + ", department=" + department + "]";
	}

	@Override
	public int hashCode() {
		// rating and officeHour are mutable — use only stable identity fields
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(degree, department);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (getClass() != obj.getClass()) return false;
		Teacher other = (Teacher) obj;
		return degree == other.degree && department == other.department;
	}

	@Override
	public int compareTo(User o) {
		int byName = super.compareTo(o);
		if (byName != 0) return byName;
		if (!(o instanceof Teacher)) return 0;
		return Double.compare(this.getRating(), ((Teacher) o).getRating());
	}
}
