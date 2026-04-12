package exceptions;

public class InvalidSupervisorException extends Exception {
	public InvalidSupervisorException() {
		super("Supervisor's h-index is less than 3. Cannot be assigned as a research supervisor.");
	}

	public InvalidSupervisorException(String message) {
		super(message);
	}
}
