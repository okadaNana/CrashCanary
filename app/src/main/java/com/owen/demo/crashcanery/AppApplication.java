package com.owen.demo.crashcanery;

import android.app.Application;

import com.owen.crashcanary.CrashCanary;


public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashCanary.install(this);
    }
}
