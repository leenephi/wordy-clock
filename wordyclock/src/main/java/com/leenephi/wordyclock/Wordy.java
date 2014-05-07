package com.leenephi.wordyclock;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.View;

import java.util.Calendar;

/**
 * Created by smithsonln on 4/28/14.
 */
public class Wordy {

    private static final String COLOR = "#D95B43";

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

    private Wordy() {
    }

    static {
        cal = Calendar.getInstance();
    }

//    public static String getMonthWord(int month) {
//        return MONTHS[--month];
//    }
//
//    public static String getDayWord(int day) {
//        return DAYS[--day];
//    }
//
//    public static String getDayOfWeekWord(int day) {
//        return DAYS_OF_WEEK[--day];
//    }
//
//    public static String getHourWord(int hour) {
//        return HOURS[--hour];
//    }
//
//    public static String getMinuteWord(int minute) {
//        return MINUTES[--minute];
//    }

    private static void colorize(SpannableStringBuilder b, String text) {
        int startIndex = b.length();
        int endIndex = b.length() + text.length();

        b.append(text);
        b.setSpan(new NonUnderlinedClickableSpan() {
            @Override
            public void onClick(View widget) {
                // nada
                // We only need a clickable span to be able to find their position
            }
        }, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    public static Spanned getWords() {
        cal.setTimeInMillis(System.currentTimeMillis());
        SpannableStringBuilder b = new SpannableStringBuilder();

        // Calendar returns DAY_OF_WEEK, and starts at 1 for Sunday
        // so we need to take 1 off since our Sunday starts at 0
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
//        Log.e("app", "dayOfWeek: " + dayOfWeek);
        for (int i = 0; i < DAYS_OF_WEEK.length; i++) {
            if (i == dayOfWeek) {
                colorize(b, DAYS_OF_WEEK[i]);
            } else {
                b.append(DAYS_OF_WEEK[i]);
            }
            b.append(" ");
        }

        // MONTH is returned starting at 0, so it fits with our array
        int month = cal.get(Calendar.MONTH);
//        Log.e("app", "month: " + month);
        for (int i = 0; i < MONTHS.length; i++) {
            if (i == month) {
                colorize(b, MONTHS[i]);
            } else {
                b.append(MONTHS[i]);
            }
            b.append(" ");
        }

        // DAY_OF_MONTH also starts at 1
        int day = cal.get(Calendar.DAY_OF_MONTH) - 1;
//        Log.e("app", "day: " + day);
        for (int i = 0; i < DAYS.length; i++) {
            if (i == day) {
                colorize(b, DAYS[i]);
            } else {
                b.append(DAYS[i]);
            }
            b.append(" ");
        }

        // HOUR starts at 1
        int hour = cal.get(Calendar.HOUR) - 1;
//        Log.e("app", "hour: " + hour);
        for (int i = 0; i < HOURS.length; i++) {
            if (i == hour) {
                colorize(b, HOURS[i]);
            } else {
                b.append(HOURS[i]);
            }
            b.append(" ");
        }

        // MINUTE starts at 1
        int minute = cal.get(Calendar.MINUTE) - 1;
//        Log.e("app", "minute: " + minute);
        for (int i = 0; i < MINUTES.length; i++) {
            if (i == minute) {
                colorize(b, MINUTES[i]);
            } else {
                b.append(MINUTES[i]);
            }
            b.append(" ");
        }

        // Turns all the <font color></font> into a spannable
        // string that the TextView can recognize
//        return Html.fromHtml(b.toString());

        return b;
    }

}
