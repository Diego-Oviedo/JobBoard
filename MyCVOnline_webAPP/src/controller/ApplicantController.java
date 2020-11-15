package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import model.*;
import model.DAOImplementation.ApplicantImplementation;
import model.DAOInterface.DAOApplicant;

@WebServlet("/Applicant")
@MultipartConfig(maxFileSize = 16177215)
public class ApplicantController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  DAOApplicant applicant_dao;
	private static String APPLICANT_HOME = "Applicants_Home.jsp";
	private static String APPLICANT_JOB_OFFER = "Applicants_job_offer.jsp";
	private static String APPLICANT_JOB_OFFERS = "Applicants_job_offers.jsp";
	private static String APPLICANT_LOGIN = "Applicants_login.jsp";
	private static String APPLICANT_MY_APPLICATIONS = "Applicants_my_applications.jsp";
	private static String APPLICANT_MY_PROFILE_EDIT = "Applicants_my_profile_edit.jsp";
	private static String APPLICANT_EDUCATIONS = "Applicants_my_resume_education.jsp";
	private static String APPLICANT_EXPERIENCES = "Applicants_my_resume_experience.jsp";
	private static String APPLICANT_SKILLS_OTHERS = "Applicants_my_resume_other_skills.jsp";
	private static String APPLICANT_SKILLS_TECHNICALS = "Applicants_my_resume_technical_skills.jsp";
	private static String APPLICANT_MY_RESUME = "Applicants_my_resume.jsp";
	private static String APPLICANT_MY_RESUME_EDIT = "Applicants_my_resume_edit.jsp";
	String action = " ";
	
    public ApplicantController() throws ServletException, IOException, ClassNotFoundException {
        super();
        applicant_dao = new ApplicantImplementation();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doPost(request, response);}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action = request.getParameter("action");
			
		/********************************LOGIN PGAE*********************************/
		
		if(action.equalsIgnoreCase("login")) {
		
		try {
		String username_access = request.getParameter("username");
		String password_access= request.getParameter("password");
		
		Applicant applicant_access = applicant_dao.retreiveUser(username_access);
		
		String username_retreived = applicant_access.getUsername();
		String password_retreived = applicant_access.getPassword();
		
		
		if(username_access.equals(username_retreived) && password_access.equals(password_retreived)) {
			
			HttpSession session = request.getSession();
			
			String ID_retreived = applicant_access.getApplicantID();
			
			session.setAttribute("applicant_id",ID_retreived);
			applicant_retreive(request,response);
			applicant_retreive_TechSkill(request,response);
			applicants_retreive_Experiences(request,response);
			applicants_retreive_Educations(request,response);
			applicant_retreive_OtherSkill(request,response);
			RequestDispatcher view = request.getRequestDispatcher(APPLICANT_MY_RESUME);
			view.forward(request, response);
			
		}else { //if the password is not correct 
			RequestDispatcher view = request.getRequestDispatcher(APPLICANT_LOGIN);
			view.forward(request, response);
		}
		}catch(Exception e) {//if the username does not exists 
			if(e.getClass() == NullPointerException.class) {
				RequestDispatcher view = request.getRequestDispatcher(APPLICANT_LOGIN);
				view.forward(request, response);
			}else {//if another problem, sent to login page and pint-console the error
				e.printStackTrace();
				RequestDispatcher view = request.getRequestDispatcher(APPLICANT_LOGIN);
				view.forward(request, response);
			}
			
			
		}

		
		/*-----------------------------------OFFER PGAE-----------------------------------------------*/
		
		}else if (action.equalsIgnoreCase("job_offer")) {
			
			applicant_retreive(request,response);
			company_retreive_JobPosition(request,response);
			company_retreive_JobPosition_Experiences(request,response);
			company_retreive_JobPosition_Qualifications(request,response);
			company_retreive_employee(request,response);
			RequestDispatcher view = request.getRequestDispatcher(APPLICANT_JOB_OFFER);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("apply_position")) {
			
			applicant_retreive(request,response);
			applicant_create_Application(request,response);
			applicants_retreive_Applications(request,response);
			RequestDispatcher view = request.getRequestDispatcher(APPLICANT_MY_APPLICATIONS);
			view.forward(request, response);
		}
		
		/*-----------------------------------OFFERS PGAE-----------------------------------------------*/
		
		else if (action.equalsIgnoreCase("job_offers")) {
			
			applicant_retreive(request,response);
			company_retreive_JobPositions(request,response);
			RequestDispatcher view = request.getRequestDispatcher(APPLICANT_JOB_OFFERS);
			view.forward(request, response);
		}
		
		/*-----------------------------------APPLICATIONS PGAE-----------------------------------------------*/
		
		else if (action.equalsIgnoreCase("view_applications")) {
			
			applicant_retreive(request,response);
			applicants_retreive_Applications(request,response);
			RequestDispatcher view = request.getRequestDispatcher(APPLICANT_MY_APPLICATIONS);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("delete_application")) {
			
			applicant_retreive(request,response);
			applicant_delete_Application(request,response);
			applicants_retreive_Applications(request,response);
			RequestDispatcher view = request.getRequestDispatcher(APPLICANT_MY_APPLICATIONS);
			view.forward(request, response);
		}
			
		
		/*-----------------------------------ABOUT YOU SECTION-----------------------------------------------*/
		
		else if (action.equalsIgnoreCase("about_you_submit")) {
			
			applicant_update_AboutYou(request,response);
			applicant_retreive(request,response);
			applicant_retreive_TechSkill(request,response);
			applicants_retreive_Experiences(request,response);
			applicants_retreive_Educations(request,response);
			applicant_retreive_OtherSkill(request,response);
			RequestDispatcher view = request.getRequestDispatcher(APPLICANT_MY_RESUME_EDIT);
			view.forward(request, response);
		}
		
		
		/*-----------------------------------PROFILE EDIT PGAE-----------------------------------------------*/
		
		else if (action.equalsIgnoreCase("my_profile_edit")) {
			
			
			applicant_retreive(request,response);
			RequestDispatcher view = request.getRequestDispatcher(APPLICANT_MY_PROFILE_EDIT);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("my_profile_edit_submit")) {
			
			applicant_update_MyProfile(request,response);
			applicant_retreive(request,response);
			RequestDispatcher view = request.getRequestDispatcher(APPLICANT_MY_PROFILE_EDIT);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("address_info_submit")) {
			
			applicant_update_AddressInformation(request,response);
			applicant_retreive(request,response);
			RequestDispatcher view = request.getRequestDispatcher(APPLICANT_MY_PROFILE_EDIT);
			view.forward(request, response);
		}
		
		/*-----------------------------------EDUCATION SECTION-----------------------------------------------*/
		
		else if (action.equalsIgnoreCase("applicant_education")) {
			
			applicant_retreive(request,response);
			applicants_retreive_Educations(request,response);
			RequestDispatcher view = request.getRequestDispatcher(APPLICANT_EDUCATIONS);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("applicant_education_delete")) {
			
			applicant_delete_Education(request,response);
			applicant_retreive(request,response);
			applicant_retreive_TechSkill(request,response);
			applicants_retreive_Experiences(request,response);
			applicants_retreive_Educations(request,response);
			applicant_retreive_OtherSkill(request,response);
			RequestDispatcher view = request.getRequestDispatcher(APPLICANT_MY_RESUME_EDIT);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("applicant_education_submit")) {
			
			applicant_create_Education(request,response);
			applicant_retreive(request,response);
			applicant_retreive_TechSkill(request,response);
			applicants_retreive_Experiences(request,response);
			applicants_retreive_Educations(request,response);
			applicant_retreive_OtherSkill(request,response);
			RequestDispatcher view = request.getRequestDispatcher(APPLICANT_MY_RESUME_EDIT);
			view.forward(request, response);
		}
		
		/*-----------------------------------EXPERIENCE SECTION-----------------------------------------------*/
		
		else if (action.equalsIgnoreCase("applicant_experience")) {
			
			applicant_retreive(request,response);
			applicants_retreive_Experiences(request,response);
			RequestDispatcher view = request.getRequestDispatcher(APPLICANT_EXPERIENCES);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("applicant_experience_delete")) {
			
			applicant_delete_Experience(request,response);
			applicant_retreive(request,response);
			applicant_retreive_TechSkill(request,response);
			applicants_retreive_Experiences(request,response);
			applicants_retreive_Educations(request,response);
			applicant_retreive_OtherSkill(request,response);
			RequestDispatcher view = request.getRequestDispatcher(APPLICANT_MY_RESUME_EDIT);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("applicant_experience_submit")) {
			
			applicant_create_Experience(request,response);
			applicant_retreive(request,response);
			applicant_retreive_TechSkill(request,response);
			applicants_retreive_Experiences(request,response);
			applicants_retreive_Educations(request,response);
			applicant_retreive_OtherSkill(request,response);
			RequestDispatcher view = request.getRequestDispatcher(APPLICANT_MY_RESUME_EDIT);
			view.forward(request, response);
		}
		
		/*-----------------------------------OTHER SKILLS SECTION-----------------------------------------------*/
		
		else if (action.equalsIgnoreCase("other_skills")) {
			
			applicant_retreive_OtherSkill(request,response);
			applicant_retreive(request,response);
			RequestDispatcher view = request.getRequestDispatcher(APPLICANT_SKILLS_OTHERS);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("other_skill_delete")) {
	
			applicant_delete_OtherSkill(request,response);
			applicant_retreive(request,response);
			applicant_retreive_TechSkill(request,response);
			applicants_retreive_Experiences(request,response);
			applicants_retreive_Educations(request,response);
			applicant_retreive_OtherSkill(request,response);
			RequestDispatcher view = request.getRequestDispatcher(APPLICANT_MY_RESUME_EDIT);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("other_skill_submit")) {
			
			applicant_create_OtherSkill(request,response);
			applicant_retreive(request,response);
			applicant_retreive_TechSkill(request,response);
			applicants_retreive_Experiences(request,response);
			applicants_retreive_Educations(request,response);
			applicant_retreive_OtherSkill(request,response);
			RequestDispatcher view = request.getRequestDispatcher(APPLICANT_MY_RESUME_EDIT);
			view.forward(request, response);
		}
		
		/*-----------------------------------TECH SKILLS SECTION-----------------------------------------------*/
		
		else if (action.equalsIgnoreCase("tech_skills")) {
			
			applicant_retreive_TechSkill(request,response);
			applicant_retreive(request,response);
			RequestDispatcher view = request.getRequestDispatcher(APPLICANT_SKILLS_TECHNICALS);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("tech_skill_delete")) {
	
			applicant_delete_TechSkill(request,response);
			applicant_retreive(request,response);
			applicant_retreive_TechSkill(request,response);
			applicants_retreive_Experiences(request,response);
			applicants_retreive_Educations(request,response);
			applicant_retreive_OtherSkill(request,response);
			RequestDispatcher view = request.getRequestDispatcher(APPLICANT_MY_RESUME_EDIT);
			view.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("tech_skill_submit")) {
			
			applicant_create_TechSkill(request,response);
			applicant_retreive(request,response);
			applicant_retreive_TechSkill(request,response);
			applicants_retreive_Experiences(request,response);
			applicants_retreive_Educations(request,response);
			applicant_retreive_OtherSkill(request,response);
			RequestDispatcher view = request.getRequestDispatcher(APPLICANT_MY_RESUME_EDIT);
			view.forward(request, response);
		}
		
		/*-----------------------------------MY RESUME PGAE-----------------------------------------------*/

		else if (action.equalsIgnoreCase("applicant_resume")) {
			
			applicant_retreive(request,response);
			applicant_retreive_TechSkill(request,response);
			applicants_retreive_Experiences(request,response);
			applicants_retreive_Educations(request,response);
			applicant_retreive_OtherSkill(request,response);
			RequestDispatcher view = request.getRequestDispatcher(APPLICANT_MY_RESUME);
			view.forward(request, response);
		}
		
		/*-----------------------------------MY RESUME EDIT PGAE-----------------------------------------------*/

		else if (action.equalsIgnoreCase("applicant_resume_edit")) {
			
			applicant_retreive(request,response);
			applicant_retreive_TechSkill(request,response);
			applicants_retreive_Experiences(request,response);
			applicants_retreive_Educations(request,response);
			applicant_retreive_OtherSkill(request,response);
			RequestDispatcher view = request.getRequestDispatcher(APPLICANT_MY_RESUME_EDIT);
			view.forward(request, response);
		}
		 
		/*********************************HOME PGAE*********************************/
		else if (action.equalsIgnoreCase("applicants_home")) {
			
			company_retreive_JobPositions(request,response);
			RequestDispatcher view = request.getRequestDispatcher(APPLICANT_HOME);
			view.forward(request, response);
		}
		/********************************REGISTER PGAE*********************************/
		
		else if (action.equalsIgnoreCase("create_applicant")) {
			
			applicant_create(request,response);
			applicant_retreive(request,response);
			RequestDispatcher view = request.getRequestDispatcher(APPLICANT_LOGIN);
			view.forward(request, response);
		
		}
		/********************************LOGOUT*********************************/
		
		else if (action.equalsIgnoreCase("logout")) {
			
			applicant_logout(request,response);
			company_retreive_JobPositions(request,response);
			RequestDispatcher view = request.getRequestDispatcher(APPLICANT_HOME);
			view.forward(request, response);
		
		}

		
	}

	
	public void applicant_create (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("confirm_password");
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String profession = request.getParameter("profession");
		String phoneNumber = request.getParameter("phone_number");
		String email = request.getParameter("email");
		String streetAddress = request.getParameter("street_address");
		String postalCode = request.getParameter("postal_code");
		String city = request.getParameter("city");
		String province = request.getParameter("province");
		String country = request.getParameter("country");
		
		ServletContext context = getServletContext();
		String path = context.getRealPath("/WEB-INF/CSS/CSS_Pictures/contact_picture_default.png");
		File file = new File(path);
		InputStream profilePicture = new FileInputStream(file);

		if(password.equals(passwordConfirm)) {
			Applicant applicant = new Applicant();
			applicant.setUsername(username);
			applicant.setPassword(passwordConfirm);
			applicant.setFirstName(firstName);
			applicant.setLastName(lastName);
			applicant.setProfession(profession);
			applicant.setPhoneNumber(phoneNumber);
			applicant.setEmail(email);
			applicant.setStreetAddress(streetAddress);
			applicant.setPostalCode(postalCode);
			applicant.setCity(city);
			applicant.setProvince(province);
			applicant.setCountry(country);
			applicant.setProfilePicture(profilePicture);
			
			applicant_dao.insertApplicant(applicant);
			
		
		}else {
			
				/*Redirect a JSP for 3secs to advise that passwords must match */
			
		}
		
	}
	
	public void applicant_create_aboutYou (HttpServletRequest request, HttpServletResponse response) {
		
		String applicantID = request.getParameter("applicant_id");
		String aboutYou = request.getParameter("about_you");
		
		applicant_dao.insertApplicantAboutYou(applicantID, aboutYou);
		
	}
	
	public void applicant_delete (HttpServletRequest request, HttpServletResponse response) {
		
		String applicantID = request.getParameter("applicant_id");
		
		applicant_dao.deleteApplicant(applicantID);
		
	}
	
	public void applicant_retreive (HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		String applicantID = (String) session.getAttribute("applicant_id");
		
		request.setAttribute("applicant", applicant_dao.retreiveApplicant(applicantID));
	}
	
	public void applicant_update_AddressInformation (HttpServletRequest request, HttpServletResponse response) {
		
		String applicantID = request.getParameter("applicant_id");

		String phoneNumber = request.getParameter("phone_number");
		String email = request.getParameter("email");
		String streetAddress = request.getParameter("street_address");
		String postalCode = request.getParameter("postal_code");
		String city = request.getParameter("city");
		String province = request.getParameter("province");
		String country = request.getParameter("country");

		
		applicant_dao.updateApplicantAddressInformation(applicantID, phoneNumber, email, streetAddress, postalCode, city,province,country);
		
	}
	
	public void applicant_update_AboutYou (HttpServletRequest request, HttpServletResponse response) {
		
		String applicantID = request.getParameter("applicant_id");

		String aboutYou = request.getParameter("about_you");
	
		
		applicant_dao.updateApplicantAboutYou(applicantID, aboutYou);
		
	}
	
	public void applicant_update_MyProfile (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String applicantID = request.getParameter("applicant_id");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm_password");
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String profession = request.getParameter("profession");
		
		InputStream profilePicture = null;

        Part filePart = request.getPart("profile_picture");
        profilePicture = filePart.getInputStream();
		
			applicant_dao.updateApplicantMyProfile(applicantID, username, password, confirmPassword, firstName, lastName, profession,profilePicture);

	}
	
	public void applicants_retreive (HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<Applicant> applicants = applicant_dao.retreiveApplicants();
		
		request.setAttribute("applicants",applicants);
	}
	
	public void applicant_create_TechSkill (HttpServletRequest request, HttpServletResponse response) {
		
		String applicantID = request.getParameter("applicant_id");
		String skillName = request.getParameter("skill_name");
		
		Applicant applicant = applicant_dao.retreiveApplicant(applicantID);
		
		ApplicantTechSkill techskill = new ApplicantTechSkill();
		
		techskill.setApplicant(applicant);
		techskill.setSkillName(skillName);
		
		applicant_dao.insertApplicantTechSkill(techskill);
	}
	
	public void applicant_retreive_TechSkill (HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		String applicantID = (String) session.getAttribute("applicant_id");
		
		ArrayList<ApplicantTechSkill> applicantTechSkills;
		
		applicantTechSkills = applicant_dao.retreiveAppicantTechnicalSkillsByID(applicantID);
		
		request.setAttribute("applicant_tech_skills", applicantTechSkills);
		
	}
	
	public void applicant_delete_TechSkill (HttpServletRequest request, HttpServletResponse response) {
		
		String applicantID = request.getParameter("applicant_id");
		String skillName = request.getParameter("skill_name");
		
		applicant_dao.deleteApplicantTechSkill(applicantID, skillName);
		
	}
	
	public void applicant_create_Experience (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String applicantID = request.getParameter("applicant_id");
		String experienceTitle = request.getParameter("experience_title");
		String companyName = request.getParameter("company_name");
		String startDate = request.getParameter("start_date");
		String endDate = request.getParameter("end_date");
		String description = request.getParameter("description");
		
		InputStream expLogo = null;

        Part filePart = request.getPart("exp_logo");
            expLogo = filePart.getInputStream();

    	Applicant applicant = applicant_dao.retreiveApplicant(applicantID);

    	ApplicantExperience experience = new ApplicantExperience();
    	
    	experience.setApplicant(applicant);
    	experience.setExperienceTitle(experienceTitle);
    	experience.setCompanyName(companyName);
    	experience.setStartDate(startDate);
    	experience.setEndDate(endDate);
    	experience.setDescription(description);
    	experience.setExpLogo(expLogo);
    	
    	applicant_dao.insertApplicantExperience(experience);
		
		
	}
	
	public void applicants_retreive_Experiences (HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		String applicantID = (String) session.getAttribute("applicant_id");
		
		ArrayList<ApplicantExperience> applicantExperiences; 
		
		applicantExperiences = applicant_dao.retreiveApplicantExperiencesByID(applicantID);
		
		request.setAttribute("applicant_experiences", applicantExperiences);

	}
	
	public void applicant_delete_Experience (HttpServletRequest request, HttpServletResponse response) {
		
		String applicantID = request.getParameter("applicant_id");
		
		String experienceTitle = request.getParameter("experience_title");
		
		applicant_dao.deleteApplicantExperience(applicantID, experienceTitle);
		
	}
	
	public void applicant_create_Education (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String applicantID = request.getParameter("applicant_id");
		String educationTitle = request.getParameter("education_title");
		String schoolName = request.getParameter("school_name");
		String startDate = request.getParameter("start_date");
		String endDate = request.getParameter("end_date");
		String description = request.getParameter("description");
		
		InputStream eduLogo = null;

        Part filePart = request.getPart("edu_logo");
        	eduLogo = filePart.getInputStream();

    	Applicant applicant = applicant_dao.retreiveApplicant(applicantID);

    	ApplicantEducation education = new ApplicantEducation();
    	
    	education.setApplicant(applicant);
    	education.setEducationTitle(educationTitle);
    	education.setSchoolName(schoolName);
    	education.setStartDate(startDate);
    	education.setEndDate(endDate);
    	education.setDescription(description);
    	education.setEduLogo(eduLogo);
    	applicant_dao.insertApplicantEducation(education);
		
		
	}
	
	public void applicants_retreive_Educations (HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		String applicantID = (String) session.getAttribute("applicant_id");
		
		ArrayList<ApplicantEducation> applicantEducations; 
		
		applicantEducations = applicant_dao.retreiveApplicantEducationsByID(applicantID);
		
		request.setAttribute("applicant_educations", applicantEducations);

	}
	
	public void applicant_delete_Education (HttpServletRequest request, HttpServletResponse response) {
		
		String applicantID = request.getParameter("applicant_id");
		
		String educationTitle = request.getParameter("education_title");
		
		applicant_dao.deleteApplicantEducation(applicantID, educationTitle);
		
	}

	public void applicant_create_OtherSkill (HttpServletRequest request, HttpServletResponse response) {
		
		String applicantID = request.getParameter("applicant_id");
		String skillName = request.getParameter("skill_name");
		
		Applicant applicant = applicant_dao.retreiveApplicant(applicantID);
		
		ApplicantOtherSkill otherskill = new ApplicantOtherSkill();
		
		otherskill.setApplicant(applicant);
		otherskill.setSkillName(skillName);
		
		applicant_dao.insertApplicantOtherSkill(otherskill);
	}
	
	public void applicant_retreive_OtherSkill (HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		String applicantID = (String) session.getAttribute("applicant_id");
		
		ArrayList<ApplicantOtherSkill> applicantOtherSkills;
		
		applicantOtherSkills = applicant_dao.retreiveAppicantOtherSkillsByID(applicantID);
		
		request.setAttribute("applicant_other_skills", applicantOtherSkills);
		
	}
	
	public void applicant_delete_OtherSkill (HttpServletRequest request, HttpServletResponse response) {
		
		String applicantID = request.getParameter("applicant_id");
		String skillName = request.getParameter("skill_name");
		
		applicant_dao.deleteApplicantOtherSkill(applicantID, skillName);
		
	}
	
	public void applicant_create_Application (HttpServletRequest request, HttpServletResponse response) {
	
		String applicantID = request.getParameter("applicant_id");	
		Applicant applicant = applicant_dao.retreiveApplicant(applicantID);
		
		String positionID = request.getParameter("position_id");
		CompanyPosition position = applicant_dao.retreiveJobPosition(positionID);
		
		Application application = new Application();
		application.setApplicant(applicant);
		application.setPosition(position);
		
		applicant_dao.insertApplication(application);
		
		
	}
	
	public void applicants_retreive_Applications (HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		String applicantID = (String) session.getAttribute("applicant_id");
		
		ArrayList<Application> applicantApplications; 
		
		applicantApplications = applicant_dao.retreiveApplicationsByApplicantID(applicantID);
		
		request.setAttribute("applicant_applications", applicantApplications);

	}
	
	public void applicant_delete_Application (HttpServletRequest request, HttpServletResponse response) {
		
		String applicationNumber = request.getParameter("application_number");
		
		applicant_dao.deleteApplication(applicationNumber);
		
	}
	
	public void company_retreive (HttpServletRequest request, HttpServletResponse response) {
		
		String companyID = request.getParameter("company_id");
		
		request.setAttribute("company",applicant_dao.retreiveCompany(companyID));
		
	}
	
	public void company_retreive_employee (HttpServletRequest request, HttpServletResponse response) {
		
		String positionID = request.getParameter("position_id");
		
		request.setAttribute("company_employee",applicant_dao.retreiveCompanyEmployee(positionID));
		
	}
	
	public void company_retreive_JobPositions (HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<CompanyPosition> jobPositions;
		
		jobPositions = applicant_dao.retreiveJobPositions();
		
		request.setAttribute("job_positions", jobPositions);
		
	}
	
	public void company_retreive_JobPosition (HttpServletRequest request, HttpServletResponse response) {
		
		String positionID = request.getParameter("position_id");
		
		request.setAttribute("position",applicant_dao.retreiveJobPosition(positionID));
		
	}
	
	public void company_retreive_JobPosition_Experiences (HttpServletRequest request, HttpServletResponse response) {
		
		String positionID = request.getParameter("position_id");
		
		ArrayList<CPositionExperience> job_experiences;
		
		job_experiences = applicant_dao.retreiveJobExperiencesByPost(positionID);
		
		request.setAttribute("job_post_expreiences", job_experiences);
		
	}

	public void company_retreive_JobPosition_Qualifications (HttpServletRequest request, HttpServletResponse response) {
	
	String positionID = request.getParameter("position_id");
	
	ArrayList<CPositionQualification> job_qualifications;
	
	job_qualifications = applicant_dao.retreiveJobQualificationsByPost(positionID);
	
	request.setAttribute("job_post_qualifications",job_qualifications);
	
}
	
	public void applicant_logout (HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("applicant_id");
	}
}
