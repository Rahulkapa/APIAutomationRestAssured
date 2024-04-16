package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class PropertiesReader {
	
	//Default constructor
	public PropertiesReader(){
		
	}
	
	public static String readyKey(String key) throws Exception  {
		
		FileInputStream fileInputStream = new FileInputStream(new File(System.getProperty("user.dir")+"C:\\Users\\Admin\\eclipse-workspace\\APIAutomationRestAssured\\src\\main\\java\\Resources\\TestDataProperties"));
		Properties p = new Properties();
		p.load(fileInputStream);
		
		if(p.getProperty(key) == null ) {
			throw new Exception("not able to find the key");
		}
		else {
			return p.getProperty(key);
		}

}
}