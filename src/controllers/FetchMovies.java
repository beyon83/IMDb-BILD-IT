package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MySqlConnectDAO;
import model.Movie;
import model.Services;

/**
 * Servlet implementation class FetchMovies
 */
@WebServlet("/FetchMovies")
public class FetchMovies extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection mysqlConnect = null;

	public void init() {
		mysqlConnect = MySqlConnectDAO.mySqlConnect();
	}
	
	public void destroy() {
		try {
			if(mysqlConnect != null) {
				mysqlConnect.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("image/jpeg");
		
		System.out.println("FetchMovies - Servlet has been invoked! ");
		
		/** Instantiate Services object */
		Services service = new Services();
		
		int page = 1; // set page = 1 by default
		
		if(request.getParameter("page") != null) {
			/** If page number is pressed, set page = that number of page */
			page = Integer.valueOf(request.getParameter("page"));
		}
		
		request.setAttribute("currentPage", page);
		
		/** Assign getMovies() method's results to the ArrayList */
		ArrayList<Movie> movie = service.getMovies(page - 1, mysqlConnect);
		
		/** Get pagination links */
		ArrayList<Integer> paginationList = service.paginationList(page);
		request.setAttribute("paginationList", paginationList);
		
		/** Set request attribute object for movies ArrayList */
		request.setAttribute("movies", movie);
		
		request.setAttribute("pages", Movie.getNumberOfPages());
		
		/** Redirect user to the movies.jsp page */
		request.getRequestDispatcher("movies.jsp").forward(request, response);
		
	}

}
