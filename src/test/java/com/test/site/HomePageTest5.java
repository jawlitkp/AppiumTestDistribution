package com.test.site;


import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.test.base.BaseTest;

public class HomePageTest5 extends BaseTest {

	@Test

	public void testMethodOne_5() throws Exception {
		System.out.println("ThreadName: " + Thread.currentThread().getName());
		waitForElement(By.id("com.android2.calculator3:id/cling_dismiss"), 30);
		//WebElement el= driver.findElement(By.id("com.android2.calculator3:id/cling_dismiss"));
		//highlightElement(driver, el);
		driver.findElement(By.id("com.android2.calculator3:id/cling_dismiss")).click();
		driver.findElement(By.id("com.android2.calculator3:id/digit9")).click();
		driver.findElement(By.id("com.android2.calculator3:id/minus")).click();
		driver.findElement(By.id("com.android2.calculator3:id/digit9")).click();
		driver.findElement(By.id("com.android2.calculator3:id/equal")).click();

	}

}