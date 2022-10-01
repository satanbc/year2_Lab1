package com.lab1.task.controller;

import java.util.List;

import com.lab1.task.Entities.Author;
import com.lab1.task.Entities.Book;
import com.lab1.task.Entities.Character;
import com.lab1.task.Entities.Series;
import com.lab1.task.service.AuthorService;
import com.lab1.task.service.BookService;
import com.lab1.task.service.SeriesService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.NoResultException;

@Controller
@RequestMapping("/books")
public class BookController {

	private BookService bookService;
	private SeriesService seriesService;
	private AuthorService authorService;
	
	public BookController(BookService theBookService, SeriesService seriesService, AuthorService authorService) {
		bookService = theBookService;
		this.seriesService = seriesService;
		this.authorService = authorService;
	}
	
	// add mapping for "/list"

	@GetMapping("/list")
	public String listBooks(Model theModel){

		List<Book> theBooks = bookService.findAll();

		theModel.addAttribute("books", theBooks);

		return "books/list-books";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){

		Book theBook = new Book();

		theModel.addAttribute("book", theBook);

		return "books/book-form";
	}

	@PostMapping("/save")
	public String saveBook(@ModelAttribute("book") Book theBook){

		int i = -1;

		Series series = seriesService.findById(1);

		String a = theBook.getAuthor().getName();

		SessionFactory factory = new Configuration()
				.configure()
				.addAnnotatedClass(Author.class)
				.addAnnotatedClass(Series.class)
				.addAnnotatedClass(Book.class)
				.addAnnotatedClass(Character.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();
		session.beginTransaction();

		try{
			Query<Integer> query = session.createQuery("select author.id from Author author where author.name=:name");
			query = query.setParameter("name", a);
			i = query.getSingleResult();
		}catch (NoResultException e){

		}
		Author author;

		if (i == -1){
			author = new Author(a, "00.00.00");
		}
		else
			author = authorService.findById(i);

		theBook.setAuthor(author);
		theBook.setSeries(series);
		bookService.save(theBook);

		return "redirect:/books/list";
	}
}









