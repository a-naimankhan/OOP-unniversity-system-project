package other;

import java.io.Serializable;


public class News implements Serializable{
	
	static final long serialVersionUID = 1L;
	
	private String title;
	private String text;
	private String postDate;
	
	public News() {
		
	}
	public News(String title, String text, String postDate) {
		this.title = title;
		this.text = text;
		this.postDate = postDate;
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
	
	
}
