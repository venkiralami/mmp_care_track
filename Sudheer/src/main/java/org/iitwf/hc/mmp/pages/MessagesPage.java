package org.iitwf.hc.mmp.pages;

import java.util.HashMap;

import org.iitwf.healthcare.mmp.DateUtils;
import org.iitwf.healthcare.mmp.RandomUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MessagesPage {
	protected WebDriver driver;
	/**
	 * Constructor for MessagesPage
	 * @param driver WebDriver instance to interact with the browser
	 */

	public MessagesPage(WebDriver driver){
		this.driver = driver;
		if (!driver.getTitle().equals("Send Messages")) {
			throw new IllegalStateException("This is not Send Messages Page," +
					" current page is: " + driver.getCurrentUrl());
		}
	}

	/**
	 * This method will contain the logic to send a message to a patient
	 * It will use the FrameworkLibrary and other page classes to perform the actions
	 * @return 
	 */
	public HashMap<String, String> sendMessageToAdmin(String patientName, String message) {


		HashMap<String, String> expectedHMap = new HashMap<String, String>();
		// Logic to send message goes here
		System.out.println("Sending message to patient: " + patientName);
		System.out.println("Message content: " + message);
		expectedHMap.put("patientName", patientName);
		//Generate Subject and Message
		// Assuming the subject is a combination of a static string and a random string
		String ranString = RandomUtils.generateRandomString(5);
		driver.findElement(By.id("subject")).sendKeys("Visit Reminder - "+ranString);
		expectedHMap.put("subject", driver.findElement(By.id("subject")).getDomProperty("value"));
		driver.findElement(By.id("message")).sendKeys(message+ranString);
		expectedHMap.put("message", driver.findElement(By.id("message")).getDomProperty("value"));
		driver.findElement(By.xpath("//input[@value='Send']")).submit();
		expectedHMap.put("date", DateUtils.getFutureDate("dd-MM-YYYY",0));
		System.out.println("Message sent successfully.");
		return expectedHMap;
	}	

	public String getMessageStatus() {
		// This method would typically check the status of the sent message
		// For now, we will return a dummy status
		Alert alert = driver.switchTo().alert();
		String messagesText = alert.getText(); // Get the alert text
		alert.accept(); // Accept the alert to close it
		return messagesText;
	}
}
