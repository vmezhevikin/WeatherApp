package com.vemezhevikin.dao;

import com.vemezhevikin.weather.Selection;

public interface SelectionDao
{
	Selection select(int userId, int locationId);
	
	void create(int userId, int locationId);
	
	void remove(int userId, int locationId);
}
