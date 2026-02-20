package com.qa.api.gorest.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.Base.Base;
import com.qa.api.constants.AuthType;
import com.qa.api.constants.URIAndEndpoints;
import com.qa.api.pojo.UserPojo;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PatchCallTest extends Base {
	
	//create a user
		//get the user
		//patch call - updating the user details
		//get call 

	
	@Test
	
	public void updatingUserDetailUsingPutCall() {
		
	System.out.println("---------------------------------------------------------------------------");
		
	System.out.println("Creating  a User Using Post call");
		
		UserPojo userdetails = UserPojo.builder()
				.name("Frameworjuser1")
				.email(StringUtils.generateRandomEmail())
				.gender("male")
				.status("inactive").build();
		
		Response createresponse = client.post(GOREST_BASE_URI, GOREST_ENDPOINT, userdetails, null, AuthType.GOREST_BEARER_TOKEN, ContentType.JSON);
		
			AssertJUnit.assertEquals(createresponse.statusCode(), 201);
			AssertJUnit.assertTrue(createresponse.statusLine().contains("Created"));
		
			int Userid = createresponse.jsonPath().getInt("id");
			
	System.out.println("---------------------------------------------------------------------------");	
		
	System.out.println("Get the newly created user details");
		
		Response getresponse = client.get(GOREST_BASE_URI, GOREST_ENDPOINT.concat("/"+Userid), null, null, AuthType.GOREST_BEARER_TOKEN, ContentType.JSON);
		
			AssertJUnit.assertEquals(getresponse.statusCode(), 200);
			AssertJUnit.assertTrue(getresponse.statusLine().contains("OK"));
			
			AssertJUnit.assertEquals(getresponse.jsonPath().getString("name"),userdetails.getName() );
			AssertJUnit.assertEquals(getresponse.jsonPath().getString("status"),userdetails.getStatus() );
			AssertJUnit.assertEquals(getresponse.jsonPath().getString("email"),userdetails.getEmail() );
			AssertJUnit.assertEquals(getresponse.jsonPath().getString("gender"),userdetails.getGender() );
			System.out.println("All assertions are passed");
		

	
	System.out.println("---------------------------------------------------------------------------");
	
	userdetails.setStatus("active");
	
	System.out.println("Update the newly created user details using Patch Call");
	
		Response putresponse = client.patch(GOREST_BASE_URI, GOREST_ENDPOINT.concat("/"+Userid), userdetails, null, AuthType.GOREST_BEARER_TOKEN, ContentType.JSON);
	
			AssertJUnit.assertEquals(putresponse.statusCode(), 200);
			AssertJUnit.assertTrue(putresponse.statusLine().contains("OK"));
			System.out.println("Updated Gender : "+ putresponse.jsonPath().getString("gender"));
			
		
	System.out.println("---------------------------------------------------------------------------");	

    System.out.println("Get the updated Gender in the user details of newly created user");	

		Response updatedgetresponse = client.get(GOREST_BASE_URI, GOREST_ENDPOINT.concat("/"+Userid), null, null, AuthType.GOREST_BEARER_TOKEN, ContentType.JSON);
		
			AssertJUnit.assertEquals(updatedgetresponse.statusCode(), 200);
			AssertJUnit.assertTrue(updatedgetresponse.statusLine().contains("OK"));
		
			AssertJUnit.assertEquals(updatedgetresponse.jsonPath().getString("name"),userdetails.getName() );
			AssertJUnit.assertEquals(updatedgetresponse.jsonPath().getString("status"),userdetails.getStatus() );
			AssertJUnit.assertEquals(updatedgetresponse.jsonPath().getString("email"),userdetails.getEmail() );
			AssertJUnit.assertEquals(updatedgetresponse.jsonPath().getString("gender"),userdetails.getGender() );
			System.out.println("All assertions are passed");
	
	
}

	
	
	
	}

