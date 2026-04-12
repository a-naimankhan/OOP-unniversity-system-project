package exceptions;

public class MaxFailException extends Exception {
	public MaxFailException() {
		super("Student has failed more than 3 times and cannot continue.");
	}

	public MaxFailException(String message) {
		super(message);
	}
}
