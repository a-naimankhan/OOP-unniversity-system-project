package demo;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

import course.Course;
import users.Database;
import users.Student;
import users.Teacher;
import users.User;

public class StudentDemo {
	static Student student = null;
	static BufferedReader br = null;
	
	private static void register() throws IOException {
		System.out.println("Enter course name: ");
		String courseName = br.readLine();

		for (Course c : Database.courses) {
			if (c.getCourseName().equals(courseName)) {
				try {
					if (student.registerCourse(c)) {
						System.out.println("Registered successfully");
					} else {
						System.out.println("Could not register (course not in DB)");
					}
				} catch (Exception ex) {
					System.out.println("Registration failed: " + ex.getMessage());
				}
				return;
			}
		}
		System.out.println("Course not found");
	}

	private static void drop() throws IOException {
		System.out.println("Enter course name: ");
		String courseName = br.readLine();

		for (Course c : Database.courses) {
			if (c.getCourseName().equals(courseName)) {
				if (student.dropCourse(c)) {
					System.out.println("Dropped successfully");
				} else {
					System.out.println("Cannot drop — course already has marks");
				}
				return;
			}
		}
		System.out.println("Course not found");
	}
	
	private static void exit() {
		Database.save();
		System.out.println("Bye bye");
	}
	
	public static void run(User user) {
		student = (Student)user;
		br = new BufferedReader(new InputStreamReader(System.in));
		try {
			menu: while(true) {
				System.out.println("What do you want to do?\n 1) Register to courses \n 2) Drop courses  \n 3) Rate teachers  \n 4) View courses \n 5) View teacher info \n 6) View Marks \n 7) Logout");
				int choice = Integer.parseInt(br.readLine());
				if (choice == 1) {
					register: while(true) {		
						register();	
						System.out.println("\n 1) Register to another course  \n 2) Return back \n 3) Exit");
						choice = Integer.parseInt(br.readLine());
						if(choice == 1) continue register;
						if(choice == 2) continue menu;
						if(choice == 3) {
							exit();  
							break menu;
						}
						break;
					}
				}
				else if (choice == 2) {
					drop: while(true) {
						drop();
						System.out.println("\n 1) Drop another course  \n 2) Return back \n 3) Exit");
						choice = Integer.parseInt(br.readLine());
						if(choice == 1) continue drop;
						if(choice == 2) continue menu;
						if(choice == 8) {
							exit();  
							break menu;
						}
						break;
					}
				}
				else if (choice == 3) {
					rate: while(true) {
						System.out.println("\n Enter the teacher's name: ");
						String teacherName = br.readLine();
						
						System.out.println("\n Enter the point: ");
						double point = Double.parseDouble(br.readLine());
						
						for (Teacher t : Database.teachers) {
							if (t.getFullName().equals(teacherName)) {
								student.rateTeachers(t, point);
								System.out.println("Rated");
							}
						}
						
						
						System.out.println("\n 1) Rate another teacher \n 2) Return back \n 3) Exit");
						choice = Integer.parseInt(br.readLine());
						if(choice == 1) continue rate;
						if(choice == 2) continue menu;
						if(choice == 8) {
							exit();  
							break menu;
						}
						break;
					}
				}
				else if (choice == 4) {
						System.out.println(student.viewCourse());
						
						System.out.println("\n 1) Return back \n 2) Exit");
						choice = Integer.parseInt(br.readLine());
						if(choice == 1) continue menu;
						if(choice == 2) {
							exit();  
							break menu;
						}
				}
				else if (choice == 5) {
					teacherInfo: while(true) {
						System.out.println("\n Enter teacher's name: ");
						String name = br.readLine();
						
						for (Teacher t : Database.teachers) {
							if (t.getFullName().equals(name)) {
								System.out.println(student.viewTeacher(t));
							}
						}
						
						System.out.println("\n 1) View another teacher \n 2) Return back \n 3) Exit");
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
					viewMark: while(true) {
						System.out.println("\n - view course marks \n - view all marks");
						String operation = br.readLine();
						
						if (operation.equals("course marks")) {
							forCourse: while(true) {
								System.out.println("\n Enter course name:");
								String name = br.readLine();
								for (Course c : Database.courses) {
									if (c.getCourseName().equals(name)) {
										System.out.println(student.viewMarks(c));
									}
								}
								
								System.out.println("\n 1) View another course's marks \n 2) Return back \n 3) Exit");
								choice = Integer.parseInt(br.readLine());
								if(choice == 1) continue forCourse;
								if(choice == 2) continue menu;
								if(choice == 3) {
									exit();  
									break menu;
								}
								break;
							}
							
						}
						else if (operation.equals("all marks")) {
								System.out.println(student.getMarks());
								
							}
						System.out.println("\n 1) View marks \n 2) Return back \n 3) Exit");
						choice = Integer.parseInt(br.readLine());
						if(choice == 1) continue viewMark;
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