package com.spokenmessenger.spoken.util;

import android.hardware.Camera;
import android.util.Log;

public class CameraHelper {

	public static boolean cameraAvailable(Camera camera) {
		return camera != null;
	}

	public static Camera getCameraInstance() {
		Camera c = null;
		try {
			Log.d("debug", "Trying to open camera");
			c = Camera.open();
		} catch (Exception e) {
			// Camera is not available or doesn't exist
			Log.d("debug", "getCamera failed " + e.getMessage());
		}
		return c;
	}

}
