<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Applicants | My Resume </title>
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
					<li><a href="Applicant?action=applicant_resume_edit&applicant_id=<c:out value="${applicant.applicantID}"/>">My Resume - Edit</a></li>
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
				
		<aside>
			<div class="module_personal_info">
				<img class="profile_picture" src="PicturesController?action=applicant_profile_picture&applicant_id=<c:out value="${applicant.applicantID}"/>"/>
				<a class="edit_icon" href="Applicant?action=applicant_resume_edit&applicant_id=<c:out value="${applicant.applicantID}"/>"><img src="icon-edit.png"></a>
				<div class="first_name"><c:out value="${applicant.firstName}" /></div>
				<div class="last_name"><c:out value="${applicant.lastName}" /></div>
				<section>
					<c:out value="${applicant.profession}" />
					<br><br>
					<c:out value="${applicant.phoneNumber}"/>
					<br>
					<c:out value="${applicant.email}"/>
					<br>
					<c:out value="${applicant.streetAddress}"/>
					<br>
					<c:out value="${applicant.postalCode}"/>, <c:out value="${applicant.city}" />
					<br>
					<c:out value="${applicant.province}"/> , <c:out value="${applicant.country}" />
				</section>
			</div>
			

			<div class="module_title_small">About</div>

				<div class="module_about_you">
				<c:out value="${applicant.aboutYou}"/>
				</div>

			<div class="module_tech_skills">

				<div class="module_title_small">Technical Skills <img src="icon-techSkills_module.png"></div>
				
				<ul>
					<c:forEach items="${applicant_tech_skills}" var="skill">
					<li><c:out value="${skill.skillName}"/></li>
					</c:forEach>
				</ul>
			</div>

			<div class="module_other_skills">

				<div class="module_title_small">Other Skills <img src="icon-otherSkills_module.png"></div>
				
				<ul>
					<c:forEach items="${applicant_other_skills}" var="skill">
					<li><c:out value="${skill.skillName}"/></li>
					</c:forEach>
				</ul>
			</div>


		</aside>

		<div class="experiences_module">
		
			<div class="module_title">Experience <img src="icon-experience_module.png"> <a class="edit_icon" href="Applicant?action=applicant_resume_edit&applicant_id=<c:out value="${applicant.applicantID}"/>"><img src="icon-edit.png"></a></div>

			<c:forEach items="${applicant_experiences}" var="experience">
				<div class="experience_module">
		
				<div class="module_logo">
					<img alt="" src="PicturesController?action=applicant_experience_logos&applicant_id=<c:out value="${experience.applicant.applicantID}"/>&experience_title=<c:out value="${experience.experienceTitle}"/>"/>
				</div>
		
				<div class="module_subtitle"><c:out value="${experience.experienceTitle}"/></div>
				<samp class="module_name"><c:out value="${experience.companyName}"/></samp>
				<br>
				<span class="module_date"><c:out value="${experience.startDate}"/> - <c:out value="${experience.endDate}"/></span>
				<br><br>
				<section class="module_description"><c:out value="${experience.description}"/></section>
				</div>
				<br><br>
			</c:forEach>
		</div>


		
		
		<div class="educations_module">
		
			<div class="module_title">Education <img src="icon-education_module.png"> <a class="edit_icon" href="Applicant?action=applicant_resume_edit&applicant_id=<c:out value="${applicant.applicantID}"/>"><img src="icon-edit.png"></a></div>

			<c:forEach items="${applicant_educations}" var="education">
				<div class="education_module">
	
				<div class="module_logo">
						<img alt="" src="PicturesController?action=applicant_education_logos&applicant_id=<c:out value="${education.applicant.applicantID}"/>&education_title=<c:out value="${education.educationTitle}"/>"/>
				</div>
				
				<div class="module_subtitle"><c:out value="${education.educationTitle}"/></div>
	
				<samp class="module_name"><c:out value="${education.schoolName}"/></samp>
				<br>
				<span class="module_date"><c:out value="${education.startDate}"/> - <c:out value="${education.endDate}"/></span>
				<br> <br>
				<section class="module_description"><c:out value="${education.description}"/></section>
				</div>
				<br><br>
			</c:forEach>
			
		</div>

</div>

<footer class="footer">
	<p>	
	The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.</p>

</footer>

</body>
</html>