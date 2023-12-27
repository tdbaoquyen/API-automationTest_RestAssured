package com.api.PropertiesTC;

import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.testng.Assert.*;
import org.testng.annotations.*;


public class GET_03_Brands_List {
	private Response response;
	private RequestSpecification request;
	
	@BeforeClass
	public void openRequest()
	{
	//	thiết lập Endpoint Request
		RestAssured.baseURI = "https://automationexercise.com/";
		RestAssured.basePath = "api/brandsList";
		
		request = RestAssured.given()
				.contentType(ContentType.JSON)
				.when();
	
	//	thực hiện GET và xuất ra chi tiết Response
		response = request.get();
		
		System.out.println("Result of Response: \n" + response.body().jsonPath().prettyPrint());
		System.out.println("\n==> Quantity of products: " + response.body().jsonPath().getList("brands").size() + "\n");
	}
	
	@Test (priority = 1)
	public void TC01_verifyStatusCode()
	{
		assertEquals(200, response.getStatusCode(), "valid StatusCode 200 OK: FAILED");
	}
	
	@Test (priority = 2)
	public void TC02_verifyBrand_ID()
	{
		assertTrue(response.asString().contains("id"), "valid Brand_ID field: FAILED");
	}
	
	@Test (priority = 3)
	public void TC03_verifyBrand_field()
	{
		assertTrue(response.asString().contains("brand"), "valid Brand_name field: FAILED");
	}
	
	@AfterClass
	public void closeRequest()
	{
		RestAssured.baseURI = null;
		RestAssured.basePath = null;
	}

}
