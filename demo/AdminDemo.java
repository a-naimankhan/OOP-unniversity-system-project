package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import course.Course;
import users.Admin;
import users.Database;
import users.DegreeStudent;
import users.DegreeTeacher;
import users.FacultyType;
import users.Manager;
import users.ManagerType;
import users.Student;
import users.Teacher;
import users.User;

public class AdminDemo {
	static Admin admin = null;
	static BufferedReader br = null;

	private static void addUser() throws IOException {
		System.out.println("Enter user type (student/teacher/manager/admin): ");
		String type = br.readLine();
		System.out.println("Enter full name: ");
		String fullName = br.readLine();
		System.out.println("Enter username: ");
		String username = br.readLine();
		System.out.println("Enter password: ");
		String password = br.readLine();

		if (type.equalsIgnoreCase("student")) {
			System.out.println("Enter student ID: ");
			String id = br.readLine();
			System.out.println("Enter study year: ");
			int year = Integer.parseInt(br.readLine());
			admin.addUser(new Student(fullName, username, password, id, year,
					DegreeStudent.BACHELOR, FacultyType.FIT, "CS"));
			System.out.println("Student added.");
		} else if (type.equalsIgnoreCase("teacher")) {
			System.out.println("Enter salary: ");
			int salary = Integer.parseInt(br.readLine());
			admin.addUser(new Teacher(fullName, username, password, salary,
					DegreeTeacher.LECTURER, FacultyType.FIT));
			System.out.println("Teacher added.");
		} else if (type.equalsIgnoreCase("manager")) {
			System.out.println("Enter salary: ");
			int salary = Integer.parseInt(br.readLine());
			admin.addUser(new Manager(fullName, username, password, salary, ManagerType.OR));
			System.out.println("Manager added.");
		} else if (type.equalsIgnoreCase("admin")) {
			System.out.println("Enter salary: ");
			int salary = Integer.parseInt(br.readLine());
			admin.addUser(new Admin(fullName, username, password, salary));
			System.out.println("Admin added.");
		} else {
			System.out.println("Unknown type.");
		}
	}

	private static void removeUser() throws IOException {
		System.out.println("Enter username to remove: ");
		String username = br.readLine();
		for (User u : Database.users) {
			if (u.getUsername().equals(username)) {
				admin.removeUser(u);
				System.out.println("Removed.");
				return;
			}
		}
		System.out.println("User not found.");
	}

	private static void viewUsers() {
		System.out.println("=== All Users ===");
		for (User u : Database.users) System.out.println(u);
	}

	private static void viewLogs() {
		System.out.println("=== Logs ===");
		if (Database.logs.isEmpty()) {
			System.out.println("No logs.");
			return;
		}
		for (String log : admin.viewLogs()) System.out.println(log);
	}

	private static void search() throws IOException {
		System.out.println("Search in: 1) Users  2) Courses  3) Research papers");
		int target = Integer.parseInt(br.readLine());
		System.out.print("Enter regex pattern: ");
		String pattern = br.readLine();

		try {
			if (target == 1) {
				List<User> results = Database.searchUsers(pattern);
				System.out.println("Found " + results.size() + " user(s):");
				for (User u : results) System.out.println("  " + u);
			} else if (target == 2) {
				List<Course> results = Database.searchCourses(pattern);
				System.out.println("Found " + results.size() + " course(s):");
				for (Course c : results) System.out.println("  " + c);
			} else if (target == 3) {
				List<research.ResearchPaper> results = Database.searchResearchPapers(pattern);
				System.out.println("Found " + results.size() + " paper(s):");
				for (research.ResearchPaper p : results) System.out.println("  " + p.getTitle());
			} else {
				System.out.println("Invalid choice.");
			}
		} catch (PatternSyntaxException e) {
			System.out.println("Invalid regex: " + e.getMessage());
		}
	}

	private static void exit() {
		Database.save();
		System.out.println("Bye bye");
	}

	public static void run(User user) {
		admin = (Admin) user;
		br = new BufferedReader(new InputStreamReader(System.in));
		try {
			menu: while (true) {
				System.out.println("What do you want to do?\n"
						+ " 1) Add user\n 2) Remove user\n 3) View all users\n"
						+ " 4) View logs\n 5) Search (RegEx)\n 6) Logout");
				int choice = Integer.parseInt(br.readLine());
				switch (choice) {
				case 1:
					addUser();
					System.out.println(" 1) Back  2) Exit");
					if (Integer.parseInt(br.readLine()) != 1) { exit(); break menu; }
					break;
				case 2:
					removeUser();
					System.out.println(" 1) Back  2) Exit");
					if (Integer.parseInt(br.readLine()) != 1) { exit(); break menu; }
					break;
				case 3:
					viewUsers();
					System.out.println(" 1) Back  2) Exit");
					if (Integer.parseInt(br.readLine()) != 1) { exit(); break menu; }
					break;
				case 4:
					viewLogs();
					System.out.println(" 1) Back  2) Exit");
					if (Integer.parseInt(br.readLine()) != 1) { exit(); break menu; }
					break;
				case 5:
					while (true) {
						search();
						System.out.println(" 1) Search again  2) Back  3) Exit");
						int c = Integer.parseInt(br.readLine());
						if (c == 1) continue;
						if (c == 2) break;
						exit(); break menu;
					}
					break;
				default:
					exit();
					break menu;
				}
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
