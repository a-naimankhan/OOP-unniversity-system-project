package other;

import users.User;

// abstract Decorator — wraps IMessage and delegates all calls to the wrapped instance
public abstract class MessageDecorator implements IMessage {

    protected IMessage wrapped;

    public MessageDecorator(IMessage message) {
        this.wrapped = message;
    }

    @Override public String getSubject()          { return wrapped.getSubject(); }
    @Override public String getText()             { return wrapped.getText(); }
    @Override public User   getSender()           { return wrapped.getSender(); }
    @Override public User   getReceiver()         { return wrapped.getReceiver(); }
    @Override public String getDate()             { return wrapped.getDate(); }
    @Override public String getFormattedContent() { return wrapped.getFormattedContent(); }
}
