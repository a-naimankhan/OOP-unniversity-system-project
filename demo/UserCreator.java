package demo;

import users.User;

// abstract Creator for Factory Method pattern — subclasses decide which User type to instantiate
public abstract class UserCreator {

    // factory method — overridden by each concrete creator
    public abstract User createUser();

    // template method — wraps createUser() for optional pre/post logic
    public final User register() {
        return createUser();
    }
}
