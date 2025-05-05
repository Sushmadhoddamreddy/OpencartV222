package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups={"Sanity","Master"})
	public void verify_login()
	{
		logger.info("*******Starting TC002_LoginTest*****");
		try {
		
		//Home Page
		HomePage Hp = new HomePage(driver);
		Hp.clickMyAccount();
		Hp.clickLogin();
		
		// Login
		LoginPage Lp = new LoginPage(driver);
		Lp.setEmail(p.getProperty("email"));
		Lp.setPassword(p.getProperty("password"));
		Lp.clickLogin();
		
		//myaccount page
		
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists();
		
		Assert.assertEquals(targetPage,true,"Login failed");
		
		
		}
		catch(Exception e)
		{
			Assert.fail();
			
		}
		logger.info("******Finished  TC002_LoginTest*****");
	
	}
	
	
	
	

}
