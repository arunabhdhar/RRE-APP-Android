package org.rehab.app.ui.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.rehab.app.R;
import org.rehab.app.async.ReqRespAsync;
import org.rehab.app.constants.AppConstants;
import org.rehab.app.interfaces.IASCommon;
import org.rehab.app.models.request.ReqBase;
import org.rehab.app.models.response.ResLogin;
import org.rehab.app.preferences.MySharedPreference;
import org.rehab.app.utils.HideKeyboard;
import org.rehab.app.utils.NetworkStatus;
import org.rehab.app.utils.ProgressDialogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Activity is used for showing the login screen.
 *
 * @author and15031989
 */
public class LoginActivity extends Activity implements IASCommon {

    @BindView(R.id.ed_email)       EditText edEmailId;
    @BindView(R.id.ed_password)    EditText edPassword;

    private ActivityFinishBroadcast activityFinishBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        activityFinishBroadcast = new ActivityFinishBroadcast();
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(activityFinishBroadcast, new IntentFilter("finish"));
    }




    @OnClick(R.id.tv_forgo_password)
    public void onForgotPassword() {
        Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.tv_signup)
    public void onSignUp() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.btn_signin)
    public void onLoginClick() {
        String emailId = edEmailId.getText().toString().trim();
        String password = edPassword.getText().toString().trim();
        if (TextUtils.isEmpty(emailId)) {
            edEmailId.requestFocus();
            Toast.makeText(LoginActivity.this, R.string.val_email_address, Toast.LENGTH_LONG).show();
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailId).matches()) {
            edEmailId.requestFocus();
            Toast.makeText(LoginActivity.this, R.string.val_email_invalid, Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(password)) {
            edPassword.requestFocus();
            Toast.makeText(LoginActivity.this, R.string.val_password, Toast.LENGTH_LONG).show();
        } else if (password.length() < 4) {
            edPassword.requestFocus();
            Toast.makeText(LoginActivity.this, R.string.val_password_min_length, Toast.LENGTH_LONG).show();
        } else {
            if (NetworkStatus.isInternetOn(LoginActivity.this)) {
                HideKeyboard.keyboardHide(LoginActivity.this);
                ProgressDialogUtil.getInstance().showProgressDialog(LoginActivity.this);
                List<NameValuePair> mList = new ArrayList<>();
                mList.add(new BasicNameValuePair("user_email", emailId));
                mList.add(new BasicNameValuePair("user_password", password));
                mList.add(new BasicNameValuePair("user_device_type", "1"));
                mList.add(new BasicNameValuePair("service_access_key", AppConstants.APP_KEY));
                ReqRespAsync<ReqBase, ResLogin> mReqRespAsync = new ReqRespAsync<ReqBase, ResLogin>(LoginActivity.this, LoginActivity.this, AppConstants.LOGIN_API, "post", mList, ResLogin.class, "login");
                mReqRespAsync.execute();
            } else {
                Toast.makeText(LoginActivity.this, R.string.err_internet_connection_error, Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(activityFinishBroadcast);
    }

    @Override
    public void onResponse(Object type, Object data) {
        ProgressDialogUtil.getInstance().dismissProgressDialog();
        if(type.toString().equals("login")){
            ResLogin resLogin= (ResLogin) data;
            if(resLogin!=null && resLogin.getErrorCode()==0){
                MySharedPreference mySharedPreference = MySharedPreference.getInstance(getApplicationContext());
                ResLogin.Data user=resLogin.getData().get(0);
                mySharedPreference.setLogin(true);
                mySharedPreference.setName(user.getUserFullName());
                mySharedPreference.setUserId(user.getUserId()+"");
                mySharedPreference.setDOB(user.getUserDob());
                mySharedPreference.setEmail(user.getUserEmail());
                mySharedPreference.setGender(user.getUserGender()==1?"Male":"Female");
                mySharedPreference.setUserProfileImage(user.getUserImage().length()>0?AppConstants.BASE_URL+user.getUserImage():"");
                Intent mIntent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(mIntent);
                Intent mFBroadcast = new Intent("finish");
                LocalBroadcastManager.getInstance(LoginActivity.this).sendBroadcast(mFBroadcast);
                finish();

            }else if(resLogin!=null  && (resLogin.getErrorCode()==1 || resLogin.getErrorCode()==2)) {
                Toast.makeText(LoginActivity.this, R.string.error_invalid_login_param, Toast.LENGTH_LONG).show();
            }else if(resLogin!=null  && resLogin.getErrorCode()==3){
                Toast.makeText(LoginActivity.this,resLogin.getMessage(), Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(LoginActivity.this, R.string.err_server_error, Toast.LENGTH_LONG).show();
            }

        }
    }


    private class ActivityFinishBroadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    }

}
