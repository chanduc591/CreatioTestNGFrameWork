package com.creatio.crm.framework.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTest implements IRetryAnalyzer {
	
	int count = 0;
	int retryLimit = 2; // Set the retry limit to 2

	@Override
	public boolean retry(ITestResult result) {
		
		if(!result.isSuccess()) {
			if(count < retryLimit) {
				count++;
				return true;
			}
		}
		
		return false;
	}

	

}
