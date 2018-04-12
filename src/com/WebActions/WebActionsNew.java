package com.WebActions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.engine.*;

import com.thoughtworks.selenium.Selenium;

public class WebActionsNew extends LoadEnvironment{

	public WebDriver driver;
	public Selenium selenium;
	public int timeoutInSeconds=30;

	public WebActionsNew() {

	}
	public WebActionsNew(WebDriver Driver) {
		driver = Driver;
	}
	
	
	public void VerifyElementPresentAndType(String element, String content) {

		try {
			
			By byElement = getElementFromLoc(element);


			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
			if (wait.until(ExpectedConditions.presenceOfElementLocated(By.id(element))) != null) {;
			
				driver.findElement(byElement).sendKeys(content);
			}

			
		} catch (Exception exception) {
		}
		
		}
	public void VerifyElementPresentAndClick(String element) {
		System.out.println("element "+element);


			try {

				By byElement = getElementFromLoc(element);
				WebElement WE = driver.findElement(byElement);

				((JavascriptExecutor)
						driver).executeScript("arguments[0].scrollIntoView(true);",WE);
				((JavascriptExecutor) driver).executeScript("javascript:window.scrollTo(" + WE.getLocation().getX()
						+ "," + (WE.getLocation().getY()));

				if (waitForElementToAppear(byElement, timeoutInSeconds)) {
					Thread.sleep(1000);
			
					while (!WE.isDisplayed()) {
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", WE);
					}


					try {
						driver.findElement(byElement).click();
					} catch (WebDriverException exception) {

						System.out.println(exception.getMessage());
						if (exception.getMessage().contains("Element is not clickable")) {
							WE.sendKeys("");
						}
					}

				}
			} catch (Exception exception) {


			}
		
	}
	
	
	public static By getElementFromLoc(String target) {
		StringBuffer element = new StringBuffer(target);
		if (target.startsWith("//") || target.startsWith("(//")) {
			return (By.xpath(element.toString()));
		}
		if (target.startsWith("xpath=") || target.startsWith("XPATH=")) {
			element.delete(0, 6);
			return (By.xpath(element.toString()));
		}
		if (target.startsWith("id=") || target.startsWith("ID=")) {
			element.delete(0, 3);
			return (By.id(element.toString()));
		}
		if (target.startsWith("name=") || target.startsWith("NAME=")) {
			element.delete(0, 5);
			return (By.name(element.toString()));
		}
		if (target.startsWith("css=") || target.startsWith("CSS=")) {
			element.delete(0, 4);
			return (By.cssSelector(element.toString()));
		}
		if (target.startsWith("link=") || target.startsWith("LINK=")) {
			element.delete(0, 5);
			return (By.linkText(element.toString()));
		}
		if (target.startsWith("tagname=") || target.startsWith("TAGNAME=")) {
			element.delete(0, 8);
			return (By.tagName(element.toString()));
		}
		if (target.startsWith("classname=") || target.startsWith("CLASSNAME=")) {
			element.delete(0, 10);
			return (By.className(element.toString()));
		}
		return null;
	}

	public boolean waitForElementToAppear(By elementId, int time) {
		if (waitForElement(elementId, time) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public WebElement waitForElement(By elementId, int timeoutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		
			wait.until(ExpectedConditions.visibilityOfElementLocated(elementId));
			return driver.findElement(elementId);
		} catch (Exception exception) {
			System.out.println("element not found " + elementId + " Error message:" + exception.getMessage());
			return null;
		}

	}
}
