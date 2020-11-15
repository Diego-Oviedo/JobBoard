<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<title>Company | My profile </title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<link rel="stylesheet" type="text/css" href="companies_styles.css">
</head>
<body>
		<header>
			<nav class="nav_bar">

				<ul class="nav_buttons">
					<li><a href="Company?action=jobPosts_view&company_id=<c:out value="${company.companyID}"/>&username=<c:out value="${employee.username}"/>">Jobs posted</a></li>
					<li><a href="Company?action=jobPost_create_view&company_id=<c:out value="${company.companyID}"/>&username=<c:out value="${employee.username}"/>">Create a Job post</a></li>
					<li><a href="Company?action=applications_view&company_id=<c:out value="${company.companyID}"/>&username=<c:out value="${employee.username}"/>">Applications</a></li>
					<li><a href="Company?action=my_profile&company_id=<c:out value="${company.companyID}"/>&username=<c:out value="${employee.username}"/>">My profile</a></li>	
					<li><a href="Company?action=logout">Log out</a></li>
				</ul>

				<ul class="nav_greeting">
					<li>Welcome </li>
					<li><c:out value="${employee.fullName}"/></li>
					<li> | </li>
					<li>Company ID: <c:out value="${company.companyID}"/></li>
				</ul>

			</nav>
		</header>
		

		<div class="main">
			
			<div class="company_profile">
				<h1>My profile</h1>
					
					<div class="company_profile_display">
						<label>Username:</label> <c:out value="${employee.username}"/>
						
						<br>
						
						<label>Password:</label> ********
						
						<br>
						
						<label>Employee name:</label> <c:out value="${employee.fullName}"/>
						
						<br><br>
						
						<label>Company name:</label> <c:out value="${company.companyName}"/>
						
						<br>
						
						<label>Phone number:</label> <c:out value="${company.phoneNumber}"/>
						
						<br>
						
						<label>E-mail:</label> <c:out value="${company.email}"/>
						
						<br><br>
						
						<label>City:</label> <c:out value="${company.city}"/>
						
						<br>
						
						<label>Postal code:</label> <c:out value="${company.postalCode}"/>
						
						<br>
						
						<label>Province:</label> <c:out value="${company.province}"/>
						
						<br>
						
						<label>Country:</label> <c:out value="${company.country}"/>
						
						<br>
						
						<label>Company logo:</label> <img alt="" src="PicturesController?action=company_logo&company_id=<c:out value="${company.companyID}"/>" width="100"/>

						<br><br>

						<div class="row-buttons">
						<a class="button" href="Company?action=my_profile_edit&company_id=<c:out value="${company.companyID}"/>&username=<c:out value="${employee.username}"/>">Edit</a>
						</div>

					</div>

			</div>

		</div>
</body>
</html>