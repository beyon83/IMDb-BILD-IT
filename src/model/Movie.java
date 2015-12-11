package model;

import java.io.InputStream;
import java.sql.Blob;

@SuppressWarnings("unused")
public class Movie {
	
	private int id;
	private int rowNumber;
	private static int numberOfPages;
	private String movieTitle;
	private int year;
	private String genre;
	private double rating;
	private int votes;
	private String cast;
	private String director;
	private String description;
	private InputStream photo;
	
	private String titleTrimmed;
	
	public Movie() {
		
	}
	
	public Movie(int id, int rowNumber, String movieTitle, int year, String genre, double rating, int votes, String cast, String director, String description) {
		this.id = id;
		this.rowNumber = rowNumber;
		this.movieTitle = movieTitle;
		this.year = year;
		this.genre = genre;
		this.rating = rating;
		this.votes = votes;
		this.cast = cast;
		this.director = director;
		this.description = description;
	}
	
	public Movie(int id, String movieTitle, int year, String genre, double rating, int votes, String cast, String director, String description) {
		this.id = id;
		this.movieTitle = movieTitle;
		this.year = year;
		this.genre = genre;
		this.rating = rating;
		this.votes = votes;
		this.cast = cast;
		this.director = director;
		this.description = description;
	}
	
	public Movie(String movieTitle, String cast, String director) {
		this.movieTitle = movieTitle;
		this.cast = cast;
		this.director = director;
	}
	
	public Movie(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public static int getNumberOfPages() {
		return numberOfPages;
	}

	public static void setNumberOfPages(int numberOfPages) {
		Movie.numberOfPages = numberOfPages;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}
	
	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public InputStream getPhoto() {
		return photo;
	}

	public void setPhoto(InputStream photo) {
		this.photo = photo;
	}

	public String getTitleTrimmed() {
		return titleTrimmed.replaceAll("\\s", "");
	}

	public void setTitleTrimmed(String titleTrimmed) {
		this.titleTrimmed = titleTrimmed;
	}

}
