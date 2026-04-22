package other;

import java.io.Serializable;
import users.User;

public class Request implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String description;
    private RequestStatus status;
    private User createdBy;
    private String date;
    private UrgencyLevel urgency;

    public Request(String description, User createdBy, String date, UrgencyLevel urgency) {
        this.description = description;
        this.createdBy = createdBy;
        this.date = date;
        this.urgency = urgency;
        this.status = RequestStatus.NEW;
    }

    public UrgencyLevel getUrgency() {
        return urgency;
    }

    public void setUrgency(UrgencyLevel urgency) {
        this.urgency = urgency;
    }

    public String getDescription() {
        return description;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "[" + status + "] " + description + " (by " + createdBy.getFullName() + " on " + date + ")";
    }
}
