package com.oxi.bigu.controller;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.FacebookSdk;

public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        FacebookSdk.sdkInitialize(getApplicationContext());
        Log.i("signature", FacebookSdk.getApplicationSignature(getApplicationContext()));
    }

    public static Context getContext() {
        return mContext;
    }
}

