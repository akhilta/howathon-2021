package com.sapient.teamsApi.Data;

import org.springframework.stereotype.Service;

@Service
public class TeamsApiDataImpl implements TeamsApiData {

	@Override
	public boolean checkPersonLoginStatus(String loginDetails) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValidEmail(String teamsData) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addCitationToDataBase(String from, String to, String citation, String timeStamp, String from_email,
			String to_email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addPointsToDataBase(String from, String to, String points, String timeStamp, String from_email,
			String to_email) {
		// TODO Auto-generated method stub
		return false;
	}

}
