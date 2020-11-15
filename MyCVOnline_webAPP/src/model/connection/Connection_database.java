package model.connection;
import java.sql.*;

public class Connection_database {

	private static Connection connection = null;
	
	public static final String URL = "jdbc:oracle:thin:@//localhost:1521/xe";
	public static final String USER = "MyCVOnline";
	public static final String PASSWORD = "curriculum123";
	
	public static Connection get_Connection() throws ClassNotFoundException{
		try {
			
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection(URL,USER,PASSWORD);
			return connection;
			
		}catch (SQLException e){
			
			throw new RuntimeException("Error connection to the database", e);
			
		}
	}
	
	public void closeConnection () throws SQLException{
		if(connection != null) {
			connection.close();
		}
		
	}
}
