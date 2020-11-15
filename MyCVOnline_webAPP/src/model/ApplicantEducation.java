package model;

import java.io.InputStream;

public class ApplicantEducation {

	private Applicant applicant;
	
	private String educationTitle,schoolName, startDate, endDate, description;
	
	private InputStream eduLogo;

	public ApplicantEducation() {
		super();
		
	}

	public ApplicantEducation(Applicant applicant, String educationTitle, String schoolName, String startDate,
			String endDate, String description, InputStream eduLogo) {
		super();
		this.applicant = applicant;
		this.educationTitle = educationTitle;
		this.schoolName = schoolName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.eduLogo = eduLogo;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public String getEducationTitle() {
		return educationTitle;
	}

	public String getSchoolName() {
		return schoolName;
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

	public InputStream getEduLogo() {
		return eduLogo;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public void setEducationTitle(String educationTitle) {
		this.educationTitle = educationTitle;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
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

	public void setEduLogo(InputStream eduLogo) {
		this.eduLogo = eduLogo;
	}

	@Override
	public String toString() {
		return "ApplicantEducation [applicant=" + applicant + ", educationTitle=" + educationTitle + ", schoolName="
				+ schoolName + ", startDate=" + startDate + ", endDate=" + endDate + ", description=" + description
				+ ", eduLogo=" + eduLogo + "]";
	}
	
	
}
