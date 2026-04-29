package demo;

import users.User;

// Factory Method entry point — routes type string to the correct UserCreator subclass
public class UserFactory {

    // returns a User built by the matching concrete creator
    public User getUser(String type) {
        UserCreator creator;
        if (type.equalsIgnoreCase("student")) {
            creator = new StudentCreator();
        } else if (type.equalsIgnoreCase("teacher")) {
            creator = new TeacherCreator();
        } else if (type.equalsIgnoreCase("admin")) {
            creator = new AdminCreator();
        } else if (type.equalsIgnoreCase("manager")) {
            creator = new ManagerCreator();
        } else {
            return null;
        }
        return creator.register();
    }
}
