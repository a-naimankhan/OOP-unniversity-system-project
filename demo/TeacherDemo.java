package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import course.Course;
import mark.AttestationType;
import other.Language;
import other.LanguageManager;
import other.RecommendationLetter;
import users.Database;
import users.Student;
import users.Teacher;
import users.User;

public class TeacherDemo {
	static Teacher teacher = null;
	static BufferedReader br = null;

	private static String t(String key) {
		return LanguageManager.getTranslation(key, teacher.getLanguage());
	}

	private static Course pickCourse() throws IOException {
		List<Course> list = new ArrayList<>(Database.courses);
		if (list.isEmpty()) { System.out.println(t("no_courses")); return null; }
		System.out.println(t("pick_course"));
		for (int i = 0; i < list.size(); i++)
			System.out.printf(" %d) %s%n", i + 1, list.get(i).getCourseName());
		System.out.print(t("pick_0_cancel"));
		int idx;
		try { idx = Integer.parseInt(br.readLine().trim()); } catch (NumberFormatException e) { idx = 0; }
		return (idx >= 1 && idx <= list.size()) ? list.get(idx - 1) : null;
	}

	private static Student pickStudent() throws IOException {
		List<Student> list = new ArrayList<>(Database.students);
		if (list.isEmpty()) { System.out.println(t("no_students")); return null; }
		System.out.println(t("pick_student"));
		for (int i = 0; i < list.size(); i++)
			System.out.printf(" %d) %s%n", i + 1, list.get(i).getFullName());
		System.out.print(t("pick_0_cancel"));
		int idx;
		try { idx = Integer.parseInt(br.readLine().trim()); } catch (NumberFormatException e) { idx = 0; }
		return (idx >= 1 && idx <= list.size()) ? list.get(idx - 1) : null;
	}

	private static void putMark() throws IOException {
		Course c = pickCourse();
		if (c == null) return;
		Student s = pickStudent();
		if (s == null) return;
		System.out.println(t("attestation_type"));
		int num = Integer.parseInt(br.readLine().trim());
		AttestationType period = (num == 1) ? AttestationType.FIRST
				: (num == 2) ? AttestationType.SECOND : AttestationType.EXAM;
		System.out.print(t("enter_point"));
		double point = Double.parseDouble(br.readLine().trim());
		if (teacher.putMark(c, s, point, period))
			System.out.println(t("mark_added") + s.getFullName() + " — " + c.getCourseName() + " " + period + " = " + point);
		else
			System.out.println(t("mark_fail"));
	}

	private static void generateReport() throws IOException {
		Course c = pickCourse();
		if (c == null) return;
		System.out.println(t("report_label") + c.getCourseName() + " ===");
		System.out.println(teacher.generateReport(c));
	}

	private static void writeRecommendationLetter() throws IOException {
		Student target = pickStudent();
		if (target == null) return;
		System.out.print(t("rec_content"));
		String content = br.readLine();
		RecommendationLetter letter = new RecommendationLetter(target, teacher, content);
		Database.recommendationLetters.add(letter);
		Database.log("Recommendation letter written by " + teacher.getFullName()
				+ " for " + target.getFullName());
		System.out.println(t("rec_saved"));
	}

	private static void exit() {
		Database.save();
		System.out.println(t("menu_logout"));
	}

	public static void run(User user, BufferedReader brIn) {
		teacher = (Teacher) user;
		br = brIn;
		try {
			menu: while (true) {
				System.out.println(t("teacher_menu"));
				System.out.print("Choice: ");
				int choice = Integer.parseInt(br.readLine());
				switch (choice) {
				case 1:
					while (true) {
						putMark();
						System.out.println(t("prompt_continue"));
						int c = Integer.parseInt(br.readLine());
						if (c == 1) continue;
						if (c == 2) break;
						exit(); break menu;
					}
					break;
				case 2:
					System.out.println(teacher.viewCourses());
					System.out.println(" 1) " + t("menu_back") + "  2) " + t("menu_exit"));
					if (Integer.parseInt(br.readLine()) != 1) { exit(); break menu; }
					break;
				case 3:
					System.out.println(teacher.viewStudents());
					System.out.println(" 1) " + t("menu_back") + "  2) " + t("menu_exit"));
					if (Integer.parseInt(br.readLine()) != 1) { exit(); break menu; }
					break;
				case 4:
					while (true) {
						generateReport();
						System.out.println(t("prompt_continue"));
						int c = Integer.parseInt(br.readLine());
						if (c == 1) continue;
						if (c == 2) break;
						exit(); break menu;
					}
					break;
				case 5:
					while (true) {
						writeRecommendationLetter();
						System.out.println(t("prompt_continue"));
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
