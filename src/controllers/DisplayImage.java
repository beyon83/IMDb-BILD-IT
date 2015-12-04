package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MySqlConnectDAO;

/**
 * Servlet implementation class ImageTest
 */
@WebServlet("/DisplayImage")
public class DisplayImage extends HttpServlet {
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
		
		String id = request.getParameter("id");
		int movieId = Integer.valueOf(id);
		
		String sqlSelect = " SELECT `photo` FROM `movies` WHERE `movieID` = '" + movieId + "' ";
		ResultSet rs = null;
		try(PreparedStatement prepStatement = mysqlConnect.prepareStatement(sqlSelect)) {
			rs = prepStatement.executeQuery();
			while(rs.next()) {
				byte[] content = rs.getBytes("photo");
				response.setContentType("image/jpeg");
                response.setContentLength(content.length);
                response.getOutputStream().write(content);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
