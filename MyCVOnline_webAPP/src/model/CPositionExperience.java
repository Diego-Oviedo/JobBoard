package model;

public class CPositionExperience {

	private CompanyPosition position;
	
	private String experienceName, desiredYears, experienceDescription;

	public CPositionExperience() {
		super();
		
	}

	public CPositionExperience(CompanyPosition position, String experienceName, String desiredYears,
			String experienceDescription) {
		super();
		this.position = position;
		this.experienceName = experienceName;
		this.desiredYears = desiredYears;
		this.experienceDescription = experienceDescription;
	}

	public CompanyPosition getPosition() {
		return position;
	}

	public String getExperienceName() {
		return experienceName;
	}

	public String getDesiredYears() {
		return desiredYears;
	}

	public String getExperienceDescription() {
		return experienceDescription;
	}

	public void setPosition(CompanyPosition position) {
		this.position = position;
	}

	public void setExperienceName(String experienceName) {
		this.experienceName = experienceName;
	}

	public void setDesiredYears(String desiredYears) {
		this.desiredYears = desiredYears;
	}

	public void setExperienceDescription(String experienceDescription) {
		this.experienceDescription = experienceDescription;
	}

	@Override
	public String toString() {
		return "CPositionExperience [position=" + position + ", experienceName=" + experienceName + ", desiredYears="
				+ desiredYears + ", experienceDescription=" + experienceDescription + "]";
	}

	
	
}
