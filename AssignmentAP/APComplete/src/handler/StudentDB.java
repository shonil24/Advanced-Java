package handler;

import java.sql.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class StudentDB {

	public static void main (String args []) {  
	       
	       String URL = "jdbc:sqlite:F:\\MS\\Sem 1\\PF\\APComplete\\StudentDB.db";
	       
	       try {
	            
	            Connection con = DriverManager.getConnection(URL);
	            Statement st = con.createStatement();
	            String Student = "CREATE TABLE Student "
	            		+ "(StudentID varchar(30) PRIMARY KEY NOT NULL, "
	            		+ "Fskill varchar(30)," 
	            		+ "value4 number, "
	            		+ "Sskill varchar(30), "
	            		+ "Value3 number, "
	            		+ "Tskill varchar(30)," 
	            		+ "Value2 number,"
	            		+ "Foskill varchar(30),"
	            		+ "Value1 number,"
	            		+ "Persona varchar(30),"
	            		+ "Conflict1 varchar(30),"
	            		+ "Conflict2 varchar(30))";
	            st.executeUpdate("DROP TABLE IF EXISTS Student");
	            st.executeUpdate(Student);
	            populateTable(st,"F:\\MS\\Sem 1\\PF\\APComplete\\studentinfo.txt");	// read and populate the array
	            ResultSet rs = st.executeQuery("select * from Student");	
	            while (rs.next()) {
	               System.out.println( rs.getString(1) + " " + rs.getString(2));
	            }
	            con.close();
	       } 
	       catch( Exception ex ) {
	         System.out.println("Error populating database");
	         ex.printStackTrace();
	         System.exit(1);
	      }

	}

	
	private static void populateTable(Statement st, String fName) throws IOException {
		
		Stream<String> prows = Files.lines(Paths.get(fName));

		prows.map(x -> x.replaceAll("[^a-zA-Z\\d]", " ").trim().split("\\s+"))
				.forEachOrdered(x -> {
					try {
						st.executeUpdate("INSERT INTO Student " + "VALUES('"+x[0]+"', '"+ x[1]+"', '"+Integer.parseInt(x[2])+"', "
								+ "'"+ x[3]+"', '"+Integer.parseInt(x[4])+"', '"+ x[5]+"', '"+Integer.parseInt(x[6])+"', '"+ x[7]+"', "
										+ "'"+Integer.parseInt(x[8])+"', '"+ x[9]+"', '"+ x[10]+"', '"+ x[11]+"')");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
		prows.close();
	} 
	   
}
