package org.iitwf.hc.mmp.pages;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class HomePage {

	protected WebDriver driver;
	public HomePage(WebDriver driver){
		this.driver = driver;
		if (!driver.getTitle().equals("home")) {
			throw new IllegalStateException("This is not Home Page," +
					" current page is: " + driver.getCurrentUrl());
		}
	}
	public void navigateToAModule(String moduleName)
	{
		driver.findElement(By.xpath( "//span[normalize-space(text())='"+moduleName+"']")).click();
	}

	public HashMap<String, String> fetchDatafromPatientPortalTable()
	{
		HashMap<String,String> actualHMap = new HashMap<String,String>();
		actualHMap.put("date",driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[1]")).getText());
		actualHMap.put("time",driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[2]")).getText());
		actualHMap.put("sym",driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[3]")).getText());
		actualHMap.put("doctor",driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[4]")).getText());
		return actualHMap;



	}

}
