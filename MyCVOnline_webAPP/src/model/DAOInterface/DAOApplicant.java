package model.DAOInterface;

import java.io.InputStream;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import model.*;

public interface DAOApplicant {

	/*Applicant Module*/
	
	public void insertApplicant(Applicant applicant);
	public void insertApplicantAboutYou(String applicantID,String aboutYou);
	public void deleteApplicant(String applicnatID);
	public Applicant retreiveApplicant(String applicantID);
	public Applicant retreiveUser(String username);
	public void updateApplicantAddressInformation(String applicantID, String phoneNumber, String email, String streetAddress, String postalCode, String city,String province, String country);
	public void updateApplicantAboutYou(String applicantID,String aboutYou);
	public void updateApplicantMyProfile(String applicantID, String username, String password, String confirmPassword, String firstName, String lastName,String profession,InputStream profilePicture);
	public void displayApplicantProfilePicture(String applicantID, HttpServletResponse response);
	public ArrayList<Applicant> retreiveApplicants();
	
	public void insertApplicantExperience(ApplicantExperience experience);
	public void deleteApplicantExperience(String applicantID, String experienceTitle);
	public ArrayList<ApplicantExperience> retreiveApplicantExperiencesByID(String applicantID);
	public void displayApplicantExperienceLogo(String applicantID, String experienceTitle, HttpServletResponse response);
	public void updateApplicantExperience(String applicantID, String OldexperienceTitle, String experienceTitle, String companyName,
			String startDate, String endDate, String description, InputStream expLogo);
	public ArrayList<ApplicantExperience> retreiveApplicantExperiences();
	
	public void insertApplicantEducation(ApplicantEducation education);
	public void deleteApplicantEducation(String applicantID, String educationTitle);
	public ArrayList<ApplicantEducation> retreiveApplicantEducationsByID(String applicantID);
	public void displayApplicantEducationLogo(String applicantID, String educationTitle, HttpServletResponse response);
	public void updateApplicantEducation(String applicantID,String OldeducationTitle, String educationTitle, String schoolName, String startDate,
			String endDate, String description, InputStream eduLogo);
	public ArrayList<ApplicantEducation> retreiveApplicantEducations();

	public void insertApplicantTechSkill(ApplicantTechSkill techSkill);
	public void deleteApplicantTechSkill(String applicantID, String skillName);
	public ArrayList<ApplicantTechSkill> retreiveAppicantTechnicalSkillsByID(String applicantID);
	public void updateApplicantTechSkill(String applicantID, String OldtechnicalSkill,String technicalSkill);
	public ArrayList<ApplicantTechSkill> retreiveAppicantTechnicalSkills();
	
	public void insertApplicantOtherSkill(ApplicantOtherSkill otherSkill);
	public void deleteApplicantOtherSkill(String applicantID, String skillName);
	public ArrayList<ApplicantOtherSkill> retreiveAppicantOtherSkillsByID(String applicantID);
	public void updateApplicantOtherSkill(String applicantID, String OldOtherSkill,String OtherSkill);
	public ArrayList<ApplicantOtherSkill> retreiveAppicantOtherSkills();
	
	public void insertApplication(Application application);
	public void deleteApplication(String applicationNumber);
	public int getApplicationsCount();
	public Application retreiveApplication(String applicationNumber);
	public ArrayList<Application> retreiveApplications();
	public void updateApplication(String applicantID, String applicationNumber, String applicationDate,
			String positionID);
	public ArrayList<Application> retreiveApplicationsByApplicantID(String applicantID);
	
	
	/* Company module*/
	
	public ArrayList<CompanyPosition> retreiveJobPositions();
	public CompanyPosition retreiveJobPosition(String positionID);
	public ArrayList<CPositionQualification> retreiveJobQualificationsByPost(String positionID);
	public ArrayList<CPositionExperience> retreiveJobExperiencesByPost(String positionID);
	public CompanyEmployee retreiveCompanyEmployee(String positionID);
	public Company retreiveCompany(String companyID);
}