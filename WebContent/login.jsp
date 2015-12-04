<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link type="text/css" rel="stylesheet" href="css/login-styles.css" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link href='http://fonts.googleapis.com/css?family=Oswald:300&subset=latin-ext' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Slabo+27px' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
<title>Login page</title>
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
	        		<li><a href="/IMDb-BILD-IT/signup.jsp">Sign up</a></li>
	        		<li><a href="#">Log in</a></li>
	        		<c:if test="${sessionUser != null}">
	        			<li><a href="#">Log Out</a></li>
	        		</c:if>
	      		</ul>
	      		<ul class="nav navbar-nav">
					<li><a href="#"></a></li>
	      		</ul>
	    	</div>
		</div>
	</nav> <!-- End of navigation bar -->
	
	<div class="form-container clearfix">
	
		<form action="LoginHandler" method="post">
			<h4>Enter Your User Name and Your Password</h4>
		    <div class="form-group">
				<input type="text" name="userName" id="userName" class="form-control" placeholder="User name:" />
			</div>
			<div id="separation-line"></div>
			<div class="form-group">
				<input type="password" name="password" id="pass" class="form-control" placeholder="Password:" />
			</div>
			<c:if test="${errorLoginMessage != null}">
				<div id="error-login">
					<c:out value="${errorLoginMessage}" />
				</div>
			</c:if>
			<div class="form-group">
				<button type="submit" class="btn btn-info btn-block" class="form-control"> Login </button>
			</div>
		</form>
		
	</div> <!-- End of form-container div -->

</body>
</html>