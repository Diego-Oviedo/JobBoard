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


					<div class="drop-down-wrapper">
						<div class="content_inside_drop-down-wrapper">

							<h1>Applicants</h1>
							<br>
							<table>
								<thead>
									<th>APPLICATION ID</th>
									<th>JOB TITLE</th>
									<th>PROFESSION</th>
									<th>CITY</th>
									<th>PROVINCE</th>
									<th>COUNTRY</th>
									<th>NAME</th>
									<th colspan="2">ACTION</th>
								</thead>
								<tbody>
									<c:forEach items="${applications}" var="application">
										<tr>
											<td>${application.applicationNumber}</td>
											<td>${application.position.jobTitle}</td>
											<td>${application.applicant.profession}</td>
											<td>${application.position.company.city}</td>
											<td>${application.position.company.province}</td>
											<td>${application.position.company.country} </td>
											<td>${application.applicant.firstName} ${application.applicant.lastName} </td>
											<td colspan="2"><a class="small_button" href="Company?action=applicant_profile_view&applicant_id=${application.applicant.applicantID}">See profile </a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

						</div>
						<div class="parent_drop-down">
							<h3>Jobs posted</h3>
							<br>
							<table>
								<thead>
									<th>POSITION ID</th>
									<th>JOB TITLE</th>
									<th>DOMAIN</th>
									<th>TYPE OF JOB</th>
									<th></th>
									<th></th>
								</thead>
								
								<tbody>
									<c:forEach items="${positions}" var="position">
										<tr>
											<td><c:out value="${position.positionID}" /></td>
											<td><c:out value="${position.jobTitle}" /></td>
											<td><c:out value="${position.jobDomain}" /></td>
											<td><c:out value="${position.typeOfJob}" /></td>
											<td><a class="table_button" href="Company?action=jobPost_view&position_id=${position.positionID}">See post </a></td>
											<td><a class="table_button" href="Company?action=applications_per_post_view&position_id=${position.positionID}">Applicants applied</a></td>
										</tr>
									</c:forEach>
								</tbody>
								
							</table>

						</div>
					</div>






				</div>

			</div>

		</div>

	</div>


</body>
</html>