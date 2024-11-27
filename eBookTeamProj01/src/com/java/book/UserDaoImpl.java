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

public class UserDaoImpl implements UserDao {
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
	public List<UserVo> getList(){
		List<UserVo> list = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = "SELECT uid, user_id, user_password, address,"
					+ " phoneNumber, email, admin"
					+ " FROM USER;";
			
			rs = stmt.executeQuery(sql);
			
			//	각 레코드를 List<AuthorVo>로 변환
			while (rs.next()) {
				int uid = rs.getInt("uid");
				String userId = rs.getString("user_id");
				int userPassword = rs.getInt("user_password");
				String address = rs.getString("address");
				String phoneNumber = rs.getString("phoneNumber");
				String email = rs.getString("email");
				int admin = rs.getInt("admin");
				
				UserVo vo = new UserVo(uid, userId, userPassword, address, phoneNumber,
						email, admin);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public List<UserVo> search(String keyword){
		
		return null;
	}
	public UserVo get(int bookId) {
		
		return null;
	}
	
	public boolean insert(UserVo vo) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		int insertedCount = 0;
//		
//		try {
//			conn = getConnection();
//			
//			String sql = "SELECT uid, user_id, rating, author_name,"
//					+ " publisher, date, category_id,"
//					+ " genre1, genre2, genre3, is_rental, price,"
//					+ " img_url, update, comment "
//					+ " FROM BOOK"
//					+ " VALUES(?, ?)";
//			pstmt = conn.prepareStatement(sql);
//			
////			pstmt.setInt(1, vo.getBookId());
////			pstmt.setString(2, vo.getBookTitle());
////			pstmt.setInt(3, vo.getRating());
////			pstmt.setString(4, vo.getAuthorName());
////			pstmt.setString(5, vo.getPublisher());
////			pstmt.setDate(6, ((java.sql.Date)vo.getDate()));
////			pstmt.setInt(7, vo.getGenre1());
////			pstmt.setInt(8, vo.getCategoryId());
////			pstmt.setInt(9, vo.getGenre2());
////			pstmt.setInt(10, vo.getGenre3());
////			pstmt.setInt(11, vo.getIsRental());
////			pstmt.setInt(12, vo.getPrice());
////			pstmt.setString(13, vo.getImgUrl());
////			pstmt.setDate(14, ((java.sql.Date)vo.getUpdDate()));
////			pstmt.setString(15, vo.getComment());
//			
//			insertedCount = pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (pstmt != null) pstmt.close();
//				if (conn != null) conn.close();
//			} catch (Exception e) {}
//		}
//		return 1 == insertedCount;
		
		return false;
	}
	public boolean update(UserVo vo) {
		
		return false;
	}
	public boolean delete(int bookId) {
}
