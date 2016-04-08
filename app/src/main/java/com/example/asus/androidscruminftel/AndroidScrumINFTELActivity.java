package com.example.asus.androidscruminftel;

import android.app.Application;

import com.google.android.gms.common.api.GoogleApiClient;


public class AndroidScrumINFTELActivity extends Application {

    private GoogleApiClient mGoogleApiClient;
    private static AndroidScrumINFTELActivity mInstance;
    private String userName;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

    }

    public GoogleApiClient getmGoogleApiClient() {
        return mGoogleApiClient;
    }

    public void setmGoogleApiClient(GoogleApiClient mGoogleApiClient) {
        this.mGoogleApiClient = mGoogleApiClient;
    }

    public static synchronized AndroidScrumINFTELActivity getInstance() {
        return mInstance;
    }
}
