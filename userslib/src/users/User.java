package users;

import persons.*;

/**
 * A class for representing users, which are of course persons.
 * They can have a password, a description and a personal image.
 *
 * @author Macky Dieng
 * @author Baptiste Vannesson
 */
public class User extends Person {

    /** The user's id */
    private int id;

    /** The user's passowrd */
    private String password;

    /** The user's description */
    private String description;

    /** The user's image */
    private String image;

    /** The user's role */
    private String role;

    /**
     * Builds a new user.
     *
     * @param lastName The user's last name
     * @param firstName The user's first name
     * @param email The user's email address
     * @param password The user's password
     */
    public User(String lastName, String firstName, String email, String password, String role) {
        super(lastName,firstName, email);
        this.password = password;
        this.role = role;
    }

    /**
     * Builds a new user.
     *
     * @param lastName The user's last name
     * @param firstName The user's first name
     * @param desc The user's description
     * @param image The user's image
     * @param email The user's email
     * @param password The user's password
     */
    public User(int id, String lastName, String firstName, String desc, String image, String email, String password, String role) {
        super(lastName, firstName, email);
        this.id = id;
        this.password = password;
        this.description = desc;
        this.image = image;
        this.role = role;
    }

    /**
     * Returns the user's id
     *
     * @return id The user's id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the user's id
     *
     * @param id The user's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the user's password.
     *
     * @return The user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password The user's new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the user's description.
     *
     * @return The user's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the user's description.
     *
     * @param description The user's new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the user's image.
     *
     * @return The user's image
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the user's image.
     *
     * @param image The user's new image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Returns the user's role
     *
     * @return The user's role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the user's role
     *
     * @param role The user's new role
     */
    public void setRole(String role) {
        this.role = role;
    }

}
