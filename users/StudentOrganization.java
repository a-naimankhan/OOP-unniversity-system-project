package users;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentOrganization implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String role;
	private List<Student> members;
	private Student head;
	
		this.name = name;
		this.head = head;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public List<Student> getMembers() {
		return members;
	}
	
	public Student getHead() {
		return head;
	}
	
	public void setHead(Student head) {
		this.head = head;
	}
	
	@Override
	public String toString() {
	}
}
