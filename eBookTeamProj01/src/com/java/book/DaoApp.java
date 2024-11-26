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
		
		String title = scanner.nextLine();
		System.out.print(" ");
		
		
		BookVo vo = new BookVo();
		boolean success = dao.insert(vo);
		
		System.out.println("Book INSERT" + (success ? "성공":"실패"));
		scanner.close();
		
	}
	
	
	
}
