package research;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DiplomaProject implements Serializable {
	private static final long serialVersionUID = 1L;

	private String title;
	private List<ResearchPaper> publishedPapers;

	public DiplomaProject() {
		this.publishedPapers = new ArrayList<>();
	}

	public DiplomaProject(String title) {
		this.title = title;
		this.publishedPapers = new ArrayList<>();
	}

	public void addPaper(ResearchPaper paper) {
		publishedPapers.add(paper);
	}

	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }

	public List<ResearchPaper> getPublishedPapers() { return publishedPapers; }
	public void setPublishedPapers(List<ResearchPaper> publishedPapers) { this.publishedPapers = publishedPapers; }

	@Override
	public String toString() {
		return "DiplomaProject [title=" + title + ", papers=" + publishedPapers.size() + "]";
	}
}
