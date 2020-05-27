package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.property.Book;

@RestController
public class HelloController {
	@Autowired
	private Book book;

	@RequestMapping("/hello")
	public String hello() {
		return "Greetings from Spring Boot!";
	}

	@RequestMapping("/book")
	public Book book() {
		return book;
	}

}