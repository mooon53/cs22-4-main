package Exceptions;

public class SessionException extends Throwable {
	String message;

	public SessionException(String message) {
	}

	public SessionException() {
		this("");
	}
}
