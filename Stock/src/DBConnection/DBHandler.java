package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler extends Configs{
	
	Connection dbconnection;
	
	public Connection getConnection() {
		
		String connectionString = "jdbc:mysql://" + Configs.dbhost +":" + Configs.dbport + "/" + Configs.dbname + "?autoReconnect=true&useSSL=false";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			dbconnection = DriverManager.getConnection(connectionString,Configs.dbuser,Configs.dbpass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dbconnection;
		
		
	}

}
