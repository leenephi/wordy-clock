package com.leenephi.wordyclock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by smithsonln on 4/29/14.
 */
public class WordyView extends View {

    private Paint mDefault;
    private Context mContext;

    public WordyView(Context context) {
        super(context);
        initialize(context);
    }

    public WordyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public WordyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    private void initialize(Context context) {
        // set up paint objects
        mDefault = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDefault.setColor(Color.parseColor("#555555"));

        // get density independent pixel value
        int textSizePixels = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 20,getResources().getDisplayMetrics());
        mDefault.setTextSize(textSizePixels);

        Typeface montserratRegular = Typeface.createFromAsset(
                context.getAssets(), "fonts/montserrat_regular.ttf");
        mDefault.setTypeface(montserratRegular);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText(Wordy.getDayWord(23), 50, 50, mDefault);
    }
}
