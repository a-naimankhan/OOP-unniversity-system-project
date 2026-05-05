package users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentOrganization implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String role;
    private List<Student> members;
    private Student head;

    public StudentOrganization(String name, String role) {
        this.name = name;
        this.role = role;
        this.members = new ArrayList<>();
    }

    public boolean addMember(Student student) {
        if (members.contains(student)) return false;
        members.add(student);
        student.getOrganizations().add(this);
        return true;
    }

    public boolean removeMember(Student student) {
        if (!members.contains(student)) return false;
        members.remove(student);
        student.getOrganizations().remove(this);
        if (student.equals(head)) head = null;
        return true;
    }

    public boolean setHead(Student student) {
        if (!members.contains(student)) return false;
        this.head = student;
        return true;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public List<Student> getMembers() { return members; }

    public Student getHead() { return head; }

    @Override
    public String toString() {
        return "StudentOrganization [name=" + name + ", role=" + role
                + ", members=" + members.size()
                + ", head=" + (head != null ? head.getFullName() : "none") + "]";
    }

    @Override
    public int hashCode() { return Objects.hash(name); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        StudentOrganization other = (StudentOrganization) obj;
        return Objects.equals(name, other.name);
    }
}
