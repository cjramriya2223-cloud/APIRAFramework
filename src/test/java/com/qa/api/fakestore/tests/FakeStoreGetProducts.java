package com.qa.api.fakestore.tests;

import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.Base.Base;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.FakeStoreProductResponsePojo;
import com.qa.api.utils.JsonPathValidatorUtil;
import com.qa.api.utils.ObjectmapperUtil;
import com.qa.api.utils.SchemaValidator;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class FakeStoreGetProducts extends Base {
	
	@Test
	public void getallproduct() {
		
		Response response = client.get(FAKESTORE_BASE_URI, FAKESTORE_PRODUCT, null, null, AuthType.NO_AUTH, ContentType.JSON);
		
		FakeStoreProductResponsePojo[] products = ObjectmapperUtil.deserialize(response, FakeStoreProductResponsePojo[].class);
		
		SchemaValidator.validateschema(response, "Schemas/FakeStoreProduct.json");
		
		//Single Element
		
		int lenghtofthelist =JsonPathValidatorUtil.readResponseWithSingleElement(response, "length($)");
		
		System.out.println("Lenght of an array : " +lenghtofthelist);
		
		double minprice =JsonPathValidatorUtil.readResponseWithSingleElement(response, "min($.[*].price)");
		
		System.out.println("Minimum Price : " +minprice);
		
		double maxprice =JsonPathValidatorUtil.readResponseWithSingleElement(response, "max($.[*].price)");
		
		System.out.println("maximum Price : " +maxprice);
		
		
		//List of single Attribute
		
		List<Integer> productid = JsonPathValidatorUtil.readListOfResponseWithSingleAttribute(response, "$.[?(@.title =~ /.*Gold.*/i)].['id']");
		
		System.out.println(productid);
		
		List<String> producttitle = JsonPathValidatorUtil.readListOfResponseWithSingleAttribute(response, "$.[?(@.title =~ /.*Gold.*/i)].['title']");
		
		System.out.println(producttitle);
		
		
		List<Double> productratingrate = JsonPathValidatorUtil.readListOfResponseWithSingleAttribute(response, "$.[?(@.title =~ /.*Gold.*/i)].rating.rate");
		
		System.out.println(productratingrate);
		
		
		//List of Multiple Attribute
		

		List<Map<String, Object>> productidtitle = JsonPathValidatorUtil.readListOfResponseWithSingleAttribute(response, "$.[?(@.title =~ /.*Gold.*/i)].['id','title']");
		
		
		System.out.println("-------------------------------------Two Attribute response ------------------------------------------------------");
		for(Map<String, Object> elements : productidtitle) {
			
			System.out.println("Id : " +elements.get("id"));
			System.out.println("Title : " + elements.get("title"));
			System.out.println("---------------");
		}
		
		List<Map<String, Object>> productidtitleprice = JsonPathValidatorUtil.readListOfResponseWithSingleAttribute(response, "$.[?(@.title =~ /.*Gold.*/i)].['id','price','title']");
		
		System.out.println("---------------------------------------Three Attribute response ----------------------------");
		
		for(Map<String, Object> elements : productidtitleprice) {
			
			System.out.println("Id : " +elements.get("id"));
			System.out.println("Title : " + elements.get("title"));
			System.out.println("Price : " + elements.get("price"));
			System.out.println("---------------");
		}
		
		
		List<Map<String, Object>> productidtitlepricecategory = JsonPathValidatorUtil.readListOfResponseWithSingleAttribute(response, "$.[?(@.title =~ /.*Gold.*/i)].['id','price','title','category']");
		
		
		System.out.println("---------------------------------------Four Attribute response ----------------------------");
		for(Map<String, Object> elements : productidtitlepricecategory) {
			
			System.out.println("Id : " +elements.get("id"));
			System.out.println("Title : " + elements.get("title"));
			System.out.println("Price : " + elements.get("price"));
			System.out.println("Category : " + elements.get("category"));
			
			System.out.println("---------------");
		}
		
		
		
	}

}
