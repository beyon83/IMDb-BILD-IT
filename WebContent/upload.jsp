<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link type="text/css" rel="stylesheet" href="css/signup-styles.css" />
<link type="text/css" rel="stylesheet" href="css/styles.css" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="//code.jquery.com/jquery-1.9.1.js"></script>
<script src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
<script src="js/movieValidation.js"></script>
<link href='http://fonts.googleapis.com/css?family=Oswald:300&subset=latin-ext' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Slabo+27px' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
<title>Upload page</title>
</head>
<body>

	<!-- Bootstrap navigation bar  -->
	<nav class="navbar navbar-inverse" style="border-radius: 0px;">
		<div class="container-fluid">
	    	<div class="navbar-header">
	      		<a class="navbar-brand" style="color: #e3e3e3;" href="/IMDb-BILD-IT/index.jsp">MoviesDbApp</a>
	    	</div>
	    	<div>
	        	<ul class="nav navbar-nav">
	        		<!-- inside li: class="active" -->
	        		<li><a href="/IMDb-BILD-IT/FetchMovies">Movies list</a></li>
	        		<li><a href="#">Top Rated Movies</a></li>
	        		<c:if test="${sessionUser == null and admin == null}">
	        			<li><a href="/IMDb-BILD-IT/signup.jsp">Sign up</a></li>
	        			<li><a href="/IMDb-BILD-IT/login.jsp">Log in</a></li>
	        		</c:if>
	        		<c:if test="${sessionUser != null or admin != null}">
	        			<li><a href="Logout">Log Out</a></li>
	        			<li><a href="/IMDb-BILD-IT/upload.jsp">Upload Movie</a></li>
	        		</c:if>
	        		<li style="width: 100px;">
						<!-- Bootstrap Search form -->
						<form action="Search" method="get">
							<div class="row-search" style="margin-left: 300px; margin-top: 7px;">
						    	<div class="col-lg-6">
						        	<div class="input-group" style="width: 300px;">
						      			<input style="background-color: #444444;" type="text" name="query" class="form-control" placeholder="Search for...">
						      			<span class="input-group-btn">
						        			<a href="/IMDb-BILD-IT/Search?query=${query}"><button name="query" class="btn btn-default" type="button"><img src="images/search-icon.png" /></button></a>
						      			</span>
						    		</div><!-- /input-group -->
						   		</div><!-- /.col-lg-6 -->
						    </div><!-- /.row -->
					    </form>
				    </li>
	      		</ul>
	      		<ul class="nav navbar-nav">
					<li><a href="#"></a></li>
	      		</ul>
	      		<ul class="nav">
					<c:if test="${sessionUser != null or admin != null}">
						<li><a id="logged-user" href="#"><img id="img-icon" src="img/user_avatar.png" /><c:out value="${sessionUser}${admin}" /></a></li>
					</c:if>
	      		</ul>
	    	</div>
		</div>
	</nav> <!-- End of navigation bar -->
	
	<!-- Upload Form  -->
	<div class="form-container clearfix">
	<h4 id="h4-title"> Upload Movie Informations: </h4>
		<form method="post" action="UploadHandler" class="form-validation" enctype="multipart/form-data">
			<div class="form-wrapper">
				<div class="form-group" style="margin-bottom: 0px; position: relative; top: 10px;">
					<input type="text" id="movieTitle" name="movieTitle" class="form-control" placeholder="Movie title" required /><br />
				</div>
				<p id="response"></p> <!-- AJAX's username taken error message -->
				<div id="separation-line"></div>
				<div class="form-group" style="margin-bottom: 0px; position: relative; top: 10px;">
					<input type="number" name="year" class="form-control" placeholder="Year" required /><br />
				</div>
				<div id="separation-line"></div>
				<div class="form-group" style="margin-bottom: 0px; position: relative; top: 10px;">
					<input type="text" name="genre" class="form-control" placeholder="Genre" required /><br />
				</div>
				<div id="separation-line"></div>
				<div class="form-group" style="margin-bottom: 0px; position: relative; top: 10px;">
					<input type="text" name="description" class="form-control" placeholder="Description" required /><br />
				</div>
				<div id="separation-line"></div>
				<div class="form-group" style="margin-bottom: 0px; position: relative; top: 10px;">
					<input type="text" name="cast" class="form-control" placeholder="Cast" required /><br />
				</div>
				<div id="separation-line"></div>
				<div class="form-group" style="margin-bottom: 0px; position: relative; top: 10px;">
					<input type="text" name="director" class="form-control" placeholder="Director" required /><br />
				</div>
				<div id="separation-line"></div>
				<div class="form-group" style="margin-bottom: 0px; position: relative; top: 10px;">
					<label class="control-label">Select file:</label>
    				<input id="input-1" type="file" class="file" name="photo"><br />
				</div>
				<div id="separation-line"></div>
				<div class="form-group" style="margin-bottom: 10px;">
					<button type="submit" name="signup" class="btn btn-info btn-block" class="form-control"> Submit </button>
				</div>
			</div>		
		</form> <!-- End of Sign Up Form -->
	</div> <!-- End of the form-container class -->

</body>
</html>