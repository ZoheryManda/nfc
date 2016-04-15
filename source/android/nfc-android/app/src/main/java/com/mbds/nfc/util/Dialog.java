package com.mbds.nfc.util;

import android.app.ProgressDialog;

/**
 * Created by macbook on 12/07/2015.
 */
public class Dialog {
    public static void hidePDialog(ProgressDialog pDialog) {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
