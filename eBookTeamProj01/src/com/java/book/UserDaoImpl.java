package com.java.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
			
			String sql = "SELECT uid, user_id, user_password, user_name, address,"
					+ " phoneNumber, email, admin"
					+ " FROM USER;";
			
			rs = stmt.executeQuery(sql);
			
			//	각 레코드를 List<AuthorVo>로 변환
			while (rs.next()) {
				int uid = rs.getInt("uid");
				String userId = rs.getString("user_id");
				String userPassword = rs.getString("user_password");
				String userName = rs.getString("userName");
				String address = rs.getString("address");
				String phoneNumber = rs.getString("phoneNumber");
				String email = rs.getString("email");
				int admin = rs.getInt("admin");
				
				UserVo vo = new UserVo(uid, userId, userPassword, userName, address, phoneNumber,
						email, admin);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	// 유저 회원가입 기능
	@Override
	public boolean register(String userId, String userPassword, String userName, String address, String PhoneNumber, String email) {
		
		
		return false;
	}
	
	// 해당 아이디 가입 여부
	@Override
	public boolean isUserRegistered (String id) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = String.format("SELECT * FROM USER WHERE user_id='%s';", id);
			
			rs = stmt.executeQuery(sql);
			
			rs.last();
			int rowCount = rs.getRow();
			rs.beforeFirst();
			
			if(rowCount > 0) {
				return true; 
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	// 로그인 기능
	@Override
	public UserVo login(String id, String password) {
		UserVo user = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "SELECT * FROM USER WHERE user_id = ? AND user_password = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int uid = rs.getInt("uid");
				String userId = rs.getString("user_id");
				String userPassword = rs.getString("user_password");
				String userName = rs.getString("user_name");
				String address = rs.getString("address");
				String phoneNumber = rs.getString("phone_number");
				String email = rs.getString("email");
				int admin = rs.getInt("admin");
			
				UserVo vo = new UserVo(uid, userId, userPassword, userName, address, 
						phoneNumber, email, admin);
				
				user = vo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	// 게스트 로그인 기능
	@Override
	public UserVo guestLogin() {
		UserVo user = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();

			double ranValue = Math.random();
			
			String userId = String.format("guest_%d", (ranValue*1000) + 1);
			String userPassword = String.format("%d", (ranValue*1000000) + 1);
			String userName = "guest";
			String address = "";
			String phoneNumber = "";
			String email = "";
			
			String sql = String.format("INSERT INTO USER (user_id, user_password, userName, address, phone_number, email, admin) VALUES (%s, %s);", 
									userId, userPassword, userName, address, phoneNumber, email, 3);
			
			rs = stmt.executeQuery(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	@Override
	public List<UserVo> search(String keyword){
		List<UserVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = "SELECT uid, user_id, user_password,"
					+ " user_name, address, phon_number, email, admin "
					+ " FROM USER"
					+ " WHERE (user_id) LIKE ?" ;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword.toUpperCase() + "%");
			
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				int uid = rs.getInt("uid");
				String userId = rs.getString("user_id");
				String userPassword = rs.getString("user_password");
				String userName = rs.getString("userName");
				String address = rs.getString("address");
				String phonNumber = rs.getString("phon_number");
				String email = rs.getString("email");
				int admin = rs.getInt("admin");
			
				UserVo vo = new UserVo(uid, userId, userPassword, userName, address, 
						phonNumber, email, admin);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			}
		return null;
	}
	
	@Override
	public UserVo get(int uid) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		UserVo temp = null;
		
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = "SELECT uid, user_id, user_password,"
					+ " user_name, address, phon_number, email, admin "
					+ " FROM USER"
					+ " WHERE (uid)" ;
			
			rs = stmt.executeQuery(sql);
		
			while (rs.next()) {
				String userId = rs.getString("user_id");
				String userPassword = rs.getString("user_password");
				String userName = rs.getString("userName");
				String address = rs.getString("address");
				String phonNumber = rs.getString("phon_number");
				String email = rs.getString("email");
				int admin = rs.getInt("admin");
			
				UserVo vo = new UserVo(uid, userId, userPassword, userName, address, 
						phonNumber, email, admin);
				
				temp = vo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return temp;
	}
	
	public boolean insert(UserVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;
		
		try {
			conn = getConnection();
			
			String sql = "SELECT uid, user_id, user_password, user_name, address,"
					+ " phone_number, email, admin"
					+ " FROM USER"
					+ " VALUES(?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getUid());
			pstmt.setString(2, vo.getUserId());
			pstmt.setString(3, vo.getUserPassword());
			pstmt.setString(4, vo.getUserName());
			pstmt.setString(5, vo.getAddress());
			pstmt.setString(6, vo.getPhoneNumber());
			pstmt.setString(7, vo.getEmail());
			pstmt.setInt(8, vo.getAdmin());

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
	
