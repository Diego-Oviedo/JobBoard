<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<title>Company | Register</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<link rel="stylesheet" type="text/css" href="companies_styles.css">
</head>
<body>
		<header>
			<nav class="nav_bar">

				<ul class="nav_buttons">
					<li><a href="Company_login.jsp">Log in</a></li>
					<li><a href="Home.jsp">Back</a></li>
				</ul>
				 
			</nav>
		</header>

		<div class="main">

			<div class="register_box">

				<h1>Company Register</h1>

					<div class="register_box_form">
						<form method="post" action="Company?action=create_profile" enctype="multipart/form-data">

							<label for="username">Username: </label>
							<input type="text" name="username" placeholder="username" maxlength="25" required>

							<br><br>

							<label for="password">Password: </label>
							<input type="password" name="password" placeholder="password" maxlength="25" required>

							<br><br>

							<label for="confirm_password">Confirm password: </label>
							<input type="password" name="confirm_password" placeholder="confirm password" maxlength="25" required>

							<br><br>

							<label for="employee_full_name">Employee full name: </label>
							<input type="text" name="employee_full_name" placeholder="full name" maxlength="25" required>

							<br><br>

							<label for="company_name">Company name: </label>
							<input type="text" name="company_name" placeholder="company name" maxlength="25" required>

							<br><br>

							<label for="phone_number">Phone number: </label>
							<input type="text" name="phone_number" placeholder="phone number" maxlength="25" required>

							<br><br>
							
							<label for="email">E-mail: </label>
							<input type="email" name="email" placeholder="E-mail" required>

							<br><br>

							<label for="postal_code">Postal Code: </label>
							<input type="text" name="postal_code" placeholder="postal code" maxlength="8" required>

							<br><br>

							<label for="city">City: </label>
							<input type="text" name="city" placeholder="city" maxlength="25" required>

							<br><br>

							<label for="province">Province: </label>
							<input type="text" name="province" placeholder="province" maxlength="25" required>

							<br><br>

							<label for="country">Country: </label>
							<input type="text" name="country" placeholder="country" maxlength="25" required>

							<br><br>

							<label for="company_logo">Company logo: </label>
							<input type="file" name="company_logo">

							<br><br>

							<div class="row-buttons">
							<a class="button" href="Company_login.jsp">Back</a>
							<input class="button"  type="submit" value="submit">
							</div>

						</form>
					</div>	

			</div>
			
		</div>
</body>
</html>