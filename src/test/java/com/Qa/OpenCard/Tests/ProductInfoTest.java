package com.Qa.OpenCard.Tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Qa.OpenCard.Base.BaseTest;
import com.Qa.OpenCard.Utils.Constants;

public class ProductInfoTest extends BaseTest{

	//there is 4 level of pagechaning
	
	@BeforeClass
	public void productInfoSetup() {
		accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@DataProvider
	public Object [][] getProductData1() {
		return new Object [][] {
			{"MacBook", "MacBook Pro"},
			{"MacBook", "MacBook Air"},
			{"Apple", "Apple Cinema 30\""}// escaping a character is use there 
		};
	}
	
	@Test(dataProvider = "getProductData1")
	public void productInfoHeaderTest(String category, String product) {
		sresultpage = accpage.doSearch(category);
		productInfoPage = sresultpage.selectProduct(product);
		Assert.assertEquals(productInfoPage.getProductHeaderText(), product);
		
	}
	
	@DataProvider
	public Object [][] getProductData2() {
		return new Object [][] {
			{"MacBook", "MacBook Pro", 4},
			{"MacBook", "MacBook Air", 4},
			{"Apple", "Apple Cinema 30\"", 4}// escaping a character is use there 
		};
	}
	@Test(dataProvider = "getProductData2")
	public void productImagesTest(String category, String product, int i) {
		sresultpage = accpage.doSearch(category);
		productInfoPage = sresultpage.selectProduct(product);
		Assert.assertTrue(productInfoPage.getProductImageCount() == i);
		
	}
	
	@Test
	public void productInfoTest() {
		sresultpage = accpage.doSearch("MacBook");
		productInfoPage = sresultpage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();
		actProductInfoMap.forEach((k,v) -> System.out.println(k + ":" +v));
		
		softAssert.assertEquals(actProductInfoMap.get("productname"), "MacBook");
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Reward Points"), "600");
		softAssert.assertEquals(actProductInfoMap.get("price"), "$602.00");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 16");
		softAssert.assertAll();// it define how many of this assert got pass in this particular test 
	}
}
