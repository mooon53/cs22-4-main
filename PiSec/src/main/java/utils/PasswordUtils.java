package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordUtils {
	private static final String PEPPER = "eebc6750f8b148e013349ab5ea16efc4";

	private PasswordUtils() {
		throw new IllegalStateException("This class should not be instantiated");
	}

	public static String preparePassword(String password, String salt) {
		return hash(pepper(password), salt);
	}

	private static String hash(String password, String saltString) {
		byte[] salt = new byte[saltString.length()/2];
		for (int i = 0; i < salt.length; i++) {
			salt[i] = (byte) (Integer.parseInt(saltString.substring(2 * i, 2 * i + 2), 16) - 128);
		}
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-512");
			md.update(salt);
			byte[] hash = md.digest(password.getBytes());
			StringBuilder hashedPass = new StringBuilder();
			for (byte b : hash) {
				String hex = Integer.toHexString(Byte.toUnsignedInt(b));
				if (hex.length() < 2) hashedPass.append(0);
				hashedPass.append(hex);
			}
			return hashedPass.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} return null;
	}

	private static String pepper(String password) {
		return password + PEPPER;
	}

	public static String generateSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		StringBuilder saltString = new StringBuilder();
		for (byte b : salt) {
			String hex = Integer.toHexString(Byte.toUnsignedInt(b));
			if (hex.length() < 2) saltString.append(0);
			saltString.append(hex);
		}
		return saltString.toString();
	}
}
