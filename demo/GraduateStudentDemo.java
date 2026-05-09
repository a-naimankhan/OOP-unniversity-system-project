package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import other.LanguageManager;
import other.RecommendationLetter;
import other.Startup;
import research.PaperByCitations;
import research.PaperByDate;
import research.PaperByPages;
import research.ResearchPaper;
import research.ResearchProject;
import users.Database;
import users.GraduateStudent;
import users.User;
import exceptions.NotResearcherException;
import exceptions.LowHIndexException;

public class GraduateStudentDemo {

	public static void run(User user, BufferedReader brIn) {
		if (!(user instanceof GraduateStudent)) {
			System.out.println("This demo is only for GraduateStudent users.");
			return;
		}
		GraduateStudent grad = (GraduateStudent) user;
		BufferedReader br = brIn;

		try {
			menu: while (true) {
				System.out.println(t("grad_menu", grad));
				System.out.print("Choice: ");
				String line = br.readLine();
				int choice;
				try { choice = Integer.parseInt(line.trim()); }
				catch (NumberFormatException e) { System.out.println("Invalid input"); continue; }

				switch (choice) {
				case 1: {
					System.out.print(t("title_prompt", grad));    String title = br.readLine();
					System.out.print(t("authors_prompt", grad));  String authLine = br.readLine();
					List<String> authors = new ArrayList<>();
					for (String a : authLine.split(",")) authors.add(a.trim());
					System.out.print(t("journal_prompt", grad));  String journal = br.readLine();
					System.out.print(t("pages_prompt", grad));    int pages = Integer.parseInt(br.readLine().trim());
					System.out.print(t("year_prompt", grad));     int year = Integer.parseInt(br.readLine().trim());
					Date date = new Date(year - 1900, 0, 1);
					System.out.print(t("doi_prompt", grad));      String doi = br.readLine();
					if (doi != null && doi.trim().isEmpty()) doi = null;
					System.out.print(t("citations_prompt", grad)); int citations = Integer.parseInt(br.readLine().trim());
					System.out.print(t("volume_prompt", grad));   int volume = Integer.parseInt(br.readLine().trim());
					System.out.print(t("number_prompt", grad));   int number = Integer.parseInt(br.readLine().trim());
					try {
						grad.addResearchPaper(new ResearchPaper(title, authors, journal, pages, date, doi, citations, volume, number));
						System.out.println(t("paper_added", grad));
					} catch (IllegalArgumentException ex) {
						System.out.println(t("paper_fail", grad) + ex.getMessage());
					}
					break;
				}
				case 2: {
					System.out.println(t("sort_by", grad));
					int s;
					try { s = Integer.parseInt(br.readLine().trim()); } catch (NumberFormatException e) { s = 1; }
					if (s == 2) grad.printPapers(new PaperByCitations());
					else if (s == 3) grad.printPapers(new PaperByPages());
					else grad.printPapers(new PaperByDate());
					break;
				}
				case 3: {
					System.out.print(t("project_topic", grad));
					String topic = br.readLine();
					ResearchProject proj = new ResearchProject(topic);
					try {
						proj.addParticipant(grad);
						grad.getResearchProjects().add(proj);
						System.out.println(t("joined_project", grad) + topic);
					} catch (NotResearcherException | LowHIndexException ex) {
						System.out.println(t("cannot_join", grad) + ex.getMessage());
					}
					break;
				}
				case 4:
					System.out.println("h-index: " + grad.calculateHIndex());
					break;
				case 5: {
					System.out.print(t("startup_name", grad));  String sName = br.readLine();
					System.out.print(t("startup_desc", grad));  String sDesc = br.readLine();
					Startup startup = new Startup(sName, grad, sDesc);
					Database.startups.add(startup);
					Database.log("Startup created by " + grad.getFullName() + ": " + sName);
					System.out.println(t("startup_created", grad));
					System.out.println(startup);
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
					if (!found) System.out.println(t("no_rec_letters", grad));
					break;
				}
				case 7:
					Database.save();
					System.out.println(t("menu_logout", grad));
					break menu;
				default:
					System.out.println("Unknown choice");
				}
			}
		} catch (IOException ex) {
			System.out.println("I/O error: " + ex.getMessage());
		}
	}

	private static String t(String key, GraduateStudent grad) {
		return LanguageManager.getTranslation(key, grad.getLanguage());
	}
}
