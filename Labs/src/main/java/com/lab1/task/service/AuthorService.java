package com.lab1.task.service;

import com.lab1.task.Entities.Author;

import java.util.List;

public interface AuthorService {

	public List<Author> findAll();
	
	public Author findById(int theId);
	
	public void save(Author theAuthor);
	
	public void deleteById(int theId);
	
}
