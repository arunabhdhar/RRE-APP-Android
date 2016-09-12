package org.rehab.app.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import org.rehab.app.R;
import org.rehab.app.interfaces.IImagePickOption;


public class DialogUtils {

    public void showImagePickDialog(Context context , final IImagePickOption iImagePickOption, String title) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder
                .setMessage(title)
                .setCancelable(true)
                .setPositiveButton(R.string.txt_signup_gallery, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        new Thread() {
                            public void run() {
                                iImagePickOption.onImagePickOption(1);
                            }
                        }.start();
                    }
                })
                .setNegativeButton(R.string.txt_signup_camera, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        iImagePickOption.onImagePickOption(2);
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }
}
