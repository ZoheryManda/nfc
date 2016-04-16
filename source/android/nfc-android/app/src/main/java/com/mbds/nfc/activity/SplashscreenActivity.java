package com.mbds.nfc.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;

import com.mbds.nfc.R;


public class SplashscreenActivity extends Activity {

    private static int splashInterval = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splashscreen);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SplashscreenActivity.this);
        prefs.edit().putString("wsurl", "192.168.1.64:8080").commit();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashscreenActivity.this, MainActivity.class);
                startActivity(i);

                this.finish();
            }

            private void finish() {
                // TODO Auto-generated method stub

            }
        }, splashInterval);

    }
}
