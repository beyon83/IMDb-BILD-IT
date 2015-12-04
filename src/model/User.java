package model;

import java.util.Date;
import java.text.SimpleDateFormat;

public class User {
	
	/** Declare instance variables */
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	private String gender;
	private String registrationDate;
	private String email;
	
	/** No-arg constructor */
	public User() {
		
	}
	
	/** Constructor with specified data */
	public User(String userName, String firstName, String lastName, String password, String gender, String registrationDate, String email) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.gender = gender;
		this.registrationDate = registrationDate;
		this.email = email;
	}
	
	/** Getters and Setters */
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRegistrationDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		Date date = new Date();
		this.registrationDate = dateFormat.format(date);
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
