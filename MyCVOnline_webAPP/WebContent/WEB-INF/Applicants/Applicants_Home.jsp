<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Applicants | Home </title>
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
					<li><a href="Applicants_login.jsp">Log in</a></li>
					<li><a href="Applicants_Register.jsp">Register</a></li>
					<li><a href="Applicant?action=applicants_home">Job Offers</a></li>
					<li><a href="Home.jsp">Back</a></li>	
					
				</ul>
				
				<ul class="nav_greeting">
					<li> </li>
				</ul>

			</nav>
		</header>

<div class="main_job_offers">
				
		<aside class="offers_module">
			
			<c:forEach items="${job_positions}" var="position">			
				<div class="job_position">
					
					<div class="module_subtitle"><c:out value="${position.jobTitle}"/></div>
	
					<div class="module_logo">
							<img alt="" src="PicturesController?action=company_logo&company_id=<c:out value="${position.company.companyID}"/>"/>
					</div>	
		
					<samp class="module_info_name"><c:out value="${position.company.companyName}"/> | <c:out value="${position.jobDomain}"/></samp>
					<br>
					<span class="module_info"><c:out value="${position.offerSalary}"/> - <c:out value="${position.availability}"/></span>
					<br>
					<span class="module_info"><c:out value="${position.typeOfJob}"/></span>
					<br>
					<span class="module_info">Position ID: <c:out value="${position.positionID}"/></span>
					<br>
					<section class="module_position_description" ><c:out value="${position.jobDescription}"/></section>

							<div class="buttons_box">
								<a class="button" href="Applicants_login.jsp"> See offer</a>
							</div>
							<br><br>

				</div>

				<div class="divisor"></div>

			</c:forEach>	

		</aside>

		<div class="offer_module">

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
						
						<div class="buttons_box">
						<a class="button" href="Applicants_login.jsp">Apply</a>
						</div>

				</div>
			
		</div>


		

</div>

<footer class="footer">
	<p>	
	The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.</p>

</footer>

</body>
</html>