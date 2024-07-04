package com.luma.qa.util;

import com.luma.qa.logger.Log;

public class TimeUtil {
    public final int DEFAULT_TIME = 500;

    public void applyWait(int waitTime) {
        try {
            Thread.sleep(waitTime * 1000L);
        } catch (InterruptedException e) {
            Log.error("Wait interrupted..", e);
        }
    }
    public void defaultTime() {
        try {
            Thread.sleep(DEFAULT_TIME);
        } catch (InterruptedException e) {
            Log.error("Wait interrupted..", e);
        }
    }
}
