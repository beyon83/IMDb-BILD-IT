package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MySqlConnectDAO;
import model.Movie;
import model.Services;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection mysqlConnect = null;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		this.mysqlConnect = MySqlConnectDAO.mySqlConnect();
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			if(mysqlConnect != null) {
				mysqlConnect.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Services service = new Services();
		
		String query = request.getParameter("query");
		request.setAttribute("query", query);
		
		ArrayList<Movie> getResults = service.searchResults(query, mysqlConnect);
		
		if(getResults.size() == 0) {
			request.setAttribute("noResults", "No results found.");
			System.out.println("No results found.");
		} else {
			request.setAttribute("arraylist", getResults);
			System.out.println("Results found: ");
		}
		
		request.getRequestDispatcher("SearchResults.jsp").forward(request, response);
	}

}
