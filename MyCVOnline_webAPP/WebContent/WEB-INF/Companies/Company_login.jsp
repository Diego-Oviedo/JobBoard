<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<title>Company | Login </title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<link rel="stylesheet" type="text/css" href="companies_styles.css">
</head>
<body>
		<header>
			<nav class="nav_bar">

				<ul class="nav_buttons">
					<li><a href="Home.jsp">Home</a></li>
					<li><a href="Company_register.jsp">Register</a></li>	
				</ul>
				
			</nav>
		</header>
		
		<div class="login_box">

				<div class="login_inside_box">
					<h1>Log-in</h1>

				<form accept="" autocomplete="on" method="post" action="Company?action=login">

					<label for="username">Username</label>
					<input type="text" name="username" maxlength="25" required>


					<label for="password">Password</label>
					<input type="password" name="password" maxlength="25" required>

					<div class="row-buttons">
					<a class="button" href="Company_register.jsp">Register</a>
					<input class="button"  type="submit" value="submit">
					</div>

				</form>

			</div>
			
		</div>
		
</body>
</html>