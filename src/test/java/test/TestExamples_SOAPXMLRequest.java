package test;

import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.apache.commons.io.IOUtils;


public class TestExamples_SOAPXMLRequest {

	@Test
	public void test01() throws IOException
	{
		File file = new File("./SoapRequest/Add.xml"); 					//CREATE AN XML FILE AND SAVE IT ANYWHERE IN YOUR PC -> CREATE FOLDER IN THE PROJECT -> COPY XML FILE TO THE NEW FOLDER
		if(file.exists())
			System.out.println(" >> XML FILE EXISTS ------ ");
		
		FileInputStream fileInputStream = new FileInputStream(file);
		String requestBody = IOUtils.toString(fileInputStream, "UTF-8");
		
		baseURI = "http://dneonline.com";
		
		given()
			.contentType("text/xml")
			.accept(ContentType.XML)
			.body(requestBody)
		.when()
			.post("/calculator.asmx")
		.then()
			.statusCode(200)
			.log().all()
		.and()
			.body("//*:AddResult.text()", equalTo("30"));
	}
	
	public void method()
	{
		
	}
}
