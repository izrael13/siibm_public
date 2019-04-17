package com.websystique.springmvc.utilities;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

public class JsonFunctions {
	
	public JSONObject jsonObject(String jsonStr)
	{
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try
		{
		
			jsonObject = (JSONObject) parser.parse(jsonStr);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return jsonObject;
	}
	
	public String ObjToStringJson(Object o)
	{
		Gson  gson = new Gson();
		return gson.toJson(o);
	}
	
	/*public static void main(String args[])
	{
		User u = new User();
		u.setEmail("email");
		u.setFirstName("nombres");
		u.setId(1);
		u.setLastName("apellido");
		u.setPassword("contraseña");
		u.setSsoId("idm");
		JsonFunctions j = new JsonFunctions();
		System.out.println(j.ObjToStringJson(u));
	}*/
}
