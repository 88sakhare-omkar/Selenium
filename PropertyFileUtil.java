package CommonUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtil {

	public String getDataFromPropertyFile(String Keyname) throws IOException {
		
		FileInputStream fis = new FileInputStream("src\\test\\resources\\LoginCredentials.properties");
		Properties p = new Properties();
		p.load(fis);
		String value = p.getProperty(Keyname);
		return value;
	}

}
