package demo;

import users.Student;
import users.User;

// creates a default Student instance
public class StudentCreator extends UserCreator {

    @Override
    public User createUser() {
        return new Student();
    }
}
