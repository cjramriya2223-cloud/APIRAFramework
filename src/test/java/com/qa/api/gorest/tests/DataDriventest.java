package com.qa.api.gorest.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.qa.api.Base.Base;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.UserPOJOResponse;
import com.qa.api.pojo.UserPojo;
import com.qa.api.utils.ExcelUtil;
import com.qa.api.utils.ObjectmapperUtil;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DataDriventest extends Base{
	
	
	@DataProvider
	public Object[][] getuserdetails() {
		
		return new Object[][] {
			{"Rajesh1","male","active"},
			{"Anu","female","active"},
			{"Divya","female","active"},
			{"Karthick","male","inactive"},
			{"Riya","female","active"}
			
			
		};
		
	}
	
@Test(dataProvider = "getuserdetails")
		
		public void createuserByPassingValueDirectly(String name, String gender, String status) throws JsonMappingException, JsonProcessingException {
			
			System.out.println("Creating  a User Using Post call");
			
			String email = StringUtils.generateRandomEmail();
			
			UserPojo requestdata = UserPojo.builder()
					
					.name(name)
					.email(email)
					.gender(gender)
					.status(status).build();
			
			Response createresponse = client.post(GOREST_BASE_URI, GOREST_ENDPOINT, requestdata, null, AuthType.GOREST_BEARER_TOKEN, ContentType.JSON);
			
				AssertJUnit.assertEquals(createresponse.statusCode(), 201);
				AssertJUnit.assertTrue(createresponse.statusLine().contains("Created"));
			
			int Userid = createresponse.jsonPath().getInt("id");
			
			System.out.println("Get the newly created user details");
			
			Response getresponse =  client.get(GOREST_BASE_URI, GOREST_ENDPOINT+"/"+Userid, null, null, AuthType.GOREST_BEARER_TOKEN, ContentType.JSON);
	
	
			UserPOJOResponse userdetails = ObjectmapperUtil.deserialize(getresponse, UserPOJOResponse.class);
			
			AssertJUnit.assertEquals(getresponse.statusCode(), 200);
			AssertJUnit.assertTrue(getresponse.statusLine().contains("OK"));
			
			AssertJUnit.assertEquals(userdetails.getName(), requestdata.getName());
			AssertJUnit.assertEquals(userdetails.getEmail(),email);
			AssertJUnit.assertEquals(userdetails.getGender(),requestdata.getGender());
			AssertJUnit.assertEquals(userdetails.getStatus(),requestdata.getStatus());
			System.out.println("All asseryions are passed");

}

@DataProvider
public Object[][] getuserdetailsviafile() throws EncryptedDocumentException, IOException{
	return ExcelUtil.readData("createuser");
	
}


@Test(dataProvider = "getuserdetailsviafile")

public void createuserByPassingValueViaFile(String name, String gender, String status) throws JsonMappingException, JsonProcessingException {
	
	System.out.println("Creating  a User Using Post call");
	
	String email = StringUtils.generateRandomEmail();
	
	UserPojo requestdata = UserPojo.builder()
			
			.name(name)
			.email(email)
			.gender(gender)
			.status(status).build();
	
	Response createresponse = client.post(GOREST_BASE_URI, GOREST_ENDPOINT, requestdata, null, AuthType.GOREST_BEARER_TOKEN, ContentType.JSON);
	
		AssertJUnit.assertEquals(createresponse.statusCode(), 201);
		AssertJUnit.assertTrue(createresponse.statusLine().contains("Created"));
	
	int Userid = createresponse.jsonPath().getInt("id");
	
	System.out.println("Get the newly created user details");
	
	Response getresponse =  client.get(GOREST_BASE_URI, GOREST_ENDPOINT+"/"+Userid, null, null, AuthType.GOREST_BEARER_TOKEN, ContentType.JSON);


	UserPOJOResponse userdetails = ObjectmapperUtil.deserialize(getresponse, UserPOJOResponse.class);
	
	AssertJUnit.assertEquals(getresponse.statusCode(), 200);
	AssertJUnit.assertTrue(getresponse.statusLine().contains("OK"));
	
	AssertJUnit.assertEquals(userdetails.getName(), requestdata.getName());
	AssertJUnit.assertEquals(userdetails.getEmail(),email);
	AssertJUnit.assertEquals(userdetails.getGender(),requestdata.getGender());
	AssertJUnit.assertEquals(userdetails.getStatus(),requestdata.getStatus());
	System.out.println("All asseryions are passed");

}
}