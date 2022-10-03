package com.lab1.task.service;

import java.util.List;

import com.lab1.task.Entities.Book;
import com.lab1.task.dao.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface BookService {

	public List<Book> findAll();
	
	public Book findById(int theId);
	
	public void save(Book theBook);
	
	public void deleteById(int theId);

	public List<Book> getByKeyword(String keyword);
}
