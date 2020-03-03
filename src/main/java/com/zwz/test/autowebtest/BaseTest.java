package com.zwz.test.autowebtest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
*@author  zhangwenzhe
*@date  2020年3月1日---下午3:15:21
*/
public class BaseTest {
	WebDriver driver;
	@BeforeMethod
	public void setUp() throws InterruptedException{
		driver=new ChromeDriver();
		//设置隐式等待时间10s
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://ask.testfan.cn/login");
		Thread.sleep(1000);
		driver.findElement(By.name("email")).sendKeys("2811920486@qq.com");
		driver.findElement(By.name("password")).sendKeys("123456");
		driver.findElement(By.className("btn-primary")).click();
		Thread.sleep(3000);
	}
	@AfterMethod
	public void close() throws InterruptedException{
		Thread.sleep(5000);
		driver.close();
	}
}
