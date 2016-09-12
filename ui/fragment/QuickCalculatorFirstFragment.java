package org.rehab.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.rehab.app.R;
import org.rehab.app.utils.Toaster;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 */
public class QuickCalculatorFirstFragment extends Fragment {


    @BindView(R.id.rd_property_title)
    EditText edPropertyTitle;
    @BindView(R.id.ed_location)
    EditText edLocation;
    @BindView(R.id.ed_area)
    EditText edArea;
    protected String propertyTitle,location,area;
    private CalculatorFragment calculatorFragment;
    public boolean isSwitch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView=inflater.inflate(R.layout.fragment_quick_cal_1,container,false);
        ButterKnife.bind(this,mView);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        calculatorFragment=(CalculatorFragment)getParentFragment();
    }

    @OnClick(R.id.btn_continue)
    public void onContinue(){
        if(calculateData(true)){
            calculatorFragment.onFirstFragRes();
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
        }else {
            isSwitch = true;
        }
        return  isSwitch;
    }

    public void clearAll(){
        edPropertyTitle.setText("");
        edArea.setText("");
        edLocation.setText("");
        edPropertyTitle.requestFocus();

    }


}
