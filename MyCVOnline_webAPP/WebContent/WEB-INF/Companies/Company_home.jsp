<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<title>Company | My profile </title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<link rel="stylesheet" type="text/css" href="companies_styles.css">
</head>
<body>


  <div class="header">
    <h1>My CV Online</h1>
		<p>
			A <b>resume-generator</b> webapp.
		</p>
  </div>

  <div class="navbar">
    <a href="Company?action=jobPosts_view&company_id=<c:out value="${company.companyID}"/>&username=<c:out value="${employee.username}"/>">Jobs posted</a>
	<a href="Company?action=jobPost_create_view&company_id=<c:out value="${company.companyID}"/>&username=<c:out value="${employee.username}"/>">Create a Job post</a>
	<a href="Company?action=applications_view&company_id=<c:out value="${company.companyID}"/>&username=<c:out value="${employee.username}"/>">Applications</a>
	<a href="Company?action=my_profile&company_id=<c:out value="${company.companyID}"/>&username=<c:out value="${employee.username}"/>">My profile</a>
	<a href="Company?action=logout">Log out</a>
      

    <div class="chip">
      <img src="PicturesController?action=company_logo&company_id=${company.companyID}"/>
      ${company.companyID} | ${employee.fullName}
    </div>
  </div>


	<div class="main">

		<div class="row">

			<div class="aside_box_left">

				<div class="aside_box">
					<h1>My profile</h1>

					<label>Username:</label> ${employee.username}

					<br> <label>Password:</label> ******** 
					
					<br> <label>Employee name:</label> ${employee.fullName}

					<br>
					
					<br> <label>Company name:</label> ${company.companyName}

					<br> <label>Phone number:</label> ${company.phoneNumber}

					<br> <label>E-mail:</label> ${company.email}

					<br>
					
					<br> <label>City:</label> ${company.city}

					<br> <label>Postal code:</label> ${company.postalCode}

					<br> <label>Province:</label> ${company.province}

					<br> <label>Country:</label> ${company.country}

					<br> <a class="button"
						href="Company?action=my_profile_edit&company_id=${company.companyID}&username=${employee.username}">Edit</a>
				</div>

			</div>

			<div class="aside_right">

				<div class="aside_box"></div>


				<div class="aside_box">

					<div id="demo">
						<c:forEach items="${positions}" var="position">
						<div class="wrapper">
							
							<div class="content">
								<ul>
									<c:forEach items="${applications}" var="application">
									<a href="#"><li>${application.applicationNumber} - ${application.applicant.firstName} ${application.applicant.lastName}</li></a>
									</c:forEach>
								</ul> 
							</div>
							
							
							<div class="parent"><h3>Job post - ${position.jobTitle} - ${position.positionID}</h3></div>
						</div>
						</c:forEach>
						</div>
					</div>

				</div>

		</div>

	</div>


</body>
</html>