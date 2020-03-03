package com.zwz.test.autowebtest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
*@author  zhangwenzhe
*@date  2020年3月1日---下午4:37:03
*/
public class InfoTest extends BaseTest{
	@Test
	public void modifyInfo() throws Exception {
		try {
			driver.get("http://ask.testfan.cn/profile/base");
			List<WebElement> radioSex = driver.findElements(By.name("gender"));
			String sexValue="";
			for (WebElement element : radioSex) {
				if (element.getAttribute("value").equals("0")) {
					element.click();
					sexValue=element.getAttribute("value");
					break;
				}
			}
			//生日
			JavascriptExecutor executor=(JavascriptExecutor) driver;
			executor.executeScript("document.getElementById(\"birthday\").value=\"1994-03-23\"");
			String birthValue=driver.findElement(By.id("birthday")).getAttribute("value");
			//省份
			WebElement province = driver.findElement(By.name("province"));
			Select proSelec=new Select(province);
			proSelec.selectByVisibleText("重庆");
			//表示当前选择的是哪一个
			String provinceText = proSelec.getFirstSelectedOption().getText();
			
			driver.findElement(By.xpath("//*[@id=\"base_form\"]/div[8]/div/button")).click();
			
			Boolean flag = new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(WebDriver d) {
					return d.getPageSource().contains("个人资料修改成功");
				}
			});
			Assert.assertTrue(flag);
			
			//断言
			List<WebElement> radioSexActual = driver.findElements(By.name("gender"));
			String actualSex="";
			for (WebElement element : radioSexActual) {
				if (element.isSelected()) {
					actualSex=element.getAttribute("value");
					break;
				}
			}
			Assert.assertEquals(actualSex, sexValue);
			
			String birthValueActual=driver.findElement(By.id("birthday")).getAttribute("value");
			Assert.assertEquals(birthValue, birthValueActual);
			
			//省份实际值
			WebElement provinceActual = driver.findElement(By.name("province"));
			Select proSelectActual=new Select(provinceActual);
			String proActualText = proSelectActual.getFirstSelectedOption().getText();
			Assert.assertEquals(provinceText, proActualText);
		} catch (Exception | Error e) {
			File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File("images/modifyInfoerror.png"));
			Assert.fail(e.getMessage());
		}
		
	}
}
