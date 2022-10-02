package com.lab1.task.controller;

import java.util.ArrayList;
import java.util.List;

import com.lab1.task.Entities.Author;
import com.lab1.task.Entities.Book;
import com.lab1.task.Entities.Character;
import com.lab1.task.Entities.Series;
import com.lab1.task.service.AuthorService;
import com.lab1.task.service.BookService;
import com.lab1.task.service.CharacterService;
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
	private CharacterService characterService;
	
	public BookController(BookService theBookService, SeriesService seriesService, AuthorService authorService, CharacterService characterService) {
		bookService = theBookService;
		this.seriesService = seriesService;
		this.authorService = authorService;
		this.characterService = characterService;
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

		for(int i = 1; i <= 3; i++){
			theBook.addCharacter(new Character());
		}

		theModel.addAttribute("book", theBook);

		return "books/book-form";
	}

	@PostMapping("/save")
	public String saveBook(@ModelAttribute("book") Book theBook){

		int index = -1;
		boolean isMainCharacterNew = true;
		String authorName = theBook.getAuthor().getName();

		Author tempAuthor;
		Series series = new Series("Not in any series");
		Series tempSeriesObject = null;

		List<Integer> characterIdList = new ArrayList<>();
		List<Character> characterList = new ArrayList<>();


		SessionFactory factory = new Configuration()
				.configure()
				.addAnnotatedClass(Author.class)
				.addAnnotatedClass(Series.class)
				.addAnnotatedClass(Book.class)
				.addAnnotatedClass(Character.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();


		try{
			Query<Integer> query1 = session.createQuery("select author.id from Author author where author.name=:name");
			query1 = query1.setParameter("name", authorName);
			index = query1.getSingleResult();
		}catch (NoResultException e){}
		try{
			Query<Integer> query2 = session.createQuery("select character.id from Character character where character.role=:role");
			query2 = query2.setParameter("role", "main");
			characterIdList = query2.getResultList();
		}catch (NoResultException e){}

		for (int m : characterIdList){
			characterList.add(characterService.findById(m));
		}


		if (index == -1){
			tempAuthor = new Author(authorName);
		}
		else {
			tempAuthor = authorService.findById(index);
		}


		for (Character cha : characterList){
			for (int t = 0; t < theBook.getCharacters().size(); t++){
				if(cha.getName().equals(theBook.getCharacters().get(t).getName())){
					tempSeriesObject = cha.getBooks().get(0).getSeries();
					isMainCharacterNew = false;
					break;
				}
			}
		}


		if (isMainCharacterNew) {
			for (int t = 0; t < theBook.getCharacters().size(); t++) {
				if (theBook.getCharacters().get(t).getRole().equals("main")) {
					String name = theBook.getCharacters().get(t).getName();
					series = new Series(name + " adventures");
					tempAuthor.addSeries(series);
					authorService.save(tempAuthor);
					series.setAuthor(tempAuthor);
					series.addBook(theBook);
					seriesService.save(series);
					break;
				}
			}
		}else 
			series = tempSeriesObject;


		tempAuthor.addBook(theBook);
		theBook.setAuthor(tempAuthor);
		theBook.setSeries(series);

		bookService.save(theBook);

		return "redirect:/books/list";
	}
}









