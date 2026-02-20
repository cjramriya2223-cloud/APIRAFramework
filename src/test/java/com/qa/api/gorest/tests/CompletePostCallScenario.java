package com.qa.api.gorest.tests;


import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import com.qa.api.pojo.ContactRequestPOJO;
import com.qa.api.pojo.ContactResponsePOJO;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.api.Base.Base;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.UserPOJOResponse;
import com.qa.api.pojo.UserPojo;
import com.qa.api.utils.ObjectmapperUtil;
import com.qa.api.utils.SchemaValidator;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class CompletePostCallScenario extends Base{
	
	@Test
	
	public void createuserinGorest() throws JsonMappingException, JsonProcessingException {
		
		System.out.println("Creating  a User Using Post call");
		
		UserPojo requestdata = UserPojo.builder()
				
				.name("Frameworjuser1")
				.email(StringUtils.generateRandomEmail())
				.gender("male")
				.status("active").build();
		
		Response createresponse = client.post(GOREST_BASE_URI, GOREST_ENDPOINT, requestdata, null, AuthType.GOREST_BEARER_TOKEN, ContentType.JSON);
		
			AssertJUnit.assertEquals(createresponse.statusCode(), 201);
			AssertJUnit.assertTrue(createresponse.statusLine().contains("Created"));
		
			int Userid = createresponse.jsonPath().getInt("id");
		System.out.println("Get the newly created user details");
		
		Response getresponse = client.get(GOREST_BASE_URI, GOREST_ENDPOINT.concat("/"+Userid), null, null, AuthType.GOREST_BEARER_TOKEN, ContentType.JSON);
		
			AssertJUnit.assertEquals(getresponse.statusCode(), 200);
			AssertJUnit.assertTrue(getresponse.statusLine().contains("OK"));
			
			UserPOJOResponse responsedata = ObjectmapperUtil.deserialize(getresponse, UserPOJOResponse.class);
		
			AssertJUnit.assertEquals(responsedata.getName(), requestdata.getName());
			AssertJUnit.assertEquals(responsedata.getStatus(), requestdata.getStatus());
			AssertJUnit.assertEquals(responsedata.getEmail(), requestdata.getEmail());
			AssertJUnit.assertEquals(responsedata.getGender(), requestdata.getGender());
		}
	
	
	@Test
	
	public void createuserinContact() throws JsonMappingException, JsonProcessingException {
		
		System.out.println("Creating  a User Using Post call");
		
		ContactRequestPOJO contactrequest = ContactRequestPOJO.builder()
				
				.firstName("Jamuna")
				.lastName("cerlin")
				.birthdate("2025-12-20")
				.email("apitesting2@gmail.com")
				.phone("8956231230")
				.street1("Sathy Rd")
				.street2("Saravanampatti")
				.city("Coimbatore")
				.stateProvince("Tamil Nadu")
				.postalCode("641035")
				.country("India")
				.build();
		
		Response createresponse = client.post(CONTACTS_ADD_CONTACTS, CONTACT_USERS, contactrequest, null, AuthType.CONTACT_BEARER_TOKEN, ContentType.JSON);
		
			AssertJUnit.assertEquals(createresponse.statusCode(), 201);
			AssertJUnit.assertTrue(createresponse.statusLine().contains("Created"));
		
			String Userid = createresponse.jsonPath().getString("_id");
		System.out.println("Get the newly created user details");
		
		Response getresponse = client.get(CONTACTS_ADD_CONTACTS, CONTACT_USERS.concat("/"+Userid), null, null, AuthType.CONTACT_BEARER_TOKEN, ContentType.JSON);
		
			AssertJUnit.assertEquals(getresponse.statusCode(), 200);
			AssertJUnit.assertTrue(getresponse.statusLine().contains("OK"));
			
			ContactResponsePOJO contactresponse = ObjectmapperUtil.deserialize(getresponse, ContactResponsePOJO.class);
			
			AssertJUnit.assertEquals(contactresponse.get_id(),Userid );
		
			AssertJUnit.assertEquals(contactresponse.getFirstName(),contactrequest.getFirstName() );
			
			AssertJUnit.assertEquals(contactresponse.getLastName(),contactrequest.getLastName() );
		
			AssertJUnit.assertEquals(contactresponse.getBirthdate(),contactrequest.getBirthdate() );
			
			AssertJUnit.assertEquals(contactresponse.getEmail(),contactrequest.getEmail() );
			
			AssertJUnit.assertEquals(contactresponse.getPhone(),contactrequest.getPhone() );
			
			AssertJUnit.assertEquals(contactresponse.getStreet1(),contactrequest.getStreet1() );
			
			AssertJUnit.assertEquals(contactresponse.getStreet2(),contactrequest.getStreet2() );
			
			AssertJUnit.assertEquals(contactresponse.getCity(),contactrequest.getCity());
			
			AssertJUnit.assertEquals(contactresponse.getStateProvince(),contactrequest.getStateProvince()) ;
			
			AssertJUnit.assertEquals(contactresponse.getPostalCode(),contactrequest.getPostalCode() );
			
			AssertJUnit.assertEquals(contactresponse.getCountry(),contactrequest.getCountry() );
			
			
			System.out.println("All Assertion are passed");
		
		
			
		}


}
