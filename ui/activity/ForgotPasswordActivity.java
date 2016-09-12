package org.rehab.app.ui.activity;

import android.app.Activity;
import android.os.Bundle;
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
import org.rehab.app.models.response.ResBase;
import org.rehab.app.utils.HideKeyboard;
import org.rehab.app.utils.NetworkStatus;
import org.rehab.app.utils.ProgressDialogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Activity for showing the forgot password screen.
 *
 * @author and15031989
 */
public class ForgotPasswordActivity extends Activity  implements IASCommon{


    @BindView(R.id.ed_email)    EditText edEmailId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_submit)
    public void onSubmitClick() {
        onValidate();
    }
    @OnClick(R.id.iv_back)
    public void onBackClick(View view){
        finish();
    }

    private void onValidate() {
        String emailId = edEmailId.getText().toString().trim();
        if (TextUtils.isEmpty(emailId)) {
            Toast.makeText(ForgotPasswordActivity.this, R.string.val_email_address, Toast.LENGTH_LONG).show();
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailId).matches()) {
            Toast.makeText(ForgotPasswordActivity.this, R.string.val_email_invalid, Toast.LENGTH_LONG).show();
        } else {
            if (NetworkStatus.isInternetOn(ForgotPasswordActivity.this)) {
                HideKeyboard.keyboardHide(ForgotPasswordActivity.this);
                ProgressDialogUtil.getInstance().showProgressDialog(ForgotPasswordActivity.this);
                List<NameValuePair> mList = new ArrayList<>();
                mList.add(new BasicNameValuePair("user_email", emailId));
                mList.add(new BasicNameValuePair("service_access_key", AppConstants.APP_KEY));
                ReqRespAsync<ReqBase, ResBase> mReqRespAsync = new ReqRespAsync<ReqBase, ResBase>(ForgotPasswordActivity.this, ForgotPasswordActivity.this, AppConstants.FORGOT_PASSWORD_API, "post", mList, ResBase.class, "forgot_password");
                mReqRespAsync.execute();
            } else {
                Toast.makeText(ForgotPasswordActivity.this, R.string.err_internet_connection_error, Toast.LENGTH_LONG).show();
            }
        }
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResponse(Object type, Object data) {
        ProgressDialogUtil.getInstance().dismissProgressDialog();
        if(data instanceof ResBase) {
            if (type.toString().equals("forgot_password")) {
                ResBase obj = (ResBase) data;
                if (obj.getErrorCode() == 0) {
                    Toast.makeText(ForgotPasswordActivity.this, R.string.succ_email_send, Toast.LENGTH_LONG).show();
                    finish();
                } else if (obj.getErrorCode() == 1) {
                    Toast.makeText(ForgotPasswordActivity.this, R.string.error_email_not_registred, Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}
