package com.vemezhevikin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vemezhevikin.weather.Location;

public class LocationDaoDB implements Connectable, LocationDao
{
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/";
	private static final String DB_NAME = "weatherApp";

	private static final String USER = "root";
	private static final String PASS = "password";

	private static LocationDaoDB instance = null;

	private LocationDaoDB()
	{
	}

	public static LocationDaoDB getInstance()
	{
		if (instance == null)
			instance = new LocationDaoDB();
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
	public void create(Location location)
	{
		String sql = "INSERT INTO locations (name) VALUES (?)";

		Connection connection = getConnection();
		try
		{
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, location.getName());
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
	public List<Location> selectAll(int userId)
	{
		List<Location> locations = new ArrayList<>();

		String sql = "SELECT id, name FROM locations WHERE id IN (SELECT locationId FROM selections WHERE userId=?)";

		Connection connection = getConnection();
		try
		{
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, userId);
			ResultSet results = statement.executeQuery();
			while (results.next())
			{
				int id = results.getInt("id");
				String name = results.getString("name");
				Location location = new Location(id, name);
				locations.add(location);
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

		return locations;
	}

	@Override
	public Location select(int id)
	{
		Location location = null;

		String sql = "SELECT name FROM locations WHERE id=?";

		Connection connection = getConnection();
		try
		{
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			if (results.next())
			{
				String name = results.getString("name");
				location = new Location(id, name);
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

		return location;
	}

	@Override
	public Location select(String locationName)
	{
		Location location = null;

		String sql = "SELECT id FROM locations WHERE name=?";

		Connection connection = getConnection();
		try
		{
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, locationName);
			ResultSet results = statement.executeQuery();
			if (results.next())
			{
				int id = results.getInt("id");
				location = new Location(id, locationName);
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

		return location;
	}

	public static void main(String[] args)
	{
		LocationDaoDB dao = LocationDaoDB.getInstance();

		// Weather weather = new Weather("Kharkiv");
		// dao.create(weather);

		List<Location> weathers = dao.selectAll(1);
		for (Location w : weathers)
			System.out.println(w);
	}
}
