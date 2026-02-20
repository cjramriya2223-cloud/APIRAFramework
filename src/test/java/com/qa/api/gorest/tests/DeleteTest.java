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
import com.qa.api.pojo.UserPojo;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteTest extends Base{
	
	//create
	//get
	//delete
	//get
	
	@Test
	
	public void updatingUserDetailUsingPutCall() {
		
	System.out.println("---------------------------------------------------------------------------");
		
	System.out.println("Creating  a User Using Post call");
		
		UserPojo userdetails = UserPojo.builder()
				.name("Frameworjuser1")
				.email(StringUtils.generateRandomEmail())
				.gender("male")
				.status("active").build();
		
		Response createresponse = client.post(GOREST_BASE_URI, GOREST_ENDPOINT, userdetails, null, AuthType.GOREST_BEARER_TOKEN, ContentType.JSON);
		
			AssertJUnit.assertEquals(createresponse.statusCode(), 201);
			AssertJUnit.assertTrue(createresponse.statusLine().contains("Created"));
			System.out.println("All assertions are passed");
		
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
	
	System.out.println("Delete the newly created user details");
	
		Response deleteresponse = client.delete(GOREST_BASE_URI, GOREST_ENDPOINT.concat("/"+Userid), null, AuthType.GOREST_BEARER_TOKEN, ContentType.JSON);
	
			AssertJUnit.assertEquals(deleteresponse.statusCode(), 204);
			AssertJUnit.assertTrue(deleteresponse.statusLine().contains("No Content"));
			System.out.println("All assertions are passed");
			
	System.out.println("---------------------------------------------------------------------------");
			
	System.out.println("Get the deleted newly created user details");
			

		Response getDeleteduserresponse = client.get(GOREST_BASE_URI, GOREST_ENDPOINT.concat("/"+Userid), null, null, AuthType.GOREST_BEARER_TOKEN, ContentType.JSON);
	
			AssertJUnit.assertEquals(getDeleteduserresponse.statusCode(), 404);
			AssertJUnit.assertTrue(getDeleteduserresponse.statusLine().contains("Not Found"));
			AssertJUnit.assertEquals(getDeleteduserresponse.jsonPath().getString("message"), "Resource not found");
			System.out.println("All assertions are passed");

}
}
