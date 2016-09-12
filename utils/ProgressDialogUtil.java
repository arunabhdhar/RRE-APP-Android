package org.rehab.app.utils;

import android.app.ProgressDialog;
import android.content.Context;

import org.rehab.app.R;


/**
 * Class is used for showing the Progress dialog and hide.
 *
 * @author and15031989
 */
public class ProgressDialogUtil {
    private ProgressDialog mProgressDialog;
    private static ProgressDialogUtil mInstance;

    public static ProgressDialogUtil getInstance() {
        if (mInstance == null) {
            mInstance = new ProgressDialogUtil();
        }
        return mInstance;
    }

    /**
     * Show the progress dialog...
     *
     * @param context Context should be activity context
     */
    public void showProgressDialog(Context context) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(context);
                mProgressDialog.setMessage(context.getString(R.string.msg_plz_wait));
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.setCancelable(true);
            } else if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }

            mProgressDialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Hide the progress dialog...
     */
    public void dismissProgressDialog() {
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
