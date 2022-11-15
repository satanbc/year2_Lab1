package com.lab1.task.service;

import com.lab1.task.Entities.Series;

import java.util.List;

public interface SeriesService {

	public List<Series> findAll();
	
	public Series findById(int theId);
	
	public void save(Series theSeries);
	
	public void deleteById(int theId);
	
}
