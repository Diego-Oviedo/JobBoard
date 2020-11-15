
package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAOImplementation.*;
import model.DAOInterface.*;

@WebServlet("/PicturesController")
@MultipartConfig(maxFileSize = 16177215)
public class PicturesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOApplicant applicant_dao;
	private DAOCompany company_dao;
	private String action = " ";
	 

    public PicturesController()  throws ServletException, IOException, ClassNotFoundException {
        super();
        applicant_dao = new ApplicantImplementation();
        company_dao = new CompanyImplementation();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action = request.getParameter("action");
		
		
		if (action.equalsIgnoreCase("applicant_resume_logos")) {
			
			getApplicantExpLogo(request,response);
			getApplicantEduLogo(request,response);
			
		}
		
		else if (action.equalsIgnoreCase("applicant_profile_picture")) {
			
			getApplicantProfilePicture(request,response);
		}	
		/********************************************************************/
		
		else if (action.equalsIgnoreCase("applicant_education_logos")) {
			
			getApplicantEduLogo(request,response);
			
		}else if (action.equalsIgnoreCase("applicant_experience_logos")) {
			
			getApplicantExpLogo(request,response);
			
		/********************************************************************/	
			
		}else if (action.equalsIgnoreCase("company_logo")) {
			
			getCompanyLogo(request,response);
			
		}
		
	}
	
	
	public void getApplicantExpLogo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String applicantID = request.getParameter("applicant_id");
		
		String experienceTitle = request.getParameter("experience_title");
		
		applicant_dao.displayApplicantExperienceLogo(applicantID,experienceTitle,response);
	}
	
	public void getApplicantEduLogo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String applicantID = request.getParameter("applicant_id");
			
			String educationTitle = request.getParameter("education_title");
			
			applicant_dao.displayApplicantEducationLogo(applicantID,educationTitle,response);
		}
	
	public void getCompanyLogo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String companyID = request.getParameter("company_id");
		
		company_dao.displayCompanyLogo(companyID,response);
	}
	
	public void getApplicantProfilePicture(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String applicantID = request.getParameter("applicant_id");
		
		applicant_dao.displayApplicantProfilePicture(applicantID,response);
	}
	
}
