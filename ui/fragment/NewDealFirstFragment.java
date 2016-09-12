package org.rehab.app.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.rehab.app.R;
import org.rehab.app.dialog.DialogUtils;
import org.rehab.app.interfaces.IImagePickOption;
import org.rehab.app.utils.TakeImageClass;
import org.rehab.app.utils.Toaster;

import java.io.File;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 */
public class NewDealFirstFragment extends Fragment implements IImagePickOption {

    @BindView(R.id.ed_property_title)
    EditText edPropertyTitle;
    @BindView(R.id.ed_location)
    EditText edLocation;
    @BindView(R.id.ed_area)
    EditText edArea;
    @BindView(R.id.ed_agent_name)
    EditText edAgentName;
    @BindView(R.id.ed_no_of_bedroom)
    EditText edNoOfBedroom;
    @BindView(R.id.ed_no_of_bathroom)
    EditText edNoOfBathroom;
    @BindView(R.id.ed_build_year)
    EditText edBuildYear;
    @BindView(R.id.iv_property_img)
    ImageView ivPropertyImage;

    private NewDealFragment newDealFragment;
    protected String propertyTitle,location,area,agentName,noOfBedroom,noOfBathroom,buildYear;
    public boolean isSwitch;

    private TakeImageClass mTakeImageClass;
    protected String mProfileImagePath;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView=inflater.inflate(R.layout.fragment_new_deal_1,container,false);
        ButterKnife.bind(this,mView);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        newDealFragment=(NewDealFragment)getParentFragment();
        mTakeImageClass = new TakeImageClass(getActivity());
        if(!TextUtils.isEmpty(mProfileImagePath)){
            Uri uri=Uri.fromFile(new File(mProfileImagePath));
            Picasso.with(getActivity())
                    .load(uri)
                    .fit()
                    .placeholder(R.drawable.profile_pic_frame)
                    .into(ivPropertyImage);
        }
    }
    @OnClick(R.id.btn_continue)
    public void onContinueClick(){
        if(calculateData(true)){
            newDealFragment.onNewDeal1Response();
        }else{

        }
    }

    /**
     * Method is used to check the input data.
     * @return
     */
    protected boolean calculateData(boolean isShow){
        propertyTitle=edPropertyTitle.getText().toString().trim();
        location=edLocation.getText().toString().trim();
        area=edArea.getText().toString().trim();
        agentName=edAgentName.getText().toString().trim();
        noOfBedroom=edNoOfBedroom.getText().toString().trim();
        noOfBathroom=edNoOfBathroom.getText().toString().trim();
        buildYear=edBuildYear.getText().toString().trim();
        if(TextUtils.isEmpty(propertyTitle)){
            if(isShow) {
                Toaster.show("Enter Property Name");
            }
            edPropertyTitle.requestFocus();
            isSwitch=false;
        }else if(TextUtils.isEmpty(location)){
            if(isShow) {
                Toaster.show("Enter Location");
            }
            edLocation.requestFocus();

            isSwitch=false;
        }else if(TextUtils.isEmpty(area)){
            if(isShow) {
                Toaster.show("Enter Area Value");
            }
            isSwitch=false;
            edArea.requestFocus();
        }else if(TextUtils.isEmpty(agentName)){
            if(isShow) {
                Toaster.show("Enter Agent Name");
            }
            isSwitch=false;
            edAgentName.requestFocus();
        }else if(TextUtils.isEmpty(noOfBedroom)){
            if(isShow) {
                Toaster.show("Enter No of Bedroom");
            }
            isSwitch=false;
            edNoOfBedroom.requestFocus();
        }else if(TextUtils.isEmpty(noOfBathroom)){
            if(isShow) {
                Toaster.show("Enter No of Bathroom");
            }
            isSwitch=false;
            edNoOfBathroom.requestFocus();
        }else if(TextUtils.isEmpty(buildYear)){
            if(isShow) {

                Toaster.show("Enter Build Year");
            }
            isSwitch=false;
            edBuildYear.requestFocus();
        }else if(Integer.parseInt(buildYear)<=1500 || Integer.parseInt(buildYear)> Calendar.getInstance().get(Calendar.YEAR)){
            if(isShow) {
                Toaster.show("Enter Valid Build Year");
            }
            isSwitch=false;
            edBuildYear.requestFocus();
        }
        else {
            isSwitch = true;
        }
        return  isSwitch;
    }



    public void clearAll(){
        edAgentName.setText("");
        edPropertyTitle.setText("");
        edArea.setText("");
        edLocation.setText("");
        edNoOfBedroom.setText("");
        edNoOfBathroom.setText("");
        edBuildYear.setText("");
        edPropertyTitle.requestFocus();

    }


    @OnClick(R.id.iv_property_img)
    public void onImageClick(){
        new DialogUtils().showImagePickDialog(getActivity(),NewDealFirstFragment.this, "Pick Property Image ?");
    }


    @Override
    public void onImagePickOption(int option) {
        if (option == 1) {
            mTakeImageClass.openGallery();
        } else if (option == 2) {
            mTakeImageClass.takePicture();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
                    if (ivPropertyImage != null) {
                        Uri uri=Uri.fromFile(new File(mProfileImagePath));
                        Picasso.with(getActivity())
                                .load(uri)
                                .fit()
                                .placeholder(R.drawable.profile_pic_frame)
                                .into(ivPropertyImage);
                    }
                }

                break;
        }







    }
}


