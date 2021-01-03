package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.*;
import model.DAOImplementation.*;
import model.DAOInterface.*;

@WebServlet("/Company")
@MultipartConfig(maxFileSize = 16177215)
public class CompanyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOCompany company_dao;
	private static String COMPANY_APPLICANT_PROFILE = "Company_applicant_profile.jsp";
	private static String COMPANY_APPLICATIONS = "Company_applications.jsp";
	private static String COMPANY_APPLICATIONS_PER_POST = "Company_applications_per_post.jsp";
	private static String COMPANY_JOB_POST_CREATE = "Company_job_post_create.jsp";
	private static String COMPANY_JOB_POST_EDIT_EXPERIENCES = "Company_job_post_edit_experience.jsp";
	private static String COMPANY_JOB_POST_EDIT_QUALIFICATIONS = "Company_job_post_edit_qualifications.jsp";
	private static String COMPANY_JOB_POST_EDIT = "Company_job_post_edit.jsp";
	private static String COMPANY_JOB_POST_VIEW = "Company_job_post.jsp";
	private static String COMPANY_JOBS_OFFERS_POSTED = "Company_jobs_posted.jsp";
	private static String COMPANY_LOGIN = "Company_login.jsp";
	private static String COMPANY_HOME = "Company_home.jsp";
	private static String COMPANY_MY_PROFILE_EDIT = "Company_my_profile_edit.jsp";

	 
	String action = " ";
	
	public CompanyController() throws ServletException, IOException, ClassNotFoundException {
		super(); 
		company_dao = new CompanyImplementation();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doPost(request, response);}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action = request.getParameter("action");
		
		/********************************REGISTER PGAE*********************************/
		if (action.equalsIgnoreCase("create_profile")) {
			
			company_create_profile(request,response);
			RequestDispatcher view = request.getRequestDispatcher(COMPANY_LOGIN);
			view.forward(request, response);
		}
		
		/********************************LOGIN PGAE*********************************/
		
		if(action.equalsIgnoreCase("login")) {
			
			try {
			String username_access = request.getParameter("username");
			String password_access= request.getParameter("password");
			
			CompanyEmployee employee_access = company_dao.retreiveEmployee(username_access);
			
			String username_ret = employee_access.getUsername();
			String password_ret = employee_access.getPassword();
			
			
			if(username_access.equals(username_ret) && password_access.equals(password_ret)) {
				
				HttpSession session = request.getSession();
				
				String username_retreived = employee_access.getUsername();
				
				Company company_retreived = company_dao.retreiveCompanyByUsername(username_retreived);
				
				String companyID_retreived = company_retreived.getCompanyID();
				
				session.setAttribute("username",username_retreived);
				session.setAttribute("company_id",companyID_retreived);
				
				company_retreive_profile(request, response);
				company_retreive_employee(request,response);
				company_retreive_jobPositions(request,response);
				company_retreive_ApplicationsByPostID(request,response);
				company_retreive_PositionExperiences(request,response);
				company_retreive_PositionQualifications(request,response);
				RequestDispatcher view = request.getRequestDispatcher(COMPANY_HOME);
				view.forward(request, response);
				
			}else { //if the password is not correct 
				RequestDispatcher view = request.getRequestDispatcher(COMPANY_LOGIN);
				view.forward(request, response);
			}
			}catch(Exception e) {//if the username does not exists 
				if(e.getClass() == NullPointerException.class) {
					RequestDispatcher view = request.getRequestDispatcher(COMPANY_LOGIN);
					view.forward(request, response);
				}else {//if another problem, sent to login page and pint-console the error
					e.printStackTrace();
					RequestDispatcher view = request.getRequestDispatcher(COMPANY_LOGIN);
					view.forward(request, response);
				}
				
				
			}
			}
		
		
		/*-----------------------------------APPLICANT PROFILE PGAE-----------------------------------------------*/
		if (action.equalsIgnoreCase("applicant_profile_view")) {
			
			company_retreive_profile(request,response);
			applicant_retreive(request,response);
			applicant_retreive_TechSkill(request,response);
			applicants_retreive_Experiences(request,response);
			applicants_retreive_Educations(request,response);
			applicant_retreive_OtherSkill(request,response);
			RequestDispatcher view = request.getRequestDispatcher(COMPANY_APPLICANT_PROFILE);
			view.forward(request, response);
		}
		
		/*-----------------------------------APPLICATIONS PGAE-----------------------------------------------*/
		else if (action.equalsIgnoreCase("applications_view")) {
			
			company_retreive_profile(request,response);
			company_retreive_ApplicationsByCompany(request,response);
			RequestDispatcher view = request.getRequestDispatcher(COMPANY_APPLICATIONS);
			view.forward(request, response);
		}
		else if (action.equalsIgnoreCase("applications_per_post_view")) {
			
			company_retreive_profile(request,response);
			company_retreive_jobPosition(request,response);
			company_retreive_ApplicationsByPostID(request,response);
			RequestDispatcher view = request.getRequestDispatcher(COMPANY_APPLICATIONS_PER_POST);
			view.forward(request, response);
		}
		
		/*-----------------------------------JOB POST PGAES-----------------------------------------------*/
		else if (action.equalsIgnoreCase("jobPost_create_view")) {
			
			company_retreive_profile(request,response);
			RequestDispatcher view = request.getRequestDispatcher(COMPANY_JOB_POST_CREATE);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("jobPost_create_submit")) {
			
			company_create_jobPosition(request,response);
			company_retreive_jobPositions(request,response);
			company_retreive_profile(request,response);
			company_retreive_employee(request,response);
			RequestDispatcher view = request.getRequestDispatcher(COMPANY_JOBS_OFFERS_POSTED);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("jobPost_edit_view")) {
			
			company_retreive_profile(request,response);
			company_retreive_employee(request,response);
			company_retreive_jobPosition(request,response);
			company_retreive_PositionExperiences(request,response);
			company_retreive_PositionQualifications(request,response);
			RequestDispatcher view = request.getRequestDispatcher(COMPANY_JOB_POST_EDIT);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("jobPost_edit_submit")) {
			
			company_update_jobPosition(request,response);
			company_retreive_profile(request,response);
			company_retreive_employee(request,response);
			company_retreive_jobPosition(request,response);
			company_retreive_PositionExperiences(request,response);
			company_retreive_PositionQualifications(request,response);
			RequestDispatcher view = request.getRequestDispatcher(COMPANY_JOBS_OFFERS_POSTED);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("jobPosts_view")) {
			
			company_retreive_profile(request,response);
			company_retreive_jobPositions(request,response);
			RequestDispatcher view = request.getRequestDispatcher(COMPANY_JOBS_OFFERS_POSTED);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("jobPost_view")) {
			
			company_retreive_profile(request,response);
			company_retreive_employee(request,response);
			company_retreive_jobPosition(request,response);
			company_retreive_PositionExperiences(request,response);
			company_retreive_PositionQualifications(request,response);
			RequestDispatcher view = request.getRequestDispatcher(COMPANY_JOB_POST_VIEW);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("jobPost_delete")) {
			
			company_delete_jobPosition(request,response);
			company_retreive_profile(request,response);
			company_retreive_jobPositions(request,response);
			RequestDispatcher view = request.getRequestDispatcher(COMPANY_JOBS_OFFERS_POSTED);
			view.forward(request, response);
		}
		
		/*-----------------------------------JOB POST EXPERIENCES PGAES-----------------------------------------------*/
		else if (action.equalsIgnoreCase("jobPost_experiences")) {
			
			company_retreive_profile(request,response);
			company_retreive_employee(request,response);
			company_retreive_jobPosition(request,response);
			company_retreive_PositionExperiences(request,response);
			RequestDispatcher view = request.getRequestDispatcher(COMPANY_JOB_POST_EDIT_EXPERIENCES);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("jobPost_experiences_submit")) {
			
			company_create_PositionExperience(request,response);
			company_retreive_profile(request,response);
			company_retreive_employee(request,response);
			company_retreive_jobPosition(request,response);
			company_retreive_PositionExperiences(request,response);
			RequestDispatcher view = request.getRequestDispatcher(COMPANY_JOB_POST_EDIT_EXPERIENCES);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("jobPost_experiences_delete")) {
			
			company_delete_PositionExperience(request,response);
			company_retreive_profile(request,response);
			company_retreive_employee(request,response);
			company_retreive_jobPosition(request,response);
			company_retreive_PositionExperiences(request,response);
			RequestDispatcher view = request.getRequestDispatcher(COMPANY_JOB_POST_EDIT_EXPERIENCES);
			view.forward(request, response);
		}
		
		/*-----------------------------------JOB POST QUALIFICATIONS PGAES-----------------------------------------------*/
		else if (action.equalsIgnoreCase("jobPost_qualifications")) {
			
			company_retreive_profile(request,response);
			company_retreive_employee(request,response);
			company_retreive_jobPosition(request,response);
			company_retreive_PositionQualifications(request,response);
			RequestDispatcher view = request.getRequestDispatcher(COMPANY_JOB_POST_EDIT_QUALIFICATIONS);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("jobPost_qualifications_submit")) {
			
			company_create_PositionQualification(request,response);
			company_retreive_profile(request,response);
			company_retreive_employee(request,response);
			company_retreive_jobPosition(request,response);
			company_retreive_PositionQualifications(request,response);
			RequestDispatcher view = request.getRequestDispatcher(COMPANY_JOB_POST_EDIT_QUALIFICATIONS);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("jobPost_qualifications_delete")) {
			
			company_delete_PositionQualification(request,response);
			company_retreive_profile(request,response);
			company_retreive_employee(request,response);
			company_retreive_jobPosition(request,response);
			company_retreive_PositionQualifications(request,response);
			RequestDispatcher view = request.getRequestDispatcher(COMPANY_JOB_POST_EDIT_QUALIFICATIONS);
			view.forward(request, response);
		}
		
		/*-----------------------------------JOB POST QUALIFICATIONS PGAES-----------------------------------------------*/
		
		else if (action.equalsIgnoreCase("my_profile")) {
			
			company_retreive_profile(request,response);
			company_retreive_employee(request,response);
			RequestDispatcher view = request.getRequestDispatcher(COMPANY_HOME);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("my_profile_edit")) {
			
			company_retreive_profile(request,response);
			company_retreive_employee(request,response);
			RequestDispatcher view = request.getRequestDispatcher(COMPANY_MY_PROFILE_EDIT);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("my_profile_edit_submmit")) {
			
			company_update_profile(request,response);
			company_retreive_profile(request,response);
			company_retreive_employee(request,response);
			RequestDispatcher view = request.getRequestDispatcher(COMPANY_HOME);
			view.forward(request, response);
		}
		
		/********************************LOGOUT*********************************/
		
		else if (action.equalsIgnoreCase("logout")) {
			
			company_logout(request,response);
			RequestDispatcher view = request.getRequestDispatcher(COMPANY_LOGIN);
			view.forward(request, response);
		
		}
		
		
	}	
	
	public void company_create_profile (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("confirm_password");
		String fullName = request.getParameter("employee_full_name");
		String companyName = request.getParameter("company_name");
		String phoneNumber = request.getParameter("phone_number");
		String email = request.getParameter("email");
		String city = request.getParameter("city");
		String postalCode = request.getParameter("postal_code");
		String province = request.getParameter("province");
		String country = request.getParameter("country");
		InputStream companyLogo = null;
		
		Part filePart = request.getPart("company_logo");
		companyLogo = filePart.getInputStream();
		
		if(password.equals(passwordConfirm)) {
		Company company = new Company();
		company.setCompanyName(companyName);
		company.setPhoneNumber(phoneNumber);
		company.setEmail(email);
		company.setCity(city);
		company.setPostalCode(postalCode);
		company.setProvince(province);
		company.setCountry(country);
		company.setCompanyLogo(companyLogo);
		
		company_dao.insertCompany(company);
		
		Company company_created = company_dao.retreiveCompanyByName(companyName);
		
		CompanyEmployee employee = new CompanyEmployee();
		employee.setCompany(company_created);
		employee.setUsername(username);
		employee.setPassword(password);
		employee.setFullName(fullName);
		company_dao.insertEmployee(employee);
		}else {
			
			/*Redirect a JSP for 3secs to advise that passwords must match */
			
			}
	}
	
	public void company_delete_profile (HttpServletRequest request, HttpServletResponse response){
		String companyID = request.getParameter("company_id");
		String username = request.getParameter("username");
		
		company_dao.deleteCompany(companyID);
		company_dao.deleteEmployee(username);
	}
	
	public void company_retreive_profile (HttpServletRequest request, HttpServletResponse response){
		
		HttpSession session = request.getSession();

		String companyID = (String) session.getAttribute("company_id");
		String username = (String) session.getAttribute("username");
		
		request.setAttribute("company",company_dao.retreiveCompany(companyID));
		request.setAttribute("employee",company_dao.retreiveEmployee(username));
		 
	}
	
	public void company_update_profile (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String companyID = request.getParameter("company_id");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("confirm_password");
		String fullName = request.getParameter("employee_full_name");
		
		String companyName = request.getParameter("company_name");
		String phoneNumber = request.getParameter("phone_number");
		String email = request.getParameter("email");
		String city = request.getParameter("city");
		String postalCode = request.getParameter("postal_code");
		String province = request.getParameter("province");
		String country = request.getParameter("country");
		InputStream companyLogo = null;
		
		Part filePart = request.getPart("company_logo");
		companyLogo = filePart.getInputStream();
		
		if(password.equals(passwordConfirm)) {
			
			company_dao.updateCompany(companyID, companyName, phoneNumber, email, city, postalCode, province, country, companyLogo);
			
			Company company = company_dao.retreiveCompany(companyID);
			company_dao.updateEmployee(company, username, password, passwordConfirm, fullName);
			
		}else {
			
			/*Redirect a JSP for 3secs to advise that passwords must match */
			
			}	
	}
	
	public void company_create_jobPosition (HttpServletRequest request, HttpServletResponse response){
		
		String jobTitle = request.getParameter("job_title");
		String jobDescription = request.getParameter("description");
		String jobDomain = request.getParameter("domain");
		String typeOfJob = request.getParameter("job_type");
		String availability = request.getParameter("availability");
		String offerSalary = request.getParameter("salary") + " " + request.getParameter("salary_freq");
		String additionalCompensation = request.getParameter("additional_compensation");
		Company company = new Company();
		company = company_dao.retreiveCompany(request.getParameter("company_id"));
		
		CompanyPosition position = new CompanyPosition(); 
		
		position.setJobTitle(jobTitle);
		position.setJobDescription(jobDescription);
		position.setJobDomain(jobDomain);
		position.setTypeOfJob(typeOfJob);
		position.setAvailability(availability);
		position.setOfferSalary(offerSalary);
		position.setAdditionalCompensation(additionalCompensation);
		position.setCompany(company);
		
		company_dao.insertJobPosition(position);
	}
	
	public void company_delete_jobPosition (HttpServletRequest request, HttpServletResponse response){
		String positionID = request.getParameter("position_id");
		
		company_dao.deleteJobPosition(positionID);
		
	}
	
	public void company_retreive_jobPosition (HttpServletRequest request, HttpServletResponse response){
		String positionID = request.getParameter("position_id");
		
		request.setAttribute("position",company_dao.retreiveJobPosition(positionID));
		
	}
	
	public void company_retreive_jobPositions (HttpServletRequest request, HttpServletResponse response){

		HttpSession session = request.getSession();

		String companyID = (String) session.getAttribute("company_id");
		
		ArrayList<CompanyPosition> positions = company_dao.retreiveJobPositionsByCompanyID(companyID);
		
		
		request.setAttribute("positions",positions);
		
	}
		
	public void company_update_jobPosition (HttpServletRequest request, HttpServletResponse response){
		
		String positionID = request.getParameter("position_id");
		
		String companyID = request.getParameter("company_id");
		
		String jobTitle = request.getParameter("job_title");
		String jobDescription = request.getParameter("description");
		String jobDomain = request.getParameter("domain");
		String typeOfJob = request.getParameter("job_type");
		String availability = request.getParameter("availability");
		String offerSalary = request.getParameter("salary") + " " + request.getParameter("salary_freq");
		String additionalCompensation = request.getParameter("additional_compensation");
		
		Company company = company_dao.retreiveCompany(companyID);
		
		
		company_dao.updateJobPosition(positionID, jobTitle, jobDescription,
				jobDomain, typeOfJob, availability, offerSalary,
				additionalCompensation, company);
	}
	
	public void company_create_PositionQualification (HttpServletRequest request, HttpServletResponse response) {

		String positionID = request.getParameter("position_id");
		
		String qualificationName = request.getParameter("qual_name");
		String desiredYears = request.getParameter("qual_years") + " " + request.getParameter("qual_years_time");
		String qualificationDescription = request.getParameter("qual_description");
		
		CompanyPosition position = company_dao.retreiveJobPosition(positionID);
		
		CPositionQualification qualification = new CPositionQualification();
		
		qualification.setPosition(position);
		qualification.setQualificationName(qualificationName);
		qualification.setDesiredYears(desiredYears);
		qualification.setQualificationDescription(qualificationDescription);

		company_dao.insertJobQualification(qualification);
	}
	
	public void company_delete_PositionQualification (HttpServletRequest request, HttpServletResponse response) {
		
		String positionID = request.getParameter("position_id");
		String qualificationName = request.getParameter("qual_name");
		
		company_dao.deleteJobQualification(positionID, qualificationName);
		
	}
	
	public void company_retreive_PositionQualifications (HttpServletRequest request, HttpServletResponse response) {
		
		String positionID = request.getParameter("position_id");
		
		ArrayList<CPositionQualification> qualifications = company_dao.retreiveJobQualificationsByPost(positionID);
		
		request.setAttribute("qualifications",qualifications);
		
	}
	
	public void company_create_PositionExperience (HttpServletRequest request, HttpServletResponse response) {

		String positionID = request.getParameter("position_id");
		
		String experienceName = request.getParameter("exp_name");
		String desiredYears = request.getParameter("exp_years") + " " + request.getParameter("exp_years_time");
		String experienceDescription = request.getParameter("exp_description");
		
		CompanyPosition position = company_dao.retreiveJobPosition(positionID);
		
		CPositionExperience experience = new CPositionExperience();
		
		experience.setPosition(position);
		experience.setExperienceName(experienceName);
		experience.setDesiredYears(desiredYears);
		experience.setExperienceDescription(experienceDescription);

		company_dao.insertJobExperience(experience);
	}
	
	public void company_delete_PositionExperience (HttpServletRequest request, HttpServletResponse response) {
		
		String positionID = request.getParameter("position_id");
		String experienceName = request.getParameter("exp_name");
		
		company_dao.deleteJobExperience(positionID, experienceName);
		
	}
	
	public void company_retreive_PositionExperiences (HttpServletRequest request, HttpServletResponse response) {
		
		String positionID = request.getParameter("position_id");
		
		ArrayList<CPositionExperience> experiences = company_dao.retreiveJobExperiencesByPost(positionID);
		
		request.setAttribute("experiences",experiences);
		
	}
	
	public void company_retreive_ApplicationsByCompany (HttpServletRequest request, HttpServletResponse response) {
		
		String companyID = request.getParameter("company_id");
		
		ArrayList<Application> applications = company_dao.retreiveApplicationsByCompany(companyID);
		
		request.setAttribute("applications",applications);
		
	}
	
	public void company_retreive_ApplicationsByPostID (HttpServletRequest request, HttpServletResponse response) {
		
		String positionID = request.getParameter("position_id");
		
		ArrayList<Application> applications = company_dao.retreiveApplicationsByPostID(positionID);
		
		request.setAttribute("applications",applications);
		
	}
	public void company_retreive_employee (HttpServletRequest request, HttpServletResponse response) {
		
		String positionID = request.getParameter("position_id");
		
		request.setAttribute("company_employee",company_dao.retreiveEmployee(positionID));
		
	}
	
	public void applicants_retreive (HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<Applicant> applicants = company_dao.retreiveApplicants();
		
		request.setAttribute("applicants",applicants);
	}
	
	public void applicant_retreive (HttpServletRequest request, HttpServletResponse response) {
		
		String applicantID = request.getParameter("applicant_id");
		
		request.setAttribute("applicant", company_dao.retreiveApplicant(applicantID));
	}
	
	public void applicant_retreive_TechSkill (HttpServletRequest request, HttpServletResponse response) {
		
		String applicantID = request.getParameter("applicant_id");
		
		ArrayList<ApplicantTechSkill> applicantTechSkills;
		
		applicantTechSkills = company_dao.retreiveAppicantTechnicalSkillsByID(applicantID);
		
		request.setAttribute("applicant_tech_skills", applicantTechSkills);
		
	}
	
	public void applicants_retreive_Experiences (HttpServletRequest request, HttpServletResponse response) {
		
		String applicantID = request.getParameter("applicant_id");
		
		ArrayList<ApplicantExperience> applicantExperiences; 
		
		applicantExperiences = company_dao.retreiveApplicantExperiencesByID(applicantID);
		
		request.setAttribute("applicant_experiences", applicantExperiences);

	}
	
	public void applicants_retreive_Educations (HttpServletRequest request, HttpServletResponse response) {
		
		String applicantID = request.getParameter("applicant_id");
		
		ArrayList<ApplicantEducation> applicantEducations; 
		
		applicantEducations = company_dao.retreiveApplicantEducationsByID(applicantID);
		
		request.setAttribute("applicant_educations", applicantEducations);

	}
	
	public void applicant_retreive_OtherSkill (HttpServletRequest request, HttpServletResponse response) {
		
		String applicantID = request.getParameter("applicant_id");
		
		ArrayList<ApplicantOtherSkill> applicantOtherSkills;
		
		applicantOtherSkills = company_dao.retreiveAppicantOtherSkillsByID(applicantID);
		
		request.setAttribute("applicant_other_skills", applicantOtherSkills);
		
	}
	
	public void company_logout (HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("company_id");
		session.removeAttribute("username");
		
	}
	
}
