package models;

public class Account {
	private final String username;
	private final String password;
	private final String salt;
	private final String ppPath;

	public Account(String username, String password, String salt, String filePath) {
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.ppPath = filePath;
	}

	public Account(String username, String password, String salt) {
		this(username, password, salt, "");
	}

	public String getPassword() {
		return null;  // DO NOT RETURN PASSWORD, USE checkPassword() INSTEAD!!!
	}

	public String getPpPath() {return ppPath;}
	public String getUsername() {return username;}

	public String getSalt() {return salt;}

	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
}
