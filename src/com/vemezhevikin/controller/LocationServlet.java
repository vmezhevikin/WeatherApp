package com.vemezhevikin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vemezhevikin.dao.LocationDaoDB;
import com.vemezhevikin.dao.SelectionDaoDB;
import com.vemezhevikin.dao.UserDaoDB;
import com.vemezhevikin.weather.Location;
import com.vemezhevikin.weather.User;

@WebServlet(name = "LocationServlet", urlPatterns = { "/location" })
public class LocationServlet extends HttpServlet
{
	private static final long serialVersionUID = -6235783815398583057L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String userid = request.getParameter("userid");
		String locationname = request.getParameter("locationname");
		String action = request.getParameter("action");
		String locationid = request.getParameter("locationid");
		
		UserDaoDB userDao = UserDaoDB.getInstance();
		LocationDaoDB locationDao = LocationDaoDB.getInstance();
		SelectionDaoDB selectioDao = SelectionDaoDB.getInstance();
		
		if ("add".equals(action))
		{
			int userId = Integer.parseInt(userid);	
			
			if (!"".equals(locationname))
			{
				Location location = locationDao.select(locationname);
				if (location == null)
				{
					locationDao.create(new Location(locationname));
					location = locationDao.select(locationname);
				}
				
				selectioDao.create(userId, location.getId());
			}
			
			User user = userDao.select(userId);
			
			List<Location> locations = locationDao.selectAll(user.getId());
			request.setAttribute("user", user);
			request.setAttribute("locations", locations);
			request.getRequestDispatcher("/locations.jsp").forward(request, response);
		}
		
		if ("remove".equals(action))
		{
			int userId = Integer.parseInt(userid);
			int locationId = Integer.parseInt(locationid);
			
			selectioDao.remove(userId, locationId);

			User user = userDao.select(userId);
			
			List<Location> locations = locationDao.selectAll(user.getId());
			request.setAttribute("user", user);
			request.setAttribute("locations", locations);
			request.getRequestDispatcher("/locations.jsp").forward(request, response);
		}
	}
}
