/**
 * 
 * Phonegap share plugin for Android
 * Kevin Schaul 2011
 *
 */

package com.schaul.plugins.share;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.net.Uri;
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
		sendIntent.setType("image/png");
		String filePath = "file:///sdcard/Pictures/" + fileName;
		Log.i(TAG, "SENDINTENT.PUTEXTRA " + filePath);

		sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(filePath));
		sendIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
		this.cordova.startActivityForResult(this, sendIntent, 0);
	}
}
