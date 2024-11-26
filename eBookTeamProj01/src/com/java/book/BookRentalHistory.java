package com.java.book;

import java.util.Date;

public class BookRentalHistory {
	private int uid;
	private int bookId;
	private Date regDate;
	
	
	public int getUid() {
		return uid;
	}
	
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	
	public int getBookId() {
		return bookId;
	}
	
	
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	
	public Date getRegDate() {
		return regDate;
	}
	
	
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
}
