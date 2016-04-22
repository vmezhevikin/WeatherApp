package com.vemezhevikin.dao;

import com.vemezhevikin.weather.User;

public interface UserDao
{
	User select(String name, String password);
	
	User select(int id);
	
	void create(String name, String password);
}
