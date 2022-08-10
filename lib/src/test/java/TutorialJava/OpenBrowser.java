package TutorialJava;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OpenBrowser {
	
	public static String site = "https://demo.guru99.com/test/cookie/selenium_aut.php";
	public static String username = "admin";
	public static String pwd = "admin";
	public static String menu_xpath = "//*[@id='root']/section/header/ul/li[2]/a";
	public static String username_xpath = "//*[@name='username']";
	public static String pwd_xpath = "//*[@name='password']";
	public static String btn_xpath = "//*[@type='submit']";
	public static String file_name = "cookie_ep";
	

	public static void saveCookies(WebDriver driver) {
		
		// Get All available cookies
	    Set<Cookie> cookies = driver.manage().getCookies();
	    System.out.println(cookies);    
	    JSONObject jsonObject = new JSONObject();
	    
	    for (Cookie ck : cookies) {
	    	jsonObject.put(ck.getName(), ck.getValue());
	    }
	    try {
	         FileWriter file = new FileWriter("cookies2.json");
	         file.write(jsonObject.toJSONString());
	         file.close();
	      } catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      System.out.println("JSON file created: "+jsonObject);
	      
	      
		    
	}
	
	public static void loadCookies(WebDriver driver) throws IOException, JSONException {
		LoadJson.setCookies(driver, "cookies2.json");
		 
		//driver.manage().addCookie(new Cookie("test1", "cookie1"));
		//driver.manage().addCookie(new Cookie("test2", "cookie2"));
	}
	
	public static void main(String[] args) throws InterruptedException, IOException, JSONException {  
	
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();  
		driver.navigate().to(site);  
		driver.manage().window().maximize();  
		driver.findElement(By.xpath(username_xpath)).sendKeys(username);
		driver.findElement(By.xpath(pwd_xpath)).sendKeys(pwd);
		driver.findElement(By.xpath(btn_xpath)).click();
		Thread.sleep(5000);
		
		saveCookies(driver);
		driver.close();
		Thread.sleep(3000);
		WebDriver driver2 = new ChromeDriver();  
		driver2.navigate().to(site);  
		driver2.manage().window().maximize();  
		loadCookies(driver2);
		driver2.navigate().refresh();
		
 }  

}
