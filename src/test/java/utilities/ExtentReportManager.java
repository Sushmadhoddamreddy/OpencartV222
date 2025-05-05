package utilities;


import java.awt.Desktop;
import java.io.File;
//import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//import org.apache.commons.mail.DefaultAuthenticator;
//import org.apache.commons.mail.ImageHtmlEmail;
//import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
		
		public ExtentSparkReporter SparkReporter;
		
		public ExtentReports extent;
		
		public ExtentTest test;
		
		String repName;
		public void onStart(ITestContext testcontext) {
			
			/*SimpleDateFormat df =new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
			Date dt =new Date();
			String currentdatetimeStamp = df.format(dt);*/
			
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			repName= "Test-Report-" + timeStamp + ".html";
			
			SparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);
			
			SparkReporter.config().setDocumentTitle(" OpenCart Automation Report");
			
			SparkReporter.config().setReportName("OpenCart Functional Testing");
			
			SparkReporter.config().setTheme(Theme.DARK);
			
			extent = new ExtentReports();
			extent.attachReporter(SparkReporter );
			
			extent.setSystemInfo("Application", "OpenCart");
			extent.setSystemInfo("Module", "Admin");
			extent.setSystemInfo("Sub Module", "Customers");
			extent.setSystemInfo("user Name",System.getProperty("user.name"));
			extent.setSystemInfo("Environment", "QA");
			
			String OS = testcontext.getCurrentXmlTest().getParameter("os");
			extent.setSystemInfo("Operating System", OS);
			
			String browser = testcontext.getCurrentXmlTest().getParameter("browser");
			extent.setSystemInfo("Browser", browser);
			
			List<String> includedGroups = testcontext.getCurrentXmlTest().getIncludedGroups();
			if(!includedGroups.isEmpty())
			{
				extent.setSystemInfo("Groups", includedGroups.toString());
			}
		}
		
		public void onTestSuccess(ITestResult result) 
		{
		    test = extent.createTest(result.getTestClass().getName());
		    test.assignCategory(result.getMethod().getGroups());
		    test.log(Status.PASS,result.getName()+ "got successfully executed");
		}
		
		
		
		public void onTestFailure(ITestResult result) 
		{
			test = extent.createTest(result.getTestClass().getName());
		    test.assignCategory(result.getMethod().getGroups());
		    test.log(Status.FAIL,result.getName()+ "got failed");
		
		    test.log(Status.INFO,result.getThrowable().getMessage());
		    try
		    {
		    	String impPath = new BaseClass().CaptureScreen(result.getName());
		    	test.addScreenCaptureFromPath(impPath);
		    }
		    catch(Exception e1)
		    {
		    	e1.printStackTrace();
		    }
		    
		}
		public void onTestSkipped(ITestResult result)
		{
			test = extent.createTest(result.getTestClass().getName());
		    test.assignCategory(result.getMethod().getGroups());
		    test.log(Status.SKIP,result.getName()+ "got Skipped");
		
		    test.log(Status.INFO,result.getThrowable().getMessage());
		}
		
		public void onFinish(ITestContext context)
		{
		 extent.flush();  
		 
		 String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+ repName;
		 File extentReport = new File( pathOfExtentReport);
		 
		 try
		 {
			 Desktop.getDesktop().browse(extentReport .toURI());
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
	     }
		/* 
		  try
		  {
			  URL url =new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
			  
			  //Create the email message
			  ImageHtmlEmail email = new ImageHtmlEmail();
			 
			  email.setDataSourceResolver(new DataSourceUrlResolver(url));
			  email.setHostName("smtp.googlemail.com");// only applicable for google ,depends on host we need to change
			  email.setSmtpPort(465);
			  email.setAuthenticator(new DefaultAuthenticator("sushmadoddamreddy@gmail.com","Sushma@2210"));
			  email.setSSLOnConnect(true);
			  email.setFrom("sushmadoddamreddy@gmail.com");//sender
			  email.setSubject("Test Result");
			  email.setMsg("Please find the Auotmation report...");
			  email.addTo("sushmadronadula2214@gmail.com","vivekanadaorc@gmail.com");//Receiver
			  // to send for multiple members we can use distribution email
			  email.attach(url,"extent report","please check report");
			  email.send();//send the email
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }*/
		  
		 
		}
		
}


