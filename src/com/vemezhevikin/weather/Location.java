package com.vemezhevikin.weather;

public class Location
{
	private int id;
	private String name;

	public Location()
	{
		super();
	}

	public Location(String name)
	{
		super();
		this.name = name;
	}

	public Location(int id, String name)
	{
		super();
		this.id = id;
		this.name = name;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return "Weather [id=" + id + ", locationName=" + name + "]";
	}
}
