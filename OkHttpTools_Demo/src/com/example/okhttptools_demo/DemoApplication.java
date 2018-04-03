package com.example.okhttptools_demo;

import com.hs.okhttptools.OkHttpApp;

import android.app.Application;

public class DemoApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		
		// TODO 初始化OkHttpTools
		OkHttpApp.init();
	}
}
