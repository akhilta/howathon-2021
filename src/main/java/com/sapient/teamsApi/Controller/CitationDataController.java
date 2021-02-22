package com.sapient.teamsApi.Controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.teamsApi.Data.MapperTableRepo;
import com.sapient.teamsApi.DataDocuments.MapperCollection;
import com.sapient.teamsApi.Helpers.JsonToStringToJson;
import com.sapient.teamsApi.Service.TeamsApiServiceImplementation;


@RestController
public class CitationDataController {

	@Autowired
	private MapperTableRepo mapperTableRepo;
	
	@Autowired
	private TeamsApiServiceImplementation teamsApiServiceImplementation;
	
	@PostMapping("/teamsData")
	public String teamsData(@RequestBody String data) {
		String action=null;
		String result="";
		JSONObject jsonObject=JsonToStringToJson.convertStringtoJson(data);
		try {
			JSONObject valueObject=(JSONObject)jsonObject.get("value");
			action=(String)valueObject.get("commandId");
		}
		catch(Exception e) {
			
		}
		switch(action) {
		case "createCitation":
			result=teamsApiServiceImplementation.citationWritten(data);
			break;
		case "LoginToRandR":
			result=teamsApiServiceImplementation.LoginFunctionality(data);
			break;
		default:
			result="somethingWentWrongPleaseTryAgain";
			break;
			
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/getmappings")
	public ResponseEntity<?> getAllMappers(){
		//List<MapperCollection> mt=mapperTableRepo.findAll();
		List<MapperCollection> mt=mapperTableRepo.findByJsonId("3580abc");
		if(mt.isEmpty()==true) {
			return new ResponseEntity<String>("no mappings",HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<List<MapperCollection>>(mt,HttpStatus.ACCEPTED);
			
		}
		
	}
	
	@PostMapping("sendmapping")
	public ResponseEntity<?> sendMapping(@RequestBody MapperCollection mp){
		try {
			mapperTableRepo.save(mp);
			return new ResponseEntity<MapperCollection>(mp,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<String>("somthing went wrong",HttpStatus.BAD_REQUEST);
		}
		
		
		
	}
}
