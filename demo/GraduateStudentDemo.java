package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import other.RecommendationLetter;
import other.Startup;
import research.PaperByDate;
import research.ResearchPaper;
import research.ResearchProject;
import users.Database;
import users.GraduateStudent;
import users.User;
import exceptions.NotResearcherException;
import exceptions.LowHIndexException;

public class GraduateStudentDemo {

	public static void run(User user) {
		if (!(user instanceof GraduateStudent)) {
			System.out.println("This demo is only for GraduateStudent users.");
			return;
		}
		GraduateStudent grad = (GraduateStudent) user;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			menu: while (true) {
				System.out.println("\nGraduateStudent Menu:\n"
						+ " 1) Add research paper\n"
						+ " 2) Print papers\n"
						+ " 3) Join research project\n"
						+ " 4) View h-index\n"
						+ " 5) Create startup\n"
						+ " 6) View recommendation letters\n"
						+ " 7) Logout");
				System.out.print("Choice: ");
				String line = br.readLine();
				int choice;
				try { choice = Integer.parseInt(line.trim()); }
				catch (NumberFormatException e) { System.out.println("Invalid input"); continue; }

				switch (choice) {
				case 1: {
					System.out.print("Title: ");
					String title = br.readLine();
					System.out.print("Authors (comma-separated): ");
					String authorsLine = br.readLine();
					List<String> authors = new ArrayList<>();
					for (String a : authorsLine.split(",")) authors.add(a.trim());
					System.out.print("Journal: ");
					String journal = br.readLine();
					System.out.print("Pages (int): ");
					int pages = Integer.parseInt(br.readLine());
					System.out.print("Year (e.g. 2024): ");
					int year = Integer.parseInt(br.readLine());
					Date date = new Date(year - 1900, 0, 1);
					System.out.print("DOI (or leave blank): ");
					String doi = br.readLine();
					if (doi != null && doi.trim().isEmpty()) doi = null;
					System.out.print("Citations (int): ");
					int citations = Integer.parseInt(br.readLine());
					System.out.print("Volume (int): ");
					int volume = Integer.parseInt(br.readLine());
					System.out.print("Number (int): ");
					int number = Integer.parseInt(br.readLine());
					try {
						ResearchPaper p = new ResearchPaper(title, authors, journal,
								pages, date, doi, citations, volume, number);
						grad.addResearchPaper(p);
						System.out.println("Paper added.");
					} catch (IllegalArgumentException ex) {
						System.out.println("Failed: " + ex.getMessage());
					}
					break;
				}
				case 2:
					grad.printPapers(new PaperByDate());
					break;
				case 3: {
					System.out.print("Project topic: ");
					String topic = br.readLine();
					ResearchProject proj = new ResearchProject(topic);
					try {
						proj.addParticipant(grad);
						grad.getResearchProjects().add(proj);
						System.out.println("Joined project: " + topic);
					} catch (NotResearcherException | LowHIndexException ex) {
						System.out.println("Cannot join: " + ex.getMessage());
					}
					break;
				}
				case 4:
					System.out.println("h-index: " + grad.calculateHIndex());
					break;
				case 5: {
					System.out.print("Startup name: ");
					String sName = br.readLine();
					System.out.print("Description: ");
					String sDesc = br.readLine();
					Startup startup = new Startup(sName, grad, sDesc);
					Database.startups.add(startup);
					Database.log("Startup created by " + grad.getFullName() + ": " + sName);
					System.out.println("Startup created:\n" + startup);
					break;
				}
				case 6: {
					boolean found = false;
					for (RecommendationLetter letter : Database.recommendationLetters) {
						if (letter.getStudent() != null
								&& letter.getStudent().getFullName().equals(grad.getFullName())) {
							System.out.println(letter);
							found = true;
						}
					}
					if (!found) System.out.println("No recommendation letters found.");
					break;
				}
				case 7:
					System.out.println("Logging out...");
					break menu;
				default:
					System.out.println("Unknown choice");
				}
			}
		} catch (IOException ex) {
			System.out.println("I/O error: " + ex.getMessage());
		}
	}
}
