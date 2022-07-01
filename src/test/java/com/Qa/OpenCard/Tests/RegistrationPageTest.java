package com.Qa.OpenCard.Tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Qa.OpenCard.Base.BaseTest;
import com.Qa.OpenCard.Utils.Constants;
import com.Qa.OpenCard.Utils.ExcelUtils;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void regPageSetup() {
		Registrationpage = loginpage.navigateToRegisteredLink();

	}

	public String getRandomEmail() {
		Random random = new Random(); // Random is class in java, that present inside a java util random package
		// neaxtIn() is method that generate any random number between a given number
		// like, if we pass 100, like nextIn(100), then it will generate any number
		// between 1 to 100 to users
		String email = "janautomation" + random.nextInt(1000) + "@gmail.com";
		return email;

	}

	@DataProvider
	public Object[][] getRegistrationData() {
		//to Provide a data for different user we have provide data in 2D Object array 
	return new Object[][] {
		{"Abhishek", "Choubey", "7895683723", "abhishek123", "yes"},
		{"Praksh", "Singh", "7895683743","Prakash123", "no"},
			{"Mohan", "Choubey", "7895683523", "Mohan123", "no"}
			
	};
	
	}

	// Data from excel sheet
//	 @DataProvider
//	 public Object[][] getRegistrationData() {
//	 Object regData [][]= ExcelUtils.getTestData(Constants.REGISTER_SHEET_NAME);
//	 return regData;
//	 }

	@Test(dataProvider = "getRegistrationData")
	public void userRegistrationTest(String firstname, String lastname, String Mobile, String password, String subscribe) {
		Assert.assertTrue(Registrationpage
				.accountRegistration(firstname, lastname, getRandomEmail(), Mobile, password, subscribe));
	}

}
