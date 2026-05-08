package other;

import users.User;

public interface IMessage {
    String getSubject();
    String getText();
    User getSender();
    User getReceiver();
    String getDate();
    String getFormattedContent();
}
