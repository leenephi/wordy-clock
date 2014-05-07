package com.leenephi.wordyclock;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Created by smithsonln on 5/6/14.
 */
public class NonUnderlinedClickableSpan extends ClickableSpan {

    private int mColor;

    public NonUnderlinedClickableSpan() {
        super();
    }

    @Override
    public void onClick(View widget) {

    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(mColor);
        ds.setUnderlineText(false);
    }

    public void setColor(String color) {
        mColor = Color.parseColor(color);
    }
}
