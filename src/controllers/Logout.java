package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Logout servlet has been invoked");
		
		/** Create a session object */
		HttpSession session = request.getSession();
		
		/** Create a Dispatcher for redirecting the user to the login.jsp page after logging out */
		RequestDispatcher dsp = request.getRequestDispatcher("index.jsp");
		session.invalidate(); // destroy session
		dsp.forward(request, response);
		
	}

}
