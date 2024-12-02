package com.java.book;

import java.util.List;

public interface UserDao {
	public List<UserVo> getList(); // 전체 유 목록
	public boolean register(String userId, String userPassword, String userName, String address, 
								String PhoneNumber, String email, String birthday, String sex );
	public boolean isUserRegistered(String id);
	public UserVo login(String id, String password);
	public UserVo guestLogin();
	public List<UserVo> search(String keyword); //@dhkim: 일부 문자로 유저를 검색할 수 있는 기능
	public List<Integer> getMyRentalBookList(int uid);
	public List<Integer> getMyRentalBookHistory(int uid);
	public UserVo get(int userId);		// 리스트에서 객체 가져오기
	public boolean insert(UserVo vo); 	// 추가
	public boolean update(UserVo vo); 	// 수정
	public boolean delete(int userId); 	// 삭제
}
