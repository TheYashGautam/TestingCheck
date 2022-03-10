package com.yash.testcases;

import org.testng.annotations.Test;

import com.yash.testbase.Testbase;

public class SecondTest extends Testbase{
	
	@Test(priority=1)
	public void searchtest() {
		System.out.println("iphone is searched @test1");
	}
	@Test(priority=2)
	public void shoppingtest() {
		System.out.println("iphone is added in cart @test2");
	}
	@Test(priority=3)
	public void paymenttest() {
		System.out.println("iphone is done @test3 ");
	}
}
