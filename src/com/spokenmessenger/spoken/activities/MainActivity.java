package com.spokenmessenger.spoken.activities;

import java.io.File;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.spokenmessenger.spoken.ActionActivity;
import com.spokenmessenger.spoken.R;
import com.spokenmessenger.spoken.fragments.ContactsFragment;
import com.spokenmessenger.spoken.fragments.RecentFragment;

public class MainActivity extends FragmentActivity {
	
	public static final String APP_TAG = "Spoken";
	private static final int ACTION_VIEW_REQUEST_CODE = 123;
	public static final String TEMP_PHOTO_FILE = "spoken-temp.jpg";
	
	RecentFragment recentFragment;
	ContactsFragment contactsFragment;
	
	ImageView recentTab;
	ImageView contactsTab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if(cameraNotDetected()){
        	Log.d("debug", "No camera detected, clicking the button below will have unexpected behaviour.");
        } else {
        	Log.d("debug", "Camera detected");
        }
		
		recentTab = (ImageView) findViewById(R.id.ivRecentTab);
		contactsTab = (ImageView) findViewById(R.id.ivContactsTab);
	}
	
	
	private boolean cameraNotDetected() {
		return !getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
	}
	
	// Show Recent Messages Fragment
	public void onTapRecentTab(View v) {
		recentTab.setBackgroundColor(Color.parseColor("#447a3d"));
		recentTab.setImageResource(R.drawable.btn_recent_green);
		contactsTab.setImageResource(R.drawable.btn_contact_off);
		contactsTab.setBackgroundColor(Color.parseColor("#292929"));
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
		// Change background image and color for both buttons
		recentTab.setBackgroundColor(Color.parseColor("#292929"));
		recentTab.setImageResource(R.drawable.btn_recent_android_grey);
		contactsTab.setImageResource(R.drawable.btn_contact_android_blue);
		contactsTab.setBackgroundColor(Color.parseColor("#00abfa"));
		
		if (contactsFragment == null) {
			contactsFragment = new ContactsFragment();
		}
		Log.d("debug", "onTapContactsTab");
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction ft = manager.beginTransaction();
		ft.replace(R.id.flMainContainer, contactsFragment);
		ft.commit();
	}
	
	// Show Camera View
	public void onTapRedCircle(View v) {
//		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//	    intent.putExtra(MediaStore.EXTRA_OUTPUT, getPhotoFileUri(TEMP_PHOTO_FILE)); 
		Intent intent = new Intent(this, ActionActivity.class);
	    startActivityForResult(intent, ACTION_VIEW_REQUEST_CODE);
	}
	
	// Returns the Uri for a photo stored on disk given the fileName
	public Uri getPhotoFileUri(String fileName) {
	    // Get safe storage directory for photos
	    File mediaStorageDir = new File(
	        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), APP_TAG);

	    // Create the storage directory if it does not exist
	    if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
	        Log.d(APP_TAG, "failed to create directory");
	    }

	    // Return the file target for the photo based on filename
	    return Uri.fromFile(new File(mediaStorageDir.getPath() + File.separator + fileName));
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		if(requestCode == REQ_CAMERA_IMAGE && resultCode == RESULT_OK){
//			String imgPath = data.getStringExtra(CameraActivity.EXTRA_IMAGE_PATH);
//			Log.i("Got image path: "+ imgPath);
//			displayImage(imgPath);
//		} else
//		if(requestCode == REQ_CAMERA_IMAGE && resultCode == RESULT_CANCELED){
//			Log.i("User didn't take an image");
//		}
	}
}
