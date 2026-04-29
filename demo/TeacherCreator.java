package demo;

import users.Teacher;
import users.User;

// creates a default Teacher instance
public class TeacherCreator extends UserCreator {

    @Override
    public User createUser() {
        return new Teacher();
    }
}
