package com.qa.api.scehma.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.Base.Base;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.UserPojo;
import com.qa.api.utils.SchemaValidator;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetAllUserSchemaValidationTest extends Base{
	
	
@Test(priority = 1)
	
	public void getallusersSchemaValidation() {
		
	
	Response response = client.get(GOREST_BASE_URI, GOREST_ENDPOINT, null, null, AuthType.GOREST_BEARER_TOKEN, ContentType.JSON);
	AssertJUnit.assertTrue(SchemaValidator.validateschema(response, "Schemas/GorestGetAllUserScehma.json"));
	
}

@Test(priority = 1, enabled = false)

public void getSingleusersSchemaValidation() {
	

Response response = client.get(GOREST_BASE_URI, GOREST_ENDPOINT+"/"+8361391, null, null, AuthType.GOREST_BEARER_TOKEN, ContentType.JSON);
AssertJUnit.assertTrue(SchemaValidator.validateschema(response, "Schemas/GetSingleUserSchema.json"));

}

@Test(priority = 1)

public void postSingleusersSchemaValidation() {
	

	
	System.out.println("Creating  a User Using Post call");
	
	UserPojo userdetails = UserPojo.builder()
			.name("Frameworjuser1")
			.email(StringUtils.generateRandomEmail())
			.gender("male")
			.status("active").build();
	
	Response createresponse = client.post(GOREST_BASE_URI, GOREST_ENDPOINT, userdetails, null, AuthType.GOREST_BEARER_TOKEN, ContentType.JSON);
	AssertJUnit.assertTrue(SchemaValidator.validateschema(createresponse, "Schemas/PostCallSchema.json"));

}

@Test(priority = 1)

public void getallcartsSchemaValidation() {
	

Response response = client.get(FAKESTORE_BASE_URI, FAKESTORE_CARTS, null, null, AuthType.NO_AUTH, ContentType.JSON);
AssertJUnit.assertTrue(SchemaValidator.validateschema(response, "Schemas/FakeStoreCart.json"));

}

@Test(priority = 1)

public void getallproductsSchemaValidation() {
	

Response response = client.get(FAKESTORE_BASE_URI, FAKESTORE_PRODUCT, null, null, AuthType.NO_AUTH, ContentType.JSON);
AssertJUnit.assertTrue(SchemaValidator.validateschema(response, "Schemas/FakeStoreProduct.json"));

}



//Validate only the following

	// If we miss any key
	//If we data type is mismatched


//Skips the Validate of the following

	//Validates only required field is present if other fields are present it just omits.
	//It wont validate the Spelling of the Key


}
