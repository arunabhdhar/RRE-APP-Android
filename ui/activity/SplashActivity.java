package org.rehab.app.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;

import org.rehab.app.R;
import org.rehab.app.constants.AppConstants;
import org.rehab.app.preferences.MySharedPreference;
import org.rehab.app.utils.Logger;
import org.rehab.app.utils.RadiantCalulator;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Activity for showing the splash screen.
 *
 * @author and15031989
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isTaskRoot()) {
            finish();
            return;
        }

        setContentView(R.layout.activity_splash);

//        new RadiantCalulator().init();
        printHashKey();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (MySharedPreference.getInstance(getApplicationContext()).getLogin()) {
                    Intent mIntent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(mIntent);
                } else {
                    Intent mIntent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(mIntent);
                }
                finish();
            }
        }, 2000);


        mkDirFile();


    }


    /**
     * Method is used for fetching the key-hash of facebook.
     */
    private void printHashKey() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getApplicationContext().getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Logger.e("TAG", "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Logger.e("TAG", "printHashKey()");
        } catch (Exception e) {
            Logger.e("TAG", "printHashKey()");
        }
    }

    private void mkDirFile() {
        File mFile = new File(AppConstants.CHILD_FOLDER_PROFILE_IMAGES);
        if (!mFile.isDirectory()) {
            mFile.mkdir();
        }
    }

    @Override
    public void onBackPressed() {
    }


}
