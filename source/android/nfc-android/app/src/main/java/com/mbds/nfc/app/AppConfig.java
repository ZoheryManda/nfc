package com.mbds.nfc.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.mbds.nfc.activity.MainActivity;

public class AppConfig {
    // Server user login url
    // Server user register url
    public static String URL_REGISTER = "http://auctionserver-mandadomain.rhcloud.com/register";

    public static String BASE_URL = "http://192.168.1.64:8080/nfc/rest";

    // Google project id
    public static final String SENDER_ID = "1097031827274";

    public static final String DISPLAY_MESSAGE_ACTION =
            "com.mbds.nfc.DISPLAY_MESSAGE";

    public static final String EXTRA_MESSAGE = "message";

    /**
     * Notifies UI to display a message.
     * <p>
     * This method is defined in the common helper because it's used both by
     * the UI and the background service.
     *
     * @param context application's context.
     * @param message message to be displayed.
     */
    public static void displayMessage(Context context, String message) {
        Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
        intent.putExtra(EXTRA_MESSAGE, message);
        context.sendBroadcast(intent);
    }
}