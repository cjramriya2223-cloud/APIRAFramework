package com.qa.api.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.response.Response;

public class JsonPathValidatorUtil {
	
	private static String getResponseAsString(Response response) {
		
		return response.getBody().asString();
	}

	
	public static <T>T readResponseWithSingleElement(Response response, String jsonquery) {
		
		
		ReadContext ctx = JsonPath.parse(getResponseAsString(response));
		
		return ctx.read(jsonquery);
		
	}
	
	public static <T> List<T> readListOfResponseWithSingleAttribute(Response response, String jsonquery) {
		
		
		ReadContext ctx = JsonPath.parse(getResponseAsString(response));
		
		return ctx.read(jsonquery);
		
	}
	
	public static <T> List<Map<String, T>> readListOfResponseWithMultipleAttribute(Response response, String jsonquery) {
		
		
		ReadContext ctx = JsonPath.parse(getResponseAsString(response));
		
		return ctx.read(jsonquery);
		
	}
	
	
	
	
	
	
}
