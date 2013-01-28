/**
 * 
 * Phonegap share plugin for Android
 * Kevin Schaul 2011
 *
 */

package com.schaul.plugins.share;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import org.apache.cordova.api.Plugin;
import org.apache.cordova.api.PluginResult;

public class Share extends Plugin {
	private static final String TAG = "Share";
	
	@Override
	public PluginResult execute(String action, JSONArray args, String callbackId) {
		try {
			JSONObject jo = args.getJSONObject(0);
			doSendIntent(jo.getString("subject"), jo.getString("fileName"), callbackId); 
			return new PluginResult(PluginResult.Status.OK);
		} catch (JSONException e) {
			return new PluginResult(PluginResult.Status.JSON_EXCEPTION);
		}
	}
	
	private void doSendIntent(String subject, String fileName, String callbackId) {
		Intent sendIntent = new Intent(android.content.Intent.ACTION_SEND);
		/*try {
			File folder = new File(Environment.getExternalStorageDirectory(), "Pictures");
			if (!folder.exists()) {
				folder.mkdirs();
			}
			File f = new File(folder, fileName);
			FileOutputStream fos = new FileOutputStream(f);
		}
		catch (IOException e) {
			this.success(new PluginResult(PluginResult.Status.IO_EXCEPTION, e.getMessage()), callbackId);
		}*/ 
		sendIntent.setType("image/png");
		String filePath = "file:///sdcard/Pictures/" + fileName;
		Log.i(TAG, "SENDINTENT.PUTEXTRA " + filePath);
		sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(filePath));

		//this.cordova.startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
		//sendIntent.setType("text/plain");
		//sendIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
		//sendIntent.putExtra(android.content.Intent.EXTRA_TEXT, text);
		this.cordova.startActivityForResult(this, sendIntent, 0);
	}
}
