package com.api.PropertiesTC;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;
import static org.testng.Assert.*;


public class DEL_12_deleteUser {
	private Response response;
	private RequestSpecification request;
	
	@BeforeClass
	public void openRequest()
	{
		RestAssured.baseURI = "https://automationexercise.com/";
		RestAssured.basePath = "api/deleteAccount";
		
		request = RestAssured.given().contentType("multipart/form-data")
				.multiPart("email", "quyentest01@gmail.com")
				.multiPart("password", "quyenTest01");
		
		response = request.delete();
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
	public void TC03_verifyMessage()
	{
		assertTrue(response.asString().contains("message"), "valid Message field: FAILED");
		String expectedMsg = "Account deleted!";
		assertEquals(response.body().jsonPath().getString("message"), expectedMsg, "valid Message value: NOT matching");
	}

}
