package com.api.PropertiesTC;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.testng.Assert.*;
import org.testng.annotations.*;


public class POST_07_valid_VerifyLogin {
	private Response response;
	private RequestSpecification request;
	
	@BeforeClass
	public void openRequest()
	{
		RestAssured.baseURI = "https://automationexercise.com/";
		RestAssured.basePath = "api/verifyLogin";
		
		request = RestAssured.given()
				.contentType("multipart/form-data")
				.multiPart("email","funixExample3@gmail.com")
				.multiPart("password","Funix1122");
		
		response = request.post();
		System.out.println("Result of Response: \n" + response.body().jsonPath().prettyPrint());
	}
	
	@Test
	public void TC01_verifyStatusCode()
	{
		assertEquals(200, response.getStatusCode(), "valid StatusCode 200 OK: FAILED");
	}
	
	@Test
	public void TC02_verifyResponseCode()
	{
		assertTrue(response.asString().contains("responseCode"), "valid ResponseCode field: FAILED");
		assertEquals(200, response.body().jsonPath().getInt("responseCode"), "valid ResponseCode value: NOT matching");
	}
	
	@Test
	public void TC03_verifyMessageField()
	{
		assertTrue(response.asString().contains("message"), "valid Message field: FAILED");
		String expectedMsg = "User exists!";
		assertEquals(response.body().jsonPath().getString("message"), expectedMsg, "valid Message value: NOT matching");
	}
}
