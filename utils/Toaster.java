package org.rehab.app.utils;

import android.content.Context;
import android.widget.Toast;


/**
 * Class is used for showing the toast.
 *
 * @author and15031989
 */
public class Toaster {

    private static Context mContext;

    private Toaster() {
    }

    public Toaster(Context mContext) {
        this.mContext = mContext;
    }

    public static void show(int value) {
        Toast.makeText(mContext, value, Toast.LENGTH_SHORT).show();
    }

    public static void show(String value) {
        Toast.makeText(mContext, value, Toast.LENGTH_SHORT).show();
    }
}
