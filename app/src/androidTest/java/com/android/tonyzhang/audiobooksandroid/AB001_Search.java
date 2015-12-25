package com.android.tonyzhang.audiobooksandroid;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiCollection;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;
import android.util.Log;

/**
 * Created by tonyzhang on 12/24/15.
 */
public class AB001_Search extends InstrumentationTestCase {
    public void testSearch() throws UiObjectNotFoundException, InterruptedException {
        try{
            Utility.findAndRunApp();
            Thread.sleep(10000);
            Utility.search("The Hunger Games");
            Thread.sleep(5000);

            assertTrue("Hunger Games book not found",
                    Utility.device.findObject(By.text("Hunger Games").clazz("android.widget.TextView"))!=null);


        }catch(UiObjectNotFoundException e){
            Utility.dumpLog(Utility.TEST_LOG_TAG, String.valueOf(e));
        }
    }
}

