package com.hacktx.model;

public class User {
	private String firstName;
	private String lastName;
	private String email;
	private String aadvantageId;
	private String gender;
	
	public User(String firstName, String lastName, String email, String aadvantageId, String gender) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.aadvantageId = aadvantageId;
		this.gender = gender;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAadvantageId() {
		return aadvantageId;
	}
	public void setAadvantageId(String aadvantageId) {
		this.aadvantageId = aadvantageId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
