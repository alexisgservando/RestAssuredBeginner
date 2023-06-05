package test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.testng.annotations.Test;

public class TestExamples_JSONSchema {
	@Test
	public void test07_JSONSchema()
	{
		baseURI = "https://reqres.in/api";

		given()
			.get("/users?page=2")
		.then()
			.assertThat().body(matchesJsonSchemaInClasspath("schema.json")) //Look for the schema.json file in the C:\Users\alexi\Documents\JavaProjects\RestAssuredBeginner\target\classes path
			.statusCode(200)
			.log().all();
	}
}
