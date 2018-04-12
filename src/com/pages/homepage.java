package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;


import com.WebActions.*;
import com.pageObjectRepo.*;


public class homepage extends WebActionsNew implements salesPageObjects{
		
	public WebDriver driver;
	public enum Pages{
		
	}
	public homepage(WebDriver Driver) {
		super(Driver);
		try{
			driver = Driver;
			
		}catch(WebDriverException wde){
			wde.printStackTrace();
		}
	}
	
	public synchronized void EnterSearchText(String searchtext) throws Exception{
		try{
			if(searchtext.length() > 0){
				VerifyElementPresentAndType(salesPageObjects.area,searchtext);

			}
		}catch(Exception e){
			System.out.println("Exception while entering Phone Number");

		}
	}
	
	public void ClickSalesButton(){
		try{
			VerifyElementPresentAndClick(salesPageObjects.forsale);
		}catch(Exception e){
			System.out.println("Exception");
		}
	}
	

}
