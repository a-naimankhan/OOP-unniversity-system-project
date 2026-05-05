package other;

import java.io.Serializable;
import users.User;

public class Request implements Serializable {
    private static final long serialVersionUID = 1L;

    private String description;
    private RequestStatus status;
    private User createdBy;
    private User signedBy;
    private String date;
    private UrgencyLevel urgency;
    private User signedBy;

    public Request(String description, User createdBy, String date, UrgencyLevel urgency) {
        this.description = description;
        this.createdBy = createdBy;
        this.date = date;
        this.urgency = urgency;
        this.status = RequestStatus.NEW;
    }

    public UrgencyLevel getUrgency() { return urgency; }
    public void setUrgency(UrgencyLevel urgency) { this.urgency = urgency; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public RequestStatus getStatus() { return status; }
    public void setStatus(RequestStatus status) { this.status = status; }

    public User getCreatedBy() { return createdBy; }

    public String getDate() { return date; }

    public User getSignedBy() { return signedBy; }

    public void signBy(User official) {
        this.signedBy = official;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(status).append("] [").append(urgency).append("] ");
        sb.append(description);
        sb.append(" (by ").append(createdBy.getFullName()).append(" on ").append(date).append(")");
        if (signedBy != null) {
            sb.append(" [signed by ").append(signedBy.getFullName()).append("]");
        }
        return sb.toString();
    }
}
