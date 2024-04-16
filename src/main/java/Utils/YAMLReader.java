package Utils;

import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class YAMLReader {
	
	public void readKey() {
		Yaml yaml = new Yaml();
		InputStream inputStream = this.getClass()
				.getClassLoader()
				.getResourceAsStream("C:\\Users\\Admin\\eclipse-workspace\\APIAutomationRestAssured\\src\\main\\java\\Resources\\TestDataYAML");
		Map<String , Object> obj = yaml.load(inputStream);
		System.out.println(obj);
	}
}
