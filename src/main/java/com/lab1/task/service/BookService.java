package com.lab1.task.service;

import java.util.List;

import com.lab1.task.Entities.Book;

public interface BookService {

	public List<Book> findAll();
	
	public Book findById(int theId);
	
	public void save(Book theBook);
	
	public void deleteById(int theId);
	
}
