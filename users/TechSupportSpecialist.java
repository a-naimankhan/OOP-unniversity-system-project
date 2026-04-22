package users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import other.Request;
import other.RequestStatus;

public class TechSupportSpecialist extends Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    public TechSupportSpecialist() {
        super();
    }

    public TechSupportSpecialist(String fullName, String username, String password, int salary) {
        super(fullName, username, password, salary);
    }

    public List<Request> viewNewRequests() {
        List<Request> newReqs = new ArrayList<>();
        for (Request r : Database.requests) {
            if (r.getStatus() == RequestStatus.NEW) {
                newReqs.add(r);
            }
        }
        return newReqs;
    }

    public List<Request> viewAcceptedRequests() {
        List<Request> acceptedReqs = new ArrayList<>();
        for (Request r : Database.requests) {
            if (r.getStatus() == RequestStatus.ACCEPTED) {
                acceptedReqs.add(r);
            }
        }
        return acceptedReqs;
    }

    public List<Request> viewDoneRequests() {
        List<Request> doneReqs = new ArrayList<>();
        for (Request r : Database.requests) {
            if (r.getStatus() == RequestStatus.DONE) {
                doneReqs.add(r);
            }
        }
        return doneReqs;
    }

    public void acceptRequest(Request r) {
        r.setStatus(RequestStatus.ACCEPTED);
        System.out.println("Request accepted: " + r.getDescription());
    }

    public void rejectRequest(Request r) {
        r.setStatus(RequestStatus.REJECTED);
        System.out.println("Request rejected: " + r.getDescription());
    }

    public void markAsDone(Request r) {
        r.setStatus(RequestStatus.DONE);
        System.out.println("Request marked as done: " + r.getDescription());
    }

    public void viewRequest(Request r) {
        if (r.getStatus() == RequestStatus.NEW) {
            r.setStatus(RequestStatus.VIEWED);
        }
        System.out.println("Viewing request: " + r.toString());
    }

    @Override
    public String toString() {
        return super.toString() + " TechSupportSpecialist";
    }
}
