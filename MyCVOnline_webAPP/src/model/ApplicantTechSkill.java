package model;

public class ApplicantTechSkill {

	@Override
	public String toString() {
		return "ApplicantTechSkill [applicant=" + applicant + ", skillName=" + skillName + "]";
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

	public ApplicantTechSkill() {
		super();
	}

	public ApplicantTechSkill(Applicant applicant, String skillName) {
		super();
		this.applicant = applicant;
		this.skillName = skillName;
	}

	

}
