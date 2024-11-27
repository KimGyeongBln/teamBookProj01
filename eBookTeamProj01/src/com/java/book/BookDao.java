package com.java.book;

import java.util.List;

public interface BookDao {
	public List<BookVo> getList(); // 전체 책 목록
	public List<BookVo> search(String keyword); //@dhkim: 일부 문자로 책을 검색할 수 있는 기능
	public List<BookVo> searchPublisher(String pulisher); //@hsNa: 저자 검색
	public List	<BookVo> searchcategoryId(String category_id); //@hsNa: 카테고리 검색
	public BookVo get(int bookId);		// 리스트에서 객체 가져오기
	public boolean insert(BookVo vo); 	// 추가
	public boolean update(BookVo vo); 	// 수정
	public boolean delete(int bookId); 	// 삭제
}
