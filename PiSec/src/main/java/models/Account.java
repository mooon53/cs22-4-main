package models;

public class Account {
	private final String username;
	private final String password;
	private final String ppPath;

	public Account(String username, String password, String filePath) {
		this.username = username;
		this.password = password;
		this.ppPath = filePath;
	}

	public Account(String username, String password) {
		this(username, password, "");
	}

//	public String getPassword() {
//		return password;
//	}

	public String getPpPath() {return ppPath;}
	public String getUsername() {return username;}

	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
}
