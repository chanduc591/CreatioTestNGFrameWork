package com.creatio.crm.framework.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BasePage {

	private static WebDriver driver = null;

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "BROWSER" })
	public void setupBrowser(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {

			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {

			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {

			driver = new EdgeDriver();

		} else {

			Assert.fail("Browser is not supported: " + browserName);
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

	}

	@AfterMethod(alwaysRun = true)
	public void teardownBrowser() {

		if (driver != null) {
			driver.quit();
		}
	}

	public static WebDriver getDriver() {

		return driver;

	}

	public static void setDriver(WebDriver driver) {
		BasePage.driver = driver;
	}

}
