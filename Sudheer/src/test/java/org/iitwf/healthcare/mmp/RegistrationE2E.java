package org.iitwf.healthcare.mmp;

import java.time.Duration;
import java.util.HashMap;

import org.iitwf.hc.lib.FrameworkLibrary;
import org.iitwf.hc.mmp.pages.HomePage;
import org.iitwf.hc.mmp.pages.LoginPage;
import org.iitwf.hc.mmp.pages.MessagesAdminPage;
import org.iitwf.hc.mmp.pages.MessagesPage;
import org.iitwf.hc.mmp.pages.ProfilePage;
import org.iitwf.hc.mmp.pages.RegistrationPage;
import org.iitwf.hc.mmp.pages.UsersPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class RegistrationE2E extends FrameworkLibrary {

	@Test
	public void registerNewPatient() {

		//Patient login to send a message
		LoginPage lPage = new LoginPage(driver);
		RegistrationPage rPage = lPage.registerNewUser();	 
		HashMap<String, String> expectedHMap =rPage.registerNewUser();
		System.out.println("expectedHMap :"+expectedHMap);
		launchApplication(prop.getProperty("patient_url"));
		SoftAssert sa = new SoftAssert();
		String alertMsg = lPage.loginInValidUser(expectedHMap.get("username"), expectedHMap.get("password"));
		sa.assertEquals(alertMsg, "Admin Approval is pending. "); 

		//Admin login to verify the message
		launchApplication(prop.getProperty("admin_url"));
		HomePage hPage = lPage.loginValidUser(prop.getProperty("admin_username"), prop.getProperty("admin_password"));	 
		hPage.navigateToAModule("Users");
		UsersPage uPage = new UsersPage(driver);
		HashMap<String, String> actualHMap = uPage.actionOnNewUser(expectedHMap.get("fname"), expectedHMap.get("ssn"));

		launchApplication(prop.getProperty("patient_url"));
		lPage.loginValidUser(expectedHMap.get("username"), expectedHMap.get("password"));

		expectedHMap.remove("username");
		expectedHMap.remove("password");
		expectedHMap.remove("security");
		System.out.println("expectedHMap :"+expectedHMap);
		System.out.println("actualHMap :"+actualHMap);
		sa.assertEquals(expectedHMap, actualHMap);
		sa.assertAll();
	}
}
