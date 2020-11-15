package model.DAOInterface;

import java.io.InputStream;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import model.*;


public interface DAOCompany {
	
	/* Company module*/
	
	public void insertCompany(Company company);
	public void deleteCompany(String companyID);
	public Company retreiveCompany(String companyID);
	public Company retreiveCompanyByName(String companyName);
	public Company retreiveCompanyByUsername(String username);
	public void displayCompanyLogo(String companyID, HttpServletResponse response);
	public void displayCompaniesLogos(String companyID, HttpServletResponse response);
	public void updateCompany(String companyID, String companyName, String phoneNumber, String email, String city,
			String postalCode, String province, String country, InputStream companyLogo);
	public ArrayList<Company> retreiveCompanies();
	
	public void insertEmployee(CompanyEmployee employee);
	public void deleteEmployee(String username);
	public CompanyEmployee retreiveEmployee(String username);
	public void updateEmployee(Company company, String username, String password, String passwordConfirm, String fullName);
	public ArrayList<CompanyEmployee> retreiveEmployees();
	
	public void insertJobPosition(CompanyPosition position);
	public void deleteJobPosition(String positionID);
	public CompanyPosition retreiveJobPosition(String positionID);
	public void updateJobPosition(String positionID, String jobTitle, String jobDescription,
			String jobDomain, String typeOfJob, String availability, String offerSalary,
			String additionalCompensation,Company company);
	public ArrayList<CompanyPosition> retreiveJobPositions();
	public ArrayList<CompanyPosition> retreiveJobPositionsByCompanyID(String companyID);
	
	public void insertJobQualification(CPositionQualification qualification);
	public void deleteJobQualification(String positionID, String qualificationName);
	public ArrayList<CPositionQualification> retreiveJobQualificationsByPost(String positionID);
	public void updateJobQualification(CompanyPosition position, String OldQualificationName, String qualificationName , String desiredYears,
			String qualificationDescription);
	public ArrayList<CPositionQualification> retreiveJobQualifications();
	
	public void insertJobExperience(CPositionExperience experience);
	public void deleteJobExperience(String positionID, String experienceName);
	public ArrayList<CPositionExperience> retreiveJobExperiencesByPost(String positionID);
	public void updateJobExperience(CompanyPosition position,String OldexperienceName, String experienceName, String desiredYears,
			String experienceDescription);
	public ArrayList<CPositionExperience> retreiveJobExperiences();
	
	/*Applicant Module*/
	
	public ArrayList<Application> retreiveApplicationsByCompany(String companyID);
	public ArrayList<Application> retreiveApplicationsByPostID(String JobPostID);
	
	public Applicant retreiveApplicant(String applicantID);
	public ArrayList<Applicant> retreiveApplicants();
	public ArrayList<ApplicantTechSkill> retreiveAppicantTechnicalSkillsByID(String applicantID);
	public ArrayList<ApplicantExperience> retreiveApplicantExperiencesByID(String applicantID);
	public ArrayList<ApplicantEducation> retreiveApplicantEducationsByID(String applicantID);
	public ArrayList<ApplicantOtherSkill> retreiveAppicantOtherSkillsByID(String applicantID);
}
