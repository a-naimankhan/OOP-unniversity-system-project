package users;

import java.io.Serializable;
import java.util.Collections;
import java.util.Objects;

import course.Course;
import other.News;

public class Manager extends Employee implements Serializable, Comparable<Object>{

	private static final long serialVersionUID = 1L;
	
	private ManagerType type;
	
	public Manager() {
		
	}
	public Manager(String fullName, String username, String password, int salary, ManagerType type) {
		super(fullName, username, password, salary);
		this.type = type;
	}
	
	public boolean addNews(News news) {
		if (Database.news.contains(news)) return false;
		Database.news.add(news);
		return true;
	}
	public boolean removeNews(News news) {
		if (!Database.news.contains(news)) return false;
		Database.news.remove(news);
		return true;
	}
	public boolean updateNews(News oldNews, News newNews) {
		Database.news.add(newNews);
		Database.news.remove(oldNews);
		return true;
	}
	
	public boolean addCourse(Course course) {
		if (Database.courses.contains(course)) return false;
		Database.courses.add(course);
		return true;
	}
	public boolean removeCourse(Course course) {
		if (!Database.courses.contains(course)) return false;
		Database.courses.remove(course);
		return true;
	}
	public boolean assignCoursesToTeachers(Course course, Teacher teacher) {
		return course.teaches(teacher);
	}
	
	public boolean approveRegistration(Student student, Course course) {
		return true;
	}

    public void signRequest(other.Request request) {
        request.setSignedBy(this);
        Database.log("Signed request: " + request.getDescription(), this.getUsername());
    }
	
	public String viewStudentsByName() {
		String info = "";
			
		Collections.sort(Database.students, new SortByNames());
		info += "Sorting by name:\n";
		for(Student s : Database.students) {
			info += s.getFullName() + " ";
		}
		return info;
	}
	
	public String viewStudentsByGPA() {
		String info = "";
			
		Collections.sort(Database.students, new SortByGPA());
		info += "Sorting by gpa:\n";
		for(Student s : Database.students) {
			info += s.getFullName() + " " + s.getGpa() + " ";
		}
		return info;
	}
	
	public String viewTeachersByName() {
		String info = "";
			
		Collections.sort(Database.teachers, new SortByNames());
		info += "Sorting by name:\n";
		for(Teacher t : Database.teachers) {
			info += t.getFullName() + " ";
		}
		return info;
	}

    public String generateAcademicReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("Academic Report:\n");
        
        for (FacultyType faculty : FacultyType.values()) {
            double facultyGpaSum = 0;
            int studentCount = 0;
            for (Student s : Database.students) {
                if (s.getFaculty() == faculty) {
                    facultyGpaSum += s.getGpa();
                    studentCount++;
                }
            }
            if (studentCount > 0) {
                sb.append("Faculty: ").append(faculty).append("\n");
                sb.append("  Avg GPA: ").append(facultyGpaSum / studentCount).append("\n");
                sb.append("  Students: ").append(studentCount).append("\n");
            }
        }
        return sb.toString();
    }

	public ManagerType getType() {
		return type;
	}
	public void setType(ManagerType type) {
		this.type = type;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(type);
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
		Manager other = (Manager) obj;
		return type == other.type;
	}
	
	
	
}
