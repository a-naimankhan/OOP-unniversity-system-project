package users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import other.Request;
import other.RequestStatus;

public class TechSupportSpecialist extends Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    public TechSupportSpecialist() { super(); }

    public TechSupportSpecialist(String fullName, String username, String password, int salary) {
        super(fullName, username, password, salary);
    }

    public List<Request> viewNewRequests() {
        List<Request> result = new ArrayList<>();
        for (Request r : Database.requests) {
            if (r.getStatus() == RequestStatus.NEW) result.add(r);
        }
        return result;
    }

    public List<Request> viewAcceptedRequests() {
        List<Request> result = new ArrayList<>();
        for (Request r : Database.requests) {
            if (r.getStatus() == RequestStatus.ACCEPTED) result.add(r);
        }
        return result;
    }

    public List<Request> viewDoneRequests() {
        List<Request> result = new ArrayList<>();
        for (Request r : Database.requests) {
            if (r.getStatus() == RequestStatus.DONE) result.add(r);
        }
        return result;
    }

    /** NEW → VIEWED */
    public void viewRequest(Request r) {
        if (r.getStatus() == RequestStatus.NEW) {
            r.setStatus(RequestStatus.VIEWED);
            System.out.println("Viewing: " + r);
        } else {
            System.out.println("Request is already " + r.getStatus());
        }
    }

    /** VIEWED → ACCEPTED */
    public boolean acceptRequest(Request r) {
        if (r.getStatus() != RequestStatus.VIEWED) {
            System.out.println("Cannot accept — request must be in VIEWED state, currently: " + r.getStatus());
            return false;
        }
        r.setStatus(RequestStatus.ACCEPTED);
        System.out.println("Accepted: " + r.getDescription());
        return true;
    }

    /** VIEWED → REJECTED */
    public boolean rejectRequest(Request r) {
        if (r.getStatus() != RequestStatus.VIEWED) {
            System.out.println("Cannot reject — request must be in VIEWED state, currently: " + r.getStatus());
            return false;
        }
        r.setStatus(RequestStatus.REJECTED);
        System.out.println("Rejected: " + r.getDescription());
        return true;
    }

    /** ACCEPTED → DONE */
    public boolean markAsDone(Request r) {
        if (r.getStatus() != RequestStatus.ACCEPTED) {
            System.out.println("Cannot mark done — request must be ACCEPTED, currently: " + r.getStatus());
            return false;
        }
        r.setStatus(RequestStatus.DONE);
        System.out.println("Done: " + r.getDescription());
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " TechSupportSpecialist";
    }
}
