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
import org.rehab.app.utils.Toaster;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 */
public class QuickCalculatorSecondFragment extends Fragment {

    @BindView(R.id.tv_maximum_offers_val)
    TextView tvMaxOffer;
    @BindView(R.id.ed_avr)
    EditText edAVR;
    @BindView(R.id.ed_max_per_avr)
    EditText edMaxPerAvr;
    @BindView(R.id.ed_repair)
    EditText edRepair;
    @BindView(R.id.ed_clos_cost_for_buy)
    EditText edClosCost4Buy;
    @BindView(R.id.ed_clos_cost_for_sell)
    EditText edClosCost4Sell;
    @BindView(R.id.ed_holding_cost)
    EditText edHoldingCost;
    @BindView(R.id.ed_other_expenses)
    EditText edOtherExpense;
    @BindView(R.id.ed_wholesale_profit)
    EditText edWholeSaleProfit;

    //c5,c6,c8,c9,c9b,c10,c11,c13
    protected Float avr=0.0f,maxPerAvr=0.0f,repair=0.0f,closeCost4Buy=0.0f,closeCost4Sell=0.0f,holdingCost=0.0f,
            otherExpense=0.0f,wholeSaleProfit=0.0f,maxOffer=0.0f;
    private boolean isSwitch;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView=inflater.inflate(R.layout.fragment_quick_cal_2,container,false);
        ButterKnife.bind(this,mView);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        calculateMaxOffer();
        edAVR.addTextChangedListener(textWatcher);
        edMaxPerAvr.addTextChangedListener(textWatcher);
        edRepair.addTextChangedListener(textWatcher);
        edClosCost4Buy.addTextChangedListener(textWatcher);
        edClosCost4Sell.addTextChangedListener(textWatcher);
        edHoldingCost.addTextChangedListener(textWatcher);
        edOtherExpense.addTextChangedListener(textWatcher);
        edWholeSaleProfit.addTextChangedListener(textWatcher);



    }
    private void calculateMaxOffer(){
        avr=Float.parseFloat((edAVR.getText().toString().trim().length()==0?"0":edAVR.getText().toString().trim()));
        maxPerAvr=Float.parseFloat((edMaxPerAvr.getText().toString().trim().length()==0?"1":edMaxPerAvr.getText().toString().trim()))% avr / 100;
        repair=Float.parseFloat((edRepair.getText().toString().trim().length()==0?"0":edRepair.getText().toString().trim()));
        closeCost4Buy=Float.parseFloat((edClosCost4Buy.getText().toString().trim().length()==0?"0":edClosCost4Buy.getText().toString().trim()));
        closeCost4Sell=Float.parseFloat((edClosCost4Sell.getText().toString().trim().length()==0?"0":edClosCost4Sell.getText().toString().trim()))/100;
        holdingCost=Float.parseFloat((edHoldingCost.getText().toString().trim().length()==0?"0":edHoldingCost.getText().toString().trim()));
        otherExpense=Float.parseFloat((edOtherExpense.getText().toString().trim().length()==0?"0":edOtherExpense.getText().toString().trim()));
        wholeSaleProfit=Float.parseFloat((edWholeSaleProfit.getText().toString().trim().length()==0?"0":edWholeSaleProfit.getText().toString().trim()));

        if(edMaxPerAvr.getText().toString().length()==0){
            ((CalculatorFragment)getParentFragment()).changeSFCalVal(avr*(maxPerAvr==0?1:maxPerAvr));
            maxOffer=avr;
            tvMaxOffer.setText(avr+"");
            return;
        }

        float c14=avr*maxPerAvr-repair-closeCost4Buy-avr*closeCost4Sell-holdingCost-otherExpense-wholeSaleProfit;
        if((c14+"").equalsIgnoreCase("NaN")){
            maxOffer=0.0f;
            tvMaxOffer.setText("0");
        }else {
            c14 = round(c14, 2);
            maxOffer=Math.abs(c14);
            tvMaxOffer.setText(Math.abs(c14) + "");
        }
        ((CalculatorFragment)getParentFragment()).changeSFCalVal(avr*(maxPerAvr==0?1:maxPerAvr));
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

    @OnClick(R.id.btn_continue)
    public void onContinue(){
        if(calculatedData(true)){
            ((CalculatorFragment)getParentFragment()).onSecondFragRes();
        }

    }


    protected boolean calculatedData(boolean isShow){
        if(edAVR.getText().toString().trim().length()==0){
            if (isShow) {
                Toaster.show("Enter AVR Value");
            }
            edAVR.requestFocus();
            isSwitch= false;
        }else if(edMaxPerAvr.getText().toString().trim().length()==0){
            if (isShow) {
                Toaster.show("Enter Maximum Percentage AVR");
            }
            edAVR.requestFocus();
            isSwitch= false;
        }else if(edRepair.getText().toString().trim().length()==0){
            if (isShow) {
                Toaster.show("Enter Repair Value");
            }
            edAVR.requestFocus();
            isSwitch= false;
        }else
        {
            isSwitch = true;
        }
        return  isSwitch;
    }

    public void clearAll() {
        edAVR.setText("");
        edMaxPerAvr.setText("");
        edRepair.setText("");
        edClosCost4Buy.setText("");
        edClosCost4Sell.setText("");
        edHoldingCost.setText("");
        edOtherExpense.setText("");
        edWholeSaleProfit.setText("");
        tvMaxOffer.setText("0");
        maxOffer=0.0f;
    }
}
