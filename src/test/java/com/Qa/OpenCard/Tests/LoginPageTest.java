package com.Qa.OpenCard.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Qa.OpenCard.Base.BaseTest;
import com.Qa.OpenCard.Utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100 - Design login page for open cart application")
@Story("US 101 - desgin login page features")
//here Epic and Story annotations is from allure, where we have mention our epic and story information 
public class LoginPageTest extends BaseTest {

	// we can write these test, description and serverity annotation in any order it
	// does not matter, all written annotation are applied to below test, means we
	// can write description or servrity annotation before test annotation or after
	// test annoation, it does not matter
	@Test
	@Description("This test is related to login page title") // Description annotation is imported from allure listener,
																// where we have
	// to write a description
	@Severity(SeverityLevel.NORMAL) // Severity annotation is imported from allure listener and SeverityLevel is a
									// class in allure
	public void loginPageTitleTest() {
		// here we donot need to create a loginpage object to call a loginpage, because
		// loginpage object is already create under basetest
		String actualTitle = loginpage.getLoginPageTitle();
		System.out.println("page title is:" + actualTitle);
		Assert.assertEquals(actualTitle, Constants.LOGIN_PAGE_TITLE); // for expected value we have create a constants
																		// class in utils package that return expected
																		// result
	}

	@Test
	@Description("This test related to login page url")
	@Severity(SeverityLevel.CRITICAL)
	public void loginPageURlTest() {
		String actUrl = loginpage.getLoginPageUrl();
		System.out.println("Login page url: " + actUrl);
		Assert.assertTrue(actUrl.contains(Constants.LOGIN_PAGE_FRACTION_URL));

	}

	@Test
	@Description("This test is related to forgot password link test on login page")
	@Severity(SeverityLevel.CRITICAL)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginpage.isForwardPwdlinkExist());
	}

	@Test
	@Description("This test related to login test on login page")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
		//accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		accpage = loginpage.doLogin("abhishek10mar@gmail.com", "Abhishek@123");
		Assert.assertTrue(accpage.isAccountPageHeadeExist());
	}

	@Test
	@Description("This test register link available on login page")
	@Severity(SeverityLevel.CRITICAL)
	public void registerdLinkTest() {
		Assert.assertTrue(loginpage.isRegisteredLinkExist());
	}
}
