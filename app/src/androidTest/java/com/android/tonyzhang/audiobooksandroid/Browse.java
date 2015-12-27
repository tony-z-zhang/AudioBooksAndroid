package com.android.tonyzhang.audiobooksandroid;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import java.util.Random;

/**
 * Created by tonyzhang on 12/26/15.
 */
public class Browse {
    private final static String LOG_TAG = Utility.class.getSimpleName();

    private static String[] book_categories = {
            "Free Books",
            "Book Lists and Bestsellers",
            "Biographies",
            "Comedy",
            "Fiction",
            "History",
            "Horror",
            "Mystery & Thriller",
            "Sports"};

    public static void randombrowse() throws UiObjectNotFoundException {

        Random randomGenerator = new Random();
        int descnumber = randomGenerator.nextInt(book_categories.length-1);

        UiScrollable browseScrollView = new UiScrollable(new UiSelector()
                .className("android.widget.ListView").resourceId("com.audiobooks.androidapp:id/list"));

        browseScrollView.scrollTextIntoView(book_categories[descnumber]);
        browseScrollView.waitForExists(3000);

        UiObject randombrowse = browseScrollView.getChildByText(new UiSelector()
                .className("android.widget.LinearLayout"), book_categories[descnumber]);

        if (randombrowse!=null) {
            randombrowse.click();
        }else{Utility.dumpLog(LOG_TAG, "The object not found. Please check your code");}
    }
}
