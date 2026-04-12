package exceptions;

public class NotResearcherException extends Exception {
	public NotResearcherException() {
		super("This person is not a Researcher and cannot join a ResearchProject.");
	}

	public NotResearcherException(String message) {
		super(message);
	}
}
