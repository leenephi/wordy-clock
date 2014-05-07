package com.leenephi.wordyclock;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Created by smithsonln on 5/6/14.
 */
public class NonUnderlinedClickableSpan extends ClickableSpan {

    @Override
    public void onClick(View widget) {

    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(Color.parseColor("#D95B43"));
        ds.setUnderlineText(false);
    }
}
