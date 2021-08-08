/**
 * Author: SNHU
 * Edit By: Francis Rinehart
 * Email: Francis.rinehart@SNHU.EDU
 * Language: Java
 * Purpose: This is a Hash Compare
 * 
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

// class variable creation
public class HashCompare {  
    private FileInputStream credFS;
    private Scanner credScnr;
    private String credUserName;
    private String credPassHash;
    private String credRole;
    private String userPassHash;
    private String fullLine;
    //private String[] credParts;
    ArrayList<String> credentials = new ArrayList<String>();

    
    // class constructor
    public HashCompare() throws FileNotFoundException {
        credUserName = "none";
        credPassHash = "none";
        credRole = "none";
        userPassHash = "none";
        
    }
    
    // method to create a string array from credentials file: no return
    public void setCreds() throws IOException {
        credFS = new FileInputStream("Zoo Authentication/credentials.txt"); // reads the credentials from Zoo Authentication
        credScnr = new Scanner(credFS); // new Scanner for file information
        
        while ( credScnr.hasNext() ) 
        {
        	fullLine = credScnr.nextLine();
        	Scanner inFullLine = new Scanner(fullLine); // reads the full line of a scanner
        	String user = inFullLine.next();
            String hash = inFullLine.next();
            String role = "";
        	
        	while (inFullLine.hasNext())
            {
                role = inFullLine.next();
            }
        	
        	credentials.add(user);
        	credentials.add(hash);
        	credentials.add(role);
        
        }
    } 
    
    // Search function to obtain credential parts.
    public void getCred(String username) {
    	int credIndex = 0;
    	int hashIndex = credIndex + 1;
    	int roleIndex = credIndex + 2;
    	
    	for (int i=0; i >= credentials.size(); i= i+3) {  	
	    	if ( username == credentials.get(credIndex) ) {
	    		credUserName = credentials.get(credIndex);
				credPassHash = credentials.get(hashIndex);
				credRole = credentials.get(roleIndex);
	    		credIndex = credIndex + 3;
	    	}
	    	else {
	    		credIndex = credIndex + 3;
	    	}
	    	
			
	    }
    	
    }
    
    // method creates a true/false return if the user password hash and the credentials password hash match
    public boolean comparePassword(String userPassHash, String credPassHash){
        return userPassHash.equals(credPassHash); // returns true if match found and false if not
    }
    
    // method to create a MD5 hash from the users input password
    public String hashPassword(String userPassWord){
        MD5Digest md5digest = new MD5Digest(); // reads new instance of MD5Digest class

        try 
        {              
            userPassHash = md5digest.Hash(userPassWord);
        }
        catch (NoSuchAlgorithmException ex)
        {
            System.err.println("Caught No Such Algorithm Exception: ");
        }
        return userPassHash;// return hashed password
    }
    
    // get method from credParts array first element
    public String getCredUserName() {
        return credUserName;
    }
    
    // get method from credParts array second element
    public String getCredPassHash() {
        return credPassHash;
    }
    
    // get method from credParts array third element
    public String getCredRole() {
        return credRole;
    }
}
