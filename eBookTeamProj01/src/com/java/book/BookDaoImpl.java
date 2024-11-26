package com.java.book;

import java.sql.Connection;
import java.sql.DriverManager;
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
				Date date = rs.getDate("bate");
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
	public boolean insert(BookVo vo) {}
	public boolean update(BookVo vo) {}
	public boolean delete(int bookId) {}
	
	
	
	
	
}
