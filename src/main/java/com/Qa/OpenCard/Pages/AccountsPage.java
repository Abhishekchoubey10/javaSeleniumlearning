package com.Qa.OpenCard.Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Qa.OpenCard.Utils.Constants;
import com.Qa.OpenCard.Utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleutil;
	
	private By search = By.name("search");
	private By searchBtn = By.cssSelector("div#search button");
	private By header = By.cssSelector("div#logo a");
	private By accSecList = By.cssSelector("div#content h2");
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}
	public String getAccountsPageTitle() {
		//return driver.getTitle();
		return eleutil.waitForTitleIs(Constants.DEFAULT_TIME_OUT, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	public boolean isAccountPageHeadeExist() {
		//return driver.findElement(header).isDisplayed();
		return eleutil.doIsDisplayed(header);
	}
	
	public boolean isSearchExist() {
		//return driver.findElement(search).isDisplayed();
		return eleutil.doIsDisplayed(search);
	}
	public SearchResultsPage doSearch(String productname) {
		if(isSearchExist()) {
			eleutil.doSendKeys(search, productname);
			eleutil.doclick(searchBtn);
			return new SearchResultsPage(driver);
		}
		return null;
	}
	
	public List<String> grtAccountPageActionList() {
		//List<WebElement> secList = driver.findElements(accSecList);
		List<WebElement> secList = eleutil.getElements(accSecList);
		List<String> accSecValList = new ArrayList<String>();
		for(WebElement e: secList) {
			String text = e.getText();
			accSecValList.add(text);
		}
		return accSecValList;
	}
}
