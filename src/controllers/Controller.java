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
import model.Services;
import model.User;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection mysqlConnect = null;
	
	public void init() {
		mysqlConnect = MySqlConnectDAO.mySqlConnect();
		System.out.println("Controller's init() has been invoked.");
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
		
		HttpSession session = request.getSession();
		
		/** Instantiate Services object */
		Services service = new Services();
		
		/** Instantiate User object */
		User user = new User();
		
		/** Obtain user's input informations from the sign-up form */
		String userName = request.getParameter("username");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String registrationDate = user.getRegistrationDate();
		String email = request.getParameter("email");
		
		/** SignUp User Registration */
		if(request.getParameter("signup") != null) {
			
			/** Check if username is already taken invoking isUsernameTaken() method from the "Services" class */
			if(service.isUsernameTaken(userName, mysqlConnect) == false) {
				session.setAttribute("sessionUser", userName); // set user session
				/** If username is not taken, pass arguments to the signUp() method and register user in the database */
				service.signUp(userName, firstName, lastName, password, gender, registrationDate, email, mysqlConnect);
				/** Send successfully message to the index.jsp page */
				request.setAttribute("message", "User " + userName + " has been successfully added to the database.");
				request.getRequestDispatcher("movies.jsp").forward(request, response); // redirect to the index.jsp page
				System.out.println("User has been added to the database!");
			} else {
				request.setAttribute("usernameTaken", userName); // set request for taken username
				request.getRequestDispatcher("signup.jsp").forward(request, response); // redirect to the signup.jsp page
				System.out.println("The username is already taken!");
			}
		} // End of SignUp User
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
