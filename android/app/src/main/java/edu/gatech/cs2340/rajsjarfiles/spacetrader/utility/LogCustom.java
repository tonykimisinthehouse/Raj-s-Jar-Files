package edu.gatech.cs2340.rajsjarfiles.spacetrader.utility;

import android.util.Log;

/**
 * Custom logging class for messages more than 4000 characters.
 */
public class LogCustom {
    private static final int MAX_LENGTH = 4000;

    /**
     * Custom logging method for long messages.
     *
     * @param tag the tag
     * @param content the message
     */
    public static void largeLog(String tag, String content) {
        if (content.length() > MAX_LENGTH) {
            Log.d(tag, content.substring(0, MAX_LENGTH));
            largeLog(tag, content.substring(MAX_LENGTH));
        } else {
            Log.d(tag, content);
        }
    }
}
