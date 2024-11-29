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
	
	// sql에 연결
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
	
	// 유저 리스트 호출
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
					+ " phoneNumber, email, birthday, sex, admin"
					+ " FROM USER;";
			
			rs = stmt.executeQuery(sql);
			
			//	각 레코드를 List<AuthorVo>로 변환
			while (rs.next()) {
				int uid = rs.getInt("uid");
				String userId = rs.getString("user_id");
				String userPassword = rs.getString("user_password");
				String userName = rs.getString("user_name");
				String address = rs.getString("address");
				String phoneNumber = rs.getString("phone_number");
				String email = rs.getString("email");
				String birthday = rs.getString("birthday");
				String sex = rs.getString("sex");
				int admin = rs.getInt("admin");
				
				UserVo vo = new UserVo(uid, userId, userPassword, userName, address, phoneNumber,
						email, birthday, sex, admin);
				
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
	
	// 유저 회원가입 기능
	@Override
	public boolean register(String userId, String userPassword, String userName, 
							String address, String phoneNumber, String email, String birthday, String sex) {	
		
		UserVo user = new UserVo(userId, userPassword, userName, address, phoneNumber, email, birthday, sex, 1);
				
		return insert(user);
	}
	
	// 해당 아이디 가입 여부
	@Override
	public boolean isUserRegistered (String id) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
//			ResultSet.TYPE_SCROLL_INSENSITIVE : 양방향 이동은 가능하지만 데이터의 변경이 바로 적용되지 않는다.
//			ResultSet.CONCUR_READ_ONLY : 데이터를 읽을 수만 있다.
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = String.format("SELECT * FROM USER WHERE user_id='%s';", id);
			
			rs = stmt.executeQuery(sql);
			
			// resultSet의 마지막 데이터로 이동 
			rs.last();
			// 마지막 데이터가 몇번째 열인지 숫자를 가져온다 (이것이 곧 데이터가 몇개인지)
			int rowCount = rs.getRow();
			
			// 다시 처음으로 돌리기
			rs.beforeFirst();
			
			if(rowCount > 0) {
				return true; 
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
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
				String birthday = rs.getString("birthday");
				String sex = rs.getString("sex");
				int admin = rs.getInt("admin");
			
				UserVo vo = new UserVo(uid, userId, userPassword, userName, address, 
						phoneNumber, email, birthday, sex, admin);
				
				user = vo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
		}
		
		return user;
	}
	
	// 게스트 로그인 기능
	@Override
	public UserVo guestLogin() {
		UserVo user = null;
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			double ranValue = Math.random();
			
			String userId = String.format("guest_%d", (int)(ranValue*1000) + 1);
			String userPassword = String.format("%d", (int)(ranValue*1000000) + 1);
			String userName = "guest";
			String address = "guest";
			String phoneNumber = "guest";
			String email = "guest";
			String birthday = "birthday";
			String sex = "sex";
			int admin = 3;
			
			String sql = "INSERT INTO USER (user_id, user_password, user_name, address, phone_number, email, birthday, sex, admin) VALUES "
					+ " (?, ?, ?, ?, ?, ?, ?, ?, ?);";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPassword);
			pstmt.setString(3, userName);
			pstmt.setString(4, address);
			pstmt.setString(5, phoneNumber);
			pstmt.setString(6, email);
			pstmt.setString(7, birthday);
			pstmt.setString(8, sex);
			pstmt.setInt(9, admin);
			
			pstmt.execute();
			
			stmt = conn.createStatement();
			sql = "SELECT * FROM USER WHERE user_id='" + userId + "';";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int uid = rs.getInt("uid");
			
				UserVo vo = new UserVo(uid, userId, userPassword, userName, address, 
						phoneNumber, email, birthday, sex, admin);
				
				user = vo;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
		}
		
		return user;
	}
	
	// 유저 검색
	@Override
	public List<UserVo> search(String keyword){
		List<UserVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = "SELECT uid, user_id, user_password,"
					+ " user_name, address, phone_number, email, birthday, sex, admin "
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
				String phonNumber = rs.getString("phone_number");
				String email = rs.getString("email");
				String birthday = rs.getString("birthday");
				String sex = rs.getString("sex");
				int admin = rs.getInt("admin");
			
				UserVo vo = new UserVo(uid, userId, userPassword, userName, address, 
						phonNumber, email, birthday, sex, admin);
				
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
		
		return null;
	}
	
	// 유저가 대여한 책 목록
	@Override
	public List<Integer> getMyRentalBookList(int uid) {
		List<Integer> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = "SELECT book_id, reg_date"
					+ " FROM BOOK_RENTAL_HISTORY"
					+ " WHERE uid = ?;";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				
				//TODO:날짜도 컨테이너에 받아서 리턴하고 싶다.
				Date regDate = rs.getDate("reg_date");
			
				list.add(bookId);
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
	
	public List<Date> regDateReturn(int uid) {
		List<Date> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = "SELECT reg_date"
					+ " FROM BOOK_RENTAL_HISTORY"
					+ " WHERE uid = ?;";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				Date regDate = rs.getDate("reg_date");
			
				list.add(regDate);
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
	// 유저 정보 가져오기
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
					+ " user_name, address, phone_number, email, birthday, sex, admin "
					+ " FROM USER"
					+ " WHERE (uid)" ;
			
			rs = stmt.executeQuery(sql);
		
			while (rs.next()) {
				String userId = rs.getString("user_id");
				String userPassword = rs.getString("user_password");
				String userName = rs.getString("userName");
				String address = rs.getString("address");
				String phonNumber = rs.getString("phone_number");
				String email = rs.getString("email");
				String birthday = rs.getString("birthday");
				String sex = rs.getString("sex");
				int admin = rs.getInt("admin");
			
				UserVo vo = new UserVo(uid, userId, userPassword, userName, address, 
						phonNumber, email, birthday, sex, admin);
				
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
	
	// 유저 정보 추가
	public boolean insert(UserVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;
		
		try {
			conn = getConnection();
			
			String sql = "INSERT INTO USER (user_id, user_password, user_name, address,"
					+ " phone_number, email, birthday, sex, admin) "
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getUserId());
			pstmt.setString(2, vo.getUserPassword());
			pstmt.setString(3, vo.getUserName());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getPhoneNumber());
			pstmt.setString(6, vo.getEmail());
			pstmt.setString(7, vo.getBirthday());
			pstmt.setString(8, vo.getSex());
			pstmt.setInt(9, vo.getAdmin());

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
	
	// 유저 정보 업데이트 (TODO : 관리자 기능으로)
	public boolean update(UserVo vo) {
		
		return false;
	}
	
	// 유저 정보 삭제
	public boolean delete(int uid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;
		
		try {
			conn = getConnection();
			
			String sql = "DELETE FROM USER WHERE uid = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);

			deletedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
		}
		return 1 == deletedCount;
	}
}
	
