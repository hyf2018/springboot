package com.example.demo.property;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookTest {
	@Autowired
	private Book book;

	@Test
	public void test() {
		System.out.println(book.getName());
		System.out.println(book.getAuthor());
		System.out.println(book.getDesc());
	}
}
