package com.qa.api.gorest.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.HashMap;
import java.util.Map;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.qa.api.Base.Base;
import com.qa.api.constants.AuthType;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;




@Epic("JIRA TICKET #12352 - Automation API from GOrest and Fake sotre")
@Feature("JIRA TICKET #123 Get All users and products from Gorest and Fake store")
@Story("JIRA TICKET #12121 Verify Get Call of Gorest and Fake Store")

public class GetAllUserTest extends Base{
	
	
	@Owner("Jamuna")
	@Severity(SeverityLevel.CRITICAL)
	@Description(" Get all Gorest Users")
	@Test(priority = 1)

	public void getallusers() {
		
		Map<String, String> pathparams = new HashMap<String,String>();
		pathparams.put("access", "public");
		pathparams.put("version","v2");
 		
	Response response = client.get(GOREST_BASE_URI, GOREST_ENDPOINT, null, null, AuthType.GOREST_BEARER_TOKEN, ContentType.JSON);
	AssertJUnit.assertEquals(response.statusCode(), 200);
	AssertJUnit.assertTrue(response.statusLine().contains("OK"))
	
	;
	
}
	
	@Owner("Jamuna")
	@Severity(SeverityLevel.TRIVIAL)
	@Description(" Get all Gorest Male and active Userrs")
	@Test(priority = 2)
	
	public void getMaleandActiveusers() {
		
		Map<String, String> queryparam = new HashMap<String, String>();
		queryparam.put("gender", "male");
		queryparam.put("status","active");
		
		Map<String, String> pathparams = new HashMap<String,String>();
		pathparams.put("access", "public");
		pathparams.put("version","v2");
 		
		
	Response response = client.get(GOREST_BASE_URI, GOREST_ENDPOINT, queryparam, null, AuthType.GOREST_BEARER_TOKEN, ContentType.JSON);
	AssertJUnit.assertEquals(response.statusCode(), 200);
	AssertJUnit.assertTrue(response.statusLine().contains("OK"));
	
	}
	
	
	@Owner("Geetha")
	@Severity(SeverityLevel.MINOR)
	@Description(" Get a Gorest single  Users")
	@Test(priority = 3)
	
	public void getSingleusers() {
		
		String userid = "8371243";
		
		Map<String, String> pathparams = new HashMap<String,String>();
		pathparams.put("access", "public");
		pathparams.put("version","v2");
 		
		
	Response response = client.get(GOREST_BASE_URI, GOREST_ENDPOINT.concat("/"+userid), null, null, AuthType.GOREST_BEARER_TOKEN, ContentType.JSON);
	AssertJUnit.assertEquals(response.statusCode(), 200);
	AssertJUnit.assertTrue(response.statusLine().contains("OK"));
	
	
	System.out.println("All assertions are passed.");
	
	}
	
	@Owner("Geetha")
	@Description("Get all Male And active Users")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 4)
	
	public void getFeMaleandInActiveusers() {
		
		Map<String, String> queryparam = new HashMap<String, String>();
		queryparam.put("gender", "male");
		queryparam.put("status","active");
		
	Response response = client.get(GOREST_BASE_URI, GOREST_ENDPOINT, queryparam, null, AuthType.GOREST_BEARER_TOKEN, ContentType.JSON);
	AssertJUnit.assertEquals(response.statusCode(), 200);
	AssertJUnit.assertTrue(response.statusLine().contains("OK"));
	
	}
	
	@Owner("Anu")
	@Description("Get All Fake Store Products ")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 5)
	
	public void getallFakeStoreProducts() {
		
	Response response = client.get(FAKESTORE_BASE_URI, FAKESTORE_PRODUCT, null, null, AuthType.NO_AUTH, ContentType.JSON);
	AssertJUnit.assertEquals(response.statusCode(), 200);
	AssertJUnit.assertTrue(response.statusLine().contains("OK"));
	
}
	@Owner("Rajesh")
	@Description("Get all Fake store Users")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 6)
	
	public void getallFakeStoreUsers() {
		
	Response response = client.get(FAKESTORE_BASE_URI, FAKESTORE_USERS, null, null, AuthType.NO_AUTH, ContentType.JSON);
	AssertJUnit.assertEquals(response.statusCode(), 200);
	AssertJUnit.assertTrue(response.statusLine().contains("OK"));
	
}
}
