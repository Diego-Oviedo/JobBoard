<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<title>Company | Applications</title>
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
		

		<div class="main_applicants">

			<div class="applicants">

					<div class="applications">
					<h1>Applications - <c:out value="${position.positionID}"/></h1>
							<br>
								<table>
									<thead>
										<th>APPLICATION ID</th>
										<th>APPLICANT ID</th>
										<th>PROFESSION</th>
										<th>CITY</th>
										<th>PROVINCE</th>
										<th>COUNTRY</th>
										<th>NAME</th>
										<th colspan="2">ACTION</th>
									</thead>
									<tbody>
									<c:forEach items="${applications}" var="application">
										<tr>
											<td><c:out value="${application.applicationNumber}"/></td>
											<td><c:out value="${application.applicant.applicantID}"/></td>
											<td><c:out value="${application.applicant.profession}"/></td>
											<td><c:out value="${application.position.company.city}"/></td>
											<td><c:out value="${application.position.company.province}"/></td>
											<td><c:out value="${application.position.company.country}"/></td>
											<td><c:out value="${application.applicant.firstName}"/> <c:out value="${application.applicant.lastName}"/></td>
											<td colspan="2"><a class="small_button" href="Company?action=applicant_profile_view&applicant_id=<c:out value="${application.applicant.applicantID}"/>">See profile </a></td>
										</tr>
									</c:forEach>	
									</tbody>
								</table>				
					</div>
			</div>
		</div>
</body>
</html>