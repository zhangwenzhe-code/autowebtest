package com.zwz.test.autowebtest;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
*@author  zhangwenzhe
*@date  2020年3月1日---下午3:15:02
*/
public class WindowSwitch extends BaseTest{
	@Test
	public void switchToWindow() throws IOException {
		try {
			String currHandle = driver.getWindowHandle();
			driver.findElement(By.linkText("Selenium3环境搭建")).click();
			driver.findElement(By.linkText("Selenium中CssSelector定位器基础语法")).click();
			Set<String> allHandles = driver.getWindowHandles();
			for (String handle:allHandles) {
				driver.switchTo().window(handle);
				if (driver.getTitle().equals("Selenium3环境搭建-Testfan软件测试社区")) {
					break;
				}
			}
//			for (String handle:allHandles) {
//				if (!driver.getTitle().equals("Selenium3环境搭建-Testfan软件测试社区")) {
//					driver.switchTo().window(handle);
//					//break;
//				}else {
//					break;
//				}
//			}
//			for (String handle:allHandles) {
//				if (!currHandle.equals(handle)) {
//					driver.switchTo().window(handle);
//					break;
//				}
//			}
			driver.findElement(By.id("support-button")).click();
			Boolean flag = new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(WebDriver d) {
					return d.findElement(By.id("support-button")).getText().contains("已推荐");
				}
			});
			Assert.assertTrue(flag);
		} catch (Exception |Error e) {
			File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File("images/windowSwitcherror.png"));
			Assert.fail(e.getMessage());
		}
	}
}
