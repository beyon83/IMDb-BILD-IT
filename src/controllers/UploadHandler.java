package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DAO.MySqlConnectDAO;
import model.Movie;
import model.Services;

/**
 * Servlet implementation class UploadHandler
 */
@WebServlet("/UploadHandler")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class UploadHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/** Create Connection object */
	private Connection mysqlConnect = null;
	
	/** Invoke init() method and assign Connection to the mysqlConnect variable */
	public void init() {
		mysqlConnect = MySqlConnectDAO.mySqlConnect();
	}
	
	/** Close the connection with the database */
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Upload servlet invoked.");
		
		/** Initialize Service object */
		Services service = new Services();
		
		/** Get parameters from input form */
		String movieTitle = request.getParameter("movieTitle");
		String year = request.getParameter("year");
		int movieYear = Integer.parseInt(year); // Parse string year to the integer
		String genre = request.getParameter("genre");
		String description = request.getParameter("description");
		String cast = request.getParameter("cast");
		String director = request.getParameter("director");
		
		/** Obtain upload file */
		Part filePart = request.getPart("photo");
		
		/** Obtain stream of the upload file */
		 InputStream inputStream = filePart.getInputStream();
		
		/** Invoke uploadMovie() method, and pass arguments to it */
		service.uploadMovie(movieTitle, movieYear, genre, description, cast, director, inputStream, mysqlConnect);
		
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.valueOf(request.getParameter("page"));
		}
		
		/** Assign getMovies() method's results to the ArrayList */
		ArrayList<Movie> movie = service.getMovies(page, mysqlConnect);
		
		/** Set request attribute object for movies ArrayList */
		request.setAttribute("movies", movie);
		
		/** Send request message to the user */
		request.setAttribute("message", "The movie \"" + movieTitle + "\" has been successfully added to the database!");
		/** Redirect user to the movies.jsp page */
		request.getRequestDispatcher("movies.jsp").forward(request, response);
		
//		System.out.println("filePart getName(): " + filePart.getName());
//		System.out.println("filePart getContentType() " + filePart.getContentType());
//		System.out.println("filePart getSize() " + filePart.getSize());
		
	}

}
