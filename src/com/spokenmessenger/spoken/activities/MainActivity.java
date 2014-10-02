package com.spokenmessenger.spoken.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.spokenmessenger.spoken.R;
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
	public void onTapRecentTab(View v) {
		if (recentFragment == null) {
			recentFragment = new RecentFragment();
		}
		Log.d("debug", "onTapRecentTab");
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction ft = manager.beginTransaction();
		ft.replace(R.id.flMainContainer, recentFragment);
		ft.commit();
		
	}
	
	// Show Contacts Fragment
	public void onTapContactsTab(View v) {
		if (contactsFragment == null) {
			contactsFragment = new ContactsFragment();
		}
		Log.d("debug", "onTapContactsTab");
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction ft = manager.beginTransaction();
		ft.replace(R.id.flMainContainer, contactsFragment);
		ft.commit();
	}
}
