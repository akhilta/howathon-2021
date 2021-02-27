package com.sapient.teamsApi.Service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.sapient.teamsApi.DataDocuments.CitationCollection;
import com.sapient.teamsApi.DataDocuments.FilterCitationCollection;

public interface TeamsApiService {

	String citationWritten(String citationData);

	String LoginFunctionality(String loginDetails);

	boolean checkPersonLoginStatus(String loginDetails);

	boolean isValidEmail(String teamsData);

	boolean createFilterCitiation(String name, String email, String citationType, int points, Date timestamp,
			String type);

	String pointsGiven(String pointsData);

	List<FilterCitationCollection> findFilterCitiation();
	
	List<FilterCitationCollection> findFilterCitiationByEmail(String email);
	
	List<CitationCollection> findCitiationByEmail(String to_email);

}
