package com.vemezhevikin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vemezhevikin.dao.UserDaoDB;
import com.vemezhevikin.dao.LocationDaoDB;
import com.vemezhevikin.weather.User;
import com.vemezhevikin.weather.Location;

@WebServlet(name = "UserServlet", urlPatterns = { "/login" })
public class UserServlet extends HttpServlet
{
	private static final long serialVersionUID = -6538448610354199438L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String action = request.getParameter("action");
		
		if ("".equals(username) || "".equals(password))
		{
			response.sendRedirect("/WeatherApp/login.jsp");
			return;
		}
		
		UserDaoDB userDao = UserDaoDB.getInstance();
		LocationDaoDB locationDao = LocationDaoDB.getInstance();
		
		User user = userDao.select(username, password);
		
		if ("login".equals(action))
		{
			if (user == null)
				response.sendRedirect("/WeatherApp/login.jsp");
			else
			{
				List<Location> locations = locationDao.selectAll(user.getId());
				request.setAttribute("user", user);
				request.setAttribute("locations", locations);
				request.getRequestDispatcher("/locations.jsp").forward(request, response);
			}
		}

		if ("signin".equals(action))
		{
			if (user == null)
			{
				userDao.create(username, password);
				user = userDao.select(username, password);
				List<Location> locations = locationDao.selectAll(user.getId());
				request.setAttribute("user", user);
				request.setAttribute("locations", locations);
				request.getRequestDispatcher("/locations.jsp").forward(request, response);
			}
			else
				response.sendRedirect("/WeatherApp/login.jsp");
		}
	}
}
