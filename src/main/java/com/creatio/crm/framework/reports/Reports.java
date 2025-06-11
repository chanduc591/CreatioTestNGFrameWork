package com.creatio.crm.framework.reports;

import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Reports {
	
	//Initialize all classes
	
	public static ExtentHtmlReporter html; //white paper
	public static ExtentReports extent;//printer
	public static ExtentTest logger; //ink
	
	@BeforeSuite(alwaysRun = true)
	public static void setupReport() {
		html = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\Reports\\AutomationTestReport.html");
		extent = new ExtentReports();
		extent.attachReporter(html);
	}
	
	public static void startReporting(String testName) {
		logger = extent.createTest(testName);
	}
	
	public static void stopReporting() {
		extent.flush();
	}

}
