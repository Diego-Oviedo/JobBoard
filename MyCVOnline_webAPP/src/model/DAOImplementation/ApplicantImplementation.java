package model.DAOImplementation;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import model.connection.Connection_database;
import model.*;
import model.DAOInterface.*;


public class ApplicantImplementation implements DAOApplicant {
	
	Connection connection;

	public ApplicantImplementation() throws ClassNotFoundException {
		connection = Connection_database.get_Connection();
	}

	//Applicant Object 
	
	public void insertApplicant(Applicant applicant) {
		CallableStatement statement;
		try { 
			statement = connection.prepareCall("CALL insert_applicant (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			 
			String username = applicant.getUsername();
			String password = applicant.getPassword();
			String firstName = applicant.getFirstName().toUpperCase();
			String lastName = applicant.getLastName().toUpperCase();
			String profession = applicant.getProfession().toUpperCase();
			String phoneNumber = applicant.getPhoneNumber();
			String email = applicant.getEmail().toUpperCase();
			String streetAddress = applicant.getStreetAddress().toUpperCase();
			String postalCode = applicant.getPostalCode().toUpperCase();
			String possibleSpace =postalCode.charAt(3)+"";			
			String city = applicant.getCity().toUpperCase();
			String province = applicant.getProvince().toUpperCase();
			String country = applicant.getCountry().toUpperCase();
			InputStream profilePicture = applicant.getProfilePicture();
			
			
			SimpleDateFormat formatter = new SimpleDateFormat("MMYY");
			Date currentDate = new Date();
			String intial1 =firstName.charAt(0)+"";
			String intial2 =lastName.charAt(0)+"";
			String applicantID = intial1.toUpperCase() + intial2.toUpperCase() + formatter.format(currentDate) + (getApplicantsCount()+1); 
			
			
			statement.setString(1, applicantID);
			statement.setString(2, username);
			statement.setString(3, password);
			statement.setString(4, firstName);
			statement.setString(5, lastName);
			statement.setString(6, profession);
			statement.setString(7, phoneNumber);
			statement.setString(8, email);
			statement.setString(9, streetAddress);
			if(possibleSpace.equalsIgnoreCase(" ")) {statement.setString(10, postalCode.substring(0, 3)+postalCode.substring(4, 7));}
			else {statement.setString(10, postalCode);}
			statement.setString(11, city);
			statement.setString(12, province);
			statement.setString(13, country);
			statement.setString(14, "");
			statement.setBinaryStream(15 , profilePicture);
	
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			
			if (e.getClass() == SQLIntegrityConstraintViolationException.class) {// in order to avoid duplicated application numbers 
				
				try { 
					try {
						statement = connection.prepareCall("CALL insert_applicant (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
						
						
						String username = applicant.getUsername();
						String password = applicant.getPassword();
						String firstName = applicant.getFirstName().toUpperCase();
						String lastName = applicant.getLastName().toUpperCase();
						String profession = applicant.getProfession().toUpperCase();
						String phoneNumber = applicant.getPhoneNumber();
						String email = applicant.getEmail().toUpperCase();
						String streetAddress = applicant.getStreetAddress().toUpperCase();
						String postalCode = applicant.getPostalCode().toUpperCase();
						String possibleSpace =postalCode.charAt(3)+"";
						String city = applicant.getCity().toUpperCase();
						String province = applicant.getProvince().toUpperCase();
						String country = applicant.getCountry().toUpperCase();
						InputStream profilePicture = applicant.getProfilePicture();
						
						
						
						SimpleDateFormat formatter = new SimpleDateFormat("MMYY");
						Date currentDate = new Date();
						String intial1 =firstName.charAt(0)+"";
						String intial2 =lastName.charAt(0)+"";
						String applicantID = intial1.toUpperCase() + intial2.toUpperCase() + formatter.format(currentDate) + (getApplicantsCount()+2); //if the number exists then add one more 
						
						
						statement.setString(1, applicantID);
						statement.setString(2, username);
						statement.setString(3, password);
						statement.setString(4, firstName);
						statement.setString(5, lastName);
						statement.setString(6, profession);
						statement.setString(7, phoneNumber);
						statement.setString(8, email);
						statement.setString(9, streetAddress);
						if(possibleSpace.equalsIgnoreCase(" ")) {statement.setString(10, postalCode.substring(0, 3)+postalCode.substring(4, 7));}
						else {statement.setString(10, postalCode);}
						statement.setString(11, city);
						statement.setString(12, province);
						statement.setString(13, country);
						statement.setString(14, "");
						statement.setBinaryStream(15 , profilePicture);
				
						statement.executeUpdate();
						connection.commit();
						statement.close();
						
					
					} catch (SQLException e1) {
						
						if (e1.getClass() == SQLIntegrityConstraintViolationException.class) {
							System.out.println("username already exists, please change it");
						}else {
							e1.printStackTrace();
						}
					}

				}finally {	

				}
			
			}
	
	else {
		e.printStackTrace();
	}
		}
		
	}

	public void insertApplicantAboutYou(String applicantID,String aboutYou) {
		PreparedStatement statement;
		try { 
			statement = connection.prepareCall("UPDATE applicants SET about_you = ? WHERE applicant_id = ? ");
			
			statement.setString(1,aboutYou.substring(0, 1).toUpperCase() + aboutYou.substring(1));
			statement.setString(2,applicantID);
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getApplicantsCount() {
		int counter = 0;
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("SELECT COUNT(applicant_id) AS counter FROM APPLICANTS");
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				counter = result.getInt("counter");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return counter;
	}
	
	public void deleteApplicant(String applicnatID) {
		PreparedStatement statement;
		try { 
			statement = connection.prepareCall("DELETE FROM APPLICANTS WHERE applicant_id = ?");

			statement.setString(1, applicnatID);
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Applicant retreiveApplicant(String applicantID) {
		Applicant applicant = null;
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement("SELECT * FROM APPLICANTS WHERE applicant_id = ? ");
			
			statement.setString(1, applicantID);
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				applicant = new Applicant();
				applicant.setApplicantID(result.getString("applicant_id"));
				applicant.setUsername(result.getString("username"));
				applicant.setPassword(result.getString("password"));
				String firstName = result.getString("first_name").substring(0, 1).toUpperCase() + result.getString("first_name").substring(1).toLowerCase();
				applicant.setFirstName(firstName);
				String lastName = result.getString("last_name").substring(0, 1).toUpperCase() + result.getString("last_name").substring(1).toLowerCase();
				applicant.setLastName(lastName);
				String profession = result.getString("profession");
				if(profession != null) {
				profession = profession.substring(0, 1).toUpperCase() + profession.substring(1).toLowerCase();
				applicant.setProfession(profession);
				}else {
					applicant.setProfession("");
				}
				
				String phoneNumber = result.getString("phone_number");
				phoneNumber = "(" + phoneNumber.substring(0, 3) + ")-" + phoneNumber.substring(3, 6) + "-" + phoneNumber.substring(6, 10);
				applicant.setPhoneNumber(phoneNumber);
				String email = result.getString("email").toLowerCase();
				applicant.setEmail(email);
				String streetAddress = result.getString("street_address").substring(0, 1).toUpperCase() + result.getString("street_address").substring(1).toLowerCase();
				applicant.setStreetAddress(streetAddress);
				String postalCode = result.getString("postal_code");
				postalCode = postalCode.substring(0, 3) + " " + postalCode.substring(3, 6);
				applicant.setPostalCode(postalCode);
				String city = result.getString("city").substring(0, 1).toUpperCase() + result.getString("city").substring(1).toLowerCase();
				applicant.setCity(city);
				String province = result.getString("province").substring(0, 1).toUpperCase() + result.getString("province").substring(1).toLowerCase();
				applicant.setProvince(province);
				String country = result.getString("country").substring(0, 1).toUpperCase() + result.getString("country").substring(1).toLowerCase();
				applicant.setCountry(country);
				applicant.setAboutYou(result.getString("about_you"));
				
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return applicant;
	}
	
	public Applicant retreiveUser(String username) {
		Applicant applicant = null;
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement("SELECT * FROM APPLICANTS WHERE username = ? ");
			
			statement.setString(1, username);
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				applicant = new Applicant();
				applicant.setApplicantID(result.getString("applicant_id"));
				applicant.setUsername(result.getString("username"));
				applicant.setPassword(result.getString("password"));

			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return applicant;
	}

	public void updateApplicantAddressInformation(String applicantID, String phoneNumber, String email, String streetAddress, String postalCode, String city,String province, String country) {
		PreparedStatement statement;
		try { 
			statement = connection.prepareCall("UPDATE applicants SET phone_number = ? ,email = ? ,street_address = ? ,postal_code = ? ,city = ?,province = ? ,country = ? WHERE applicant_id = ? ");
			
			statement.setString(8,applicantID);
			statement.setString(1,phoneNumber.toUpperCase());
			statement.setString(2,email.toUpperCase());
			statement.setString(3,streetAddress.toUpperCase());
			statement.setString(4,postalCode.toUpperCase());
			statement.setString(5,city.toUpperCase());
			statement.setString(6,province.toUpperCase());
			statement.setString(7,country.toUpperCase());
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateApplicantAboutYou(String applicantID,String aboutYou) {
		PreparedStatement statement;
		try { 
			statement = connection.prepareCall("UPDATE applicants SET about_you = ? WHERE applicant_id = ? ");
			
			statement.setString(2,applicantID);
			statement.setString(1,aboutYou.substring(0, 1).toUpperCase() + aboutYou.substring(1));
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateApplicantMyProfile(String applicantID, String username, String password,  String confirmPassword, String firstName, String lastName,String profession,InputStream profilePicture) {
		PreparedStatement statement;
		try { 
			
			if(password.isEmpty()||confirmPassword.isEmpty()) { //if any field are empty
			
				Applicant applicant = retreiveApplicant(applicantID);
			
				password = applicant.getPassword();
			
				statement = connection.prepareCall("UPDATE applicants SET username = ? ,password = ? ,first_name = ? ,last_name = ? ,profession = ? ,profile_picture = ? WHERE applicant_id = ? ");
				
				statement.setString(7,applicantID);
				statement.setString(1,username);
				statement.setString(2,password);
				statement.setString(3,firstName.toUpperCase());
				statement.setString(4,lastName.toUpperCase());
				statement.setString(5,profession.toUpperCase());
				statement.setBinaryStream(6, profilePicture);

				statement.executeUpdate();
				connection.commit();
				statement.close();
			
			}else if (password.equals(confirmPassword)) {//if the fields matches 
			
				statement = connection.prepareCall("UPDATE applicants SET username = ? ,password = ? ,first_name = ? ,last_name = ? ,profession = ? ,profile_picture = ?  WHERE applicant_id = ? ");
				
				statement.setString(7,applicantID);
				statement.setString(1,username);
				statement.setString(2,confirmPassword);
				statement.setString(3,firstName.toUpperCase());
				statement.setString(4,lastName.toUpperCase());
				statement.setString(5,profession.toUpperCase());
				statement.setBinaryStream(6, profilePicture);

				statement.executeUpdate();
				connection.commit();
				statement.close();
				
			}else if (profilePicture == null) {//if the profile picture is null 
			
				statement = connection.prepareCall("UPDATE applicants SET username = ? ,password = ? ,first_name = ? ,last_name = ? ,profession = ?  WHERE applicant_id = ? ");
				
				statement.setString(7,applicantID);
				statement.setString(1,username);
				statement.setString(2,confirmPassword);
				statement.setString(3,firstName.toUpperCase());
				statement.setString(4,lastName.toUpperCase());
				statement.setString(5,profession.toUpperCase());

				statement.executeUpdate();
				connection.commit();
				statement.close();
			
		
			}else {
			
				Applicant applicant = retreiveApplicant(applicantID);
			
				password = applicant.getPassword();
			
				statement = connection.prepareCall("UPDATE applicants SET username = ? ,password = ? ,first_name = ? ,last_name = ? ,profession = ? ,profile_picture = ?  WHERE applicant_id = ? ");
				
				statement.setString(6,applicantID);
				statement.setString(1,username);
				statement.setString(2,password);
				statement.setString(3,firstName.toUpperCase());
				statement.setString(4,lastName.toUpperCase());
				statement.setString(5,profession.toUpperCase());
				statement.setBinaryStream(6, profilePicture);

				statement.executeUpdate();
				connection.commit();
				statement.close();
				
			}	
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void displayApplicantProfilePicture(String applicantID, HttpServletResponse response) {
		PreparedStatement statement;
		InputStream inputstream = null;
		OutputStream outputstream = null;
		BufferedInputStream bufferedInputstream = null;
		BufferedOutputStream bufferedOutputstream = null;
		response.setContentType("image/*");

		try {
			outputstream = response.getOutputStream();
			
			statement = connection.prepareStatement("SELECT * FROM APPLICANTS WHERE applicant_id = ?");
			
			statement.setString(1, applicantID);
			
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				inputstream = result.getBinaryStream("profile_picture");
			}else {
				System.out.println("No picture on record");
				statement.close();
			}
			bufferedInputstream = new BufferedInputStream(inputstream);
			bufferedOutputstream = new BufferedOutputStream(outputstream);
	
			int i=0;
			
			while ((i=bufferedInputstream.read()) != -1) {
				
				bufferedOutputstream.write(i);
			}

			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public ArrayList<Applicant> retreiveApplicants() {
		ArrayList<Applicant> applicants = new ArrayList<Applicant>();
		Applicant applicant = null;
		
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement("SELECT * FROM APPLICANTS");
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				applicant = new Applicant();
				applicant.setApplicantID(result.getString("applicant_id"));
				applicant.setUsername(result.getString("username"));
				applicant.setPassword(result.getString("password"));
				String firstName = result.getString("first_name").substring(0, 1).toUpperCase() + result.getString("first_name").substring(1).toLowerCase();
				applicant.setFirstName(firstName);
				String lastName = result.getString("last_name").substring(0, 1).toUpperCase() + result.getString("last_name").substring(1).toLowerCase();
				applicant.setLastName(lastName);
				String profession = result.getString("profession");
				profession = profession.substring(0, 1).toUpperCase() + profession.substring(1).toLowerCase();
				applicant.setProfession(profession);
				String phoneNumber = result.getString("phone_number");
				phoneNumber = "(" + phoneNumber.substring(0, 3) + ")-" + phoneNumber.substring(3, 6) + "-" + phoneNumber.substring(6, 10);
				applicant.setPhoneNumber(phoneNumber);
				String email = result.getString("email").toLowerCase();
				applicant.setEmail(email);
				String streetAddress = result.getString("street_address").substring(0, 1).toUpperCase() + result.getString("street_address").substring(1).toLowerCase();
				applicant.setStreetAddress(streetAddress);
				String postalCode = result.getString("postal_code");
				postalCode = postalCode.substring(0, 3) + " " + postalCode.substring(3, 6);
				applicant.setPostalCode(postalCode);
				String city = result.getString("city").substring(0, 1).toUpperCase() + result.getString("city").substring(1).toLowerCase();
				applicant.setCity(city);
				String province = result.getString("province").substring(0, 1).toUpperCase() + result.getString("province").substring(1).toLowerCase();
				applicant.setProvince(province);
				String country = result.getString("country").substring(0, 1).toUpperCase() + result.getString("country").substring(1).toLowerCase();
				applicant.setCountry(country);
				applicant.setAboutYou(result.getString("about_you"));
				
				applicants.add(applicant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return applicants;
	}

	//ApplicantExperience Object
	
	public void insertApplicantExperience(ApplicantExperience experience) {
		CallableStatement statement;
		try { 
			statement = connection.prepareCall("CALL insert_applicant_experience (?,?,?,?,?,?,?)");
			
			String applicantID = experience.getApplicant().getApplicantID();
			
			statement.setString(1,applicantID);
			statement.setString(2, experience.getExperienceTitle());
			statement.setString(3, experience.getCompanyName());
			statement.setString(4, experience.getStartDate().toUpperCase());
			statement.setString(5, experience.getEndDate().toUpperCase());
			statement.setString(6, experience.getDescription().substring(0, 1).toUpperCase()+ experience.getDescription().substring(1));
			statement.setBinaryStream(7 , experience.getExpLogo());
							
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteApplicantExperience(String applicantID, String experienceTitle) {
		PreparedStatement statement;
		try { 
			statement = connection.prepareCall("DELETE FROM APPLICANTS_EXPERIENCES WHERE applicant_id = ? AND experience_title = ?");
			
			statement.setString(1, applicantID);
			statement.setString(2, experienceTitle);
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ApplicantExperience> retreiveApplicantExperiencesByID(String applicantID) {
		ArrayList <ApplicantExperience> applicant_experiences = new ArrayList<ApplicantExperience>();
		ApplicantExperience applicant_experience = null ;
		Applicant applicant = null;
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement("SELECT * FROM APPLICANTS_EXPERIENCES WHERE applicant_id = ?");
			
			statement.setString(1, applicantID);
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				applicant_experience = new ApplicantExperience();
				
				applicant = retreiveApplicant(result.getString("applicant_id"));
				
				applicant_experience.setApplicant(applicant);
				applicant_experience.setExperienceTitle(result.getString("experience_title"));
				applicant_experience.setCompanyName(result.getString("company_name"));
				applicant_experience.setStartDate(result.getString("start_date").toUpperCase());
				applicant_experience.setEndDate(result.getString("end_date").toUpperCase());
				applicant_experience.setDescription(result.getString("description").substring(0, 1).toUpperCase() + result.getString("description").substring(1));
				applicant_experience.setExpLogo(result.getBinaryStream("exp_logo"));
				

				applicant_experiences.add(applicant_experience);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return applicant_experiences;
	}
	
	public void displayApplicantExperienceLogo(String applicantID, String experienceTitle, HttpServletResponse response) {
		PreparedStatement statement;
		InputStream inputstream = null;
		OutputStream outputstream = null;
		BufferedInputStream bufferedInputstream = null;
		BufferedOutputStream bufferedOutputstream = null;
		response.setContentType("image/*");

		try {
			outputstream = response.getOutputStream();
			
			statement = connection.prepareStatement("SELECT * FROM APPLICANTS_EXPERIENCES WHERE applicant_id = ? AND experience_title = ?");
			
			statement.setString(1, applicantID);
			statement.setString(2, experienceTitle);
			
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				inputstream = result.getBinaryStream("exp_logo");
			}else {
				System.out.println("No picture on record");
				statement.close();
			}
			bufferedInputstream = new BufferedInputStream(inputstream);
			bufferedOutputstream = new BufferedOutputStream(outputstream);
	
			int i=0;
			
			while ((i=bufferedInputstream.read()) != -1) {
				
				bufferedOutputstream.write(i);
			}

			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateApplicantExperience(String applicantID, String OldexperienceTitle, String experienceTitle, String companyName,
			String startDate, String endDate, String description, InputStream expLogo) {
		PreparedStatement statement;
		try { 
			statement = connection.prepareCall("UPDATE APPLICANTS_EXPERIENCES SET applicant_id = ?,experience_title = ?,company_name = ?,start_date = ?,end_date = ?,description = ?,exp_logo = ? WHERE applicant_id = ? AND experience_title = ?");

			statement.setString(8, applicantID);
			statement.setString(9, OldexperienceTitle);
			statement.setString(1, applicantID);
			statement.setString(2, experienceTitle);
			statement.setString(3, companyName);
			statement.setString(4, startDate);
			statement.setString(5, endDate);
			statement.setString(6, description);
			statement.setBinaryStream(7, expLogo);
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<ApplicantExperience> retreiveApplicantExperiences() {
		ArrayList <ApplicantExperience> applicant_experiences = new ArrayList<ApplicantExperience>();
		ApplicantExperience applicant_experience = null ;
		Applicant applicant = null;
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement("SELECT applicant_id,experience_title,company_name,start_date,end_date,description,exp_logo FROM APPLICANTS_EXPERIENCES ");
			
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				applicant_experience = new ApplicantExperience();
				applicant = retreiveApplicant(result.getString("applicant_id"));
				
				applicant_experience.setApplicant(applicant);
				applicant_experience.setExperienceTitle(result.getString("experience_title"));
				applicant_experience.setCompanyName(result.getString("company_name"));
				applicant_experience.setStartDate(result.getString("start_date"));
				applicant_experience.setEndDate(result.getString("end_date"));
				applicant_experience.setDescription(result.getString("description"));
				applicant_experience.setExpLogo(result.getBinaryStream("exp_logo"));
				
				applicant_experiences.add(applicant_experience);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return applicant_experiences;
	}

	//ApplicantEducation Object
	
	public void insertApplicantEducation(ApplicantEducation education) {
		CallableStatement statement;
		try { 
			statement = connection.prepareCall("CALL insert_applicant_education (?,?,?,?,?,?,?)");

			String applicantID = education.getApplicant().getApplicantID();
			
			statement.setString(1,applicantID);
			statement.setString(2, education.getEducationTitle());
			statement.setString(3, education.getSchoolName());
			statement.setString(4, education.getStartDate().toUpperCase());
			statement.setString(5, education.getEndDate().toUpperCase());
			statement.setString(6, education.getDescription().substring(0, 1).toUpperCase()+ education.getDescription().substring(1));
			statement.setBinaryStream(7 , education.getEduLogo());
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void deleteApplicantEducation(String applicantID, String educationTitle) {
		PreparedStatement statement;
		try { 
			statement = connection.prepareCall("DELETE FROM APPLICANTS_EDUCATION WHERE applicant_id = ? AND education_title = ?");
			
			statement.setString(1, applicantID);
			statement.setString(2, educationTitle);
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public ArrayList<ApplicantEducation> retreiveApplicantEducationsByID(String applicantID) {
		ArrayList <ApplicantEducation> applicant_educations = new ArrayList<ApplicantEducation>();
		ApplicantEducation applicant_education = null ;
		Applicant applicant = null;
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement("SELECT * FROM APPLICANTS_EDUCATION WHERE applicant_id = ?");
			
			statement.setString(1, applicantID);
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				applicant_education = new ApplicantEducation();
				
				applicant = retreiveApplicant(result.getString("applicant_id"));
				
				applicant_education.setApplicant(applicant);
				applicant_education.setEducationTitle(result.getString("education_title"));
				applicant_education.setSchoolName(result.getString("school_name"));
				applicant_education.setStartDate(result.getString("start_date").toUpperCase());
				applicant_education.setEndDate(result.getString("end_date").toUpperCase());
				applicant_education.setDescription(result.getString("description").substring(0, 1).toUpperCase() + result.getString("description").substring(1));
				applicant_education.setEduLogo(result.getBinaryStream("edu_logo"));
				

				applicant_educations.add(applicant_education);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return applicant_educations;
	}

	public void displayApplicantEducationLogo(String applicantID, String educationTitle, HttpServletResponse response) {
		PreparedStatement statement;
		InputStream inputstream = null;
		OutputStream outputstream = null;
		BufferedInputStream bufferedInputstream = null;
		BufferedOutputStream bufferedOutputstream = null;
		response.setContentType("image/*");

		try {
			outputstream = response.getOutputStream();
			
			statement = connection.prepareStatement("SELECT * FROM APPLICANTS_EDUCATION WHERE applicant_id = ? AND education_title = ?");
			
			statement.setString(1, applicantID);
			statement.setString(2, educationTitle);
			
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				inputstream = result.getBinaryStream("edu_logo");
			}else {
				System.out.println("No picture on record");
				statement.close();
			}
			bufferedInputstream = new BufferedInputStream(inputstream);
			bufferedOutputstream = new BufferedOutputStream(outputstream);
			
			int i=0;
			
			while ((i=bufferedInputstream.read()) != -1) {
				
				bufferedOutputstream.write(i);
			}

			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateApplicantEducation(String applicantID,String OldeducationTitle, String educationTitle, String schoolName, String startDate,
			String endDate, String description, InputStream eduLogo) {
		PreparedStatement statement;
		try { 
			statement = connection.prepareCall("UPDATE APPLICANTS_EDUCATION SET applicant_id = ?,education_title = ?,school_name = ?,start_date = ?,end_date = ?,description = ?,edu_logo = ? WHERE applicant_id = ? AND education_title = ?");
			
			statement.setString(8,applicantID);
			statement.setString(9,OldeducationTitle);
			statement.setString(1,applicantID);
			statement.setString(2, educationTitle);
			statement.setString(3, schoolName);
			statement.setString(4, startDate);
			statement.setString(5, endDate);
			statement.setString(6, description);
			statement.setBinaryStream(7 , eduLogo);
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ApplicantEducation> retreiveApplicantEducations() {
		ArrayList <ApplicantEducation> applicant_educations = new ArrayList<ApplicantEducation>();
		ApplicantEducation applicant_education = null ;
		Applicant applicant = null;
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement("SELECT * FROM APPLICANTS_EDUCATION");

			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				applicant_education = new ApplicantEducation();
				
				applicant = retreiveApplicant(result.getString("applicant_id"));
				
				applicant_education.setApplicant(applicant);
				applicant_education.setEducationTitle(result.getString("education_title"));
				applicant_education.setSchoolName(result.getString("school_name"));
				applicant_education.setStartDate(result.getString("start_date"));
				applicant_education.setEndDate(result.getString("end_date"));
				applicant_education.setDescription(result.getString("description"));
				applicant_education.setEduLogo(result.getBinaryStream("edu_logo"));
				

				applicant_educations.add(applicant_education);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return applicant_educations;
	}

	//Applicant Tech-Skill Object
	
	public void insertApplicantTechSkill(ApplicantTechSkill techSkill) {
		CallableStatement statement;
		try { 
			statement = connection.prepareCall("CALL insert_tech_skill (?,?)");

			String applicantID = techSkill.getApplicant().getApplicantID();
			
			statement.setString(1,applicantID);
			statement.setString(2, techSkill.getSkillName());
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (Exception e) {
			if (e.getClass() == StringIndexOutOfBoundsException.class) {
				
				
			}
			
			e.printStackTrace();
		}
	}

	public void deleteApplicantTechSkill(String applicantID, String skillName) {
		PreparedStatement statement;
		try { 
			statement = connection.prepareCall("DELETE FROM APPLICANTS_TECH_SKILLS WHERE applicant_id = ? AND skill_name = ? ");

			statement.setString(1, applicantID);
			statement.setString(2, skillName);
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ApplicantTechSkill> retreiveAppicantTechnicalSkillsByID(String applicantID) {
		ArrayList<ApplicantTechSkill> techSkills =  new ArrayList<ApplicantTechSkill>();
		Applicant applicant = null;
		ApplicantTechSkill techSkill;
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement("SELECT * FROM APPLICANTS_TECH_SKILLS WHERE applicant_id = ? ");
			
			statement.setString(1, applicantID);
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				techSkill = new ApplicantTechSkill();
				
				applicant = retreiveApplicant(result.getString("applicant_id"));
				
				techSkill.setApplicant(applicant);
				techSkill.setSkillName(result.getString("skill_name"));
				
				techSkills.add(techSkill);
				
			}
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		return techSkills;
	}

	public void updateApplicantTechSkill(String applicantID, String OldtechnicalSkill,String technicalSkill) {

		PreparedStatement statement;
		try { 
			statement = connection.prepareCall("UPDATE APPLICANTS_TECH_SKILLS SET skill_name = ? WHERE  applicant_id = ? AND skill_name = ?");

			statement.setString(1, technicalSkill);
			statement.setString(2, applicantID);
			statement.setString(3, OldtechnicalSkill);
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ApplicantTechSkill> retreiveAppicantTechnicalSkills() {
		ArrayList<ApplicantTechSkill> techSkills =  new ArrayList<ApplicantTechSkill>();
		Applicant applicant = null;
		ApplicantTechSkill techSkill;
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement("SELECT * FROM APPLICANTS_TECH_SKILLS ");
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				techSkill = new ApplicantTechSkill();
				
				applicant = retreiveApplicant(result.getString("applicant_id"));
				
				techSkill.setApplicant(applicant);
				techSkill.setSkillName(result.getString("skill_name"));
				
				techSkills.add(techSkill);
				
			}
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		return techSkills;
	}
	
	
	//Applicant Other-Skill Object
	
	public void insertApplicantOtherSkill(ApplicantOtherSkill otherSkill) {
		CallableStatement statement;
		try { 
			statement = connection.prepareCall("CALL insert_other_skill (?,?)");

			String applicantID = otherSkill.getApplicant().getApplicantID();
			
			statement.setString(1,applicantID);
			statement.setString(2, otherSkill.getSkillName());
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		}catch (Exception e) {
			if (e.getClass() == StringIndexOutOfBoundsException.class) {
				
				
			}
			
			e.printStackTrace();
		}
	}

	
	public void deleteApplicantOtherSkill(String applicantID, String skillName) {
		PreparedStatement statement;
		try { 
			statement = connection.prepareCall("DELETE FROM APPLICANTS_OTHER_SKILLS WHERE applicant_id = ? AND skill_name = ? ");

			statement.setString(1, applicantID);
			statement.setString(2, skillName);
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public ArrayList<ApplicantOtherSkill> retreiveAppicantOtherSkillsByID(String applicantID) {
		ArrayList<ApplicantOtherSkill> otherSkills =  new ArrayList<ApplicantOtherSkill>();
		Applicant applicant = null;
		ApplicantOtherSkill otherSkill;
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement("SELECT * FROM APPLICANTS_OTHER_SKILLS WHERE applicant_id = ? ");
			
			statement.setString(1, applicantID);
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				otherSkill = new ApplicantOtherSkill();
				
				applicant = retreiveApplicant(result.getString("applicant_id"));
				
				otherSkill.setApplicant(applicant);
				otherSkill.setSkillName(result.getString("skill_name"));
				
				otherSkills.add(otherSkill);
				
			}
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		return otherSkills;
	}

	
	public void updateApplicantOtherSkill(String applicantID, String OldOtherSkill,String OtherSkill) {
		PreparedStatement statement;
		try { 
			statement = connection.prepareCall("UPDATE APPLICANTS_OTHER_SKILLS  SET skill_name = ? WHERE  applicant_id = ? AND skill_name = ?");

			statement.setString(1, OtherSkill);
			statement.setString(2, applicantID);
			statement.setString(3, OldOtherSkill);
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	public ArrayList<ApplicantOtherSkill> retreiveAppicantOtherSkills() {
		ArrayList<ApplicantOtherSkill> otherSkills =  new ArrayList<ApplicantOtherSkill>();
		Applicant applicant = null;
		ApplicantOtherSkill otherSkill;
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement("SELECT * FROM APPLICANTS_OTHER_SKILLS");
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				otherSkill = new ApplicantOtherSkill();
				
				applicant = retreiveApplicant(result.getString("applicant_id"));
				
				otherSkill.setApplicant(applicant);
				otherSkill.setSkillName(result.getString("skill_name"));
				
				otherSkills.add(otherSkill);
				
			}
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		return otherSkills;
	}

	/*APPLICATIONS MODULE*/

	//Applicant Application Object
	
	public void insertApplication(Application application) {
		CallableStatement statement;
		try { 
			statement = connection.prepareCall("CALL insert_application (?,?,?)");
			
			SimpleDateFormat formatter = new SimpleDateFormat("MMYY");
			Date currentDate = new Date();
			String applicationNUmber = null;
			
			applicationNUmber = "A" + formatter.format(currentDate) + (getApplicationsCount()+1);//to the count of the applications add one 
			
			statement.setString(1, applicationNUmber);
			statement.setString(2, application.getApplicant().getApplicantID());
			statement.setString(3, application.getPosition().getPositionID());
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			
				if (e.getClass() == SQLIntegrityConstraintViolationException.class) {// in order to avoid duplicated application numbers 
						try { 
							try {
								statement = connection.prepareCall("CALL insert_application (?,?,?)");
							
							
							SimpleDateFormat formatter = new SimpleDateFormat("MMYY");
							Date currentDate = new Date();
							String applicationNUmber = null;
							
							applicationNUmber = "A" + formatter.format(currentDate) + (getApplicationsCount()+2);//if the number exists then add one more 
							
							statement.setString(1, applicationNUmber);
							statement.setString(2, application.getApplicant().getApplicantID());
							statement.setString(3, application.getPosition().getPositionID());
							
							statement.executeUpdate();
							connection.commit();
							statement.close();
							
							} catch (SQLException e1) {
								
								e1.printStackTrace();
							}
		
						}finally {	
		
						}
					
					}
			
			else {
				e.printStackTrace();
			}
		}
		
	}
		
	public int getApplicationsCount() {//this method will get the number of applications, in order to later concatenate it with the application number, so it will create a sequence 
		int counter = 0;
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("SELECT COUNT(application_number) AS counter FROM APPLICATIONS");
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				counter = result.getInt("counter");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return counter;
	}
	
	public void deleteApplication(String applicationNumber) {
		PreparedStatement statement;
		try { 
			statement = connection.prepareCall("DELETE FROM APPLICATIONS WHERE application_number = ?");

			statement.setString(1, applicationNumber);
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Application retreiveApplication(String applicationNumber) {
		Application application = null;
		Applicant applicant = null;
		CompanyPosition position = null;
		
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement("SELECT application_number, TO_CHAR(application_date,'DY DD, MON YYYY') AS app_date,applicant_id,position_id FROM APPLICATIONS WHERE application_number = ?");
			
			statement.setString(1, applicationNumber);
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				application = new Application();
				
				applicant = retreiveApplicant(result.getString("applicant_id"));
				position = retreiveJobPosition(result.getString("position_id"));
				
				application.setApplicationNumber(result.getString("application_number"));
				application.setApplicationDate(result.getString("app_date").substring(0, 1).toUpperCase()+result.getString("app_date").substring(1,3).toLowerCase()+result.getString("app_date").substring(3, 9).toUpperCase()+result.getString("app_date").substring(9, 16).toLowerCase());
				application.setApplicant(applicant);
				application.setPosition(position);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return application;
	}
	
	public ArrayList<Application> retreiveApplications() {
		ArrayList<Application> applications = new ArrayList<Application>();
		Application application = null;
		Applicant applicant = null;
		CompanyPosition position = null;
		
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement("SELECT application_number, TO_CHAR(application_date,'DY DD, MON YYYY') AS app_date,applicant_id,position_id FROM APPLICATIONS");

			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				application = new Application();
				
				applicant = retreiveApplicant(result.getString("applicant_id"));
				position = retreiveJobPosition(result.getString("position_id"));
				
				application.setApplicationNumber(result.getString("application_number"));
				application.setApplicationDate(result.getString("app_date").substring(0, 1).toUpperCase()+result.getString("app_date").substring(1,3).toLowerCase()+result.getString("app_date").substring(3, 9).toUpperCase()+result.getString("app_date").substring(9, 16).toLowerCase());
				application.setApplicant(applicant);
				application.setPosition(position);
				
				applications.add(application);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return applications;
	}

	public void updateApplication(String applicantID, String applicationNumber, String applicationDate,
			String positionID) {

		PreparedStatement statement;

		try {
			statement = connection.prepareStatement("UPDATE APPLICATIONS SET application_date = ? , applicant_id = ? , position_id = ? WHERE application_number = ?");
			
			statement.setString(1, applicationDate);
			statement.setString(2, applicantID);
			statement.setString(3, positionID);
			statement.setString(4, applicationNumber);
			
			statement.executeUpdate();
			connection.commit();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Application> retreiveApplicationsByApplicantID(String applicantID) {
		ArrayList<Application> applications = new ArrayList<Application>();
		Application application = null;
		Applicant applicant = null;
		CompanyPosition position = null;
		
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement("SELECT application_number, TO_CHAR(application_date,'DY DD, MON YYYY') AS app_date,applicant_id,position_id FROM APPLICATIONS WHERE applicant_id = ?");
			
			statement.setString(1, applicantID);
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				application = new Application();
				
				applicant = retreiveApplicant(result.getString("applicant_id"));
				position = retreiveJobPosition(result.getString("position_id"));
				
				application.setApplicationNumber(result.getString("application_number"));
				application.setApplicationDate(result.getString("app_date").substring(0, 1).toUpperCase()+result.getString("app_date").substring(1,3).toLowerCase()+result.getString("app_date").substring(3, 9).toUpperCase()+result.getString("app_date").substring(9, 16).toLowerCase());
				application.setApplicant(applicant);
				application.setPosition(position);
				
				applications.add(application);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return applications;
	}
	

	/*COMPANY MODULE*/
	
	//CompanyPosition Object
	
	
	//Jobs Objects
	
	public ArrayList<CompanyPosition> retreiveJobPositions() {
		ArrayList<CompanyPosition> positions = new ArrayList<CompanyPosition>();
		CompanyPosition position = null;
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement("SELECT * FROM COMPANIES_POSITIONS ORDER BY position_id");
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				position = new CompanyPosition();	
				
				position.setPositionID(result.getString("position_id"));
				position.setJobTitle(result.getString("job_title"));
				position.setJobDescription(result.getString("job_description"));
				position.setJobDomain(result.getString("job_domain"));
				position.setTypeOfJob(result.getString("type_of_job"));
				position.setAvailability(result.getString("availability"));
				position.setOfferSalary(result.getString("offer_salary"));
				position.setAdditionalCompensation(result.getString("additional_compensation"));
				position.setCompany(retreiveCompany(result.getString("company_id")));
				
				
				positions.add(position);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return positions;
	}
	
	public CompanyPosition retreiveJobPosition(String positionID) {
		CompanyPosition position = null;
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement("SELECT * FROM COMPANIES_POSITIONS WHERE position_id = ?");
			
			statement.setString(1, positionID);
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				position = new CompanyPosition();	
				
				position.setPositionID(result.getString("position_id"));
				position.setJobTitle(result.getString("job_title"));
				position.setJobDescription(result.getString("job_description"));
				position.setJobDomain(result.getString("job_domain"));
				position.setTypeOfJob(result.getString("type_of_job"));
				position.setAvailability(result.getString("availability"));
				position.setOfferSalary(result.getString("offer_salary"));
				position.setAdditionalCompensation(result.getString("additional_compensation"));
				position.setCompany(retreiveCompany(result.getString("company_id")));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return position;
	}

	//CPositionQualification Object
	
	
	public ArrayList<CPositionQualification> retreiveJobQualificationsByPost(String positionID) {
		ArrayList<CPositionQualification> qualifications = new ArrayList<CPositionQualification>();
		CPositionQualification qualification = null;
		CompanyPosition position = null;
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement("SELECT * FROM C_POSITIONS_QUALIFICATIONS WHERE position_id = ?");
			
			statement.setString(1, positionID);
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				qualification = new CPositionQualification();
				
				position = retreiveJobPosition(result.getString("position_id"));
				
				qualification.setPosition(position);
				qualification.setQualificationName(result.getString("qualification_name"));
				qualification.setDesiredYears(result.getString("desired_years"));
				qualification.setQualificationDescription(result.getString("qualification_description"));
				
				qualifications.add(qualification);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qualifications;
	}

	//CPositionExperience
	
	
	public ArrayList<CPositionExperience> retreiveJobExperiencesByPost(String positionID) {
		ArrayList<CPositionExperience> experiences = new ArrayList<CPositionExperience>();
		CPositionExperience experience = null;
		CompanyPosition position = null;
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement("SELECT * FROM C_POSITIONS_EXPERIENCES WHERE position_id = ?");
			
			statement.setString(1, positionID);
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				experience = new CPositionExperience();
				
				position = retreiveJobPosition(result.getString("position_id"));
				
				experience.setPosition(position);
				experience.setExperienceName(result.getString("experience_name"));
				experience.setDesiredYears(result.getString("experience_years"));
				experience.setExperienceDescription(result.getString("experience_description"));
				
				experiences.add(experience);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return experiences;
	}

	//CompanyEmployee
	
	
	public CompanyEmployee retreiveCompanyEmployee(String positionID) {
		CompanyEmployee employee = null;
		Company company = null;
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement("SELECT * FROM COMPANIES_EMPLOYEES WHERE employee_id in (SELECT employee_id FROM companies_positions WHERE position_id = ?)");
			
			statement.setString(1, positionID);
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				employee = new CompanyEmployee();
				company = retreiveCompany(result.getString("company_id"));
				
				employee.setCompany(company);
				employee.setUsername(result.getString("username"));
				employee.setPassword(result.getString("password"));
				employee.setFullName(result.getString("employee_full_name"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	//Company
	
	
	public Company retreiveCompany(String companyID) {
		Company company = null;
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement("SELECT *  FROM COMPANIES WHERE company_id = ?");
			
			statement.setString(1, companyID);
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				company = new Company();
				company.setCompanyID(result.getString("company_id"));
				company.setCompanyName(result.getString("company_name"));
				company.setPhoneNumber("(" + result.getString("phone_number").substring(0, 3) + ")-" + result.getString("phone_number").substring(3, 6) + "-" + result.getString("phone_number").substring(6, 10));
				company.setEmail(result.getString("email").toLowerCase());
				company.setCity(result.getString("city").substring(0, 1).toUpperCase() + result.getString("city").substring(1).toLowerCase());
				company.setPostalCode(result.getString("postal_code").substring(0, 3) + " " + result.getString("postal_code").substring(3, 6));
				company.setProvince(result.getString("province").substring(0, 1).toUpperCase() + result.getString("province").substring(1).toLowerCase());
				company.setCountry(result.getString("country").substring(0, 1).toUpperCase() + result.getString("country").substring(1).toLowerCase());
				company.setCompanyLogo(result.getBinaryStream("company_logo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}

}
