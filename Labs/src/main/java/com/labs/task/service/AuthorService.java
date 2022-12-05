package com.labs.task.service;

import com.labs.task.Entities.Author;

import java.util.List;

public interface AuthorService {

	public List<Author> findAll();
	
	public Author findById(int theId);
	
	public void save(Author theAuthor);
	
	public void deleteById(int theId);
	
}
