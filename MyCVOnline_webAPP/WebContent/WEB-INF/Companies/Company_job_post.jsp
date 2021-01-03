<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<title>Company | Job post</title>
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

			<div class="job_post">

				<h1>Job post - <c:out value="${position.positionID}"/></h1>
					
					<div class="job_post_section_1">

						<h2><c:out value="${position.jobTitle}"/></h2>

						<table>
							<tr>
								<td><img alt="" src="PicturesController?action=job_offer_logo&company_id=<c:out value="${position.company.companyID}"/>" width="100"/><br><c:out value="${position.company.companyName}" /></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td><strong>Post ID:</strong> <c:out value="${position.positionID}"/><br><strong>Domain:</strong> <c:out value="${position.jobDomain}"/></td>
							</tr>
							<tr>
								<td><c:out value="${position.company.city}"/>, <c:out value="${position.company.country}"/></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
								<tr>
								<td colspan="5"><strong>Description:</strong> <br><c:out value="${position.jobDescription}"/></td>
							</tr>
							<tr>
								<td>&nbsp</td>
							</tr>
							<tr>
								<td><strong>Type of job:</strong> <c:out value="${position.typeOfJob}" /></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td><strong>Availability:</strong> <c:out value="${position.availability}" /></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td><strong>Salary:</strong> <c:out value="${position.offerSalary}" /></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td><strong>Additional<br>compensations:</strong><br><c:out value="${position.additionalCompensation}" /></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</table>
					</div>

					<div class="job_post_section_2">
							<h2>Requirements</h2>
							
							<h3>Qualifications</h3>

								<table>
									<c:forEach items="${qualifications}" var="qualifications">
										<tr>
											<td><c:out value="${qualifications.qualificationName}"/></td>
										</tr>
										<tr>
											<td><c:out value="${qualifications.desiredYears}"/></td>
										</tr>
										<tr>
											<td colspan="4"><c:out value="${qualifications.qualificationDescription}"/></td>
										</tr>
										<tr>
											<td>&nbsp</td>
										</tr>
									</c:forEach>
								</table>


							<h3>Experience</h3>

								<table>
									<c:forEach items="${experiences}" var="experience">
										<tr>
											<td><c:out value="${experience.experienceName}"/></td>
										</tr>
										<tr>
											<td><c:out value="${experience.desiredYears}"/></td>
										</tr>
										<tr>
											<td colspan="4"><c:out value="${experience.experienceDescription}"/></td>
										</tr>
										<tr>
											<td>&nbsp</td>
										</tr>
									</c:forEach>
								</table>
					</div>	

					<div class="job_post_section_3">
						Posted by: <c:out value="${company_employee.fullName}" />
						<br><br>
					</div>	

					<div class="job_post_section_4">
						<h2>About company</h2>
						
						<c:out value="${position.company.companyName}"/> | <c:out value="${position.company.companyID}"/>
						<br>
						<img alt="" src="PicturesController?action=company_logo&company_id=<c:out value="${position.company.companyID}"/>" width="100"/>
						<br>
						<strong>Phone number:</strong> <c:out value="${position.company.phoneNumber}"/>
						<br>
						<strong>E-mail:</strong> <c:out value="${position.company.email}"/>
						<br>
						<i><c:out value="${position.company.city}"/>, <c:out value="${position.company.postalCode}"/>
						<br>
						<c:out value="${position.company.province}"/>, <c:out value="${position.company.country}"/></i>
					</div>

				<div class="row-buttons">
					<a class="button" href="Company?action=jobPost_edit_view&company_id=<c:out value="${company.companyID}"/>&username=<c:out value="${employee.username}"/>&position_id=<c:out value="${position.positionID}"/>">Edit</a>
					<a class="button" href="Company?action=jobPost_delete&company_id=<c:out value="${company.companyID}"/>&username=<c:out value="${employee.username}"/>&position_id=<c:out value="${position.positionID}"/>">Delete</a>
					<a class="button" href="Company?action=jobPosts_view&company_id=<c:out value="${company.companyID}"/>&username=<c:out value="${employee.username}"/>">Back</a>
				</div>

			</div>

	</div>
</body>
</html>