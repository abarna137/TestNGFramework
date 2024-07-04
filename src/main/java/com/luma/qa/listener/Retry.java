package com.luma.qa.listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    private int count = 0;
    private int maxRetryCount = 2;
    @Override
    public boolean retry(ITestResult iTestResult) {
        if(!iTestResult.isSuccess()) {
            if(count<maxRetryCount) {
                count++;
                iTestResult.setStatus(iTestResult.FAILURE);
                return true;
            } else {
                iTestResult.setStatus(iTestResult.FAILURE);
            }
        } else {
            iTestResult.setStatus(iTestResult.SUCCESS);
        }
        return false;
    }
}
