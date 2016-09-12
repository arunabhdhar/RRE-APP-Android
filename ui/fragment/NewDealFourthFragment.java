package org.rehab.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.rehab.app.R;
import org.rehab.app.utils.RadiantCalulator;
import org.rehab.app.utils.ToolTipUtils;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 */
public class NewDealFourthFragment extends Fragment {

    @BindView(R.id.ed_after_repair_value)
    EditText etAfterRepairValue;
    @BindView(R.id.s_months_complete_sales)
    Spinner sMonthsCompleteSales;
    @BindView(R.id.tv_total_capital_needed)
    TextView tvTotalCapitalNeeded;
    @BindView(R.id.tv_max_that_can_be)
    TextView tvMaxThatCanBe;
    @BindView(R.id.tv_actual_to_be_financed)
    TextView tvActualToBeFinanced;
    @BindView(R.id.tv_closing_holding_costs_interest)
    TextView tvClosingHoldingCostsInterest;
    @BindView(R.id.tv_total_loan_amount)
    TextView tvTotalLoanAmount;
    @BindView(R.id.tv_cash_required)
    TextView tvCashRequired;
    @BindView(R.id.tv_total_allin_costs)
    TextView tvTotalAllinCosts;
    @BindView(R.id.tv_per_of_arv)
    TextView tvPerOfArv;
    @BindView(R.id.ed_projected_resale_price)
    EditText etProjectedResalePrice;
    @BindView(R.id.ed_proj_cost_of_sale)
    EditText etProjCostOfSale;
    @BindView(R.id.tv_projected_profit)
    TextView tvProjectedProfit;
    @BindView(R.id.tv_return_of_cash)
    TextView tvReturnOfCash;
    @BindView(R.id.tv_roi_annualized)
    TextView tvRoiAnnualized;

    @BindView(R.id.ll_finance_options)
    View llFinanceOptions;

    String[] aMonths=new String[24];
    RadiantCalulator radiantCalulator;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for(int i=0;i<24;i++){
            aMonths[i]=(i+1)+"";
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView=inflater.inflate(R.layout.fragment_new_deal_4,container,false);
        ButterKnife.bind(this,mView);
        return mView;
    }
    @OnClick({ R.id.tv_q_after_repair_value, R.id.tv_que_months_complete_sales, R.id.tv_que_total_capital_needed,
            R.id.tv_que_max_that_can_be,R.id.tv_que_actual_to_be_financed,R.id.tv_que_closing_holding_costs_interest,
            R.id.tv_que_total_loan_amount,R.id.tv_que_cah_required,R.id.tv_que_total_allin_costs,R.id.tv_que_per_of_arv,
            R.id.tv_que_projected_profit,R.id.tv_que_return_of_cash,R.id.tv_que_roi_annualized,R.id.tv_projected_resale_price,R.id.tv_proj_cost_of_sale })
    public void onClickQClick(View v){
        ToolTipUtils.showToolTip(getActivity(),v,v.getTag().toString());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        radiantCalulator=((NewDealFragment)getParentFragment()).getCalculatorInstance();

        ArrayAdapter<String> sMonthArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.inflate_spinner_row, aMonths);
        sMonthArrayAdapter.setDropDownViewResource(R.layout.inflate_spinner_row); // The drop down view
        sMonthsCompleteSales.setAdapter(sMonthArrayAdapter);
        sMonthsCompleteSales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                valueSet();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sMonthsCompleteSales.setSelection(1);

        if(!radiantCalulator.isFinancingUsed()){
            llFinanceOptions.setVisibility(View.GONE);
        }else{
            llFinanceOptions.setVisibility(View.VISIBLE);
        }

        valueSet();
        etAfterRepairValue.addTextChangedListener(textWatcher);
        etProjCostOfSale.addTextChangedListener(textWatcher);
        etProjectedResalePrice.addTextChangedListener(textWatcher);
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
            valueSet();
        }
    };

    /**
     * Set the value and calculate the data.
     */
    public void valueSet(){
        String afterRepairValue=etAfterRepairValue.getText().toString().trim();
        String projectResalePrice=etProjectedResalePrice.getText().toString().trim();
        String projectCostOfSale=etProjCostOfSale.getText().toString().trim();
        if(TextUtils.isEmpty(afterRepairValue)){
            afterRepairValue="0";
        }
        if(TextUtils.isEmpty(projectResalePrice)){
            projectResalePrice="0";
        }
        if(TextUtils.isEmpty(projectCostOfSale)){
            projectCostOfSale="0";
        }

        radiantCalulator.setValueForND4(afterRepairValue,sMonthsCompleteSales.getSelectedItem().toString(),projectResalePrice,projectCostOfSale);
        radiantCalulator.init();
        tvTotalCapitalNeeded.setText(Math.round(Float.parseFloat(radiantCalulator.f19TCN))+"");
        tvCashRequired.setText(Math.round(Float.parseFloat(radiantCalulator.f24CROLOP))+"");
        tvTotalAllinCosts.setText(Math.round(Float.parseFloat(radiantCalulator.f25TAICER))+"");
        tvPerOfArv.setText(Math.round(Float.parseFloat(radiantCalulator.f26PARV))+"");
        tvProjectedProfit.setText(Math.round(Float.parseFloat(radiantCalulator.f29PP))+"");
        if(radiantCalulator.f30ROI.equals("Infinite")){
            tvReturnOfCash.setText(radiantCalulator.f30ROI);
        }else{
            tvReturnOfCash.setText(round(Float.parseFloat(radiantCalulator.f30ROI),2)+"%");
        }

        tvRoiAnnualized.setText(round(Float.parseFloat(radiantCalulator.f31ROIAnn),2)+"%");
        if(radiantCalulator.isFinancingUsed()){
            llFinanceOptions.setVisibility(View.VISIBLE);
            tvMaxThatCanBe.setText(Math.round(Float.parseFloat(radiantCalulator.f20M$TCF))+"");
            tvActualToBeFinanced.setText(Math.round(Float.parseFloat(radiantCalulator.f21ATBFNIC))+"");
            tvClosingHoldingCostsInterest.setText(Math.round(Float.parseFloat(radiantCalulator.f22CHCIATL))+"");
            tvTotalLoanAmount.setText(Math.round(Float.parseFloat(radiantCalulator.f23TLAm))+"");
        }else{
            llFinanceOptions.setVisibility(View.GONE);
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

    @OnClick(R.id.btn_continue)
    void onContinue(){
        ((NewDealFragment)getParentFragment()).onNewDeal4Response();
    }
}
