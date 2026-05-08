package other;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import users.User;

public class Startup implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private User founder;
    private String description;
    private List<User> members;
    private Date created;

    public Startup(String name, User founder, String description) {
        this.name = name;
        this.founder = founder;
        this.description = description;
        this.members = new ArrayList<>();
        this.created = new Date();
        this.members.add(founder);
    }

    public void addMember(User member) {
        if (!members.contains(member)) members.add(member);
    }

    public boolean removeMember(User member) {
        return members.remove(member);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public User getFounder() { return founder; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<User> getMembers() { return members; }

    public Date getCreated() { return created; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Startup ===\n");
        sb.append("Name       : ").append(name).append("\n");
        sb.append("Founder    : ").append(founder != null ? founder.getFullName() : "unknown").append("\n");
        sb.append("Description: ").append(description).append("\n");
        sb.append("Created    : ").append(created).append("\n");
        sb.append("Members (").append(members.size()).append("):");
        for (User m : members) sb.append("\n  - ").append(m.getFullName());
        return sb.toString();
    }
}
