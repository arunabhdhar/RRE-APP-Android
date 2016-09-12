package org.rehab.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.rehab.app.R;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 */
public class QuickCalculatorThirdFragment extends Fragment{

    @BindView(R.id.tv_maximum_offers_val)
    TextView tvValue;
    @BindView(R.id.ed_buyer_cost_basis)
    EditText edBuyerCostBasis;
    @BindView(R.id.ed_project_income_monthly)
    EditText edProjectIncomeMonthly;
    @BindView(R.id.ed_project_expenses_monthly)
    EditText edProjectExpenses;

   protected Float buyerCost=0.0f,projectIcome=0.0f,projectExpenses=0.0f,capRate=0.0f;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView=inflater.inflate(R.layout.fragment_quick_cal_3,container,false);
        ButterKnife.bind(this,mView);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        calculateMaxOffer();
        edBuyerCostBasis.setText(buyerCost+"");
        edProjectIncomeMonthly.addTextChangedListener(textWatcher);
        edProjectExpenses.addTextChangedListener(textWatcher);
    }

    public void setBuyerCost(Float buyerCost){
        if(!(buyerCost+"").equals("NaN")){
            this.buyerCost=buyerCost;
            edBuyerCostBasis.setText(round(buyerCost,2)+"");
        }


    }
    private void calculateMaxOffer(){
        projectIcome=Float.parseFloat((edProjectIncomeMonthly.getText().toString().trim().length()==0?"0":edProjectIncomeMonthly.getText().toString().trim()));
        projectExpenses=Float.parseFloat((edProjectExpenses.getText().toString().trim().length()==0?"0":edProjectExpenses.getText().toString().trim()));
        float c14=(projectIcome-projectExpenses)*12*100/buyerCost;
        tvValue.setText(c14+"");
        if((c14+"").equalsIgnoreCase("NaN")){
            tvValue.setText("0");
            capRate=0.0f;
        }else {
            c14 = round(c14, 2);
            capRate=Math.abs(c14);
            tvValue.setText(Math.abs(c14) + "%");
        }
    }


    /**
     * Round to certain number of decimals
     *
     * @param d
     * @param decimalPlace
     * @return
     */
    private float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
    private TextWatcher textWatcher=new TextWatcher() {

        @Override
        public void afterTextChanged(Editable s) {}

        @Override
        public void beforeTextChanged(CharSequence s, int start,
                                      int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {
            calculateMaxOffer();
        }
    };


    @OnClick(R.id.btn_save_calculator)
    public void onSaveCalculator(){
//        if(edProjectIncomeMonthly.getText().toString().length()==0){
//            Toaster.show("Enter Project Monthly Income");
//        }else if(edProjectExpenses.getText().toString().length()==0){
//            Toaster.show("Enter Project Monthly Expenses");
//        }else {
            ((CalculatorFragment) getParentFragment()).onThirdFragRes();
//        }
    }

    /**
     * Clear All the fields.
     */
    public void clearAll(){
        tvValue.setText("0");
        edProjectIncomeMonthly.setText("");
        edProjectExpenses.setText("");
        edBuyerCostBasis.setText("");
    }
}

