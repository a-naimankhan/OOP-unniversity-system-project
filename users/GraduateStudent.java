package users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import research.Researcher;
import research.ResearchPaper;
import research.ResearchProject;
import research.DiplomaProject;
import exceptions.InvalidSupervisorException;

public class GraduateStudent extends Student implements Serializable, Researcher {
    private static final long serialVersionUID = 1L;
    
    private Researcher researchSupervisor;
    private DiplomaProject diplomaProject;
    private List<ResearchPaper> researchPapers;
    private List<ResearchProject> researchProjects;

    {
        researchPapers = new ArrayList<>();
        researchProjects = new ArrayList<>();
    }

    public GraduateStudent() {
        super();
    }

    public GraduateStudent(String fullName, String username, String password, String id, int studyYear, DegreeStudent degree, FacultyType faculty, String speciality) {
        super(fullName, username, password, id, studyYear, degree, faculty, speciality);
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

    public void setResearchSupervisor(Researcher supervisor) throws InvalidSupervisorException {
        if (supervisor.calculateHIndex() < 3) {
            throw new InvalidSupervisorException(
                "Supervisor h-index is " + supervisor.calculateHIndex() + ", must be >= 3.");
        }
        this.researchSupervisor = supervisor;
    }

    public Researcher getResearchSupervisor() {
        return researchSupervisor;
    }

    public DiplomaProject getDiplomaProject() {
        return diplomaProject;
    }

    public void setDiplomaProject(DiplomaProject diplomaProject) {
        this.diplomaProject = diplomaProject;
    }
}
