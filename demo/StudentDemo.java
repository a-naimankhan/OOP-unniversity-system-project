package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import course.Course;
import other.Language;
import other.LanguageManager;
import other.News;
import users.Database;
import users.Student;
import users.Teacher;
import users.Transcript;
import users.User;

public class StudentDemo {
	static Student student = null;
	static BufferedReader br = null;

	private static String t(String key) {
		return LanguageManager.getTranslation(key, student.getLanguage());
	}

	private static void register() throws IOException {
		if (Database.courses.isEmpty()) {
			System.out.println(t("course_not_found"));
			return;
		}
		System.out.println("Available courses:");
		java.util.List<Course> list = new java.util.ArrayList<>(Database.courses);
		for (int i = 0; i < list.size(); i++) {
			Course c = list.get(i);
			System.out.printf(" %d) %s  [%s, %d credits]%n", i + 1, c.getCourseName(), c.getSemester(), c.getCredit());
		}
		System.out.print("Choice (0 to cancel): ");
		String line = br.readLine();
		int idx;
		try { idx = Integer.parseInt(line.trim()); } catch (NumberFormatException e) { idx = 0; }
		if (idx < 1 || idx > list.size()) return;
		Course chosen = list.get(idx - 1);
		try {
			if (student.registerCourse(chosen)) {
				System.out.println(t("reg_success") + " (" + chosen.getCourseName() + ")");
			} else {
				System.out.println(t("reg_fail") + chosen.getCourseName());
			}
		} catch (Exception ex) {
			System.out.println(t("reg_fail") + ex.getMessage());
		}
	}

	private static void drop() throws IOException {
		java.util.List<Course> enrolled = new java.util.ArrayList<>(student.viewCourse());
		if (enrolled.isEmpty()) {
			System.out.println(t("course_not_found"));
			return;
		}
		System.out.println("Your courses:");
		for (int i = 0; i < enrolled.size(); i++) {
			System.out.printf(" %d) %s%n", i + 1, enrolled.get(i).getCourseName());
		}
		System.out.print("Choice (0 to cancel): ");
		String line = br.readLine();
		int idx;
		try { idx = Integer.parseInt(line.trim()); } catch (NumberFormatException e) { idx = 0; }
		if (idx < 1 || idx > enrolled.size()) return;
		Course chosen = enrolled.get(idx - 1);
		if (student.dropCourse(chosen)) {
			System.out.println(t("drop_success") + " (" + chosen.getCourseName() + ")");
		} else {
			System.out.println(t("drop_fail"));
		}
	}

	private static void rateTeacher() throws IOException {
		if (Database.teachers.isEmpty()) {
			System.out.println(t("teacher_not_found"));
			return;
		}
		System.out.println("Teachers:");
		java.util.List<Teacher> teachers = new java.util.ArrayList<>(Database.teachers);
		for (int i = 0; i < teachers.size(); i++) {
			System.out.printf(" %d) %s%n", i + 1, teachers.get(i).getFullName());
		}
		System.out.print("Choice (0 to cancel): ");
		String line = br.readLine();
		int idx;
		try { idx = Integer.parseInt(line.trim()); } catch (NumberFormatException e) { idx = 0; }
		if (idx < 1 || idx > teachers.size()) return;
		Teacher chosen = teachers.get(idx - 1);
		System.out.print(t("menu_rate_point"));
		double point = Double.parseDouble(br.readLine().trim());
		student.rateTeachers(chosen, point);
		System.out.println(t("rated") + " " + chosen.getFullName());
	}

	private static void viewNews() {
		java.util.Vector<News> newsList = User.viewNews();
		if (newsList.isEmpty()) {
			System.out.println(t("no_news"));
		} else {
			System.out.println(t("menu_news"));
			for (News n : newsList) System.out.println(n);
		}
	}

	private static void exit() {
		Database.save();
		System.out.println(t("menu_logout"));
	}

	// Returns false if user chose exit (break menu), true otherwise.
	private static boolean subPrompt() throws IOException {
		System.out.println(t("prompt_continue"));
		int c = Integer.parseInt(br.readLine());
		if (c == 3) { exit(); return false; }
		return true; // 1=again, 2=back — both handled by caller
	}

	public static void run(User user, BufferedReader brIn) {
		student = (Student) user;
		br = brIn;
		try {
			menu: while (true) {
				System.out.println(t("student_menu"));
				System.out.print("Choice: ");
				int choice = Integer.parseInt(br.readLine());

				switch (choice) {
				case 1: // Register for course
					while (true) {
						register();
						System.out.println(t("prompt_continue"));
						int c1 = Integer.parseInt(br.readLine());
						if (c1 == 1) continue;
						if (c1 == 2) break;
						exit(); break menu;
					}
					break;
				case 2: // Drop course
					while (true) {
						drop();
						System.out.println(t("prompt_continue"));
						int c2 = Integer.parseInt(br.readLine());
						if (c2 == 1) continue;
						if (c2 == 2) break;
						exit(); break menu;
					}
					break;
				case 3: // Rate teacher
					while (true) {
						rateTeacher();
						System.out.println(t("prompt_continue"));
						int c3 = Integer.parseInt(br.readLine());
						if (c3 == 1) continue;
						if (c3 == 2) break;
						exit(); break menu;
					}
					break;
				case 4: // View courses
					if (student.viewCourse().isEmpty()) {
						System.out.println("No courses registered.");
					} else {
						System.out.println("Your registered courses:");
						for (Course c : student.viewCourse())
							System.out.printf("  - %s [%s, %d credits]%n", c.getCourseName(), c.getSemester(), c.getCredit());
					}
					System.out.println(" 1) " + t("menu_back") + "  2) " + t("menu_exit"));
					if (Integer.parseInt(br.readLine()) != 1) { exit(); break menu; }
					break;
				case 5: // View teacher info
					while (true) {
						java.util.List<Teacher> tlist = new java.util.ArrayList<>(Database.teachers);
						if (tlist.isEmpty()) { System.out.println(t("teacher_not_found")); break; }
						System.out.println("Teachers:");
						for (int i = 0; i < tlist.size(); i++)
							System.out.printf(" %d) %s%n", i + 1, tlist.get(i).getFullName());
						System.out.print("Choice (0 to cancel): ");
						int ti5;
						try { ti5 = Integer.parseInt(br.readLine().trim()); } catch (NumberFormatException e) { ti5 = 0; }
						if (ti5 >= 1 && ti5 <= tlist.size())
							System.out.println(student.viewTeacher(tlist.get(ti5 - 1)));
						System.out.println(t("prompt_continue"));
						int c5 = Integer.parseInt(br.readLine());
						if (c5 == 1) continue;
						if (c5 == 2) break;
						exit(); break menu;
					}
					break;
				case 6: // View marks
					while (true) {
						System.out.println(" 1) Course marks  2) All marks  3) " + t("menu_back"));
						int sub = Integer.parseInt(br.readLine());
						if (sub == 1) {
							java.util.List<Course> myCourses = new java.util.ArrayList<>(student.viewCourse());
							if (myCourses.isEmpty()) {
								System.out.println(t("course_not_found"));
							} else {
								System.out.println("Your courses:");
								for (int i = 0; i < myCourses.size(); i++)
									System.out.printf(" %d) %s%n", i + 1, myCourses.get(i).getCourseName());
								System.out.print("Choice (0 to cancel): ");
								int ci;
								try { ci = Integer.parseInt(br.readLine().trim()); } catch (NumberFormatException e) { ci = 0; }
								if (ci >= 1 && ci <= myCourses.size())
									System.out.println(student.viewMarks(myCourses.get(ci - 1)));
							}
						} else if (sub == 2) {
							if (student.getMarks().isEmpty()) {
								System.out.println("No marks yet.");
							} else {
								System.out.println("All marks:");
								for (java.util.Map.Entry<Course, mark.Mark> e : student.getMarks().entrySet())
									System.out.printf("  %-20s %s%n", e.getKey().getCourseName(), e.getValue());
							}
						} else {
							break;
						}
						System.out.println(t("prompt_continue"));
						int c6 = Integer.parseInt(br.readLine());
						if (c6 == 1) continue;
						if (c6 == 2) break;
						exit(); break menu;
					}
					break;
				case 7: // Transcript
					System.out.println(new Transcript(student).getTranscript());
					System.out.println(" 1) " + t("menu_back") + "  2) " + t("menu_exit"));
					if (Integer.parseInt(br.readLine()) != 1) { exit(); break menu; }
					break;
				case 8: // News
					viewNews();
					System.out.println(" 1) " + t("menu_back") + "  2) " + t("menu_exit"));
					if (Integer.parseInt(br.readLine()) != 1) { exit(); break menu; }
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
