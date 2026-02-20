package com.qa.api.gorest.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.Base.*;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.ContactRequestPOJO;
import com.qa.api.pojo.UserPojo;
import com.qa.api.utils.ExcelUtil;
import com.qa.api.utils.StringUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PostCallsTest extends Base {
	

	@Test
	
 	public void createuserByPojowithoutBuilder() {
		

		Map<String, String> pathparams = new HashMap<String,String>();
		pathparams.put("access", "public");
		pathparams.put("version","v2");
		
		String email = StringUtils.generateRandomEmail();
		
		UserPojo userdetails = new UserPojo("Frameworjuser1",email, "male","active");
		
		Response response = client.post(GOREST_BASE_URI, GOREST_ENDPOINT, userdetails, null, AuthType.GOREST_BEARER_TOKEN, ContentType.JSON);
		AssertJUnit.assertEquals(response.statusCode(), 201);
		AssertJUnit.assertTrue(response.statusLine().contains("Created"));
		AssertJUnit.assertEquals(response.jsonPath().getString("name"),userdetails.getName() );
		AssertJUnit.assertEquals(response.jsonPath().getString("status"),userdetails.getStatus() );
		AssertJUnit.assertEquals(response.jsonPath().getString("email"),userdetails.getEmail() );
		AssertJUnit.assertEquals(response.jsonPath().getString("gender"),userdetails.getGender() );
		System.out.println("All assertions are passed");
		
	}
	
	@DataProvider
	public Object[][] getUserData() {
		return new Object[][] {
			{"karim1","Male","active",},
			{"karim2","Male","active",},
			{"karim3","Male","active",}
			
		};
	}
	

@Test(dataProvider = "getUserData")
	
 	public void createuserByPojowithBuilder(String name, String gender, String status) {
		
		
		String email = StringUtils.generateRandomEmail();
		
		UserPojo userdetails = UserPojo.builder()
				.name(name)
				.email(email)
				.gender(gender)
				.status(status).build();
		
		Response response = client.post(GOREST_BASE_URI, GOREST_ENDPOINT, userdetails, null, AuthType.GOREST_BEARER_TOKEN, ContentType.JSON);
		AssertJUnit.assertEquals(response.statusCode(), 201);
		AssertJUnit.assertTrue(response.statusLine().contains("Created"));
		ChainTestListener.log(response.getBody().asString());
		System.out.println("All assertions are passed");
		
	}

@Test(enabled=false)

	public void createuserByPassingFilewithRandomEmail() throws IOException {


	Map<String, String> pathparams = new HashMap<String,String>();
	pathparams.put("access", "public");
	pathparams.put("version","v2");
	
	String email = StringUtils.generateRandomEmail();
	
	String rawbody = new String(Files.readAllBytes(Paths.get("./src/test/resources/TestData/gorestpostcall.json")));
	
	String updatedbody = rawbody.replace("{{email}}", email);
	
	Response response = client.post(GOREST_BASE_URI, GOREST_ENDPOINT, updatedbody, null, AuthType.GOREST_BEARER_TOKEN, ContentType.JSON);
	AssertJUnit.assertEquals(response.statusCode(), 201);
	AssertJUnit.assertTrue(response.statusLine().contains("Created"));
	System.out.println("All assertions are passed");

}
	
	
	
	

}
