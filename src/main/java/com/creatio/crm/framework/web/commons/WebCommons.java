package com.creatio.crm.framework.web.commons;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.creatio.crm.framework.base.BasePage;
import com.creatio.crm.framework.utilities.PropUtil;

public class WebCommons {
	
	public static WebDriver driver=BasePage.getDriver();
	
	public static Properties prop= PropUtil.readData("config.properties");
	
	public void scrollToElement(WebElement element) {

		JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);

	}
	
	public void click(WebElement element) {
		scrollToElement(element);
		element.click();
	}
	
	 public void jsClick(WebElement element) {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].click();", element);
	    }
	 
	 
	 public void doubleClick(WebElement element) {
	        scrollToElement(element);
	        Actions action=new Actions(driver);
	        action.doubleClick(element).perform();
	    }
	 
	    public void rightClick(WebElement element) {
	        scrollToElement(element);
	        Actions action=new Actions(driver);
	        action.contextClick(element).perform();
	    }
	    
	    public void enterTextUsingActions(WebElement element, String text) {
	        scrollToElement(element);
	        Actions action=new Actions(driver);
	        action.sendKeys(element, text).perform();
	    }

	    public void selectCheckbox(WebElement checkbox, boolean status) {
	        scrollToElement(checkbox);
	        if (checkbox.isSelected() != status) {
	            checkbox.click();
	        }
	    }

	    public void selectDropdownOption(WebElement dropdown, String option, String selectBy) {
	        scrollToElement(dropdown);
	        Select select = new Select(dropdown);
	        switch (selectBy.toLowerCase()) {
	            case "value":
	                select.selectByValue(option);
	                break;
	            case "visibletext":
	                select.selectByVisibleText(option);
	                break;
	            case "index":
	                select.selectByIndex(Integer.parseInt(option));
	                break;
	            default:
	                throw new IllegalArgumentException("Invalid selection method: " + selectBy);
	        }
	    }
	    public void implicitWait(long seconds) {
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	    }

	    public void waitForElement(WebElement element, long seconds) {
	        new WebDriverWait(driver, Duration.ofSeconds(seconds))
	            .until(ExpectedConditions.visibilityOf(element));
	    }

	    public void waitForElement(By locator, long seconds) {
	        new WebDriverWait(driver, Duration.ofSeconds(seconds))
	            .until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, 0));
	    }
	    public void waitForAlert(long seconds) {
	        new WebDriverWait(driver, Duration.ofSeconds(seconds))
	            .until(ExpectedConditions.alertIsPresent());
	    }

	    public void uploadFile(WebElement element, String filePath) {
	        scrollToElement(element);
	        element.sendKeys(filePath);
	    }
	    public static String windowScreenshot(WebDriver driver, String fileName) throws IOException {
	        String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + fileName + ".png";
	        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        FileUtils.copyFile(screenshotFile, new File(screenshotPath));
	        return screenshotPath;
	    }
	    public static String elementScreenshot(WebElement element, String fileName) throws IOException {
	        String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + fileName + ".png";
	        File screenshotFile = element.getScreenshotAs(OutputType.FILE);
	        FileUtils.copyFile(screenshotFile, new File(screenshotPath));
	        return screenshotPath;
	    }
	    public void switchToFrame(WebElement frameElement) {
	        driver.switchTo().frame(frameElement);
	    }
	    public void switchToFrame(String frameNameOrId) {
	        driver.switchTo().frame(frameNameOrId);
	    }
	    public String uniqueId(String format) {
	        return new SimpleDateFormat(format).format(Calendar.getInstance().getTime());
	    }
	    public boolean isElementEnabled(WebElement element) {
	        return element.isEnabled();
	    }
	    public String getElementText(WebElement element) {
	        return element.getText();
	    }
	    public String getAttributeValue(WebElement element, String attribute) {
	        return element.getAttribute(attribute);
	    }
	    public String getTitle() {
	        return driver.getTitle();
	    }
	    public boolean isElementDisplayed(WebElement element) {
	        return element.isDisplayed();
	    }
	    public String getCurrentWindowHandle() {
	        return driver.getWindowHandle();
	    }
	    public Set<String> getAllWindowHandles() {
	        return driver.getWindowHandles();
	    }
	    public void switchToNewWindow() {
	        String currentWindow = driver.getWindowHandle();
	        for (String handle : driver.getWindowHandles()) {
	            if (!handle.equals(currentWindow)) {
	                driver.switchTo().window(handle);
	                break;
	            }
	        }
	    }
	    public void switchToWindowByTitle(String title) {
	        for (String handle : driver.getWindowHandles()) {
	            driver.switchTo().window(handle);
	            if (driver.getTitle().equalsIgnoreCase(title)) {
	                break;
	            }
	        }
	    }
	    public void switchToWindow(String windowHandle) {
	        driver.switchTo().window(windowHandle);
	    }
	    public void closeAllOtherWindows() {
	        String mainWindow = driver.getWindowHandle();
	        for (String handle : driver.getWindowHandles()) {
	            if (!handle.equals(mainWindow)) {
	                driver.switchTo().window(handle);
	                driver.close();
	            }
	        }
	        driver.switchTo().window(mainWindow);
	    }
	    public void acceptAlert() {
	        Alert alert = driver.switchTo().alert();
	        alert.accept();
	    }
	    public void dismissAlert() {
	        Alert alert = driver.switchTo().alert();
	        alert.dismiss();
	    }
	    public String getAlertText() {
	        return driver.switchTo().alert().getText();
	    }
	    public void sendTextToAlert(String text) {
	        Alert alert = driver.switchTo().alert();
	        alert.sendKeys(text);
	    }
	    public void navigateBack() {
	        driver.navigate().back();
	    }
	    public void navigateForward() {
	        driver.navigate().forward();
	    }
	    public void refreshPage() {
	        driver.navigate().refresh();
	    }
	    public boolean isElementPresent(WebElement element) {
	        try {
	            return element.isDisplayed();
	        } catch (NoSuchElementException e) {
	            return false;
	        }
	    }
	    public void mouseHover(WebElement element) {
	        scrollToElement(element);
	        new Actions(driver).moveToElement(element).perform();
	    }
	    public void scrollToBottom() {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	    }
	    public void scrollToTop() {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollTo(0, 0);");
	    }



}
