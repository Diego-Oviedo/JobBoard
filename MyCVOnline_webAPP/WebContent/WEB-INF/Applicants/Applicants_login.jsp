<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Applicants | Home </title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="styles_applicants.css">
	<script src="navbar.js"></script>

</head>
<body>

	<header>
	<div class="mobile_header" >
	
	<div class="greater_1024" ><img src="Logo>1024.png"></div>
	<div class="less_1024" ><img src="Logo<1024.png"></div>
	
	<button onclick="dropDownToggle()" class="dropbtn">☰</button>
	
	</div>
	
			<nav class="nav_bar" id="DropdownContent">
				<ul class="nav_buttons" >				
					<li><a href="Home.jsp">Home</a></li>
					<li><a href="Applicant?action=applicants_home">Job Offers</a></li>
					<li><a href="Applicants_Register.jsp">Register</a></li>	
					
				</ul>
				
				<ul class="nav_greeting">
					<li> </li>
				</ul>

			</nav>
		</header>

<div class="main">
				
		<div class="login_main">
					

				<div class="login_inside_box">
						
						<div class="title_container">Log-in</div>

					<form accept="" autocomplete="on" method="post" action="Applicant?action=login" class="login_box_form">

						<label for="username">Username:</label>
						<input type="text" name="username" maxlength="25" required>
						
						<br>
							
						<label for="password">Password:</label>
						<input type="password" name="password" maxlength="25" required>


						<div class="login_buttons">
						<input class="button_login"  type="submit" value="Submit">
						<a class="button_login" href="Applicants_Register.jsp">Register</a>
						</div>

					</form>

				</div>


		</div>

</div>

<footer class="footer">
	<p>	
	The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.</p>

</footer>

</body>
</html>