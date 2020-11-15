<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<title>Company | Job post - Edit - Qualifications</title>
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
			
			<div class="job_job_post">
				
				<h1>Job post [Qualifications] - <c:out value="${position.positionID}"/></h1>

						<div class="edit_qualifications">
							
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
												<td><a class="button" href="Company?action=jobPost_qualifications_delete&company_id=<c:out value="${company.companyID}"/>&username=<c:out value="${employee.username}"/>&position_id=<c:out value="${qualifications.position.positionID}"/>&qual_name=<c:out value="${qualifications.qualificationName}"/>">Delete</a></td>
											</tr>
											<tr>
												<td>&nbsp</td>
											</tr>
										</c:forEach>
									</table>
									
						</div>

						<div class="edit_qualifications_form">
							<form method="post" action="Company?action=jobPost_qualifications_submit&company_id=<c:out value="${company.companyID}"/>&username=<c:out value="${employee.username}"/>&position_id=<c:out value="${position.positionID}"/>">

								<label for="qual_name">Qualification name:</label>
								<input type="text" name="qual_name" maxlength="25" placeholder="qualification name" required>
															
								<br><br>
								
								<label for="qual_years"> Years of domain:</label>
								<input class="input_50" type="text" name="qual_years" placeholder="months / years of experience" required>
								<select class="input_50" id="qual_years_time" required> 
									<option value="" disabled selected>Months / Years</option>
									<option value="Months">Months</option>
									<option value="Years">Years</option>
								</select> 
																							
								<br><br>
								
								
								<label for="qual_description">Description:</label><br>
								<textarea name="qual_description" maxlength="400" placeholder="Description..." required></textarea>
																							
								<br><br>
								
								
								<div class="row-buttons">
								<a class="button" href="Company?action=jobPost_edit_view&company_id=<c:out value="${company.companyID}"/>&username=<c:out value="${employee.username}"/>&position_id=<c:out value="${position.positionID}"/>">Back</a>
								<input class="button"  type="submit" value="submit">
								</div>


							</form>

						</div>	
			</div>

		</div>
</body>
</html>