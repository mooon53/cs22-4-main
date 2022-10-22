package models;

public class Account {
	private final String username;
	private final String password;

	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}
}
