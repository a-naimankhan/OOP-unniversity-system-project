package users;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

import course.Course;
import mark.AttestationType;
import mark.Mark;

public class Student extends User implements Serializable, Comparable<Object> {
	
	private static final long serialVersionUID = 1L;
	
	int creditLimit = 21;
	private double gpa;
	private String id;
	private int studyYear;
	private DegreeStudent degree;
	private FacultyType faculty;
	private String speciality;
	private int totalCredit;
	private HashMap<Course, Mark> marks;
	
	{
		marks = new HashMap<Course, Mark>();
	}
	
	public Student() {
		
	}
	public Student(String fullName, String username, String password, String id, int studyYear, DegreeStudent degree, FacultyType faculty, String speciality) {
		super(fullName, username, password);
		this.id = id;
		this.studyYear = studyYear;
		this.degree = degree;
		this.faculty = faculty;
		this.speciality = speciality;
	}
	
	public boolean registerCourse(Course course) {
		if (Database.courses.contains(course)) {
			if (this.totalCredit + course.getCredit() <= creditLimit) {
				marks.put(course, new Mark());
				setTotalCredit(this.totalCredit + course.getCredit());
				return true;
			}
		}
		return false;
	}
	
	public boolean dropCourse(Course course) {
		if(this.getMarks().get(course).getFinalAttestation() == 0) {
			marks.remove(course);
			return true;
		}
		return false;
	}
	
	public void rateTeachers(Teacher teacher, double rating) {
		teacher.setRating(rating);
	}
	
	public String viewTeacher(Teacher teacher) {
		String info = "";
		info += "Name: " + teacher.getFullName() + ", courses: " + teacher.viewCourses();
		return info;
	}
	
	public Set<Course> viewCourse() {
		return marks.keySet();
	}
	
	public HashMap<Course, Mark> getMarks() {
		return marks;
	}
	
	public Mark viewMarks(Course course) {
		return marks.get(course);
	}
	
	public double getGpa() {
		int cnt = 0;
		double point = 0;
		for (Course c : Database.courses) {
			if (this.getMarks().containsKey(c)) {
				point += this.getMarks().get(c).getFinalAttestation();
				cnt++;
			}
		}
		Mark m = new Mark();
		m.setFinalAttestation(point/cnt);
		gpa = m.convertToGPA();
		return gpa;
	}
	
	
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getStudyYear() {
		return studyYear;
	}
	public void setStudyYear(int studyYear) {
		this.studyYear = studyYear;
	}
	public DegreeStudent getDegree() {
		return degree;
	}
	public void setDegree(DegreeStudent degree) {
		this.degree = degree;
	}
	public FacultyType getFaculty() {
		return faculty;
	}
	public void setFaculty(FacultyType faculty) {
		this.faculty = faculty;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public int getTotalCredit() {
		return totalCredit;
	}
	public void setTotalCredit(int totalCredit) {
		this.totalCredit = totalCredit;
	}
	public void setMarks(HashMap<Course, Mark> marks) {
		this.marks = marks;
	}
	
	public void leaveOrganization(StudentOrganization org) {
		if (organizations.contains(org)) {
			organizations.remove(org);
			org.getMembers().remove(this);
		}
	}
	
	public void becomeHead(StudentOrganization org) {
		if (organizations.contains(org)) {
			org.setHead(this);
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + "Student [gpa=" + gpa + ", id=" + id + ", studyYear=" + studyYear + ", degree=" + degree + ", faculty="
				+ faculty + ", speciality=" + speciality + ", totalCredit=" + totalCredit + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(degree, faculty, gpa, id, speciality, studyYear, totalCredit);
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
		Student other = (Student) obj;
		return degree == other.degree && faculty == other.faculty
				&& Double.doubleToLongBits(gpa) == Double.doubleToLongBits(other.gpa) && Objects.equals(id, other.id)
				&& Objects.equals(speciality, other.speciality) && studyYear == other.studyYear
				&& totalCredit == other.totalCredit;
	}
	@Override
	public int compareTo(Object o) {
		if (super.compareTo(o) == 0) {
			Student s = (Student)o;
			if (gpa < s.gpa) return -1;
			else return 1;
		}
		return 0;
	}
	
}