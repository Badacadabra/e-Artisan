package users;
import persons.*;

public class User extends Person {

	private String passwd;
	
	public User(String lastname, String firstName, String email,String passwd) {
		super(lastname,firstName, email);
		this.passwd = passwd;
		// TODO Auto-generated constructor stub
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
