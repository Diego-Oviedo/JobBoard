<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<title>Company | Job post - Edit</title>
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

				<h1>Job post - Edit - <c:out value="${position.positionID}"/></h1>

					<div class="job_post_form">
						<form method="post" action="Company?action=jobPost_edit_submit&company_id=<c:out value="${company.companyID}"/>&username=<c:out value="${employee.username}"/>&position_id=<c:out value="${position.positionID}"/>">

						   	<label for="job_title">Job Title:</label>
							<input type="text" name="job_title" maxlength="25" placeholder="job title" value="<c:out value="${position.jobTitle}"/>" required>

							<br><br>

							<label for="description">Description:</label><br>
							<textarea name="description" maxlength="1000" placeholder="Description..." required><c:out value="${position.jobDescription}"/></textarea>

							<br><br>
							
							<label for="doamin">Domain:</label>
							<input type="text" name="domain" maxlength="25" placeholder="domain" value="<c:out value="${position.jobDomain}"/>" required>

							<br><br>
							
							<label for="job_type">Job type:</label>
							<input type="text" name="job_type" maxlength="25" placeholder="job type" value="<c:out value="${position.typeOfJob}"/>" required>

							<br><br>
							
							<label for="availability">Availability:</label>
							<input type="text" name="availability" maxlength="25" placeholder="availability" value="<c:out value="${position.availability}"/>" required>

							<br><br>
							
							<label for="salary"> Salary:</label>
							<input class="input_50" type="number" name="salary" min="0" max="300000" step="0.01" placeholder="12.50" required>
							<select class="input_50" id="salary_freq" required name="salary_freq"> 
								<option value="" disabled selected>frequency</option>
								<option value="Hourly">Hourly</option>
								<option value="Weekly">Weekly</option>
								<option value="Bi-weekly">Biweekly</option>
								<option value="Monthly">Monthly</option>
								<option value="Anually">Annually</option>
							</select> 

							<br><br>
							
							<label for="additional_compensation">Additional compensation:</label><br>
							<textarea name="additional_compensation" maxlength="25" placeholder="additional compensation..."></textarea>

							<br><br>
			
								<div class="module_experience">
										<a href="Company?action=jobPost_experiences&company_id=<c:out value="${company.companyID}"/>&username=<c:out value="${employee.username}"/>&position_id=<c:out value="${position.positionID}"/>">
											<h2>Experience:</h2>	
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
										</a>
								</div>


								<br><br>
															
								<div class="module_qualification">
									<a href="Company?action=jobPost_qualifications&company_id=<c:out value="${company.companyID}"/>&username=<c:out value="${employee.username}"/>&position_id=<c:out value="${position.positionID}"/>">
										<h2>Qualifications:</h2>
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
									</a>
								</div>

							<div class="row-buttons">
							<a class="button" href="Company?action=jobPost_view&company_id=<c:out value="${company.companyID}"/>&username=<c:out value="${employee.username}"/>&position_id=<c:out value="${position.positionID}"/>">Cancel</a>
							<input class="button"  type="submit" value="submit">
							</div>

						</form>
						
					</div>
			</div>

		</div>
</body>
</html>