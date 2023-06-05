package test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestExamples_REQRES {

	@Test
	public void test01()
	{
		Response response = get("https://reqres.in/api/users?page=2");
		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		System.out.println(response.getStatusLine());
		System.out.println(response.getHeader("content-type"));
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	public void test02()
	{
		baseURI = "https://reqres.in/api/";
		given()
			.get("/users?page=2")
		.then()
			.statusCode(200)
			.body("data.id[1]", equalTo(8))
			.log().all();
	}
	
	@Test
	public void test03_GET()
	{
		baseURI = "https://reqres.in/api/";
		given()
			.get("/users?page=2")
		.then()
			.statusCode(200)
			.body("data[2].first_name", equalTo("Tobias"))
			.body("data.first_name", hasItems("Tobias", "Michael", "Lindsay"))
			.log().all();
	}
	
	@Test
	public void test04_POST()
	{
		//Using Hashmap
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "Alexis");
		map.put("job", "QA");
		System.out.println(map);
		
		JSONObject request = new JSONObject(map);
		System.out.println(request);
		
		baseURI = "https://reqres.in/api/";
		given()
			.body(request.toJSONString())
		.when()
			.post("/users")
		.then()
			.statusCode(201)
			.log().all();
	}
	
	@Test
	public void test05_POST()
	{
		//Using JSONObject directly
		JSONObject request = new JSONObject();
		request.put("name", "John");
		request.put("job", "teacher");
		
		System.out.println(request);
		
		baseURI = "https://reqres.in/api/";
		given()
			.header("Content-type", "application/json")
			.contentType(ContentType.JSON).accept(ContentType.JSON)
			.body(request.toJSONString())
		.when()
			.post("/users")
		.then()
			.statusCode(201)
			.log().all();
	}
	
	@Test
	public void test06_PUT()
	{
		//Using JSONObject directly
		JSONObject request = new JSONObject();
		request.put("name", "John");
		request.put("job", "student");

		System.out.println(request);

		baseURI = "https://reqres.in/api/";
		given()
			.header("Content-type", "application/json")
			.contentType(ContentType.JSON).accept(ContentType.JSON)
			.body(request.toJSONString())
		.when()
			.put("/users/415")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test
	public void test06_PATCH()
	{
		// Using JSONObject directly
		JSONObject request = new JSONObject();
		request.put("name", "John");
		request.put("job", "Rambo");

		System.out.println(request);

		baseURI = "https://reqres.in/api/";
		given()
			.header("Content-type", "application/json")
			.contentType(ContentType.JSON).accept(ContentType.JSON)
			.body(request.toJSONString())
		.when()
			.patch("/users/637")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test
	public void test06_DELETE()
	{
		baseURI = "https://reqres.in/api/";
		when()
			.delete("/users/2")
		.then()
			.statusCode(204)
			.log().all();
	}
}
