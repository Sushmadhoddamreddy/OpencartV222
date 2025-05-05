package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;


/* data is valid - login success -test pass- logout
                    Login unsucces - test fail 
 */

/* data is Invalid - login success -test fail- logout
                      Login unsucces - test pass
*/
public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="datadriven")//getting data provider from different class
	public void verify_loginDDT(String email,String pwd,String exp)
	{
		
		logger.info("*****Starting TC003_LoginDDT***" );
		//Home Page
		
		try
		{
			HomePage Hp = new HomePage(driver);
			Hp.clickMyAccount();
			Hp.clickLogin();
			
			// Login
			LoginPage Lp = new LoginPage(driver);
			Lp.setEmail(email);
			Lp.setPassword(pwd);
			Lp.clickLogin();
			
			//myaccount page
			
			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetPage = macc.isMyAccountPageExists();
			
			if(exp.equalsIgnoreCase("valid"))
			{
				if(targetPage==true)
				{
					macc.clickLogout();
					Assert.assertTrue(true);
					
				}
				else
				{
					Assert.assertTrue(false);
				}
			}
			
			if(exp.equalsIgnoreCase("Invalid"))
			{
				if(targetPage==true)
				{
					macc.clickLogout();
					Assert.assertTrue(false);
					
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
			
			logger.info("*****Excecution of TC003_LoginDDT is completed***" );
	
	}

}
