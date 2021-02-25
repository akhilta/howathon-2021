package com.sapient.teamsApi.Helpers;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonToStringToJson {

	
	public static JSONObject convertStringtoJson(String string) {
		JSONObject result=null;
		try {
		     JSONObject jsonObject = new JSONObject(string);
		     //System.out.println(jsonObject);
		     result=jsonObject;
		 
		}catch (JSONException err){

			
		}
		return result;
	}
}
