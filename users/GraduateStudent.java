package users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import exceptions.InvalidSupervisorException;
import research.DiplomaProject;
import research.Researcher;
import research.ResearchPaper;
import research.ResearchProject;

public class GraduateStudent extends Student implements Serializable, Researcher {
	private static final long serialVersionUID = 1L;

	private Researcher researchSupervisor;
	private DiplomaProject diplomaProject;
	private List<ResearchPaper> researchPapers;
	private List<ResearchProject> researchProjects;

	public GraduateStudent() {
		this.researchPapers = new ArrayList<>();
		this.researchProjects = new ArrayList<>();
	}

	public GraduateStudent(String fullName, String username, String password,
			String id, int studyYear, DegreeStudent degree,
			FacultyType faculty, String speciality) {
		super(fullName, username, password, id, studyYear, degree, faculty, speciality);
		this.researchPapers = new ArrayList<>();
		this.researchProjects = new ArrayList<>();
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

	@Override
	public List<ResearchPaper> getResearchPapers() {
		return researchPapers;
	}

	@Override
	public List<ResearchProject> getResearchProjects() {
		return researchProjects;
	}

	public void addResearchPaper(ResearchPaper paper) {
		researchPapers.add(paper);
	}

	public void addResearchProject(ResearchProject project) {
		researchProjects.add(project);
	}

	@Override
	public String toString() {
		return super.toString() + " GraduateStudent [supervisor=" + researchSupervisor
				+ ", diplomaProject=" + diplomaProject
				+ ", h-index=" + calculateHIndex() + "]";
	}
}
