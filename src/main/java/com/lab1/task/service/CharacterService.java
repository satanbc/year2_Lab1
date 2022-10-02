package com.lab1.task.service;

import com.lab1.task.Entities.Character;
import com.lab1.task.Entities.Series;

import java.util.List;

public interface CharacterService {

	public List<Character> findAll();
	
	public Character findById(int theId);
	
	public void save(Character theCharacter);
	
	public void deleteById(int theId);
	
}
