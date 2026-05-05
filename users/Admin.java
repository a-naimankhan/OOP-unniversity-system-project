package users;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

public class Admin extends Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	public Admin() {}

	public Admin(String fullName, String username, String password, int salary) {
		super(fullName, username, password, salary);
	}

	public void addUser(User user) {
		Database.users.add(user);
		if (user instanceof Student) {
			Database.students.add((Student) user);
		} else if (user instanceof Teacher) {
			Database.teachers.add((Teacher) user);
		} else if (user instanceof Manager) {
			Database.managers.add((Manager) user);
		} else if (user instanceof Admin) {
			Database.admins.add((Admin) user);
		} else if (user instanceof Employee) {
			Database.employees.add((Employee) user);
		}
		Database.log("ADD_USER: " + user.getClass().getSimpleName() + " '" + user.getUsername() + "'");
	}

	public void removeUser(User user) {
		Database.users.remove(user);
		if (user instanceof Student) {
			Database.students.remove((Student) user);
		} else if (user instanceof Teacher) {
			Database.teachers.remove((Teacher) user);
		} else if (user instanceof Manager) {
			Database.managers.remove((Manager) user);
		} else if (user instanceof Admin) {
			Database.admins.remove((Admin) user);
		} else if (user instanceof Employee) {
			Database.employees.remove((Employee) user);
		}
		Database.log("REMOVE_USER: " + user.getClass().getSimpleName() + " '" + user.getUsername() + "'");
	}

	public void updateUser(User oldUser, User newUser) {
		int idx = Database.users.indexOf(oldUser);
		if (idx >= 0) Database.users.set(idx, newUser);
		Database.log("UPDATE_USER: '" + oldUser.getUsername() + "' → '" + newUser.getUsername() + "'");
	}

	public List<String> viewLogs() {
		return Database.logs;
	}

	public void clearLogs() {
		Database.logs.clear();
		Database.log("LOGS_CLEARED by " + this.getUsername());
	}

	@Override
	public String toString() {
		return super.toString() + " Admin";
	}
}
