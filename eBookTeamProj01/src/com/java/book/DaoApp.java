package com.java.book;

import java.util.List;
import java.util.Scanner;

public class DaoApp {

	// 현재 접속할 유저의 객체를 저장하는 클래스
	private static UserVo currentUser = null;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
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
			}
			break;
		case 2:
			if(guestLogin(sc)) {
				mainMenuForGuest(sc);
			} else {
				System.out.println("게스트 로그인 실패. 다시 시도해주세요.");
			}
			break;
		case 3:
			if(register(sc)) {
				System.out.println("회원가입 성공");
				init(sc);
			} else {
				System.out.println("회원가입 실패. 다시 시도해주세요.");
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
		
		// 기능 호출
		UserDao user = new UserDaoImpl();
		
		// 아이디 비번 입력
		System.out.print("아이디: ");
		String id = sc.nextLine();
		System.out.print("비밀번호: ");
		String password = sc.nextLine();
		
		// 아이디나 비번을 입력 안했을 경우 다시 입력하라고 처리
		if(!id.equals("") && !password.equals("")) {
			//유저 등록 되어있는지 여부 확인. 없으면 회원가입이 안되어있는 아이디
			if(user.isUserRegistered(id)) {
				// 현재 유저 정보 받아놓음
				currentUser = user.login(id, password);
				
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
	
	private static boolean guestLogin(Scanner sc) {
		System.out.println("게스트 로그인 시도합니다.");
		System.out.println("게스트 로그인 시도합니다.");
		System.out.println("게스트 로그인 시도합니다.");
		
		return true;
	}
	
	private static boolean register(Scanner sc) {
		System.out.println("회원가입 시도합니다.");
		System.out.println("회원가입 시도합니다.");
		System.out.println("회원가입 시도합니다.");
		
		System.out.println("=========================================================");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|                                        \t\t|");
		System.out.println("|             다음 중 원하시는 기능의 번호를 선택해주세요.\t|");
		System.out.println("|             1. 아이디\t\t\t\t\t|");
		System.out.println("|             2. 비밀번호\t\t\t\t\t|");
		System.out.println("|             3. 비밀번호 확인\t\t\t\t|");
		System.out.println("|             4. 이름\t\t\t\t\t|");
		System.out.println("|             5. 이메일\t\t\t\t\t|");
		System.out.println("|             6. 전화번호\t\t\t\t\t|");
		System.out.println("|             7. 생년월일\t\t\t\t\t|");
		System.out.println("|             8. 성별\t\t\t\t\t|");
		System.out.println("|             9. 주소\t\t\t\t\t|");
		System.out.println("=========================================================");
		System.out.print("명령어를 입력해주세요 : ");
		int commend = sc.nextInt();
		
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
		System.out.println("|             다음 중 원하시는 기능의 번호를 선택해주세요.\t|");
		System.out.println("|             1. 기존회원 로그인\t\t\t\t|");
		System.out.println("|             2. 게스트 로그인\t\t\t\t|");
		System.out.println("|             3. 회원가입\t\t\t\t\t|");
		System.out.println("|             4. 나가기\t\t\t\t\t|");
		System.out.println("=========================================================");
		System.out.print("명령어를 입력해주세요 : ");
		int commend = sc.nextInt();
		
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
			showRentalBookList(sc);
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
		
		BookDao books = new BookDaoImpl();
		
		return books.search(keyword);
	}
	
	private static List<BookVo> gerneSearch(Scanner sc) {
		
		return null;
	}

	private static List<BookVo> detailSearch(Scanner sc) {
		
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
			
			for(var book : bookList) {
				if (book.getBookId() == bookNo) {
					book.setIsRental(1);
					break;
				}
			}
		}
	}

	private static void rentalBook(Scanner sc) {
		System.out.println("=========================================================");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|                                        \t\t|");
		System.out.println("|             다음 중 원하시는 기능의 번호를 선택해주세요.\t\t|");
		System.out.println("|             1. \t\t\t\t\t|");
		System.out.println("|             2. \t\t\t\t\t|");
		System.out.println("=========================================================");
		
		int commend = sc.nextInt();
		
		switch(commend)
		{
		case 1:
		}
	}
	
	private static void showRentalBookList(Scanner sc) {
		
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
		System.out.print("반납할 책 제목 : ");
		
		int commend = sc.nextInt();
		
		switch(commend)
		{
		case 1:
				
		case 2:
				mainMenu(sc);
				break;
		}
		
		return true;
	}
	
	private static void recommandBook(Scanner sc) {
		
	}
	
	private static void showRentalBookLog(Scanner sc) {
		
	}
	
	private static void logout(Scanner sc) {
		
	}
}
