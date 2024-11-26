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
				int bookId = rs.getInteger("book_id");
				String bookTitle = rs.getString("book_title");
				int rating = rs.getInteger("rating");
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
				
				BookVo vo = new BookVo(bookTitle, authorName, publisher, categoryId,  genre1, genre2, genre3, 
						isRental, price, imgUrl, update, comment);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public List<BookVo> search(String keyword){}
	public BookVo get(int bookId) {}
	
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
			pstmt.setDate(6, vo.getDate());
			pstmt.setInt(7, vo.getGenre1());
			pstmt.setString(8, vo.getCategoryId());
			pstmt.setString(9, vo.getGenre2());
			pstmt.setString(10, vo.getGenre3());
			pstmt.setString(11, vo.getIsRental());
			pstmt.setString(12, vo.getPrice());
			pstmt.setString(13, vo.getImgUrl());
			pstmt.setString(14, vo.getUpdDate());
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
	public boolean update(BookVo vo) {}
	public boolean delete(int bookId) {}
	
	
	
	
	
}
