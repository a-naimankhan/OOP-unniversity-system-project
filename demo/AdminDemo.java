package demo;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import users.Admin;
import Book.Book;
import Book.State;
import course.Course;
import course.CourseFile;
import course.Period;
import mark.AttestationType;
import other.Message;
import other.News;
import users.Database;
import users.Employee;
import users.FacultyType;
import users.Librarian;
import users.Manager;
import users.Student;
import users.Teacher;
import users.User;

public class AdminDemo {
	static Admin admin = null;
	static BufferedReader br = null;
	
	private static void addUser() throws IOException{
		System.out.println("Welcome ");
		String fullName;
		String username;
		String password;
		
		System.out.println("Enter user full name: ");
		fullName = br.readLine();
		
		System.out.println("Enter username ");
		username = br.readLine();
		
		System.out.println("Enter user password ");
		password = br.readLine();
		
		if(admin.addUser(new User(fullName, username, password)));
			System.out.println("User successfully added! ");
			
	}
}
