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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.cordova.CordovaActivity;
import org.apache.http.util.EncodingUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.foreveross.bsl.model.Modules;

public class bslsdk extends CordovaActivity {
	ListView listview;
	List<String> data = new ArrayList<String>();
	ArrayList<Modules> addmodules = new ArrayList<Modules>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// super.init();
		// Set by <content src="index.html" /> in config.xml
		// super.loadUrl(Config.getStartUrl());
		// super.loadUrl("file:///android_asset/www/index.html")

		setContentView(R.layout.activity_main);
		ArrayList<Modules> modules = buildBSLModule();
		listview = (ListView) findViewById(R.id.listView1);
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//				android.R.layout.simple_list_item_1, data);
		
		


		
		for (Modules module : modules) {
			Class<?> clazz = null;
			try {
				clazz = Class.forName(module.getPackagename() + "."
						+ module.getName());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (clazz != null) {
				data.add(module.getPackagename() + "." + module.getName());
				addmodules.add(module);
			}
		}
		
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.list_item,
                R.id.tv,
                data);

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

	/**
	 * 从assets 文件夹中获取文件并读取数据
	 * 
	 * @param fileName
	 * @return
	 */
	public String getFromAssets(String fileName) {
		String result = "";
		try {
			InputStream in = this.getResources().getAssets().open(fileName);
			// 获取文件的字节数
			int lenght = in.available();
			// 创建byte数组
			byte[] buffer = new byte[lenght];
			// 将文件中的数据读到byte数组中
			in.read(buffer);
			result = EncodingUtils.getString(buffer, "ENCODING");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private ArrayList<Modules> buildBSLModule() {
		// 读取JSON，并进行反射
		String result = getFromAssets("bsl.json").trim();
		ArrayList<Modules> modules = null;
		try {
			JSONObject json = new JSONObject(result);
			JSONArray jay = json.getJSONArray("modules");
			modules = new ArrayList<Modules>();
			for (int i = 0; i < jay.length(); i++) {
				Modules module = new Modules();
				JSONObject jb = (JSONObject) jay.get(i);
				String identifier = (String) jb.get("identifier");
				String name = (String) jb.get("name");
				String packagename = (String) jb.get("packagename");
				module.setIdentifier(identifier);
				module.setName(name);
				module.setPackagename(packagename);
				modules.add(module);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modules;
	}
}
