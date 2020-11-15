<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Applicants |  My Applications</title>
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
				
		<div class="my_applications_main">
			<div class="title_container">My Applications</div>

					<div class="my_applications">


						<div class="my_applications_table">
							<table>
								<thead>
									<th>APPLICATION<br>NUMBER</th>
									<th>POSITION ID</th>
									<th>JOB TITLE</th>
									<th>DOMAIN</th>
									<th>COMPANY<br>NAME</th>
									<th>DATE<br>APPLIED</th>
									<th colspan="2">ACTION</th>
								</thead>
								<tbody>
								<c:forEach items="${applicant_applications}" var="application">
								<tr>
									<td><c:out value="${application.applicationNumber}"/></td>
									<td><c:out value="${application.position.positionID}"/></td>
									<td><c:out value="${application.position.jobTitle}"/></td>
									<td><c:out value="${application.position.jobDomain}"/></td>
									<td><c:out value="${application.position.company.companyName}"/></td>
									<td><c:out value="${application.applicationDate}"/></td>
									<td><a class="icon_button" href="Applicant?action=job_offer&position_id=<c:out value="${application.position.positionID}"/>"> <img src="icon-view.png" title="View application"> </a></td>
									<td><a class="icon_button" href="Applicant?action=delete_application&application_number=<c:out value="${application.applicationNumber}"/>&applicant_id=<c:out value="${applicant.applicantID}"/>"><img src="icon-withdraw.png" title="Withdraw application"></a></td>
								</tr>
								</c:forEach>
								</tbody>
							</table>
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
