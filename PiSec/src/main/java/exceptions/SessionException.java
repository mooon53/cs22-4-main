package exceptions;

public class SessionException extends Throwable {
	private final String detailMessage;

	public SessionException(String message) {
		this.detailMessage = message;
	}

	public SessionException() {
		this("");
	}
}
