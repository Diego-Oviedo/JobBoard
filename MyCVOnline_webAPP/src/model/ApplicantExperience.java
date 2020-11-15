package model;

import java.io.InputStream;

public class ApplicantExperience {

	private Applicant applicant;
	
	private String experienceTitle, companyName, startDate, endDate, description;
	
	private InputStream expLogo;

	public ApplicantExperience() {
		super();
	}

	public ApplicantExperience(Applicant applicant, String experienceTitle, String companyName, String startDate,
			String endDate, String description, InputStream expLogo) {
		super();
		this.applicant = applicant;
		this.experienceTitle = experienceTitle;
		this.companyName = companyName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.expLogo = expLogo;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public String getExperienceTitle() {
		return experienceTitle;
	}
	public String getCompanyName() {
		return companyName;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getDescription() {
		return description;
	}

	public InputStream getExpLogo() {
		return expLogo;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public void setExperienceTitle(String experienceTitle) {
		this.experienceTitle = experienceTitle;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setExpLogo(InputStream expLogo) {
		this.expLogo = expLogo;
	}

	@Override
	public String toString() {
		return "ApplicantExperience [applicant=" + applicant + ", experienceTitle=" + experienceTitle + ", companyName="
				+ companyName + ", startDate=" + startDate + ", endDate=" + endDate + ", description=" + description
				+ ", expLogo=" + expLogo + "]";
	}
	
	

}
