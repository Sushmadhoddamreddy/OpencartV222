package utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//Data provider 1
	
	@DataProvider(name ="LoginData")
	public String[][] getData() throws Exception
	{
      String path =".\\testData\\OpenCart_LoginDetails.xlsx";//taking xl file from testData
      
      ExcelUtility xlutil = new ExcelUtility(path); //creating object for excelUtility
      
      
      int totalrows = xlutil.getRowCount("Sheet1");
      int totalcols = xlutil.getCellCount("Sheet1",1);
      
      String logindata[][] = new String[totalrows][totalcols];
      
      for(int i=1;i<=totalrows;i++)
      {
    	  for(int j=0;j<totalcols;j++)
    	  {
    		  
    		  logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);
    	  }
      }
      
      return logindata;//returing two dimensional array
	}
}


// data provider 2


//data provider 3