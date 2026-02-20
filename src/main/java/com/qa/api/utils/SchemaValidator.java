package com.qa.api.utils;

import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaValidator {
	
	public static boolean validateschema(Response response, String Filename) {
		
		try {
			response
				.then()
					.assertThat()
						.body(matchesJsonSchemaInClasspath(Filename));
			System.out.println("Schema Validation Passed =====> "+Filename);
			return true;
		} catch (Exception e) {
			System.out.println("Schema Validation Failed =====> "+Filename);
			return false;
		}
		
	}
	
}
