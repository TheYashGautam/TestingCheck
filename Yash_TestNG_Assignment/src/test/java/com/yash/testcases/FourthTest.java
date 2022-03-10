package com.yash.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FourthTest {
	
	@Test(priority=1)
	public void doUserReg() {
		System.out.println(" Executing User Register @Test1");
		Assert.assertEquals("Vivek","Test");
	}
	
	@Test(priority=2, dependsOnMethods= {"doUserReg"},alwaysRun=true)//Soft Dependencies
	public void doLogin()
	{
		System.out.println("Executing login @Test2");
	}
	
	@Test(priority=3, dependsOnMethods= {"doUserReg","doLogin"})
	public void viewprofile()
	{
		System.out.println("Check Complete");
	}

}
