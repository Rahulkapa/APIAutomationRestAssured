package Actions;

import static org.testng.Assert.assertEquals;

import io.restassured.*;
import io.restassured.response.Response;

//import org.openqa.selenium.devtools.v116.fetch.model.AuthChallengeResponse.Response;

public class AssertActions {
	
	public void verifyStatuscode (Response response) {
		assertEquals(String.valueOf(response.getStatusCode()).startsWith("20"), "value of the status code is " + response.getStatusCode());
	}
    
	public void verifyResponseBody(String actual ,String expected ,String description) {
		assertEquals(actual, expected,description);
	}
	
	public void verifyResponseBody(float actual ,float expected ,String description) {
		assertEquals(actual, expected,description);
	}
	
	public void verifyResponseBody(int actual ,int expected ,String description) {
		assertEquals(actual, expected,description);
	}
	
	public void verifyResponseBody(double actual ,double expected ,String description) {
		assertEquals(actual, expected,description);
	}
	
	public void verifyResponseBody(boolean actual ,boolean expected ,String description) {
		assertEquals(actual, expected,description);
	}
	
}
