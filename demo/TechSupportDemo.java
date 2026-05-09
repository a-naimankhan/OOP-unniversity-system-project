package demo;

import java.io.BufferedReader;
import java.io.IOException;

import other.LanguageManager;
import other.Request;
import users.Database;
import users.TechSupportSpecialist;
import users.User;

public class TechSupportDemo {

	public static void run(User user, BufferedReader brIn) {
		if (!(user instanceof TechSupportSpecialist)) {
			System.out.println("This demo is only for TechSupportSpecialist users.");
			return;
		}
		TechSupportSpecialist ts = (TechSupportSpecialist) user;
		BufferedReader br = brIn;

		try {
			menu: while (true) {
				System.out.println(t("ts_menu", ts));
				System.out.print("Choice: ");
				String line = br.readLine();
				int choice;
				try { choice = Integer.parseInt(line); } catch (NumberFormatException e) { System.out.println("Invalid input"); continue; }
				switch (choice) {
				case 1:
					if (Database.requests.isEmpty()) { System.out.println(t("no_requests", ts)); break; }
					for (int i = 0; i < Database.requests.size(); i++)
						System.out.println(i + ") " + Database.requests.get(i));
					break;
				case 2:
					for (Request r : ts.viewNewRequests()) System.out.println(r);
					break;
				case 3:
					System.out.print(t("idx_accept", ts));
					try {
						Request ra = Database.requests.get(Integer.parseInt(br.readLine().trim()));
						ts.viewRequest(ra); ts.acceptRequest(ra);
					} catch (IndexOutOfBoundsException | NumberFormatException ex) {
						System.out.println(t("invalid_index", ts));
					}
					break;
				case 4:
					System.out.print(t("idx_reject", ts));
					try {
						Request rr = Database.requests.get(Integer.parseInt(br.readLine().trim()));
						ts.viewRequest(rr); ts.rejectRequest(rr);
					} catch (IndexOutOfBoundsException | NumberFormatException ex) {
						System.out.println(t("invalid_index", ts));
					}
					break;
				case 5:
					System.out.print(t("idx_done", ts));
					try {
						Request rd = Database.requests.get(Integer.parseInt(br.readLine().trim()));
						ts.markAsDone(rd);
					} catch (IndexOutOfBoundsException | NumberFormatException ex) {
						System.out.println(t("invalid_index", ts));
					}
					break;
				case 6:
					Database.save();
					System.out.println(t("menu_logout", ts));
					break menu;
				default:
					System.out.println("Unknown choice");
				}
			}
		} catch (IOException ex) {
			System.err.println("I/O error: " + ex.getMessage());
		}
	}

	private static String t(String key, TechSupportSpecialist ts) {
		return LanguageManager.getTranslation(key, ts.getLanguage());
	}
}
