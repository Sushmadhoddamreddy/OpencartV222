package utilities;


import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtility {
	
       public static FileInputStream fi;
		public static FileOutputStream fo;
		public static XSSFWorkbook workbook;
		public static XSSFSheet sheet;
		public static XSSFRow row;
		public static XSSFCell cell;
		public static CellStyle style ;
		String path;
		
		public ExcelUtility(String path)
		{
			this.path=path;
		}
		
		
	    public  int getRowCount(String sheetName) throws  Exception 	
	    {
	    	fi = new FileInputStream(path);
	    	workbook= new XSSFWorkbook(fi);
	    	sheet= workbook.getSheet(sheetName);
	    	int rowcount = sheet.getLastRowNum();
	    	workbook.close();
	    	fi.close();
	    	return rowcount;
	    }
	    
	    public int getCellCount(String sheetName,int rownum) throws  IOException
	    {
	    	fi = new FileInputStream(path);
	    	workbook= new XSSFWorkbook(fi);
	    	sheet= workbook.getSheet(sheetName);
	    	row = sheet.getRow(rownum);
	    	int cellcount = row.getLastCellNum();
	    	workbook.close();
	    	fi.close();
	    	return cellcount;
	    }
	    
	    public  String getCellData(String sheetName,int rownum,int colnum) throws  IOException
	    {
	    	fi = new FileInputStream(path);
	    	workbook = new XSSFWorkbook(fi);
	    	sheet = workbook.getSheet(sheetName);
	    	row = sheet.getRow(rownum);
	    	cell = row.getCell(colnum);
	    	
	    	String data;
	    	try
	    	
	    	{
	    		//data = cell.toString(); (or)below one provided by Apache poi
	    		DataFormatter formatter = new DataFormatter();
	    		data = formatter.formatCellValue(cell);//returns the formatted value of a cell as a string regardless of the cell type 
	    	}
	    	catch(Exception e)
	    	{
	    		data="";
	    	}
	        workbook.close();
	        fi.close();
	        return data;
	    }
	    
	    
	    
	    
	    public  void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException
	    {
	    	File xlfile = new File(path);
	    	
	    	if(!xlfile.exists())
	    	{
	    		
	    	workbook = new XSSFWorkbook();
	    	fo = new FileOutputStream(path);
	    	workbook.write(fo);
	    	}
	    	fi = new FileInputStream(path);
	    	workbook = new XSSFWorkbook(fi);
	    	if(workbook.getSheetIndex(sheetName)==-1)//If sheet not exists then create new one
	    	       workbook.createSheet(sheetName);
	    	 sheet=workbook.getSheet(sheetName);
	    	 
	    	 if(sheet.getRow(rownum)==null) // if row not exists then create new one
	    		 sheet.createRow(rownum);
	    	 row = sheet.getRow(rownum);
	    	 
	    	 
	    	cell = row.createCell(colnum);
	    	cell.setCellValue(data);
	    	fo = new FileOutputStream(path);
	    	workbook.write(fo);
	    	workbook.close();
	    	fi.close();
	    	fo.close();
	    }
	    public void fillGreenColor(String sheetName,int rownum,int colnum) throws IOException
	    {
	    	fi = new FileInputStream(path);
	    	workbook = new XSSFWorkbook(fi);
	    	sheet = workbook.getSheet(sheetName);
	    	row = sheet.getRow(rownum);
	    	cell = row.getCell(colnum);
	    	
	    	style = workbook.createCellStyle();
	    	style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
	    	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    	
	    	cell.setCellStyle(style);
	    	fo = new FileOutputStream(path);
	    	workbook.write(fo);
	    	workbook.close();
	    	fi.close();
	    	fo.close();
	    }
	    public  void fillRedColor(String sheetName,int rownum,int colnum) throws IOException
	    {
	    	fi = new FileInputStream(path);
	    	workbook = new XSSFWorkbook(fi);
	    	sheet = workbook.getSheet(sheetName);
	    	row = sheet.getRow(rownum);
	    	cell = row.getCell(colnum);
	    	
	    	style = workbook.createCellStyle();
	    	style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
	    	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    	
	    	cell.setCellStyle(style);
	    	fo = new FileOutputStream(path);
	    	workbook.write(fo);
	    	workbook.close();
	    	fi.close();
	    	fo.close();
	   }

	    
	    
	}
	        
	       
