package com.vemezhevikin.weather;

import java.util.Arrays;

public class Weather
{
	private Condition currentCondition;
	private Condition[][] forecast;

	public Weather()
	{
		super();
		currentCondition = null;
		forecast = new Condition[5][8];
	}

	public void setCurrentCondition(Condition currentCondition)
	{
		this.currentCondition = currentCondition;
	}

	public Condition getCurrentCondition()
	{
		return currentCondition;
	}

	public void addForecast(int day, int time, Condition forecast)
	{
		this.forecast[day][time] = forecast;
	}
	
	public Condition getForecast(int day, int time)
	{
		return forecast[day][time];
	}

	@Override
	public String toString()
	{
		return "Weather [currentCondition=" + currentCondition + ", forecast=" + Arrays.toString(forecast) + "]";
	}
}
