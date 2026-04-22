package research;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Journal implements Subject, Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private List<ResearchPaper> publishedPapers;
    private List<Observer> subscribers;

    public Journal(String name) {
        this.name = name;
        this.publishedPapers = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<ResearchPaper> getPublishedPapers() {
        return publishedPapers;
    }

    public void publishPaper(ResearchPaper paper) {
        publishedPapers.add(paper);
        notifyObservers("New paper published in " + name + ": \"" + paper.getTitle() + "\"");
    }

    @Override
    public void subscribe(Observer observer) {
        if (!subscribers.contains(observer)) {
            subscribers.add(observer);
        }
    }

    @Override
    public void unsubscribe(Observer observer) {
        subscribers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : subscribers) {
            observer.update(message);
        }
    }

    @Override
    public String toString() {
        return "Journal: " + name + " (" + publishedPapers.size() + " papers, " + subscribers.size() + " subscribers)";
    }
}
