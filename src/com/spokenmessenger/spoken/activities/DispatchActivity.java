package com.spokenmessenger.spoken.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.spokenmessenger.spoken.WelcomeActivity;
import com.spokenmessenger.spoken.models.User;

public class DispatchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_dispatch);
		showActivity();
	}
	
	// Show main app if user is logged in, otherwise show login screen
	private void showActivity() {
		if (User.getCurrentUser().pUser != null) {
			// TODO: Read accounts for credentials and attempt to log in first
			startActivity(new Intent(this, MainActivity.class));
		} else {
			startActivity(new Intent(this, WelcomeActivity.class));
		}
		
//		startActivity(new Intent(this, MainActivity.class));
	}
	
	
}
