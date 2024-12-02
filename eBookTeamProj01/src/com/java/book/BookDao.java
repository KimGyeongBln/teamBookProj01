package com.java.book;

import java.util.List;

public interface BookDao {
	public List<BookVo> getList(); // 전체 책 목록
	public List<BookVo> search(String keyword); //@dhkim: 일부 문자로 책을 검색할 수 있는 기능
	public List<BookVo> searchPublisher(String pulisher); //@hsNa: 저자 검색
	public List	<BookVo> searchCategoryId(String category_id); //@hsNa: 카테고리 검색
	public List<BookVo> searchGenre(int uid); //@hsNa: 장르검색
	public List<BookVo> searchRating(int minRating); //@hsNa : 평점 검색
	public List<BookVo> searchPrice(int minPrice, int maxPrice); //@hsNa : 가격 검색
	public List<BookVo> genreSearch(int categoryId, int genreId); // 장르 검색(예진)
	public List<BookVo> recommandBook(int genreId); // 장르로 도서 추천(예진)
	public boolean rentalBook(int uid, int bookId);
	public boolean returnBook(int bookId);
	public boolean updateRental(int book_id, int isRental); //@hsNa : 책 대여 기능
	public boolean updateHistory(int uid, int book_id); //@hsNa: 책 대여 기록 기능
	public boolean deleteHistory(int uid, int book_id); //@hsNa: 책 대여 기록 기능
	public BookVo get(int bookId);		// 리스트에서 객체 가져오기
	public boolean insert(BookVo vo); 	// 추가
	public boolean update(BookVo vo); 	// 수정
	public boolean delete(int bookId); 	// 삭제
}
