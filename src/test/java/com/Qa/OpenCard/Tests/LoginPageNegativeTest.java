package com.Qa.OpenCard.Tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Qa.OpenCard.Base.BaseTest;
import com.Qa.OpenCard.Utils.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100 - Design login page for open cart application")
@Story("US 101 - desgin login page features")
public class LoginPageNegativeTest extends BaseTest {

	@DataProvider
	public Object[][] getLoginNegativeData() {
		return new Object[][] { { "abhishek10mar@gmail.com", "12334567" }, { "sohan@gamil.com", "Abhishek@123" },
				{ "gupta@gmail.com", "93443nhd" }, { "   ", "   " }, { "prakash@gmail.@com", "8390992" },
				{ "   ", "929838u8" }

		};
	}

	@Test(dataProvider = "getLoginNegativeData")
	@Description("This test is related to do login test on login page with incorrect crediencials")
	@Severity(SeverityLevel.NORMAL)
	public void negativeLoginTest(String userName, String password) {
		loginpage.doInvaildLogin(userName, password);
		Assert.assertTrue(loginpage.doInvaildLogin(userName, password), Errors.LOGIN_PAGE_MESSAGE_NOT_DISPLAYED);

	}

//	@Test
//	public void test1() {
//		byte a = 10;
//		byte b = 30;
//		int sum = a + b;
//		Assert.assertEquals(sum, 50, "---This not a correct sum---"); //along with true and false,
	//if we want to add own error message,but error message only display when assertion got falied 
//	}
}
