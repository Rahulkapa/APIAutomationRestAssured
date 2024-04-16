package CURD;
import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JacksonException;

import Base.TestBase;
import EndPoints.APIConstants;
import Payloads.Jackson.Booking;
import Payloads.Jackson.BookingResponse;
import Payloads.gson.BookingRespons;
import groovyjarjarantlr4.v4.parse.GrammarTreeVisitor.tokenSpec_return;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

public class TC_Integration extends TestBase {
	
	String token;
	String bookingID ;
	String bookingIDgson ;
	
	// Get a token
	// Create a booking
	// Update a booking with Token and booking ID - How to pass variable from one test to another
	   // 1. Auth - API key
	   // Cookie Based Auth side
	   // Outh 2.0  - Method how can you use
	// Delete
	
	
	
    // Step 1 : Get a Token === It is write in BaseClass because it is common to every testclass
	
	
	
	// Step 2 : Create a Booking
	
	@Test(groups ="P0")
	public void testCreateBooking() throws JacksonException {
		
	    token = getToken();
	    System.out.println(token);
	    
	   // assertThat(token).isNotNull().isEmpty();
	    
	    requestSpecification.basePath(APIConstants.CREATE_BOOKING);
		response = RestAssured.given().spec(requestSpecification)
				   .when().body(payloadManager.createPayload()).post();
	    validatableResponse = response.then().log().all();
	    // method 1 : get the booking Id by using JsonPath - if we want only value on payload then we use jsonpath
	    
	    jsonPath = JsonPath.from(response.asString());
	    validatableResponse.statusCode(200);
	    bookingID = jsonPath.getString("bookingid");
		System.out.println(bookingID);
	    
	    // But we want more than value needed than we use serialization and deserialization concept.
	    // object to string and string to object
	    // Method 2 : get a BookingRespons class
	    
	    BookingRespons bookingRespons = payloadManager.JsonToObject(response.asString());
	    bookingIDgson = bookingRespons.getBookingid().toString();
		System.out.println(bookingIDgson);
		
	    //assertThat(bookingID).isNotNull().isEmpty();
		
	}
	
	
	// Step 3 : Update the booking with Token and ID
		
		@Test(groups ="P0",dependsOnMethods = {"testCreateBooking"})
		public void testCreateAndUpdateBooking() throws JacksonException {
		  
		    requestSpecification.basePath(APIConstants.UPDATE_BOOKING +"/"+bookingID);
			response = RestAssured.given().spec(requestSpecification).cookie("token",token)
					   .when().body(payloadManager.updatePayload()).put();
			
			// if we required.
//			response = RestAssured.given().spec(requestSpecification).auth().oauth2(token)
//					   .when().body(payloadManager.updatePayload()).put();
//		    validatableResponse = response.then().log().all();
		    		    
		    Booking bookingResponse = payloadManager.JsonToObjectPut(response.asString());
			 
			
			assertThat(bookingResponse.getFirstname()).isEqualTo("purva").isNotNull();
			assertThat(bookingResponse.getLastname()).isEqualTo("Kapadnis").isNotNull();
			assertThat(bookingResponse.getTotalprice()).isEqualTo(2343).isNotNull();
		}
	
	
	// Step 4 : Delete the Booking
	@Test(groups ="P0", dependsOnMethods = {"testCreateAndUpdateBooking"})
	public void testDeleteCreateBooking() {
		
		requestSpecification.basePath(APIConstants.UPDATE_BOOKING +"/"+bookingID).cookie("token",token);
	    validatableResponse = RestAssured.given().spec(requestSpecification).auth().basic("admin","password123")
				   .when().delete().then().log().all();
	    
		validatableResponse.statusCode(201);
	    
		
	}

	// Step : Confirm to delete
	
	@Test(groups ="P0", dependsOnMethods = {"testDeleteCreateBooking"})
	public void testDeleteBookingByGet() {
		
		requestSpecification.basePath(APIConstants.UPDATE_BOOKING +"/"+bookingID);
		response = RestAssured.given().spec(requestSpecification).when().get();
	    validatableResponse = response.then().log().all();
	    
		validatableResponse.statusCode(404);
	    
		
	}

	
}
