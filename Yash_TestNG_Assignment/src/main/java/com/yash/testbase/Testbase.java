package com.yash.testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.yash.testutil.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Testbase {
	
	public static WebDriver driver;
	public static String browserName;
	public static File file;
	public static FileInputStream fs;
	public static Properties prop;
	public static String UserName,UserRole,EmployeeName,Status,ExpectedResult,ActualResult;
	
	
	public Testbase() throws IOException {
		file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\yash\\properties\\config.properties");
	    fs= new FileInputStream(file);
		prop= new Properties();
		prop.load(fs);
	}
public void launchBrowser() {
	browserName=prop.getProperty("browser");
	switch(browserName) {
	    case "firefox":
		    WebDriverManager.firefoxdriver().setup();
		    driver = new FirefoxDriver();
		    break;
	    case "chrome":
	    	WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
	    case "ie":
	    	WebDriverManager.iedriver().setup();
			driver= new InternetExplorerDriver();
			break;
	    case "edge":
	    	WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
			break;
	    default:
	    	System.out.println("Selenium tool dont support this browser ==> "+ browserName);
	    	break;
	}
	driver.get(prop.getProperty("url"));
	driver.manage().window().maximize();//optional
//	driver.manage().deleteAllCookies();//optional
	driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICITWAITTIMEOUT, TimeUnit.SECONDS);
	driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGELOADTIMEOUT, TimeUnit.SECONDS);
}
public void login() throws InterruptedException {
	driver.findElement(By.xpath(prop.getProperty("Username_editbox"))).clear();
	driver.findElement(By.xpath(prop.getProperty("Username_editbox"))).sendKeys("Admin");
	driver.findElement(By.xpath(prop.getProperty("Password_editbox"))).clear();
	driver.findElement(By.xpath(prop.getProperty("Password_editbox"))).sendKeys("admin123");
	Thread.sleep(2000);
	driver.findElement(By.xpath(prop.getProperty("Login_button"))).click();
	Thread.sleep(2000);
}
}
