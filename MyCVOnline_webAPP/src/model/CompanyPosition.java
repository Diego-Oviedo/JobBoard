package model;

public class CompanyPosition {

	private Company company;
	
	private String positionID,jobTitle, jobDescription, jobDomain, typeOfJob, availability, offerSalary, additionalCompensation;

	public CompanyPosition() {
		super();
	}

	public CompanyPosition(Company company, String positionID, String jobTitle, String jobDescription, String jobDomain,
			String typeOfJob, String availability, String offerSalary, String additionalCompensation) {
		super();
		this.company = company;
		this.positionID = positionID;
		this.jobTitle = jobTitle;
		this.jobDescription = jobDescription;
		this.jobDomain = jobDomain;
		this.typeOfJob = typeOfJob;
		this.availability = availability;
		this.offerSalary = offerSalary;
		this.additionalCompensation = additionalCompensation;
	}

	public Company getCompany() {
		return company;
	}

	public String getPositionID() {
		return positionID;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public String getJobDomain() {
		return jobDomain;
	}

	public String getTypeOfJob() {
		return typeOfJob;
	}

	public String getAvailability() {
		return availability;
	}

	public String getOfferSalary() {
		return offerSalary;
	}

	public String getAdditionalCompensation() {
		return additionalCompensation;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setPositionID(String positionID) {
		this.positionID = positionID;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public void setJobDomain(String jobDomain) {
		this.jobDomain = jobDomain;
	}

	public void setTypeOfJob(String typeOfJob) {
		this.typeOfJob = typeOfJob;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public void setOfferSalary(String offerSalary) {
		this.offerSalary = offerSalary;
	}

	public void setAdditionalCompensation(String additionalCompensation) {
		this.additionalCompensation = additionalCompensation;
	}

	@Override
	public String toString() {
		return "CompanyPosition [company=" + company + ", positionID=" + positionID + ", jobTitle=" + jobTitle
				+ ", jobDescription=" + jobDescription + ", jobDomain=" + jobDomain + ", typeOfJob=" + typeOfJob
				+ ", availability=" + availability + ", offerSalary=" + offerSalary + ", additionalCompensation="
				+ additionalCompensation + "]";
	}
	
	
	
}
