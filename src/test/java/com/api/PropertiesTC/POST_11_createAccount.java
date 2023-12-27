package com.api.PropertiesTC;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;
import static org.testng.Assert.*;


public class POST_11_createAccount {
	private Response response;
	private RequestSpecification request;
	
	@BeforeClass
	public void openRequest()
	{
		RestAssured.baseURI = "https://automationexercise.com/";
		RestAssured.basePath = "api/createAccount";
		
		request = RestAssured.given().contentType("multipart/form-data")
				.multiPart("name", "quyentest01")
				.multiPart("email", "quyentest01@gmail.com")
				.multiPart("password", "quyenTest01")
				.multiPart("title", "Miss")
				.multiPart("birth_date", "01")
				.multiPart("birth_month", "01")
				.multiPart("birth_year", "1991")
				.multiPart("firstname", "quyen")
				.multiPart("lastname", "tester")
				.multiPart("company", "non-company")
				.multiPart("address1", "HCM001")
				.multiPart("address2", "HCM002")
				.multiPart("country", "VietNam")
				.multiPart("zipcode", "70000")
				.multiPart("state", "BinhThanh")
				.multiPart("city", "TPHCM")
				.multiPart("mobile_number", "0123456789");
		
		response = request.post();
		System.out.println("Result of Response: \n" + response.body().jsonPath().prettyPrint());
	}
	
	@Test
	public void TC01_verifyStatusCode()
	{
		assertEquals(201, response.getStatusCode(), "valid StatusCode 201 Created: FAILED");
	}
	
	@Test
	public void TC02_verifyResponseCode()
	{
		assertTrue(response.asString().contains("responseCode"), "valid ResponseCode field: FAILED");
		assertEquals(201, response.body().jsonPath().getInt("responseCode"), "valid ResponseCode value: FAILED");
	}
	
	@Test
	public void TC03_verifyMessage()
	{
		assertTrue(response.asString().contains("message"), "valid Message field: FAILED");
		String expectedMsg = "User created!";
		assertEquals(response.body().jsonPath().getString("message"), expectedMsg, "valid Message value: NOT matching");
	}

}
