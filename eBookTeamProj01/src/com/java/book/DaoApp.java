package com.java.book;

import java.util.List;
import java.util.Scanner;

public class DaoApp {
	// 유저 기능
	private static UserDao userDAO = null;
	// 책 기능
	private static BookDao bookDAO = null;
	// 현재 접속할 유저의 객체를 저장하는 클래스
	private static UserVo currentUser = null;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		userDAO = new UserDaoImpl();
		bookDAO = new BookDaoImpl();
		
		init(sc);
	}
	
	private static void init(Scanner sc) {
		System.out.println("라이브러리를 시작합니다.");
		System.out.println("라이브러리를 시작합니다.");
		System.out.println("라이브러리를 시작합니다.");
		
		System.out.println("=========================================================");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");			
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|                                        \t\t|");
		System.out.println("|             다음 중 원하시는 기능의 번호를 선택해주세요.\t|");
		System.out.println("|             1. 기존회원 로그인\t\t\t\t|");
		System.out.println("|             2. 게스트 로그인\t\t\t\t|");
		System.out.println("|             3. 회원가입\t\t\t\t\t|");
		System.out.println("|             4. 나가기\t\t\t\t\t|");
		System.out.println("=========================================================");
		System.out.print("명령어를 입력해주세요 : ");
		int commend = sc.nextInt();
		
		switch(commend)
		{
		case 1:
			if(login(sc)) {
				mainMenu(sc);
			} else {
				System.out.println("로그인 실패. 다시 시도해주세요.");
				init(sc);
			}
			break;
		case 2:
			if(guestLogin(sc)) {
				mainMenuForGuest(sc);
			} else {
				System.out.println("게스트 로그인 실패. 다시 시도해주세요.");
				init(sc);
			}
			break;
		case 3:
			if(register(sc)) {
				System.out.println("회원가입 성공");
				init(sc);
			} else {
				System.out.println("회원가입 실패. 다시 시도해주세요.");
				init(sc);
			}
			break;
		case 4:
			exit();
			break;
		default:
			init(sc);
			break;
		}
		
//		update();
		sc.close();
	}
	
	// 로그인 기능
	private static boolean login(Scanner sc) {
		System.out.println("로그인 시도합니다.");
		boolean isSucceed = false;
		
		// 아이디 비번 입력
		System.out.print("아이디: ");
		String id = sc.next();
		System.out.print("비밀번호: ");
		String password = sc.next();
		
		// 아이디나 비번을 입력 안했을 경우 다시 입력하라고 처리
		if(!id.equals("") && !password.equals("")) {
			//유저 등록 되어있는지 여부 확인. 없으면 회원가입이 안되어있는 아이디
			if(userDAO.isUserRegistered(id)) {
				// 현재 유저 정보 받아놓음
				currentUser = userDAO.login(id, password);
				
				// 입력한 아이디 비번으로 유저를 찾으면 성공, 못찾으면 실패
				if(currentUser != null) {
					isSucceed = true;
				} else {
					System.out.println("아이디 혹은 비번이 틀렸습니다.");
					isSucceed = false;
				}
			} else {
				System.out.println("회원가입이 안되어있습니다.");
				isSucceed = false;
			}
		} else {
			System.out.println("다시 입력해 주세요.");
			isSucceed = false;
		}
		
		return isSucceed;
	}
	
	// 게스트 로그인 기능
	private static boolean guestLogin(Scanner sc) {
		System.out.println("게스트 로그인 시도합니다.");
		System.out.println("게스트 로그인 시도합니다.");
		System.out.println("게스트 로그인 시도합니다.");
		
		currentUser = userDAO.guestLogin();
		
		return currentUser == null;
	}
	
	// 회원가입 기능
	private static boolean register(Scanner sc) {
		System.out.println("회원가입 시도합니다.");
		System.out.println("회원가입 시도합니다.");
		System.out.println("회원가입 시도합니다.");
		
		System.out.println("=========================================================");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|                                        \t\t|");
		System.out.println("|             순서대로 정보를 입력해주세요.\t|");
		System.out.println("|             1. 아이디\t\t\t\t\t|");
		System.out.println("|             2. 비밀번호\t\t\t\t\t|");
		System.out.println("|             3. 이름\t\t\t\t\t|");
		System.out.println("|             4. 이메일\t\t\t\t\t|");
		System.out.println("|             5. 전화번호\t\t\t\t\t|");
//		System.out.println("|             7. 생년월일\t\t\t\t\t|");
//		System.out.println("|             8. 성별\t\t\t\t\t|");
		System.out.println("|             6. 주소\t\t\t\t\t|");
		System.out.println("|             뒤로가기 버튼은 언제든지 0을 입력하고 엔터 쳐주세요.\t\t\t\t\t|");
		System.out.println("=========================================================");
		
		System.out.print("아이디 : ");
		String userId = sc.next();
		if(userId == "0")
			return false;
		
		System.out.print("비밀번호 : ");
		String userPassword = sc.next();
		if(userPassword == "0")
			return false;
		
		System.out.print("이름 : ");
		String userName = sc.next();
		if(userName == "0")
			return false;
		
		System.out.print("이메일 : ");
		String email = sc.next();
		if(email == "0")
			return false;
		
		System.out.print("전화번호 : ");
		String phoneNumber = sc.next();
		if(phoneNumber == "0")
			return false;
		
		System.out.print("주소 : ");
		String address = sc.next();
		if(address == "0")
			return false;
		
		
		
		return true;
	}

	private static void exit() {
		System.out.println("프로그램 종료합니다.");
		System.out.println("프로그램 종료합니다.");
		System.out.println("프로그램 종료합니다.");
		
		System.out.println("=========================================================");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|                                        \t\t|");
		System.out.println("|             이용해주셔서 감사합니다.\t\t\t|");
		System.out.println("=========================================================");

		userDAO = null;
		bookDAO = null;
		currentUser = null;
	}
	
	private static void mainMenu(Scanner sc) {
		System.out.println("메인메뉴 진입");
		System.out.println("메인메뉴 진입");
		System.out.println("메인메뉴 진입");
		
		System.out.println("=========================================================");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|                                        \t\t|");
		System.out.println("|             다음 중 원하시는 기능의 번호를 선택해주세요.\t|");
		System.out.println("|             1. 검색\t\t\t\t\t|");
		System.out.println("|             2. 대여목록\t\t\t\t\t|");
		System.out.println("|             3. 반납\t\t\t\t\t|");
		System.out.println("|             4. 추천\t\t\t\t\t|");
		System.out.println("|             5. 기록\t\t\t\t\t|");
		System.out.println("|             6. 로그아웃\t\t\t\t\t|");
		System.out.println("=========================================================");
		System.out.print("명령어를 입력해주세요 : ");
		int commend = sc.nextInt();
		
		switch(commend)
		{
		case 1:
			searchMenu(sc);
			break;
		case 2:
			showRentalBookList(sc, null);
			break;
		case 3:
			if(returnRentalBook(sc)) {
				mainMenu(sc);
			} else {
				System.out.println("회원가입 실패. 다시 시도해주세요.");
			}
			break;
		case 4:
			recommandBook(sc);
			break;
		case 5:
			showRentalBookLog(sc);
			break;
		case 6:
			logout(sc);
			break;
		default:
			init(sc);
			break;
		}
	}
	
	private static void mainMenuForGuest(Scanner sc) {
		System.out.println("게스트 메인메뉴 진입");
		System.out.println("게스트 메인메뉴 진입");
		System.out.println("게스트 메인메뉴 진입");
		
		System.out.println("=========================================================");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|                                        \t\t|");
		System.out.println("|             다음 중 원하시는 기능의 번호를 선택해주세요.\t\t|");
		System.out.println("|             1. 검색\t\t\t\t\t|");
		System.out.println("|             2. 이전으로\t\t\t\t\t|");
		System.out.println("=========================================================");
		System.out.print("명령어를 입력해주세요 : ");
		int commend = sc.nextInt();
		
		switch(commend)
		{
		case 1:
			searchMenu(sc);
			break;
		case 2:
			mainMenu(sc);
			break;
		default:
			init(sc);
			break;
		}
	}
	
	private static void searchMenu(Scanner sc) {
		System.out.println("검색기능 돌아가는 중");
		System.out.println("검색기능 돌아가는 중");
		System.out.println("검색기능 돌아가는 중");
		
		System.out.println("=========================================================");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|                                        \t\t|");
		System.out.println("|             다음 중 원하시는 기능의 번호를 선택해주세요.\t\t|");
		System.out.println("|             1. 일반 검색\t\t\t\t\t|");
		System.out.println("|             2. 장르 검색\t\t\t\t\t|");
		System.out.println("|             3. 상세 검색\t\t\t\t\t|");
		System.out.println("|             4. 뒤로가기\t\t\t\t\t|");
		System.out.println("=========================================================");
		System.out.print("명령어를 입력해주세요 : ");
		
		int commend = sc.nextInt();
		
		switch(commend)
		{
		case 1:
			if(generalSearch(sc).size() > 0) {
				var bookList = generalSearch(sc);
				searchResult(sc, bookList);
				init(sc);
			} else {
				System.out.println("키워드 검색 실패. 다시 시도해주세요.");
			}
			break;
		case 2:
			if(gerneSearch(sc).size() > 0) {
				var bookList = gerneSearch(sc);
				searchResult(sc, bookList);
				init(sc);
			} else {
				System.out.println("장르 검색 실패. 다시 시도해주세요.");
			}
			break;
		case 3:
			if(detailSearch(sc).size() > 0) {
				var bookList = detailSearch(sc);
				searchResult(sc, bookList);
				init(sc);
			} else {
				System.out.println("상세 검색 실패. 다시 시도해주세요.");
			}
			break;
		case 4:
			exit();
			break;
		default:
			init(sc);
			break;
		}
	}
	
	private static List<BookVo> generalSearch(Scanner sc) {
		System.out.println("=========================================================");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("=========================================================");
		System.out.print("작가 이름 혹은 작품 제목으로 검색 : ");
		String keyword = sc.nextLine();
		
		return bookDAO.search(keyword);
	}
	
	private static List<BookVo> gerneSearch(Scanner sc) {
		System.out.println("=========================================================");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|                                        \t\t|");
		System.out.println("|             다음 중 원하시는 장르의 번호를 선택해주세요.\t\t|");
		System.out.println("|             1. 판타지\t\t\t\t\t|");
		System.out.println("|             2. 로맨스\t\t\t\t\t|");
		System.out.println("|             3. 문학\t\t\t\t\t|");
		System.out.println("|             4. 뒤로가기\t\t\t\t\t|");
		System.out.println("=========================================================");
		System.out.print("명령어를 입력해주세요 : ");
		
		int commend = sc.nextInt();
		
		switch(commend)
		{
		case 1: 
			gerne1Menu(sc);
			break;
		case  2:
			gerne2Menu(sc);
			break;
		case 3:
			gerne3Menu(sc);
			break;
		case 4:
			searchMenu(sc);
		default:
			init(sc);
			break;
		}
		
	}

	private static List<BookVo> detailSearch(Scanner sc) {
		System.out.println("=========================================================");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|                                        \t\t|");
		System.out.println("|             다음 중 원하시는 장르의 번호를 선택해주세요.\t\t|");
		System.out.println("|             1. 제목\t\t\t\t\t|");
		System.out.println("|             2. 작가\t\t\t\t\t|");
		System.out.println("|             3. 출판사\t\t\t\t\t|");
		System.out.println("|             4. 카테고리\t\t\t\t\t|");
		System.out.println("|             5. 뒤로가기\t\t\t\t\t|");
		System.out.println("=========================================================");
		System.out.print("명령어를 입력해주세요 : ");
		
		System.out.print("제목: ");
		String bookTitle = sc.next();
			if(bookTitle == "5") {
				searchMenu(sc);				
			}
		
		System.out.print("작가: ");
		String authorName = sc.next();
			if(authorName == "5") {
				searchMenu(sc);				
			}
		System.out.print("출판사: ");
		String publisher = sc.next();
			if(publisher == "5") {
				searchMenu(sc);				
			}
		System.out.print("카테고리: ");
		String categoryId = sc.next();
			if(categoryId == "5") {
				searchMenu(sc);				
			}
			
			return null;
	}
	
	private static void searchResult(Scanner sc, List<BookVo> bookList) {
		System.out.println("=========================================================");
		for(var book : bookList) {
			book.toString();
		}
		System.out.println("=========================================================");
		
		if(currentUser.getAdmin() < 3) {
			System.out.print("대여할 책 번호를 입력해주세요 : ");
			int bookNo = sc.nextInt();
			
			rentalBook(bookNo);
//			for(var book : bookList) {
//				if (book.getBookId() == bookNo) {
//					book.setIsRental(1);
//					break;
//				}
//			}
		}
	}

	private static void rentalBook(Scanner sc, List<BookVo> rentalBookList) {
		System.out.println("=========================================================");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|                                        \t\t|");
		System.out.println("|             다음 중 원하시는 기능의 번호를 선택해주세요.\t\t|");
		System.out.println("|             대여목록\t\t\t\t\t|");
//		showRentalBookList(sc, rentalBookList);
		for(var book : rentalBookList) {
			book.toString();
		}
		System.out.println("|             1. 대여하기\t\t\t\t\t|");
		System.out.println("|             2. 뒤로가기\t\t\t\t\t|");
		System.out.println("=========================================================");
		System.out.print("명령어를 입력해주세요 : ");
		
		int commend = sc.nextInt();
		
		switch(commend)
		{
		case 1:
			System.out.print("대여할 책 제목: ");
			sc.nextLine();
			String bookTitle = sc.nextLine();
//			rentedBooks.add(bookTitle);
			System.out.println(bookTitle + "책을 대여하였습니다,");
				break;
		case 2:
				mainMenu(sc);
				break;
		
		}
	}
	
	private static void showRentalBookList(Scanner sc, List<BookVo> rentalBookList) {
		System.out.println("=========================================================");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|                                        \t\t|");
		System.out.println("|             대여중인 도서 목록입니다.");
		System.out.println("=========================================================");
		for(var book : rentalBookList) {
			book.toString();
		}
		System.out.println("=========================================================");
		System.out.println("|             다음 중 원하시는 기능의 번호를 선택해주세요.\t\t|");
		System.out.println("|             1. 반납하기\t\t\t\t\t|");
		System.out.println("|             2. 뒤로가기\t\t\t\t\t|");
		System.out.println("=========================================================");
		System.out.print("명령어를 입력해주세요 : ");
		int commend = sc.nextInt();
		
		switch(commend)
		{
		case 1:
			rentalBook(sc, null);
			break;
		case 2:
			mainMenu(sc);
			break;
		}
		
	}
	
	private static boolean returnRentalBook(Scanner sc) {
		System.out.println("=========================================================");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|                                        \t\t|");
		System.out.println("|             다음 중 원하시는 기능의 번호를 선택해주세요.\t\t|");
		System.out.println("|             1. 반납할 책 제목\t\t\t\t\t|");
		System.out.println("|             2. 뒤로가기\t\t\t\t\t|");
		System.out.println("=========================================================");
		System.out.print("명령어를 입력해주세요: ");
		
		int commend = sc.nextInt();
		
		switch(commend)
		{
		case 1:
				System.out.print("반납할 책 제목: ");
				rentalBook(sc ,null);
				break;
		case 2:
				mainMenu(sc);
				break;
		default:
			System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
            returnRentalBook(sc); 
            break;
		}
		
		return true;
	}
	
	private static void recommandBook(Scanner sc) {
		System.out.println("=========================================================");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|                                        \t\t|");
		System.out.println("|             회원님께서 선택하신 추천 목록입니다.\t\t|");
		System.out.println("|                                        \t\t|");
		System.out.println("|             뒤로가기를 원하신다면 숫자 1을 입력해 주세요.\t|");
		System.out.println("=========================================================");
		System.out.print("명령어를 입력해주세요 : ");
		
		int commend = sc.nextInt();
	}
	
	private static void showRentalBookLog(Scanner sc,  List<BookVo> rentalBookList) {
		System.out.println("=========================================================");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|                                        \t\t|");
		System.out.println("|             지금까지 대여한 도서 목록입니다.\t\t\t\t\t|");
		System.out.println("=========================================================");
		 if (rentalBookList.isEmpty()) {
	            System.out.println("대여한 책이 없습니다.");
	        } else {
	            for (var book : rentalBookList) {
	                System.out.println(book);
		}
	            
	   }

		
	}
	
	
	private static void logout(Scanner sc) {
		
	}
}
