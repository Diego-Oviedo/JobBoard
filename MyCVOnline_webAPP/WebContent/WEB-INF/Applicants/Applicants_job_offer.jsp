<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Applicants | Job Offer</title>
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
			<div class="job_offer">

						<h2><c:out value="${position.jobTitle}"/></h2>

						<div class="position_module_logo">
							<img alt="" src="PicturesController?action=company_logo&company_id=<c:out value="${position.company.companyID}"/>"/>
						</div>

						<div class="job_offer_module_info">
						<c:out value="${position.company.companyName}" />
						<br>
						<strong>Post ID:</strong> <c:out value="${position.positionID}"/>
						<br>
						<strong>Domain:</strong> <c:out value="${position.jobDomain}"/>
						<br>
						<c:out value="${position.company.city}"/>, <c:out value="${position.company.country}"/>
						<br>
						</div>
						
						<div class="job_offer_module_description">
						<strong>Description:</strong> 
						<br><br>
						<c:out value="${position.jobDescription}"/>
						<br><br>
						<strong>Additional compensations:</strong><br><c:out value="${position.additionalCompensation}" />
						</div>



							<div class="job_offer_module_subtitle">Qualifications</div>

								<div class="job_offer_module_requirements_list">
								
								<c:forEach items="${job_post_qualifications}" var="qualification">
								<div class="job_offer_module_requirement">
								<ul>
									<li><c:out value="${qualification.qualificationName}"/></li>
									<li><c:out value="${qualification.desiredYears}" /></li>
									<li><c:out value="${qualification.qualificationDescription}"/></li>
								</ul>
								</div>
								</c:forEach>
								</div>


							<div class="job_offer_module_subtitle">Experience</div>

								<div class="job_offer_module_requirements_list">
								
								<c:forEach items="${job_post_expreiences}" var="experience">
								<div class="job_offer_module_requirement">
								<ul>
									<li><c:out value="${experience.experienceName}"/></li>
									<li><c:out value="${experience.desiredYears}"/></li>
									<li><c:out value="${experience.experienceDescription}"/></li>
								</ul>
								</div>
								</c:forEach>
								</div>


					<div class="job_offer_module_company">
						<div class="job_offer_module_subtitle">About company</div>
						
						<img alt="" src="PicturesController?action=company_logo&company_id=<c:out value="${position.company.companyID}"/>" width="100"/>
						<br>
						<c:out value="${position.company.companyName}"/> | <c:out value="${position.company.companyID}"/>
						<br>
						<strong>Phone number:</strong> <c:out value="${position.company.phoneNumber}"/>
						<br>
						<strong>E-mail:</strong> <c:out value="${position.company.email}"/>
						<br>
						<i><c:out value="${position.company.city}"/>, <c:out value="${position.company.postalCode}"/>
						<br>
						<c:out value="${position.company.province}"/>, <c:out value="${position.company.country}"/></i>
					</div>
						
						<div class="buttons_box">
						<a class="button" href="Applicant?action=apply_position&applicant_id=<c:out value="${applicant.applicantID}" />&position_id=<c:out value="${position.positionID}" />">Apply</a>
						</div>
							<br>
				</div>
			
		</div>
</div>



<footer class="footer">
	<p>	
	The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.</p>

</footer>

</body>
</html>
