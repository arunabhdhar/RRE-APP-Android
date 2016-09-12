package org.rehab.app.ui.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.rehab.app.R;
import org.rehab.app.async.ASFileUploadAsync;
import org.rehab.app.constants.AppConstants;
import org.rehab.app.dialog.DialogUtils;
import org.rehab.app.interfaces.IASCommon;
import org.rehab.app.interfaces.IImagePickOption;
import org.rehab.app.models.response.ResBase;
import org.rehab.app.models.response.ResLogin;
import org.rehab.app.preferences.MySharedPreference;
import org.rehab.app.utils.HideKeyboard;
import org.rehab.app.utils.Logger;
import org.rehab.app.utils.NetworkStatus;
import org.rehab.app.utils.ProgressDialogUtil;
import org.rehab.app.utils.TakeImageClass;
import org.rehab.app.utils.Toaster;
import org.rehab.app.utils.transformation.RoundedTransformationBuilder;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 */
public class SignUpActivity extends Activity implements IImagePickOption,IASCommon{

    @BindView(R.id.ed_name)             EditText edName;
    @BindView(R.id.ed_email)            EditText edEmail;
    @BindView(R.id.iv_profile_pic)      ImageView ivProfilePic;
    @BindView(R.id.ed_dob)              EditText edDob;
    @BindView(R.id.ed_password)         EditText edPassword;
    @BindView(R.id.ed_confirm_password) EditText edConfirmPassword;
    @BindView(R.id.rb_male)             RadioButton rbMale;
    @BindView(R.id.rb_female)           RadioButton rbFemale;
    @BindView(R.id.iv_profile_camera)   ImageView ivHolder;

    private TakeImageClass mTakeImageClass;
    private String mProfileImagePath,dobFormat="";
    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener dpdDOB;
    private Transformation transformation;
    String fileUrl="http://rehabrealestate.pixelandcodes.asia//images/profile/d5a8b582f296c87dc7c8cca7735eb8e9.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        mTakeImageClass = new TakeImageClass(SignUpActivity.this);
        myCalendar=Calendar.getInstance();
        dpdDOB = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.YEAR, year);
                updateLabelDOB();
            }
        };
         transformation = new RoundedTransformationBuilder()
                .borderColor(Color.WHITE)
                .borderWidthDp(3)
                .cornerRadiusDp(5)
                .oval(false)
                .build();

//        Picasso.with(SignUpActivity.this)
//                .load(fileUrl)
//                .fit()
//                .transform(transformation)
//                .into(ivProfilePic);
    }


    @OnClick(R.id.tv_sign_in)
    public void onSingInClick(View view){
        finish();
    }
    @OnClick(R.id.btn_signup)
    public void onSignUpClick(View view){
        //TODO check fields and update...


        String name = edName.getText().toString().trim();
        String emailId = edEmail.getText().toString().trim();
        String dob = edDob.getText().toString();
        String password = edPassword.getText().toString().trim();
        String confirmPass = edConfirmPassword.getText().toString().trim();

        if (name.length() == 0) {
            edName.requestFocus();
            Toaster.show(R.string.val_name_empty);
        } else if (emailId.length() == 0) {
            edEmail.requestFocus();
            Toaster.show(R.string.val_email_address);
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailId).matches()) {
            edEmail.requestFocus();
            Toaster.show(R.string.val_email_invalid);
        }  else if (TextUtils.isEmpty(dob)) {
            Toaster.show(R.string.val_dob_empty);
        } else if (password.length() == 0) {
            edPassword.requestFocus();
            Toaster.show(R.string.val_password);
        } else if (password.length() < 4) {
            edPassword.requestFocus();
            Toaster.show(R.string.val_password_min_length);
        }else if(!password.equals(confirmPass)){
            edConfirmPassword.requestFocus();
            Toaster.show(R.string.val_confirm_pass_mismatch);
        }
        else {
            if (NetworkStatus.isInternetOn(getApplicationContext())) {
                HideKeyboard.keyboardHide(SignUpActivity.this);
                ProgressDialogUtil.getInstance().showProgressDialog(SignUpActivity.this);
                HashMap<String, String> mValues = new HashMap<String, String>();
                mValues.put("user_full_name", name);
                mValues.put("user_dob", dob);
                mValues.put("user_email", emailId);

                if (rbMale.isChecked()) {
                    mValues.put("user_gender", "1");
                } else {
                    mValues.put("user_gender", "2");
                }
                mValues.put("user_type", "app");
                mValues.put("user_password", password);
                mValues.put("user_image",mProfileImagePath);
                mValues.put("service_access_key", AppConstants.APP_KEY);
                mValues.put("user_device_type","1");
                ASFileUploadAsync<ResBase, ResLogin> mReqRespAsync = new ASFileUploadAsync<ResBase, ResLogin>(this,this, AppConstants.SIGNUP_API,mValues, ResLogin.class, "signup");
                mReqRespAsync.execute();
            } else {
                Toaster.show(R.string.err_internet_connection_error);
            }

        }

    }

    @OnClick(R.id.rl_image_pick)
    public void onImageClick(View v){
        new DialogUtils().showImagePickDialog(SignUpActivity.this,SignUpActivity.this, "Pick Image ?");
    }


    @OnClick(R.id.iv_dob)
    public void onDOBSel(){
        HideKeyboard.keyboardHide(this);
        new DatePickerDialog(SignUpActivity.this, dpdDOB, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public void onImagePickOption(int option) {
        if (option == 1) {
            mTakeImageClass.openGallery();
        } else if (option == 2) {
            mTakeImageClass.takePicture();
        }
    }

    private void updateLabelDOB() {
        String myFormat = "MM/dd/yyyy"; // In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        if (myCalendar.getTime().getTime() > System.currentTimeMillis() - 1000) {
            Toast.makeText(SignUpActivity.this,R.string.val_invalid_dob,Toast.LENGTH_SHORT).show();
        } else {
            edDob.setText(sdf.format(myCalendar.getTime()));
            String myFormatd = "yyyy-MM-dd"; // In which you need put here
            SimpleDateFormat sdfd = new SimpleDateFormat(myFormatd, Locale.US);
            dobFormat=sdfd.format(myCalendar.getTime());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 129) {
            if (resultCode == RESULT_OK) {
                finish();
            }
        } else if (requestCode == TakeImageClass.REQUEST_CODE_TAKE_PICTURE || requestCode == TakeImageClass.REQUEST_CODE_GALLERY
                || requestCode == TakeImageClass.REQUEST_CODE_CROP_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                switch (requestCode) {
                    case TakeImageClass.REQUEST_CODE_TAKE_PICTURE:
                        mTakeImageClass.onActivityResult(requestCode, resultCode, data);
                        break;
                    case TakeImageClass.REQUEST_CODE_GALLERY:
                        mTakeImageClass.onActivityResult(requestCode, resultCode, data);
                        break;
                    case TakeImageClass.REQUEST_CODE_CROP_IMAGE:
                        mTakeImageClass.onActivityResult(requestCode, resultCode, data);
                        if (TakeImageClass.sImagePath != null) {
                            mProfileImagePath = TakeImageClass.sImagePath;

//                            Bitmap bitmap = BitmapFactory.decodeFile(mProfileImagePath);
                            if (ivProfilePic != null) {
                                ivHolder.setVisibility(View.GONE);
                                Uri uri=Uri.fromFile(new File(mProfileImagePath));
                                Picasso.with(SignUpActivity.this)
                                        .load(uri)
                                        .fit()
                                        .placeholder(R.drawable.profile_pic_frame)
                                        .transform(transformation)
                                        .into(ivProfilePic);
//                                ivProfilePic.setImageBitmap(bitmap);

                            }
                        }
                        break;
                }
            }
        }
    }

    @Override
    public void onResponse(Object type, Object data) {
        if(type.toString().equals("signup")){
            Logger.e(data.toString());
            ProgressDialogUtil.getInstance().dismissProgressDialog();
            if(data instanceof ResLogin){
                ResLogin resLogin= (ResLogin) data;
                if(((ResLogin) data).getErrorCode()==0){

                    MySharedPreference mySharedPreference = MySharedPreference.getInstance(getApplicationContext());
                    ResLogin.Data user=resLogin.getData().get(0);
                    mySharedPreference.setLogin(true);
                    mySharedPreference.setName(user.getUserFullName());
                    mySharedPreference.setUserId(user.getUserId()+"");
                    mySharedPreference.setDOB(user.getUserDob());
                    mySharedPreference.setEmail(user.getUserEmail());
                    mySharedPreference.setGender(user.getUserGender()==1?"Male":"Female");
                    mySharedPreference.setUserProfileImage(user.getUserImage().length()>0?AppConstants.BASE_URL+user.getUserImage():"");
                    Intent mIntent = new Intent(SignUpActivity.this, HomeActivity.class);
                    startActivity(mIntent);
                    Intent mFBroadcast = new Intent("finish");
                    LocalBroadcastManager.getInstance(SignUpActivity.this).sendBroadcast(mFBroadcast);
                    finish();
                }else{
                    Toaster.show(resLogin.getMessage());
                }
            }
        }
    }
}
