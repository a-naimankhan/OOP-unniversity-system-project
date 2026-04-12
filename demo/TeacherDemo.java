package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import course.Course;
import mark.AttestationType;
import users.Database;
import users.Student;
import users.Teacher;
import users.User;

public class TeacherDemo {
	static Teacher teacher = null;
	static BufferedReader br = null;

	private static void putMark() throws IOException {
		double point;
		AttestationType period = null;
		System.out.println("Enter course name: ");
		String courseName = br.readLine();

		System.out.println("Enter student name: ");
		String studentName = br.readLine();

		System.out.println("Choose Attestation type: \n 1) First \n 2) Second \n 3) Exam");
		int num = Integer.parseInt(br.readLine());
		if (num == 1)
			period = AttestationType.FIRST;
		else if (num == 2)
			period = AttestationType.SECOND;
		else
			period = AttestationType.EXAM;

		System.out.println("Enter student point: ");
		point = Integer.parseInt(br.readLine());

		for (Course c : Database.courses) {
			if (c.getCourseName().equals(courseName)) {
				for (Student s : Database.students) {
					if (s.getFullName().equals(studentName)) {
						if (teacher.putMark(c, s, point, period))
							System.out.println("Mark added successfully!");
					}
				}
			}
		}
	}

	private static void viewCourses() {
		System.out.println(teacher.viewCourses());
	}

	private static void viewStudents() {
		System.out.println(teacher.viewStudents());
	}

	private static void exit() {
		Database.save();
		System.out.println("Bye bye");
	}

	public static void run(User user) {
		teacher = (Teacher) user;
		br = new BufferedReader(new InputStreamReader(System.in));
		try {
			menu:
			while (true) {
				System.out.println("What do you want to do?\n 1) Put Mark \n 2) View courses \n 3) View students \n 4) Logout");
				int choice = Integer.parseInt(br.readLine());
				if (choice == 1) {
					putMark:
					while (true) {
						putMark();
						System.out.println("\n 1) Put mark to another student \n 2) Return back \n 3) Exit");
						choice = Integer.parseInt(br.readLine());
						if (choice == 1) continue putMark;
						if (choice == 2) continue menu;
						if (choice == 3) { exit(); break menu; }
						break;
					}
				} else if (choice == 2) {
					viewCourses();
					System.out.println("\n 1) Return back \n 2) Exit");
					choice = Integer.parseInt(br.readLine());
					if (choice == 1) continue menu;
					if (choice == 2) { exit(); break menu; }
				} else if (choice == 3) {
					viewStudents();
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
