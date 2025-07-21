package org.iitwf.healthcare.mmp;

import java.util.HashMap;

import org.iitwf.hc.lib.FrameworkLibrary;
import org.iitwf.hc.mmp.pages.HomePage;
import org.iitwf.hc.mmp.pages.LoginPage;
import org.iitwf.hc.mmp.pages.ScheduleAppointmentPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ScheduleAppointmentTests extends FrameworkLibrary{


	@Test
	public void validateBookAppointment()
	{
		LoginPage lPage = new LoginPage(driver);
		HomePage hPage = lPage.loginValidUser("ria1","Ria12345");
		hPage.navigateToAModule("Schedule Appointment");
		ScheduleAppointmentPage sAppointment = new ScheduleAppointmentPage(driver);
		HashMap<String,String> expectedHMap = sAppointment.bookAppointment("Alexander",365);
		HashMap<String,String> actualHMap = hPage.fetchDatafromPatientPortalTable();
		System.out.println("Actual HMap::" +actualHMap);
		System.out.println("Expected HMap::" +expectedHMap);
		Assert.assertTrue(expectedHMap.equals(actualHMap));
	}
	@Test(enabled=false)
	public void validateBookAppointment_2years()
	{
		LoginPage lPage = new LoginPage(driver);
		HomePage hPage = lPage.loginValidUser("ria1","Ria12345");
		hPage.navigateToAModule("Schedule Appointment");
		ScheduleAppointmentPage sPage = new ScheduleAppointmentPage(driver);
		sPage.bookAppointment("Alexander",730);

	}





}


//int a =2;
//int b =3;
//while(a!=b)
//{
//	System.out.println("Hello");
//	a=3;
//	
//}








