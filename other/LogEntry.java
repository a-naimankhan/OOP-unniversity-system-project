package other;

import java.io.Serializable;
import java.util.Date;

public class LogEntry implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String action;
    private Date timestamp;
    private String user;

    public LogEntry(String action, String user) {
        this.action = action;
        this.user = user;
        this.timestamp = new Date();
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] User: " + user + " | Action: " + action;
    }
}
