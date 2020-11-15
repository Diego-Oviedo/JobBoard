package model;

public class Application {

	private Applicant applicant;
	
	private String applicationNumber ,applicationDate;
	 
	private CompanyPosition position;

	public Application() {
		super();
	}

	public Application(Applicant applicant, String applicationNumber, String applicationDate,
			CompanyPosition position) {
		super();
		this.applicant = applicant;
		this.applicationNumber = applicationNumber;
		this.applicationDate = applicationDate;
		this.position = position;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public String getApplicationDate() {
		return applicationDate;
	}

	public CompanyPosition getPosition() {
		return position;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	public void setPosition(CompanyPosition position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Application [applicant=" + applicant + ", applicationNumber=" + applicationNumber + ", applicationDate="
				+ applicationDate + ", position=" + position + "]";
	}
	
	
	
}
