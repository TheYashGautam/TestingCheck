package com.yash.testcases;

import org.testng.SkipException;
import org.testng.annotations.Test;

public class MySkipTest {
	
	@Test
	public void isSkip() {
		throw new SkipException(" Skipping the test as conditions");
	}

}
