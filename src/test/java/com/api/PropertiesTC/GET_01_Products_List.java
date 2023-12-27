package com.api.PropertiesTC;

import static org.testng.Assert.*;
import org.testng.annotations.*;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class GET_01_Products_List {
	private Response response;
	private RequestSpecification request;

	@BeforeClass
	public void openProductList() {
		// thiết lập Endpoint và Body cho Request
		RestAssured.baseURI = "https://automationexercise.com/";
		RestAssured.basePath =  "api/productsList";

		// Tạo và gửi đi Request
		request = given()
				.contentType(ContentType.JSON)
				.when();

		// Lấy về Response và xuất ra nội dung Response
		response = request.get();
		System.out.println("Result of Response: \n" + response.body().jsonPath().prettyPrint());
		System.out.println("\n==> Quantity of products: " + response.body().jsonPath().getList("products").size() + "\n");
	}

	@Test(priority = 0)
	public void TC01_verifyStatusCode() {
		assertEquals(200, response.getStatusCode(), "StatusCode FAILED: 200 OK");
	}

	@Test
	public void TC02_verifyProduct_field() {
		assertTrue(response.asString().contains("products"), "valid Product field: FAILED");
	}

	@Test
	public void TC03_verifyProduct_ID() {
		assertTrue(response.asString().contains("id"), "valid Product_ID field: FAILED");
	}

	@Test
	public void TC04_verifyProduct_name() {
		assertTrue(response.asString().contains("name"), "valid Product_name field: FAILED");
	}

	@Test
	public void TC05_verifyProduct_price() {
		assertTrue(response.asString().contains("price"), "valid Product_price field: FAILED");
	}

	@Test
	public void TC06_verifyProduct_brand() {
		assertTrue(response.asString().contains("brand"), "valid Product_brand field: FAILED");
	}

	@Test
	public void TC07_verifyProduct_category() {
		assertTrue(response.asString().contains("category"), "valid Product_category field: FAILED");
	}

}
