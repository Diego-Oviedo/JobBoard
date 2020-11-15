<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<title>Company | Applicant profile </title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<link rel="stylesheet" type="text/css" href="companies_styles.css">
</head>
<body>
		<header>

				<ul class="nav_buttons">
					<li><a href="Company?action=jobPosts_view&company_id=<c:out value="${company.companyID}"/>&username=<c:out value="${employee.username}"/>">Jobs posted</a></li>
					<li><a href="Company?action=jobPost_create_view&company_id=<c:out value="${company.companyID}"/>&username=<c:out value="${employee.username}"/>">Create a Job post</a></li>
					<li><a href="Company?action=applications_view&company_id=<c:out value="${company.companyID}"/>&username=<c:out value="${employee.username}"/>">Applications</a></li>
					<li><a href="Company?action=my_profile&company_id=<c:out value="${company.companyID}"/>&username=<c:out value="${employee.username}"/>">My profile</a></li>	
					<li><a href="Company?action=logout">Log out</a></li>
					<li><img src="Logo<1024_company.png"> </li>
				</ul>



				<ul class="nav_greeting">
					<li>Welcome </li>
					<li><c:out value="${employee.fullName}"/></li>
					<li> | </li>
					<li>Company ID: <c:out value="${company.companyID}"/></li>
				</ul>

		</header>
		

		<div class="main">

			<div class="applicant_profile">

				<h1>Applicant user-profile</h1>

					<div class="applicant_profile_top">						
						
						<strong>Applicant id:</strong> <c:out value="${applicant.applicantID}"/>
						<br>
						<strong>Full name:</strong> <c:out value="${applicant.firstName}" /> <c:out value="${applicant.lastName}" />
						<br>
						<strong>Phone number:</strong> <c:out value="${applicant.phoneNumber}" />
						<br>
						<strong>E-mail:</strong> <c:out value="${applicant.email}" />

						<img class="profile_picture" src="PicturesController?action=applicant_profile_picture&applicant_id=<c:out value="${applicant.applicantID}"/>"/>
						
					</div>
 



					<h1>Applicant resume </h1>

					<div class="applicant_profile_bttm">	
		
							<div class="module_personal_info">

									<div class="full_name_title"><c:out value="${applicant.firstName}"/> <c:out value="${applicant.lastName}"/></div>
									
									<div class="profession_title"><c:out value="${applicant.profession}"/></div>

							</div>

							<div class="module_address_info">

									<div class="in_box_title"><c:out value="${applicant.phoneNumber}"/></div>
									<br>
									<div class="in_box_title"><c:out value="${applicant.email}"/></div>
									<br><br>
									<div class="in_box_title"><c:out value="${applicant.streetAddress}"/></div>
									<br>
									<div class="in_box_title"><c:out value="${applicant.postalCode}"/>, <c:out value="${applicant.city}" /></div>
									<br>
									<div class="in_box_title"><c:out value="${applicant.province}"/> , <c:out value="${applicant.country}" /></div>
									<br><br>
							</div>	

							<div class="module_about_you">

									<c:out value="${applicant.aboutYou}"/>
									<br><br>
							</div>	

							<div class="module_technical_skills">

									<h3>Technical skills</h3>

									<table>
										<c:forEach items="${applicant_tech_skills}" var="skill">
										<tr>
											<td><c:out value="${skill.skillName}"/></td>
										</tr>
										</c:forEach>
									</table>
							</div>	


							<div class="module_experiences">

								<h3>Experience</h3>

									<table>
									<c:forEach items="${applicant_experiences}" var="experience">
										<tr>
											<td colspan="2"><c:out value="${experience.experienceTitle}"/></td>
											<td><img alt="" src="PicturesController?action=applicant_experience_logos&applicant_id=<c:out value="${experience.applicant.applicantID}"/>&experience_title=<c:out value="${experience.experienceTitle}"/>" width="100"/></td>
											<td>| <c:out value="${experience.companyName}"/></td>
										</tr>
										<tr>
											<td><c:out value="${experience.startDate}"/> - <c:out value="${experience.endDate}"/></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
										<tr>
											<td colspan="4"><c:out value="${experience.description}"/></td>
										</tr>
									</c:forEach>	
								</table>
								
							</div>

							<div class="module_education">

								<h3>Education</h3>

									<table>
										<c:forEach items="${applicant_educations}" var="education">
											<tr>
												<td colspan="2"><c:out value="${education.educationTitle}"/></td>
												<td><img alt="" src="PicturesController?action=applicant_education_logos&applicant_id=<c:out value="${education.applicant.applicantID}"/>&education_title=<c:out value="${education.educationTitle}"/>" width="100"/></td>
												<td>| <c:out value="${education.schoolName}"/></td>
											</tr>
											<tr>
												<td><c:out value="${education.startDate}"/> - <c:out value="${education.endDate}"/></td>
												<td></td>
												<td></td>
												<td></td>
											</tr>
											<tr>
												<td colspan="4"><c:out value="${education.description}"/></td>					
											</tr>
										</c:forEach>
								</table>
								
							</div>

							<div class="module_other_skills">

								<h3>Other skills</h3>

									<table>
										<c:forEach items="${applicant_other_skills}" var="skill">
											<tr>
												<td><c:out value="${skill.skillName}"/></td>
											</tr>
										</c:forEach>
									</table>
								
							</div>	
							
					</div>	

			</div>	
		</div>
</body>
</html>