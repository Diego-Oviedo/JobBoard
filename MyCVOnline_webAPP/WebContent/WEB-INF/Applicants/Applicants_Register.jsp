<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Applicants | Register </title>
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
					<li><a href="Applicants_login.jsp">Log in</a></li>
					<li><a href="Applicants_Register.jsp">Register</a></li>
					<li><a href="Applicant?action=applicants_home">Job Offers</a></li>
					<li><a href="Home.jsp">Back</a></li>
				</ul>
				
				<ul class="nav_greeting">
					<li> </li>
				</ul>

			</nav>
		</header>

<div class="main">
				
		<div class="inner_main">
					
				<div class="title_container">Applicant Register</div>

				<div class="register_box">

						<form accept="" autocomplete="on" method="post" action="Applicant?action=create_applicant" class="register_box_form" enctype="multipart/form-data">

							<label for="username">Username: </label>
							<input type="text" name="username" placeholder="username" maxlength="25" required>
							
							<br>

							<label for="password">Password: </label>
							<input type="password" name="password" placeholder="password" maxlength="25" required>
							
							<br>

							<label for="confirm_password">Confirm password: </label>
							<input type="password" name="confirm_password" placeholder="confirm password" maxlength="25" required>
							
							<br>

							<label for="first_name">First name: </label>
							<input type="text" name="first_name" placeholder="first name" maxlength="25" required>
							
							<br>

							<label for="last_name">Last name: </label>
							<input type="text" name="last_name" placeholder="last name" maxlength="25" required>
							
							<br>

							<label for="profession">Profession: </label>
							<input type="text" name="profession" placeholder="profession" maxlength="25" required>
														
							<br>

							<label for="phone_number">Phone number: </label>
							<input type="text" name="phone_number" placeholder="phone number" maxlength="10" required>
														
							<br>

							<label for="email">E-mail: </label>
							<input type="email" name="email" placeholder="E-mail" required>
														
							<br>

							<label for="street_address">Street address: </label>
							<input type="text" name="street_address" placeholder="street address" maxlength="25" required>
														
							<br>

							<label for="postal_code">Postal Code: </label>
							<input type="text" name="postal_code" placeholder="postal code" maxlength="7" required>
														
							<br>

							<label for="city">City: </label>
							<input type="text" name="city" placeholder="city" maxlength="25" required>
														
							<br>

							<label for="province">Province: </label>
							<input type="text" name="province" placeholder="province" maxlength="25" required>
														
							<br>

							<label for="country">Country: </label>
							<input type="text" name="country" placeholder="country" maxlength="25" required>
							
							<div class="buttons_box">
						    <a class="button" href="Applicant?action=applicants_home">Back</a>
							<input class="button"  type="submit" value="submit">
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