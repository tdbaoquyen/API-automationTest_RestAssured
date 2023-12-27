package com.api.PropertiesTC;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import java.util.*;


public class PUT_04_to_Brands_List {
	private Response response;
	private RequestSpecification request;
	String brand_name = "Uniqlo";
	int brand_id = 1;

	@BeforeClass
	public void openRequest()
	{
	//	thiết lập Endpoint và Body cho Request
		RestAssured.baseURI = "https://automationexercise.com/";
		RestAssured.basePath = "api/brandsList";
		
		Map<String, Object> brandList = new HashMap<String, Object>();
		brandList.put("id", brand_id);
		brandList.put("brand", brand_name);
		
	//	thực hiện PUT và xuất ra chi tiết Response
		request = RestAssured.given()
				.contentType(ContentType.JSON);
		response = request.put();
		
		System.out.println("==> Result of Response: \n" + response.body().jsonPath().prettyPrint());
	}
	
	@Test
	public void TC01_verifyStatusCode()
	{
		assertEquals(405, response.getStatusCode(), "valid StatusCode 405 Method NOT Allowed: FAILED");
	}
	
	@Test
	public void TC02_verifyResponseCode()
	{
		assertTrue(response.asString().contains("responseCode"), "valid Message field: FAILED");
		assertEquals(405, response.body().jsonPath().getInt("responseCode"));
	}
	
	@Test
	public void TC03_verifyMessageField()
	{
		assertTrue(response.asString().contains("message"), "valid Message field: FAILED");
		String expectedMsg = "This request method is not supported.";
		assertEquals(response.body().jsonPath().getString("message"), expectedMsg, "Message is NOT matching expected value");
	}
	
}
