<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>Movie</title>
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
	
	<div class="container">
			<c:forEach items="${movie}" var="mov">
			<div id="movie-img">
				<img src="${pageContext.request.contextPath}/DisplayImage?id=${mov.id}" />
	<!-- 			<img src="/IMDb-BILD-IT/DisplayImage" /> -->
			</div>
			<div class="row">
	  			<div class="col-sm-9" style="margin-left: 15px;">
		   		 	<h2><c:out value="${mov.movieTitle}" /><br /></h2>
		    		<div class="row" id="row">
			      		<div class="col-xs-2 col-sm-2" style="width: 120px;">
			       			<label>Year:</label> <c:out value="${mov.year}" />
			      		</div>
			     		 <div class="col-xs-3 col-sm-3" style="width: 170px;">
			        		<label>Genre:</label> <c:out value="${mov.genre}" />
			      		</div>
			      		<div class="col-xs-2 col-sm-2" style="width: 120px;">
			       			<label>Rating:</label> <c:out value="${mov.rating}" />
			      		</div>
			      		<div class="col-xs-2 col-sm-2" style="width: 120px;">
			        		<label>Votes:</label> <c:out value="${mov.votes}" />
			      		</div>
		   		 	</div>
		   		 	<div class="row" id="row-description">
			      		<div class="col-xs-2 col-sm-2" style="width: 650px;">
			       			<c:out value="${mov.description}" />
			      		</div>
		   		 	</div>
		   		 	<div id="sep-line"></div>
		   		 	<div class="row" id="row-director">
			      		<div class="col-xs-6 col-sm-8" >
			       			<label>Cast: </label> <span><c:out value="${mov.cast}" /></span>
			      		</div>
			     		<div class="col-xs-6 col-sm-8" >
			        		<label>Director: </label> <span><c:out value="${mov.director}" /></span>
			      		</div>
		   		 	</div>
		   		 	<div class="row" id="row-rate">
			      		<div class="col-xs-6 col-sm-8" >
			       			<span id="vote">
			       				<c:choose>
			       					<c:when test="${sessionUser == null and admin == null}">
			       						<div data-toggle="tooltip" title="You have to login in order to rate this movie.">
			       							<form action="login.jsp">
			       								<label style="position: relative; bottom: 2px;">Rate this movie: </label>&nbsp;&nbsp;
		<!-- 			       					<a href="/IMDb-BILD-IT/login.jsp" data-toggle="tooltip" title="You have to login in order to rate this movie.">Vote</a> -->
					       						<input type="radio" name="rating" value="1" class="star" data-toggle="tooltip" title="1" disabled>
										        <input type="radio" name="rating" value="2" class="star" data-toggle="tooltip" title="2" disabled>
										        <input type="radio" name="rating" value="3" class="star" data-toggle="tooltip" title="3" disabled>
										        <input type="radio" name="rating" value="4" class="star" data-toggle="tooltip" title="4" disabled>
										        <input type="radio" name="rating" value="5" class="star" data-toggle="tooltip" title="5" disabled>
										        <input type="radio" name="rating" value="6" class="star" data-toggle="tooltip" title="6" disabled>
										        <input type="radio" name="rating" value="7" class="star" data-toggle="tooltip" title="7" disabled>
										        <input type="radio" name="rating" value="8" class="star" data-toggle="tooltip" title="8" disabled>
										        <input type="radio" name="rating" value="9" class="star" data-toggle="tooltip" title="9" disabled>
										        <input type="radio" name="rating" value="10" class="star" data-toggle="tooltip" title="10" disabled>
										   <%-- <a href="ShowMovie?id=${mov.id}&vote">Submit</a> --%>
										        <input type="submit" value="Submit"><br />
			       							</form>
			       						</div>
			       					</c:when>
			       					<c:otherwise>
			       						<form method="GET" action="ShowMovie">
			       							<label style="position: relative; bottom: 2px;">Rate this movie: </label>&nbsp;&nbsp;
			       							<input type="hidden" name="id" value="${mov.id}">
								            <input type="radio" name="rating" value="1" class="star" data-toggle="tooltip" title="1">
								            <input type="radio" name="rating" value="2" class="star" data-toggle="tooltip" title="2">
								            <input type="radio" name="rating" value="3" class="star" data-toggle="tooltip" title="3">
								            <input type="radio" name="rating" value="4" class="star" data-toggle="tooltip" title="4">
								            <input type="radio" name="rating" value="5" class="star" data-toggle="tooltip" title="5">
								            <input type="radio" name="rating" value="6" class="star" data-toggle="tooltip" title="6">
								            <input type="radio" name="rating" value="7" class="star" data-toggle="tooltip" title="7">
								            <input type="radio" name="rating" value="8" class="star" data-toggle="tooltip" title="8">
								            <input type="radio" name="rating" value="9" class="star" data-toggle="tooltip" title="9">
								            <input type="radio" name="rating" value="10" class="star" data-toggle="tooltip" title="10">
											<%-- <a href="ShowMovie?id=${mov.id}&vote">Submit</a> --%>
								            <input type="submit" value="Submit"><br />
								            <span><c:out value="${alreadyRated}" /></span>
								            <span><c:out value="${successfullyRated}" /></span>
								        </form>
			       					</c:otherwise>
			       				</c:choose>
			       			</span>
			      		</div>
		   		 	</div>
	  			</div>
			</div>
		</c:forEach>
	</div>

<script>

	$(document).ready(function() {
	    $('[data-toggle="tooltip"]').tooltip();
	});

</script>

</body>
</html>