package com.api.PropertiesTC;

import static org.testng.Assert.*;
import java.util.*;
import org.testng.annotations.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class POST_02_to_Product_List {
	private Response response;
	private RequestSpecification request;
	
//	khởi tạo giá trị Product mới
	String product_ID = "001";
	String product_name = "Sweeter";
	String product_price = "Rs. 800";
	String product_brand = "Uniqlo";
	String product_category = "Woman";
	
	@BeforeClass
	public void openRequest()
	{
	//	thiết lập Endpoint và Body cho Request
		RestAssured.baseURI = "https://automationexercise.com/";
		RestAssured.basePath =  "api/productsList";
		
		Map<String, Object> productList = new HashMap<String, Object>();
		productList.put("id", product_ID);
		productList.put("name", product_name);
		productList.put("price", product_price);
		productList.put("brand", product_brand);
		productList.put("category", product_category);
		
	//	thiết lập Request
		request = RestAssured.given()
				.contentType(ContentType.JSON)
				.when()
				.body(productList);
		
	//	thực hiện POST và xuất ra nội dung Response
		response = request.post();
		System.out.println("Result of Response: \n" + response.body().jsonPath().prettyPrint());
	}
	
	@Test (priority = 1)
	public void TC01_verifyStatusCode()
	{
		assertEquals(405, response.getStatusCode(), "valid StatusCode 405 Method Not Allowed: FAILED!");
	}
	
	@Test (priority = 2)
	public void TC02_verifyResponseCode()
	{
		assertTrue(response.asString().contains("responseCode"), "valid ResponseCode fiel: FAILED");
		assertEquals(405, response.body().jsonPath().getInt("responseCode"), "valid responseCode value: FAILED");
	}
	
	@Test (priority = 3)
	public void TC03_verifyMessage()
	{
		assertTrue(response.asString().contains("message"), "valid Message field: FAILED");
		String expectedMsg = "This request method is not supported.";
		assertEquals(expectedMsg, response.body().jsonPath().getString("message"), "Message is NOT matching expected value");
	}

}
