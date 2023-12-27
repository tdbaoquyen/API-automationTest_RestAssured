package com.api.PropertiesTC;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;
import static org.testng.Assert.*;

public class GET_14_detailAccount {
	private Response response;
	private RequestSpecification request;
	String userEmail = "quyentest01@gmail.com";
	
	@BeforeClass
	public void openRequest()
	{
		RestAssured.baseURI = "https://automationexercise.com/";
		RestAssured.basePath = "api/getUserDetailByEmail";
		
		
		request = RestAssured.given()
				.contentType(ContentType.JSON)
				.queryParam("email", userEmail);
		
		response = request.get();
		System.out.println("Rest of Response: " + response.body().jsonPath().prettyPrint());
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
	public void TC03_matchingNameField()
	{
		String updateValue = "test01QUYEN";
		assertTrue(response.asString().contains("name"), "valid Account_Name field: FAILED");
		assertEquals(updateValue, response.body().jsonPath().getString("user.name"), "valid Account_Name: NOT matching");
	}
	
	@Test
	public void TC04_matchingPassField()
	{
		assertTrue(response.asString().contains("country"), "valid Account_country field: FAILED");
		assertEquals("VietNam", response.body().jsonPath().getString("user.country"), "valid Account_country: NOT matching");
	}
	
	@Test
	public void TC05_matchingEmailField()
	{
		assertTrue(response.asString().contains("email"), "valid Account_Email field: FAILED");
		assertEquals(userEmail, response.body().jsonPath().getString("user.email"), "valid Email: NOT matching");
	}
	
	@Test
	public void TC06_verifyMainInfo()
	{
		assertEquals("quyen", response.body().jsonPath().getString("user.first_name"));
		assertEquals("tester", response.body().jsonPath().getString("user.last_name"));
		assertEquals("BinhThanh", response.body().jsonPath().getString("user.state"));
		assertEquals("TPHCM", response.body().jsonPath().getString("user.city"));
	}

}
