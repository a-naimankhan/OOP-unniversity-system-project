package users;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import course.Course;
import mark.Mark;
import other.News;
import other.NewsTopic;
import research.Researcher;
import research.ResearchPaper;
import research.Journal;
import other.Request;
import other.RecommendationLetter;
import other.Startup;

public final class Database implements Serializable {

    private static final long serialVersionUID = 1L;
    private final static String BASEPATH = "data" + File.separator;
    private static Database instance = new Database(BASEPATH);
    private String value;
    private static Researcher currentTopResearcher = null;

    static {
        try {
            Files.createDirectories(Paths.get(BASEPATH));
        } catch (IOException e) {
            System.err.println("Failed to create data directory: " + e.getMessage());
        }
    }

    public static Vector<String> logs = new Vector<String>();

    public static synchronized void log(String action) {
        String entry = "[" + new java.util.Date() + "] " + action;
        logs.add(entry);
    }

	private Database() {}

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
	public static Vector<Journal> journals = new Vector<Journal>();
	public static Vector<Request> requests = new Vector<Request>();
	public static Vector<course.Attendance> attendanceRecords = new Vector<course.Attendance>();
	public static Vector<course.Room> rooms = new Vector<course.Room>();
	public static Vector<Startup> startups = new Vector<Startup>();
	public static Vector<RecommendationLetter> recommendationLetters = new Vector<RecommendationLetter>();

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
        
        if (top != null && top != currentTopResearcher) {
            currentTopResearcher = top;
            generateTopCitedNews(top);
        }
		return top;
	}

    public static Researcher getTopCitedResearcherOfSchool(FacultyType faculty) {
        Researcher top = null;
        int maxCitations = 0;
        for (Researcher r : getAllResearchers()) {
            FacultyType rFaculty = null;
            if (r instanceof Student) rFaculty = ((Student)r).getFaculty();
            else if (r instanceof Teacher) rFaculty = ((Teacher)r).getDepartment();
            
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

    private static void generateTopCitedNews(Researcher r) {
        String name = (r instanceof User) ? ((User)r).getFullName() : "A researcher";
        String title = "New Top Cited Researcher!";
        String text = "Congratulations to " + name + " for becoming the top cited researcher in our university!";
        
        News n = new News(title, text, new java.util.Date().toString(), NewsTopic.RESEARCH, (r instanceof User) ? (User)r : null);
        news.add(n);
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
		serializeJournals();
		serializeRequests();
		serializeLogs();
		serializeAttendance();
		serializeRooms();
		serializeStartups();
		serializeRecommendationLetters();
		saveCredentials();
	}

	public static void saveCredentials() {
		try (java.io.PrintWriter pw = new java.io.PrintWriter(
				new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(BASEPATH + "credentials.txt"), java.nio.charset.StandardCharsets.UTF_8))) {
			pw.println("=== University System — User Credentials ===");
			pw.println(String.format("%-30s %-20s %-15s %s", "Full Name", "Username", "Password", "Role"));
			pw.println("-".repeat(85));
			for (User u : users) {
				pw.println(String.format("%-30s %-20s %-15s %s",
						u.getFullName(), u.getUsername(), u.getPassword(),
						u.getClass().getSimpleName()));
			}
		} catch (Exception e) {
			System.out.println("Could not save credentials.txt: " + e.getMessage());
		}
	}

	/**
	 * Loads all serialized state back into the static collections.
	 * Silently ignores files that don't exist yet (first run).
	 */
	public static void load() {
		try {
			Vector<User> u = deserializeUser();
			if (u != null) users = u;
			Vector<Student> s = deserializeStudent();
			if (s != null) students = s;
			Vector<Teacher> t = deserializeTeacher();
			if (t != null) teachers = t;
			Vector<Admin> a = deserializeAdmin();
			if (a != null) admins = a;
			Vector<Employee> e = deserializeEmployee();
			if (e != null) employees = e;
			Vector<Manager> m = deserializeManager();
			if (m != null) managers = m;
			Vector<Course> c = deserializeCourse();
			if (c != null) courses = c;
			Vector<Mark> mk = deserializeMark();
			if (mk != null) marks = mk;
			Vector<News> n = deserializeNews();
			if (n != null) news = n;
			Vector<Journal> j = deserializeJournals();
			if (j != null) journals = j;
			Vector<Request> r = deserializeRequests();
			if (r != null) requests = r;
			Vector<String> l = deserializeLogs();
			if (l != null) logs = l;
			Vector<course.Attendance> att = deserializeAttendance();
			if (att != null) attendanceRecords = att;
			Vector<course.Room> rm = deserializeRooms();
			if (rm != null) rooms = rm;
			Vector<Startup> st = deserializeStartups();
			if (st != null) startups = st;
			Vector<RecommendationLetter> rl = deserializeRecommendationLetters();
			if (rl != null) recommendationLetters = rl;
		} catch (ClassNotFoundException ex) {
			System.err.println("Failed to load database: " + ex.getMessage());
		}
	}

	// Startups
	public static void serializeStartups() {
		try (FileOutputStream fos = new FileOutputStream(BASEPATH + "startups.txt");
			 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(startups);
		} catch (IOException e) { e.printStackTrace(); }
	}

	public static Vector<Startup> deserializeStartups() throws ClassNotFoundException {
		try (FileInputStream fis = new FileInputStream(BASEPATH + "startups.txt");
			 ObjectInputStream ois = new ObjectInputStream(fis)) {
			@SuppressWarnings("unchecked")
			Vector<Startup> s = (Vector<Startup>) ois.readObject();
			return s;
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) { e.printStackTrace(); }
		return null;
	}

	// RecommendationLetters
	public static void serializeRecommendationLetters() {
		try (FileOutputStream fos = new FileOutputStream(BASEPATH + "recletters.txt");
			 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(recommendationLetters);
		} catch (IOException e) { e.printStackTrace(); }
	}

	public static Vector<RecommendationLetter> deserializeRecommendationLetters() throws ClassNotFoundException {
		try (FileInputStream fis = new FileInputStream(BASEPATH + "recletters.txt");
			 ObjectInputStream ois = new ObjectInputStream(fis)) {
			@SuppressWarnings("unchecked")
			Vector<RecommendationLetter> r = (Vector<RecommendationLetter>) ois.readObject();
			return r;
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) { e.printStackTrace(); }
		return null;
	}

	// ── RegEx search ──────────────────────────────────────────────────────────

	public static List<User> searchUsers(String regex) {
		List<User> result = new ArrayList<>();
		for (User u : users) {
			if (u.getFullName() != null && u.getFullName().matches("(?i).*" + regex + ".*"))
				result.add(u);
			else if (u.getUsername() != null && u.getUsername().matches("(?i).*" + regex + ".*"))
				result.add(u);
		}
		return result;
	}

	public static List<Course> searchCourses(String regex) {
		List<Course> result = new ArrayList<>();
		for (Course c : courses) {
			if (c.getCourseName() != null && c.getCourseName().matches("(?i).*" + regex + ".*"))
				result.add(c);
			else if (c.getCourseCode() != null && c.getCourseCode().matches("(?i).*" + regex + ".*"))
				result.add(c);
		}
		return result;
	}

	public static List<ResearchPaper> searchResearchPapers(String regex) {
		List<ResearchPaper> result = new ArrayList<>();
		for (Researcher r : getAllResearchers()) {
			for (ResearchPaper p : r.getResearchPapers()) {
				if (p.getTitle() != null && p.getTitle().matches("(?i).*" + regex + ".*"))
					if (!result.contains(p)) result.add(p);
			}
		}
		return result;
	}

	// Journals
	public static void serializeJournals() {
		try (FileOutputStream fis = new FileOutputStream(BASEPATH + "journals.txt")){
			ObjectOutputStream oos = new ObjectOutputStream(fis);
			oos.writeObject(journals);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Vector<Journal> deserializeJournals() throws ClassNotFoundException {
		try (FileInputStream fs = new FileInputStream(BASEPATH + "journals.txt")){
			ObjectInputStream ois = new ObjectInputStream(fs);
			@SuppressWarnings("unchecked")
			Vector<Journal> j = (Vector<Journal>)ois.readObject();
			return j;
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Attendance
	public static void serializeAttendance() {
		try (FileOutputStream fis = new FileOutputStream(BASEPATH + "attendance.txt")){
			ObjectOutputStream oos = new ObjectOutputStream(fis);
			oos.writeObject(attendanceRecords);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Vector<course.Attendance> deserializeAttendance() throws ClassNotFoundException {
		try (FileInputStream fs = new FileInputStream(BASEPATH + "attendance.txt")){
			ObjectInputStream ois = new ObjectInputStream(fs);
			@SuppressWarnings("unchecked")
			Vector<course.Attendance> a = (Vector<course.Attendance>)ois.readObject();
			return a;
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Rooms
	public static void serializeRooms() {
		try (FileOutputStream fis = new FileOutputStream(BASEPATH + "rooms.txt")){
			ObjectOutputStream oos = new ObjectOutputStream(fis);
			oos.writeObject(rooms);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Vector<course.Room> deserializeRooms() throws ClassNotFoundException {
		try (FileInputStream fs = new FileInputStream(BASEPATH + "rooms.txt")){
			ObjectInputStream ois = new ObjectInputStream(fs);
			@SuppressWarnings("unchecked")
			Vector<course.Room> r = (Vector<course.Room>)ois.readObject();
			return r;
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Requests
	public static void serializeRequests() {
		try (FileOutputStream fis = new FileOutputStream(BASEPATH + "requests.txt")){
			ObjectOutputStream oos = new ObjectOutputStream(fis);
			oos.writeObject(requests);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Vector<Request> deserializeRequests() throws ClassNotFoundException {
		try (FileInputStream fs = new FileInputStream(BASEPATH + "requests.txt")){
			ObjectInputStream ois = new ObjectInputStream(fs);
			@SuppressWarnings("unchecked")
			Vector<Request> r = (Vector<Request>)ois.readObject();
			return r;
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Logs
	public static void serializeLogs() {
		try (FileOutputStream fis = new FileOutputStream(BASEPATH + "logs.txt")){
			ObjectOutputStream oos = new ObjectOutputStream(fis);
			oos.writeObject(logs);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Vector<String> deserializeLogs() throws ClassNotFoundException {
		try (FileInputStream fs = new FileInputStream(BASEPATH + "logs.txt")){
			ObjectInputStream ois = new ObjectInputStream(fs);
			@SuppressWarnings("unchecked")
			Vector<String> l = (Vector<String>)ois.readObject();
			return l;
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
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
