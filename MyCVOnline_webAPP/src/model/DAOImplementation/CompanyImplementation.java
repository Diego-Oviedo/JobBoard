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

public class CompanyImplementation implements DAOCompany{

	Connection connection;
	
	public CompanyImplementation() throws ClassNotFoundException {
		connection = Connection_database.get_Connection();
	}

	//Company Object 
	
	public void insertCompany(Company company) {
		CallableStatement statement;
		try { 
			statement = connection.prepareCall("CALL insert_company(?,?,?,?,?,?,?,?,?) ");

			String companyID = null;
			String companyName = company.getCompanyName();
			String phoneNumber = company.getPhoneNumber();
			String email = company.getEmail().toUpperCase();
			String city = company.getCity().toUpperCase();
			String postalCode = company.getPostalCode().toUpperCase();
			String province = company.getProvince().toUpperCase();
			String country = company.getCountry().toUpperCase();
			InputStream companyLogo = company.getCompanyLogo();
			
			
			if(companyName.length() > 4) {
			SimpleDateFormat formatter = new SimpleDateFormat("MMYY");
			Date currentDate = new Date();
			String intial1 =companyName.charAt(0)+"";
			if(intial1.equalsIgnoreCase(" ")) {intial1 =companyName.charAt(0+1)+"";}
			String intial2 =companyName.charAt(1)+"";
			if(intial2.equalsIgnoreCase(" ")) {intial2 =companyName.charAt(1+1)+"";}
			String intial3 =companyName.charAt(3)+"";
			if(intial3.equalsIgnoreCase(" ")) {intial3 =companyName.charAt(3+1)+"";}
			String intial4 =companyName.charAt(4)+"";
			if(intial4.equalsIgnoreCase(" ")) {intial4 =companyName.charAt(4+1)+"";}
			
			companyID = intial1.toUpperCase() + intial2.toUpperCase() + intial3.toUpperCase() + intial4.toUpperCase() + formatter.format(currentDate);
			}else {
				SimpleDateFormat formatter = new SimpleDateFormat("MMYY");
				Date currentDate = new Date();
				companyID = companyName.toUpperCase() + formatter.format(currentDate);
			}
			
			
			statement.setString(1,companyID);
			statement.setString(2,companyName);
			statement.setString(3,phoneNumber);
			statement.setString(4,email);
			statement.setString(5,city);
			statement.setString(6,postalCode);
			statement.setString(7,province);
			statement.setString(8,country);
			statement.setBinaryStream(9,companyLogo);
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void deleteCompany(String companyID) {
		PreparedStatement statement;
		try { 
			statement = connection.prepareCall("DELETE FROM COMPANIES WHERE company_id = ?");

			statement.setString(1, companyID);
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

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
	
	public Company retreiveCompanyByName(String companyName) {
		Company company = null;
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement("SELECT *  FROM COMPANIES WHERE company_name = ?");
			
			statement.setString(1, companyName);
			
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
	

	public Company retreiveCompanyByUsername(String username) {
		Company company = null;
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement("SELECT *  FROM COMPANIES WHERE company_id = (SELECT company_id FROM COMPANIES_EMPLOYEES WHERE username = ?)");
			
			statement.setString(1, username);
			
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
	
	public void displayCompanyLogo(String companyID, HttpServletResponse response) {
		PreparedStatement statement;
		InputStream inputstream = null;
		OutputStream outputstream = null;
		BufferedInputStream bufferedInputstream = null;
		BufferedOutputStream bufferedOutputstream = null;
		response.setContentType("image/*");

		try {
			outputstream = response.getOutputStream();
			
			statement = connection.prepareStatement("SELECT * FROM COMPANIES WHERE company_id = ?");
			
			statement.setString(1, companyID);
			
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				inputstream = result.getBinaryStream("company_logo");
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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void displayCompaniesLogos(String companyID, HttpServletResponse response) {
		PreparedStatement statement;
		InputStream inputstream = null;
		OutputStream outputstream = null;
		BufferedInputStream bufferedInputstream = null;
		BufferedOutputStream bufferedOutputstream = null;
		response.setContentType("image/*");

		try {
			outputstream = response.getOutputStream();
			
			statement = connection.prepareStatement("SELECT * FROM COMPANIES WHERE company_id = ?");
			
			statement.setString(1, companyID);
			
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				inputstream = result.getBinaryStream("company_logo");
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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateCompany(String companyID, String companyName, String phoneNumber, String email, String city,
			String postalCode, String province, String country, InputStream companyLogo) {
		PreparedStatement statement;
		try { 
			statement = connection.prepareCall("UPDATE companies SET company_id = ?,company_name = ?,phone_number = ?,email = ?,city = ?,postal_code = ?,province =?,country = ?,company_logo = ? WHERE company_id = ?");

			statement.setString(1,companyID);
			statement.setString(2,companyName);
			statement.setString(3,phoneNumber);
			statement.setString(4,email.toUpperCase());
			statement.setString(5,city.toUpperCase());
			statement.setString(6,postalCode.toUpperCase());
			statement.setString(7,province.toUpperCase());
			statement.setString(8,country.toUpperCase());
			statement.setBinaryStream(9,companyLogo);
			statement.setString(10,companyID);
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public ArrayList<Company> retreiveCompanies() {
		ArrayList<Company> companies = new ArrayList<Company>();
		Company company = null;
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement("SELECT *  FROM COMPANIES ");

			
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
				
				companies.add(company);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return companies;
	}

	//CompanyEmployee Object
	
	public void insertEmployee(CompanyEmployee employee) {
		CallableStatement statement;
		try { 
			statement = connection.prepareCall("CALL  insert_company_employee (?,?,?,?)");
			
			statement.setString(1, employee.getCompany().getCompanyID());
			statement.setString(2, employee.getUsername());
			statement.setString(3,employee.getPassword());
			statement.setString(4, employee.getFullName());
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			if (e.getClass() == SQLIntegrityConstraintViolationException.class) {
				System.out.println("username already exists, please change it");
			}else {
			e.printStackTrace();
			}
		}
	}

	public void deleteEmployee(String username) {
		PreparedStatement statement;
		try { 
			statement = connection.prepareCall("DELETE FROM COMPANIES_EMPLOYEES WHERE username = ?");

			statement.setString(1, username);
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public CompanyEmployee retreiveEmployee(String username) {
		CompanyEmployee employee = null;
		Company company = null;
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement("SELECT * FROM COMPANIES_EMPLOYEES WHERE username = ?");
			
			statement.setString(1, username);
			
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

	public void updateEmployee(Company company, String username, String password, String passwordConfirm, String fullName) {
		PreparedStatement statement;
		try { 
			
			if(password.isEmpty()||passwordConfirm.isEmpty()) { //if any field are empty
				
				CompanyEmployee employee = retreiveEmployee(username);
			
				password = employee.getPassword();
			
				statement = connection.prepareCall("UPDATE COMPANIES_EMPLOYEES SET company_id = ?,username = ?,password = ?,employee_full_name = ? WHERE username = ?");
				
				statement.setString(5, username);
				statement.setString(1, company.getCompanyID());
				statement.setString(2, username);
				statement.setString(3, password);
				statement.setString(4, fullName);
				
				statement.executeUpdate();
				connection.commit();
				statement.close();
			
			}else if (password.equals(passwordConfirm)) {//if the fields matches 
			
				statement = connection.prepareCall("UPDATE COMPANIES_EMPLOYEES SET company_id = ?,username = ?,password = ?,employee_full_name = ? WHERE username = ?");
				
				statement.setString(5, username);
				statement.setString(1, company.getCompanyID());
				statement.setString(2, username);
				statement.setString(3, passwordConfirm);
				statement.setString(4, fullName);
				
				statement.executeUpdate();
				connection.commit();
				statement.close();
			
			}else {
			
				CompanyEmployee employee = retreiveEmployee(username);
				
				password = employee.getPassword();
			
				statement = connection.prepareCall("UPDATE COMPANIES_EMPLOYEES SET company_id = ?,username = ?,password = ?,employee_full_name = ? WHERE username = ?");
				
				statement.setString(5, username);
				statement.setString(1, company.getCompanyID());
				statement.setString(2, username);
				statement.setString(3, password);
				statement.setString(4, fullName);
				
				statement.executeUpdate();
				connection.commit();
				statement.close();
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<CompanyEmployee> retreiveEmployees() {
		ArrayList<CompanyEmployee> employees = new ArrayList<CompanyEmployee>();
		CompanyEmployee employee = null;
		Company company = null;
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement("SELECT * FROM COMPANIES_EMPLOYEES");
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				employee = new CompanyEmployee();
				company = retreiveCompany(result.getString("company_id"));
				
				employee.setCompany(company);
				employee.setUsername(result.getString("username"));
				employee.setPassword(result.getString("password"));
				employee.setFullName(result.getString("employee_full_name"));
				
				employees.add(employee);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

	//CompanyPosition Object
	
	public void insertJobPosition(CompanyPosition position) {
		CallableStatement statement;
		try { 
			statement = connection.prepareCall("CALL insert_position (?,?,?,?,?,?,?,?,?)");
			
			String positionID = null;
			int numberOfPost = (getPositionsCount()+1);
			if(numberOfPost<=9 && numberOfPost>=0) {
				positionID = "POS" + "000" + numberOfPost;
			}else if(numberOfPost<=99 && numberOfPost>=10) {
				positionID = "POS" + "00" + numberOfPost;
			}else if(numberOfPost<=999 && numberOfPost>=100) {
				positionID = "POS" + "0" + numberOfPost;
			}else if(numberOfPost<=9999 && numberOfPost>=1000) {
				positionID = "POS" + numberOfPost;
			}
			
			statement.setString(1, positionID);
			statement.setString(2, position.getJobTitle());
			statement.setString(3, position.getJobDescription());
			statement.setString(4, position.getJobDomain());
			statement.setString(5, position.getTypeOfJob());
			statement.setString(6, position.getAvailability());
			statement.setString(7, position.getOfferSalary());
			String additionalCompensation = position.getAdditionalCompensation();
			if(additionalCompensation == null || additionalCompensation == "") {
				statement.setString(8, "N/A");
			}else {
				statement.setString(8, additionalCompensation);
			}
			statement.setString(9, position.getCompany().getCompanyID());
			
			
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		}  catch (SQLException e) {
			
			if (e.getClass() == SQLIntegrityConstraintViolationException.class) {// in order to avoid duplicated application numbers 
				
				try { 
					try {
						statement = connection.prepareCall("CALL insert_position (?,?,?,?,?,?,?,?,?)");
						
						String positionID = null;
						int numberOfPost = (getPositionsCount()+2);
						if(numberOfPost<=9 && numberOfPost>=0) {
							positionID = "POS" + "000" + numberOfPost;
						}else if(numberOfPost<=99 && numberOfPost>=10) {
							positionID = "POS" + "00" + numberOfPost;
						}else if(numberOfPost<=999 && numberOfPost>=100) {
							positionID = "POS" + "0" + numberOfPost;
						}else if(numberOfPost<=9999 && numberOfPost>=1000) {
							positionID = "POS" + numberOfPost;
						}
						
						statement.setString(1, positionID);
						statement.setString(2, position.getJobTitle());
						statement.setString(3, position.getJobDescription());
						statement.setString(4, position.getJobDomain());
						statement.setString(5, position.getTypeOfJob());
						statement.setString(6, position.getAvailability());
						statement.setString(7, position.getOfferSalary());
						String additionalCompensation = position.getAdditionalCompensation();
						if(additionalCompensation == null || additionalCompensation == "") {
							statement.setString(8, "N/A");
						}else {
							statement.setString(8, additionalCompensation);
						}
						statement.setString(9, position.getCompany().getCompanyID());
						
						statement.executeUpdate();
						connection.commit();
						statement.close();
						
					} catch (SQLException e1) {		
							e1.printStackTrace();
					}

				}finally {	

				}
			
				}else {
					e.printStackTrace();
					}
			
			}
	}
	
	public int getPositionsCount() {
		int counter = 0;
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("SELECT COUNT(position_id) AS counter FROM COMPANIES_POSITIONS");
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				counter = result.getInt("counter");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return counter;
	}
	
	public void deleteJobPosition(String positionID) {
		PreparedStatement statement;
		try { 
			statement = connection.prepareCall("DELETE FROM COMPANIES_POSITIONS WHERE position_id = ?");

			statement.setString(1, positionID);
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
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

	public void updateJobPosition(String positionID, String jobTitle, String jobDescription,
			String jobDomain, String typeOfJob, String availability, String offerSalary,
			String additionalCompensation,Company company) {
		PreparedStatement statement;
		try { 
			statement = connection.prepareCall("UPDATE COMPANIES_POSITIONS SET job_title = ?, job_description = ?, job_domain = ?, type_of_job = ?, availability = ?,offer_salary = ?, additional_compensation = ?, company_id = ? WHERE position_id = ?");
			
			statement.setString(1, jobTitle);
			statement.setString(2, jobDescription);
			statement.setString(3, jobDomain);
			statement.setString(4, typeOfJob);
			statement.setString(5, availability);
			statement.setString(6, offerSalary);
			statement.setString(7, additionalCompensation);
			statement.setString(8, company.getCompanyID());
			statement.setString(9, positionID);
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

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
	
	public ArrayList<CompanyPosition> retreiveJobPositionsByCompanyID(String companyID) {
		ArrayList<CompanyPosition> positions = new ArrayList<CompanyPosition>();
		CompanyPosition position = null;
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement("SELECT * FROM COMPANIES_POSITIONS WHERE company_id = ? ORDER BY position_id");
			
			statement.setString(1, companyID);
			
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

	//CPositionQualification Object
	
	public void insertJobQualification(CPositionQualification qualification) {
		CallableStatement statement;
		try { 
			statement = connection.prepareCall("CALL insert_qualification (?,?,?,?)");

			statement.setString(1,qualification.getPosition().getPositionID());
			statement.setString(2,qualification.getQualificationName());
			statement.setString(3,qualification.getDesiredYears());
			statement.setString(4,qualification.getQualificationDescription());
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteJobQualification(String positionID, String qualificationName) {
		PreparedStatement statement;
		try { 
			statement = connection.prepareCall("DELETE FROM C_POSITIONS_QUALIFICATIONS WHERE position_id = ? AND qualification_name = ?");

			statement.setString(1, positionID);
			statement.setString(2, qualificationName);
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

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

	public void updateJobQualification(CompanyPosition position, String OldQualificationName, String qualificationName , String desiredYears,
			String qualificationDescription) {
		PreparedStatement statement;
		try { 
			statement = connection.prepareCall("UPDATE C_POSITIONS_QUALIFICATIONS SET qualification_name = ?, desired_years = ?, qualification_description =? WHERE position_id = ? AND qualification_name = ? ");

			statement.setString(4, position.getPositionID());
			statement.setString(5, OldQualificationName);
			
			statement.setString(1, qualificationName);
			statement.setString(2, desiredYears);
			statement.setString(3, qualificationDescription);
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<CPositionQualification> retreiveJobQualifications() {
		ArrayList<CPositionQualification> positions = new ArrayList<CPositionQualification>();
		CPositionQualification qualification = null;
		CompanyPosition position = null;
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement("SELECT * FROM C_POSITIONS_QUALIFICATIONS ORDER BY position_id");
			
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				qualification = new CPositionQualification();
				
				position = retreiveJobPosition(result.getString("position_id"));
				
				qualification.setPosition(position);
				qualification.setQualificationName(result.getString("qualification_name"));
				qualification.setDesiredYears(result.getString("desired_years"));
				qualification.setQualificationDescription(result.getString("qualification_description"));
				
				positions.add(qualification);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return positions;
	}

	//CPositionExperience Object
	
	public void insertJobExperience(CPositionExperience experience) {
		CallableStatement statement;
		try { 
			statement = connection.prepareCall("CALL insert_experience(?,?,?,?)");

			statement.setString(1, experience.getPosition().getPositionID());
			statement.setString(2, experience.getExperienceName());
			statement.setString(3, experience.getDesiredYears());
			statement.setString(4, experience.getExperienceDescription());
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteJobExperience(String positionID, String experienceName) {
		PreparedStatement statement;
		try { 
			statement = connection.prepareCall("DELETE FROM C_POSITIONS_EXPERIENCES WHERE position_id = ? AND experience_name = ?");

			statement.setString(1, positionID);
			statement.setString(2, experienceName);
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

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

	public void updateJobExperience(CompanyPosition position,String OldexperienceName, String experienceName, String desiredYears,
			String experienceDescription) {
		PreparedStatement statement;
		try { 
			statement = connection.prepareCall("UPDATE C_POSITIONS_EXPERIENCES SET experience_name = ?, experience_years = ?,experience_description = ? WHERE position_id = ? AND experience_name = ?");

			statement.setString(4, position.getPositionID());
			statement.setString(5, OldexperienceName);
			
			statement.setString(1, experienceName);
			statement.setString(2, desiredYears);
			statement.setString(3, experienceDescription);
			
			statement.executeUpdate();
			connection.commit();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<CPositionExperience> retreiveJobExperiences() {
		ArrayList<CPositionExperience> experiences = new ArrayList<CPositionExperience>();
		CPositionExperience experience = null;
		CompanyPosition position = null;
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement("SELECT * FROM C_POSITIONS_EXPERIENCES ORDER BY position_id");
			
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

	/*APPLLICANT MODULE*/
	
	//Application Object
	
	public ArrayList<Application> retreiveApplicationsByCompany(String companyID) {
			ArrayList<Application> applications = new ArrayList<Application>();
			Application application = null;
			Applicant applicant = null;
			CompanyPosition position = null;
			PreparedStatement statement;

			try {
				statement = connection.prepareStatement("SELECT application_number, TO_CHAR(application_date,'DY DD, MON YYYY') AS app_date,applicant_id,position_id FROM APPLICATIONS WHERE position_id IN (SELECT position_id FROM companies_positions WHERE company_id = ?)");
				statement.setString(1, companyID);
				
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
	
	public ArrayList<Application> retreiveApplicationsByPostID(String positionID) {
		ArrayList<Application> applications = new ArrayList<Application>();
		Application application = null;
		Applicant applicant = null;
		CompanyPosition position = null;
		
		PreparedStatement statement;

		try {
			statement = connection.prepareStatement("SELECT application_number, TO_CHAR(application_date,'DY DD, MON YYYY') AS app_date,applicant_id,position_id FROM APPLICATIONS WHERE position_id = ?");
			
			statement.setString(1, positionID);
			
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
	
	//Applicant Object
	
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return applicant;
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

}
