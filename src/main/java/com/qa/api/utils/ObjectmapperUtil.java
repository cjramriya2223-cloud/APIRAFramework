package com.qa.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

public class ObjectmapperUtil {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static <T>T deserialize(Response response, Class<T> targetClass){
		
		try {
			return mapper.readValue(response.getBody().asString(), targetClass);
		} catch (JsonMappingException e) {
			System.out.println("Deserialzation is failed -->" +targetClass);
			return null;
		} catch (JsonProcessingException e) {
			System.out.println("Deserialzation is failed -->" +targetClass);
			return null;
		}
		
		
	}
	
	
	

}
