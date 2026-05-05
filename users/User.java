package users;

import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;
import java.util.ArrayList;
import java.util.List;

import other.News;
import other.Language;
import other.LanguageManager;
import other.Request;
import other.UrgencyLevel;
import research.Observer;
import research.Journal;

public abstract class User implements Serializable, Comparable<User>, Observer {

	private static final long serialVersionUID = 1L;
	private String fullName;
	private String username;
	private String password;
	private boolean isLogged;
    private List<String> notifications;
    private Language language;
	
	public User() {
		this.notifications = new ArrayList<>();
        this.language = Language.EN; // Default language
	}
	public User(String fullName, String username, String password) {
		this.fullName = fullName;
		this.username = username;
		this.password = password;
        this.notifications = new ArrayList<>();
        this.language = Language.EN; // Default language
	}

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getTranslatedText(String key) {
        return LanguageManager.getTranslation(key, this.language);
    }

    public void subscribeToJournal(Journal journal) {
        journal.subscribe(this);
    }

    public void unsubscribeFromJournal(Journal journal) {
        journal.unsubscribe(this);
    }
	
    @Override
    public void update(String message) {
        notifications.add(message);
        System.out.println("[NOTIFICATION for " + fullName + "]: " + message);
    }

    public List<String> getNotifications() {
        return notifications;
    }

	/**
	 * to login
	 */
	public boolean login(String username, String password) {
		isLogged = username.equals(this.username) && password.equals(this.password);
		return isLogged;
	}
	
	/**
	 * to logout
	 */
	public boolean logout() {
		isLogged = false;
		return isLogged;
	}
	
	/**
	 * we can change password
	 */
	public void changePassword(String oldPassword, String newPassword) {
		if (oldPassword.equals(this.password)) {
			setPassword(newPassword);
		}
	}
	
	/**
	 * Creates a support request and adds it to the Database.
	 */
	public Request createRequest(String description, UrgencyLevel urgency) {
		Request r = new Request(description, this, new java.util.Date().toString(), urgency);
		Database.requests.add(r);
		Database.log("REQUEST created by " + username + ": " + description);
		return r;
	}

	/**
	 * we view News (sorted: pinned RESEARCH first, then by date desc)
	 */
	public static Vector<News> viewNews(){
		Vector<News> sorted = new Vector<>(Database.news);
		java.util.Collections.sort(sorted);
		return sorted;
	}
	
	/**
	 * to check logged or not
	 */
	public boolean isLogged() {
		return isLogged;
	}
	
	/**
	 * to get Users full name
	 */
	public String getFullName() {
		return fullName;
	}
	
	/**
	 * to set Users full name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	/**
	 * to get login name
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * to set login name
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * to get Users password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * to set Users password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * print Users fullName and loginName 
	 */
	public String toString() {
		return "User [fullName=" + fullName + ", username=" + username + "]";
	}
	
	/**
	 * generate the hash values of objects
	 */
	public int hashCode() {
		return Objects.hash(fullName, password, username);
	}
	
	/**
	 * we check inequality
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(fullName, other.fullName) && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}
	/**
	 * we compare full names of 2 objects
	 */
	@Override
	public int compareTo(User u) {
		if (u == null) return 1;
		if (this.fullName == null) return (u.fullName == null) ? 0 : -1;
		if (u.fullName == null) return 1;
		return fullName.compareTo(u.fullName);
	}
	
}