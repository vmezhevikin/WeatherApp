package com.vemezhevikin.dao;

import com.vemezhevikin.weather.Weather;

public interface WeatherDao
{
	Weather getWeatherForCity(String city);
}
