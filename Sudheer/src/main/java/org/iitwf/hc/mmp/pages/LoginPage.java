package org.iitwf.hc.mmp.pages;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class LoginPage {

	protected WebDriver driver;
	private By usernameBy = By.id("username");
	private By passwordBy = By.id("password");
	private By signinBy = By.xpath("//input[@value='Sign In']");
	private By registerBy = By.xpath("//input[@value='Register']");

	public LoginPage(WebDriver driver){
		this.driver = driver;
		if (!driver.getTitle().equals("Login")) {
			throw new IllegalStateException("This is Login Page," +
					" current page is: " + driver.getCurrentUrl());
		}
	}
	public HomePage loginValidUser(String userName, String password) {
		driver.findElement(usernameBy).sendKeys(userName);
		driver.findElement(passwordBy).sendKeys(password);
		driver.findElement(signinBy).click();
		return new HomePage(driver);
	}

	public String loginInValidUser(String userName, String password) {
		driver.findElement(usernameBy).sendKeys(userName);
		driver.findElement(passwordBy).sendKeys(password);
		driver.findElement(signinBy).click();
		
		String alertMsg= handleAlertIfPresent(driver);
		
		return alertMsg;
	}
	
	public String handleAlertIfPresent(WebDriver driver) {
	    String alertText = null;
		try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
	        wait.until(ExpectedConditions.alertIsPresent());
	        Alert alert = driver.switchTo().alert();
	        alertText =  alert.getText();
	        System.out.println("Alert found: " +alertText);
	        alert.accept();
	    } catch (TimeoutException e) {
	        // No alert appeared
	    }
	    return alertText;
	}

	public RegistrationPage registerNewUser() {

		driver.findElement(registerBy).click();

		return new RegistrationPage(driver);
	}

}
