package MISCtotestanythink;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class hello {
	
	@Test
	public void postreques() {
		
		String payload = "{\r\n"
				+ "    \"username\" : \"admin\",\r\n"
				+ "    \"password\" : \"password123\"\r\n"
				+ "}";
		
		// payload == String , hashmap , class (class is best)
		
		RestAssured
		           .given()
		           .baseUri("https://restful-booker.herokuapp.com")
		           .basePath("/auth")
		           .contentType(ContentType.JSON)
		           
		           .when().body(payload).post()
		           
		           .then().log().all().statusCode(200);
	}


	
	
}
