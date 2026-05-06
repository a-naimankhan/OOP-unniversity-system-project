package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import research.Format;
import research.PaperByCitations;
import research.PaperByDate;
import research.PaperByPages;
import research.ResearchPaper;
import research.ResearchProject;
import research.Researcher;
import users.User;
import exceptions.LowHIndexException;
import exceptions.NotResearcherException;

public class ResearcherDemo {

	public static void run(User user) {
		if (!(user instanceof Researcher)) {
			System.out.println("This demo is only for Researcher users.");
			return;
		}
		Researcher r = (Researcher) user;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			menu: while (true) {
				System.out.println("\nResearcher Menu:\n 1) Add research paper\n 2) Print papers (sort)\n 3) Compute h-index\n 4) Join research project\n 5) Generate citation\n 6) Logout");
				System.out.print("Choice: ");
				String line = br.readLine();
				int choice;
				try { choice = Integer.parseInt(line); } catch (NumberFormatException e) { System.out.println("Invalid input"); continue; }
				switch (choice) {
				case 1:
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
						ResearchPaper p = new ResearchPaper(title, authors, journal, pages, date, doi, citations, volume, number);
						r.addResearchPaper(p);
						System.out.println("Paper added.");
					} catch (IllegalArgumentException ex) {
						System.out.println("Failed to add paper: " + ex.getMessage());
					}
					break;
				case 2:
					System.out.println("Sort by: 1) Date 2) Citations 3) Pages");
					int s = Integer.parseInt(br.readLine());
					if (s == 1) r.printPapers(new PaperByDate());
					else if (s == 2) r.printPapers(new PaperByCitations());
					else if (s == 3) r.printPapers(new PaperByPages());
					else System.out.println("Unknown sort");
					break;
				case 3:
					System.out.println("h-index: " + r.calculateHIndex());
					break;
				case 4:
					System.out.print("Project topic: ");
					String topic = br.readLine();
					ResearchProject proj = new ResearchProject(topic);
					try {
						proj.addParticipant(r);
						r.getResearchProjects().add(proj);
						System.out.println("Joined project: " + topic);
					} catch (NotResearcherException | LowHIndexException ex) {
						System.out.println("Cannot join project: " + ex.getMessage());
					}
					break;
				case 5:
					// List papers with indices
					List<ResearchPaper> papers = r.getResearchPapers();
					if (papers.isEmpty()) { System.out.println("No papers"); break; }
					for (int i = 0; i < papers.size(); i++) System.out.println(i + ") " + papers.get(i).getTitle());
					System.out.print("Select paper index: ");
					int pi = Integer.parseInt(br.readLine());
					if (pi < 0 || pi >= papers.size()) { System.out.println("Invalid index"); break; }
					ResearchPaper chosen = papers.get(pi);
					System.out.println("Format: 1) Plain text 2) BibTeX");
					int fmt = Integer.parseInt(br.readLine());
					if (fmt == 1) System.out.println(chosen.getCitation(Format.PLAIN_TEXT));
					else if (fmt == 2) System.out.println(chosen.getCitation(Format.BIBTEX));
					else System.out.println("Unknown format");
					break;
				case 6:
					System.out.println("Logging out...");
					break menu;
				default:
					System.out.println("Unknown choice");
				}
			}
		} catch (IOException ex) {
			System.out.println("I/O error in ResearcherDemo: " + ex.getMessage());
		}
	}
}
