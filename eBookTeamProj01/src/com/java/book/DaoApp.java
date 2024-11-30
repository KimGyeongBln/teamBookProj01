package com.java.book;

import java.util.ArrayList;
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

		System.out.println("=========================================================");
		System.out.println("|             		  어서오세요!\t\t\t|");			
		System.out.println("|             	   하이미디어 디지털 지식방입니다.\t\t|");
		System.out.println("|                고객님께서 원하시는 걸 선택해 주세요.\t\t|");
		System.out.println("|                                        \t\t|");
		System.out.println("|             	      1. 기 존 회 원 로그인\t\t\t|");
		System.out.println("|            	      2. 게 스 트 로 그 인\t\t\t|");
		System.out.println("|            	      3. 회   원   가  입\t\t\t|");
		System.out.println("|             	      4. 나    가     기\t\t\t|");
		System.out.println("|                                        \t\t|");
		System.out.println("|             지식방의 모든 도서는 '지식'으로 통일 됩니다!\t\t|");
		System.out.println("|                   이용에 참고 부탁드립니다.\t\t\t|");
		System.out.println("=========================================================");

		System.out.print("번호를 입력해주세요 : ");
		int commend = sc.nextInt();
		
		switch(commend)
		{
		case 1:
			if(login(sc)) {
				mainMenu(sc);
			} else {
				System.out.println("로그인에 실패했어요.");
				System.out.println("아이디와 비밀번호를 확인해 주세요.");
				init(sc);
			}
			break;
		case 2:
			if(guestLogin(sc)) {
				mainMenuForGuest(sc);
			} else {
				System.out.println("게스트 로그인에 실패했어요.");
				System.out.println("다시 시도해주세요.");
				init(sc);
			}
			break;
		case 3:
			if(register(sc)) {
				System.out.println("=========================================================");
				System.out.println("|             		  환영합니다!\t\t\t|");			
				System.out.println("|             	      앞으로 지식방의 서비스를 \t\t\t|");
				System.out.println("|             	      자유롭게 이용해 주세요!\t\t\t|");
				System.out.println("=========================================================");
				init(sc);
			} else {
				System.out.println("회원가입 실패했어요.");
				System.out.println("다시 시도해주세요.");
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
		System.out.println("다시 방문해 주셨군요!");
		System.out.println("로그인 정보를 입력해 주세요.");
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
					System.out.println("회원 정보가 틀려요.");
					System.out.println("아이디나 비밀번호를 확인해 주세요.");
					isSucceed = false;
				}
			} else {
				System.out.println("회원 정보를 찾을 수 없습니다.");
				System.out.println("기존 유저 로그인을 원하시면 회원가입 해 주세요.");
				isSucceed = false;
			}
		} else {
			System.out.println("로그인 정보를 다시 입력해 주세요.");
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

		System.out.println("=========================================================");
		System.out.println("|                  신규 가입을 환영합니다.\t\t|");	
		System.out.println("|          회원 가입을 위한 정보를 안내에 따라 입력해 주세요.\t\t|");		
		System.out.println("|        회원 가입을 취소하고 싶으시다면 '0'번을 입력해 주세요. \t|");
		System.out.println("=========================================================");

		
		System.out.println("1. 사용하실 아이디 : ");
		String userId = sc.next();
		if(userId.equals("0"))
			return false;
		
		// 아이디 중복체크
		if(userDAO.isUserRegistered(userId)) {
			System.out.println("이미 사용 중인 아이디입니다.");
			return false;
		}
		
		System.out.println("2. 사용하실 비밀번호 : ");
		String userPassword = sc.next();
		if(userPassword.equals("0"))
			return false;
		
		System.out.println("3. 이름 : ");
		String userName = sc.next();
		if(userName.equals("0"))
			return false;
		
		System.out.println("4. 이메일 : ");
		String email = sc.next();
		if(email.equals("0"))
			return false;
		
		System.out.println("5. 전화번호 : ");
		String phoneNumber = sc.next();
		if(phoneNumber.equals("0"))
			return false;
		
		System.out.println("6. 생년월일 : ");
		String birthday = sc.next();
		if(birthday.equals("0"))
			return false;
		
		System.out.println("7. 성별 : ");
		String sex = sc.next();
		if(sex.equals("0"))
			return false;
		
		System.out.println("8. 주소 : ");
		String address = sc.next();
		if(address.equals("0"))
			return false;
		
		//	0입력했을 떄 뜰 수 있게 해 주세요.
//		System.out.println("=========================================================");
//		System.out.println("|               회원 가입 없이 로그인을 하시게 되면\t\t|");	
//		System.out.println("|                  지식 검색만 가능합니다.\t\t\t|");
//		System.out.println("|          저희 지식방이 제공하는 모든 서비스를 이용하기 위해서는\t|");
//		System.out.println("|                  회원가입이 필요합니다.\t\t\t|");;		
//		System.out.println("|           생각이 바뀌시면 언제든 회원가입 해 주세요!\t\t|");
//		System.out.println("=========================================================");

		
		return userDAO.register(userId, userPassword, userName, address, phoneNumber, email, birthday, sex);
	}

	private static void exit() {
		System.out.println("프로그램 종료합니다.");
		
		System.out.println("=========================================================");
		System.out.println("|           저희 하이디미어 디지털 지식방 이용은 즐거우셨나요?\t\t|");
		System.out.println("|              지금까지 언제나 최상의 서비스를 생각하는\t\t|");
		System.out.println("|                하이미디어 디지털 지식방이었습니다.\t\t|");;		
		System.out.println("|               고객님께 도움이 되었다면 좋겠습니다.\t\t|");
		System.out.println("|                    이용해주셔서 감사합니다.\t\t\t|");
		System.out.println("=========================================================");

		userDAO = null;
		bookDAO = null;
		currentUser = null;
	}
	
	private static void mainMenu(Scanner sc) {
		System.out.println("메인메뉴 진입");


		System.out.println("=========================================================");
		System.out.println("|             	      고객님 어서오세요!\t\t\t|");	
		System.out.println("|             	  하이미디어 디지털 지식방입니다.\t\t|");		
		System.out.println("|             	      무엇을 원하실까요?\t\t\t|");	
		System.out.println("|                                        \t\t|");
		System.out.println("|            	    1. 지  식    검  색\t\t\t|");
		System.out.println("|            	    2. 대  여    목  록\t\t\t|");
		System.out.println("|            	    3. 지  식    반  납\t\t\t|");
		System.out.println("|            	    4. 추  천    지  식\t\t\t|");
		System.out.println("|            	    5. 대  여    기  록\t\t\t|");
		System.out.println("|            	    6. 로  그    아  웃\t\t\t|");
		System.out.println("=========================================================");
		System.out.print("원하시는 번호를 입력해주세요 : ");
		
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
				System.out.println("회원가입에 실패했어요. 다시 시도해주세요.");
			}
			break;
		case 4:
			recommandBook(sc);
			break;
		case 5:
			showRentalBookLog(sc, null);
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
		
		System.out.println("=========================================================");
		System.out.println("|             	      게스트님 어서오세요!\t\t\t|");	
		System.out.println("|             	  하이미디어 디지털 지식방입니다.\t\t|");		
		System.out.println("|              게스트 로그인은 도서 검색만 가능합니다.\t\t|");	
		System.out.println("|                                        \t\t|");
		System.out.println("|            	    1. 지  식    검  색\t\t\t|");
		System.out.println("|            	    2. 회  원    가  입\t\t\t|");
		System.out.println("|            	    3. 로  그    아  웃\t\t\t|");
		System.out.println("=========================================================");
		System.out.print("원하시는 번호를 입력해주세요 : ");

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

		System.out.println("=========================================================");
		System.out.println("|             	      고객님 어서오세요!\t\t\t|");	
		System.out.println("|             	  원하시는 검색을 골라주세요.\t\t\t|");		
		System.out.println("|                                        \t\t|");
		System.out.println("|  1. 일  반    검  색 (제목 또는 작가 이름으로 지식 검색)\t\t|");	
		System.out.println("|  2. 카 테 고 리 검 색  (소설 또는 만화책 구분해 지식 검색)\t|");	
		System.out.println("|  3. 상  세    검  색 (제목, 장르, 출판사 등을 입력해 지식 검색)\t|");	
		System.out.println("|  4. 메인으로 돌아가기\t\t\t\t\t|");
		System.out.println("=========================================================");
		System.out.print("원하시는 번호를 입력해주세요 : ");
		
		int commend = sc.nextInt();
		List<BookVo> bookList = null;
		
		switch(commend)
		{
		case 1:
			System.out.println("=========================================================");
			System.out.println("|             	      고객님 어서오세요!\t\t\t|");	
			System.out.println("|             찾으시는 지식의 이름이나 작가의 이름을 적어주세요.\t|");		
			System.out.println("=========================================================");
			System.out.print("작가 이름 혹은 작품 제목 : ");
			
			if(generalSearch(sc).size() > 0) {
				bookList = generalSearch(sc);
				searchResult(sc, bookList);
				mainMenu(sc);
			} else {
				System.out.println("키워드 검색에 실패했어요. 다시 입력해주세요.");
			}
			break;
		case 2:
			int categoryId = categorySearch(sc);
			
			if(genreSearch(sc, categoryId).size() > 0) {
				bookList = genreSearch(sc, categoryId);
				searchResult(sc, bookList);
				mainMenu(sc);
			} else {
				System.out.println("장르 검색에 실패했어요. 다시 입력해주세요.");
			}
			break;
		case 3:
			if(detailSearch(sc).size() > 0) {
				bookList = detailSearch(sc);
				searchResult(sc, bookList);
				mainMenu(sc);
			} else {
				System.out.println("상세 검색에 실패했어요. 다시 입력해주세요.");
			}
			break;
		case 4:
			mainMenu(sc);
			break;
		default:
			init(sc);
			break;
		}
	}
	
	private static List<BookVo> generalSearch(Scanner sc) {
		String keyword = sc.nextLine();
		
		return bookDAO.search(keyword);
	}
	

	
	private static int categorySearch(Scanner sc) {
		System.out.println("=========================================================");
		System.out.println("|             	      고객님 어서오세요!\t\t\t|");	
		System.out.println("|             	    원하시는 장르를 골라주세요.\t\t\t|");		
		System.out.println("|                                        \t\t|");
		System.out.println("|            	    1. 소           설\t\t\t|");
		System.out.println("|            	    2. 만           화\t\t\t|");
		System.out.println("|            	    3. 뒤  로    가  기\t\t\t|");	
		System.out.println("=========================================================");
		System.out.print("원하시는 번호를 입력해주세요 : ");
		
		int commend = sc.nextInt();
		if(commend == 3) {
			searchMenu(sc);
		}
		
		return commend;
	}
	
	private static List<BookVo> genreSearch(Scanner sc, int categoryId) {
		System.out.println("=========================================================");
		System.out.println("|             	      고객님 어서오세요!\t\t\t|");	
		System.out.println("|             	  원하시는 장르를 골라주세요.\t\t\t|");		
		System.out.println("|                                        \t\t|");
		System.out.println("|            	    1. 판      타     지\t\t\t|");
		System.out.println("|            	    2. 로      맨     스\t\t\t|");
		System.out.println("|            	    3. 무              협\t\t\t|");
		System.out.println("|            	    4. 추              리\t\t\t|");
		System.out.println("|            	    5. 스      릴     러\t\t\t|");
		System.out.println("|            	    6. 코      미     디\t\t\t|");
		System.out.println("|            	    7. 현대   판 타 지\t\t\t|");
		System.out.println("|            	    8. 게임   판 타 지\t\t\t|");
		System.out.println("|            	    9. 	   시	 \t\t\t|");
		System.out.println("|            	   10. 일  반    소  설\t\t\t|");
		System.out.println("|            	   11. 뒤  로    가  기\t\t\t|");
		System.out.println("=========================================================");
		System.out.print("원하시는 번호를 입력해주세요 : ");
		
		int commend = sc.nextInt();
		
		if(commend == 11) {
			categorySearch(sc);
		}
		
		return bookDAO.gerneSearch(categoryId, commend);
	}

	private static List<BookVo> detailSearch(Scanner sc) {
		System.out.println("=========================================================");
		System.out.println("|             	      고객님 어서오세요!\t\t\t|");	
		System.out.println("|               찾으시는 지식의 정보를 입력해 주세요.\t\t|");		
		System.out.println("|             뒤로가기를 원하신다면 5번을 입력해 주세요.\t\t|");	
		System.out.println("=========================================================");
		
		System.out.print("제목: ");
		String bookTitle = sc.next();
		
		if(bookTitle.equals("5")) {
			searchMenu(sc);				
		}
		
		System.out.print("작가: ");
		String authorName = sc.next();
		
		if(authorName.equals("5")) {
			searchMenu(sc);				
		}
		System.out.print("출판사: ");
		String publisher = sc.next();
		
		if(publisher.equals("5")) {
			searchMenu(sc);				
		}
		System.out.print("카테고리 (소설: 1, 만화: 2): ");
		int categoryId = sc.nextInt();
		
		if(categoryId == 5) {
			searchMenu(sc);				
		}
			
		return null;
	}
	
	private static void searchResult(Scanner sc, List<BookVo> bookList) {
		System.out.println("=========================================================");
		for(var book : bookList) {
			System.out.println(book.toString());
		}
//		System.out.println(bookList.size());
		System.out.println("=========================================================");
		
		if(currentUser.getAdmin() < 3) {
			System.out.print("대여할 지식의 번호를 입력해주세요 : ");
			int bookNo = sc.nextInt();
			BookVo book = bookDAO.get(bookNo);
			
			List<BookVo> container = new ArrayList<BookVo>();
			
			// 내가 빌린 책 아이디 리스트
			List<Integer> bookIdList = userDAO.getMyRentalBookList(currentUser.getUid());
			
			for(var bookId : bookIdList) {
				var bookTemp = bookDAO.get(bookId);
				
				container.add(bookTemp);
			}
			
			rentalBook(sc, book, container);
		}
	}

	private static void rentalBook(Scanner sc, BookVo book, List<BookVo> rentalBookList) {
		System.out.println("=========================================================");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|             월컴 투 하이미디어 도서관 프로젝트!\t\t|");
		System.out.println("|                                        \t\t|");
		System.out.println("|             다음 중 원하시는 기능의 번호를 선택해주세요.\t\t|");
		System.out.println("|             대여목록\t\t\t\t\t|");
		for(var rentalBook : rentalBookList) {
			System.out.println(rentalBook.toString());
		}
		System.out.println("|             1. 대여하기\t\t\t\t\t|");
		System.out.println("|             2. 뒤로가기\t\t\t\t\t|");
		System.out.println("=========================================================");
		System.out.print("명령어를 입력해주세요 : ");
		
		int commend = sc.nextInt();
		
		switch(commend)
		{
		case 1:
			System.out.print("대여하실 지식의 지식은 저에게 알려주세요!");
			sc.nextLine();
			String bookTitle = sc.nextLine();
//			rentedBooks.add(bookTitle);
			System.out.println(bookTitle + "지식이 추가 되었습니다!");
			break;
		case 2:
			mainMenu(sc);
			break;
		
		}
	}
	
	private static void showRentalBookList(Scanner sc, List<BookVo> rentalBookList) {
		System.out.println("=========================================================");
		System.out.println("|             쌓여가는 내 지식은 뭐가 있을까요?\t\t\t|");
		System.out.println("|             현재 회원님의 지식 게이지가 궁금합니다!\t\t|");
		System.out.println("|                                        \t\t|");
		// 대여중인 리스트 System.out.println()
		System.out.println("|             뒤로가기는 언제나 0번을 불러주세요!\t\t|");
		System.out.println("=========================================================");
		System.out.print("명령어를 입력해주세요 : ");
		
		int commend = sc.nextInt();
		
		if(rentalBookList != null) { 
			for(var rentalBook : rentalBookList) {
				System.out.println(rentalBook.toString());
			}
		}
		switch(commend)
		{
		case 0:
			mainMenu(sc);
			break;
		}
		
	}
	
	private static boolean returnRentalBook(Scanner sc) {
		System.out.println("=========================================================");
		System.out.println("|             읽다보니 벌써 반납시간이?\t\t\t|");
		System.out.println("|             저희 지식방은 언제나 회원님께 열려있습니다!\t\t|");
		System.out.println("|                                        \t\t|");
		System.out.println("|             1번을 누르신 후 반납 해 주실 지식을 알려주세요!\t|");
		System.out.println("|             뒤로가기는 언제나 0번을 불러주세요!\t\t|");
		System.out.println("=========================================================");
		System.out.print("명령어를 입력해주세요: ");
		
		int commend = sc.nextInt();
		
		switch(commend)
		{
		case 1:
			System.out.print(" 반납 해 주실 지식은 저에게 알려주세요!");
//			리스트 나와야한다( 함수 하나 만들기)
			break;
		case 2:
			mainMenu(sc);
			break;
		default:
			System.out.println("알려주신 지식을 한번 더 확인 후 알려주세요!");

            returnRentalBook(sc); 
            break;
		}
		
		return true;
	}
	
	private static void recommandBook(Scanner sc) {
		System.out.println("=========================================================");
		System.out.println("|             이런 지식은 어떨까요?\t\t\t\t|");
		System.out.println("|             회원님께 추천 드리고싶은 지식의 목록입니다!\t\t|");
		System.out.println("|                                        \t\t|");
		// 추천
		System.out.println("|             뒤로가기는 언제나 0번을 불러주세요!\t\t|");
		System.out.println("=========================================================");
		System.out.print("명령어를 입력해주세요 : ");
		
		int commend = sc.nextInt();
	}
	
	private static void showRentalBookLog(Scanner sc,  List<BookVo> rentalBookList) {
		System.out.println("=========================================================");
		System.out.println("|             플러스 된 지식들은 재미있으셨나요?\t\t|");
		System.out.println("|             플러스 된 회원님의 지식을 알려드립니다!\t\t|");
		System.out.println("|                                        \t\t|");
		// 렌탈리스트
		
		System.out.println("|             뒤로가기는 언제나 0번을 불러주세요!\t\t|");
		System.out.println("=========================================================");
		 if (rentalBookList == null || rentalBookList.isEmpty()) {
			 System.out.println("플러스 된 지식이 아직 없습니다 지식을 플러스 해 보세요!");
	     } else {
	    	 for (var book : rentalBookList) {
	    		 System.out.println(book);
	    	 }    
	     }
	}
	
	
	private static void logout(Scanner sc) {
		currentUser = null;
		init(sc);
	
	}
}
