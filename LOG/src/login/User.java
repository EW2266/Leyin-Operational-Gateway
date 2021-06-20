package login;

public class User {
	private String username;
	private String password;
	private int permission;
	
	public User(String name, String pass, int perm) {
		username = name;
		password = pass;
		permission = perm;
	}
	
	public String getpass() {
		return password;
	}
	
	public String getname() {
		return username;
	}
	
	public int getperm() {
		return permission;
	}
}
