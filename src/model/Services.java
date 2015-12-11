package model;

//import java.io.ByteArrayOutputStream;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
//import java.io.OutputStream;
//import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * @author Bojan Aleksic
 */
public class Services {
	
////////////////////////////////////////////////////////////////////////////////	
	
	/**
	 * Method for authenticate username and password
	 * @param userName
	 * @param password
	 * @param mysqlConnect
	 * @return
	 */
	public boolean authenticateUser(String userName, String password, Connection mysqlConnect) {
		
		/** Assign an sql query to the String variable */
		String sql = " SELECT `userName`, `password` FROM `users` ";
		
		/** Create PreparedStatement and ResultSet and pass sql query to the PreparedStatement object */
		try(PreparedStatement prepStat = mysqlConnect.prepareStatement(sql);
			ResultSet rs = prepStat.executeQuery();) {
			while(rs.next()) {
				/** Compare input's username and password with username and password in database */
				if((userName.equals(rs.getString("userName"))) && (password.equals(rs.getString("password")))) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////	
	
	/**
	 * Check if logged user is administrator
	 * @param userName
	 * @param mysqlConnect
	 * @return
	 */
	public boolean isAdmin(String userName, Connection mysqlConnect) {
		
		/** Assign an sql query to the String variable */
		String sqlSelect = " SELECT `isAdmin` FROM `users` WHERE `userName` = '" + userName + "' ";
		
		/** Create PreparedStatement and ResultSet and pass sql query to the PreparedStatement object */
		try(PreparedStatement prepStat = mysqlConnect.prepareStatement(sqlSelect);
			ResultSet rs = prepStat.executeQuery();) {
			while(rs.next()) {
				String isAdmin = rs.getString("isAdmin");
				/** Check if logged user is administrator */
				if(isAdmin.equals("true")) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////	
	
	/**
	 * User Sign-Up
	 * @param userName
	 * @param firstName
	 * @param lastName
	 * @param password
	 * @param gender
	 * @param registrationDate
	 * @param email
	 * @param mysqlConnect
	 */
	public void signUp(String userName, String firstName, String lastName, String password, String gender, String registrationDate, String email, Connection mysqlConnect) {
		
		/** Declare String sql query for prepared statement */
		String sqlQuery = " INSERT INTO `users` "
						+ " (`userName`, `firstName`, `lastName`, `password`, `gender`, `registrationDate`, `email`) "
						+ " VALUES (?, ?, ?, ?, ?, ?, ?) ";
		
		/** Instantiate PreparedStatement object and pass sql query to it */
		try(PreparedStatement prepStatement = mysqlConnect.prepareStatement(sqlQuery);) {
			/** Insert data to the fields in the database */
			prepStatement.setString(1, userName);
			prepStatement.setString(2, firstName);
			prepStatement.setString(3, lastName);
			prepStatement.setString(4, password);
			prepStatement.setString(5, gender);
			prepStatement.setString(6, registrationDate);
			prepStatement.setString(7, email);
			prepStatement.executeUpdate(); // execute prepared statement
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
///////////////////////////////////////////////////////////////////////////////////////////	
	
	/**
	 * Check if username is already taken
	 * @param userName
	 * @param mysqlConnect
	 * @return
	 */
	public boolean isUsernameTaken(String userName, Connection mysqlConnect) {
		
		/** Declare String with sql query */
		String sqlQuery = " SELECT `userName` FROM `users` ";
		
		/** Declare ResultSet object */
		ResultSet rs = null;
		
		/** Instantiate PreparedStatement and pass sqlQuery to it */
		try(PreparedStatement prepStatement = mysqlConnect.prepareStatement(sqlQuery);) {
			rs = prepStatement.executeQuery(); // execute PreparedStatement
			while(rs.next()) {
				/** Assign userName from the database to the String */
				String name = rs.getString("userName");
				/** Check if passed userName matches username from the database */
				if(userName.equals(name)) {
					return true; // if so, return true, user already exists in the database
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // otherwise return false, user does not exist in the database.
	}
	
///////////////////////////////////////////////////////////////////////////////////////////	
	
	/**
	 * Method for uploading movie data (title, year, genre, description and movie photo)
	 * @param movieTitle
	 * @param movieYear
	 * @param genre
	 * @param description
	 * @param cast
	 * @param director
	 * @param inputStream
	 * @param mysqlConnect
	 */
	public void uploadMovie(String movieTitle, int movieYear, String genre, String description, String cast, String director, InputStream inputStream, Connection mysqlConnect) {
		
		/** Sql query for inserting data into database */
		String sqlQuery = " INSERT INTO `movies`(`movieTitle`, `year`, `genre`, `description`, `cast`, `director`, `photo`) "
				        + " VALUES(?, ?, ?, ?, ?, ?, ?) ";
		
		/** Create PreparedStatement for inserting data into database */
		try(PreparedStatement prepStatement = mysqlConnect.prepareStatement(sqlQuery);) {
			/** Set data */
			prepStatement.setString(1, movieTitle);
			prepStatement.setInt(2, movieYear);
			prepStatement.setString(3, genre);
			prepStatement.setString(4, description);
			prepStatement.setString(5, cast);
			prepStatement.setString(6, director);
			prepStatement.setBlob(7, inputStream);
			prepStatement.executeUpdate(); // execute update
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
///////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Simple algorithm for displaying max 5 page links in pagination
	 * @param page
	 * @return
	 */
	public ArrayList<Integer> paginationList(int page) {
		
		ArrayList<Integer> list = new ArrayList<>();
		
		if(page == 1 || page == 2) {
			if(Movie.getNumberOfPages() <= 5) {
				for(int i = 1; i <= Movie.getNumberOfPages(); i++) {
					list.add(i);
				}
			} else if(Movie.getNumberOfPages() > 5) {
				for(int i = 1; i <= 5; i++) {
					list.add(i);
				}
			}
		} else if(page > 2 && page < Movie.getNumberOfPages()-1) { 
			for(int i = page - 2; i <= page + 2; i++) {
				list.add(i);
			}
		} else if((page + 2) > Movie.getNumberOfPages() || (page + 2) == Movie.getNumberOfPages()) {
			list.clear();
			for(int i = page - 2; i <= Movie.getNumberOfPages(); i++) {
				list.add(i);
			}
		}
		return list;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////	
	
	/**
	 * Obtain list of all movies from the database
	 * @param row
	 * @param mysqlConnect
	 * @return
	 * @throws IOException
	 */
	public ArrayList<Movie> getMovies(int row, Connection mysqlConnect) throws IOException {
		
		/** Create ArrayList for movies */
		ArrayList<Movie> moviesList = new ArrayList<>();
		
		/** Pagination set limit to 15 per page */
		int rowsPerPage = 15;
		row = row * rowsPerPage; // get starting row depending on which page is clicked on
		
		/** Row numbers */
		int rowNumber = 1;
		if(row != 0) {
			rowNumber = row + 1;
		}
		
		/** Declare String with sql select syntax, set limit to 15 records per page */
		String sqlQuery = " SELECT * FROM `movies` LIMIT  " + row + ", " + rowsPerPage + " " ;
		
		String sqlCount = " SELECT COUNT(*) FROM `movies` ";
		
		ResultSet rs = null; // ResultSet object
		
		/** Initialize PreparedStatement and pass sql string to it */
		try(PreparedStatement prepStatement = mysqlConnect.prepareStatement(sqlQuery)) {
			rs = prepStatement.executeQuery(); // execute PreparedStatement
			while(rs.next()) { // while result set contains data
				int id = rs.getInt("movieID");
				String movieTitle = rs.getString("movieTitle"); // Assign movieTitle from the DB to the string variable
				if(movieTitle.length() > 25) {
					movieTitle = movieTitle.substring(0, 25) + " ...";
				}
				String titleTrimmed = movieTitle.replaceAll("\\s", ""); // Remove empty spaces between words
				int year = rs.getInt("year"); // Assign year from the DB to the int year variable
				String genre = rs.getString("genre"); // Do same for rest 
				if(genre.length() > 15) {
					genre = genre.substring(0, 15) + " ...";
				}
				
				double rating = rs.getDouble("rating"); // Obtain rating from the DB
				DecimalFormat decFormat = new DecimalFormat("#.00"); // Format double value into two decimal places
				rating = Double.valueOf(decFormat.format(rating)); // parse back decimal format object into double 
				
				int votes = rs.getInt("votes");
				String cast = rs.getString("cast");
				if(cast.length() > 20) { // If length of the "cast" exceeds 20 characters...
					cast = cast.substring(0, 20) + " ..."; // ...show only first 25 characters
				}
				String director = rs.getString("director");
				if(director.length() > 25) {
					director = director.substring(0, 25) + " ...";
				}
				String description = rs.getString("description"); 
				if(description.length() > 25) { // If length of the "description" exceeds 25 chars...
					description = description.substring(0, 25) + " ..."; // ...show first 25 chars
				}
				
				/** Initialize Movie constructor, and pass data from the database */
				Movie movie = new Movie(id, rowNumber, movieTitle, year, genre, rating, votes, cast, director, description);
				moviesList.add(movie); // add data to the ArrayList
				movie.setTitleTrimmed(titleTrimmed);
				
				rowNumber++;
				
//				Blob imageBlob = rs.getBlob("photo"); // get image using Blob object
//				InputStream binaryStream = imageBlob.getBinaryStream(); // Retrieve binary data of the image blob using getBinaryStream()
//				ByteArrayOutputStream outByteArray = new ByteArrayOutputStream();
//				/** Saving path of the file on your computer (c:/users/beyon/images) */
//				OutputStream outputStream = new FileOutputStream(System.getProperty("user.home") + "/images/" + id + ".jpg");
//				
//				/** Get length of the image */
//				int length = (int) imageBlob.length();
//			 
//				/** Create byte[] array */
//			    byte[] buffer = new byte[1024];
//			     
//			    System.out.println("write to has been invoked.");
//			    while ((length = binaryStream.read(buffer)) != -1) {
////			    	System.out.println("writing " + length + " bytes");
//			    	outByteArray.write(buffer, 0, length);   
//			    }
//			    outByteArray.writeTo(outputStream); // Write image content from ByteArrayOutputStream to the OutputStream
			}
			rs.close();
			
			/** Obtain number of records from the movies table */
			rs = prepStatement.executeQuery(sqlCount);
			rs.next();
			int rowsCount = rs.getInt(1); // get count
			
			/** Get number of pages for pagination */
			double numbOfPages = (double) rowsCount / rowsPerPage; // divide count by 10, to get decimal number
			int pages = (int) Math.ceil(numbOfPages); // rounding up to the next whole number
			Movie.setNumberOfPages(pages); // set number of pages 
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return moviesList; // return ArrayList of movies
		
	}
	
///////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Display movie
	 * @param movieId
	 * @param mysqlConnect
	 * @return
	 */
	public Movie[] showMovie(int movieId, Connection mysqlConnect) {
		
		Movie[] getMovie = new Movie[1];
		
		String sqlQuery = " SELECT * FROM `movies` WHERE `movieID` = '" + movieId + "' ";
		
		ResultSet rs = null;
		
		try(PreparedStatement prepStatement = mysqlConnect.prepareStatement(sqlQuery)) {
			rs = prepStatement.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("movieID");
				String movieTitle = rs.getString("movieTitle"); // Assign movieTitle from the DB to the string variable
				int year = rs.getInt("year"); // Assign year from the DB to the int year variable
				String genre = rs.getString("genre"); // Do same for rest 
				
				double rating = rs.getDouble("rating");
				DecimalFormat decFormat = new DecimalFormat("#.00");
				rating = Double.valueOf(decFormat.format(rating));
				
				int votes = rs.getInt("votes");
				String cast = rs.getString("cast");
				String director = rs.getString("director");
				String description = rs.getString("description"); 
				getMovie[0] = new Movie(id, movieTitle, year, genre, rating, votes, cast, director, description);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getMovie;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Check if user is already rated current movie
	 * @param userId
	 * @param movieId
	 * @param mysqlConnect
	 * @return
	 */
	public boolean isAlreadyVoted(int userId, int movieId, Connection mysqlConnect) {
		
		String sqlSelect = " SELECT * FROM `user_votes` ";
		
		ResultSet rs = null;
		
		try(PreparedStatement statementSelect = mysqlConnect.prepareStatement(sqlSelect)) {
			rs = statementSelect.executeQuery();
			while(rs.next()) {
				int idUser = rs.getInt("userID");
				int idMovie = rs.getInt("movieID");
				if((idUser == userId) && (idMovie == movieId)) {
					System.out.println("You already submitted rate for this movie!!!");
					return true;
				} 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Rate movie
	 * @param userId
	 * @param movieId
	 * @param rating
	 * @param mysqlConnect
	 */
	public void vote(int userId, int movieId, int rating, Connection mysqlConnect) {
		
		String sqlInsert = " INSERT INTO `user_votes`(`userID`, `movieID`, `rating`) VALUES(?, ?, ?) ";
		
		try(PreparedStatement statementInsert = mysqlConnect.prepareStatement(sqlInsert)) {
			statementInsert.setInt(1, userId);
			statementInsert.setInt(2, movieId);
			statementInsert.setInt(3, rating);
			statementInsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
///////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Retrieve user's rating for current movie
	 * @param userId
	 * @param movieId
	 * @param mysqlConnect
	 * @return
	 */
	public int getUsersRating(int userId, int movieId, Connection mysqlConnect) {
		
		int yourRating = 0;
		
		String sqlSelect = " SELECT * FROM `user_votes` ";
		
		ResultSet rs = null;
		
		try(PreparedStatement statementSelect = mysqlConnect.prepareStatement(sqlSelect)) {
			rs = statementSelect.executeQuery();
			while(rs.next()) {
				int idUser = rs.getInt("userID");
				int idMovie = rs.getInt("movieID");
				if((idUser == userId) && (idMovie == movieId)) {
					yourRating = rs.getInt("rating");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return yourRating;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Update ratings
	 * @param movieId
	 * @param ratingNumber
	 * @param mysqlConnect
	 */
	public void updateVotes(int movieId, int ratingNumber, Connection mysqlConnect) {
		
		String sqlUpdate = " UPDATE `movies` SET `votes` = `votes` + 1, `overallPoints` = `overallPoints` + '" + ratingNumber + "', "
				         + " `rating` = `overallPoints` / `votes` "
						 + " WHERE `movieID` = '" + movieId + "' "; 
		
		try(PreparedStatement statementUpdate = mysqlConnect.prepareStatement(sqlUpdate)) {
			statementUpdate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
///////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Return logged user's ID
	 * @param userName
	 * @param mysqlConnect
	 * @return
	 */
	public int getUserId(String userName, Connection mysqlConnect) {
		
		int userId = 0;
		
		String sqlSelect = " SELECT `userID` FROM `users` WHERE `userName` = '" + userName + "' ";
		
		ResultSet rs = null;
		
		try(PreparedStatement prepStatement = mysqlConnect.prepareStatement(sqlSelect)) {
			rs = prepStatement.executeQuery();
			while(rs.next()) {
				userId = rs.getInt("userID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userId;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Get Search Results
	 * @param query
	 * @param mysqlConnect
	 * @return
	 */
	public ArrayList<Movie> searchResults(String query, Connection mysqlConnect) {
		
		ArrayList<Movie> listResults = new ArrayList<>();
		
		ResultSet rs = null;
		
		String sqlSelect = " SELECT * FROM `movies` ";
		
		try(PreparedStatement prepStat = mysqlConnect.prepareStatement(sqlSelect)) {
			
			rs = prepStat.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("movieID");
				String movieTitle = rs.getString("movieTitle");
				if(movieTitle.length() > 25) {
					movieTitle = movieTitle.substring(0, 25) + " ...";
				}
				int year = rs.getInt("year");
				String genre = rs.getString("genre");
				if(genre.length() > 15) {
					genre = genre.substring(0, 15) + " ...";
				}
				double rating = rs.getDouble("rating");
				int votes = rs.getInt("votes");
				String cast = rs.getString("cast");
//				String castSearch = cast; // Copy cast into castSearch for searching by cast members 
				if(cast.length() > 25) { // If length of the "cast" exceeds 25 characters...
					cast = cast.substring(0, 25) + " ..."; // ...show only first 25 characters
				}
				String director = rs.getString("director");
				if(director.length() > 25) {
					director = director.substring(0, 25) + " ...";
				}
				String description = rs.getString("description");
				if(description.length() > 25) { // If length of the "description" exceeds 25 chars...
					description = description.substring(0, 25) + " ..."; // ...show first 25 chars
				}
				
				Movie getResult = new Movie(id, movieTitle, year, genre, rating, votes, cast, director, description);
				
				/** If length of the query is only one letter */
				if(query.length() < 2) {
					/** Check if only first letter matches in movie title */
					if(movieTitle.matches("(?i:" + query + ".*)")) {
						listResults.add(getResult);
					}
				} else { // otherwise, match query before and after query
					/** Use "(?i:X)" - case insensitive pattern to ignore case letters */
					if(movieTitle.matches("(?i:.*" + query + ".*)")) {
						listResults.add(getResult);
					}
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return listResults;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////
	
}
