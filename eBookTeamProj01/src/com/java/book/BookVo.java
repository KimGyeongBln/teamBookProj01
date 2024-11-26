package com.java.book;

import java.util.Date;

public class BookVo {
	private int book_id;
	private String category;
	private String bookTitle;
	private int rating;
	private String authorName;
	private String publisher;
	private	Date date;
	private int genre1;
	private int genre2;
	private int genre3;
	private int isRental;
	private String imgUrl;
	private Date update;
	private String comment;
	
	BookVo() {
		
	}
	
	BookVo(String category, String bookTitle, String authorName, String publisher, int genre1, int genre2, int genre3, 
			int isRental, String imgUrl, Date update, String comment) {
		this.book_id = book_id;
		this.bookTitle = bookTitle;
		this.authorName = authorName;
		this.publisher = publisher;
		this.genre1 = genre1;
		this.genre2 = genre2;
		this.genre2 = genre2;
		this.isRental = isRental;
		this.imgUrl = imgUrl;
		this.update = update;
		this.comment = comment;
	}
	
	// Getter, Setter
	public int getBook_id() {
		return book_id;
	}
	
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getBook_title() {
		return book_title;
	}
	
	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public int getAuthor_id() {
		return author_id;
	}
	
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
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
	
	public int getIs_rental() {
		return is_rental;
	}
	
	public void setIs_rental(int is_rental) {
		this.is_rental = is_rental;
	}
	
	public String getImg_url() {
		return img_url;
	}
	
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	
	public Date getUpd_date() {
		return upd_date;
	}
	
	public void setUpd_date(Date upd_date) {
		this.upd_date = upd_date;
	}
	
	public String getComment() {
		return comment;
		
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
}
