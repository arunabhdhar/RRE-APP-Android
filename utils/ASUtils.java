package org.rehab.app.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.view.inputmethod.InputMethodManager;

/**
 * This class is contain the utility methods.
 *
 * @author and15031989
 */
public class ASUtils {


    /**
     * Get the country code of the sim.
     *
     * @param context
     * @return countrycode
     */
    public static String getCountryISOCode(Context context) {
        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String operator = manager.getSimCountryIso();
            Logger.error("ISO  CODE:- " + operator);
            return operator;
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
    }

    /**
     * This method is used for Hide the keyboard
     *
     * @param mCtx
     */
    public static void keyboardHide(@NonNull Context mCtx) {
        try {
            InputMethodManager imm = (InputMethodManager) mCtx
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(((Activity) mCtx).getCurrentFocus()
                    .getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is use to check the status of Internet connection that is it
     * available or not and return.
     *
     * @param context
     * @return
     */
    public static boolean isInternetOn(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = connectivityManager.getActiveNetworkInfo();
        if (ni != null && ni.isAvailable() && ni.isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}
