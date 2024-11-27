package com.java.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
				String userPassword = rs.getString("user_password");
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
		List<UserVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = "SELECT uid, user_id, user_password,"
					+ " address, phon_number, email, admin "
					+ " FROM USER"
					+ " WHERE (user_id) LIKE ?" ;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword.toUpperCase() + "%");
			
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				int uid = rs.getInt("uid");
				String userId = rs.getString("user_id");
				String userPassword = rs.getString("user_password");
				String address = rs.getString("address");
				String phonNumber = rs.getString("phon_number");
				String email = rs.getString("email");
				int admin = rs.getInt("admin");
			
				UserVo vo = new UserVo(uid, userId, userPassword, address, 
						phonNumber, email, admin);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			}
		return null;
	}
	public UserVo get(int bookId) {
		
		return null;
	}
	
	public boolean insert(UserVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;
		
		try {
			conn = getConnection();
			
			String sql = "SELECT uid, user_id, user_password, address,"
					+ " phone_number, email, admin"
					+ " FROM USER"
					+ " VALUES(?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getUid());
			pstmt.setString(2, vo.getUserId());
			pstmt.setString(3, vo.getUserPassword());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getPhoneNumber());
			pstmt.setString(6, vo.getEmail());
			pstmt.setInt(7, vo.getAdmin());

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
	
	public boolean update(UserVo vo) {
		
		return false;
	}
	
	public boolean delete(int userId) {
		UserDao dao = new UserDaoImpl();
		boolean success = dao.delete(userId);
		
		System.out.println("USER_ID DELETE " +
				(success ? "성공": "실패"));
		
		return false;
	}
}
	
