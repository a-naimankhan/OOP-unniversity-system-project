package exceptions;

public class CreditLimitExceededException extends Exception {
	public CreditLimitExceededException() {
		super("Student cannot have more than 21 credits.");
	}

	public CreditLimitExceededException(String message) {
		super(message);
	}
}

