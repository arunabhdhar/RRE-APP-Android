package org.rehab.app.constants;

import android.os.Environment;

/**
 * Class for global constants variable.
 */
public class AppConstants {

    public static final String CHILD_FOLDER_PROFILE_IMAGES = Environment.getExternalStorageDirectory() + "/.Rehab";

    public static final String APP_KEY = "aaznyZDQPLLPeAiblq6Mitk6uchEpvEx1ZiorfgAKmA=";

    public static final String BASE_URL="http://192.96.206.221/";//http://rehabrealestate.pixelandcodes.asia/";
    public static final String IMAGE_URL=BASE_URL+"images/";

    public static final String LOGIN_API=BASE_URL+"service/v1/user/userlogin";
    public static final String FORGOT_PASSWORD_API=BASE_URL+"service/v1/user/userforgotpassword";
    public static final String SIGNUP_API=BASE_URL+"service/v1/user/userregistration";
    public static final String QUICK_CALCULATOR_API=BASE_URL+"service/v1/deal/adddeals";
    public static final String NEW_DEAL_API=BASE_URL+"service/v1/deal/newdeals";
    public static final String SAVED_DEAL_API=BASE_URL+"service/v1/deal/savedeallist";
    public static final String UPDATE_USER_PROFILE=BASE_URL+"service/v1/user/userprofileupdate";


    public static final String CONTACT_US_API=BASE_URL+"service/v1/user/contactus";
    public static final String CHANGE_PASSWORD_API=BASE_URL+"service/v1/user/changepassword";
    public static final String SET_NOTIFICATION_API=BASE_URL+"service/v1/user/setnotifications";

//    http://rehabrealestate.pixelandcodes.asia/service/v1/deal/savedeallist?page=1&user_id=1

    public static final int LM_QUICK_CALCULATOR = 1;
    public static final int LM_NEW_DEAL = 2;
    public static final int LM_SAVE_DEAL = 3;
    public static final int LM_PLANS = 4;
    public static final int LM_TUTORIALS = 5;
    public static final int LM_CONTACT_US = 6;
    public static final int LM_ABOUT_US = 7;
    public static final int LM_SETTINGS = 8;
    public static final int LM_USER_PROFILE=9;
    public static final int LM_COMPARE=10;

    public static final int ND_CLOSING_COSTS=100;
    public static final int ND_HOLDING_COSTS = 101;
    public static final int ND_RADIANT_COSTS = 102;
    public static final int ND_EX2_OPER_PRO_INCOME = 103;
    public static final int ND_EX2_OPER_PRO_EXPENSE = 104;
}
