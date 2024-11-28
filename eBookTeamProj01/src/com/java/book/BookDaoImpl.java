package com.java.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookDaoImpl implements BookDao {
	static final String dburl = "jdbc:mysql://localhost:3306/e_book";
	static final String dbuser = "testuser";
	static final String dbpass = "test";
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dburl,
												dbuser, 
												dbpass);
		} catch (ClassNotFoundException e) {
			System.err.println("드라이버 로드 실패!");
		}
		return conn;
	}
	
	@Override
	public List<BookVo> getList(){
		List<BookVo> list = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = "SELECT book_id, book_title, rating, author_name,"
					+ " publisher, date, category_id,"
					+ " genre1, genre2, genre3, is_rental, price,"
					+ " img_url, update, comment "
					+ " FROM BOOK;";
			
			rs = stmt.executeQuery(sql);
			
			//	각 레코드를 List<AuthorVo>로 변환
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getString("book_title");
				int rating = rs.getInt("rating");
				String authorName = rs.getString("author_name");
				String publisher = rs.getString("publisher");
				Date date = rs.getDate("date");
				int categoryId = rs.getInt("category_id");
				int genre1 = rs.getInt("genre1");
				int genre2 = rs.getInt("genre2");
				int genre3 = rs.getInt("genre3");
				int isRental = rs.getInt("is_rental");
				int price = rs.getInt("price");
				String imgUrl = rs.getString("img_url");
				Date update = rs.getDate("update");
				String comment = rs.getString("comment");
				
				BookVo vo = new BookVo(bookId, bookTitle, rating, authorName, publisher, date, categoryId,  genre1, genre2, genre3, 
						isRental, price, imgUrl, update, comment);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<BookVo> search(String keyword){
		List<BookVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
	
			
			String sql = "SELECT book_id, book_title, rating, author_name,"
					+ " publisher, date, category_id,"
					+ " genre1, genre2, genre3, is_rental, price,"
					+ " img_url, update, comment "
					+ " FROM BOOK"
					+ " WHERE UPPER(book_title) LIKE ? OR UPPER(author_name) LIKE ?" ;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword.toUpperCase() + "%");
			pstmt.setString(2, "%" + keyword.toUpperCase() + "%");
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getString("book_title");
				int rating = rs.getInt("rating");
				String authorName = rs.getString("author_name");
				String publisher = rs.getString("publisher");
				Date date = rs.getDate("date");
				int categoryId = rs.getInt("category_id");
				int genre1 = rs.getInt("genre1");
				int genre2 = rs.getInt("genre2");
				int genre3 = rs.getInt("genre3");
				int isRental = rs.getInt("is_rental");
				int price = rs.getInt("price");
				String imgUrl = rs.getString("img_url");
				Date update = rs.getDate("update");
				String comment = rs.getString("comment");
				
				BookVo vo = new BookVo(bookId, bookTitle, rating, authorName,
						publisher, date, categoryId, genre1, genre2, genre3,
						isRental, price, imgUrl, update, comment);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;	
	}
	
	
	public List<BookVo> searchPublisher(String pulisher){
		List<BookVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = "SELECT book_id, book_title, rating, author_name,"
					+ " publisher, date, category_id,"
					+ " genre1, genre2, genre3, is_rental, price,"
					+ " img_url, update, comment "
					+ " FROM BOOK"
					+ " WHERE UPPER(pulisher) LIKE ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + pulisher.toUpperCase() + "%");
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getString("book_title");
				int rating = rs.getInt("rating");
				String authorName = rs.getString("author_name");
				String publisher = rs.getString("publisher");
				Date date = rs.getDate("date");
				int categoryId = rs.getInt("category_id");
				int genre1 = rs.getInt("genre1");
				int genre2 = rs.getInt("genre2");
				int genre3 = rs.getInt("genre3");
				int isRental = rs.getInt("is_rental");
				int price = rs.getInt("price");
				String imgUrl = rs.getString("img_url");
				Date update = rs.getDate("update");
				String comment = rs.getString("comment");
				
				BookVo vo = new BookVo(bookId, bookTitle, rating, authorName,
						publisher, date, categoryId, genre1, genre2, genre3,
						isRental, price, imgUrl, update, comment);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List	<BookVo> searchCategoryId(String category_id){
		List<BookVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = "SELECT book_id, book_title, rating, author_name,"
					+ " publisher, date, category_id,"
					+ " genre1, genre2, genre3, is_rental, price,"
					+ " img_url, update, comment "
					+ " FROM BOOK"
					+ " WHERE UPPER(category_id) LIKE ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + category_id.toUpperCase() + "%");
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getString("book_title");
				int rating = rs.getInt("rating");
				String authorName = rs.getString("author_name");
				String publisher = rs.getString("publisher");
				Date date = rs.getDate("date");
				int categoryId = rs.getInt("category_id");
				int genre1 = rs.getInt("genre1");
				int genre2 = rs.getInt("genre2");
				int genre3 = rs.getInt("genre3");
				int isRental = rs.getInt("is_rental");
				int price = rs.getInt("price");
				String imgUrl = rs.getString("img_url");
				Date update = rs.getDate("update");
				String comment = rs.getString("comment");
				
				BookVo vo = new BookVo(bookId, bookTitle, rating, authorName,
						publisher, date, categoryId, genre1, genre2, genre3,
						isRental, price, imgUrl, update, comment);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<BookVo> searchGenre(String keyword){
		List<BookVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
	
			
			String sql = "SELECT book_id, book_title, rating, author_name,"
					+ " publisher, date, category_id,"
					+ " genre1, genre2, genre3, is_rental, price,"
					+ " img_url, update, comment "
					+ " FROM BOOK"
					+ " WHERE UPPER(genre1) LIKE ? OR UPPER(genre2) LIKE ? OR UPPER(genre3) LIKE ?" ;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword.toUpperCase() + "%");
			pstmt.setString(2, "%" + keyword.toUpperCase() + "%");
			pstmt.setString(3, "%" + keyword.toUpperCase() + "%");
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getString("book_title");
				int rating = rs.getInt("rating");
				String authorName = rs.getString("author_name");
				String publisher = rs.getString("publisher");
				Date date = rs.getDate("date");
				int categoryId = rs.getInt("category_id");
				int genre1 = rs.getInt("genre1");
				int genre2 = rs.getInt("genre2");
				int genre3 = rs.getInt("genre3");
				int isRental = rs.getInt("is_rental");
				int price = rs.getInt("price");
				String imgUrl = rs.getString("img_url");
				Date update = rs.getDate("update");
				String comment = rs.getString("comment");
				
				BookVo vo = new BookVo(bookId, bookTitle, rating, authorName,
						publisher, date, categoryId, genre1, genre2, genre3,
						isRental, price, imgUrl, update, comment);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;	
	}
	

	public List<BookVo> searchRating(int minRating){
		List<BookVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
	
			
			String sql = "SELECT book_id, book_title, rating, author_name,"
					+ " publisher, date, category_id,"
					+ " genre1, genre2, genre3, is_rental, price,"
					+ " img_url, update, comment "
					+ " FROM BOOK"
					+ " WHERE UPPER(rating) LIKE ?" ;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, minRating );
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getString("book_title");
				int rating = rs.getInt("rating");
				String authorName = rs.getString("author_name");
				String publisher = rs.getString("publisher");
				Date date = rs.getDate("date");
				int categoryId = rs.getInt("category_id");
				int genre1 = rs.getInt("genre1");
				int genre2 = rs.getInt("genre2");
				int genre3 = rs.getInt("genre3");
				int isRental = rs.getInt("is_rental");
				int price = rs.getInt("price");
				String imgUrl = rs.getString("img_url");
				Date update = rs.getDate("update");
				String comment = rs.getString("comment");
				
				BookVo vo = new BookVo(bookId, bookTitle, rating, authorName,
						publisher, date, categoryId, genre1, genre2, genre3,
						isRental, price, imgUrl, update, comment);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;	
	}
	
	public List<BookVo> searchPrice(int minPrice, int maxPrice){
		List<BookVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
	
			
			String sql = "SELECT book_id, book_title, rating, author_name,"
					+ " publisher, date, category_id,"
					+ " genre1, genre2, genre3, is_rental, price,"
					+ " img_url, update, comment "
					+ " FROM BOOK"
					+ " WHERE WHERE price BETWEEN ? AND ?" ;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, minPrice);
			pstmt.setInt(2, maxPrice);
			
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getString("book_title");
				int rating = rs.getInt("rating");
				String authorName = rs.getString("author_name");
				String publisher = rs.getString("publisher");
				Date date = rs.getDate("date");
				int categoryId = rs.getInt("category_id");
				int genre1 = rs.getInt("genre1");
				int genre2 = rs.getInt("genre2");
				int genre3 = rs.getInt("genre3");
				int isRental = rs.getInt("is_rental");
				int price = rs.getInt("price");
				String imgUrl = rs.getString("img_url");
				Date update = rs.getDate("update");
				String comment = rs.getString("comment");
				
				BookVo vo = new BookVo(bookId, bookTitle, rating, authorName,
						publisher, date, categoryId, genre1, genre2, genre3,
						isRental, price, imgUrl, update, comment);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;	
	}
	
	public List<BookVo> searchIsRental(String IsRental){
		List<BookVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
	
			
			String sql = "SELECT book_id, book_title, rating, author_name,"
					+ " publisher, date, category_id,"
					+ " genre1, genre2, genre3, is_rental, price,"
					+ " img_url, update, comment "
					+ " FROM BOOK"
					+ " WHERE UPPER(is_rental) LIKE ?" ;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + IsRental.toUpperCase() + "%");
		
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getString("book_title");
				int rating = rs.getInt("rating");
				String authorName = rs.getString("author_name");
				String publisher = rs.getString("publisher");
				Date date = rs.getDate("date");
				int categoryId = rs.getInt("category_id");
				int genre1 = rs.getInt("genre1");
				int genre2 = rs.getInt("genre2");
				int genre3 = rs.getInt("genre3");
				int isRental = rs.getInt("is_rental");
				int price = rs.getInt("price");
				String imgUrl = rs.getString("img_url");
				Date update = rs.getDate("update");
				String comment = rs.getString("comment");
				
				String rentalStatus = (isRental == 1) ? "대여 가능" : "대여 불가";
				
				BookVo vo = new BookVo(bookId, bookTitle, rating, authorName,
						publisher, date, categoryId, genre1, genre2, genre3,
						isRental, price, imgUrl, update, comment);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;	
	}
	
	
	
	public BookVo get(int bookId) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		BookVo temp = null;
		
		try {
			conn = getConnection();
			stmt          = conn.createStatement();
			
			String sql = "SELECT book_id, book_title, rating, author_name,"
					+ " publisher, date, category_id,"
					+ " genre1, genre2, genre3, is_rental, price,"
					+ " img_url, update, comment "
					+ " FROM BOOK"
					+ " WHERE UPPER(book_id)" ;
			
			rs = stmt.executeQuery(sql);
		
			while (rs.next()) {
				String bookTitle = rs.getString("book_title");
				int rating = rs.getInt("rating");
				String authorName = rs.getString("author_name");
				String publisher = rs.getString("publisher");
				Date date = rs.getDate("date");
				int categoryId = rs.getInt("category_id");
				int genre1 = rs.getInt("genre1");
				int genre2 = rs.getInt("genre2");
				int genre3 = rs.getInt("genre3");
				int isRental = rs.getInt("is_rental");
				int price = rs.getInt("price");
				String imgUrl = rs.getString("img_url");
				Date update = rs.getDate("update");
				String comment = rs.getString("comment");
			
				BookVo vo = new BookVo(bookId, bookTitle, rating, authorName, publisher, date,
						categoryId, genre1, genre2, genre3, isRental, price, imgUrl,
						update, comment);
				
				temp = vo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return temp;
	}
	
	
	public boolean insert(BookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;
		
		try {
			conn = getConnection();
			
			String sql = "SELECT book_id, book_title, rating, author_name,"
					+ " publisher, date, category_id,"
					+ " genre1, genre2, genre3, is_rental, price,"
					+ " img_url, update, comment "
					+ " FROM BOOK"
					+ " VALUES(?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getBookId());
			pstmt.setString(2, vo.getBookTitle());
			pstmt.setInt(3, vo.getRating());
			pstmt.setString(4, vo.getAuthorName());
			pstmt.setString(5, vo.getPublisher());
			pstmt.setDate(6, ((java.sql.Date)vo.getDate()));
			pstmt.setInt(7, vo.getGenre1());
			pstmt.setInt(8, vo.getCategoryId());
			pstmt.setInt(9, vo.getGenre2());
			pstmt.setInt(10, vo.getGenre3());
			pstmt.setInt(11, vo.getIsRental());
			pstmt.setInt(12, vo.getPrice());
			pstmt.setString(13, vo.getImgUrl());
			pstmt.setDate(14, ((java.sql.Date)vo.getUpdate()));
			pstmt.setString(15, vo.getComment());
			
			insertedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
		}
		return 1 == insertedCount;
	}
	public boolean update(BookVo vo) {
		
		return false;
	}
	public boolean delete(int bookId) {
		
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
