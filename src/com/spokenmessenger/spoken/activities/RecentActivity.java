package com.spokenmessenger.spoken.activities;

import com.spokenmessenger.spoken.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class RecentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.recent, menu);
        return true;
    }
    
}
