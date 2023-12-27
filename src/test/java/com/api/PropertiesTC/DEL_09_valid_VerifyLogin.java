package com.api.PropertiesTC;

import java.util.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.testng.Assert.*;
import org.testng.annotations.*;


public class DEL_09_valid_VerifyLogin {
	private Response response;
	private RequestSpecification request;
	String userEmail = "funixExample3@gmail.com";
	
	@BeforeClass
	public void openRequest()
	{
		RestAssured.baseURI = "https://automationexercise.com/";
		RestAssured.basePath = "api/verifyLogin";
		
		Map<String, Object> userLogin = new HashMap<String, Object>();
		userLogin.put("email", userEmail);
		
		request = RestAssured.given()
				.contentType(ContentType.JSON)
				.body(userLogin);
		response = request.delete();
		
		System.out.println("Result of Response: \n" + response.body().jsonPath().prettyPrint());
	}
	
	@Test
	public void TC01_verifyStatusCode()
	{
		assertEquals(405, response.getStatusCode(), "valid StatusCode 405: FAILED");
	}
	
	@Test
	public void TC02_verifyResponseCode()
	{
		assertTrue(response.asString().contains("responseCode"), "valid ResponseCode field: FAILED");
		assertEquals(405, response.body().jsonPath().getInt("responseCode"), "valid ResponseCode value: FAILED");
	}
	
	@Test
	public void TC03_verifyMessage()
	{
		assertTrue(response.asString().contains("message"), "valid Message field: FAILED");
		String expectedMsg = "This request method is not supported.";
		assertEquals(response.body().jsonPath().getString("message"), expectedMsg, "valid Message value: NOT matching");
	}

}
