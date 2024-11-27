package com.java.book;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class DaoApp {

	public static void main(String[] args) {
		listBook();
		insertBook();
	}

	private static void listBook() {
		BookDao dao = new BookDaoImpl();
		
		List<BookVo> list = dao.getList();
		Iterator<BookVo> iter = list.iterator();
		
		System.out.println("  ");
		
		while (iter.hasNext()) {
			BookVo vo = iter.next();
			System.out.println(vo);
		}
		System.out.println("  ");
	}
	
	private static void insertBook() {
		Scanner scanner = new Scanner(System.in);
		System.out.print(" ");
		String text = scanner.nextLine();
		
		String bookId = scanner.nextLine();
		System.out.print(" ");
		String bookTitle = scanner.nextLine();
		System.out.print(" ");
		String rating = scanner.nextLine();
		System.out.print(" ");
		String authorName = scanner.nextLine();
		System.out.print(" ");
		String publisher = scanner.nextLine();
		System.out.print(" ");
		String date = scanner.nextLine();
		System.out.print(" ");
		String categoryId = scanner.nextLine();
		System.out.print(" ");
		String genre1 = scanner.nextLine();
		System.out.print(" ");
		String genre2 = scanner.nextLine();
		System.out.print(" ");
		String genre3 = scanner.nextLine();
		System.out.print(" ");
		String isRental = scanner.nextLine();
		System.out.print(" ");
		String price = scanner.nextLine();
		System.out.print(" ");
		String imgUrl = scanner.nextLine();
		System.out.print(" ");
		String update = scanner.nextLine();
		System.out.print(" ");
		String comment = scanner.nextLine();
		System.out.print(" ");
		
		
		
		BookVo vo = new BookVo();
		boolean success = dao.insert(vo);
		
		System.out.println("Book INSERT" + (success ? "성공":"실패"));
		scanner.close();
		
	}
	
	private static void deletedBook() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("삭제:");
		
		BookDao dao = new BookDaoImpl();
		boolean success = dao.delete(bookId);
		
		System.out.println("BOOK DELETE" + (success ? "성공": "실패"));
		scanner.close();
	}
	
	
}
