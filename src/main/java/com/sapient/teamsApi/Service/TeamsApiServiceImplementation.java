package com.sapient.teamsApi.Service;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.teamsApi.Data.TeamsApiDataImpl;

import com.sapient.teamsApi.DataDocuments.FilterCitationCollection;

import com.sapient.teamsApi.DataDocuments.CitationCollection;

import com.sapient.teamsApi.DataDocuments.MapperCollection;
import com.sapient.teamsApi.Helpers.JsonToStringToJson;

@Service
public class TeamsApiServiceImplementation implements TeamsApiService {

	@Autowired
	TeamsApiDataImpl teamsApiDataImpl;


	@Override
	public String citationWritten(String citationData) {

		String 	result=null;
		JSONObject jsonObject=JsonToStringToJson.convertStringtoJson(citationData);
		JSONObject valueObject=(JSONObject)jsonObject.get("value");
		JSONObject data=(JSONObject)valueObject.get("data");
		String email=(String)data.get("Email");
		JSONObject from=(JSONObject)jsonObject.get("from");
		String jsonId=(String)from.get("id");
		boolean isValidJsonId=teamsApiDataImpl.isValidJsonId(jsonId);
		boolean isValidEmail =teamsApiDataImpl.isValidEmail(email);
		if(isValidJsonId && isValidEmail) {
			MapperCollection fromObject=teamsApiDataImpl.getMapperObject(jsonId);
			MapperCollection toObject=teamsApiDataImpl.getMapperObjectWithEmail(email);
			if(toObject.getEmail().equals(fromObject.getEmail()))
			{
				return "Sorry! You cannot write citation to yourself";
			}
			String citation=(String)data.get("Citation");
			String type=(String)data.get("type");
			CitationCollection citationCollection=new CitationCollection();
			citationCollection.setCitation(citation);
			citationCollection.setFrom(fromObject.getName());
			citationCollection.setFromEmail(fromObject.getEmail());
			citationCollection.setPoints(0);
			citationCollection.setTimestamp(new Date());
			citationCollection.setTo(toObject.getName());
			citationCollection.setToEmail(toObject.getEmail());
			citationCollection.setType(type);

			teamsApiDataImpl.saveCitationData(citationCollection);

			String name=toObject.getName();
			String fromName=fromObject.getName();
			result="Thank you "+ fromName+" for adding more points to "+name;

	        createFilterCitiation(toObject.getName(), toObject.getEmail(), "c", 0, new Date(), type);

		}
		else if(!isValidJsonId) {
			result="please login first.This is just one time login ";
		}
		else {
			result="Rewardee didnt login once";

		}
		return result;
	}
	
	public static boolean IntegerStringValidation(String s, int radix) {
	    if(s.isEmpty()) 
	    	return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) 
	            	return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) 
	        	return false;
	    }
	    return true;
	}
	@Override
	public String pointsGiven(String pointsData) {
		String 	result=null;
		JSONObject jsonObject=JsonToStringToJson.convertStringtoJson(pointsData);
		JSONObject valueObject=(JSONObject)jsonObject.get("value");
		JSONObject data=(JSONObject)valueObject.get("data");
		String email=(String)data.get("email");
		JSONObject from=(JSONObject)jsonObject.get("from");
		String jsonId=(String)from.get("id");
		boolean isValidJsonId=teamsApiDataImpl.isValidJsonId(jsonId);
		boolean isValidEmail =teamsApiDataImpl.isValidEmail(email);
		
		if(isValidJsonId && isValidEmail) {
			MapperCollection fromObject=teamsApiDataImpl.getMapperObject(jsonId);
			MapperCollection toObject=teamsApiDataImpl.getMapperObjectWithEmail(email);
			if(toObject.getEmail().equals(fromObject.getEmail()))
			{
				return "Sorry! You cannot send points to yourself";
			}
			String pointsString=(String)data.get("points");
			boolean flag=IntegerStringValidation(pointsString,10);
			if(!flag)
				return "Please enter valid points. (points should be numeric only)";
			if(flag && pointsString.charAt(0)=='-')
				return "Please enter valid points. (points should be a positive number)";
			int points=Integer.parseInt(pointsString);
			String type=(String)data.get("type");
			CitationCollection citationCollection=new CitationCollection();
			citationCollection.setCitation(null);
			citationCollection.setFrom(fromObject.getName());
			citationCollection.setFromEmail(fromObject.getEmail());
			citationCollection.setPoints(points);
			citationCollection.setTimestamp(new Date());
			citationCollection.setTo(toObject.getName());
			citationCollection.setToEmail(toObject.getEmail());
			citationCollection.setType(type);
			Date lastrefreshed=fromObject.getTimestamp();
			Date presentDate=new Date();
			int lastRefreshedYear=lastrefreshed.getYear();
			int presentYear=presentDate.getYear();
			int lastRefreshedMonth=lastrefreshed.getMonth();
			int presentMonth=presentDate.getMonth();
			System.out.println(presentYear + lastRefreshedYear + presentMonth + lastRefreshedMonth);
			if(points>100) {
				result="You cannot give more than 100 points";
			}
			else {
				if(presentYear>lastRefreshedYear ||  presentMonth>lastRefreshedMonth) {
					System.out.println("------------>");
					fromObject.setPoints(100-points);
					fromObject.setTimestamp(new Date());
					teamsApiDataImpl.saveMapperData(fromObject);
					teamsApiDataImpl.saveCitationData(citationCollection);

					String name=toObject.getName();
					String fromName=fromObject.getName();
					result="Thank you "+ fromName+" for adding more points to "+name;

					createFilterCitiation(toObject.getName(), toObject.getEmail(), "p", points, new Date(), type);

				}
				else {
					if(points>fromObject.getPoints()) {
						result="you dont have enough points for this month your left with :"+fromObject.getPoints();
					}
					else {
						fromObject.setPoints(fromObject.getPoints()-points);
						fromObject.setTimestamp(new Date());
						teamsApiDataImpl.saveMapperData(fromObject);
						teamsApiDataImpl.saveCitationData(citationCollection);

						String name=toObject.getName();
						String fromName=fromObject.getName();
						result="Thank you "+ fromName+" for adding more points to "+name;

						createFilterCitiation(toObject.getName(), toObject.getEmail(), "p", points, new Date(), type);
						

					}
				}

			}


		}
		else if(!isValidJsonId) {
			result="please login first.This is just one time login ";
		}
		else {
			result="Rewardee didnt login once";
		}
		return result;
	}


	@Override
	public String LoginFunctionality(String loginDetails) {
		String result = "";
		JSONObject jsonObject = JsonToStringToJson.convertStringtoJson(loginDetails);
		JSONObject valueObject = (JSONObject) jsonObject.get("value");
		JSONObject data = (JSONObject) valueObject.get("data");
		String email = (String) data.get("Email");
		JSONObject from = (JSONObject) jsonObject.get("from");
		String jsonId = (String) from.get("id");
		String name = (String) from.get("name");
		String timestamp = (String) jsonObject.get("timestamp");
		boolean isValidJsonId = teamsApiDataImpl.isValidJsonId(jsonId);
		if (isValidJsonId) {
			MapperCollection object = teamsApiDataImpl.getMapperObject(jsonId);
			System.out.println(object.getName());
			System.out.println(object.getTimestamp());
			object.setEmail(email);
			boolean saveEntry=teamsApiDataImpl.saveMapperData(object);
			if(saveEntry)
			{
			result="Log in credentials updated succesfully!"+"\r\n"+"Want to say Thank you! to a team member? Go to the createCitation in Teams chat and leave a Recognition Note.\r\n"
					+ "Know someone who goes above and beyond and really lives the core values? Go to the recognition tool in Teams chat and submit a recognition or send points. \r\n"
					+ "";
			}
			else {
				result="Something went wrong! Your credentials updation is unsuccessful, please try later";
			}
		} else {
			System.out.println("came here");
			MapperCollection mapperObject = new MapperCollection();
			mapperObject.setEmail(email);
			mapperObject.setJsonId(jsonId);
			mapperObject.setName(name);
			mapperObject.setPoints(100);


			//			String[] splitTimestamp=timestamp.split("T");
			//			String[] splitTime=splitTimestamp[0].split("-");
			//			System.out.println(timestamp+" "+splitTime[0]+" "+splitTime[1]+" "+splitTime[2]);
			//			int year=Integer.parseInt(splitTime[0]);
			//			int month=Integer.parseInt(splitTime[1]);
			//			int day=Integer.parseInt(splitTime[2]);
			//			//Date date=new Date(year,month,day);



			Date date=new Date();

			mapperObject.setTimestamp(date);
			System.out.println("came here aswell");
			teamsApiDataImpl.saveMapperData(mapperObject);
			result="Logged in succesfully!"+"\r\n"+ "Want to say Thank you! to a team member? Go to the createCitation in Teams chat and leave a Recognition Note.\r\n"
					+ "Know someone who goes above and beyond and really lives the core values? Go to the recognition tool in Teams chat and submit a recognition or send points. \r\n"
					+ "";
		}
		return result;
	}

	@Override
	public boolean checkPersonLoginStatus(String loginDetails) {

		return false;
	}

	@Override
	public boolean isValidEmail(String teamsData) {

		return false;
	}


	@Override
	public boolean createFilterCitiation(String name, String email, String citationType, int points, Date timestamp,
			String type) {

		FilterCitationCollection fiterCitationCollection = new FilterCitationCollection(name, email, citationType,
				points, timestamp, type);

		Date date = new Date();
		fiterCitationCollection.setTimestamp(date);

		teamsApiDataImpl.saveFilterCitiationToDataBase(fiterCitationCollection);

		return false;
	}

	@Override
	public List<FilterCitationCollection> findFilterCitiation() {

		List<FilterCitationCollection> object = teamsApiDataImpl.findFilterCitiationFromDataBase();

		return object;
	}

	@Override
	public List<FilterCitationCollection> findFilterCitiationByEmail(String email) {
		List<FilterCitationCollection> object = teamsApiDataImpl.findFilterCitiationByEmail(email);

		return object;
	}

	@Override
	public List<CitationCollection> findCitiationByEmail(String to_email) {
		List<CitationCollection> object = teamsApiDataImpl.findCitiationByEmail(to_email);

		return object;
	}

	
}
