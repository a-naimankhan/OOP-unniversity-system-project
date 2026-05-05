package users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import research.Researcher;
import research.ResearchPaper;
import research.ResearchProject;

/**
 * A Teacher who also conducts research.
 * Per the TZ, all Professors (DegreeTeacher.PROFESSOR) must be Researchers.
 * Use this class (or its subclass) when creating professor-level teachers.
 */
public class ResearchTeacher extends Teacher implements Serializable, Researcher {

    private static final long serialVersionUID = 1L;

    private List<ResearchPaper> researchPapers;
    private List<ResearchProject> researchProjects;

    public ResearchTeacher() {
        super();
        researchPapers = new ArrayList<>();
        researchProjects = new ArrayList<>();
    }

    public ResearchTeacher(String fullName, String username, String password, int salary,
            DegreeTeacher degree, FacultyType department) {
        super(fullName, username, password, salary, degree, department);
        researchPapers = new ArrayList<>();
        researchProjects = new ArrayList<>();
    }

    @Override
    public List<ResearchPaper> getResearchPapers() { return researchPapers; }

    @Override
    public List<ResearchProject> getResearchProjects() { return researchProjects; }

    @Override
    public void addResearchPaper(ResearchPaper paper) {
        Researcher.super.addResearchPaper(paper);
    }

    @Override
    public int calculateHIndex() {
        return Researcher.super.calculateHIndex();
    }

    @Override
    public void printPapers(Comparator<ResearchPaper> c) {
        Researcher.super.printPapers(c);
    }

    @Override
    public String toString() {
        return super.toString() + " [ResearchTeacher, papers=" + researchPapers.size()
                + ", h-index=" + calculateHIndex() + "]";
    }
}
