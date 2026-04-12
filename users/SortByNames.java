package users;

import java.util.Comparator;

public class SortByNames implements Comparator<User>{
	public int compare(User u1, User u2) {
		return u1.getFullName().compareTo(u2.getFullName());
	}
}
