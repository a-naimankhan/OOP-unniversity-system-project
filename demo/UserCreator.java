package demo;

import users.User;

public abstract class UserCreator {

    public abstract User createUser();

    public final User register() {
        return createUser();
    }
}
