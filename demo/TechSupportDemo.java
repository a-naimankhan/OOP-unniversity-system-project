package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import other.Request;
import users.Database;
import users.TechSupportSpecialist;
import users.User;

public class TechSupportDemo {

	public static void run(User user) {
		if (!(user instanceof TechSupportSpecialist)) {
			System.out.println("This demo is only for TechSupportSpecialist users.");
			return;
		}
		TechSupportSpecialist ts = (TechSupportSpecialist) user;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			menu: while (true) {
				System.out.println("\nTechSupport Menu:\n 1) View all requests\n 2) View NEW requests\n 3) Accept a request\n 4) Reject a request\n 5) Mark request as DONE\n 6) Logout");
				System.out.print("Choice: ");
				String line = br.readLine();
				int choice;
				try { choice = Integer.parseInt(line); } catch (NumberFormatException e) { System.out.println("Invalid input"); continue; }
				switch (choice) {
				case 1:
					if (Database.requests.isEmpty()) { System.out.println("No requests"); break; }
					for (int i = 0; i < Database.requests.size(); i++) {
						Request r = Database.requests.get(i);
						System.out.println(i + ") " + r.toString());
					}
					break;
				case 2:
					for (Request r : ts.viewNewRequests()) System.out.println(r);
					break;
				case 3:
					System.out.print("Enter request index to ACCEPT: ");
					int idxA = Integer.parseInt(br.readLine());
					try { Request ra = Database.requests.get(idxA); ts.viewRequest(ra); ts.acceptRequest(ra); } catch (IndexOutOfBoundsException ex) { System.out.println("Invalid index"); }
					break;
				case 4:
					System.out.print("Enter request index to REJECT: ");
					int idxR = Integer.parseInt(br.readLine());
					try { Request rr = Database.requests.get(idxR); ts.viewRequest(rr); ts.rejectRequest(rr); } catch (IndexOutOfBoundsException ex) { System.out.println("Invalid index"); }
					break;
				case 5:
					System.out.print("Enter request index to MARK DONE: ");
					int idxD = Integer.parseInt(br.readLine());
					try { Request rd = Database.requests.get(idxD); ts.markAsDone(rd); } catch (IndexOutOfBoundsException ex) { System.out.println("Invalid index"); }
					break;
				case 6:
					System.out.println("Logging out...");
					break menu;
				default:
					System.out.println("Unknown choice");
				}
			}
		} catch (IOException ex) {
			System.err.println("I/O error in TechSupportDemo: " + ex.getMessage());
		}
	}
}
