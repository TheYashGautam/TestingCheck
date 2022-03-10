package com.yash.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.yash.testbase.Testbase;

public class MonthlyPay extends Testbase {
public static String xLPath,xLResultPath;//testdata excel path, result sheet path
public static String xLTD[][];//2D Array
public static int xRows,xCols,xLRowCount;
	public MonthlyPay() throws IOException {
		super();//Super Keyword pointing towards Testbase class testbase constructor
	}
	@BeforeMethod
	public void setup() throws Exception {
		xLPath=System.getProperty("user.dir")+"\\src\\main\\java\\com\\yash\\testdata\\testdata.xls";
		xLResultPath=System.getProperty("user.dir")+"\\src\\main\\java\\com\\yash\\testreports\\reports.xls";
		xLTD=readXL(xLPath,"testdata");
		xLRowCount=xLTD.length;
		System.out.println(" Total rows are ===> " +xLRowCount);
		
		
		
		launchBrowser();
	}
	@Test(priority=1)
	public void monthlypaymenttest() throws InterruptedException {
		//Step 1 Use for loop for excel sheet
		for(int i=1;i<xLRowCount;i++) {
			// Step 2 check if execute column value is Y/yes then only row data
			if(xLTD[i][1].equals("Y")) {
			//Step 3 take each cell data from array to each variables
				LoanAmount=xLTD[i][2];
				LoanYears=xLTD[i][3];
				LoanInterestRate=xLTD[i][5];
				ExpMonthlyPay=xLTD[i][6];
			
		
		//Enter valid loan amount
		driver.findElement(By.xpath(prop.getProperty("loanAmount_editbox"))).clear();
		driver.findElement(By.xpath(prop.getProperty("loanAmount_editbox"))).sendKeys(LoanAmount);
		Thread.sleep(4000);
		//Loan Year
		driver.findElement(By.xpath(prop.getProperty("loanYearsTerm_editbox"))).clear();
		driver.findElement(By.xpath(prop.getProperty("loanYearsTerm_editbox"))).sendKeys(LoanYears);
		//Interest Rate
		driver.findElement(By.xpath(prop.getProperty("loanInterestRate_editbox"))).clear();
		driver.findElement(By.xpath(prop.getProperty("loanInterestRate_editbox"))).sendKeys(LoanInterestRate);
		Thread.sleep(4000);
		//click on calculate button
		driver.findElement(By.xpath(prop.getProperty("calc_button"))).click();
		Thread.sleep(4000);//reason wait??
//		WebDriverWait wait = new WebDriverWait(driver,10);
//		//reason
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("monthlypay_text"))));
//        ExpMonthlyPay="$5666";
		ActMonthlyPay=driver.findElement(By.xpath(prop.getProperty("monthlypay_text"))).getText();
		System.out.println(" Actual monthlypayment is ---->"+ActMonthlyPay);
		xLTD[i][7]=ActMonthlyPay;
		
		if(ActMonthlyPay.equals(ExpMonthlyPay)) {
			System.out.println("Test Case Passed");
			xLTD[i][8]="Test Case Passed";
		}else
			System.out.println("Test Case Failed");
		    xLTD[i][8]="Test Case Failed";
			}
			driver.quit();
		Assert.assertEquals(ActMonthlyPay,ExpMonthlyPay);
		
		}
	}
	
	
	@AfterMethod
	public void tearDown() throws Exception {
		writeXL(xLResultPath,"report",xLTD);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		//Teach Java to R/W from MS Excel
	    // Method to read XL
	    public String[][] readXL(String fPath, String fSheet) throws Exception{
	        // Inputs : XL Path and XL Sheet name
	        // Output : cellValue
	       
	            String[][] xData;  
	//Step   1 create Constructor of FIle Class
	            File myxl = new File(fPath);
	//Step 2 create Constructor of FileInputStream instrcut read data from xls                  
	            FileInputStream myStream = new FileInputStream(myxl);
	            //  Step 3 create Constructor of HSSFworkbook
	            HSSFWorkbook myWB = new HSSFWorkbook(myStream);  
	            //  Step 4 get sheet name in run time
	            HSSFSheet mySheet = myWB.getSheet(fSheet);                                
	            xRows = mySheet.getLastRowNum()+1;                                
	            xCols = mySheet.getRow(0).getLastCellNum();  
	            System.out.println("Total Rows in Excel are " + xRows);
	            System.out.println("Total Cols in Excel are " + xCols);
	            xData = new String[xRows][xCols];        
	            for (int i = 0; i < xRows; i++) { ///outer for loop for rows                          
	                    HSSFRow row = mySheet.getRow(i);
	                    for (int j = 0; j < xCols; j++) {      // inner for loop for columns            
	                        HSSFCell cell = row.getCell(j);
	                        String value = "-";
	                        if (cell!=null){
	                            value = cellToString(cell);
	                        }
	                        xData[i][j] = value;      
	                        System.out.print(value);
	                        System.out.print("----");
	                        }        
	                    System.out.println("");
	                    }    
	            myxl = null; // Memory gets released
	            return xData;
	    }
	   
	    //Change cell type
	    public static String cellToString(HSSFCell cell) {
	        // This function will convert an object of type excel cell to a string value
	        int type = cell.getCellType();                        
	        Object result;                        
	        switch (type) {                            
	            case HSSFCell.CELL_TYPE_NUMERIC: //0                                
	                result = cell.getNumericCellValue();                                
	                break;                            
	            case HSSFCell.CELL_TYPE_STRING: //1                                
	                result = cell.getStringCellValue();                                
	                break;                            
	            case HSSFCell.CELL_TYPE_FORMULA: //2                                
	                throw new RuntimeException("We can't evaluate formulas in Java");  
	            case HSSFCell.CELL_TYPE_BLANK: //3                                
	                result = "%";                                
	                break;                            
	            case HSSFCell.CELL_TYPE_BOOLEAN: //4    
	                result = cell.getBooleanCellValue();      
	                break;                            
	            case HSSFCell.CELL_TYPE_ERROR: //5      
	                throw new RuntimeException ("This cell has an error");    
	            default:                  
	                throw new RuntimeException("We don't support this cell type: " + type);
	                }                        
	        return result.toString();      
	        }
	   
	    // Method to write into an XL
	    public void writeXL(String fPath, String fSheet, String[][] xData) throws Exception{
	        //  input parameter --  result path, sheet name, array Name
	        //output value -- nill
	        //  Step 1 create constrcutor of File  class to store result sheet path
	            File outFile = new File(fPath);
	        //  step 2 create  constructor of  HSSFWorkbook class to add workbook into meemory
	            HSSFWorkbook wb = new HSSFWorkbook();
	         // step 3 create new sheet in existing workbook
	            HSSFSheet osheet = wb.createSheet(fSheet);
	            int xR_TS = xData.length;// row count
	            int xC_TS = xData[0].length;// column count
	            for (int myrow = 0; myrow < xR_TS; myrow++) {
	                HSSFRow row = osheet.createRow(myrow);// create new rows in  workbook
	                for (int mycol = 0; mycol < xC_TS; mycol++) {
	                    HSSFCell cell = row.createCell(mycol);// create cell  in workbook
	                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	                    cell.setCellValue(xData[myrow][mycol]);//  set value
	                }
	                FileOutputStream fOut = new FileOutputStream(outFile);//  Write data
	                wb.write(fOut);//write  into excel file
	                fOut.flush();
	                fOut.close();
	            }
	        }

	
	
	
	
	
}
