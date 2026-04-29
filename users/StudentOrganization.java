package users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudentOrganization implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private String role;
    private List<Student> members;
    private Student head;

    public StudentOrganization(String name, Student head) {
        this.name = name;
        this.head = head;
        this.members = new ArrayList<>();
        this.members.add(head);
        this.role = "Head";
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

    public void addMember(Student student) {
        if (!members.contains(student)) {
            members.add(student);
        }
    }

    public void removeMember(Student student) {
        members.remove(student);
        if (head.equals(student) && !members.isEmpty()) {
            head = members.get(0);
        }
    }

    @Override
    public String toString() {
        return "StudentOrganization [name=" + name + ", head=" + head.getFullName() + ", membersCount=" + members.size() + "]";
    }
}
