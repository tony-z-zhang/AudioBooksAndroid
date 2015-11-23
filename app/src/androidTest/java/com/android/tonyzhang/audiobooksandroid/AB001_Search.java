package com.android.tonyzhang.audiobooksandroid;

import android.support.test.uiautomator.UiObjectNotFoundException;
import android.test.InstrumentationTestCase;

/**
 * Created by tonyzhang on 11/22/15.
 */
public class AB001_Search extends InstrumentationTestCase {
    public void testSearch() throws UiObjectNotFoundException, InterruptedException {
        try{
            Utility.findAndRunApp();
            Thread.sleep(10000);
            Utility.search("The Hunger Games");
            Thread.sleep(10000);


        }catch(UiObjectNotFoundException e){
            Utility.dumpLog(Utility.TEST_LOG_TAG, String.valueOf(e));
        }
    }
}

