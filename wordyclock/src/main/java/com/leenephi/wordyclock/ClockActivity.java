package com.leenephi.wordyclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;


public class ClockActivity extends RoboActivity implements GestureDetector.OnDoubleTapListener{

    private BroadcastReceiver mMinuteReceiver;

    @InjectView (R.id.wordy_view) private SpannedTextView mWordyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        Typeface montserratRegular = Typeface.createFromAsset(
                getAssets(), "fonts/montserrat_regular.ttf");
        mWordyView.setTypeface(montserratRegular);

        mMinuteReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().compareTo(Intent.ACTION_TIME_TICK) == 0) {
                    updateWords();
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateWords();
        this.registerReceiver(mMinuteReceiver, new IntentFilter(Intent.ACTION_TIME_TICK));
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mMinuteReceiver != null) {
            this.unregisterReceiver(mMinuteReceiver);
        }
    }



    private void updateWords() {
        mWordyView.setText(Wordy.getWords());
//        ClickableSpan spans[] = mWordyView.getNonUnderlinedClickableSpans();
//        Log.i("NonClickableSpans: ", String.valueOf(spans.length));
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
