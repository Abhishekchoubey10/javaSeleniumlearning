package com.Qa.OpenCard.Factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.Qa.OpenCard.Utils.Browser;
import com.Qa.OpenCard.Utils.Errors;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager optionsmanager;
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>(); // it comming form java, it added after
																					// JDK 1.8
	//public static final Logger log = Logger.getLogger(DriverFactory.class);

	/**
	 * This method is used to initialize the webdriver on the basis of given browser
	 * name. This method will take care of local and remote execution
	 * 
	 * @param browserName
	 * @return
	 */

	public WebDriver insit_driver(Properties prop) {

		String browserName = "chrome";
		// prop.getProperty("browser").trim();

		System.out.println("Browser name is :" + browserName);
		// highlight = prop.getProperty("highlight").trim();
		optionsmanager = new OptionsManager(prop);// for headless and incognito mode we also have to pass this in
													// browser

		if (browserName.equalsIgnoreCase(Browser.CHROME_BROWSER_VALUE)) {// we can create one interfacefor broswer and
																			// call that value directly as data type in
																			// interface are static and final in nature
			WebDriverManager.chromedriver().setup();
			//System.setProperty(Browser.CHROME_DRIVER_BINARY_KEY, Browser.CHROME_DRIVER_PATH);
			// driver = new ChromeDriver(optionsmanager.getChromeOptions());
			tdriver.set(new ChromeDriver(optionsmanager.getChromeOptions()));
		} else if (browserName.equalsIgnoreCase(Browser.FIREFOX_BROWSER_VALUE)) {
			WebDriverManager.firefoxdriver().setup();
			//System.setProperty(Browser.GECKO_DRIVER_BINARY_KEY, Browser.GECKO_DRIVER_PATH);
			// driver = new FirefoxDriver(optionsmanager.getFirefoxOptions());
			tdriver.set(new FirefoxDriver(optionsmanager.getFirefoxOptions()));
		} else if (browserName.equalsIgnoreCase(Browser.SAFARI_BROWSER_VALUE)) {
			driver = new SafariDriver();
			tdriver.set(new SafariDriver());

		} else {
			System.out.println(Errors.BROWSER_NOT_FOUND_ERROR_MSG + browserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().fullscreen();
		getDriver().get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		// getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	/**
	 * This method is used to initialize the properties on the basis of given
	 * environment: QA/DEV/Stage/PROD
	 * 
	 * @return his returns prop
	 */
	public Properties insit_prop() {
		prop = new Properties();
		FileInputStream ip = null;

		// mvn clean install -Denv="qa" //where env is variable name, -D is use to
		// provide environment variable
		String envName = System.getProperty("env");
		System.out.println("Running code on environment:" + envName);

		if (envName == null) {
			System.out.println("No Environment is given, so script running in qa environment");
			try {
				ip = new FileInputStream(".src/test/resources/config/qa.config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				switch (envName.toLowerCase()) {
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;

				default:
					System.out.println("Please pass a correct environment.." + envName);
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				prop.load(ip);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return prop;
	}

	/**
	 * This will return the thread local copy of WebDriver(driver)
	 * 
	 */
	public static WebDriver getDriver() {
		return tdriver.get();
	}

	/**
	 * Take screenshot
	 * 
	 * @return
	 */
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE); // here TakesScreenshot is
																							// interface and
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		// user dir means go to current project directory, here current project
		// directory is Jan2022POMSeries and getScreenshoyAs is method in selenium
		// here screenshot is folder name where images are store in png format
		File Destination = new File(path); // path is string and srcfile is file, so we have to create a file object, so
											// we store that screenshot in file format
		try {
			FileUtils.copyFile(srcFile, Destination);//
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

}
