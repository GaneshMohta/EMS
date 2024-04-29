package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JfR {
	public static void main(String args[]) {
		String url="jdbc:mysql://localhost:3306/JaI";
		String name="root";
		String password="JaiMahesh#1";
		
		try(Connection connection=DriverManager.getConnection(url,name,password)){
		String query="SELECT * FROM userr";
		try (PreparedStatement prepared=connection.prepareStatement(query);
		 ResultSet res=prepared.executeQuery()){
			
			while(res.next()) {
				int id=res.getInt("rollno");
				String Name=res.getString("uname");
				
				 System.out.println("ID: " + id + ", Name: " + Name);
			}
		}
		
	}catch(SQLException e){
		e.printStackTrace();
	}
	}

}
