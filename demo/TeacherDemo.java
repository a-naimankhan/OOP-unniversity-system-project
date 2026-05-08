package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import course.Course;
import mark.AttestationType;
import other.RecommendationLetter;
import users.Database;
import users.Student;
import users.Teacher;
import users.User;

public class TeacherDemo {
	static Teacher teacher = null;
	static BufferedReader br = null;

	private static void putMark() throws IOException {
		System.out.println("Enter course name: ");
		String courseName = br.readLine();
		System.out.println("Enter student name: ");
		String studentName = br.readLine();
		System.out.println("Attestation type: 1) First  2) Second  3) Exam");
		int num = Integer.parseInt(br.readLine());
		AttestationType period = (num == 1) ? AttestationType.FIRST
				: (num == 2) ? AttestationType.SECOND : AttestationType.EXAM;
		System.out.println("Enter point: ");
		double point = Double.parseDouble(br.readLine());

		for (Course c : Database.courses) {
			if (c.getCourseName().equals(courseName)) {
				for (Student s : Database.students) {
					if (s.getFullName().equals(studentName)) {
						if (teacher.putMark(c, s, point, period))
							System.out.println("Mark added successfully!");
						else
							System.out.println("Could not add mark.");
					}
				}
			}
		}
	}

	private static void generateReport() throws IOException {
		System.out.println("Enter course name for report: ");
		String courseName = br.readLine();
		for (Course c : Database.courses) {
			if (c.getCourseName().equals(courseName)) {
				System.out.println(teacher.generateReport(c));
				return;
			}
		}
		System.out.println("Course not found.");
	}

	private static void writeRecommendationLetter() throws IOException {
		System.out.println("Enter student name: ");
		String studentName = br.readLine();
		Student target = null;
		for (Student s : Database.students) {
			if (s.getFullName().equals(studentName)) {
				target = s;
				break;
			}
		}
		if (target == null) {
			System.out.println("Student not found.");
			return;
		}
		System.out.println("Enter letter content: ");
		String content = br.readLine();
		RecommendationLetter letter = new RecommendationLetter(target, teacher, content);
		Database.recommendationLetters.add(letter);
		Database.log("Recommendation letter written by " + teacher.getFullName()
				+ " for " + target.getFullName());
		System.out.println("Recommendation letter saved.");
	}

	private static void exit() {
		Database.save();
		System.out.println("Bye bye");
	}

	public static void run(User user) {
		teacher = (Teacher) user;
		br = new BufferedReader(new InputStreamReader(System.in));
		try {
			menu: while (true) {
				System.out.println("What do you want to do?\n"
						+ " 1) Put mark\n 2) View courses\n 3) View students\n"
						+ " 4) Generate course report\n 5) Write recommendation letter\n"
						+ " 6) Logout");
				int choice = Integer.parseInt(br.readLine());
				switch (choice) {
				case 1:
					while (true) {
						putMark();
						System.out.println(" 1) Again  2) Back  3) Exit");
						int c = Integer.parseInt(br.readLine());
						if (c == 1) continue;
						if (c == 2) break;
						exit(); break menu;
					}
					break;
				case 2:
					System.out.println(teacher.viewCourses());
					System.out.println(" 1) Back  2) Exit");
					if (Integer.parseInt(br.readLine()) != 1) { exit(); break menu; }
					break;
				case 3:
					System.out.println(teacher.viewStudents());
					System.out.println(" 1) Back  2) Exit");
					if (Integer.parseInt(br.readLine()) != 1) { exit(); break menu; }
					break;
				case 4:
					while (true) {
						generateReport();
						System.out.println(" 1) Again  2) Back  3) Exit");
						int c = Integer.parseInt(br.readLine());
						if (c == 1) continue;
						if (c == 2) break;
						exit(); break menu;
					}
					break;
				case 5:
					while (true) {
						writeRecommendationLetter();
						System.out.println(" 1) Write another  2) Back  3) Exit");
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
