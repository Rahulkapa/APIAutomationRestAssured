package ReadExcel;

import org.testng.annotations.Test;

public class LoginwithDDT {

	@Test(dataProvider ="getDat",dataProviderClass=Excel.class)
	
	public void testlogindata(String username,String password) {
		System.out.println("username" + username);
		System.out.println("password" + password);
	}
	
	
	
	
	
}
