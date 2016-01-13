package users;
import persons.*;

public class User extends Person {

	private String password;
	private String description;
	private String image;
	

	public User(String lastName, String firstName, String email,String password) {
		super(lastName,firstName, email);
		this.password = password;
	}
	public User(String lastName, String firstName,String desc, String image, String email,String password) {
		super(lastName,firstName, email);
		this.password = password;
		this.description = desc;
		this.image = image;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
