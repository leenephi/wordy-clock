package com.leenephi.wordyclock;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.bluejamesbond.textjustify.TextViewEx;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;


public class ClockActivity extends RoboActivity {

    @InjectView (R.id.wordy_view)   TextViewEx mWordyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        Typeface montserratRegular = Typeface.createFromAsset(
                getAssets(), "fonts/montserrat_regular.ttf");
        mWordyView.setTypeface(montserratRegular);

        mWordyView.setText(Wordy.getWordy());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.clock, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
