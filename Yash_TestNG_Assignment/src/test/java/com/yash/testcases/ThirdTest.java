package com.yash.testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ThirdTest{
	
	SoftAssert softassert;
	
	@Test
	public void validateTitle() {
		softassert= new SoftAssert();
		System.out.println("Begin of the test cases");
		String expTitle="yahoo.com";
		String actTitle="gmail.com";
		if(expTitle.equals(actTitle)) {
			System.out.println("Test case is passed");
		}else {
			System.out.println("Test case is failed");
		}
		softassert.assertEquals(actTitle,expTitle);
		System.out.println("Validate Image logo");
		softassert.assertTrue(false, "Element is not found");
		System.out.println("Validate text box presence");
		softassert.assertEquals(true, false);
		System.out.println("End of test cases");
		softassert.assertAll();
		
	}

}
