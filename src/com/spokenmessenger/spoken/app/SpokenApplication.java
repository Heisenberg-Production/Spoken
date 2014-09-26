package com.spokenmessenger.spoken.app;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class SpokenApplication extends Application {
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		Parse.initialize(this, "R0Zrx6jdTEhzhdl3CVO2mG4cpHq5riEp4Ni6xkXh", "HjiyTtWKQAF7uz6wbnj1X2Cr9QgDerNdnrrQj2zF");
		
		ParseObject testObject = new ParseObject("TestObject");
		testObject.put("key", "value");
		testObject.saveInBackground();
	}
	

}
