package com.java.book;

import java.util.Date;

public class BookVo {
	private int bookId;
	private String bookTitle;
	private int rating;
	private String authorName;
	private String publisher;
	private	Date date;
	private int categoryId;
	private int genre1;
	private int genre2;
	private int genre3;
	private int isRental;
	private int price;
	private String imgUrl;
	private Date update;
	private String comment;
	
	BookVo() {
		
	}
	
	BookVo(int bookId, String bookTitle, int rating, String authorName, String publisher, Date date, int categoryId, int genre1, int genre2, int genre3, 
			int isRental, int price, String imgUrl, Date update, String comment) {
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.rating = rating;
		this.authorName = authorName;
		this.publisher = publisher;
		this.categoryId = categoryId;
		this.genre1 = genre1;
		this.genre2 = genre2;
		this.genre2 = genre2;
		this.isRental = isRental;
		this.price = price;
		this.imgUrl = imgUrl;
		this.update = update;
		this.comment = comment;
	}
	
	// Getter, Setter
	public int getBookId() {
		return bookId;
	}
	
	public void setBookId(int book_id) {
		this.bookId = book_id;
	}
	

	public String getBookTitle() {
		return bookTitle;
	}
	
	public void setBookTitle(String book_title) {
		this.bookTitle = book_title;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public String getAuthorName() {
		return authorName;
	}
	
	public void setAuthorId(String authorName) {
		this.authorName = authorName;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getGenre1() {
		return genre1;
	}
	
	public void setCategory(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	
	public void setGenre1(int genre1) {
		this.genre1 = genre1;
	}
	
	public int getGenre2() {
		return genre2;
	}
	
	public void setGenre2(int genre2) {
		this.genre2 = genre2;
	}
	
	public int getGenre3() {
		return genre3;
	}
	
	public void setGenre3(int genre3) {
		this.genre3 = genre3;
	}
	
	public int getIsRental() {
		return isRental;
	}
	
	public void setIsRental(int isRental) {
		this.isRental = isRental;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public Date getUpdate() {
		return update;
	}
	
	public void setUpdate(Date update) {
		this.update = update;
	}
	
	public String getComment() {
		return comment;
		
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
}
