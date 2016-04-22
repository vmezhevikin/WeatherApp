package com.vemezhevikin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vemezhevikin.weather.User;

public class UserDaoDB implements Connectable, UserDao
{
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/";
	private static final String DB_NAME = "weatherApp";

	private static final String USER = "root";
	private static final String PASS = "password";
	
	private static UserDaoDB instance = null;
	
	private UserDaoDB(){}
	
	public static UserDaoDB getInstance()
	{
		if (instance == null)
			instance = new UserDaoDB();
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
	public User select(String name, String password)
	{
		String sql = "SELECT id FROM users WHERE name=? AND password=?";
		
		User user = null;
		
		Connection connection = getConnection();
		try
		{
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			statement.setString(2, password);
			ResultSet results = statement.executeQuery();
			if (results.next())
			{
				int id = results.getInt("id");
				user = new User(id, name, password);
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
		
		return user;
	}
	
	@Override
	public User select(int id)
	{
		String sql = "SELECT name, password FROM users WHERE id=?";
		
		User user = null;
		
		Connection connection = getConnection();
		try
		{
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			if (results.next())
			{
				String name = results.getString("name");
				String password = results.getString("password");
				user = new User(id, name, password);
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
		
		return user;
	}
	
	@Override
	public void create(String name, String password)
	{
		String sql = "INSERT INTO users (name, password) VALUES (?, ?)";
		
		Connection connection = getConnection();
		try
		{
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			statement.setString(2, password);
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
	
	public static void main(String[] args)
	{
		UserDaoDB dao = UserDaoDB.getInstance();
		User user = dao.select("Alice", "Alice");
		System.out.println(user);
	}
}
