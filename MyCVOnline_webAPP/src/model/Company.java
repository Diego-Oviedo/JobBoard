package model;

import java.io.InputStream;

public class Company {

	private String companyID,companyName,phoneNumber, email, city, postalCode, province, country;
	
	private InputStream companyLogo;

	public Company() {
		super();
		
	}

	public Company(String companyID, String companyName, String phoneNumber, String email, String city,
			String postalCode, String province, String country, InputStream companyLogo) {
		super();
		this.companyID = companyID;
		this.companyName = companyName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.city = city;
		this.postalCode = postalCode;
		this.province = province;
		this.country = country;
		this.companyLogo = companyLogo;
	}

	public String getCompanyID() {
		return companyID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getCity() {
		return city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getProvince() {
		return province;
	}

	public String getCountry() {
		return country;
	}

	public InputStream getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCompanyLogo(InputStream companyLogo) {
		this.companyLogo = companyLogo;
	}

	@Override
	public String toString() {
		return "Company [companyID=" + companyID + ", companyName=" + companyName + ", phoneNumber=" + phoneNumber
				+ ", email=" + email + ", city=" + city + ", postalCode=" + postalCode + ", province=" + province
				+ ", country=" + country + ", companyLogo=" + companyLogo + "]";
	}
	
	
	
}
