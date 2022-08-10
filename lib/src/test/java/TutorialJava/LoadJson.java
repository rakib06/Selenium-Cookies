package TutorialJava;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class LoadJson {
	public static void setCookies(WebDriver driver, String filePath) throws IOException, JSONException {
		String contents = new String((Files.readAllBytes(Paths.get(filePath))));
		//JSONObject jsonFile = new JSONObject(contents);
		JSONObject jsonObject = new JSONObject(contents);
		//JSONObject variableList = jsonFile.getJSONObject(); // <-- use getJSONObject
		for (Iterator<String> it = jsonObject.keys(); it.hasNext();) {
	        String key = it.next();
	        driver.manage().addCookie(new Cookie(key,  (String) jsonObject.get(key)));
	        //System.out.println(key+":"+ jsonObject.get(key));
	    }
	}
}
