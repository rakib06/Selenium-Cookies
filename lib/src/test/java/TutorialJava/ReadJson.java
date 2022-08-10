package TutorialJava;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import org.json.JSONException;
import org.json.JSONObject;


public class ReadJson {

	public static void main(String[] args) throws JSONException, IOException {
		String contents = new String((Files.readAllBytes(Paths.get("cookies.json"))));
		//JSONObject jsonFile = new JSONObject(contents);
		JSONObject jsonObject = new JSONObject(contents);
		//JSONObject variableList = jsonFile.getJSONObject(); // <-- use getJSONObject
		for (Iterator<String> it = jsonObject.keys(); it.hasNext();) {
	        String key = it.next();
	        
	        System.out.println(key+":"+ jsonObject.get(key));
	    }
	}

}
