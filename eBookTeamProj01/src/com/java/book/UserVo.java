package com.java.book;

public class UserVo {
	private int uid;
	private int userId;
	private int userPassword;
	private String address;
	private String phoneNumber;
	private String email;
	private int admin;
	
	public UserVo() {
		
	}
	
	public UserVo(int uid, int userId, int userPassword, String address, String phoneNumber, String email, int admin) {
		
	}
	
	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getUserPassword() {
		return userPassword;
	}
	
	public void setUserPassword(int userPassword) {
		this.userPassword = userPassword;
	}
	
	public String getAdress() {
		return address;
	}
	
	public void setAdress(String adress) {
		this.address = adress;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getAdmin() {
		return admin;
	}
	
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	
	
}
