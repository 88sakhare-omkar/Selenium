package CommonUtils;

import java.util.Random;

public class JavaUtil {

	public int getRandomNumber() {
		
		Random r = new Random();
		int ran = r.nextInt(10);
		return ran;
	}

}
