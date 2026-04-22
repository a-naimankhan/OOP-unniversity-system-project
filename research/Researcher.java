package research;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import other.News;
import other.NewsTopic;
import users.Database;
import users.User;

public interface Researcher {

    List<ResearchPaper> getResearchPapers();
    List<ResearchProject> getResearchProjects();

    default void addResearchPaper(ResearchPaper paper) {
        getResearchPapers().add(paper);
        
        // Auto-generate news
        String title = "New Research Paper Published!";
        String authorName = (this instanceof User) ? ((User)this).getFullName() : "A researcher";
        String text = authorName + " has published a new paper: \"" + paper.getTitle() + "\" in " + paper.getJournal() + ".";
        
        News n = new News(title, text, new java.util.Date().toString(), NewsTopic.RESEARCH, (this instanceof User) ? (User)this : null);
        Database.news.add(n);
        
        // Update top cited researcher news if needed
        Database.getTopCitedResearcher();
    }

    default void printPapers(Comparator<ResearchPaper> c) {
		List<ResearchPaper> sorted = new ArrayList<>(getResearchPapers());
		Collections.sort(sorted, c);
		for (ResearchPaper p : sorted) {
			System.out.println(p);
		}
	}

	default int calculateHIndex() {
		List<ResearchPaper> papers = getResearchPapers();
		List<Integer> citations = new ArrayList<>();
		for (ResearchPaper p : papers) {
			citations.add(p.getCitations());
		}
		Collections.sort(citations, Collections.reverseOrder());

		int h = 0;
		for (int i = 0; i < citations.size(); i++) {
			if (citations.get(i) >= i + 1) {
				h = i + 1;
			} else {
				break;
			}
		}
		return h;
	}
}
