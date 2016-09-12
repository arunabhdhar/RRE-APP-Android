package org.rehab.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.rehab.app.R;
import org.rehab.app.async.ReqRespAsync;
import org.rehab.app.constants.AppConstants;
import org.rehab.app.interfaces.IASCommon;
import org.rehab.app.models.request.ReqBase;
import org.rehab.app.models.response.ResBase;
import org.rehab.app.preferences.MySharedPreference;
import org.rehab.app.ui.activity.HomeActivity;
import org.rehab.app.utils.NetworkStatus;
import org.rehab.app.utils.ProgressDialogUtil;
import org.rehab.app.utils.Toaster;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 */
public class ChangePasswordFragment extends Fragment implements IASCommon{

    @BindView(R.id.et_current_password)
    EditText etCurrentPassword;
    @BindView(R.id.et_new_password)
    EditText etNewPassword;
    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_change_password,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.btn_submit)
    public void onSubmit(){
        String currentPassword=etCurrentPassword.getText().toString().trim();
        String newPassword=etNewPassword.getText().toString().trim();
        String confirmPassword=etConfirmPassword.getText().toString().trim();
        if(TextUtils.isEmpty(currentPassword)){
            Toaster.show("Enter your current password.");
            etCurrentPassword.requestFocus();
        }else if(TextUtils.isEmpty(newPassword)){
            Toaster.show("Enter your new password.");
            etNewPassword.requestFocus();
        }else if(!newPassword.equals(confirmPassword)){
            Toaster.show("Your password is not matched.");
            etConfirmPassword.requestFocus();
        }else{
            if (NetworkStatus.isInternetOn(getActivity())) {
                ProgressDialogUtil.getInstance().showProgressDialog(getActivity());
                List<NameValuePair> mList = new ArrayList<>();
                mList.add(new BasicNameValuePair("user_id", MySharedPreference.getInstance(getActivity()).getUserId()));
                mList.add(new BasicNameValuePair("old_user_password", currentPassword));
                mList.add(new BasicNameValuePair("user_password", newPassword));
                mList.add(new BasicNameValuePair("user_password_again", newPassword));
                mList.add(new BasicNameValuePair("service_access_key", AppConstants.APP_KEY));
                ReqRespAsync<ReqBase, ResBase> mReqRespAsync = new ReqRespAsync<>(getActivity(), ChangePasswordFragment.this, AppConstants.CHANGE_PASSWORD_API, "post", mList, ResBase.class, "change_password");
                mReqRespAsync.execute();
            }else{
                Toaster.show(R.string.err_internet_connection_error);
            }
        }
    }

    @Override
    public void onResponse(Object type, Object data) {
        if(type.toString().equals("change_password")){
            ProgressDialogUtil.getInstance().dismissProgressDialog();
            if(data instanceof ResBase){
                ResBase obj= (ResBase) data;
                if(obj.getErrorCode()==0){
                    Toaster.show("Password Change Successfully.");
                    etCurrentPassword.setText("");
                    etNewPassword.setText("");
                    etConfirmPassword.setText("");
                    ((HomeActivity)getActivity()).onBackPressed();
                }else{
                    Toaster.show(obj.getMessage());
                }
            }
        }
    }
}
