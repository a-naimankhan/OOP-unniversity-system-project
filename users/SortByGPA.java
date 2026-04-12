package users;

import java.util.Comparator;

public class SortByGPA implements Comparator<User>{
	public int compare(User u1, User u2) {
		Student s1 = (Student)u1;
		Student s2 = (Student)u2;
		return s1.compareTo(s2);
	}
}
