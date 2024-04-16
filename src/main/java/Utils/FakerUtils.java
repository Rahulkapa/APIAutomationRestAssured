package Utils;

import com.github.javafaker.Faker;

public class FakerUtils {
	
	static Faker faker;
	
	public static String getUserName() {
		   faker= new Faker();
	String name = faker.name().firstName();
	return name ;
	}
	
	
	public static String getLastName() {
		   faker= new Faker();
	String name = faker.name().lastName();
	return name ;
	}
	
	

}
