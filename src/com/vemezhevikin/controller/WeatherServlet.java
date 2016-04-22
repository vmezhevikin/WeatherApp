package com.vemezhevikin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vemezhevikin.dao.WeatherDaoWebService;
import com.vemezhevikin.weather.Weather;

@WebServlet(name = "WeatherServlet", urlPatterns = { "/weather" })
public class WeatherServlet extends HttpServlet
{
	private static final long serialVersionUID = -4078455644603335917L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String locationName = request.getParameter("locationname");
		
		WeatherDaoWebService weatherDao = WeatherDaoWebService.getInstance();
		
		Weather weather = weatherDao.getWeatherForCity(locationName);
		
		request.setAttribute("locationname", locationName);
		request.setAttribute("weather", weather);
		request.getRequestDispatcher("/weather.jsp").forward(request, response);
	}
}
