package utils;

public class PasswordUtils {
	private PasswordUtils() {throw new IllegalStateException("This class should not be instantiated");}

	public static String preparePassword(String password) {
		return hash(pepper(password));
	}

	private static String hash(String password) {
		return password;  // TODO: make hashing algorithm
	}

	private static String pepper(String password) {
		return password;  // TODO: make peppering algorithm
	}
}
