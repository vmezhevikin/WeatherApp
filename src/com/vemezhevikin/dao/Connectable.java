package com.vemezhevikin.dao;

import java.sql.Connection;

public interface Connectable
{
	Connection getConnection();

	void closeConnection(Connection connection);
}
