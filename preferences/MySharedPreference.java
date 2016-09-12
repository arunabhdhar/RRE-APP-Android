package org.rehab.app.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 */
public class MySharedPreference {
    private static SharedPreferences.Editor editor;
    private static Context _context;
    private static SharedPreferences mPreferences;
    private static final String PREF_NAME = "rehab";
    private static MySharedPreference uniqInstance;
    private static final int PRIVATE_MODE = 0;

    /**
     * Private Constructor for not allowing other classes to instantiate this
     * class
     */
    private MySharedPreference() {

    }

    /**
     * @param context of the class calling this method
     * @return instance of this class This method is the global point of access
     * for getting the only one instance of this class
     */
    public static synchronized MySharedPreference getInstance(Context context) {
        if (uniqInstance == null) {
            _context = context;
            mPreferences = _context.getSharedPreferences(PREF_NAME,
                    PRIVATE_MODE);
            editor = mPreferences.edit();
            uniqInstance = new MySharedPreference();
        }
        return uniqInstance;
    }


    /**
     * Method is used for reset all the key & values of the shared preference.
     */
    public void resetAll() {
        editor.clear();
        editor.commit();
    }

    public void setDeviceToken(String deviceToken) {
        editor.putString("device_token", deviceToken);
        editor.commit();
    }

    public String getDeviceToken() {
        return mPreferences.getString("device_token", "");
    }


    public boolean getLogin() {
        return mPreferences.getBoolean("is_login", false);
    }

    public void setCategoryList(boolean value) {
        editor.putBoolean("category_list", value);
        editor.commit();
    }

    public boolean getCategoryList() {
        return mPreferences.getBoolean("category_list", false);
    }

    public void setLogin(boolean is_login) {
        editor.putBoolean("is_login", is_login);
        editor.commit();
    }


    public void setUserId(String value) {
        editor.putString("user_id", value);
        editor.commit();
    }

    public String getUserId() {
        return mPreferences.getString("user_id", "");
    }


    public void setUserCountry(String value) {
        editor.putString("user_country", value);
        editor.commit();
    }

    public String getUserCountry() {
        return mPreferences.getString("user_country", "");
    }


    public void setUserProfileImage(String value) {
        editor.putString("user_image", value);
        editor.commit();
    }

    public String getUserProfileImageF() {
        return mPreferences.getString("image", "");
    }

    public void setUserProfileImageF(String value) {
        editor.putString("image", value);
        editor.commit();
    }

    public String getUserProfileImage() {
        return mPreferences.getString("user_image", "");
    }

    public String getName() {
        return mPreferences.getString("name", "Radiant");
    }

    public void setName(String value) {
        editor.putString("name", value);
        editor.commit();
    }


    public String getDOB() {
        return mPreferences.getString("dob", "");
    }

    public void setDOB(String value) {
        editor.putString("dob", value);
        editor.commit();
    }


    public String getStatus() {
        return mPreferences.getString("status", "");
    }

    public void setStatus(String value) {
        editor.putString("status", value);
        editor.commit();
    }


    public String getGender() {
        return mPreferences.getString("gender", "");
    }

    public void setGender(String value) {
        editor.putString("gender", value);
        editor.commit();
    }

    public String getEmail() {
        return mPreferences.getString("user_email", "");
    }

    public void setEmail(String value) {
        editor.putString("user_email", value);
        editor.commit();
    }

    public String getPushNotification() {
        return mPreferences.getString("push_notification", "0");
    }

    public void setPushNotification(String value) {
        editor.putString("push_notification", value);
        editor.commit();
    }


}
