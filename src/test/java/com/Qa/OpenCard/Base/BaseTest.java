package com.Qa.OpenCard.Base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.Qa.OpenCard.Factory.DriverFactory;
import com.Qa.OpenCard.Pages.AccountsPage;
import com.Qa.OpenCard.Pages.LoginPage;
import com.Qa.OpenCard.Pages.ProductInfoPage;
import com.Qa.OpenCard.Pages.RegistrationPage;
import com.Qa.OpenCard.Pages.SearchResultsPage;

public class BaseTest {

	public DriverFactory df;
	public Properties prop;
	public WebDriver driver;
	public LoginPage loginpage;
	public AccountsPage accpage;
	public SearchResultsPage sresultpage;
	public ProductInfoPage productInfoPage;
	public RegistrationPage Registrationpage;

	public SoftAssert softAssert;

	/** 
	 * On Top of before test method we have to add testNG parameters that we have added on test runners files 
	 * 
	 */ 
	@Parameters({"browser"})
	
	@BeforeTest
	public void test(String browserName) {
		df = new DriverFactory();
		prop = df.insit_prop();
		
		
		if(browserName != null) {
			prop.setProperty("browser", browserName);
		}
		
		driver = df.insit_driver(prop);
		loginpage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
