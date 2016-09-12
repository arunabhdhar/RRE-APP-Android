package org.rehab.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import org.rehab.app.utils.Toaster;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 */
public class ContactUsFragment extends Fragment implements IASCommon{
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_message)
    EditText etMessage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView=inflater.inflate(R.layout.fragment_contact_us,container,false);
        ButterKnife.bind(this,mView);
        return mView;
    }

    @OnClick(R.id.btn_send)
    public void onSendClick(){
        String emailId = etEmail.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String message=etMessage.getText().toString().trim();
        if(TextUtils.isEmpty(name)){
            etName.requestFocus();
            Toast.makeText(getActivity(), R.string.val_name_empty, Toast.LENGTH_LONG).show();
        }else if (TextUtils.isEmpty(emailId)) {
            etEmail.requestFocus();
            Toast.makeText(getActivity(), R.string.val_email_address, Toast.LENGTH_LONG).show();
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailId).matches()) {
            etEmail.requestFocus();
            Toast.makeText(getActivity(), R.string.val_email_invalid, Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(message)) {
            etMessage.requestFocus();
            Toast.makeText(getActivity(), R.string.val_message, Toast.LENGTH_LONG).show();
        } else {
            if (NetworkStatus.isInternetOn(getActivity())) {
                HideKeyboard.keyboardHide(getActivity());
                ProgressDialogUtil.getInstance().showProgressDialog(getActivity());
                List<NameValuePair> mList = new ArrayList<>();
                mList.add(new BasicNameValuePair("email", emailId));
                mList.add(new BasicNameValuePair("name", name));
                mList.add(new BasicNameValuePair("message",message));
                mList.add(new BasicNameValuePair("service_access_key", AppConstants.APP_KEY));
                ReqRespAsync<ReqBase, ResBase> mReqRespAsync = new ReqRespAsync<>(getActivity(), ContactUsFragment.this, AppConstants.CONTACT_US_API, "post", mList, ResBase.class, "contact_us");
                mReqRespAsync.execute();
            } else {
                Toast.makeText(getActivity(), R.string.err_internet_connection_error, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onResponse(Object type, Object data) {
        if(type.toString().equals("contact_us")){
            ProgressDialogUtil.getInstance().dismissProgressDialog();
            if(data instanceof ResBase){
                ResBase resBase= (ResBase) data;
                if(resBase.getErrorCode()==0){
                    Toaster.show(getString(R.string.txt_contact_us_success));
                    etEmail.setText("");
                    etName.setText("");
                    etMessage.setText("");
                }else{
                    Toaster.show(resBase.getMessage());
                }
            }
        }

    }
}
