package com.android.tonyzhang.audiobooksandroid;

import android.app.UiAutomation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.util.Log;

/**
 * Created by tonyzhang on 11/22/15.
 */
public class Utility {

    protected final static String TEST_LOG_TAG = "TEST INFO";

    protected static UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    protected static UiAutomation uia = InstrumentationRegistry.getInstrumentation().getUiAutomation();

    private static final String PACKAGE_NAME = "com.audiobooks.androidapp";
    private static final int LAUNCH_TIMEOUT = 5000;

    protected static void findAndRunApp() throws UiObjectNotFoundException {
        // Start from the home screen
        device.pressHome();
        // Launch the blueprint app
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(PACKAGE_NAME);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);    // Clear out any previous instances
        context.startActivity(intent);

        // Wait for the app to appear
        device.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)), LAUNCH_TIMEOUT);
    }

    public static void search(String searchtxt) throws UiObjectNotFoundException{

        Utility.device.findObject(By.descContains("Search").clazz("android.widget.TextView")).click();
        Utility.insertText(searchtxt);

    }
//
    protected static void insertText(String searchterms) throws UiObjectNotFoundException {

        UiObject editText = device.findObject(new UiSelector().className("android.widget.EditText"));
        if(editText.getText().length() != 0){
            editText.clearTextField();
        }
        editText.setText(searchterms);
        device.pressEnter();//simulate the search button in keyboard
    }

    protected static void dumpLog(String log_tag, String logg){
        Log.i(log_tag, logg);}
}
