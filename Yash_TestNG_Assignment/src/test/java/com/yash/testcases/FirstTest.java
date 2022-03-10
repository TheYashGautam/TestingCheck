package com.yash.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.yash.testbase.Testbase;

public class FirstTest extends Testbase{
	

	
	@Test
	public void sendmailtest()
	{
		
		System.out.println("Mail is sent @Test1");
		String Exptext="Bingo";
		String Acttext="bingo";
		Assert.assertEquals(Exptext,Acttext);
		//report failure
		Assert.assertTrue(10<5,"Logo is missing");
	}
	

}
