package model;

import java.io.InputStream;

public class Applicant {
	
	private String applicantID, username, password, firstName, lastName, profession, phoneNumber, email, streetAddress, postalCode, city, province, country, aboutYou;
	
	private InputStream profilePicture;
	
	public Applicant() {
		super();
	}

	public Applicant(String applicantID, String username, String password, String firstName, String lastName,
			String profession, String phoneNumber, String email, String streetAddress, String postalCode, String city,
			String province, String country, String aboutYou,InputStream profilePicture) {
		super();
		this.applicantID = applicantID;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.profession = profession;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.streetAddress = streetAddress;
		this.postalCode = postalCode;
		this.city = city;
		this.province = province;
		this.country = country;
		this.aboutYou = aboutYou;
		this.profilePicture = profilePicture;
	}

	public String getApplicantID() {
		return applicantID;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getProfession() {
		return profession;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getStreetAddress() {
		return streetAddress;
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

	public String getAboutYou() {
		return aboutYou;
	}
	
	public InputStream getProfilePicture() {
		return profilePicture;
	}


	public void setApplicantID(String applicantID) {
		this.applicantID = applicantID;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
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

	public void setAboutYou(String aboutYou) {
		this.aboutYou = aboutYou;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public void setProfilePicture(InputStream profilePicture) {
		this.profilePicture = profilePicture;
	}

	@Override
	public String toString() {
		return "Applicant [applicantID=" + applicantID + ", username=" + username + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", profession=" + profession
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", streetAddress=" + streetAddress
				+ ", postalCode=" + postalCode + ", city=" + city + ", province=" + province + ", country=" + country
				+ ", aboutYou=" + aboutYou + ", profilePicture=" + profilePicture + "]";
	}
	
	

	
}
