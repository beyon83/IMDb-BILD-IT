<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link type="text/css" rel="stylesheet" href="css/styles.css" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link href='http://fonts.googleapis.com/css?family=Oswald:300&subset=latin-ext' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Slabo+27px' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Movies</title>
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
	        		</c:if>
	        		<c:if test="${admin != null}">
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
	      		<ul class="nav">
					<c:if test="${sessionUser != null or admin != null}">
						<li><a id="logged-user" href="#"><img id="img-icon" src="img/user_avatar.png" /><c:out value="${sessionUser}${admin}" /></a></li>
					</c:if>
	      		</ul>
	    	</div>
		</div>
	</nav> <!-- End of navigation bar -->
	
	<c:if test="${message != null}">
		<div class="container" id="container" style="margin-bottom: 10px; padding-bottom: 8px; padding-top: 8px; background-color: #fff; box-shadow: 0px 5px 5px #888888;">
			<c:out value="${message}" /><img src="images/check.png" style="margin-left: 5px; margin-bottom: 2px;" /><br />
		</div>
	</c:if>
	
	<div class="container" style="padding-top: 20px;">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>#</th>
					<th>Movie Title</th>
					<th>Year</th>
					<th>Genre</th>
					<th>Rating</th>
					<th>Votes</th>
					<th>Cast</th>
					<th>Director</th>
					<th>Description</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${movies}" var="movie">
					<tr>
						<td><c:out value="${movie.rowNumber}." /></td>
						<td><a href="ShowMovie?id=${movie.id}"><c:out value="${movie.movieTitle}" /></a></td>
						<td><c:out value="${movie.year}" /></td>
						<td><c:out value="${movie.genre}" /></td>
						<td><c:out value="${movie.rating}" /></td>
						<td><c:out value="${movie.votes}" /></td>
						<td><c:out value="${movie.cast}" /></td>
						<td><c:out value="${movie.director}" /></td>
						<td><c:out value="${movie.description}" /></td>
					</tr>
				</c:forEach>	
			</tbody>
		</table>
		<ul class="pagination">
			<c:forEach items="${paginationList}" var="page">
				<c:if test="${page != currentPage}">
					<li><a href="FetchMovies?page=${page}"><c:out value="${page}" /></a></li>
				</c:if>
				<c:if test="${page == currentPage}">
					<li class="active"><a href="FetchMovies?page=${currentPage}"><c:out value="${currentPage}" /></a></li>
				</c:if>
			</c:forEach>
  		</ul>
	</div>
	<c:if test="${admin != null}">
		<div class="container" style="margin-top: 2px; padding-top: 8px;">
			<p>As a Administrator of this page, you can <a href="/IMDb-BILD-IT/upload.jsp"><strong>add</strong></a> new movies to the database.</p>
		</div>
	</c:if>
	
<script>
	
	$(document).ready(function() {
		$('#container').delay(2500).fadeOut(500);
	});
	
</script>

</body>
</html>