package com.android.tonyzhang.audiobooksandroid;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;

/**
 * Created by tzhang on 12/3/15.
 */
public class AB003_Login extends InstrumentationTestCase {
    private static final String PACKAGE_NAME = "com.audiobooks.androidapp";
    public void testLoginHappyPath() throws UiObjectNotFoundException, InterruptedException {
        try{
            Utility.findAndRunApp();
            Thread.sleep(10000);
            Utility.userLogin("tony.zhang@gm.com", "Abc123");
            Thread.sleep(5000);


            //verify user directed to my books screen
            UiObject2 purchasedTab =  Utility.device.findObject(By.res(PACKAGE_NAME, "button1"));
            UiObject2 headerTitle =  Utility.device.findObject(By.text("My Books").clazz("android.widget.TextView"));
            assertEquals("Purchased", purchasedTab.getText());
            assertEquals(true, headerTitle != null);


            //verify there is credit label under settings
            Utility.menu(5);
            Thread.sleep(5000);
            UiObject2 avalCredits =  Utility.device.findObject(By.res(PACKAGE_NAME, "txt_credits_available"));
            assertEquals("8", avalCredits.getText());
            Thread.sleep(5000);


            //verify if user can logout
            Utility.userLogout();
            Thread.sleep(5000);



        }catch(UiObjectNotFoundException e){
            Utility.dumpLog(Utility.TEST_LOG_TAG, String.valueOf(e));
        }
    }

    public void testLoginNegative() throws UiObjectNotFoundException, InterruptedException {
        try{
            Utility.findAndRunApp();
            Thread.sleep(5000);
            Utility.userLogin("tony.zhang@gm.com", "Abc12");
            Thread.sleep(5000);


            UiObject2 okBtn =  Utility.device.findObject(By.res(PACKAGE_NAME, "button_1"));
            UiObject2 dialogMsg =  Utility.device.findObject(By.res(PACKAGE_NAME, "dialog_message"));
            assertEquals("No match found for the supplied email address / password.", dialogMsg.getText());
            okBtn.click();


        }catch(UiObjectNotFoundException e){
            Utility.dumpLog(Utility.TEST_LOG_TAG, String.valueOf(e));
        }
    }

}
