import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DBconnection extends GUI{
/**
	 * 
	 */
	private static final long serialVersionUID = 3100991204347427788L;

public void insert(String firstn, String Lastn, String address, String address2, String City, String State, String Zip, String Phone,String Email){
	try {
	Class.forName("org.sqlite.JDBC");
	Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\DB Browser for SQLite\\AddressBook.db");

    Statement statement = connection.createStatement();
   
	 statement.executeUpdate("INSERT INTO names (firstName, lastName) VALUES ('"+firstn+"','"+Lastn+"')"); //inserts a record into names table
	 
	 
	 
	
     //getting personID number from the names the user typed
     ResultSet rs = statement.executeQuery("SELECT personID FROM names WHERE firstName = '"+firstn+"' and lastName = '"+Lastn+"'");
     
     if(rs.getInt(1) > 0) {
     	 JOptionPane.showMessageDialog(null, "Insertion Completed");
      }else {
     	 JOptionPane.showMessageDialog(null, "Insertion Not Completed");
      }
     int pID = 0;
     while(rs.next()) {
     	//setting the personID collected to pID
     	pID = rs.getInt(1);
     }
     //using pID to insert the text fields into their respected tables and fields
     statement.executeUpdate("INSERT INTO addresses (personID,address1,address2,city,state,zipcode) VALUES ('"+pID+"','"+address+"','"+address2+"','"+City+"','"+State+"','"+Zip+"')"); //inserts a record into addresses table
     statement.executeUpdate("INSERT INTO emailAddresses (personID,emailAddress) VALUES ('"+pID+"','"+Email+"')"); //inserts a record into emailAddress table
     statement.executeUpdate("INSERT INTO phoneNumbers (personID,phoneNumber) VALUES ('"+pID+"','"+Phone+"')"); //inserts a record into phoneNumbers table

     
     
      statement.close();
      connection.close();

	}
	catch ( SQLException sqlException ) {
  JOptionPane.showMessageDialog( null,
     sqlException.getMessage(), "Database Error",
     JOptionPane.ERROR_MESSAGE );
  System.exit( 1 );
}

catch(ClassNotFoundException cnfex) {

   System.out.println("Problem in loading or "
           + "registering MS Access JDBC driver");
   cnfex.printStackTrace();
}
}
public void newfields(JTextField field1,JTextField field2,JTextField field3,JTextField field4,JTextField field5,JTextField field6,JTextField field7,JTextField field8,JTextField field9) {
	field1.setText("");
	field2.setText("");
	field3.setText("");
	field4.setText("");
	field5.setText("");
	field6.setText("");
	field7.setText("");
	field8.setText("");
	field9.setText("");
}

public void search(String fname, String lname,JTextField field1,JTextField field2,JTextField field3,JTextField field4,JTextField field5,JTextField field6,JTextField field7) {
	try {
		
	Class.forName("org.sqlite.JDBC");
	Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\DB Browser for SQLite\\AddressBook.db");

    Statement statement1 = connection.createStatement();
	//collecting the personID from the first and last name the user typed
    ResultSet rs = statement1.executeQuery("SELECT personID FROM names WHERE firstName = '"+fname+"' and lastName = '"+lname+"'");
    if(rs.getInt(1) > 0) {
     	 JOptionPane.showMessageDialog(null, "Search Completed");
      }else {
     	 JOptionPane.showMessageDialog(null, "Record not Found");
      }
    
    
    int pID = 0;
    while(rs.next()) {
    	//getting the personID number from the first and last name the user typed
    	pID = rs.getInt(1);
    	while(pID == rs.getInt(1))
    	
    	{
      //gets the data from addresses table column address1 and sets to string and then placing the data in the text field
      ResultSet address1 = statement1.executeQuery("SELECT address1 FROM addresses natural join names WHERE personID = '"+pID+"'");
      	String addr = address1.getString("address1");
      	System.out.println(addr);
      	field1.setText(addr);
      //gets the data from addresses table column address2 and sets to string and then placing the data in the text field
      ResultSet address2output = statement1.executeQuery("SELECT address2 FROM addresses natural join names WHERE personID = '"+pID+"'");
       String address2out = address2output.getString("address2");	
       System.out.println(address2out);
       field2.setText(address2out);
     //gets the data from addresses table column city and sets to string and then placing the data in the text field
      ResultSet cty = statement1.executeQuery("SELECT city FROM addresses natural join names WHERE personID = '"+pID+"'");
       String ctyout = cty.getString("city");	
       System.out.println(ctyout);
       field3.setText(ctyout);
     //gets the data from addresses table column state and sets to string and then placing the data in the text field
      ResultSet st = statement1.executeQuery("SELECT state FROM addresses natural join names WHERE personID = '"+pID+"'");
       String stout = st.getString("state");	
       System.out.println(stout);
       field4.setText(stout);
     //gets the data from addresses table column zipcode and sets to string and then placing the data in the text field
      ResultSet zipc = statement1.executeQuery("SELECT zipcode FROM addresses natural join names WHERE personID = '"+pID+"'");
       String zipcout = zipc.getString("zipcode");	
       System.out.println(zipcout);
       field5.setText(zipcout);
     //gets the data from addresses table column phoneNumber and sets to string and then placing the data in the text field
      ResultSet phonen = statement1.executeQuery("SELECT phoneNumber FROM phoneNumbers natural join names WHERE personID = '"+pID+"'");
       String phonenout = phonen.getString("phoneNumber");	
       System.out.println(phonenout);
       field6.setText(phonenout);
     //gets the data from addresses table column emailAddress and sets to string and then placing the data in the text field
      ResultSet emailaddr = statement1.executeQuery("SELECT emailAddress FROM emailAddresses natural join names WHERE personID = '"+pID+"'");
       String emailout = emailaddr.getString("emailAddress");	
       System.out.println(emailout);
       field7.setText(emailout);
        break; 
    	}
    	break;
    }
    

     statement1.close();
     connection.close();
	}
	catch ( SQLException sqlException ) {
         JOptionPane.showMessageDialog( null,
            sqlException.getMessage(), "Database Error",
            JOptionPane.ERROR_MESSAGE );
         System.exit( 1 );
      }

     catch(ClassNotFoundException cnfex) {

          System.out.println("Problem in loading or "
                  + "registering MS Access JDBC driver");
          cnfex.printStackTrace();
      }
}
public void deleteF1F2(String f1, String l1) {
    // Search for the primary key using the provided first and last names
    int primaryKey = search(f1, l1);
    if(primaryKey > 0) {
   	 JOptionPane.showMessageDialog(null, "Delete Completed");
    }else {
   	 JOptionPane.showMessageDialog(null, "Delete Not Completed");
    }
    try {
        // Load the JDBC driver class
        Class.forName("org.sqlite.JDBC");

        // Establish a connection to the database
        Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\DB Browser for SQLite\\AddressBook.db");

        // Create a Statement object to send to the database
        Statement statement = connection.createStatement();
        statement.executeUpdate("PRAGMA foreign_keys=ON");
        // Execute the delete statement on the names table
        // The cascade delete will take care of associated addresses, emailAddresses, and phoneNumbers
        statement.executeUpdate("DELETE FROM names WHERE personID = " + primaryKey);
        
        // Close the statement and connection
        statement.close();
        connection.close();
    } catch (SQLException sqlException) {
        JOptionPane.showMessageDialog(null, sqlException.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    } catch (ClassNotFoundException e) {
        JOptionPane.showMessageDialog(null, "JDBC Driver not found", "Driver Error", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }
}
public int search(String fname, String lname) {
    int personID = 0;

    try {
        // Load the JDBC driver class
        Class.forName("org.sqlite.JDBC");

        // Establish a connection to the database
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\DB Browser for SQLite\\AddressBook.db");
             PreparedStatement statement = connection.prepareStatement("SELECT personID FROM names WHERE firstName = ? AND lastName = ?")) {
        	
        	
                	
            // Set parameters for the prepared statement
            statement.setString(1, fname);
            statement.setString(2, lname);

            // Execute the query
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    personID = rs.getInt(1);
                    
                }
                
            }
        }
    } catch (SQLException sqlException) {
        JOptionPane.showMessageDialog(null, sqlException.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    } catch (ClassNotFoundException e) {
        JOptionPane.showMessageDialog(null, "JDBC Driver not found", "Driver Error", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }

    return personID;
}
public void updatePerson(String firstName, String lastName, String newAddress, String newAddress2, String newEmail, String newPhoneNumber, String City, String State, String zipcode) {
    int personID = search(firstName, lastName);
    if(personID > 0) {
   	 JOptionPane.showMessageDialog(null, "Update Completed");
    }else {
   	 JOptionPane.showMessageDialog(null, "Update Not Completed");
    }
    System.out.println("Found pID: "+personID);

    if (personID == 0) {
        // Person not found
        JOptionPane.showMessageDialog(null, "Person not found", "Update Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        Class.forName("org.sqlite.JDBC");
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\DB Browser for SQLite\\AddressBook.db")) {

            // Update the address
            try (PreparedStatement stmtAddress = connection.prepareStatement("UPDATE addresses SET address1 = ? WHERE personID = ?")) {
                stmtAddress.setString(1, newAddress);
                stmtAddress.setInt(2, personID);
                stmtAddress.executeUpdate();
                System.out.println("Address updated: "+ newAddress);
            }
          // Update the address2
            try (PreparedStatement stmtAddress2 = connection.prepareStatement("UPDATE addresses SET address2 = ? WHERE personID = ?")) {
                stmtAddress2.setString(1, newAddress2);
                stmtAddress2.setInt(2, personID);
                stmtAddress2.executeUpdate();
                System.out.println("Address updated: "+ newAddress2);
            }

            // Update the email
            try (PreparedStatement stmtEmail = connection.prepareStatement("UPDATE emailAddresses SET emailAddress = ? WHERE personID = ?")) {
                stmtEmail.setString(1, newEmail);
                stmtEmail.setInt(2, personID);
                stmtEmail.executeUpdate();
                System.out.println("email address updated: "+ newEmail);
            }

            // Update the phone number
            try (PreparedStatement stmtPhone = connection.prepareStatement("UPDATE phoneNumbers SET phoneNumber = ? WHERE personID = ?")) {
                stmtPhone.setString(1, newPhoneNumber);
                stmtPhone.setInt(2, personID);
                stmtPhone.executeUpdate();
                System.out.println("Phone Number updated: "+ newPhoneNumber);
            }
            // Update the city
            try (PreparedStatement stmtCity = connection.prepareStatement("UPDATE addresses SET city = ? WHERE personID = ?")) {
                stmtCity.setString(1, City);
                stmtCity.setInt(2, personID);
                stmtCity.executeUpdate();
                System.out.println("City updated: "+ City);
            }
            // Update the state
            try (PreparedStatement stmtAddress = connection.prepareStatement("UPDATE addresses SET state = ? WHERE personID = ?")) {
                stmtAddress.setString(1, State);
                stmtAddress.setInt(2, personID);
                stmtAddress.executeUpdate();
                System.out.println("State updated: "+ State);
            }
            // Update the zipcode
            try (PreparedStatement stmtAddress = connection.prepareStatement("UPDATE addresses SET zipcode = ? WHERE personID = ?")) {
                stmtAddress.setString(1, zipcode);
                stmtAddress.setInt(2, personID);
                stmtAddress.executeUpdate();
                System.out.println("Zip updated: "+ zipcode);
            }
        }
    } catch (SQLException sqlException) {
        JOptionPane.showMessageDialog(null, sqlException.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    } catch (ClassNotFoundException e) {
        JOptionPane.showMessageDialog(null, "JDBC Driver not found", "Driver Error", JOptionPane.ERROR_MESSAGE);
    }
}


}
