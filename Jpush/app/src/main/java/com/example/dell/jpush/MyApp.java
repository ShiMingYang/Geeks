package com.example.dell.jpush;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Dell on 2019/4/9.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
