package com.vemezhevikin.weather;

public class Condition
{
	private String date;
	private String time;
	private String tempC;
	private String humidity;
	private String pressure;
	private String windspeedKmph;
	private String winddir16Point;
	private String weatherDesc;
	private String weatherIconUrl;

	public Condition()
	{
		super();
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		if (time.contains("000"))
			this.time = time.replace("000", "0:00");
		else
			this.time = time.replace("00", ":00");
	}

	public String getTempC()
	{
		return tempC;
	}

	public void setTempC(String tempC)
	{
		this.tempC = tempC;
	}

	public String getHumidity()
	{
		return humidity;
	}

	public void setHumidity(String humidity)
	{
		this.humidity = humidity;
	}

	public String getPressure()
	{
		return pressure;
	}

	public void setPressure(String pressure)
	{
		this.pressure = pressure;
	}

	public String getWindspeedKmph()
	{
		return windspeedKmph;
	}

	public void setWindspeedKmph(String windspeedKmph)
	{
		this.windspeedKmph = windspeedKmph;
	}

	public String getWinddir16Point()
	{
		return winddir16Point;
	}

	public void setWinddir16Point(String winddir16Point)
	{
		this.winddir16Point = winddir16Point;
	}

	public String getWeatherDesc()
	{
		return weatherDesc;
	}

	public void setWeatherDesc(String weatherDesc)
	{
		this.weatherDesc = weatherDesc;
	}

	public String getWeatherIconUrl()
	{
		return weatherIconUrl;
	}

	public void setWeatherIconUrl(String weatherIconUrl)
	{
		this.weatherIconUrl = weatherIconUrl;
	}

	@Override
	public String toString()
	{
		return "Condition [tempC=" + tempC + ", humidity=" + humidity + ", pressure=" + pressure + ", windspeedKmph="
				+ windspeedKmph + ", winddir16Point=" + winddir16Point + ", weatherDesc=" + weatherDesc
				+ ", weatherIconUrl=" + weatherIconUrl + "]";
	}
}
