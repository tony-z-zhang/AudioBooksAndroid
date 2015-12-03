package com.android.tonyzhang.audiobooksandroid;

import android.app.UiAutomation;
import android.content.Context;
import android.content.Intent;
import android.os.ParcelFileDescriptor;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by tonyzhang on 11/22/15.
 */
public class Utility {

    private final static String LOG_TAG = Utility.class.getSimpleName();
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

        device.findObject(By.descContains("Search").clazz("android.widget.TextView")).click();
        insertText(searchtxt);

    }

    //only Utility or any class in the same package can access the protected method
    protected static void insertText(String searchterms) throws UiObjectNotFoundException {

        UiObject editText = device.findObject(new UiSelector().className("android.widget.EditText"));
        if(editText.getText().length() != 0){
            editText.clearTextField();
        }
        editText.setText(searchterms);
        device.pressEnter();//simulate the search button in keyboard
    }


    public static void menu(int index) throws UiObjectNotFoundException{

        device.findObject(By.descContains("Menu Open").clazz("android.widget.ImageButton")).click();

        UiScrollable menuScrollView = new UiScrollable(new UiSelector()
                .className("android.widget.ScrollView"));

/*
        index = 0: Featured
        index = 1: Browse
        index = 2: My Books
        index = 4: Sign Up
        index = 5: Login
        index = 6: Settings
        index = 8: Customer Service
*/

        UiObject menuitem = menuScrollView.getChild(new UiSelector()
                .className("android.widget.LinearLayout")).getChild(new UiSelector().index(index));

        if (menuitem.isClickable()) {
            menuitem.click();
        }else{dumpLog(LOG_TAG, "The object is not clickable. Please check your code");}

    }


    public static void myBooks() throws UiObjectNotFoundException{

        device.findObject(By.descContains("Menu Open").clazz("android.widget.ImageButton")).click();

        UiScrollable menuScrollView = new UiScrollable(new UiSelector()
                .className("android.widget.ScrollView"));

        UiObject myBooks = menuScrollView.getChild(new UiSelector()
                .className("android.widget.LinearLayout")).getChild(new UiSelector().index(2));

//        UiObject oneLevelDown = menuScrollView.getChild(new UiSelector()
//                .className("android.widget.LinearLayout"));
//        UiObject myBooks = oneLevelDown.getChild(new UiSelector().index(2));

//        UiObject mybookText = myBooks.getChild(new UiSelector().className("android.widget.TextView"));
//        Log.i(TEST_LOG_TAG, "===This is a test===" + mybookText.getText());

//        device.waitForWindowUpdate(PACKAGE_NAME, 5000);

        if (myBooks.isClickable()) {
            myBooks.click();
        }else{dumpLog(LOG_TAG, "The object is not clickable. Please check your code");}

    }


    protected static String getStringFromShellCmd(String cmd){
        ParcelFileDescriptor pfd = null;
        FileDescriptor fd = null;
        String s = null;
        String result = "";
        try {
            pfd = uia.executeShellCommand(cmd);
            fd = pfd.getFileDescriptor();
            InputStream is= new BufferedInputStream(new FileInputStream(fd));
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(is));

            // read the output from the cmd
            while ((s = stdInput.readLine()) != null) {
                result = result + s + "\n";
            }
            //close streams and buffer
            is.close();
            pfd.close();
        } catch (IOException e) {
            dumpLog(LOG_TAG, "exception here's what I know: ");
            e.printStackTrace();
            dumpLog(LOG_TAG, "Exception occurred");

        }
        return result;
    }

    public static void userLogin(String username, String password) throws UiObjectNotFoundException, InterruptedException{

        menu(5);
        Thread.sleep(2000);
        UiObject2 userField = device.findObject(By.res(PACKAGE_NAME, "txtUser"));
        if(userField.getText()!=null){
            userField.clear();
        }
        Thread.sleep(1000);
        userField.setText(username);

        UiObject2 pwdField = device.findObject(By.res(PACKAGE_NAME, "txtPassword"));
        if(pwdField.getText()!=null){
            pwdField.clear();
        }
        Thread.sleep(1000);
        pwdField.setText(password);

        Thread.sleep(1000);

        device.findObject(By.res(PACKAGE_NAME, "btnLogin")).click();
    }


    public static void userLogout() throws UiObjectNotFoundException, InterruptedException{

//      For some reason
//      Automating menu click twice after login in one testfunc doesn't work properly
//      Such as click settings and then click logout
//      If that's the case, it has to re-locate the element by res, like the code below

//        device.findObject(By.descContains("Menu Open").clazz("android.widget.ImageButton")).click();
//        Thread.sleep(2000);
//        Utility.device.findObject(By.res(PACKAGE_NAME, "menu_logout")).click();

        menu(4);
        Thread.sleep(5000);
        device.findObject(By.res(PACKAGE_NAME, "button_1")).click();

    }


    protected static void dumpLog(String log_tag, String log){
        Log.i(log_tag, log);}
}
