package com.foreveross.bsl;

import java.io.InputStream;
import java.util.HashMap;

import org.apache.http.util.EncodingUtils;

import android.content.res.Configuration;

public class BSLApplication extends android.app.Application{

	private HashMap<String, BSLModule> modules = new HashMap<String, BSLModule>();
	
	public BSLApplication() {
		//
	}

	public String getFromAssets(String fileName) {
		String result = "";
		try {
			InputStream in = this.getResources().getAssets().open(fileName);
			int lenght = in.available();
			byte[] buffer = new byte[lenght];
			in.read(buffer);
			result = EncodingUtils.getString(buffer, "ENCODING");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

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
