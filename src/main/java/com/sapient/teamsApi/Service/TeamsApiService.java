package com.sapient.teamsApi.Service;

public interface TeamsApiService {

	
	
	
	String citationWritten(String citationData);
	
	String LoginFunctionality(String loginDetails);
	
	boolean checkPersonLoginStatus(String loginDetails);
	
	boolean isValidEmail(String teamsData);
	
}
