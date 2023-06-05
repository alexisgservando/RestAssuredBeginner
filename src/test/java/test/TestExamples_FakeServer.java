package test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;


public class TestExamples_FakeServer {

	@Test
	public void test00_GET()
	{
		given()
		
		.when()
			.get("http://localhost:3000/users")
		.then()
			.statusCode(200)
			.log().all();
	}
	@Test
	public void test01_GET()
	{
		baseURI = "http://localhost:3000/";
		
		given()
			.get("/users/1")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test
	public void test02_POST()
	{
		baseURI = "http://localhost:3000/";
		
		//Using JSONObject directly
		JSONObject request = new JSONObject();
		request.put("name", "John");
		request.put("job", "teacher");
		request.put("subjectId", "1");
		
		Response response = 
		given()
			.header("Content-type", "application-json")
			.contentType(ContentType.JSON).accept(ContentType.JSON)	//.contentType("aplication/json")
			.body(request.toJSONString())
		.when()
			.post("http://localhost:3000/users")
		.then()
			.statusCode(201)
			.log().body()
			.extract().response();
		
		String jsonString = response.asString();
		Assert.assertEquals(jsonString.contains("teacher"), true);
	}
	
	@Test
	public void test03_PUT()
	{
		baseURI = "http://localhost:3000/";
		
		//Using JSONObject directly
		JSONObject request = new JSONObject();
		request.put("name", "Jack");
		request.put("job", "Bauer");
		request.put("subjectId", "2");
		
		given()
			.header("Content-type", "application-json")
			.contentType(ContentType.JSON).accept(ContentType.JSON)
			.body(request.toJSONString())
		.when()
			.put("http://localhost:3000/users/4")
		.then()
			.statusCode(200)
			.body("name", equalTo("Jack"))
			.body("job", equalTo("Bauer"))
			.body("subjectId", equalTo("2"))
			.log().body();
	}
	
	@Test
	public void test04_PATCH()
	{
		baseURI = "http://localhost:3000/";
		
		//Using JSONObject directly
		JSONObject request = new JSONObject();
		//request.put("name", "John");
		//request.put("job", "Rambo");
		request.put("subjectId", "1");
		
		given()
			.header("Content-type", "application-json")
			.contentType(ContentType.JSON).accept(ContentType.JSON)
			.body(request.toJSONString())
		.when()
			.put("http://localhost:3000/users/4")
		.then()
			.statusCode(200)
			.body("subjectId", equalTo("1"))
			.log().all();
	}
	
	@Test
	public void test05_DELETE()
	{
		baseURI = "http://localhost:3000/";
		
		when()
			.delete("/users/6")
		.then()
			.statusCode(200)
			.log().all();
	}
}
