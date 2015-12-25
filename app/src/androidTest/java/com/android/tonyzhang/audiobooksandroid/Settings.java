package com.android.tonyzhang.audiobooksandroid;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

/**
 * Created by tonyzhang on 12/25/15.
 */
public class Settings {
    private static final String PACKAGE_NAME = "com.audiobooks.androidapp";
    protected static String getCredits() throws UiObjectNotFoundException, InterruptedException {

        Utility.userLogin("tony.zhang@gm.com", "Abc123");
        Thread.sleep(5000);
        Utility.menubyID(6);
        Thread.sleep(5000);
        UiObject2 avalCredits =  Utility.device.findObject(By.res(PACKAGE_NAME, "txt_credits_available"));
        return avalCredits.getText();

    }
}
