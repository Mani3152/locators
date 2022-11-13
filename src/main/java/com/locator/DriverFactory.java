package com.growpartyweb.utils;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import org.openqa.selenium.support.PageFactory;

import com.growpartyweb.pageobjects.GPHomePage;
import com.growpartyweb.pageobjects.GPLoginPage;





public class DriverFactory{
	public static WebDriver driver;
	public static GPLoginPage gpLoginPage;
	public static GPHomePage gpHomePage;




	public void getURL() throws Exception {
		Properties p = new Properties();
		String path = System.getProperty("user.dir");
		FileInputStream fi = new FileInputStream(path+"//src//main//java//com//growpartyweb//utils//config.properties");
		p.load(fi);
		String urlName=p.getProperty("baseUrl");
		driver.get(urlName);

	}


	public void setPassword() throws Exception {
		Properties p = new Properties();
		String path = System.getProperty("user.dir");
		FileInputStream fi = new FileInputStream(path+"//src//main//java//com//growpartyweb//utils//config.properties");
		p.load(fi);
		String userPassword=p.getProperty("password");
		gpLoginPage.enterPassword(userPassword);

	}


	public WebDriver getDriver() {

		try {
			// Read Config
			String path = System.getProperty("user.dir");
			Properties p = new Properties();
			FileInputStream fi = new FileInputStream(path+"//src//main//java//com//growpartyweb//utils//config.properties");

			p.load(fi);
			String browserName = "chrome";
			switch (browserName) {

				case "firefox":
					// code
					if (null == driver) {
//					System.setProperty("webdriver.gecko.driver", Constant.GECKO_DRIVER_DIRECTORY);

						driver = new FirefoxDriver();
						driver.manage().window().maximize();
						//driver.get(urlName);


					}
					break;

				case "chrome":
					// code
					if (null == driver) {
//				System.setProperty("webdriver.chrome.driver", Constant.CHROME_DRIVER_DIRECTORY);
						WebDriverManager.chromedriver().setup();
						//	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Driver\\chromedriver.exe");

						// CHROME OPTIONS
						System.out.println(browserName);
						driver = new ChromeDriver();
						driver.manage().window().maximize();
						//driver.get(urlName);

					}
					break;

				case "edge":
					// code
					if (null == driver) {
						System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\edgedriver_win64\\msedgedriver.exe");
						// EDGE OPTIONS
						System.out.println(browserName);
						driver = new EdgeDriver();
						driver.manage().window().maximize();
						//	driver.get(urlName);

					}
					break;
			}
		} catch (Exception e) {
			System.out.println("Unable to load browser: " + e.getMessage());
		} finally {
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

			gpLoginPage = PageFactory.initElements(driver, GPLoginPage.class);
			gpHomePage = PageFactory.initElements(driver, GPHomePage.class);

		}


		return driver;

	}
}
