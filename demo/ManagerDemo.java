package demo;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

import course.Course;
import course.Period;
import other.Message;
import other.News;
import users.Database;
import users.Employee;
import users.FacultyType;
import users.Manager;
import users.Teacher;
import users.User;

public class ManagerDemo {
	static Manager manager = null;
	static BufferedReader br = null;
	
	private static void addCourse() throws IOException {
		String courseName;
		Period period;
		String courseCode;
		int credit;
		boolean isElective;
		int studentLimit;
		Course prereq = null;
		FacultyType type = null;
		
		System.out.println("Enter course name: ");
		courseName = br.readLine();

		System.out.println("Chooose semester: \n 1) Fall \n 2) Spring \n 3) Summer");
		int num = Integer.parseInt(br.readLine());
		if (num == 1) 
			period = Period.FALL;
		else if (num == 2) 
			period = Period.SPRING;
		else
			period = Period.SUMMER;
		
		System.out.println("Enter course code: ");
		courseCode = br.readLine();
		
		System.out.println("Enter number of credits: ");
		credit = Integer.parseInt(br.readLine());
		
		System.out.println("Choose is course elective: \n 1) No \n 2) Yes");
		int chose = Integer.parseInt(br.readLine());
		if (chose == 1)
			isElective = false;
		else 
			isElective = true;
		
		System.out.println("Enter number of students: ");
		studentLimit = Integer.parseInt(br.readLine());
		
		System.out.println("Enter name of course prerequisites: ");
		String prereqName = br.readLine();
		for (Course c : Database.courses) {
			if (c.getCourseName().equals(prereqName))
				prereq = c;
		}
		
		System.out.println("Enter course department: ");
		String depName = br.readLine();
		if (depName.equals("FIT")) type = FacultyType.FIT;
		else if (depName.equals("BS")) type = FacultyType.BS;
		else if (depName.equals("CCE")) type = FacultyType.CCE;
		else if (depName.equals("FEOG")) type = FacultyType.FEOG;
		else if (depName.equals("FGE")) type = FacultyType.FGE;
		else if (depName.equals("FGGE")) type = FacultyType.FGGE;
		else if (depName.equals("ISE")) type = FacultyType.ISE;
		else if (depName.equals("KMA")) type = FacultyType.KMA;
		else if (depName.equals("SMS")) type = FacultyType.SMS;
		
		if(manager.addCourse(new Course(courseName, period, courseCode, credit, isElective, studentLimit, prereq, type)));
			System.out.println("Course is successfully added!");
	}
	
	private static void removeCourse() throws IOException {
		System.out.println("Enter course name: ");
		String courseName = br.readLine();
		for (Course c : Database.courses) {
			if (c.getCourseName().equals(courseName)) {
				if (manager.removeCourse(c));
					System.out.println("Course is successfully removed!");
			}
		}
		System.out.println("Course is not in the Database!");
	}
	
	private static void addNews() throws IOException {
		String title;
		String text;
		String postDate;
		System.out.println("Enter news title: ");
		title = br.readLine();
		
		System.out.println("Enter news text: ");
		text = br.readLine();
		
		System.out.println("Enter news date: ");
		postDate = br.readLine();
		
		if(manager.addNews(new News(title, text, postDate)));
			System.out.println("News is successfully added!");
	}
	
	private static void removeNews() throws IOException {
		System.out.println("Enter news title: ");
		String title = br.readLine();
		
		for (News n : Database.news) {
			if (n.getTitle().equals(title)) {
				if (manager.removeNews(n));
					System.out.println("News is successfully removed!");
			}
		}
		System.out.println("News is not in the Database!");
	}
	
	private static void updateNews() throws IOException {
		System.out.println("Enter old news title: ");
		String title = br.readLine();
		for (News n : Database.news) {
			if (n.getTitle().equals(title)) {
				System.out.println("Enter new news title: ");
				String newTitle = br.readLine();
				System.out.println("Enter new news text: ");
				String newText = br.readLine();
				System.out.println("Enter new news post date: ");
				String newDate = br.readLine();
				if (manager.updateNews(n, new News(newTitle, newText, newDate)));
					System.out.println("News is updated!");
			}
		}
		System.out.println("Oops, News is not in the Database!");
	}
	
	private static void viewStudentsByName() {
		System.out.println(manager.viewStudentsByName());
	}
	
	private static void viewStudentsByGPA() {
		System.out.println(manager.viewStudentsByGPA());
	}
	
	private static void viewTeachers() {
		System.out.println(manager.viewTeachersByName());
	}
	
	private static void sendMessages() throws IOException {
		String name;
		System.out.println("Enter name of receiver: ");
		name = br.readLine();
		
		for(Employee e : Database.employees) {
			if (e.getFullName().equals(name)) {
				String text;
				System.out.println("Enter text: ");
				text = br.readLine();
				manager.sendMessage(e, new Message(text));
				System.out.println("Message is sended");
			}
		}
	}
	
	private static void viewMessage() {
		System.out.println(manager.getMessages());
	}
	
	private static void exit() {
		Database.save();
		System.out.println("Bye bye");
	}
	
	public static void run(User user) {
		manager = (Manager)user;
		br = new BufferedReader(new InputStreamReader(System.in));
		try {
			menu: while(true) {
				System.out.println("What do you want to do?\n 1) Manage courses \n 2) Manage news  \n 3) Assign courses to teachers  \n 4) View students info \n 5) View teacher info \n 6) Send Messages \n 7) View Messages \n 8) Logout");
				int choice = Integer.parseInt(br.readLine());
				if (choice == 1) {
					System.out.println("What do you want to do?\n - add course \n - remove course");
					String operation = br.readLine();
					if (operation.equals("add course")) {		
						addCourse: while(true){		
							addCourse();	
							System.out.println("\n 1) Add another course  \n 2) Return back \n 3) Exit");
							choice = Integer.parseInt(br.readLine());
							if(choice == 1) continue addCourse;
							if(choice == 2) continue menu;
							if(choice == 3) {
								exit();  
								break menu;
							}
							break;
						}
					}
					else if (operation.equals("remove course")) {
						removeCourse: while(true) {
							removeCourse();
							System.out.println("\n 1) Remove another course  \n 2) Return back \n 3) Exit");
							choice = Integer.parseInt(br.readLine());
							if(choice == 1) continue removeCourse;
							if(choice == 2) continue menu;
							if(choice == 3) {
								exit();  
								break menu;
							}
							break;
						}
					}	
				}
				else if (choice == 2) {
					System.out.println("What do you want to do?\n - add news \n - remove news \n - update news");
					String operation = br.readLine();
					if (operation.equals("add news")) {
						addNews: while(true) {
							addNews();
							System.out.println("\n 1) Add another news  \n 2) Return back \n 3) Exit");
							choice = Integer.parseInt(br.readLine());
							if(choice == 1) continue addNews;
							if(choice == 2) continue menu;
							if(choice == 3) {
								exit();  
								break menu;
							}
							break;
						}
					}
					else if (operation.equals("remove news")) {
						removeNews: while(true) {
							removeNews();
							System.out.println("\n 1) Remove another news  \n 2) Return back \n 3) Exit");
							choice = Integer.parseInt(br.readLine());
							if(choice == 1) continue removeNews;
							if(choice == 2) continue menu;
							if(choice == 3) {
								exit();  
								break menu;
							}
							break;
						}
					}
					else {
						updateNews: while(true) {
							updateNews();
							System.out.println("\n 1) Update another news  \n 2) Return back \n 3) Exit");
							choice = Integer.parseInt(br.readLine());
							if(choice == 1) continue updateNews;
							if(choice == 2) continue menu;
							if(choice == 3) {
								exit();  
								break menu;
							}
							break;
						}
					}
				}
				else if (choice == 3) {
					assignCourse: while(true) {
						System.out.println("\n Enter the course name: ");
						String courseName = br.readLine();
						System.out.println("\n Enter the teacher's name: ");
						String teacherName = br.readLine();
						
						for (Course c : Database.courses) {
							if (c.getCourseName().equals(courseName)) {
								for (Teacher t : Database.teachers) {
									if (t.getFullName().equals(teacherName)) {
										if (manager.assignCoursesToTeachers(c, t));
											System.out.println("\n Assigned successfully!");
									}	
								}
							}
						}
						System.out.println("\n 1) Assign another course \n 2) Return back \n 3) Exit");
						choice = Integer.parseInt(br.readLine());
						if(choice == 1) continue assignCourse;
						if(choice == 2) continue menu;
						if(choice == 3) {
							exit();  
							break menu;
						}
						break;
					}
				}
				else if (choice == 4) {
					studentInfo: while(true) {
						System.out.println("View \n - by names \n - by gpa");
						String operation = br.readLine();
						
						if (operation.equals("by names")) {
							viewStudentsByName();
									
						}
						else if (operation.equals("by gpa")) {
								viewStudentsByGPA();
						}
						System.out.println("\n 1) View another info \n 2) Return back \n 3) Exit");
						choice = Integer.parseInt(br.readLine());
						if(choice == 1) continue studentInfo;
						if(choice == 2) continue menu;	
						if(choice == 3) {
							exit();  
							break menu;
						}
						break;		
						
					}
				}
				else if (choice == 5) {
					teacherInfo: while(true) {
						viewTeachers();
						
						System.out.println("\n 1) View another info \n 2) Return back \n 3) Exit");
						choice = Integer.parseInt(br.readLine());
						if(choice == 1) continue teacherInfo;
						if(choice == 2) continue menu;	
						if(choice == 3) {
							exit();  
							break menu;
						}
						break;		
					
					}
				
				}
				else if (choice == 6) {
					sendMessage: while(true) {
						sendMessages();
						
						System.out.println("\n 1) Send another message \n 2) Return back \n 3) Exit");
						choice = Integer.parseInt(br.readLine());
						if(choice == 1) continue sendMessage;
						if(choice == 2) continue menu;
						if(choice == 3) {
							exit();  
							break menu;
						}
						break;
					}
				}
				else if (choice == 7) {
					viewMessage: while(true) {
						viewMessage();
						
						System.out.println("\n 1) View message \n 2) Return back \n 3) Exit");
						choice = Integer.parseInt(br.readLine());
						if(choice == 1) continue viewMessage;
						if(choice == 2) continue menu;
						if(choice == 3) {
							exit();  
							break menu;
						}
						break;
					}
				}
				else {
					exit();
					break menu;
				}
			}
		}
		catch(Exception e) {
			System.out.println("Something bad happened... \n Saving resources...");
			e.printStackTrace();
		}
	}


}
