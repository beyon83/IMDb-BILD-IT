<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/styles.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link href='http://fonts.googleapis.com/css?family=Oswald:300&subset=latin-ext' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Slabo+27px' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
<title>Search Results</title>
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
						        			<a href="/IMDb-BILD-IT/Search"><button class="btn btn-default" type="button"><img src="images/search-icon.png" /></button></a>
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

	<div class="container">
		<c:if test="${noResults == null}">
			<h3>Results found: <c:out value="${arraylist.size()}" /></h3>
			<table class="table table-striped">
				<thead>
					<tr>
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
			    	<c:choose>
				    	<c:when test="${arraylist.size() != 0}">
					    	<c:forEach items="${arraylist}" var="list">
								<tr>
									<td><a href="/IMDb-BILD-IT/ShowMovie?id=${list.id}"><c:out value="${list.movieTitle}" /></a></td>
									<td><c:out value="${list.year}" /></td>
									<td><c:out value="${list.genre}" /></td>
									<td><c:out value="${list.rating}" /></td>
								    <td><c:out value="${list.votes}" /></td>
									<td><c:out value="${list.cast}" /></td>
									<td><c:out value="${list.director}" /></td>
									<td><c:out value="${list.description}" /></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
			    			<p>No results found...</p>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</c:if>	
	<h4><c:out value="${noResults}" /></h4>
    </div>

</body>
</html>