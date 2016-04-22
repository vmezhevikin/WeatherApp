package com.vemezhevikin.dao;

import java.util.List;

import com.vemezhevikin.weather.Location;

public interface LocationDao
{
	void create(Location location);
	
	List<Location> selectAll(int userId);
	
	Location select(int id);

	Location select(String locationName);
}
