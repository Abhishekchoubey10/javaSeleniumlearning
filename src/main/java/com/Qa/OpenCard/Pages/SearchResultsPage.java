package com.Qa.OpenCard.Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Qa.OpenCard.Utils.Constants;
import com.Qa.OpenCard.Utils.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleutil;
	private By SearchHeader = By.cssSelector("div#content h1");
	private By products = By.cssSelector("div.caption a");
	
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}
	
	public String getResultPageHeaderValue() {
		if(eleutil.doIsDisplayed(SearchHeader)) {
			return eleutil.doElementGetText(SearchHeader);
		}
		return null;
	}
	
	public int getProductSeachCount() {
		return eleutil.waitForElementsToBeVisible(products, Constants.DEFAULT_TIME_OUT).size();
	}
	
	public List<String> getProductResultsList() {
		List<WebElement> productList = eleutil.waitForElementsToBeVisible(products, Constants.DEFAULT_TIME_OUT);
		List<String> productValList = new ArrayList<String>();
		for(WebElement e : productList) {
			String text = e.getText();
			productValList.add(text);
		}
		return productValList;
	}
	
	public ProductInfoPage selectProduct(String mainProductName) {
		System.out.println("main product name: "+mainProductName);
		List<WebElement> productList = eleutil.waitForElementsToBeVisible(products, Constants.DEFAULT_TIME_OUT);
		for(WebElement e: productList) {
			String text = e.getText();
			if(text.equalsIgnoreCase(mainProductName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}
}
