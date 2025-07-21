package org.iitwf.hc.mmp.pages;

import java.time.Duration;
import java.util.HashMap;

import org.iitwf.healthcare.mmp.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScheduleAppointmentPage {

	protected WebDriver driver;
	public ScheduleAppointmentPage(WebDriver driver){
		this.driver = driver;
		if (!driver.getTitle().equals("Shedule Appointments")) {
			throw new IllegalStateException("This is not Schedule Appointment Page," +
					" current page is: " + driver.getCurrentUrl());
		}
	}
 
	/**
	 * This method helps to do the bookAppointment
	 * 
	 * @param doctorName
	 * @param n
	 * @return 
	 */
	public   HashMap<String, String> bookAppointment(String doctorName,int n) {


		HashMap<String,String> expectedHMap = new HashMap<String,String>();
		expectedHMap.put("doctor",doctorName);


		driver.findElement(By.xpath("//input[@value='Create new appointment']")).click();
		driver.findElement(By.xpath("//h4[text()='Dr."+doctorName+"']/ancestor::ul/following-sibling::button")).click();
		driver.switchTo().frame("myframe");


		Duration d = Duration.ofSeconds(30);
		WebDriverWait wait = new WebDriverWait(driver,d);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("datepicker"))));

		driver.findElement(By.id("datepicker")).click();

		String expectedDate[] = DateUtils.getFutureDate("d/MMMMM/yyyy", n).split("/");
		String expectedDay   = expectedDate[0];
		String expectedMonth = expectedDate[1];
		String expectedYear =  expectedDate[2];
		System.out.println("Expected Year:::" + expectedYear);

		String actualMonth = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
		String actualYear =   driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText(); 
		System.out.println("actual Year:::: " + actualYear);	
		System.out.println("Result:::" + actualYear.equals(expectedYear));

		while(!(expectedYear.equals(actualYear)))
		{
			System.out.println("in while loop checking the year");
			driver.findElement(By.xpath("//span[text()='Next']")).click();
			actualYear =   driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText(); 
		}

		while(!(expectedMonth.equals(actualMonth)))
		{
			System.out.println("in while loop checking the year");
			driver.findElement(By.xpath("//span[text()='Next']")).click();
			actualMonth =   driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText(); 
		}
		driver.findElement(By.linkText(expectedDay)).click();

		expectedHMap.put("date",driver.findElement(By.id("datepicker")).getDomProperty("value"));





		//		WebElement timeWE = driver.findElement(By.id("time"));
		//		Select timeListbox = new Select(timeWE);
		//		timeListbox.selectByVisibleText("10Am");


		new Select( driver.findElement(By.id("time"))).selectByVisibleText("10Am");
		expectedHMap.put("time",new Select( driver.findElement(By.id("time"))).getFirstSelectedOption().getText());

		wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("status")),"OK"));

		driver.findElement(By.id("ChangeHeatName")).click();

		driver.switchTo().defaultContent();

		driver.findElement(By.id("sym")).sendKeys("Fever and Cold");
		expectedHMap.put("sym",	driver.findElement(By.id("sym")).getDomProperty("value"));

		//https://repo1.maven.org/maven2/org/seleniumhq/selenium/selenium-java/4.33.0/
		driver.findElement(By.xpath("//input[@value='Submit']")).click();



		return expectedHMap;



	}
}
