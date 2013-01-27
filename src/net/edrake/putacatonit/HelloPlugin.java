package net.edrake.putacatonit;

//import com.android.Log
import org.apache.cordova.api.Plugin;
import org.apache.cordova.api.PluginResult;
import org.json.JSONArray;

public class HelloPlugin extends Plugin {
	public static final String NATIVE_ACTION_STRING="nativeAction";       
	public static final String SUCCESS_PARAMETER="success";       
	
	@Override       
	public PluginResult execute(String action, JSONArray data, String callbackId) {              
		//Log.d("HelloPlugin", "Hello, this is a native function called from PhoneGap/Cordova!");
		//only perform the action if it is the one that should be invoked
		if (NATIVE_ACTION_STRING.equals(action)) {
			String resultType = null;
			try {
				resultType = data.getString(0);
			}
			catch (Exception ex) {
				//Log.d("HelloPlugin", ex.toString());
			}
			if (resultType.equals(SUCCESS_PARAMETER)) {
				return new PluginResult(PluginResult.Status.OK, "Yay, Success!!!");
			}
			else {
				return new PluginResult(PluginResult.Status.ERROR, "Oops, Error :(");
			}              
		}
		return null;       
	}
}

