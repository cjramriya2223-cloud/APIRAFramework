package com.qa.api.contacts.tests;

import org.testng.annotations.Test;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.Base.Base;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.ContactRequestPOJO;
import com.qa.api.pojo.UserPojo;
import com.qa.api.utils.ExcelUtil;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTest extends Base {
	
	@DataProvider
	
	public Object[][] getcontactuserdetails() throws IOException{
		return ExcelUtil.readData("contacts");
	}
	
	@Test(dataProvider ="getcontactuserdetails", enabled = false )
	
	public void createcontact(String firstName, String lastName) {
	
	System.out.println("Creating  a User Using Post call");
	
	ContactRequestPOJO requestdata =  ContactRequestPOJO.builder()
			.firstName(firstName)
			.lastName(lastName)
			.build();
	
	Response createresponse = client.post(CONTACTS_ADD_CONTACTS,CONTACT_USERS,requestdata, null, AuthType.CONTACT_BEARER_TOKEN, ContentType.JSON);
	
		//Assert.assertEquals(createresponse.statusCode(), 201);
		//Assert.assertTrue(createresponse.statusLine().contains("Created"));
	
	
	}

}
