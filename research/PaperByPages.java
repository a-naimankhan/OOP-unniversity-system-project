package research;

import java.util.Comparator;

public class PaperByPages implements Comparator<ResearchPaper> {
	@Override
	public int compare(ResearchPaper p1, ResearchPaper p2) {
		return Integer.compare(p2.getPages(), p1.getPages());
	}
}
