package com.ppar.shoutcastradio7;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	Button startButton/* , stopButton */;
	static Context context;
	boolean isPlaying;
	Intent streamService;

	SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		startButton = (Button) findViewById(R.id.startButton);
		// stopButton = (Button) findViewById(R.id.stopButton);
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		getPrefs();
		streamService = new Intent(MainActivity.this, StreamService.class);
		startButton.setText(R.string.play);
		startButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!isPlaying) {
					isPlaying = true;
					startService(streamService);
					startButton.setText(R.string.stop);
				} else {
					isPlaying = false;
					stopService(streamService);
					startButton.setText(R.string.play);
				}
				Log.d("MainActivity", ""+isPlaying);
			}
		});

		/*
		 * stopButton.setOnClickListener(new View.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub stopService(streamService); startButton.setEnabled(true); } });
		 */
	}
	
	@Override
	public void onResume () {
		super.onResume();
		setContentView(R.layout.activity_main);
		context = this;
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		getPrefs();
		startButton = (Button) findViewById(R.id.startButton);
		if (isPlaying) {
			startButton.setText(R.string.stop);
			
		}
		else {
			startButton.setText(R.string.play);
		}
		startButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!isPlaying) {
					isPlaying = true;
					startService(streamService);
					startButton.setText(R.string.stop);
				} else {
					isPlaying = false;
					stopService(streamService);
					startButton.setText(R.string.play);
				}
				Log.d("MainActivity", ""+isPlaying);
			}
		});

		Log.d(TAG, "onResume");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.d(TAG, "onPause");
	}



	public void getPrefs() {
		isPlaying = prefs.getBoolean("isPlaying",false);
		//if (isPlaying) startButton.setEnabled(false);
	}
	
}
