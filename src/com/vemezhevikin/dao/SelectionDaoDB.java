package com.vemezhevikin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vemezhevikin.weather.Location;
import com.vemezhevikin.weather.Selection;
import com.vemezhevikin.weather.User;

public class SelectionDaoDB implements Connectable, SelectionDao
{
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/";
	private static final String DB_NAME = "weatherApp";

	private static final String USER = "root";
	private static final String PASS = "password";
	
	private static SelectionDaoDB instance = null;
	
	private SelectionDaoDB(){}
	
	public static SelectionDaoDB getInstance()
	{
		if (instance == null)
			instance = new SelectionDaoDB();
		return instance;
	}
	
	@Override
	public Connection getConnection()
	{
		Connection connection = null;

		try
		{
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL + DB_NAME, USER, PASS);
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return connection;
	}

	@Override
	public void closeConnection(Connection connection)
	{
		try
		{
			connection.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public Selection select(int userId, int locationId)
	{
		String sql1 = "SELECT name, password FROM users WHERE id=?";
		String sql2 = "SELECT name FROM locations WHERE id=?";
		String sql3 = "SELECT id FROM selections WHERE userId=? AND locationId=?";
		
		User user = null;
		Location location = null;
		Selection selection = null;
		
		Connection connection = getConnection();
		try
		{
			PreparedStatement statement1 = connection.prepareStatement(sql1);
			statement1.setInt(1, userId);
			ResultSet results1 = statement1.executeQuery();
			if (results1.next())
			{
				String name = results1.getString("name");
				String password = results1.getString("password");
				user = new User(userId, name, password);
			}
			results1.close();
			
			PreparedStatement statement2 = connection.prepareStatement(sql2);
			statement2.setInt(1, locationId);
			ResultSet results2 = statement2.executeQuery();
			if (results2.next())
			{
				String name = results2.getString("name");
				location = new Location(locationId, name);
			}
			results2.close();
			
			PreparedStatement statement3 = connection.prepareStatement(sql3);
			statement3.setInt(1, userId);
			statement3.setInt(2, locationId);
			ResultSet results = statement1.executeQuery();
			if (results.next())
			{
				int id = results.getInt("id");
				selection = new Selection(id, user, location);
			}
			results.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			if (connection != null)
				closeConnection(connection);
		}
		
		return selection;
	}
	
	@Override
	public void create(int userId, int locationId)
	{
		String sql = "INSERT INTO selections (userId, locationId) VALUES (?, ?)";
		
		Connection connection = getConnection();
		try
		{
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, userId);
			statement.setInt(2, locationId);
			statement.execute();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			if (connection != null)
				closeConnection(connection);
		}
	}
	
	@Override
	public void remove(int userId, int locationId)
	{
		String sql = "DELETE FROM selections WHERE userId=? AND locationId=?";
		
		Connection connection = getConnection();
		try
		{
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, userId);
			statement.setInt(2, locationId);
			statement.execute();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			if (connection != null)
				closeConnection(connection);
		}
	}
}
