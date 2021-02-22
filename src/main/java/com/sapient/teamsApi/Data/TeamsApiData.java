package com.sapient.teamsApi.Data;

public interface TeamsApiData {

	boolean checkPersonLoginStatus(String loginDetails);

	boolean isValidEmail(String teamsData);
	
	boolean addCitationToDataBase(String from,String to,String citation,String timeStamp,String from_email,String to_email);
	
	boolean addPointsToDataBase(String from,String to,String points,String timeStamp,String from_email,String to_email );
	
}
