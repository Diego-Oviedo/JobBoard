<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Applicants | My Profile - Edit</title>
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
					<li><a href="Applicant?action=applicant_resume&applicant_id=<c:out value="${applicant.applicantID}"/>">My Resume</a></li>
					<li><a href="Applicant?action=job_offers">Job Offers</a></li>
					<li><a href="Applicant?action=view_applications&applicant_id=<c:out value="${applicant.applicantID}"/>">My applications</a></li>
					<li><a href="Applicant?action=logout">Log out</a></li>
				</ul>
				
				<ul class="nav_greeting">
					<li>Welcome </li>
					<li><c:out value="${applicant.firstName}" /></li>
					<li> | </li>
					<li><c:out value="${applicant.applicantID}" /></li>
				</ul>

			</nav>
		</header>

<div class="main">
				
		<div class="inner_main">
			
			<form action="Applicant?action=my_profile_edit_submit&applicant_id=<c:out value="${applicant.applicantID}"/>" method="post" enctype="multipart/form-data">

			<div class="my_profile_picture">
					<input type="file" name="profile_picture">
					<img src="PicturesController?action=applicant_profile_picture&applicant_id=<c:out value="${applicant.applicantID}"/>"/>
			</div>

			<div class="my_profile_form">

					<label for="username">Username: </label>
					<input type="text" name="username" placeholder="username" maxlength="24" value="${applicant.username}">

					<br>

					<label for="password">Password: </label>	
					<input type="password" name="password" placeholder="new password" maxlength="24">

					<br>

					<label for="confirm_password">Confirm password: </label>
					<input type="password" name="confirm_password" placeholder="confirm new password" maxlength="24">

					<br>

					<label for="first_name">First name: </label>
					<input type="text" name="first_name" placeholder="first name" maxlength="24" value="${applicant.firstName}">

					<br>

					<label for="last_name">Last name: </label>
					<input type="text" name="last_name" placeholder="last name" maxlength="24" value="${applicant.lastName}">
					
					<br>
					
					<label for="profession">Profession: </label>
					<input type="text" name="profession" placeholder="profession" maxlength="24" value="${applicant.profession}">
					
					<div class="buttons_box">
					<input class="button"  type="submit" value="submit">
					</div>

				</form>
				
			</div>
				
				<br>
				
			<div class="address_info_form">
				
				<form action="Applicant?action=address_info_submit&applicant_id=<c:out value="${applicant.applicantID}"/>" method="post" enctype="multipart/form-data">	

					<label for="phone_number">Phone number: </label>
					<input type="text" name="phone_number" placeholder="phone number" maxlength="10" minlength="10" value="${applicant.phoneNumber}"">

					<br>

					<label for="email">E-mail: </label>
					<input type="email" name="email" placeholder="E-mail" value="${applicant.email}" maxlength="49">

					<br>

					<label for="street_address">Street address: </label>
					<input type="text" name="street_address" placeholder="street address" maxlength="49" value="${applicant.streetAddress}">

					<br>

					<label for="postal_code">Postal Code: </label>
					<input type="text" name="postal_code" placeholder="postal code" maxlength="6" value="${applicant.postalCode}" pattern="[A-Za-z1-9]{6}">
					<p class="module_info">Postal codes must be a combination between letters and numbers, with 6 characters in length. (K1K2J2)</p>
					

					<label for="city">City: </label>
					<input type="text" name="city" placeholder="city" maxlength="25" value="${applicant.city}">

					<br>

					<label for="province">Province: </label>
					<input type="text" name="province" placeholder="province" maxlength="25" value="${applicant.province}">

					<br>

					<label for="country">Country: </label>
					<input type="text" name="country" placeholder="country" maxlength="25" value="${applicant.country}">
						
					<div class="buttons_box">
					<input class="button"  type="submit" value="submit">
					</div>
					
					<br>
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
