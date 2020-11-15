package model;

public class ApplicantOtherSkill {

	@Override
	public String toString() {
		return "ApplicantOtherSkill [applicant=" + applicant + ", skillName=" + skillName + "]";
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	private Applicant applicant;
	
	private String skillName;

	public ApplicantOtherSkill() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApplicantOtherSkill(Applicant applicant, String skillName) {
		super();
		this.applicant = applicant;
		this.skillName = skillName;
	}
	

}
