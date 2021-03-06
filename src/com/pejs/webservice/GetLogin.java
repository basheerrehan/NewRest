package com.pejs.webservice;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/GetLogin")
public class GetLogin {
	@GET
	@Path("{id}")
	@Produces(MediaType.TEXT_HTML)
	public String getMessagePathParam(@PathParam("id") int userid) throws AddressException, UnsupportedEncodingException, MessagingException{
		
		System.out.println("parameters are"+ userid);
		Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs = null;
 int id = 0;
 String email = null;
 int uid = 0;
 int pincode=0;
	    try {
	        // STEP 2: Register JDBC driver
	    	
	        Class.forName("com.mysql.jdbc.Driver");
	        // STEP 3: Open a connection
	        System.out.print("\nConnecting to database...");
	        conn = DriverManager.getConnection(Setup.DB_URL, Setup.USER, Setup.PASS);
	        System.out.println(" SUCCESS!\n");
	        Statement st = conn.createStatement(); 
	        String query = "SELECT * FROM pusers where id = '"+userid+"'";
        
	        // create the java statement
	    
	        
	        // execute the query, and get a java resultset
	         rs = st.executeQuery(query);
	        
	        // iterate through the java resultset
	        while (rs.next())
	        {
	          uid = rs.getInt("id");
	          String firstName = rs.getString("name");
	           email = rs.getString("email");
	          pincode = rs.getInt("pincode");
	          
	          // print the results
	          System.out.format("%d,%s, %s, %d\n", uid, firstName, email,pincode);
	        }
	        st.close();
	    } catch(SQLException se) {
	        se.printStackTrace();
	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if(stmt != null)
	                conn.close();
	        } catch(SQLException se) {
	        }
	        try {
	            if(conn != null)
	                conn.close();
	        } catch(SQLException se) {
	            se.printStackTrace();
	        }
	    }
	    System.out.println("Thank you for your patronage!");
	  String message = email + pincode;
	  message= message.concat(String.valueOf(id));
	    // Store the message
	 return message;
	}
}
