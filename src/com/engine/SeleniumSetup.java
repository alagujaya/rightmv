
package com.engine;


import java.util.Map;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.engine.LoadEnvironment;

public class SeleniumSetup {

	public WebDriver driver;
	WebDriver DRIVER_AT_TEST = null;

	public enum Browser {
		CHROME
	}
	
	
	@BeforeSuite
	public void SetupBeforeTestSuite() {

			//Loading the Environments parameters//To Do - Not working
			LoadEnvironment.LoadEnvProperties("env");
			//LoadEnvironment.LoadGenericTestProperties();

			/* Setup of driver Paths */
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/SupportDrivers/chromedriver.exe");
			//To Do - Extend Report
		}
	
	@AfterMethod
	public void tearDown() throws Exception {

		//To Do Extend Report

		// Closing and Quitting the Driver instance
		if (driver != null) {
			driver.close();driver.quit();
		} else {
			System.out.println( "   --------------------DRIVER IS NULL----------------------");
		}

	}

	@AfterSuite
	// To Do
	
	public void CloseReports() {

	}

	public Object[] TestPreProcessing(String SCRIPTID, String ROW, String InputSheet, String SheetName) {
		Map<String, String> DATA_MAP 	= 	null;

		try {
			
			String Mode = "DESKTOP";
			ReadExcelSheet RX = new ReadExcelSheet();
			DATA_MAP = RX.CreateMapFromExcel(InputSheet, SheetName, ROW);

			// Launching Driver
			System.out.println("Launching Driver");
			
			
			if (DATA_MAP.containsKey("MODE")) {
				Mode = DATA_MAP.get("MODE").toUpperCase();
				System.out.println("mode value is"+DATA_MAP.get("MODE").toUpperCase());
			}
			
			String baseurl = LoadEnvironment.EnvironmentDataMap.get("LAUNCH_URL");
			
			DRIVER_AT_TEST = WhereToExecute(DATA_MAP.get("BROWSER").toString().toUpperCase(),baseurl,Mode); 
			
			System.out.println("after driver launch is "+baseurl);

			
		} catch (Exception exception) {
			exception.getMessage();
		}

		//return DATA_MAP;
		
		return new Object[] { DATA_MAP, DRIVER_AT_TEST };
	}


	@SuppressWarnings("deprecation")
	protected  WebDriver WhereToExecute(String BROWSER, String LAUNCH_URL,String mode) {
		System.out.println("Creating driver in " + BROWSER);
		Browser value = Browser.valueOf(BROWSER.toUpperCase());
						
					driver = null;
						try {

							driver = new ChromeDriver();

						} catch (Exception exception) {
							exception.getMessage();
						}
	
		return driver;
	}
	
	
}