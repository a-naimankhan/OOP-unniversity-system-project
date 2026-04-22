package users;

import java.util.Objects;
import java.util.Vector;
import java.util.ArrayList;
import java.util.List;

import other.News;
import other.Language;
import other.LanguageManager;
import research.Observer;
import research.Journal;

public abstract class User implements Comparable<Object>, Observer {
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
	 * we view News
	 */
	public static Vector<News> viewNews(){
		return Database.news;
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
	public int compareTo(Object o) {
		User u = (User)o;
		return fullName.compareTo(u.fullName);
	}
	
}