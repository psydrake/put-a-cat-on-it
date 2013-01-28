/**
 * Copyright (C) 2012 30ideas (http://30ide.as)
 * MIT licensed
 * 
 * @author Josemando Sobral
 * @created Jul 2nd, 2012.
 */
package org.apache.cordova;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.cordova.api.Plugin;
import org.apache.cordova.api.PluginResult;
import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.util.Base64;
import android.view.View;
import android.util.Log;

public class Screenshot extends Plugin {
	private static final String TAG = "Screenshot";
	private String fileName = "putacatonit.png";

	@Override
	public PluginResult execute(String action, JSONArray args, String callbackId) {
		Log.i(TAG, "In execute(). action: " + action + ", args: " + args + ", callbackId: " + callbackId);
		try {
			fileName = args.getString(0);
			Log.i(TAG, "fileName: " + fileName);
		}
		catch (Exception e) {
			 Log.e("Screenshot error", e.toString());
		}
		
		// starting on ICS, some WebView methods
		// can only be called on UI threads
		final Plugin that = this;
		final String id = callbackId;
		super.cordova.getActivity().runOnUiThread(new Runnable() {
			//@Override
			@TargetApi(Build.VERSION_CODES.FROYO)
			public void run() {
				Log.i(TAG, "run()");
				View view = webView.getRootView();
				view.setDrawingCacheEnabled(true);
				Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
				view.setDrawingCacheEnabled(false);

				try {
					File folder = new File(Environment.getExternalStorageDirectory(), "Pictures");
					if (!folder.exists()) {
						folder.mkdirs();
					}
					File f = new File(folder, fileName);
					FileOutputStream fos = new FileOutputStream(f);
					bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);

					//ByteArrayOutputStream baos = new ByteArrayOutputStream();
					//bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
					//byte[] image= baos.toByteArray();
					//Log.i(TAG, "byte array: " + image);
					//String imgStr = Base64.encodeToString(image, 0);
					//if (imgStr != null) {
					//	Log.i(TAG, "string length: " + imgStr.length());
					//}
					//that.success(new PluginResult(PluginResult.Status.OK), id);
					that.success(new PluginResult(PluginResult.Status.OK, fileName), id);
				} 
				catch (IOException e) {
					that.success(new PluginResult(PluginResult.Status.IO_EXCEPTION, e.getMessage()), id);
				}
			}
		});
		
		Log.i(TAG, "1. About to return NO_RESULT");
		PluginResult result = new PluginResult(PluginResult.Status.NO_RESULT);
		result.setKeepCallback(true);
		Log.i(TAG, "2. About to return NO_RESULT");		
		return result;
	}
}
