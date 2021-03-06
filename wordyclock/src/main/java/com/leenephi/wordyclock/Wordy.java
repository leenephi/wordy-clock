package com.leenephi.wordyclock;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import java.util.Calendar;

/**
 * Created by leenephi on 4/28/14.
 */
public class Wordy {

    private static final String[] MONTHS = {
            "january", "february", "march", "april", "may",
            "june", "july", "august", "september", "october",
            "november", "december"
    };

    private static final String[] DAYS = {
            "first", "second", "third", "fourth", "fifth",
            "sixth", "seventh", "eighth", "ninth", "tenth",
            "eleventh", "twelfth", "thirteenth", "fourteenth", "fifteenth",
            "sixteenth", "seventeenth", "eighteenth", "nineteenth", "twentieth",
            "twenty-first", "twenty-second", "twenty-third", "twenty-fourth", "twenty-fifth",
            "twenty-sixth", "twenty-seventh", "twenty-eighth", "twenty-ninth", "thirtieth",
            "thirty-first"
    };

    private static final String[] DAYS_OF_WEEK = {
            "sunday", "monday", "tuesday", "wednesday",
            "thursday", "friday", "saturday"
    };

    private static final String[] HOURS = {
            "one", "two", "three", "four", "five",
            "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve"
    };

    private static final String OCLOCK = "o'clock";

    private static final String[] MINUTES = {
            "o'one", "o'two", "o'three", "o'four", "o'five",
            "o'six", "o'seven", "o'eight", "o'nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen",
            "sixteen", "seventeen", "eighteen", "nineteen", "twenty",
            "twenty-one", "twenty-two", "twenty-three", "twenty-four", "twenty-five",
            "twenty-six", "twenty-seven", "twenty-eight", "twenty-nine", "thirty",
            "thirty-one", "thirty-two", "thirty-three", "thirty-four", "thirty-five",
            "thirty-six", "thirty-seven", "thirty-eight", "thirty-nine", "forty",
            "forty-one", "forty-two", "forty-three", "forty-four", "forty-five",
            "forty-six", "forty-seven", "forty-eight", "forty-nine", "fifty",
            "fifty-one", "fifty-two", "fifty-three", "fifty-four", "fifty-five",
            "fifty-six", "fifty-seven", "fifty-eight", "fifty-nine"
    };

    private static Calendar cal;
    private static SpannableStringBuilder b;

    private Wordy() {
    }

    static {
        cal = Calendar.getInstance();
        b = new SpannableStringBuilder();
    }

    public static int getMillisLeft() {
        cal.setTimeInMillis(System.currentTimeMillis());
        return 1000 - cal.get(Calendar.MILLISECOND);
    }

    private static void addSpace(SpannableStringBuilder b) {
        b.append(" ");
    }

    private static void colorize(SpannableStringBuilder b, String text, String color) {
        int startIndex = b.length();
        int endIndex = b.length() + text.length();

        ForegroundColorSpan span = new ForegroundColorSpan(Color.parseColor(color));

        b.append(text);
        b.setSpan(span, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    public static Spanned getWords(String defaultColor, String secondsColor) {
        cal.setTimeInMillis(System.currentTimeMillis());
        b.clear();

        // Calendar returns DAY_OF_WEEK, and starts at 1 for Sunday
        // so we need to take 1 off since our Sunday starts at 0
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
//        Log.i("app", "dayOfWeek: " + dayOfWeek);
        for (int i = 0; i < DAYS_OF_WEEK.length; i++) {
            if (i == dayOfWeek) {
                colorize(b, DAYS_OF_WEEK[i], defaultColor);
            } else {
                b.append(DAYS_OF_WEEK[i]);
            }
            addSpace(b);
        }

        // MONTH is returned starting at 0, so it fits with our array
        int month = cal.get(Calendar.MONTH);
//        Log.i("app", "month: " + month);
        for (int i = 0; i < MONTHS.length; i++) {
            if (i == month) {
                colorize(b, MONTHS[i], defaultColor);
            } else {
                b.append(MONTHS[i]);
            }
            addSpace(b);
        }

        // DAY_OF_MONTH also starts at 1
        int day = cal.get(Calendar.DAY_OF_MONTH) - 1;
//        Log.i("app", "day: " + day);
        for (int i = 0; i < DAYS.length; i++) {
            if (i == day) {
                colorize(b, DAYS[i], defaultColor);
            } else {
                b.append(DAYS[i]);
            }
            addSpace(b);
        }

        // HOUR starts at 1
        int hour = cal.get(Calendar.HOUR) - 1;
        // SECOND starts at 1 also
        // We're using the same words from hours up to 'ten' for the seconds
        int second = cal.get(Calendar.SECOND) - 1;
//        Log.i("app", "hour: " + hour);
        for (int i = 0; i < HOURS.length; i++) {
            if (i == second && i < 10) {
                colorize(b, HOURS[i], secondsColor);
            } else if (hour == -1 && i == 11) { // special case at midnight when hour == -1
                colorize(b, HOURS[i], defaultColor);
            } else if (i == hour) {
                colorize(b, HOURS[i], defaultColor);
            } else {
                b.append(HOURS[i]);
            }
            addSpace(b);
        }

        // MINUTE starts at 1
        int minute = cal.get(Calendar.MINUTE) - 1;
        if (minute == -1) {
            colorize(b, OCLOCK, defaultColor);
        } else {
            b.append(OCLOCK);
        }
        addSpace(b);

        // Here, using same words from minutes for seconds from 'eleven' to 'fifty-nine'
//        Log.i("app", "minute: " + minute);
        for (int i = 0; i < MINUTES.length; i++) {
            if (i == second && i >= 10) {
                colorize(b, MINUTES[i], secondsColor);
            } else if (i == minute) {
                colorize(b, MINUTES[i], defaultColor);
            } else {
                b.append(MINUTES[i]);
            }
            addSpace(b);
        }

        return b;
    }

}
