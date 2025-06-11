package com.creatio.crm.framework.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.creatio.crm.framework.reports.Reports;



public class TestListeners extends Reports implements ITestListener {

	public void onTestStart(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		startReporting(testName);	
		Reports.logger.info("Test Case Execution Started: " + testName);
	}

	
	public void onTestSuccess(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		Reports.logger.pass("Test Case Execution Passed: " + testName);
		stopReporting();
	}

	
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		Reports.logger.fail("Test Case Execution Failed: " + testName);
		Reports.logger.fail("Test Case Execution Failed due to Error: " + result.getThrowable().getLocalizedMessage());
		stopReporting();
	}

}
