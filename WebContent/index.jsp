<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html">
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link type="text/css" rel="stylesheet" href="css/styles.css" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link href='http://fonts.googleapis.com/css?family=Oswald:300&subset=latin-ext' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Slabo+27px' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home page</title>
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

	<c:out value="${message}" />

	<div class="container">
		<c:choose>
	  		<c:when test="${sessionUser != null or admin != null}">
	  			<c:redirect url="/FetchMovies"/>
		   	</c:when>
			<c:otherwise>
				<h4>Welcome Guest!</h4>
				<h5>Please <a href="/IMDb-BILD-IT/signup.jsp">signup</a> if you don't have account, or <a href="/IMDb-BILD-IT/login.jsp">login</a> if you are already registered.</h5>
			</c:otherwise>
		</c:choose>
    </div>

</body>
</html>