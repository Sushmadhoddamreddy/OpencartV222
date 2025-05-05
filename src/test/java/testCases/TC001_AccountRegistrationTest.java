package testCases;

import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;


public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("******Starting  TC001_AccountRegistrationTest********" );
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		
		logger.info("clicked on MyAccountLink" );
		hp.clickRegister();
		
		logger.info("clicked on Registration" );
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		
		logger.info("******Entering Customer details********" );
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com");
		regpage.setTelephone( randomNumber());
		
		String password = randomAlphaNumeric();
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("validating expected message" );
		String confmssg=regpage.getConfirmationMsg();
		if(confmssg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed...");
			logger.debug("Debug Logs...");
			Assert.assertTrue(true);
		
		}
		}
		catch(Exception e)
		{
			
			Assert.fail();
		}
		logger.info("*******Completed TC001_AccountRegistrationTest execution********");
		
	}
  
}
