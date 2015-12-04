<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link type="text/css" rel="stylesheet" href="css/signup-styles.css" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="//code.jquery.com/jquery-1.9.1.js"></script>
<script src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
<script src="js/validation.js"></script>
<link href='http://fonts.googleapis.com/css?family=Oswald:300&subset=latin-ext' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Slabo+27px' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
<title>Sign Up</title>

<script>
	$(document).ready(function() {

		$('#username').blur(function() {

			var username = $('#username').val();

			$.ajax({
				url: "CheckUsername", // Servlet name
				method: "POST", // method type
				data: { username: username }
			}).done(function(response) {
				$('#response').html(response); // html response
			});
			
		});
	});
</script>

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
	        		<li><a href="#">Sign up</a></li>
	        		<li><a href="/IMDb-BILD-IT/login.jsp">Log in</a></li>
	        		<c:if test="${sessionUser != null}">
	        			<li><a href="#">Log Out</a></li>
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
	    	</div>
		</div>
	</nav> <!-- End of navigation bar -->
	
	<!-- Sign Up Form  -->
	<div class="form-container clearfix">
	<h4 id="h4-title"> Sign Up: </h4>
		<form method="post" action="Controller" class="form-validation">
			<div class="form-wrapper">
				<div class="form-group" style="margin-bottom: 0px; position: relative; top: 10px;">
					<input type="text" id="username" name="username" class="form-control" placeholder="User name" required /><br />
				</div>
				<p id="response"></p> <!-- AJAX's username taken error message -->
				<div id="separation-line"></div>
				<div class="form-group" style="margin-bottom: 0px; position: relative; top: 10px;">
					<input type="text" name="firstName" class="form-control" placeholder="First name" required /><br />
				</div>
				<div id="separation-line"></div>
				<div class="form-group" style="margin-bottom: 0px; position: relative; top: 10px;">
					<input type="text" name="lastName" class="form-control" placeholder="Last name" required /><br />
				</div>
				<div id="separation-line"></div>
				<div class="form-group" style="margin-bottom: 0px; position: relative; top: 10px;">
					<input type="password" name="password" class="form-control" placeholder="Password" required /><br />
				</div>
				<div id="separation-line"></div>
				<div class="form-group" style="margin-bottom: 0px; position: relative; top: 10px;">
					<input type="text" name="birthDate" class="form-control" placeholder="Birth date" required /><br />
				</div>	
				<div id="separation-line"></div>
				<div class="form-group" style="margin-bottom: 0px; position: relative; top: 10px;">
					<input type="number" name="tel" class="form-control" placeholder="Tel. number" required /><br />
				</div>
				<div id="separation-line"></div>
				<div class="form-group" style="margin-bottom: 0px; position: relative; top: 10px;">	
					<input type="email" name="email" class="form-control" placeholder="Email" required /><br />
				</div>
				<div id="separation-line"></div>
				<div class="form-group" style="margin-bottom: 0px; position: relative; top: 10px;">	
					<input type="text" name="address" class="form-control" placeholder="Address" required /><br />
				</div>
				<div id="separation-line"></div>
				<div class="col-sm-9">
					<label>
						<input type="radio" checked="checked" name="radioGroup" value="Male" id="q128" />
						Male
					</label>
					<label>
						<input type="radio" name="radioGroup" value="Female" id="q129" />
						Female
					</label>
				</div>	
				<div class="form-group" style="margin-bottom: 10px;">
					<button type="submit" name="signup" class="btn btn-info btn-block" class="form-control"> Submit </button>
				</div>
			</div>		
		</form> <!-- End of Sign Up Form -->
	</div> <!-- End of the form-container class -->

</body>
</html>