package demo;

import users.Admin;
import users.User;

// creates a default Admin instance
public class AdminCreator extends UserCreator {

    @Override
    public User createUser() {
        return new Admin();
    }
}
