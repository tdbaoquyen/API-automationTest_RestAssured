package com.api.PropertiesTC;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import java.util.*;


public class POST_06_invalid_SearchProduct {
	private Response response;
	private RequestSpecification request;
	String product_name = "Winter Top";
	
	@BeforeClass
	public void openRequest()
	{
		RestAssured.baseURI = "https://automationexercise.com/";
		RestAssured.basePath = "api/searchProduct";
		
		Map<String, Object> searchItem = new HashMap<String, Object>();
		searchItem.put("search_product", product_name);
		
		request = RestAssured.given().contentType(ContentType.JSON).body(searchItem);
		
		response = request.post();
		System.out.println("Result of Response: \n" + response.body().jsonPath().prettyPrint());
	}
	
	@Test
	public void TC01_verifyStatusCode()
	{
		assertEquals(400, response.getStatusCode(), "valid StatusCode 400: FAILED");
	}
	
	@Test
	public void TC02_verifyResponseCode()
	{
		assertTrue(response.asString().contains("responseCode"), "valid ResponseCode field: FAILED");
		assertEquals(response.body().jsonPath().getInt("responseCode"), 400, "valid ResponseCode: NOT matching expected value");
	}
	
	@Test
	public void TC03_verifyMessage()
	{
		assertTrue(response.asString().contains("message"), "valid Message field: FAILED");
		String expectedMsg = "Bad request, search_product parameter is missing in POST request.";
		assertEquals(response.body().jsonPath().getString("message"), expectedMsg, "valid Message: NOT matching expected value");
	}

}
