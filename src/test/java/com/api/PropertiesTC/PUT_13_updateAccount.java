package com.api.PropertiesTC;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;
import static org.testng.Assert.*;


public class PUT_13_updateAccount {
	private Response response;
	private RequestSpecification request;
	
	@BeforeClass
	public void openRequest()
	{
		RestAssured.baseURI = "https://automationexercise.com/";
		RestAssured.basePath = "api/updateAccount";

		request = RestAssured.given().contentType("multipart/form-data")
				.multiPart("name", "test01QUYEN")
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
				.multiPart("mobile_number", "0909900099");

		response = request.put();
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
		assertEquals("User updated!", response.body().jsonPath().getString("message"), "valid Message value: NOT matching");
	}
	
}
