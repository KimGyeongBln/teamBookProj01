package com.java.book;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class DaoApp {

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
		System.out.println("|             다음 중 원하시는 기능의 번호를 선택해주세요.\t\t|");
		System.out.println("|             1. 기존회원 로그인\t\t\t\t|");
		System.out.println("|             2. 게스트 로그인\t\t\t\t|");
		System.out.println("|             3. 회원가입\t\t\t\t\t|");
		System.out.println("|             4. 나가기\t\t\t\t\t|");
		System.out.println("=========================================================");
		System.out.println("명령어를 입력해주세요 : ");
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
	
	private static boolean login(Scanner sc) {
		System.out.println("로그인 시도합니다.");
		System.out.println("로그인 시도합니다.");
		System.out.println("로그인 시도합니다.");
		
		return true;
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
		
		return true;
	}
	

	private static void exit() {
		System.out.println("프로그램 종료합니다.");
		System.out.println("프로그램 종료합니다.");
		System.out.println("프로그램 종료합니다.");
		
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
		System.out.println("|             다음 중 원하시는 기능의 번호를 선택해주세요.\t\t|");
		System.out.println("|             1. 검색\t\t\t\t|");
		System.out.println("|             2. 대여목록\t\t\t\t|");
		System.out.println("|             3. 반납\t\t\t\t|");
		System.out.println("|             4. 추천\t\t\t\t|");
		System.out.println("|             5. 기록\t\t\t\t|");
		System.out.println("|             6. 로그아웃\t\t\t\t\t|");
		System.out.println("=========================================================");
		System.out.println("명령어를 입력해주세요 : ");
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
				
			} else {
				
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
		System.out.println("|             1. 검색\t\t\t\t|");
		System.out.println("|             2. 이전으로\t\t\t\t\t|");
		System.out.println("=========================================================");
		System.out.println("명령어를 입력해주세요 : ");
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
		System.out.println("|             1. 일반 검색\t\t\t\t|");
		System.out.println("|             2. 장르 검색\t\t\t\t|");
		System.out.println("|             3. 상세 검색\t\t\t\t\t|");
		System.out.println("|             4. 뒤로가기\t\t\t\t\t|");
		System.out.println("=========================================================");
		System.out.println("명령어를 입력해주세요 : ");
		
		int commend = sc.nextInt();
		
		switch(commend)
		{
		case 1:
			if(generalSearch(sc)) {
				generalSearchResult(sc);
				init(sc);
			} else {
				System.out.println("키워드 검색 실패. 다시 시도해주세요.");
			}
			break;
		case 2:
			if(gerneSearch(sc)) {
				gerneSearchResult(sc);
				init(sc);
			} else {
				System.out.println("장르 검색 실패. 다시 시도해주세요.");
			}
			break;
		case 3:
			if(detailSearch(sc)) {
				detailSearchResult(sc);
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
	
	private static void searchForGuest(Scanner sc) {
		System.out.println("책 대여 할께요.");
		System.out.println("책 대여 할께요.");
		System.out.println("책 대여 할께요.");
	}
	
	private static boolean generalSearch(Scanner sc) {
		return true;
	}
	
	private static boolean gerneSearch(Scanner sc) {
		
		return true;
	}

	private static boolean detailSearch(Scanner sc) {
		
		return true;
	}
	
	private static boolean generalSearchResult(Scanner sc) {
		return true;
	}
	
	private static boolean gerneSearchResult(Scanner sc) {
		
		return true;
	}
	
	private static boolean detailSearchResult(Scanner sc) {
		
		return true;
	}

	private static void showRentalBookList(Scanner sc) {
		
	}
	
	private static boolean returnRentalBook(Scanner sc) {
		
		return true;
	}
	
	private static void recommandBook(Scanner sc) {
		
	}
	
	private static void showRentalBookLog(Scanner sc) {
		
	}
	
	private static void logout(Scanner sc) {
		
	}
}
