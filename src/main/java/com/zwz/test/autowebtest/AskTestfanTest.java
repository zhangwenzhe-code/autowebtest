package com.zwz.test.autowebtest;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
*@author  zhangwenzhe
*@date  2020年3月1日---上午10:50:59
*/
public class AskTestfanTest {
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
	@Test
	public void xianshidengdai() throws Exception {
		try {
			Boolean flag = new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(WebDriver d) {
					return d.getPageSource().contains("kitty");
				}
			});
			Assert.assertTrue(flag);
		} catch (Exception | Error e) {
			File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File("images/kittyerror.png"));
			Assert.fail(e.getMessage());
		}
	}
	//@Test
	public void test_sixin() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"unread_messages\"]/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()='写消息']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"message_form\"]/div[1]/span/span[1]/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("tom123");
		Thread.sleep(2000);
		List<WebElement> list = driver.findElements(By.xpath("//li[@role=\"treeitem\"]"));
		list.get(0).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//*[@id=\"message-text\"])[2]")).sendKeys("ask testfan");
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//*[text()='发送'])[2]")).click();
	}
}
