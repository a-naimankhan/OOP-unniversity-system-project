package demo;

import users.Manager;
import users.User;

// creates a default Manager instance
public class ManagerCreator extends UserCreator {

    @Override
    public User createUser() {
        return new Manager();
    }
}
