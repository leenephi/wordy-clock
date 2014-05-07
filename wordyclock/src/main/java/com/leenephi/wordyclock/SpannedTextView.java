package com.leenephi.wordyclock;

import android.content.Context;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by smithsonln on 5/6/14.
 */
public class SpannedTextView extends TextView {
    public SpannedTextView(Context context) {
        super(context);
    }

    public SpannedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SpannedTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    // Basically a copy of getUrls() from the original TextView source
    public ForegroundColorSpan[] getForegroundColorSpans() {
        CharSequence text = this.getText();
        if (text instanceof Spanned) {
            return ((Spanned) text).getSpans(0, text.length(), ForegroundColorSpan.class);
        } else {
            return new ForegroundColorSpan[0];
        }
    }

    // Basically a copy of getUrls() from the original TextView source
    public ClickableSpan[] getClickableSpans() {
        CharSequence text = this.getText();
        if (text instanceof Spanned) {
            return ((Spanned) text).getSpans(0, text.length(), ClickableSpan.class);
        } else {
            return new ClickableSpan[0];
        }
    }

    public NonUnderlinedClickableSpan[] getNonUnderlinedClickableSpans() {
        CharSequence text = this.getText();
        if (text instanceof Spanned) {
            return ((Spanned) text).getSpans(0, text.length(), NonUnderlinedClickableSpan.class);
        } else {
            return new NonUnderlinedClickableSpan[0];
        }
    }
}
