package com.sapient.teamsApi.Service;

import org.springframework.stereotype.Controller;


public interface TeamsApiService {

	
	
	
	String citationWritten(String citationData);
	
	String LoginFunctionality(String loginDetails);
	
	boolean checkPersonLoginStatus(String loginDetails);
	
	boolean isValidEmail(String teamsData);

	String pointsGiven(String pointsData);
	
}
