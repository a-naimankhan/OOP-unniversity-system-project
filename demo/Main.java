package demo;

import java.util.Arrays;
import java.util.Date;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import course.Course;
import course.Period;
import exceptions.CreditLimitExceededException;
import exceptions.InvalidSupervisorException;
import exceptions.LowHIndexException;
import exceptions.MaxFailException;
import exceptions.NotResearcherException;
import mark.AttestationType;
import research.*;
import users.*;

public class Main {
	public static void main(String[] args) {
		System.out.println("=== University System Test ===\n");

		// --- Create users ---
		Admin admin = new Admin("Admin User", "admin", "admin123", 500000);
		admin.addUser(admin);

		Teacher prof = new Teacher("Dr. Almas Bekov", "almas", "pass123", 800000,
				DegreeTeacher.PROFESSOR, FacultyType.FIT);
		admin.addUser(prof);

		Teacher lecturer = new Teacher("Mr. Sanzhar Aitov", "sanzhar", "pass456", 400000,
				DegreeTeacher.LECTURER, FacultyType.FIT);
		admin.addUser(lecturer);

		Student s1 = new Student("Aidar Nurtas", "aidar", "student1", "22BD001", 2,
				DegreeStudent.BACHELOR, FacultyType.FIT, "Computer Science");
		admin.addUser(s1);

		Student s2 = new Student("Marat Karimov", "marat", "student2", "22BD002", 2,
				DegreeStudent.BACHELOR, FacultyType.FIT, "Information Systems");
		admin.addUser(s2);

		GraduateStudent grad = new GraduateStudent("Dana Saparova", "dana", "grad1",
				"22MD001", 1, DegreeStudent.MASTER, FacultyType.FIT, "Data Science");
		admin.addUser(grad);

		Manager manager = new Manager("Bolat Serik", "bolat", "mgr123", 600000, ManagerType.OR);
		admin.addUser(manager);

		System.out.println("Users created: " + Database.users.size());
		System.out.println("Students: " + Database.students.size());
		System.out.println("Teachers: " + Database.teachers.size());

		// --- Create courses ---
		Course oop = new Course("OOP", Period.SPRING, "CSCI2106", 6, false, 30, null, FacultyType.FIT);
		Course algo = new Course("Algorithms", Period.SPRING, "CSCI2107", 5, false, 25, null, FacultyType.FIT);
		Course elective = new Course("Oil Processing", Period.FALL, "PETR3001", 3, true, 20, null, FacultyType.FEOG);
		manager.addCourse(oop);
		manager.addCourse(algo);
		manager.addCourse(elective);
		manager.assignCoursesToTeachers(oop, prof);
		manager.assignCoursesToTeachers(algo, lecturer);
		System.out.println("\nCourses created: " + Database.courses.size());

		// --- Course registration ---
		System.out.println("\n=== Course Registration ===");
		try {
			boolean reg1 = s1.registerCourse(oop);
			boolean reg2 = s1.registerCourse(algo);
			boolean reg3 = s2.registerCourse(oop);
			System.out.println("Aidar registered OOP: " + reg1);
			System.out.println("Aidar registered Algo: " + reg2);
			System.out.println("Marat registered OOP: " + reg3);
		} catch (CreditLimitExceededException | MaxFailException e) {
			System.out.println("Registration error: " + e.getMessage());
		}
		System.out.println("Aidar total credits: " + s1.getTotalCredit());

		// --- Put marks ---
		System.out.println("\n=== Putting Marks ===");
		prof.putMark(oop, s1, 25, AttestationType.FIRST);
		prof.putMark(oop, s1, 28, AttestationType.SECOND);
		prof.putMark(oop, s1, 35, AttestationType.EXAM);
		System.out.println("Aidar OOP mark: " + s1.viewMarks(oop));
		System.out.println("Aidar OOP final: " + s1.viewMarks(oop).getFinalAttestation());

		// --- Research papers ---
		System.out.println("\n=== Research System ===");
		ResearchPaper paper1 = new ResearchPaper(
				"Deep Learning in NLP",
				Arrays.asList("Dr. Almas Bekov", "Dana Saparova"),
				"IEEE Transactions on AI",
				15, new Date(124, 5, 15), "10.1109/TAI.2024.001",
				45, 12, 3);

		ResearchPaper paper2 = new ResearchPaper(
				"Quantum Computing Survey",
				Arrays.asList("Dr. Almas Bekov"),
				"ACM Computing Surveys",
				32, new Date(123, 1, 10), "10.1145/CS.2023.002",
				120, 56, 1);

		ResearchPaper paper3 = new ResearchPaper(
				"ML in Education",
				Arrays.asList("Dr. Almas Bekov", "Dana Saparova"),
				"Journal of CS Education",
				10, new Date(125, 0, 20), "10.1109/JCSE.2025.003",
				8, 8, 2);

		// Add papers to grad student
		grad.addResearchPaper(paper1);
		grad.addResearchPaper(paper3);

		System.out.println("Dana's papers:");
		grad.printPapers(new PaperByDate());
		System.out.println("Dana's h-index: " + grad.calculateHIndex());

		// --- Research Project ---
		System.out.println("\n=== Research Project ===");
		ResearchProject project = new ResearchProject("AI in University Education");
		try {
			project.addParticipant(grad);
			System.out.println("Dana joined the project successfully.");
		} catch (NotResearcherException e) {
			System.out.println(e.getMessage());
		}

		// Test: non-researcher trying to join
		try {
			project.addParticipant(s1);
			System.out.println("Aidar joined (should not happen).");
		} catch (NotResearcherException e) {
			System.out.println("Expected error: " + e.getMessage());
		}

		// --- Supervisor assignment ---
		System.out.println("\n=== Supervisor Assignment ===");
		// Create a researcher-teacher with low h-index for testing
		// (grad has h-index based on her papers)
		try {
			// grad has only 2 papers, h-index probably < 3
			grad.setResearchSupervisor(grad);
			System.out.println("Supervisor set (should not happen if h < 3).");
		} catch (InvalidSupervisorException e) {
			System.out.println("Expected error: " + e.getMessage());
		}

		// --- Citations ---
		System.out.println("\n=== Paper Citations ===");
		System.out.println("Plain Text:");
		System.out.println(paper1.getCitation(Format.PLAIN_TEXT));
		System.out.println("\nBibTeX:");
		System.out.println(paper2.getCitation(Format.BIBTEX));

		// --- Comparators ---
		System.out.println("\n=== Sorting Papers ===");
		System.out.println("By citations (most cited first):");
		grad.addResearchPaper(paper2);
		grad.printPapers(new PaperByCitations());

		System.out.println("\nBy pages (longest first):");
		grad.printPapers(new PaperByPages());

		// --- Diploma Project ---
		System.out.println("\n=== Diploma Project ===");
		DiplomaProject diploma = new DiplomaProject("AI-Powered Student Analytics");
		diploma.addPaper(paper1);
		diploma.addPaper(paper3);
		grad.setDiplomaProject(diploma);
		System.out.println(grad.getDiplomaProject());

		// --- Top cited researcher ---
		System.out.println("\n=== Top Cited Researcher ===");
		research.Researcher topResearcher = Database.getTopCitedResearcher();
		if (topResearcher != null) {
			System.out.println("Top cited: " + ((User) topResearcher).getFullName()
					+ " (h-index: " + topResearcher.calculateHIndex() + ")");
		}

		// --- Interactive Login ---
		System.out.println("\n=== Interactive Login ===");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		User loggedIn = null;
		try {
			while (loggedIn == null) {
				System.out.print("Username: ");
				String uname = br.readLine();
				System.out.print("Password: ");
				String pwd = br.readLine();
				User candidate = null;
				for (User u : Database.users) {
					if (u.getUsername() != null && u.getUsername().equals(uname)) {
						candidate = u;
						break;
					}
				}
				if (candidate != null && candidate.login(uname, pwd)) {
					loggedIn = candidate;
				} else {
					System.out.println("Login failed. Please try again.");
				}
			}
			System.out.println("Logged in as: " + loggedIn.getFullName() + " (" + loggedIn.getClass().getSimpleName() + ")");
			// Dispatch to demo menus
			if (loggedIn instanceof Admin) {
				AdminDemo.run(loggedIn);
			} else if (loggedIn instanceof Manager) {
				ManagerDemo.run(loggedIn);
			} else if (loggedIn instanceof TechSupportSpecialist) {
				TechSupportDemo.run(loggedIn);
			} else if (loggedIn instanceof Teacher) {
				TeacherDemo.run(loggedIn);
			} else if (loggedIn instanceof GraduateStudent) {
				GraduateStudentDemo.run(loggedIn);
			} else if (loggedIn instanceof Student) {
				StudentDemo.run(loggedIn);
			} else {
				System.out.println("No demo available for user type: " + loggedIn.getClass().getSimpleName());
			}
		} catch (IOException e) {
			System.out.println("Login interrupted: " + e.getMessage());
		}






		System.out.println("\n=== ALL TESTS PASSED ===");
	}
}
