package users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import research.Researcher;
import research.ResearchPaper;
import research.ResearchProject;

public class ResearchTeacher extends Teacher implements Researcher, Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<ResearchPaper> researchPapers;
    private List<ResearchProject> researchProjects;

    public ResearchTeacher(String fullName, String username, String password, int salary, DegreeTeacher degree, FacultyType department) {
        super(fullName, username, password, salary, degree, department);
        this.researchPapers = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
    }

    @Override
    public List<ResearchPaper> getResearchPapers() {
        return researchPapers;
    }

    @Override
    public List<ResearchProject> getResearchProjects() {
        return researchProjects;
    }

    @Override
    public String toString() {
        return super.toString() + " [Researcher, h-index=" + calculateHIndex() + "]";
    }
}
