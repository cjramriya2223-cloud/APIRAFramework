package com.qa.api.Base;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.client.RestClient;
import com.qa.api.constants.URIAndEndpoints;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

@Listeners(ChainTestListener.class)
public class Base  implements URIAndEndpoints{
	
	protected RestClient client;

    @BeforeMethod
    public void setup() {
        client = new RestClient();
    }
    
    @BeforeSuite
    
    public void initSetup() {
    	RestAssured.filters(new AllureRestAssured());
    	
    }

   

}
