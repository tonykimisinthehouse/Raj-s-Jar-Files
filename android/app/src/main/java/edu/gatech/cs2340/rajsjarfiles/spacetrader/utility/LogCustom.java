package edu.gatech.cs2340.rajsjarfiles.spacetrader.utility;

import android.util.Log;

/**
 * Custom logging class for messages more than 4000 characters.
 */
public class LogCustom {

    /**
     * Custom logging method for long messages.
     *
     * @param tag the tag
     * @param content the message
     */
    public static void largeLog(String tag, String content) {
        if (content.length() > 4000) {
            Log.d(tag, content.substring(0, 4000));
            largeLog(tag, content.substring(4000));
        } else {
            Log.d(tag, content);
        }
    }
}
