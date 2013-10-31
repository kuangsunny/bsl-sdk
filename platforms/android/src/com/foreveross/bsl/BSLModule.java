package com.foreveross.bsl;

import android.content.res.Configuration;

/**
 * BSL module base class
 * @author justin
 *
 */
public class BSLModule {

	private String identifier;
	private String name;
	private String packageName;
	
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPackage() {
		return packageName;
	}

	public void setPackage(String packageName) {
		this.packageName = packageName;
	}
	
	//life cycle
	public void onConfigurationChanged(Configuration newConfig) {
		
	}

	public void onCreate() {
		
	}

	public void onLowMemory() {
		
	}

	public void onTerminate() {
		
	}

}
