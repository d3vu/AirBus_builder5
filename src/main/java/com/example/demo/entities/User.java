package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String firstName;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String firstName, String email, String passowrd, String phoneNumber) {
		super();
		
		this.firstName = firstName;
		this.email = email;
		this.passowrd = passowrd;
		this.phoneNumber = phoneNumber;
	}

	private String email;
	private String passowrd;
	private String phoneNumber;

	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getemail() {
		return email;
	}

	public void setemail(String email) {
		this.email = email;
	}

	public String getpassowrd() {
		return passowrd;
	}

	public void setpassowrd(String passowrd) {
		this.passowrd = passowrd;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
