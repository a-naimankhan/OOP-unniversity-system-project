package other;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import users.User;

public class News implements Serializable, Comparable<News> {
    private static final long serialVersionUID = 1L;
    
    private String title;
    private String text;
    private String postDate;
    private NewsTopic topic;
    private List<Comment> comments;
    private User author;
    private boolean pinned;

    public News() {
        this.comments = new ArrayList<>();
    }

    public News(String title, String text, String postDate, NewsTopic topic, User author) {
        this.title = title;
        this.text = text;
        this.postDate = postDate;
        this.topic = topic;
        this.author = author;
        this.comments = new ArrayList<>();
        this.pinned = (topic == NewsTopic.RESEARCH);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public NewsTopic getTopic() {
        return topic;
    }

    public void setTopic(NewsTopic topic) {
        this.topic = topic;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public User getAuthor() {
        return author;
    }

    public boolean isPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }

    @Override
    public int compareTo(News o) {
        if (this.pinned && !o.pinned) return -1;
        if (!this.pinned && o.pinned) return 1;
        return o.postDate.compareTo(this.postDate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(topic).append("] ").append(title);
        if (pinned) sb.append(" (PINNED)");
        sb.append("\nDate: ").append(postDate).append("\n");
        sb.append(text).append("\n");
        if (!comments.isEmpty()) {
            sb.append("Comments (").append(comments.size()).append("):\n");
            for (Comment c : comments) {
                sb.append("  - ").append(c).append("\n");
            }
        }
        return sb.toString();
    }
}
