package com.Qa.OpenCard.Tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Qa.OpenCard.Base.BaseTest;
import com.Qa.OpenCard.Utils.Constants;

public class AccountPageTest extends BaseTest {

	// to Setup own login for account page test
	// if we call doLogin method from account page, then this will give us a account
	// page class object, so we have to store this under account page class
	// reference
	// but reference we have maintain in the BaseTest class, because we have call
	// this accountpage reference in our other test method in thic class
	@BeforeTest
	public void accPageSetup() {
		accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void accountsPageTitleTest() {
		String actAccountPageTitle = accpage.getAccountsPageTitle();
		System.out.println("Account page title is: " +actAccountPageTitle);
		Assert.assertEquals(actAccountPageTitle, Constants.ACCOUNTS_PAGE_TITLE );

	}
	
	@Test
	public void accountsPageHeaderTest() {
		Assert.assertTrue(accpage.isAccountPageHeadeExist());
	}
	
	@Test
	public void searchExistTest() {
		Assert.assertTrue(accpage.isSearchExist());
	}
	
	@Test
	public void accountSecListTest() {
		List<String> actSecList = accpage.grtAccountPageActionList();
		System.out.println("Accounts sections lists: "+actSecList);
		Assert.assertEquals(actSecList, Constants.ACCOUNTS_SECTION_LIST);
	}
	
	@Test
	public void searchTest() {
		sresultpage = accpage.doSearch("Macbook");
		String actHeaderValue = sresultpage.getResultPageHeaderValue();
		Assert.assertEquals(actHeaderValue, "+Macbook");
	}
	
	@Test
	public void searchProductPageCountTest() {
		sresultpage = accpage.doSearch("macbook");
		int actSearchProductCount = sresultpage.getProductSeachCount();
		Assert.assertEquals(actSearchProductCount, Constants.MACBOOK_PRODUCT_COUNT);
	}
	
	@Test
	public void getSearchProductListTest() {
		sresultpage = accpage.doSearch("macbook");
		List<String> actProductList = sresultpage.getProductResultsList();
		System.out.println(actProductList);
	}
	
}