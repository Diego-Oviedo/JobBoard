package model;

public class CPositionQualification {

	private CompanyPosition position;
	
	private String qualificationName, desiredYears, qualificationDescription;

	public CPositionQualification() {
		super();
		
	}

	public CPositionQualification(CompanyPosition position, String qualificationName, String desiredYears,
			String qualificationDescription) {
		super();
		this.position = position;
		this.qualificationName = qualificationName;
		this.desiredYears = desiredYears;
		this.qualificationDescription = qualificationDescription;
	}

	public CompanyPosition getPosition() {
		return position;
	}

	public String getQualificationName() {
		return qualificationName;
	}

	public String getDesiredYears() {
		return desiredYears;
	}

	public String getQualificationDescription() {
		return qualificationDescription;
	}

	public void setPosition(CompanyPosition position) {
		this.position = position;
	}

	public void setQualificationName(String qualificationName) {
		this.qualificationName = qualificationName;
	}

	public void setDesiredYears(String desiredYears) {
		this.desiredYears = desiredYears;
	}

	public void setQualificationDescription(String qualificationDescription) {
		this.qualificationDescription = qualificationDescription;
	}

	@Override
	public String toString() {
		return "CPositionQualification [position=" + position + ", qualificationName=" + qualificationName
				+ ", desiredYears=" + desiredYears + ", qualificationDescription=" + qualificationDescription + "]";
	}
	
	

}
