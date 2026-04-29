package other;

import users.User;

// component interface for Decorator pattern — Message and decorators both implement this
public interface IMessage {
    String getSubject();
    String getText();
    User getSender();
    User getReceiver();
    String getDate();
    String getFormattedContent();
}
