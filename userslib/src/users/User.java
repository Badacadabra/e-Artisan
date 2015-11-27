package users;
import persons.*;

public class User extends Person {

	private String password;
	
	public User(String lastName, String firstName, String email,String password) {
		super(lastName,firstName, email);
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
