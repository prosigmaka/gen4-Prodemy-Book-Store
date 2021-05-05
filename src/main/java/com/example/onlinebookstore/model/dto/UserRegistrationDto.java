package com.example.onlinebookstore.model.dto;

import lombok.Data;

@Data
public class UserRegistrationDto {
	private Long idUser;
	private String firstName;
	private String lastName;
	private String username;
	private String emailUser;
	private String address;
	private String phone;
	private String password;
	private String matchingPassword;
	private Long idRole;

//	//constructor
//	public UserRegistrationDto(){
//
//	}
//
//	public UserRegistrationDto(String firstName, String lastName, String username, String email, String address, Integer phone, String password) {
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.username = username;
//		this.email = email;
//		this.address = address;
//		this.phone = phone;
//		this.password = password;
//	}
//
//	public String getFirstName() {
//		return firstName;
//	}
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//	public String getLastName() {
//		return lastName;
//	}
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getAddress() {
//		return address;
//	}
//	public void setAddress(String address) {
//		this.address = address;
//	}
//	public Integer getPhone() {
//		return phone;
//	}
//	public void setPhone(Integer phone) {
//		this.phone = phone;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
}
