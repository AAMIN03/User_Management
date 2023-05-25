package com.springrest.User_Management.entities;
import javax.validation.constraints.Pattern;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

	@Id
	private String id;
	private String firstname;
	private String lastname;
	@Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number")
	private String mobileNo;
	private String email;
	private String address1;
	private String address2;
	
	public User(String id, String firstname, String lastname, String  mobileNo, String email, String address1,
			String address2) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.mobileNo = mobileNo;
		this.email = email;
		this.address1 = address1;
		this.address2 = address2;
	}
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getid() {
		return id;
	}
	public void setid(String id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", mobileNo="
				+ mobileNo + ", email=" + email + ", address1=" + address1 + ", address2=" + address2 + "]";
	}
}
