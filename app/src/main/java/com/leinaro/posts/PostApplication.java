package com.leinaro.posts;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class PostApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
