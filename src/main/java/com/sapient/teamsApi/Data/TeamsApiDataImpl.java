package com.sapient.teamsApi.Data;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sapient.teamsApi.DataDocuments.FilterCitationCollection;
import com.sapient.teamsApi.DataDocuments.CitationCollection;
import com.sapient.teamsApi.DataDocuments.MapperCollection;

@Service
public class TeamsApiDataImpl implements TeamsApiData {

	@Autowired
	MapperTableRepo mapperTableRepo;


	@Autowired
	FilterCitationTableRepo filterCitationTableRepo;


	@Autowired
	CitationTableRepo citationTableRepo;

	@Override
	public boolean checkPersonLoginStatus(String loginDetails) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValidEmail(String email) {
		List<MapperCollection> personMapperObject = mapperTableRepo.findByEmail(email);

		if (personMapperObject.size() > 0) {
			return true;
		}
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

	public MapperCollection getMapperObject(String jsonId) {
		List<MapperCollection> personMapperObject = mapperTableRepo.findByJsonId(jsonId);

		return personMapperObject.get(0);
	}

	public MapperCollection getMapperObjectWithEmail(String email) {
		List<MapperCollection> personMapperObject = mapperTableRepo.findByEmail(email);

		return personMapperObject.get(0);
	}

	public boolean isValidJsonId(String jsonId) {
		List<MapperCollection> personMapperObject = mapperTableRepo.findByJsonId(jsonId);
		if (personMapperObject.size() > 0) {
			return true;
		}
		return false;
	}

	public boolean saveMapperData(MapperCollection object) {

		object = mapperTableRepo.save(object);
		if (object != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean saveFilterCitiationToDataBase(FilterCitationCollection object) {
		
		filterCitationTableRepo.save(object);
		if (object != null) {
			return true;
		} else {
			return false;
		}

	}
	
	@Override
	public List<FilterCitationCollection> findFilterCitiationFromDataBase() {
		
		List<FilterCitationCollection> object =filterCitationTableRepo.findAll();
		System.out.println(object.size());
		if (object != null) {
			return object;
		} else {
			return null;
		}
		

	}

	public boolean saveCitationData(CitationCollection citationCollection) {
		citationCollection=citationTableRepo.save(citationCollection);
		if(citationCollection!=null) {
			return true;
		}
		else {
			return false;
		}
		
	}

}
