package org.iitwf.healthcare.mmp;

import java.util.HashMap;

import org.iitwf.hc.lib.FrameworkLibrary;
import org.iitwf.hc.mmp.pages.HomePage;
import org.iitwf.hc.mmp.pages.LoginPage;
import org.iitwf.hc.mmp.pages.MessagesAdminPage;
import org.iitwf.hc.mmp.pages.MessagesPage;
import org.iitwf.hc.mmp.pages.ProfilePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SendMessagesE2E extends FrameworkLibrary {
	
	@Test
	public void sendMessageToPatient() {
	 
		//Patient login to send a message
		LoginPage lPage = new LoginPage(driver);
		HomePage hPage = lPage.loginValidUser(prop.getProperty("patient_username"), prop.getProperty("patient_password"));	 
		hPage.navigateToAModule("Profile");
		ProfilePage pPage = new ProfilePage(driver);
		String patientName = pPage.getPatientFName();
		hPage.navigateToAModule("Messages");
		MessagesPage sPage = new MessagesPage(driver);
		HashMap<String,String> expectedHMap =sPage.sendMessageToAdmin(patientName, "Appointment scheduled - ");
		String expected = "Messages Successfully sent.";
		String actual = sPage.getMessageStatus();
		SoftAssert sa = new SoftAssert();
		
		sa.assertEquals(actual, expected, "Message status does not match expected value.");
		System.out.println("Message sent successfully.");
		hPage.navigateToAModule("Logout");
		
		//Admin login to verify the message
		 launchApplication(prop.getProperty("admin_url"));
		 hPage = lPage.loginValidUser(prop.getProperty("admin_username"), prop.getProperty("admin_password"));	 
		 hPage.navigateToAModule("Messages");
		 MessagesAdminPage aPage = new MessagesAdminPage(driver);
		 HashMap<String,String> actualHMap = aPage.getMessageDetails(expectedHMap.get("subject"),expectedHMap.get("message"));
		 System.out.println("expectedHMap : "+expectedHMap);
		 System.out.println("actualHMap : "+actualHMap);
		 sa.assertEquals(actualHMap, expectedHMap);
		
		 sa.assertAll();
		
		
	}
}
