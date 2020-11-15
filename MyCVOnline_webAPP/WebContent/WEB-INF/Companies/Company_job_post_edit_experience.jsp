<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<title>Company | Job post - Edit - Experience</title>
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

			<div class="job_post_edit">

					<h1>Job post [Experience] - <c:out value="${position.positionID}"/></h1>

						<div class="edit_experience">
							
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
												<td><a class="button" href="Company?action=jobPost_experiences_delete&company_id=<c:out value="${company.companyID}"/>&username=<c:out value="${employee.username}"/>&position_id=<c:out value="${experience.position.positionID}"/>&exp_name=<c:out value="${experience.experienceName}"/>">Delete</a></td>
											</tr>
											<tr>
												<td>&nbsp</td>
											</tr>
										</c:forEach>
									</table>
								
						</div>

						<div class="edit_experience_form">
							
							<form method="post" action="Company?action=jobPost_experiences_submit&company_id=<c:out value="${company.companyID}"/>&username=<c:out value="${employee.username}"/>&position_id=<c:out value="${position.positionID}"/>">

								<label for="exp_name">Domain name:</label>
								<input type="text" name="exp_name" maxlength="25" placeholder="domain name" required>
								
								<br><br>
								
								<label for="exp_years"> Years of experience:</label>
								<input class="input_50" type="text" name="exp_years" placeholder="months / years of experience"required>
								<select id="exp_years" name="exp_years_time" class="input_50" required> 
									<option value="" disabled selected>Months / Years</option>
									<option value="Months">Months</option>
									<option value="Years">Years</option>
								</select> 
								
								<br><br>
								
								<label for="exp_description">Description:</label><br>
								<textarea name="exp_description" maxlength="400" placeholder="Description..." required></textarea>
								
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