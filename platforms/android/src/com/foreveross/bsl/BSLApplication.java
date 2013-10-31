package com.foreveross.bsl;

import java.util.HashMap;

import android.content.res.Configuration;

public class BSLApplication extends android.app.Application{

	private HashMap<String, BSLModule> modules = new HashMap<String, BSLModule>();

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		for(BSLModule m : modules.values()){
			m.onConfigurationChanged(newConfig);
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();

		for(BSLModule m : modules.values()){
			m.onCreate();
		}
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		for(BSLModule m : modules.values()){
			m.onLowMemory();
		}
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		for(BSLModule m : modules.values()){
			m.onTerminate();
		}
	}
	
}
