package com.leenephi.wordyclock;

/**
 * Created by smithsonln on 4/28/14.
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
            "monday", "tuesday", "wednesday", "thursday",
            "friday", "saturday", "sunday"
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

    private Wordy() {
        // static stuff, bro
    }

    public static String getMonthWord(int month) {
        return MONTHS[--month];
    }

    public static String getDayWord(int day) {
        return DAYS[--day];
    }

    public static String getDayOfWeekWord(int day) {
        return DAYS_OF_WEEK[--day];
    }

    public static String getHourWord(int hour) {
        return HOURS[--hour];
    }

    public static String getMinuteWord(int minute) {
        return MINUTES[--minute];
    }

    public static String getWordy() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < DAYS_OF_WEEK.length; i++) {
            builder.append(DAYS_OF_WEEK[i]);
            builder.append(" ");
        }

        for (int i = 0; i < MONTHS.length; i++) {
            builder.append(MONTHS[i]);
            builder.append(" ");
        }

        for (int i = 0; i < DAYS.length; i++) {
            builder.append(DAYS[i]);
            builder.append(" ");
        }

        for (int i = 0; i < HOURS.length; i++) {
            builder.append(HOURS[i]);
            builder.append(" ");
        }

        for (int i = 0; i < MINUTES.length; i++) {
            builder.append(MINUTES[i]);
            builder.append(" ");
        }

        return builder.toString();
    }

}
