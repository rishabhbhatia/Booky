package com.bookyard.booky;

import android.app.Application;

import com.facebook.FacebookSdk;

/**
 * Created by Rishabh Bhatia on 6/11/2016.
 */
public class BookyApplication extends Application {

    public static String quoteOfDay = "";

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize the SDK before executing any other operations,
        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
