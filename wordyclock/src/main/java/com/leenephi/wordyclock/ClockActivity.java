package com.leenephi.wordyclock;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;


public class ClockActivity extends RoboActivity implements GestureDetector.OnDoubleTapListener{

    private Runnable mTimer;

    @InjectView (R.id.wordy_view) private SpannedTextView mWordyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        Typeface montserratRegular = Typeface.createFromAsset(
                getAssets(), "fonts/montserrat_regular.ttf");
        mWordyView.setTypeface(montserratRegular);

        mTimer = new Runnable() {
            @Override
            public void run() {
                updateWords();
                mWordyView.postDelayed(this, Wordy.getMillisLeft());
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateWords();
        mWordyView.postDelayed(mTimer, Wordy.getMillisLeft());
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWordyView.removeCallbacks(mTimer);
    }



    private void updateWords() {
        mWordyView.setText(Wordy.getWords());
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

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        // stuff!

        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }
}
