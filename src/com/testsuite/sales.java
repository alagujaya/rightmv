package com.testsuite;

import com.engine.SeleniumSetup;
import com.engine.LoadEnvironment;
import com.engine.ReadExcelSheet;
import com.businessflow.*;

import java.util.Map;

import org.openqa.selenium.WebDriver;

//TestNG import

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class sales extends SeleniumSetup {
	
	
	public String excelPath = (System.getProperty("user.dir")+"/dataprovider/inputsheet_v1.xls");

	public String shtName = LoadEnvironment.EnvironmentDataMap.get("shtName");

	public static int NumberOfIterations = 0;

	
	
	@DataProvider(name = "ExcelInput", parallel = true) // Data


		public Object[][] DATA() throws Exception {
				
		ReadExcelSheet DP = new ReadExcelSheet();

		shtName = "Sales";
		
		return DP.getExcelData(excelPath, shtName,"Sales"); // Get data object
		}
	
	
	
	@Test(dataProvider = "ExcelInput", invocationCount = 1, threadPoolSize = 3)

	public void Sales(String SCRIPT_ID, String ROW) throws Exception {
		
		WaitForYourTurn();

		SalesFlow SF = new SalesFlow();

		Object[] Obj = TestPreProcessing(SCRIPT_ID, ROW,excelPath,shtName);
		
		
		SF.DATA_MAP = (Map<String, String>) Obj[0];

		WebDriver driver = (WebDriver) Obj[1];

		
		SF.salesFlow(SCRIPT_ID, ROW);
	}
	
	public boolean WaitForYourTurn() {
		try {
			while (true) {
				if (NumberOfIterations < 3) {
					NumberOfIterations = NumberOfIterations + 1;
					System.out.println("Number of Iterations true: " + NumberOfIterations);
					return true;
				} else {
					Thread.sleep(10000);
					System.out.println("Number of Iterations false: " + NumberOfIterations);
				return false;
				}
			}
		} catch (NullPointerException npe) {

		} catch (Exception e) {

		}
		return false;
	}


}
