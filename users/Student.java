package users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import course.Course;
import exceptions.CreditLimitExceededException;
import exceptions.MaxFailException;
import mark.Mark;
import exceptions.MaxFailException;

public class Student extends User implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final int MAX_CREDITS = 21;

	private double gpa;
	private String id;
	private int studyYear;
	private DegreeStudent degree;
	private FacultyType faculty;
	private String speciality;
	private int totalCredit;
	private HashMap<Course, Mark> marks;
	private Map<Course, Integer> failCount;
	private List<StudentOrganization> organizations;

	{
		marks = new HashMap<>();
		failCount = new HashMap<>();
		organizations = new ArrayList<>();
	}

	public Student() {}

	public Student(String fullName, String username, String password, String id,
			int studyYear, DegreeStudent degree, FacultyType faculty, String speciality) {
		super(fullName, username, password);
		this.id = id;
		this.studyYear = studyYear;
		this.degree = degree;
		this.faculty = faculty;
		this.speciality = speciality;
	}

	public boolean registerCourse(Course course)
			throws CreditLimitExceededException, MaxFailException {
		if (!Database.courses.contains(course)) return false;

		int fails = failCount.getOrDefault(course, 0);
		if (fails >= 3) {
			throw new MaxFailException(
				"Cannot register for \"" + course.getCourseName() + "\": failed 3 times already.");
		}

		if (this.totalCredit + course.getCredit() > MAX_CREDITS) {
			throw new CreditLimitExceededException(
				"Cannot register for \"" + course.getCourseName() + "\": would exceed " + MAX_CREDITS + " credit limit.");
		}

		marks.put(course, new Mark());
		setTotalCredit(this.totalCredit + course.getCredit());
		return true;
	}

	public boolean dropCourse(Course course) {
		Mark m = marks.get(course);
		if (m == null) return false;
		if (m.getFinalAttestation() == 0) {
			marks.remove(course);
			setTotalCredit(this.totalCredit - course.getCredit());
			return true;
		}
		return false;
	}

	public void recordFail(Course course) {
		failCount.put(course, failCount.getOrDefault(course, 0) + 1);
	}

	public void joinOrganization(StudentOrganization org) {
		org.addMember(this);
	}

	public void leaveOrganization(StudentOrganization org) {
		org.removeMember(this);
	}

	public void becomeHead(StudentOrganization org) {
		org.setHead(this);
	}

	public void rateTeachers(Teacher teacher, double rating) {
		teacher.setRating(rating);
	}

	public String viewTeacher(Teacher teacher) {
		return "Name: " + teacher.getFullName() + ", courses: " + teacher.viewCourses();
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
		if (cnt == 0) {
			gpa = 0.0;
			return gpa;
		}
		Mark m = new Mark();
		m.setFirstAttestation(point / cnt);
		gpa = m.convertToGPA();
		return gpa;
	}

	public List<StudentOrganization> getOrganizations() { return organizations; }

	public Map<Course, Integer> getFailCount() { return failCount; }

	public void setGpa(double gpa) { this.gpa = gpa; }
	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
	public int getStudyYear() { return studyYear; }
	public void setStudyYear(int studyYear) { this.studyYear = studyYear; }
	public DegreeStudent getDegree() { return degree; }
	public void setDegree(DegreeStudent degree) { this.degree = degree; }
	public FacultyType getFaculty() { return faculty; }
	public void setFaculty(FacultyType faculty) { this.faculty = faculty; }
	public String getSpeciality() { return speciality; }
	public void setSpeciality(String speciality) { this.speciality = speciality; }
	public int getTotalCredit() { return totalCredit; }
	public void setTotalCredit(int totalCredit) { this.totalCredit = totalCredit; }
	public void setMarks(HashMap<Course, Mark> marks) { this.marks = marks; }

	@Override
	public String toString() {
		return super.toString() + " Student [id=" + id + ", year=" + studyYear
				+ ", degree=" + degree + ", faculty=" + faculty
				+ ", speciality=" + speciality + ", totalCredit=" + totalCredit + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Student other = (Student) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int compareTo(User o) {
		int byName = super.compareTo(o);
		if (byName != 0) return byName;
		if (!(o instanceof Student)) return 0;
		return Double.compare(this.gpa, ((Student) o).gpa);
	}
}
