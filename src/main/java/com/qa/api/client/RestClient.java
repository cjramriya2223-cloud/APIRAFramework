package com.qa.api.client;

import java.io.File;
import java.util.Base64;
import java.util.Map;



import static org.hamcrest.Matchers.*;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.constants.AuthType;
import com.qa.api.constants.URIAndEndpoints;
import com.qa.api.manager.ConfigReader;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;



public class RestClient {
	
	private ResponseSpecification responsestatus200or404 = RestAssured.expect().statusCode(anyOf(equalTo(200),equalTo(404)));
	
	private ResponseSpecification responsestatus204 = RestAssured.expect().statusCode(204);
	
	private ResponseSpecification responsestatus201or200 = RestAssured.expect().statusCode(anyOf(equalTo(201),equalTo(200)));
	
	private RequestSpecification setupRequest(String Baseuri, AuthType authtype, ContentType contenttype ) {
		
		RequestSpecification request = 	given().log().all()
												.baseUri(Baseuri)
												.contentType(contenttype);
		
		switch(authtype){
		
		case GOREST_BEARER_TOKEN:
			
			request.header("Authorization" , " Bearer ".concat(ConfigReader.get("gorest_bearer_token")).trim());
			break;
			
		case CONTACT_BEARER_TOKEN:
			
			request.header("Authorization" , " Bearer ".concat(ConfigReader.get("Contact_bearer_token")).trim());
			break;	
			
		case BASIC_TOKEN:
			
			request.header("Authorization" , "Basic "+generateBasicAuth());
			break;
			
		case API_KEY:
	
			request.header("x-api-key" , ConfigReader.get("api_key_value"));
			break;
			
		case NO_AUTH:
			
			System.out.println("Authorization not required");
			break;
			
		default:
			
			System.out.println("The Authorization token provided is "
					+ "not working or not supported please provide the correct token");
		}
			
		return request;
		
	}
	
	private static String generateBasicAuth() {
		
		//admin:admin //Username :password
		
		String credentials = ConfigReader.get("basicauthUsername").trim()+ ":"+ConfigReader.get("bacisauthPassword").trim();
		
		return Base64.getEncoder().encodeToString(credentials.getBytes());
		
	}
	
	
	
	private void applyParams(RequestSpecification request, Map<String, String> queryparams,
			Map<String, String> pathparams) {
		
		if(queryparams !=null) {
			request.queryParams(queryparams);
			}
		if(pathparams !=null) {
			request.pathParams(pathparams);
			}
		
	}
	
	public Response get(String Baseuri, String Endpoint,
			Map<String, String> queryparams,
			Map<String, String> pathparams,
			AuthType authtype,
			ContentType contenttype) {
		
		RequestSpecification request = setupRequest(Baseuri,authtype, contenttype);
		applyParams(request, queryparams, pathparams);
		
		ChainTestListener.log("Base URL: " +Baseuri+Endpoint);
		
		ChainTestListener.log("Auth Type: " +authtype);
		
		Response response = request.get(Endpoint).then().spec(responsestatus200or404).extract().response();
		
		response.prettyPrint();
		
		return response;
	}
	//T is generic type placeholder in java
	//This method can accept request body of any data type or request payload of any data type
	
	
	
	
	public <T>Response post(String Baseuri, String Endpoint,T body,
			Map<String, String> pathparams,
			AuthType authtype,
			ContentType contenttype) {
		
		RequestSpecification request = setupRequest(Baseuri,authtype, contenttype);
		applyParams(request, null, pathparams);
		Response response = request.body(body).post(Endpoint).then().spec(responsestatus201or200).extract().response();
		
		response.prettyPrint();
		
		return response;
	}
	
	
	public <T>Response postUsingFile(String Baseuri, String Endpoint,File body,
			Map<String, String> pathparams,
			AuthType authtype,
			ContentType contenttype) {
		
		RequestSpecification request = setupRequest(Baseuri,authtype, contenttype);
		applyParams(request, null, pathparams);
		Response response = request.body(body).post(Endpoint).then().spec(responsestatus201or200).extract().response();
		
		response.prettyPrint();
		
		return response;
	}
	

	public <T>Response put(String Baseuri, String Endpoint,T body,
			Map<String, String> pathparams,
			AuthType authtype,
			ContentType contenttype) {
		
		RequestSpecification request = setupRequest(Baseuri,authtype, contenttype);
		applyParams(request, null, pathparams);
		Response response = request.body(body).put(Endpoint).then().spec(responsestatus201or200).extract().response();
		
		response.prettyPrint();
		
		return response;
	}
	
	public <T>Response patch(String Baseuri, String Endpoint,T body,
			Map<String, String> pathparams,
			AuthType authtype,
			ContentType contenttype) {
		
		RequestSpecification request = setupRequest(Baseuri,authtype, contenttype);
		applyParams(request, null, pathparams);
		Response response = request.body(body).patch(Endpoint).then().spec(responsestatus200or404).extract().response();
		
		response.prettyPrint();
		
		return response;
	}
	
	public <T>Response delete(String Baseuri, String Endpoint,
			Map<String, String> pathparams,
			AuthType authtype,
			ContentType contenttype) {
		
		RequestSpecification request = setupRequest(Baseuri,authtype, contenttype);
		applyParams(request, null, pathparams);
		Response response = request.delete(Endpoint).then().spec(responsestatus204).extract().response();
		
		response.prettyPrint();
		
		return response;
	}
	
	
	
//Put call	
	//baseuri
	//endpoint+Userid
	//pathparams
	//body
	//content type
	//auth
	
	//1258946
	
	//put == if user is not avaiable , new user will be created and get stored in the database
	//201 or 200
	
//patch -- 200 status
	
	//baseuri
		//endpoint+Userid
		//pathparams
		//body
		//content type
		//auth
		
		//1258946
		
	
//delete call
	
	
	//baseuri
	//endpoint+Userid
	//pathparams
	//content type
	//auth type
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
