package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MySqlConnectDAO;
import model.Services;

/**
 * Servlet implementation class CheckUsername
 */
@WebServlet("/CheckUsername")
public class CheckUsername extends HttpServlet {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/** Instantiate Services object */
		Services service = new Services();
		
		/** Create PrintWriter for output response */
		PrintWriter out = response.getWriter();
		
		/** Obtain username from the input */
		String userName = request.getParameter("username");
		
		/** Check if username already exist in the database */
		boolean checkUsername = service.isUsernameTaken(userName, mysqlConnect);
		
		if(checkUsername == true) {
			/** Output message to the AJAX inside of the signup.jsp page */
			out.write("<span style='color: #FF4747;'>Username \"" + userName + "\" is already taken!</span>");
		}
		
	}

}
