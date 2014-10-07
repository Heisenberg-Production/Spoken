package com.spokenmessenger.spoken;

import static com.spokenmessenger.spoken.util.CameraHelper.cameraAvailable;
import static com.spokenmessenger.spoken.util.CameraHelper.getCameraInstance;
import static com.spokenmessenger.spoken.util.MediaHelper.getOutputMediaFile;
import static com.spokenmessenger.spoken.util.MediaHelper.saveToFile;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.media.ExifInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.spokenmessenger.spoken.ui.CameraPreview;

public class ActionActivity extends Activity implements PictureCallback {

	protected static final String SPOKEN_EXTRA_IMAGE_PATH = "SPOKEN_EXTRA_IMAGE_PATH";

	private Camera camera;
	private CameraPreview cameraPreview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action);
		setResult(RESULT_CANCELED);
		// Camera may be in use by another activity or the system or not available at all
		camera = getCameraInstance();
		if(cameraAvailable(camera)){
			initCameraPreview();
			setupUI(findViewById(R.id.flActionView));
		} else {
			finish();
		}
	}
	
	public void setupUI(View view) {

	    //Set up touch listener for non-text box views to hide keyboard.
	    if(!(view instanceof EditText) || !(view instanceof Button)) {
	        view.setOnTouchListener(new OnTouchListener() {
	            public boolean onTouch(View v, MotionEvent event) {
	                hideSoftKeyboard(ActionActivity.this);
	                return false;
	            }
	        });
	    }

	    //If a layout container, iterate over children and seed recursion.
	    if (view instanceof ViewGroup) {
	        for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
	            View innerView = ((ViewGroup) view).getChildAt(i);
	            setupUI(innerView);
	        }
	    }
	}

	// Show the camera view on the activity
	private void initCameraPreview() {
		cameraPreview = (CameraPreview) findViewById(R.id.camera_preview);
		cameraPreview.init(camera);
	}

	public void onCaptureClick(View button){
		// Take a picture with a callback when the photo has been created
		// Here you can add callbacks if you want to give feedback when the picture is being taken
		camera.takePicture(null, null, this);
	}

	@Override
	public void onPictureTaken(byte[] data, Camera camera) {
		LinearLayout cameraBottomNav = (LinearLayout) findViewById(R.id.llCameraBottomNav);
		cameraBottomNav.setVisibility(View.VISIBLE);
		LinearLayout memeBottomNav = (LinearLayout) findViewById(R.id.llmemeBottomNav);
		memeBottomNav.setVisibility(View.VISIBLE);
		
		String path = savePictureToFileSystem(data);
		Log.d("debug", "path of saved picture: " + path);
		
		// Show the taken picture and allow meme drawing
		setUpMemeEditor(path);
		
//		setResult(path);
//		finish();
	}
	
	private void setUpMemeEditor(String path) {
        Bitmap takenImage = rotateBitmapOrientation(path);
//        Bitmap takenImage = BitmapFactory.decodeFile(path);
		CameraPreview cameraPreview = (CameraPreview) findViewById(R.id.camera_preview);
		cameraPreview.setVisibility(View.GONE);
		ImageView imageView = (ImageView) findViewById(R.id.ivtakenPhoto);
		imageView.setVisibility(View.VISIBLE);
		imageView.setImageBitmap(takenImage);
	}
	
	public Bitmap rotateBitmapOrientation(String file) {
	    // Create and configure BitmapFactory
	    BitmapFactory.Options bounds = new BitmapFactory.Options();
	    bounds.inJustDecodeBounds = true;
	    BitmapFactory.decodeFile(file, bounds);
	    BitmapFactory.Options opts = new BitmapFactory.Options();
	    Bitmap bm = BitmapFactory.decodeFile(file, opts);
	    CameraPreview cp = (CameraPreview) findViewById(R.id.camera_preview);
	    bm = Bitmap.createScaledBitmap(bm, cp.getWidth(), cp.getHeight(), true);
	    // Read EXIF Data
	    ExifInterface exif;
		try {
			exif = new ExifInterface(file);
		    String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
		    int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;
		    int rotationAngle = 0;
		    if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
		    if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
		    if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;
		    // Rotate Bitmap
		    Matrix matrix = new Matrix();
		    matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
		    Bitmap rotatedBitmap = Bitmap.createBitmap(bm, 0, 0, cp.getWidth(), cp.getHeight(), matrix, true);
		    // Return result
		    return rotatedBitmap;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	private static String savePictureToFileSystem(byte[] data) {
		File file = getOutputMediaFile();
		Log.d("debug", data.toString());
		saveToFile(data, file);
		return file.getAbsolutePath();
	}

	private void setResult(String path) {
		Intent intent = new Intent();
		intent.putExtra(SPOKEN_EXTRA_IMAGE_PATH, path);
		setResult(RESULT_OK, intent);
	}
	
	public void onTopClick(View v) {
		Log.d("debug", "onTopClick");
		EditText topText = (EditText) findViewById(R.id.etTop);
		topText.setFocusableInTouchMode(true);
		topText.requestFocus();
		showSoftKeyboard(ActionActivity.this, topText);
	}
	
	public void onBottomClick(View v) {
		Log.d("debug", "onBottomClick");
		EditText bottomText = (EditText) findViewById(R.id.etBottom);
		bottomText.setFocusableInTouchMode(true);
		bottomText.requestFocus();
		showSoftKeyboard(ActionActivity.this, bottomText);
	}
	
	public static void hideSoftKeyboard(Activity activity) {
	    InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
	    inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
	}
	
	public static void showSoftKeyboard(Activity activity, EditText et) {
	    InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
	    inputMethodManager.showSoftInput(et, 0);
	}

	// ALWAYS remember to release the camera when you are finished
	@Override
	protected void onPause() {
		super.onPause();
		releaseCamera();
	}

	private void releaseCamera() {
		if(camera != null){
			camera.release();
			camera = null;
		}
	}
}
