package model;

public class CompanyEmployee {

	private Company company; 
	
	private String  username, password, fullName;

	public CompanyEmployee() {
		super();
		
	}

	public CompanyEmployee(Company company, String username, String password, String fullName) {
		super();
		this.company = company;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
	}

	public Company getCompany() {
		return company;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "CompanyEmployee [company=" + company + ", username=" + username + ", password=" + password
				+ ", fullName=" + fullName + "]";
	} 
	
	
	
}
