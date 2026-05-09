package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import course.Course;
import course.Period;
import other.LanguageManager;
import other.Message;
import other.News;
import other.NewsTopic;
import users.Database;
import users.Employee;
import users.FacultyType;
import users.Manager;
import users.Teacher;
import users.User;

public class ManagerDemo {
	static Manager manager = null;
	static BufferedReader br = null;

	private static String t(String key) {
		return LanguageManager.getTranslation(key, manager.getLanguage());
	}

	private static void addCourse() throws IOException {
		System.out.print(t("enter_cname"));
		String courseName = br.readLine();

		System.out.println(t("enter_semester"));
		int num = Integer.parseInt(br.readLine().trim());
		Period period = (num == 1) ? Period.FALL : (num == 2) ? Period.SPRING : Period.SUMMER;

		System.out.print(t("enter_code"));
		String courseCode = br.readLine();

		System.out.print(t("enter_credits"));
		int credit = Integer.parseInt(br.readLine().trim());

		System.out.println(t("is_elective"));
		boolean isElective = Integer.parseInt(br.readLine().trim()) == 2;

		System.out.print(t("enter_limit"));
		int studentLimit = Integer.parseInt(br.readLine().trim());

		System.out.print(t("enter_prereq"));
		String prereqName = br.readLine();
		Course prereq = null;
		for (Course c : Database.courses)
			if (c.getCourseName().equals(prereqName)) prereq = c;

		System.out.print(t("enter_dept"));
		String depName = br.readLine().trim().toUpperCase();
		FacultyType type = null;
		try { type = FacultyType.valueOf(depName); } catch (IllegalArgumentException ignored) {}

		if (manager.addCourse(new Course(courseName, period, courseCode, credit, isElective, studentLimit, prereq, type)))
			System.out.println(t("course_added"));
		else
			System.out.println(t("course_exists"));
	}

	private static void removeCourse() throws IOException {
		List<Course> list = new ArrayList<>(Database.courses);
		if (list.isEmpty()) { System.out.println(t("no_courses")); return; }
		System.out.println(t("pick_course"));
		for (int i = 0; i < list.size(); i++)
			System.out.printf(" %d) %s%n", i + 1, list.get(i).getCourseName());
		System.out.print(t("pick_0_cancel"));
		int idx;
		try { idx = Integer.parseInt(br.readLine().trim()); } catch (NumberFormatException e) { idx = 0; }
		if (idx < 1 || idx > list.size()) return;
		if (manager.removeCourse(list.get(idx - 1)))
			System.out.println(t("course_removed"));
	}

	private static void addNews() throws IOException {
		System.out.print(t("enter_ntitle")); String title = br.readLine();
		System.out.print(t("enter_ntext"));  String text  = br.readLine();
		System.out.print(t("enter_ndate"));  String date  = br.readLine();
		if (manager.addNews(new News(title, text, date, NewsTopic.GENERAL, manager)))
			System.out.println(t("news_added"));
	}

	private static void removeNews() throws IOException {
		if (Database.news.isEmpty()) { System.out.println(t("news_not_found")); return; }
		System.out.println("News:");
		List<News> list = new ArrayList<>(Database.news);
		for (int i = 0; i < list.size(); i++)
			System.out.printf(" %d) %s%n", i + 1, list.get(i).getTitle());
		System.out.print(t("pick_0_cancel"));
		int idx;
		try { idx = Integer.parseInt(br.readLine().trim()); } catch (NumberFormatException e) { idx = 0; }
		if (idx < 1 || idx > list.size()) return;
		if (manager.removeNews(list.get(idx - 1)))
			System.out.println(t("news_removed"));
	}

	private static void updateNews() throws IOException {
		if (Database.news.isEmpty()) { System.out.println(t("news_not_found")); return; }
		List<News> list = new ArrayList<>(Database.news);
		System.out.println("News:");
		for (int i = 0; i < list.size(); i++)
			System.out.printf(" %d) %s%n", i + 1, list.get(i).getTitle());
		System.out.print(t("pick_0_cancel"));
		int idx;
		try { idx = Integer.parseInt(br.readLine().trim()); } catch (NumberFormatException e) { idx = 0; }
		if (idx < 1 || idx > list.size()) return;
		News old = list.get(idx - 1);
		System.out.print(t("enter_ntitle")); String newTitle = br.readLine();
		System.out.print(t("enter_ntext"));  String newText  = br.readLine();
		System.out.print(t("enter_ndate"));  String newDate  = br.readLine();
		if (manager.updateNews(old, new News(newTitle, newText, newDate, old.getTopic(), manager)))
			System.out.println(t("news_updated"));
	}

	private static void assignCourse() throws IOException {
		List<Course> courses = new ArrayList<>(Database.courses);
		List<Teacher> teachers = new ArrayList<>(Database.teachers);
		if (courses.isEmpty() || teachers.isEmpty()) { System.out.println(t("no_assign_data")); return; }
		System.out.println(t("pick_course"));
		for (int i = 0; i < courses.size(); i++)
			System.out.printf(" %d) %s%n", i + 1, courses.get(i).getCourseName());
		System.out.print(t("pick_0_cancel"));
		int ci;
		try { ci = Integer.parseInt(br.readLine().trim()); } catch (NumberFormatException e) { ci = 0; }
		if (ci < 1 || ci > courses.size()) return;
		System.out.println(t("pick_teacher"));
		for (int i = 0; i < teachers.size(); i++)
			System.out.printf(" %d) %s%n", i + 1, teachers.get(i).getFullName());
		System.out.print(t("pick_0_cancel"));
		int ti;
		try { ti = Integer.parseInt(br.readLine().trim()); } catch (NumberFormatException e) { ti = 0; }
		if (ti < 1 || ti > teachers.size()) return;
		if (manager.assignCoursesToTeachers(courses.get(ci - 1), teachers.get(ti - 1)))
			System.out.println(t("assign_ok"));
	}

	private static void sendMessages() throws IOException {
		System.out.print(t("enter_receiver"));
		String name = br.readLine();
		for (Employee e : Database.employees) {
			if (e.getFullName().equals(name)) {
				System.out.print(t("enter_msg_text"));
				String text = br.readLine();
				manager.sendMessage(e, new Message("General Message", text, manager, e, new Date().toString()));
				System.out.println(t("msg_sent"));
				return;
			}
		}
		System.out.println(t("teacher_not_found"));
	}

	private static void exit() {
		Database.save();
		System.out.println(t("menu_logout"));
	}

	public static void run(User user, BufferedReader brIn) {
		manager = (Manager) user;
		br = brIn;
		try {
			menu: while (true) {
				System.out.println(t("manager_menu"));
				System.out.print("Choice: ");
				int choice = Integer.parseInt(br.readLine().trim());
				switch (choice) {
				case 1: {
					System.out.println(t("courses_submenu"));
					int op = Integer.parseInt(br.readLine().trim());
					courses: while (true) {
						if (op == 1) addCourse(); else removeCourse();
						System.out.println(t("prompt_continue"));
						int c = Integer.parseInt(br.readLine());
						if (c == 1) continue courses;
						if (c == 2) break courses;
						exit(); break menu;
					}
					break;
				}
				case 2: {
					System.out.println(t("news_submenu"));
					int op = Integer.parseInt(br.readLine().trim());
					news: while (true) {
						if (op == 1) addNews();
						else if (op == 2) removeNews();
						else updateNews();
						System.out.println(t("prompt_continue"));
						int c = Integer.parseInt(br.readLine());
						if (c == 1) continue news;
						if (c == 2) break news;
						exit(); break menu;
					}
					break;
				}
				case 3: {
					assign: while (true) {
						assignCourse();
						System.out.println(t("prompt_continue"));
						int c = Integer.parseInt(br.readLine());
						if (c == 1) continue assign;
						if (c == 2) break assign;
						exit(); break menu;
					}
					break;
				}
				case 4: {
					System.out.println(t("students_submenu"));
					int op = Integer.parseInt(br.readLine().trim());
					students: while (true) {
						if (op == 1) System.out.println(manager.viewStudentsByName());
						else System.out.println(manager.viewStudentsByGPA());
						System.out.println(t("prompt_continue"));
						int c = Integer.parseInt(br.readLine());
						if (c == 1) continue students;
						if (c == 2) break students;
						exit(); break menu;
					}
					break;
				}
				case 5: {
					teachers: while (true) {
						System.out.println(manager.viewTeachersByName());
						System.out.println(t("back_exit"));
						int c = Integer.parseInt(br.readLine());
						if (c == 1) break teachers;
						exit(); break menu;
					}
					break;
				}
				case 6: {
					msg: while (true) {
						sendMessages();
						System.out.println(t("prompt_continue"));
						int c = Integer.parseInt(br.readLine());
						if (c == 1) continue msg;
						if (c == 2) break msg;
						exit(); break menu;
					}
					break;
				}
				case 7: {
					Object msgs = manager.getMessages();
					System.out.println(msgs == null || msgs.toString().isBlank() ? t("no_messages") : msgs);
					System.out.println(t("back_exit"));
					if (Integer.parseInt(br.readLine()) != 1) { exit(); break menu; }
					break;
				}
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
