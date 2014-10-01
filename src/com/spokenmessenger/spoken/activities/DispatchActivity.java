package com.spokenmessenger.spoken.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.spokenmessenger.spoken.MainActivity;
import com.spokenmessenger.spoken.models.User;

public class DispatchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_dispatch);
		if (User.getCurrentUser().pUser != null) {
			// Start an intent for the logged in activity
			startActivity(new Intent(this, MainActivity.class));
			} else {
			// Start and intent for the logged out activity
//			startActivity(new Intent(this, WelcomeActivity.class));
		}
		
		startActivity(new Intent(this, MainActivity.class));

	}
	
	
}
