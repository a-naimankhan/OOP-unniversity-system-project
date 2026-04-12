package research;

import java.util.Comparator;

public class PaperByDate implements Comparator<ResearchPaper> {
	@Override
	public int compare(ResearchPaper p1, ResearchPaper p2) {
		return p1.getDate().compareTo(p2.getDate());
	}
}
