package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.MySqlConnectDAO;
import model.Movie;
import model.Services;

/**
 * Servlet implementation class ShowMovie
 */
@WebServlet("/ShowMovie")
public class ShowMovie extends HttpServlet {
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
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/** Create HttpSession object */
		HttpSession session = request.getSession();
		
		/** Instantiate Services object */
		Services service = new Services();
		
		/** Obtain id request from the movies.jsp page */
		String strID = request.getParameter("id");
		int movieId = Integer.valueOf(strID); // parse it into int
		
		/** Execute this if-statement block if "ShowMovie?vote=" is submitted */
		if(request.getParameter("rating") != null) {
			String rating = request.getParameter("rating");
			int ratingNumber = Integer.valueOf(rating);
			/** Obtain session's username */
			String userName = (String) session.getAttribute("sessionUser");
			/** Obtain logged user's ID */
			int userId = service.getUserId(userName, mysqlConnect);
			if(service.isAlreadyVoted(userId, movieId, mysqlConnect) == false) {
				/** Submit vote to the "user_votes" - table */
				service.vote(userId, movieId, mysqlConnect);
				service.updateVotes(movieId, ratingNumber, mysqlConnect);
				request.setAttribute("successfullyRated", "You gave this movie a rating +" + ratingNumber);
			} else {
				request.setAttribute("alreadyRated", "You have already submitted your rating for this movie.");
			}
		}
		
		/** Invoke showMovie() method and assign it to the getMovie object */
		Movie[] getMovie = service.showMovie(movieId, mysqlConnect);
			
		/** Set request movie object for EL call inside movie.jsp file */
		request.setAttribute("movie", getMovie);
		
		/** Redirect to movie.jsp page */
		request.getRequestDispatcher("movie.jsp").forward(request, response);
		
	}

}
