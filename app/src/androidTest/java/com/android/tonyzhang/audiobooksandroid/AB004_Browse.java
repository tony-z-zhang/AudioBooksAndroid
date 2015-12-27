package com.android.tonyzhang.audiobooksandroid;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.test.InstrumentationTestCase;

/**
 * Created by tonyzhang on 12/26/15.
 */
public class AB004_Browse extends InstrumentationTestCase {
    public void testBrowse() throws UiObjectNotFoundException, InterruptedException {
        try {
            Utility.findAndRunApp();
            Thread.sleep(10000);
            Utility.menubyID(1);
            Thread.sleep(5000);
            Browse.randombrowse();
            Thread.sleep(2000);
            Browse.randombook();
            Thread.sleep(2000);

        } catch (UiObjectNotFoundException e) {
            Utility.dumpLog(Utility.TEST_LOG_TAG, String.valueOf(e));
        }
    }
}