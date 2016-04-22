package com.vemezhevikin.weather;

public class Selection
{
	private int id;
	private User user;
	private Location location;

	public Selection()
	{
		super();
	}

	public Selection(User user, Location location)
	{
		super();
		this.user = user;
		this.location = location;
	}
	
	public Selection(int id, User user, Location location)
	{
		super();
		this.id = id;
		this.user = user;
		this.location = location;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Location getLocation()
	{
		return location;
	}

	public void setLocation(Location location)
	{
		this.location = location;
	}

	@Override
	public String toString()
	{
		return "Selection [id=" + id + ", user=" + user + ", location=" + location + "]";
	}
}
