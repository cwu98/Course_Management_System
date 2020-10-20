package courseRegistrationSystem;

public class User implements java.io.Serializable{
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	
	public void generateUsername(String username) {
		this.username = username;
	}
	public void generatePassword(String password) {
		this.password = password;
	}
}
