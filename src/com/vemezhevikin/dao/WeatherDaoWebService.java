package com.vemezhevikin.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.vemezhevikin.weather.Condition;
import com.vemezhevikin.weather.Weather;

public class WeatherDaoWebService implements WeatherDao
{
	private String urlApi = "http://api.worldweatheronline.com/premium/v1/weather.ashx?key=ebbac448198a49d7af3104627162004&q={{CITY}}&format=json&num_of_days=5";
	
	private static WeatherDaoWebService instance = null;
	
	private WeatherDaoWebService(){}
	
	public static WeatherDaoWebService getInstance()
	{
		if (instance == null)
			instance = new WeatherDaoWebService();
		return instance;
	}
	
	@Override
	public Weather getWeatherForCity(String city)
	{
		Weather weather = new Weather();
		
		String urlStr = urlApi.replace("{{CITY}}", city);
		
		try
		{
			URL url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			InputStream stream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buffer = new BufferedReader(reader);		
			JSONParser jsonParser = new JSONParser();
			
			JSONObject root = (JSONObject) jsonParser.parse(buffer);
			JSONObject data = (JSONObject) root.get("data");
			
			JSONArray currentConditionsJArray = (JSONArray) data.get("current_condition");
			JSONArray forecastConditionsJArray = (JSONArray) data.get("weather");
			
			JSONObject currentConditionJObject = (JSONObject) currentConditionsJArray.get(0);
			JSONArray weatherDescJArray = (JSONArray) currentConditionJObject.get("weatherDesc");
			JSONObject weatherDescJObject = (JSONObject) weatherDescJArray.get(0);
			JSONArray weatherIconUrlJArray = (JSONArray) currentConditionJObject.get("weatherIconUrl");
			JSONObject weatherIconUrlJObject = (JSONObject) weatherIconUrlJArray.get(0);
			
			Condition currentCondition = new Condition();
			currentCondition.setDate(currentConditionJObject.get("observation_time").toString());
			currentCondition.setTempC(currentConditionJObject.get("temp_C").toString());
			currentCondition.setHumidity(currentConditionJObject.get("humidity").toString());
			currentCondition.setPressure(currentConditionJObject.get("pressure").toString());
			currentCondition.setWindspeedKmph(currentConditionJObject.get("windspeedKmph").toString());
			currentCondition.setWinddir16Point(currentConditionJObject.get("winddir16Point").toString());
			currentCondition.setWeatherDesc(weatherDescJObject.get("value").toString());
			currentCondition.setWeatherIconUrl(weatherIconUrlJObject.get("value").toString());
			weather.setCurrentCondition(currentCondition);
			
			for (int day=0; day<5; day++)
			{
				JSONObject forecastConditionJObject = (JSONObject) forecastConditionsJArray.get(day);
				JSONArray forecastHourlyJArray = (JSONArray) forecastConditionJObject.get("hourly");
				
				for (int time=0; time<8; time++)
				{
					JSONObject forecastHourlyJObject = (JSONObject) forecastHourlyJArray.get(time);
					
					JSONArray forecastDescJArray = (JSONArray) forecastHourlyJObject.get("weatherDesc");
					JSONObject forecastDescJObject = (JSONObject) forecastDescJArray.get(0);
					JSONArray forecastIconUrlJArray = (JSONArray) forecastHourlyJObject.get("weatherIconUrl");
					JSONObject forecastIconUrlJObject = (JSONObject) forecastIconUrlJArray.get(0);
					
					Condition forecast = new Condition();
					forecast.setDate(forecastConditionJObject.get("date").toString());
					forecast.setTime(forecastHourlyJObject.get("time").toString());
					forecast.setTempC(forecastHourlyJObject.get("tempC").toString());
					forecast.setHumidity(forecastHourlyJObject.get("humidity").toString());
					forecast.setPressure(forecastHourlyJObject.get("pressure").toString());
					forecast.setWindspeedKmph(forecastHourlyJObject.get("windspeedKmph").toString());
					forecast.setWinddir16Point(forecastHourlyJObject.get("winddir16Point").toString());
					forecast.setWeatherDesc(forecastDescJObject.get("value").toString());
					forecast.setWeatherIconUrl(forecastIconUrlJObject.get("value").toString());
					weather.addForecast(day, time, forecast);
				}
			}
			
		} catch (MalformedURLException e)
		{
			weather = null;
			e.printStackTrace();
		} catch (IOException e)
		{
			weather = null;
			e.printStackTrace();
		} catch (ParseException e)
		{
			weather = null;
			e.printStackTrace();
		}
		
		return weather;
	}

	public static void main(String[] args)
	{
		WeatherDaoWebService client = WeatherDaoWebService.getInstance();
		Weather weather = client.getWeatherForCity("Kharkiv");
		System.out.println(weather);
	}
}
