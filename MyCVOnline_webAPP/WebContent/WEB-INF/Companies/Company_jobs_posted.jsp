<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<title>Company | Jobs posted</title>
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
		

		<div class="main_jobs">
			
			<div class="jobs_posted">
				
				<h1>Jobs posted</h1>

					<div class="jobs_posted_table">
	
						<table>
							<thead>
								<th>POSITION ID</th>
								<th>JOB TITLE</th>
								<th>DOMAIN</th>
								<th>TYPE OF JOB</th>
								<th></th>
								<th></th>
							</thead>
							<tbody>
								<c:forEach items="${positions}" var="position">
									<tr>
										<td><c:out value="${position.positionID}"/></td>
										<td><c:out value="${position.jobTitle}"/></td>
										<td><c:out value="${position.jobDomain}"/></td>
										<td><c:out value="${position.typeOfJob}"/></td>
										<td><a class="table_button" href="Company?action=jobPost_view&position_id=<c:out value="${position.positionID}"/>">See post </a></td>
										<td><a class="table_button" href="Company?action=applications_per_post_view&position_id=<c:out value="${position.positionID}"/>"> Applicants applied</a></td>
									</tr>
								</c:forEach>	
							</tbody>
						</table>

					</div>	

			</div>

		</div>

</body>
</html>