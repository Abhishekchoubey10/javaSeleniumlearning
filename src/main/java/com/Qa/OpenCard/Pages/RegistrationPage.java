package com.Qa.OpenCard.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.Qa.OpenCard.Utils.Constants;
import com.Qa.OpenCard.Utils.ElementUtil;

public class RegistrationPage {

	private WebDriver driver;
	private ElementUtil eleutil;
	
	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By Mobile = By.id("input-telephone");
	private By password = By.id("input-password");
	private By checkbox = By.xpath("//input[@type = 'checkbox']");
	private By submit = By.xpath("//button[@type = 'submit']");
	private By subscribeYes = By.xpath("//label[@class='radio-inline']/input[@type ='radio' and @value= 1 ]");
	private By subscribeNo = By.xpath("//label[@class='radio-inline']/input[@type ='radio' and @value= 0 ]");
	private By sucessMessg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}
	
	public boolean accountRegistration(String firstname, String lastname, String email, String Mobile, String password, String subscribe) {
		eleutil.waitForElementToBeVisible(this.firstname, Constants.DEFAULT_TIME_OUT).sendKeys(firstname);
		eleutil.doSendKeys(this.lastname, lastname);
		eleutil.doSendKeys(this.email, email);
		eleutil.doSendKeys(this.Mobile, Mobile);
		eleutil.doSendKeys(this.password, password);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eleutil.waitForElementToBeVisible(subscribeYes, 10).click();
		}else {
			eleutil.waitForElementToBeVisible(subscribeNo, 10).click();
		}
		eleutil.doclick(checkbox);
		eleutil.doclick(submit);
		
		if(getAccountRegisterSucessMsg().contains(Constants.ACCOUNT_REGISTER_SUCESSFULL)) {
			goTORegisterPage();
			return true;
		}
		return false;
		
	}
	
	private String getAccountRegisterSucessMsg() {
		return eleutil.waitForElementToBeVisible(sucessMessg, Constants.DEFAULT_TIME_OUT).getText();
	}
	
	private void goTORegisterPage() {
		eleutil.doclick(logoutLink);
		eleutil.doclick(registerLink);
	}
}
