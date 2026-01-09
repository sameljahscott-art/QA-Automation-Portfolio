package Experiment;

import java.util.Date;

public class emailGenerator {
	//String dateString = date.toString();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date date = new Date();
		//String dateString = date.toString();
		//String noSpaceDate=dateString.replaceAll("\\s","");
		//System.out.println("Generated Email Address: " + finalEmailAddress);
		
		        String dateString = date.toString();
		        
		        // Remove spaces, colons, and other characters that may not be valid in email usernames
		        String noSpaceDate = dateString.replaceAll("[\\s,:]", "");
		        
		        // Construct the email address
		        String finalEmailAddress = "user" + noSpaceDate + "@example.com";
		        
		        // Output the generated email address
		        System.out.println("Generated Email Address: " + finalEmailAddress);
		    }

	}


