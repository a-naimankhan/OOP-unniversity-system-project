package users;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class StudentOrganization implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String role;
	private List<Student> members;
	private Student head;
	
	{
		members = new ArrayList<Student>();
	}
	
	public StudentOrganization() {
		
	}
	
	public StudentOrganization(String name, String role, Student head) {
		this.name = name;
		this.role = role;
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
	
	public void setMembers(List<Student> members) {
		this.members = members;
	}
	
	public Student getHead() {
		return head;
	}
	
	public void setHead(Student head) {
		this.head = head;
	}
	
	@Override
	public String toString() {
		return "StudentOrganization [name=" + name + ", role=" + role + ", members=" + members.size() + ", head=" + (head != null ? head.getFullName() : "none") + "]";
	}
}
