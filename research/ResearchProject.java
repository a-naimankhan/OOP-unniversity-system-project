package research;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import exceptions.NotResearcherException;

public class ResearchProject implements Serializable {
	private static final long serialVersionUID = 1L;

	private String topic;
	private List<ResearchPaper> publishedPapers;
	private List<Researcher> participants;

	public ResearchProject() {
		this.publishedPapers = new ArrayList<>();
		this.participants = new ArrayList<>();
	}

	public ResearchProject(String topic) {
		this.topic = topic;
		this.publishedPapers = new ArrayList<>();
		this.participants = new ArrayList<>();
	}

	public void addParticipant(Object person) throws NotResearcherException {
		if (!(person instanceof Researcher)) {
			throw new NotResearcherException(
				"Person is not a Researcher and cannot join the project: " + topic);
		}
		participants.add((Researcher) person);
	}

	public void addPaper(ResearchPaper paper) {
		publishedPapers.add(paper);
	}

	// Getters and setters
	public String getTopic() { return topic; }
	public void setTopic(String topic) { this.topic = topic; }

	public List<ResearchPaper> getPublishedPapers() { return publishedPapers; }
	public void setPublishedPapers(List<ResearchPaper> publishedPapers) { this.publishedPapers = publishedPapers; }

	public List<Researcher> getParticipants() { return participants; }

	@Override
	public String toString() {
		return "ResearchProject [topic=" + topic + ", papers=" + publishedPapers.size()
				+ ", participants=" + participants.size() + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(topic);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		ResearchProject other = (ResearchProject) obj;
		return Objects.equals(topic, other.topic);
	}
}
