package com.java.book;

public class UserVo {
	private int uid;
	private String userId;
	private int userPassword;
	private String address;
	private String phoneNumber;
	private String email;
	private int admin;
	
	public UserVo() {
		
	}
	
	public UserVo(int uid, String userId, int userPassword, String address, 
			String phoneNumber, String email, int admin) {
		this.uid = uid;
		this.userId = userId;
		this.userPassword = userPassword;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.admin = admin;
	}
	
	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
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
