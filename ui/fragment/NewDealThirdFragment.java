package org.rehab.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import org.rehab.app.R;
import org.rehab.app.utils.RadiantCalulator;
import org.rehab.app.utils.ToolTipUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 */
public class NewDealThirdFragment extends Fragment {

    @BindView(R.id.s_financing_used)
    Spinner sFinancingUsed;
    @BindView(R.id.s_lender_caps)
    Spinner sLenderCaps;
    @BindView(R.id.ed_max_per_cost)
    EditText edMaxPerCost;
    @BindView(R.id.ed_origination_discount_points)
    EditText edOriDisPoints;
    @BindView(R.id.ed_oth_closing_costs)
    EditText edOtherClosingCosts;
    @BindView(R.id.s_points_and_closing_upfront)
    Spinner sPointsClosingUpfront;
    @BindView(R.id.ed_interest_rate)
    EditText edInterestRate;
    @BindView(R.id.s_interest_payment_during_rad)
    Spinner sInterestPaymentDurRad;
    @BindView(R.id.s_split_back_end_profits)
    Spinner sSplitBackEndProfit;
    @BindView(R.id.ed_what_per_pretax_proit)
    EditText edWhatPerPretaxProfit;

    @BindView(R.id.ll_profit_per)
    LinearLayout llProfitPer;

    @BindView(R.id.ll_finance_options)
    View llFinanceOptions;

    String[] aYesNo={"Yes","No"};
    String[] aFinancingUsed={"Financing","All Cash"};
    String[] aCostARV={"ARV","Cost"};
    String[] aPointsACUpFront={"Paid Upfront","Paid Backend"};


    protected String maxPerCost, oriDisPoints, otherClosingCosts, interestRate, whatPerPretaxProfit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView=inflater.inflate(R.layout.fragment_new_deal_3,container,false);
        ButterKnife.bind(this,mView);
        return mView;
    }

    @OnClick({ R.id.tv_q_financing_used, R.id.tv_q_lender_caps, R.id.tv_q_max_per_cost,R.id.tv_q_origination_discount_points,R.id.tv_q_oth_closing_costs,R.id.tv_q_points_and_closing_upfront,R.id.tv_q_interest_rate ,R.id.tv_q_split_back_end_profits ,R.id.tv_q_interest_payment_during_rad,R.id.tv_what_per_pretax_proit })
    public void onClickQClick(View v){
        ToolTipUtils.showToolTip(getActivity(),v,v.getTag().toString());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> sFinancingArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.inflate_spinner_row, aYesNo);
        sFinancingArrayAdapter.setDropDownViewResource(R.layout.inflate_spinner_row); // The drop down view
        sFinancingUsed.setAdapter(sFinancingArrayAdapter);
        sFinancingUsed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==1){
                    llFinanceOptions.setVisibility(View.GONE);
                }else{
                    llFinanceOptions.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> sLenderCapsArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.inflate_spinner_row, aCostARV);
        sLenderCapsArrayAdapter.setDropDownViewResource(R.layout.inflate_spinner_row); // The drop down view
        sLenderCaps.setAdapter(sLenderCapsArrayAdapter);
        sLenderCaps.setSelection(1);

        ArrayAdapter<String> sPCUOBEArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.inflate_spinner_row, aYesNo);
        sPCUOBEArrayAdapter.setDropDownViewResource(R.layout.inflate_spinner_row); // The drop down view
        sPointsClosingUpfront.setAdapter(sPCUOBEArrayAdapter);
        sPointsClosingUpfront.setSelection(1);

        ArrayAdapter<String> sIPDRArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.inflate_spinner_row, aYesNo);
        sIPDRArrayAdapter.setDropDownViewResource(R.layout.inflate_spinner_row); // The drop down view
        sInterestPaymentDurRad.setAdapter(sIPDRArrayAdapter);
        sInterestPaymentDurRad.setSelection(1);

        ArrayAdapter<String> sSBEPWLArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.inflate_spinner_row, aYesNo);
        sSBEPWLArrayAdapter.setDropDownViewResource(R.layout.inflate_spinner_row); // The drop down view
        sSplitBackEndProfit.setAdapter(sSBEPWLArrayAdapter);

        sSplitBackEndProfit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==1){
                    llProfitPer.setVisibility(View.GONE);
                    edWhatPerPretaxProfit.setText("0");
                }else{
                    llProfitPer.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    @OnClick(R.id.btn_continue)
    public void onContinueClick(){
        if(sFinancingUsed.getSelectedItemPosition()==1){
            RadiantCalulator radiantCalulator=((NewDealFragment)getParentFragment()).getCalculatorInstance();
            radiantCalulator.setValueForND3(false);
            ((NewDealFragment)getParentFragment()).onNewDeal3Response();
        }else {
            maxPerCost = edMaxPerCost.getText().toString().trim();
            oriDisPoints = edOriDisPoints.getText().toString().trim();
            otherClosingCosts = edOtherClosingCosts.getText().toString().trim();
            interestRate = edInterestRate.getText().toString().trim();
            whatPerPretaxProfit = edWhatPerPretaxProfit.getText().toString().trim();
            if (TextUtils.isEmpty(maxPerCost)) {
                edMaxPerCost.requestFocus();
            } else if (TextUtils.isEmpty(oriDisPoints)) {
                edOriDisPoints.requestFocus();
            } else if (TextUtils.isEmpty(otherClosingCosts)) {
                edOtherClosingCosts.requestFocus();
            } else if (TextUtils.isEmpty(interestRate)) {
                edInterestRate.requestFocus();
            } else if (TextUtils.isEmpty(whatPerPretaxProfit)) {
                edWhatPerPretaxProfit.requestFocus();
            } else {
                RadiantCalulator radiantCalulator=((NewDealFragment)getParentFragment()).getCalculatorInstance();
                radiantCalulator.setValueForND3(true,sLenderCaps.getSelectedItem().toString(),maxPerCost,
                        oriDisPoints,otherClosingCosts
                ,sPointsClosingUpfront.getSelectedItem().toString().equals("0")?"PU":"PB",
                        interestRate,sInterestPaymentDurRad.getSelectedItem().toString(),
                        sSplitBackEndProfit.getSelectedItem().toString(),whatPerPretaxProfit);
                ((NewDealFragment)getParentFragment()).onNewDeal3Response();
            }


        }
    }
}
