package org.iitwf.hc.mmp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {

	protected WebDriver driver;
	public ProfilePage(WebDriver driver){
		this.driver = driver;
		if (!driver.getTitle().equals("profile")) {
			throw new IllegalStateException("This is not Profile Page," +
					" current page is: " + driver.getCurrentUrl());
		}
	}
	 
	public String getPatientFName()
	{
		return driver.findElement(By.id("fname")).getDomProperty("value");
	}
	 

}
