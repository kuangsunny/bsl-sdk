package com.foreveross.bsl;

import java.io.InputStream;
import java.util.HashMap;

import org.apache.http.util.EncodingUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.res.Configuration;

public class BSLApplication extends android.app.Application {

	private HashMap<String, BSLModule> modules = new HashMap<String, BSLModule>();

	public BSLApplication() {
		//
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		buildBSLModule();
		for (BSLModule m : modules.values()) {
			m.onConfigurationChanged(newConfig);
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		buildBSLModule();
		for (BSLModule m : modules.values()) {
			m.onCreate();
		}
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		for (BSLModule m : modules.values()) {
			m.onLowMemory();
		}
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		for (BSLModule m : modules.values()) {
			m.onTerminate();
		}
	}

	private void buildBSLModule() {
		String result = getFromAssets("bsl.json").trim();
		try {
			JSONObject json = new JSONObject(result);
			JSONArray jay = json.getJSONArray("modules");
			for (int i = 0; i < jay.length(); i++) {
				BSLModule module = new BSLModule();
				JSONObject jb = (JSONObject) jay.get(i);
				String identifier = (String) jb.get("identifier");
				String name = (String) jb.get("name");
				String packagename = (String) jb.get("package");
				module.setIdentifier(identifier);
				module.setName(name);
				module.setPackage(packagename);

				Class<?> clazz = null;
				try {
					clazz = Class.forName(module.getPackage() + "."
							+ module.getName());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (clazz != null) {
					modules.put(identifier, module);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public HashMap<String, BSLModule> getModules() {
		return modules;
	}
}
