package course;

import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;

import users.Database;
import users.DegreeStudent;
import users.FacultyType;
import users.Teacher;

public class Course implements Serializable{

	private static final long serialVersionUID = 1L;

	private String courseName;
	private Period semester;
	private String courseCode;
	private int credit;
	private CourseType courseType;
	private CourseType courseType;
	private int studentLimit;
	private Course prerequisites;
	private FacultyType department;
	private int targetYear;
	private DegreeStudent targetDegree;
	private Vector<Teacher> teachers;

	{
		teachers = new Vector<Teacher>();
	}

	public Course() {
		this.courseType = CourseType.MAJOR;
	}

	public Course(String courseName, Period semester, String courseCode, int credit,
			CourseType courseType, int studentLimit, Course prerequisites, FacultyType department,
			int targetYear, DegreeStudent targetDegree) {
		this.courseName = courseName;
		this.semester = semester;
		this.courseCode = courseCode;
		this.credit = credit;
		this.courseType = courseType;
		this.courseType = courseType;
		this.studentLimit = studentLimit;
		this.prerequisites = prerequisites;
		this.department = department;
		this.targetYear = targetYear;
		this.targetDegree = targetDegree;
	}

	// Backwards-compatible constructor — boolean isElective is mapped to FREE_ELECTIVE / MAJOR
	public Course(String courseName, Period semester, String courseCode, int credit,
			boolean isElective, int studentLimit, Course prerequisites, FacultyType department) {
		this(courseName, semester, courseCode, credit,
				isElective ? CourseType.FREE_ELECTIVE : CourseType.MAJOR,
				studentLimit, prerequisites, department, 0, null);
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
	public CourseType getCourseType() {
		return courseType;
	}
	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}
	public boolean isElective() {
		return courseType == CourseType.FREE_ELECTIVE || courseType == CourseType.MINOR;
	}
	public int getTargetYear() {
		return targetYear;
	}
	public void setTargetYear(int targetYear) {
		this.targetYear = targetYear;
	}
	public DegreeStudent getTargetDegree() {
		return targetDegree;
	}
	public void setTargetDegree(DegreeStudent targetDegree) {
		this.targetDegree = targetDegree;
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
				+ ", credit=" + credit + ", type=" + courseType + ", studentLimit=" + studentLimit
				+ ", targetYear=" + targetYear + ", targetDegree=" + targetDegree + ", department=" + department + "]";
	}
	@Override
	public int hashCode() {
		// courseCode alone — including prerequisites caused stack overflow on deep chains
		return Objects.hash(courseCode);
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
		return Objects.equals(courseCode, other.courseCode);
	}
	

	
	
}