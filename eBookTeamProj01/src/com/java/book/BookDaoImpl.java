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
			
			String sql = "SELECT book_id, book_title, rating, author,"
					+ " publisher, publish_date, category_id,"
					+ " genre1, genre2, genre3, is_rental, price,"
					+ " img_url, upd_date, comment, rent_cnt "
					+ " FROM BOOK;";
			
			rs = stmt.executeQuery(sql);
			
			//	각 레코드를 List<AuthorVo>로 변환
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getString("book_title");
				int rating = rs.getInt("rating");
				String authorName = rs.getString("author");
				String publisher = rs.getString("publisher");
				Date date = rs.getDate("publish_date");
				int categoryId = rs.getInt("category_id");
				int genre1 = rs.getInt("genre1");
				int genre2 = rs.getInt("genre2");
				int genre3 = rs.getInt("genre3");
				int isRental = rs.getInt("is_rental");
				int price = rs.getInt("price");
				String imgUrl = rs.getString("img_url");
				Date update = rs.getDate("upd_date");
				String comment = rs.getString("comment");
				int rent_cnt = rs.getInt("rent_cnt");
				
				BookVo vo = new BookVo(bookId, bookTitle, rating, authorName, publisher, 
						date, categoryId,  genre1, genre2, genre3, 
						isRental, price, imgUrl, update, comment, rent_cnt);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
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
	
			
			String sql = "SELECT book_id, book_title, rating, author,"
					+ " publisher, publish_date, category_id,"
					+ " genre1, genre2, genre3, is_rental, price,"
					+ " img_url, upd_date, comment, rent_cnt "
					+ " FROM BOOK"
					+ " WHERE UPPER(book_title) LIKE ? OR UPPER(author) LIKE ?" ;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword.toUpperCase() + "%");
			pstmt.setString(2, "%" + keyword.toUpperCase() + "%");
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getString("book_title");
				int rating = rs.getInt("rating");
				String authorName = rs.getString("author");
				String publisher = rs.getString("publisher");
				Date date = rs.getDate("publish_date");
				int categoryId = rs.getInt("category_id");
				int genre1 = rs.getInt("genre1");
				int genre2 = rs.getInt("genre2");
				int genre3 = rs.getInt("genre3");
				int isRental = rs.getInt("is_rental");
				int price = rs.getInt("price");
				String imgUrl = rs.getString("img_url");
				Date update = rs.getDate("upd_date");
				String comment = rs.getString("comment");
				int rent_cnt = rs.getInt("rent_cnt");
				
				BookVo vo = new BookVo(bookId, bookTitle, rating, authorName, publisher, 
						date, categoryId,  genre1, genre2, genre3, 
						isRental, price, imgUrl, update, comment, rent_cnt);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
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
			
			String sql = "SELECT book_id, book_title, rating, author,"
					+ " publisher, publish_date, category_id,"
					+ " genre1, genre2, genre3, is_rental, price,"
					+ " img_url, upd_date, comment, rent_cnt "
					+ " FROM BOOK"
					+ " WHERE pulisher = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + pulisher.toUpperCase() + "%");
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getString("book_title");
				int rating = rs.getInt("rating");
				String authorName = rs.getString("author");
				String publisher = rs.getString("publisher");
				Date date = rs.getDate("publish_date");
				int categoryId = rs.getInt("category_id");
				int genre1 = rs.getInt("genre1");
				int genre2 = rs.getInt("genre2");
				int genre3 = rs.getInt("genre3");
				int isRental = rs.getInt("is_rental");
				int price = rs.getInt("price");
				String imgUrl = rs.getString("img_url");
				Date update = rs.getDate("upd_date");
				String comment = rs.getString("comment");
				int rent_cnt = rs.getInt("rent_cnt");
				
				BookVo vo = new BookVo(bookId, bookTitle, rating, authorName, publisher, 
						date, categoryId,  genre1, genre2, genre3, 
						isRental, price, imgUrl, update, comment, rent_cnt);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
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
			
			String sql = "SELECT book_id, book_title, rating, author,"
					+ " publisher, publish_date, category_id,"
					+ " genre1, genre2, genre3, is_rental, price,"
					+ " img_url, upd_date, comment, rent_cnt "
					+ " FROM BOOK"
					+ " WHERE UPPER(category_id) LIKE ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + category_id.toUpperCase() + "%");
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getString("book_title");
				int rating = rs.getInt("rating");
				String authorName = rs.getString("author");
				String publisher = rs.getString("publisher");
				Date date = rs.getDate("publish_date");
				int categoryId = rs.getInt("category_id");
				int genre1 = rs.getInt("genre1");
				int genre2 = rs.getInt("genre2");
				int genre3 = rs.getInt("genre3");
				int isRental = rs.getInt("is_rental");
				int price = rs.getInt("price");
				String imgUrl = rs.getString("img_url");
				Date update = rs.getDate("upd_date");
				String comment = rs.getString("comment");
				int rent_cnt = rs.getInt("rent_cnt");
				
				BookVo vo = new BookVo(bookId, bookTitle, rating, authorName, publisher, 
						date, categoryId,  genre1, genre2, genre3, 
						isRental, price, imgUrl, update, comment, rent_cnt);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
		}
		
		return list;
	}
	
	public List<BookVo> searchGenre(int genreId){
		List<BookVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
	
			
			String sql = "SELECT book_id, book_title, rating, author,"
					+ " publisher, publish_date, category_id,"
					+ " genre1, genre2, genre3, is_rental, price,"
					+ " img_url, upd_date, comment, rent_cnt "
					+ " FROM BOOK"
					+ " WHERE genre1 = ?" ;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, genreId);
//			pstmt.setString(2, "%" + keyword.toUpperCase() + "%");
//			pstmt.setString(3, "%" + keyword.toUpperCase() + "%");
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getString("book_title");
				int rating = rs.getInt("rating");
				String authorName = rs.getString("author");
				String publisher = rs.getString("publisher");
				Date date = rs.getDate("publish_date");
				int categoryId = rs.getInt("category_id");
				int genre1 = rs.getInt("genre1");
				int genre2 = rs.getInt("genre2");
				int genre3 = rs.getInt("genre3");
				int isRental = rs.getInt("is_rental");
				int price = rs.getInt("price");
				String imgUrl = rs.getString("img_url");
				Date update = rs.getDate("upd_date");
				String comment = rs.getString("comment");
				int rent_cnt = rs.getInt("rent_cnt");
				
				BookVo vo = new BookVo(bookId, bookTitle, rating, authorName, publisher, 
						date, categoryId,  genre1, genre2, genre3, 
						isRental, price, imgUrl, update, comment, rent_cnt);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
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
	
			
			String sql = "SELECT book_id, book_title, rating, author,"
					+ " publisher, publish_date, category_id,"
					+ " genre1, genre2, genre3, is_rental, price,"
					+ " img_url, upd_date, comment, rent_cnt "
					+ " FROM BOOK"
					+ " WHERE UPPER(rating) LIKE ?" ;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, minRating );
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getString("book_title");
				int rating = rs.getInt("rating");
				String authorName = rs.getString("author");
				String publisher = rs.getString("publisher");
				Date date = rs.getDate("publish_date");
				int categoryId = rs.getInt("category_id");
				int genre1 = rs.getInt("genre1");
				int genre2 = rs.getInt("genre2");
				int genre3 = rs.getInt("genre3");
				int isRental = rs.getInt("is_rental");
				int price = rs.getInt("price");
				String imgUrl = rs.getString("img_url");
				Date update = rs.getDate("upd_date");
				String comment = rs.getString("comment");
				int rent_cnt = rs.getInt("rent_cnt");
				
				BookVo vo = new BookVo(bookId, bookTitle, rating, authorName, publisher, 
						date, categoryId,  genre1, genre2, genre3, 
						isRental, price, imgUrl, update, comment, rent_cnt);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
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
	
			
			String sql = "SELECT book_id, book_title, rating, author,"
					+ " publisher, publish_date, category_id,"
					+ " genre1, genre2, genre3, is_rental, price,"
					+ " img_url, upd_date, comment, rent_cnt "
					+ " FROM BOOK"
					+ " WHERE price BETWEEN ? AND ?" ;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, minPrice);
			pstmt.setInt(2, maxPrice);
			
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getString("book_title");
				int rating = rs.getInt("rating");
				String authorName = rs.getString("author");
				String publisher = rs.getString("publisher");
				Date date = rs.getDate("publish_date");
				int categoryId = rs.getInt("category_id");
				int genre1 = rs.getInt("genre1");
				int genre2 = rs.getInt("genre2");
				int genre3 = rs.getInt("genre3");
				int isRental = rs.getInt("is_rental");
				int price = rs.getInt("price");
				String imgUrl = rs.getString("img_url");
				Date update = rs.getDate("upd_date");
				String comment = rs.getString("comment");
				int rent_cnt = rs.getInt("rent_cnt");
				
				BookVo vo = new BookVo(bookId, bookTitle, rating, authorName, publisher, 
						date, categoryId,  genre1, genre2, genre3, 
						isRental, price, imgUrl, update, comment, rent_cnt);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
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
	
			
			String sql = "SELECT book_id, book_title, rating, author,"
					+ " publisher, publish_date, category_id,"
					+ " genre1, genre2, genre3, is_rental, price,"
					+ " img_url, upd_date, comment, rent_cnt "
					+ " FROM BOOK"
					+ " WHERE is_rental = ?" ;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + IsRental.toUpperCase() + "%");
		
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getString("book_title");
				int rating = rs.getInt("rating");
				String authorName = rs.getString("author");
				String publisher = rs.getString("publisher");
				Date date = rs.getDate("publish_date");
				int categoryId = rs.getInt("category_id");
				int genre1 = rs.getInt("genre1");
				int genre2 = rs.getInt("genre2");
				int genre3 = rs.getInt("genre3");
				int isRental = rs.getInt("is_rental");
				int price = rs.getInt("price");
				String imgUrl = rs.getString("img_url");
				Date update = rs.getDate("upd_date");
				String comment = rs.getString("comment");
				int rent_cnt = rs.getInt("rent_cnt");
				
				BookVo vo = new BookVo(bookId, bookTitle, rating, authorName, publisher, 
						date, categoryId,  genre1, genre2, genre3, 
						isRental, price, imgUrl, update, comment, rent_cnt);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
		}
		
		return list;	
	}
	
	public List<BookVo> gerneSearch(int categoryId, int genreId) {
		List<BookVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = "SELECT book_id, book_title, rating, author_name,"
					+ " publisher, date, category_id,"
					+ " genre1, genre2, genre3, is_rental, price,"
					+ " img_url, upd_date, comment, rent_cnt "
					+ " FROM BOOK "
					+ " WHERE category_id = ? AND genre1 = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryId);
			pstmt.setInt(2, genreId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getString("book_title");
				int rating = rs.getInt("rating");
				String authorName = rs.getString("author_name");
				String publisher = rs.getString("publisher");
				Date date = rs.getDate("date");
				int cateId = rs.getInt("category_id");
				int genre1 = rs.getInt("genre1");
				int genre2 = rs.getInt("genre2");
				int genre3 = rs.getInt("genre3");
				int isRental = rs.getInt("is_rental");
				int price = rs.getInt("price");
				String imgUrl = rs.getString("img_url");
				Date update = rs.getDate("upd_date");
				String comment = rs.getString("comment");
				int rent_cnt = rs.getInt("rent_cnt");
				
				BookVo vo = new BookVo(bookId, bookTitle, rating, authorName, publisher, 
						date, cateId,  genre1, genre2, genre3, 
						isRental, price, imgUrl, update, comment, rent_cnt);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
		}
		
		return list;	
	}

	public String findBookNameFromId(int bookId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String bookTitle = null;
		
		try {
			conn = getConnection();

			String sql = "SELECT book_title "
					+ " FROM BOOK "
					+ " WHERE book_id = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				//temporary : 임시
				String temp = rs.getString("book_title");
				
				bookTitle = temp;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
		}
		
		return bookTitle;
	}
	
	public BookVo get(int bookId) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		BookVo temp = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = "SELECT book_id, book_title, rating, author,"
					+ " publisher, publish_date, category_id,"
					+ " genre1, genre2, genre3, is_rental, price,"
					+ " img_url, upd_date, comment, rent_cnt "
					+ " FROM BOOK"
					+ " WHERE UPPER(book_id)" ;
			
			rs = stmt.executeQuery(sql);
		
			while (rs.next()) {
				int bId = rs.getInt("book_id");  // bId = bookId
				String bookTitle = rs.getString("book_title");
				int rating = rs.getInt("rating");
				String authorName = rs.getString("author");
				String publisher = rs.getString("publisher");
				Date date = rs.getDate("publish_date");
				int categoryId = rs.getInt("category_id");
				int genre1 = rs.getInt("genre1");
				int genre2 = rs.getInt("genre2");
				int genre3 = rs.getInt("genre3");
				int isRental = rs.getInt("is_rental");
				int price = rs.getInt("price");
				String imgUrl = rs.getString("img_url");
				Date update = rs.getDate("upd_date");
				String comment = rs.getString("comment");
				int rent_cnt = rs.getInt("rent_cnt");
				
				BookVo vo = new BookVo(bId, bookTitle, rating, authorName, publisher, 
						date, categoryId,  genre1, genre2, genre3, 
						isRental, price, imgUrl, update, comment, rent_cnt);
				
				temp = vo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
		}
		
		return temp;
	}
	
	@Override
	public boolean updateRental(int bookId, int isRental) {
	    try (Connection conn = getConnection(); 
	         Statement stmt = conn.createStatement()) {

	       
	        String sql = "UPDATE BOOK SET is_rental = " + isRental 
	                   + " WHERE book_id = " + bookId;

	        return stmt.executeUpdate(sql) > 0;  // 영향을 받은 행 수가 1 이상이면 true, 아니면 false

	    } catch (SQLException e) {
	        e.printStackTrace();
	      
	    }
	    
	    return false;
	}
	
	public boolean updateHistory(int uid, int book_id, int reg_date) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;
		
		try {
			conn = getConnection();
			
			String sql = "INSERT INTO BOOK_RENTAL_HISTORY (uid, book_id, reg_date) VALUES (?, ?, ?)";
			
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, uid);
		pstmt.setInt(2, book_id);
		pstmt.setInt(3, reg_date);
		
		insertedCount = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return insertedCount > 0 ;
	}
	
	public boolean insert(BookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;
		
		try {
			conn = getConnection();
			
			String sql = "SELECT book_id, book_title, rating, author,"
					+ " publisher, publish_date, category_id,"
					+ " genre1, genre2, genre3, is_rental, price,"
					+ " img_url, upd_date, comment "
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
