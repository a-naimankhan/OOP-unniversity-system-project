package demo;

import users.Admin;
import users.Manager;
import users.Student;
import users.Teacher;
import users.User;

public class UserFactory {
	public User getUser(String type) {
		if (type.equalsIgnoreCase("student")) {
			return new Student();
		}
		if (type.equalsIgnoreCase("manager")) {
			return new Manager();
		}
		if (type.equalsIgnoreCase("teacher")) {
			return new Teacher();
		}
if (type.equalsIgnoreCase("admin")) {
			return new Admin();
		}
		return null;
	}
}

