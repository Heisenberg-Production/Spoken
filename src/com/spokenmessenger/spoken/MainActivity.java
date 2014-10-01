package com.spokenmessenger.spoken;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.spokenmessenger.spoken.fragments.ContactsFragment;
import com.spokenmessenger.spoken.fragments.RecentFragment;

public class MainActivity extends FragmentActivity {
	
	RecentFragment recentFragment;
	ContactsFragment contactsFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	// Show Recent Messages Fragment
	public void onTapRecentMenu(View v) {
		
	}
	
	// Show Contacts Fragment
	public void onTapContactsMenu(View v) {
		
	}
}
