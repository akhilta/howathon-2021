package com.sapient.teamsApi.Data;

import java.util.Date;
import java.util.List;

import com.sapient.teamsApi.DataDocuments.CitationCollection;
import com.sapient.teamsApi.DataDocuments.FilterCitationCollection;

public interface TeamsApiData {

	boolean checkPersonLoginStatus(String loginDetails);

	boolean isValidEmail(String teamsData);

	boolean addCitationToDataBase(String from, String to, String citation, String timeStamp, String from_email,
			String to_email);

	boolean addPointsToDataBase(String from, String to, String points, String timeStamp, String from_email,
			String to_email);

	boolean saveFilterCitiationToDataBase(FilterCitationCollection object);
	
	List<FilterCitationCollection> findFilterCitiationFromDataBase();
	
	List<FilterCitationCollection> findFilterCitiationByEmail(String email);
	
	List<CitationCollection> findCitiationByEmail(String to_email);
}
