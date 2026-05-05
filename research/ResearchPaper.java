package research;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ResearchPaper implements Serializable, Comparable<ResearchPaper> {
	private static final long serialVersionUID = 1L;

	private String title;
	private List<String> authors;
	private String journal;
	private int pages;
	private Date date;
	private String doi;
	private int citations;
	private int volume;
	private int number;

	public ResearchPaper() {}

	public ResearchPaper(String title, List<String> authors, String journal,
			int pages, Date date, String doi, int citations, int volume, int number) {
		this.title = title;
		this.authors = authors;
		this.journal = journal;
		this.pages = pages;
		this.date = date;
		this.doi = doi;
		this.citations = citations;
		this.volume = volume;
		this.number = number;
	}

	public String getCitation(Format f) {
		if (authors == null || authors.isEmpty()) {
			return "[No authors available for " + title + "]";
		}
		if (date == null) {
			return "[No date available for " + title + "]";
		}

		String authorsStr = String.join(", ", authors);
		@SuppressWarnings("deprecation")
		int year = date.getYear() + 1900;

		if (f == Format.PLAIN_TEXT) {
			return authorsStr + ", \"" + title + ",\" " + journal
					+ ", vol. " + volume + ", no. " + number
					+ ", pp. " + pages + ", " + year
					+ ", doi: " + (doi != null ? doi : "N/A") + ".";
		} else {
			// BibTeX
			String firstAuthorLastName = authors.get(0).split(" ")[0].toLowerCase();
			String key = firstAuthorLastName + year;
			return "@ARTICLE{" + key + ",\n"
					+ "  author={" + authorsStr + "},\n"
					+ "  journal={" + (journal != null ? journal : "") + "},\n"
					+ "  title={" + title + "},\n"
					+ "  year={" + year + "},\n"
					+ "  volume={" + volume + "},\n"
					+ "  number={" + number + "},\n"
					+ "  pages={" + pages + "},\n"
					+ "  doi={" + (doi != null ? doi : "") + "}\n"
					+ "}";
		}
	}

	@Override
	public int compareTo(ResearchPaper other) {
		if (this.date == null && other.date == null) return 0;
		if (this.date == null) return -1;
		if (other.date == null) return 1;
		return this.date.compareTo(other.date);
	}

	// Getters and setters
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }

	public List<String> getAuthors() { return authors; }
	public void setAuthors(List<String> authors) { this.authors = authors; }

	public String getJournal() { return journal; }
	public void setJournal(String journal) { this.journal = journal; }

	public int getPages() { return pages; }
	public void setPages(int pages) { this.pages = pages; }

	public Date getDate() { return date; }
	public void setDate(Date date) { this.date = date; }

	public String getDoi() { return doi; }
	public void setDoi(String doi) { this.doi = doi; }

	public int getCitations() { return citations; }
	public void setCitations(int citations) { this.citations = citations; }

	public int getVolume() { return volume; }
	public void setVolume(int volume) { this.volume = volume; }

	public int getNumber() { return number; }
	public void setNumber(int number) { this.number = number; }

	@Override
	public String toString() {
		return "ResearchPaper [title=" + title + ", authors=" + authors
				+ ", journal=" + journal + ", citations=" + citations + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(doi, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		ResearchPaper other = (ResearchPaper) obj;
		return Objects.equals(doi, other.doi) && Objects.equals(title, other.title);
	}
}
