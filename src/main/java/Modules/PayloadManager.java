package Modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import Payloads.Jackson.Bookingdates;
import Payloads.gson.BookingAuthRequest;
import Payloads.gson.BookingRespons;
import Payloads.Jackson.Booking;
import Utils.FakerUtils;

public class PayloadManager {
	
	// Java ----- Json to that when give it to the .body()
	
	 ObjectMapper objectMapper;
	 BookingAuthRequest bookingAuthRequest;
	
	// Step 1 ---- CREATEPAYLOAD
	// Object To JSON
	 public String createPayload() throws JsonProcessingException {
		
		objectMapper = new ObjectMapper();
		
        Booking booking = new Booking();
	
		booking.setFirstname("chetan");
		booking.setLastname("Kapadnis");
		booking.setDepositpaid(true);
		booking.setTotalprice(2343);
		booking.setAdditionalneeds("Breakfast");
		
		Bookingdates bookingdates = new Bookingdates();
		bookingdates.setCheckin("2021-03-14");
		bookingdates.setCheckout("2024-03-15");
		
		booking.setBookingdates(bookingdates);
		
		String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
		
		return payload;
		
	}
	
    public String createPayload_negative() throws JsonProcessingException {
		
		objectMapper = new ObjectMapper();
		
        Booking booking = new Booking();
        booking.setFirstname("chetan");
		booking.setLastname("Kapadnis");
		booking.setDepositpaid(true);
		booking.setTotalprice(4000);
		booking.setAdditionalneeds("Dinner");
		
		Bookingdates bookingdates = new Bookingdates();
		bookingdates.setCheckin("2021-03-14");
		bookingdates.setCheckout("2024-03-15");
		
		booking.setBookingdates(bookingdates);
		
		
		
		String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
		
		return payload;
		
	}
	// JSON TO Object 
	
	public BookingRespons JsonToObject(String jsonString) throws JsonMappingException, JsonProcessingException {
	
	ObjectMapper objectMapper = new ObjectMapper();
	  
	BookingRespons bookingRespons = objectMapper.readValue(jsonString,BookingRespons.class);
	  
	return bookingRespons;
	  
	}
	  
	public Booking JsonToObjectPut(String jsonString) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		  
		Booking bookingResponse = objectMapper.readValue(jsonString,Booking.class);
		  
		return bookingResponse;
		  
		}
	
public String updatePayload() throws JsonProcessingException {
		
		objectMapper = new ObjectMapper();
		
        Booking booking = new Booking();
        booking.setFirstname("purva");
		booking.setLastname("Kapadnis");
		booking.setDepositpaid(true);
		booking.setTotalprice(2343);
		booking.setAdditionalneeds("Breakfast");
		
		Bookingdates bookingdates = new Bookingdates();
		bookingdates.setCheckin("2021-03-14");
		bookingdates.setCheckout("2024-03-15");
		
		booking.setBookingdates(bookingdates);
		
		String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
		
		return payload;
		
	}


public String setToken() throws JsonProcessingException {
	objectMapper = new ObjectMapper();
    bookingAuthRequest = new BookingAuthRequest();
	bookingAuthRequest.setUsername("admin");
	bookingAuthRequest.setPassword("password123");
	return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(bookingAuthRequest);
	
}
	

}
