package com.Qa.OpenCard.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Qa.OpenCard.Utils.Constants;
import com.Qa.OpenCard.Utils.ElementUtil;
import com.Qa.OpenCard.Utils.Errors;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	// we are adding ElementUtils to eliminate a driver.findElements or findElement,
	// because we are added driver and many utils in ElementUtil

	// Login page should have own private webDriver
	// We always have to maintain private by locators on all page, because we have
	// to achieve encapsulation
	// according to the page object model, it says that locator should be design in
	// the form of encapsulation

	// 1. private by locators:
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@type='submit']");
	private By forgotPwd = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	private By logout = By.linkText("Logout");
	private By LoginErrorMsg = By.cssSelector("div.alert.alert-danger.alert-dismissible");

	// 2. Public page count
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	// 3. public page actions
	@Step("getting login page title---") // Step is a annotation from allure listener, where we have write description
											// in plain english about current method
	// Donot be confuse between Step and description annotation in allure, step is
	// used in page class actions, where description is used for test class
	public String getLoginPageTitle() {
		// return driver.getTitle();
		return eleutil.waitForTitleIs(Constants.DEFAULT_TIME_OUT, Constants.LOGIN_PAGE_TITLE);
	}

	@Step("getting login page url---")
	public String getLoginPageUrl() {
		// return driver.getCurrentUrl();
		return eleutil.waitForUrl(Constants.DEFAULT_TIME_OUT, Constants.LOGIN_PAGE_FRACTION_URL);
	}

	@Step("Checking is forgot password link available on login page---")
	public boolean isForwardPwdlinkExist() {
		// return driver.findElement(forgotPwd).isDisplayed();
		return eleutil.doIsDisplayed(forgotPwd);
	}

	// we have to get return from this method
	// where we have to return next landing page class according to the page object
	// modal
	// In this case we have return account page, so not this method responsibility
	// to return a object of next landing page
	// Remember-- Whenever we are clicking on something and we are landing on the
	// next page then its method responsibility to return a next landing page class
	// object
	// return type should be class name
	@Step("Checking login functionality with username {0} and password {1}") // here 0 in curly bracket({), define first
																				// parameter and 1 define second
																				// parameter
	public AccountsPage doLogin(String un, String pwd) {
		// driver.findElement(emailId).sendKeys(un);
		// driver.findElement(password).sendKeys(pwd);
		// driver.findElement(loginBtn).click();
		eleutil.waitForElementToBeVisible(emailId, Constants.DEFAULT_TIME_OUT).sendKeys(un);
		eleutil.doSendKeys(password, pwd);
		eleutil.doclick(loginBtn);
		return new AccountsPage(driver); // where this constructor need driver
	}
	
	@Step("Checking login functionality with incorrect username{0} and password{1}")
	public boolean doInvaildLogin(String un, String pwd) {
		WebElement email_ele = eleutil.waitForElementToBeVisible(emailId, Constants.DEFAULT_TIME_OUT);
		email_ele.clear();
		email_ele.sendKeys(un);
		eleutil.doSendKeys(password, pwd);
		eleutil.doclick(loginBtn);
		String actualErrorMsg = eleutil.doElementGetText(LoginErrorMsg);
		System.out.println(actualErrorMsg);
		if(actualErrorMsg.contains(Errors.LOGIN_PAGE_ERROR_MSG)) {
			return true;
		}
		return false;
	}

	@Step("Checking is register link available on login page")
	public boolean isRegisteredLinkExist() {
		eleutil.doclick(logout);
		// return driver.findElement(registerLink).isDisplayed();
		return eleutil.waitForElementToBeVisible(registerLink, Constants.DEFAULT_TIME_OUT).isDisplayed();
	}

	public RegistrationPage navigateToRegisteredLink() {
		if (isRegisteredLinkExist()) {
			// driver.findElement(registerLink).click();
			eleutil.waitForElementToBeVisible(registerLink, 10).click();
			return new RegistrationPage(driver);
		}
		return null;
	}
}
