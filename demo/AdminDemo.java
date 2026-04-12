package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
			Student s = new Student(fullName, username, password, id, year,
					DegreeStudent.BACHELOR, FacultyType.FIT, "CS");
			admin.addUser(s);
			System.out.println("Student added successfully!");
		} else if (type.equalsIgnoreCase("teacher")) {
			System.out.println("Enter salary: ");
			int salary = Integer.parseInt(br.readLine());
			Teacher t = new Teacher(fullName, username, password, salary,
					DegreeTeacher.LECTURER, FacultyType.FIT);
			admin.addUser(t);
			System.out.println("Teacher added successfully!");
		} else if (type.equalsIgnoreCase("manager")) {
			System.out.println("Enter salary: ");
			int salary = Integer.parseInt(br.readLine());
			Manager m = new Manager(fullName, username, password, salary, ManagerType.OR);
			admin.addUser(m);
			System.out.println("Manager added successfully!");
		} else if (type.equalsIgnoreCase("admin")) {
			System.out.println("Enter salary: ");
			int salary = Integer.parseInt(br.readLine());
			Admin a = new Admin(fullName, username, password, salary);
			admin.addUser(a);
			System.out.println("Admin added successfully!");
		} else {
			System.out.println("Unknown user type.");
		}
	}

	private static void removeUser() throws IOException {
		System.out.println("Enter username to remove: ");
		String username = br.readLine();
		for (User u : Database.users) {
			if (u.getUsername().equals(username)) {
				admin.removeUser(u);
				System.out.println("User removed.");
				return;
			}
		}
		System.out.println("User not found.");
	}

	private static void viewUsers() {
		System.out.println("=== All Users ===");
		for (User u : Database.users) {
			System.out.println(u);
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
			menu:
			while (true) {
				System.out.println("What do you want to do?\n 1) Add user \n 2) Remove user \n 3) View all users \n 4) Logout");
				int choice = Integer.parseInt(br.readLine());
				if (choice == 1) {
					addUser();
					System.out.println("\n 1) Return back \n 2) Exit");
					choice = Integer.parseInt(br.readLine());
					if (choice == 1) continue menu;
					if (choice == 2) { exit(); break menu; }
				} else if (choice == 2) {
					removeUser();
					System.out.println("\n 1) Return back \n 2) Exit");
					choice = Integer.parseInt(br.readLine());
					if (choice == 1) continue menu;
					if (choice == 2) { exit(); break menu; }
				} else if (choice == 3) {
					viewUsers();
					System.out.println("\n 1) Return back \n 2) Exit");
					choice = Integer.parseInt(br.readLine());
					if (choice == 1) continue menu;
					if (choice == 2) { exit(); break menu; }
				} else {
					exit();
					break menu;
				}
			}
		} catch (Exception e) {
			System.out.println("Something bad happened... \n Saving resources...");
			e.printStackTrace();
		}
	}
}
