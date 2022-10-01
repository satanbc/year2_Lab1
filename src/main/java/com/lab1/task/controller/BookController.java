package com.lab1.task.controller;

import java.util.List;

import com.lab1.task.Entities.Book;
import com.lab1.task.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {

	private BookService bookService;
	
	public BookController(BookService theBookService) {
		bookService = theBookService;
	}
	
	// add mapping for "/list"

	@GetMapping("/list")
	public String listBooks(Model theModel){

		List<Book> theBooks = bookService.findAll();

		theModel.addAttribute("books", theBooks);

		return "list-books";
	}
}









