package course;

import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;

import users.Database;
import users.FacultyType;
import users.Teacher;

public class Course implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String courseName;
	private Period semester;
	private String courseCode;
	private int credit;
	private boolean isElective;
	private int studentLimit;
	private Course prerequisites;
	private FacultyType department;
	private Vector<Teacher> teachers;
	
	{
		teachers = new Vector<Teacher>();
	}
	
	public Course() {
		
	}
	public Course(String courseName, Period semester, String courseCode, int credit, boolean isElective, int studentLimit, Course prerequisites, FacultyType department) {
		this.courseName = courseName;
		this.semester = semester;
		this.courseCode = courseCode;
		this.credit = credit;
		this.isElective = isElective;
		this.studentLimit = studentLimit;
		this.prerequisites = prerequisites;
		this.department = department;
	}
	
	
//	public void addStudent(Student student) {
//		students.add(student);
//	}
//	public void removeStudent(Student student) {
//		students.remove(student);
//	}
	
	public boolean teaches(Teacher teacher) {
		if (Database.teachers.contains(teacher)) {
			this.teachers.add(teacher);
			return true;
		}
		return false;
	}
	public boolean removeTeacher(Teacher teacher) {
		if (this.teachers.contains(teacher)) {
			this.teachers.remove(teacher);
			return true;
		}
		return false;
	}
	
	public Vector<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(Vector<Teacher> teachers) {
		this.teachers = teachers;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Period getSemester() {
		return semester;
	}
	public void setSemester(Period semester) {
		this.semester = semester;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public boolean isElective() {
		return isElective;
	}
	public void setElective(boolean isElective) {
		this.isElective = isElective;
	}
	public Course getPrerequisites() {
		return prerequisites;
	}
	public void setPrerequisites(Course prerequisites) {
		this.prerequisites = prerequisites;
	}
	public FacultyType getDepartment() {
		return department;
	}
	public void setDepartment(FacultyType department) {
		this.department = department;
	}
//	public Vector<Student> getStudents() {
//		return students;
//	}
//	public void setStudents(Vector<Student> students) {
//		this.students = students;
//	}
	public int getStudentLimit() {
		return studentLimit;
	}
	public void setStudentLimit(int studentLimit) {
		this.studentLimit = studentLimit;
	}
	@Override
	public String toString() {
		return "Course [courseName=" + courseName + ", semester=" + semester + ", courseCode=" + courseCode
				+ ", credit=" + credit + ", isElective=" + isElective + ", studentLimit=" + studentLimit
				+ ", prerequisites=" + prerequisites + ", department=" + department + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(courseCode, courseName, credit, department, isElective, prerequisites, semester);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(courseCode, other.courseCode) && Objects.equals(courseName, other.courseName)
				&& credit == other.credit && department == other.department && isElective == other.isElective
				&& Objects.equals(prerequisites, other.prerequisites) && semester == other.semester;
	}
	

	
	
}