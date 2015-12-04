package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.MySqlConnectDAO;
import model.Services;

/**
 * Servlet implementation class LoginHandler
 */
@WebServlet("/LoginHandler")
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/** Declare Connection field */
	private Connection conn = null;
	
	/** Establish MySql connection with the database, using init() method */
	public void init(ServletConfig config) {
		conn = MySqlConnectDAO.mySqlConnect(); // Assign connection to the conn data field variable
		System.out.println("init() has been invoked!");
	}
	
	/** Destroy connection with the database, after servlet is no longer in use */
	public void destroy() {
		try {
			if(conn != null) {
				conn.close();
			}
			System.out.println("destroy() has been invoked!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/** Session object */
		HttpSession session = request.getSession();
		/** RequestDispatcher object, redirect user to the "index.jsp" page */
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		
		/** Obtain input parameters from the "login.jsp" page */
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		/** Declare an error message */
		String errorLoginMessage = "Username or password doesn't match.";
		
		/** Instantiate Services object */
		Services service = new Services();
		/** Invoke authenticate() method to check if user name and password matches */
		boolean checkUser = service.authenticateUser(userName, password, conn);
		
		if(checkUser == true) {
			if(service.isAdmin(userName, conn) == true) {
				/** If logged user is admin, set session attribute to admin */
				session.setAttribute("admin", userName);
			} else {
				/** If username/password are valid, create session for that user */
				session.setAttribute("sessionUser", userName);
			}
			dispatcher.forward(request, response); // redirect user to the index.jsp page
		} else {
			/** Otherwise, send error message to the user and redirect to the login.jsp page */
			session.setAttribute("errorLoginMessage", errorLoginMessage);
			response.sendRedirect("login.jsp");
		}
		
	}

}
