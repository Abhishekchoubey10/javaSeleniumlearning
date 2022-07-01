package com.Qa.OpenCard.Pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Qa.OpenCard.Utils.Constants;
import com.Qa.OpenCard.Utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleutil;
	
	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("div#content img");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By qty = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private By successMessg = By.cssSelector("div.alert.alert-success.alert-dismissible");
	
	private Map<String, String> ProductInfoMap;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}
	
	public String getProductHeaderText() {
		 return eleutil.doElementGetText(productHeader).trim();
		
	}
	
	public int getProductImageCount() {
		return eleutil.waitForElementsToBeVisible(productImages, Constants.DEFAULT_TIME_OUT).size();
		
	}
	
	public Map<String, String> getProductInfo() {
		ProductInfoMap = new LinkedHashMap<String, String>();
		ProductInfoMap.put("productname", getProductHeaderText());
		ProductInfoMap.put("productimagecount", String.valueOf(getProductImageCount()));
		getProductMetaData();
		getProductPriceData();
		 return ProductInfoMap;
	}
	
	private void getProductMetaData() {
		List<WebElement> metaDataList = eleutil.getElements(productMetaData);
		Map<String, String> ProductInfoMap = new HashMap<String, String>();
//		Brand: Apple
//		Product Code: Product 18
//		Reward Points: 800
//		Availability: Out Of Stock
		for(WebElement e: metaDataList ) {
			String text = e.getText().trim();
			String meta[] = text.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			ProductInfoMap.put(metaKey, metaValue);
		}
	}
	
	private void getProductPriceData() {
		List<WebElement> metaPriceList = eleutil.getElements(productPriceData);
		Map<String, String> ProductInfoMap = new HashMap<String, String>();

		//$2,000.00
		//Ex Tax: $2,000.00
	
		String price= metaPriceList.get(0).getText().trim();
		String extprice = metaPriceList.get(1).getText().trim();
		ProductInfoMap.put("price", price);
		ProductInfoMap.put("extraprice", extprice);
	}
}
