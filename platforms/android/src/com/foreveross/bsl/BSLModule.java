package com.foreveross.bsl;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;

/**
 * BSL module base class
 * @author justin
 *
 */
public class BSLModule {

	private String identifier;
	private String name;
	private String packagename;
	
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

	public String getPackagename() {
		return packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}
	
	public Fragment getFragment(){
		return null;
	}
	
	public Activity getActivity(){
		return null;
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
