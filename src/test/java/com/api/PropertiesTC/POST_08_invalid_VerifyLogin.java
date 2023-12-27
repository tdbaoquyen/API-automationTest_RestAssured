package com.api.PropertiesTC;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.testng.Assert.*;
import org.testng.annotations.*;


public class POST_08_invalid_VerifyLogin {
	private Response response;
	private RequestSpecification request;
	
	@BeforeClass
	public void openRequest()
	{
		RestAssured.baseURI = "https://automationexercise.com/";
		RestAssured.basePath = "api/verifyLogin";
		
		request = RestAssured.given()
				.contentType("multipart/form-data")
				.multiPart("password","Funix1122");
		
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
		assertEquals(400, response.body().jsonPath().getInt("responseCode"), "valid ResponseCode value: FAILED");
	}
	
	@Test
	public void TC03_verifyMessage()
	{
		assertTrue(response.asString().contains("message"), "valid Message field: FAILED");
		String expectedMsg = "Bad request, email or password parameter is missing in POST request.";
		assertEquals(response.body().jsonPath().getString("message"), expectedMsg, "valid Message value: FAILED");
	}
	
}
