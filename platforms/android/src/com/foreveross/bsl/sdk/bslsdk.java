/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.foreveross.bsl.sdk;

import java.util.ArrayList;
import java.util.List;

import org.apache.cordova.CordovaActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.foreveross.bsl.BSLApplication;
import com.foreveross.bsl.BSLModule;

public class bslsdk extends CordovaActivity {
	ListView listview;
	List<String> data = new ArrayList<String>();
	ArrayList<BSLModule> addmodules = new ArrayList<BSLModule>();
	BSLApplication application;

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.bsl_main);
		application = BSLApplication.class.cast(bslsdk.this.getApplication());
		listview = (ListView) findViewById(R.id.listView1);

		for (BSLModule module : application.getModules().values()) {
			data.add(module.getPackage() + "." + module.getName());
			addmodules.add(module);
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.bsl_list_item, R.id.tv, data);

		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long id) {
				String className = data.get(position);
				Intent intent = new Intent();
				intent.setClassName(bslsdk.this, className);
				bslsdk.this.startActivity(intent);
			}
		});

	}
}
