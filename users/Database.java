package users;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import course.Course;
import mark.Mark;
import other.News;
import research.Researcher;
import research.ResearchPaper;

public final class Database implements Serializable{

	private static final long serialVersionUID = 1L;
	private final static String BASEPATH = "C:\\OOP\\test\\";
	private static Database instance = new Database(BASEPATH);
	private String value;

	public Database() {

	}
	private Database(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public static Database getInstance() {
		return instance;
	}

	public static Vector<User> users = new Vector<User>();
	public static Vector<Student> students = new Vector<Student>();
	public static Vector<Employee> employees = new Vector<Employee>();
	public static Vector<Manager> managers = new Vector<Manager>();
	public static Vector<Teacher> teachers = new Vector<Teacher>();
	public static Vector<Admin> admins = new Vector<Admin>();
	public static Vector<Course> courses = new Vector<Course>();
	public static Vector<Mark> marks = new Vector<Mark>();
	public static Vector<News> news = new Vector<News>();
	public static Vector<String> comments = new Vector<String>();


	/**
	 * Collects all researchers from users list
	 */
	public static List<Researcher> getAllResearchers() {
		List<Researcher> researchers = new ArrayList<>();
		for (User u : users) {
			if (u instanceof Researcher) {
				researchers.add((Researcher) u);
			}
		}
		return researchers;
	}

	/**
	 * Prints ALL research papers of ALL researchers in the university, sorted by given comparator
	 */
	public static void printAllResearchPapers(Comparator<ResearchPaper> c) {
		List<ResearchPaper> allPapers = new ArrayList<>();
		for (Researcher r : getAllResearchers()) {
			for (ResearchPaper p : r.getResearchPapers()) {
				if (!allPapers.contains(p)) {
					allPapers.add(p);
				}
			}
		}
		Collections.sort(allPapers, c);
		System.out.println("=== All Research Papers (" + allPapers.size() + ") ===");
		for (ResearchPaper p : allPapers) {
			System.out.println(p);
		}
	}

	/**
	 * Returns top cited researcher among all users
	 */
	public static Researcher getTopCitedResearcher() {
		Researcher top = null;
		int maxCitations = 0;
		for (Researcher r : getAllResearchers()) {
			int total = 0;
			for (ResearchPaper p : r.getResearchPapers()) {
				total += p.getCitations();
			}
			if (total > maxCitations) {
				maxCitations = total;
				top = r;
			}
		}
		return top;
	}

	/**
	 * Returns top cited researcher of a specific school/faculty
	 */
	public static Researcher getTopCitedResearcherOfSchool(FacultyType faculty) {
		Researcher top = null;
		int maxCitations = 0;
		for (Researcher r : getAllResearchers()) {
			// check faculty
			FacultyType rFaculty = null;
			if (r instanceof Student) rFaculty = ((Student) r).getFaculty();
			else if (r instanceof Teacher) rFaculty = ((Teacher) r).getDepartment();

			if (rFaculty == faculty) {
				int total = 0;
				for (ResearchPaper p : r.getResearchPapers()) {
					total += p.getCitations();
				}
				if (total > maxCitations) {
					maxCitations = total;
					top = r;
				}
			}
		}
		return top;
	}

	public static void save() {
		serializeUser();
		serializeStudent();
		serializeTeacher();
		serializeAdmin();
		serializeEmployee();
		serializeCourse();
		serializeMark();
		serializeManager();
		serializeNews();
	}

	public static void serializeUser() {
		try (FileOutputStream fis = new FileOutputStream(BASEPATH + "users.txt")){
			ObjectOutputStream oos = new ObjectOutputStream(fis);
			oos.writeObject(users);
			oos.flush();
			oos.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public static Vector<User> deserializeUser() throws ClassNotFoundException {
		try (FileInputStream fs = new FileInputStream(BASEPATH + "users.txt")){
			ObjectInputStream ois = new ObjectInputStream(fs);
			@SuppressWarnings("unchecked")
			Vector<User> u = (Vector<User>)ois.readObject();
			return u;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
		return users;
	}

//	Student
	public static void serializeStudent() {
		try (FileOutputStream fs = new FileOutputStream(BASEPATH + "students.txt")){
			ObjectOutputStream oos = new ObjectOutputStream(fs);
			oos.writeObject(students);
			oos.flush();
			oos.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static Vector<Student> deserializeStudent() throws ClassNotFoundException {
		try (FileInputStream fs = new FileInputStream(BASEPATH + "students.txt")){
			ObjectInputStream ois = new ObjectInputStream(fs);
			@SuppressWarnings("unchecked")
			Vector<Student> s = (Vector<Student>)ois.readObject();
			return s;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
		return students;
	}

//	Employee
	public static void serializeEmployee() {
		try (FileOutputStream fis = new FileOutputStream(BASEPATH + "employees.txt")){
			ObjectOutputStream oos = new ObjectOutputStream(fis);
			oos.writeObject(employees);
			oos.flush();
			oos.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static Vector<Employee> deserializeEmployee() throws ClassNotFoundException {
		try (FileInputStream fs = new FileInputStream(BASEPATH + "employees.txt")){
			ObjectInputStream ois = new ObjectInputStream(fs);
			@SuppressWarnings("unchecked")
			Vector<Employee> e = (Vector<Employee>)ois.readObject();
			return e;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
		return employees;
	}

//	Manager
	public static void serializeManager() {
		try (FileOutputStream fis = new FileOutputStream(BASEPATH + "managers.txt")){
			ObjectOutputStream oos = new ObjectOutputStream(fis);
			oos.writeObject(managers);
			oos.flush();
			oos.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static Vector<Manager> deserializeManager() throws ClassNotFoundException {
		try (FileInputStream fs = new FileInputStream(BASEPATH + "managers.txt")){
			ObjectInputStream ois = new ObjectInputStream(fs);
			@SuppressWarnings("unchecked")
			Vector<Manager> m = (Vector<Manager>)ois.readObject();
			return m;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
		return managers;
	}

//	Teacher
	public static void serializeTeacher() {
		try (FileOutputStream fis = new FileOutputStream(BASEPATH + "teachers.txt")){
			ObjectOutputStream oos = new ObjectOutputStream(fis);
			oos.writeObject(teachers);
			oos.flush();
			oos.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static Vector<Teacher> deserializeTeacher() throws ClassNotFoundException {
		try (FileInputStream fs = new FileInputStream(BASEPATH + "teachers.txt")){
			ObjectInputStream ois = new ObjectInputStream(fs);
			@SuppressWarnings("unchecked")
			Vector<Teacher> t = (Vector<Teacher>)ois.readObject();
			return t;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
		return teachers;
	}

//	Admin
	public static void serializeAdmin() {
		try (FileOutputStream fis = new FileOutputStream(BASEPATH + "admins.txt")){
			ObjectOutputStream oos = new ObjectOutputStream(fis);
			oos.writeObject(admins);
			oos.flush();
			oos.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static Vector<Admin> deserializeAdmin() throws ClassNotFoundException {
		try (FileInputStream fs = new FileInputStream(BASEPATH + "admins.txt")){
			ObjectInputStream ois = new ObjectInputStream(fs);
			@SuppressWarnings("unchecked")
			Vector<Admin> a = (Vector<Admin>)ois.readObject();
			return a;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
		return admins;
	}

//	Course
	public static void serializeCourse() {
		try (FileOutputStream fis = new FileOutputStream(BASEPATH + "courses.txt")){
			ObjectOutputStream oos = new ObjectOutputStream(fis);
			oos.writeObject(courses);
			oos.flush();
			oos.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static Vector<Course> deserializeCourse() throws ClassNotFoundException {
		try (FileInputStream fs = new FileInputStream(BASEPATH + "courses.txt")){
			ObjectInputStream ois = new ObjectInputStream(fs);
			@SuppressWarnings("unchecked")
			Vector<Course> c = (Vector<Course>)ois.readObject();
			return c;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
		return courses;
	}

//	Mark
	public static void serializeMark() {
		try (FileOutputStream fis = new FileOutputStream(BASEPATH + "marks.txt")){
			ObjectOutputStream oos = new ObjectOutputStream(fis);
			oos.writeObject(marks);
			oos.flush();
			oos.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static Vector<Mark> deserializeMark() throws ClassNotFoundException {
		try (FileInputStream fs = new FileInputStream(BASEPATH + "marks.txt")){
			ObjectInputStream ois = new ObjectInputStream(fs);
			@SuppressWarnings("unchecked")
			Vector<Mark> m = (Vector<Mark>)ois.readObject();
			return m;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
		return marks;
	}

//	News
	public static void serializeNews() {
		try (FileOutputStream fis = new FileOutputStream(BASEPATH + "news.txt")){
			ObjectOutputStream oos = new ObjectOutputStream(fis);
			oos.writeObject(news);
			oos.flush();
			oos.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static Vector<News> deserializeNews() throws ClassNotFoundException {
		try (FileInputStream fs = new FileInputStream(BASEPATH + "news.txt")){
			ObjectInputStream ois = new ObjectInputStream(fs);
			@SuppressWarnings("unchecked")
			Vector<News> n = (Vector<News>)ois.readObject();
			return n;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
		return news;
	}
}
