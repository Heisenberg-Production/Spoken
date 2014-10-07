package com.spokenmessenger.spoken.ui;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

	private Camera camera;
	private SurfaceHolder holder;

	public CameraPreview(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CameraPreview(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CameraPreview(Context context) {
		super(context);
	}

	public void init(Camera camera) {
		this.camera = camera;
		initSurfaceHolder();
	}

	@SuppressWarnings("deprecation") // needed for < 3.0
	private void initSurfaceHolder() {
		holder = getHolder();
		holder.addCallback(this);
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		initCamera(holder);
	}

	private void initCamera(SurfaceHolder holder) {
		try {
			Camera.Parameters p = camera.getParameters();
			p.setRotation(90);
			camera.setParameters(p);
			camera.setPreviewDisplay(holder);
			camera.startPreview();
			camera.setDisplayOrientation(90);
		} catch (Exception e) {
			Log.d("Error setting camera preview", e.getMessage());
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d("debug", "srufaceDestroyed");
		camera.stopPreview();
        camera.setPreviewCallback(null); 
        camera.release();
        camera = null;
	}

}
