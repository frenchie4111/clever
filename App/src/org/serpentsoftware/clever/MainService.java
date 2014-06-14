package org.serpentsoftware.clever;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MainService extends Service {
	String tag= "MainService";
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.v(tag, "Started!");
		
		return (START_STICKY);
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		Toast.makeText(this, "Service created...", Toast.LENGTH_LONG).show();      
		Log.i(tag, "Service created...");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "Service destroyed...", Toast.LENGTH_LONG).show();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
