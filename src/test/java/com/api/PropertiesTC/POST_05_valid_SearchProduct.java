package com.api.PropertiesTC;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.testng.Assert.*;
import org.testng.annotations.*;


public class POST_05_valid_SearchProduct {
	private Response response;
	private RequestSpecification request;
	String product_name = "Winter Top";
	
	@BeforeClass
	public void openRequest()
	{
		RestAssured.baseURI = "https://automationexercise.com/";
		RestAssured.basePath = "api/searchProduct";
		
		request = RestAssured.given()
				.contentType("multipart/form-data")
				.multiPart("search_product","jeans");
		
		response = request.post();
		System.out.println("Result of Response: \n" + response.body().jsonPath().prettyPrint());
	}
	
	@Test
	public void TC01_verifyStatusCode()
	{
		assertEquals(200, response.getStatusCode(), "valid StatusCode 200 OK: FAILED");
	}
	
	@Test
	public void TC02_validProduct_Name()
	{
		assertTrue(response.asString().contains("category"), "valid Product_Category field: FAILED");
		assertTrue(response.asString().contains("Jeans"), "valid search_product keyword: FAILED");
	}
	
	@Test
	public void TC03_countProduct_Name()
	{
		System.out.println("==> Quantity of Result: "  + response.body().jsonPath().getList("products").size());
	}

}
