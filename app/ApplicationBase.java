package org.rehab.app.app;

import android.app.Application;

import org.rehab.app.utils.Toaster;

/**
 */
public class ApplicationBase extends Application {



    @Override
    public void onCreate() {
        super.onCreate();
        new Toaster(this);
    }
}
