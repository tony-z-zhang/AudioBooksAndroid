package com.android.tonyzhang.audiobooksandroid;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.test.InstrumentationTestCase;

/**
 * Created by tzhang on 11/23/15.
 */
public class AB002_MyBooks extends InstrumentationTestCase {
    private static final String PACKAGE_NAME = "com.audiobooks.androidapp";
    public void testMyBooks() throws UiObjectNotFoundException, InterruptedException {
        try{
            Utility.findAndRunApp();
            Thread.sleep(10000);
            Utility.myBooks();
            Thread.sleep(5000);

//            // Verify the screen text
            UiObject2 purchasedTab =  Utility.device.findObject(By.res(PACKAGE_NAME, "button1"));
            assertEquals("Purchased", purchasedTab.getText());


        }catch(UiObjectNotFoundException e){
            Utility.dumpLog(Utility.TEST_LOG_TAG, String.valueOf(e));
        }
    }
}