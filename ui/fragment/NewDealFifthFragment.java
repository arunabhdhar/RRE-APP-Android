package org.rehab.app.ui.fragment;

import android.content.Intent;
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
import org.rehab.app.constants.AppConstants;
import org.rehab.app.models.app.ES2OperatingExpense;
import org.rehab.app.models.app.ES2OperatingIncome;
import org.rehab.app.ui.activity.new_deal.EX2OperatingExpenseActivity;
import org.rehab.app.ui.activity.new_deal.EX2OperatingIncomeActivity;
import org.rehab.app.utils.RadiantCalulator2;
import org.rehab.app.utils.ToolTipUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 */
public class NewDealFifthFragment extends Fragment {

    @BindView(R.id.ed_after_repair_value)
    EditText edAfterRepairValue;
    @BindView(R.id.s_months_to_complete)
    Spinner sMonthsToComplete;
    @BindView(R.id.tv_total_capital_needed)
    TextView tv_total_capital_needed;
    @BindView(R.id.tv_max_that_can_be)
    TextView tv_max_that_can_be;
    @BindView(R.id.tv_actual_to_be_financed)
    TextView tv_actual_to_be_financed;
    @BindView(R.id.tv_closing_holding_costs_interest)
    TextView tv_closing_holding_costs_interest;
    @BindView(R.id.tv_total_loan_amount)
    TextView tv_total_loan_amount;
    @BindView(R.id.tv_cash_required)
    TextView tv_cash_required;
    @BindView(R.id.tv_total_allin_costs)
    TextView tv_total_allin_costs;
    @BindView(R.id.tv_per_of_arv)
    TextView tv_per_of_arv;
    @BindView(R.id.tv_projected_operation_income)
    TextView tv_projected_operation_income;
    @BindView(R.id.tv_projected_operation_expenses)
    TextView tv_projected_operation_expenses;
    @BindView(R.id.tv_net_operating_income)
    TextView tv_net_operating_income;
    @BindView(R.id.s_refinance_into_permanent)
    Spinner s_refinance_into_permanent;
    @BindView(R.id.ed_refi_per_of_app)
    EditText ed_refi_per_of_app;
    @BindView(R.id.ed_new_mortgage_rate)
    EditText ed_new_mortgage_rate;
    @BindView(R.id.s_of_amo_year)
    Spinner s_of_amo_year;
    @BindView(R.id.ed_refi_discount_points)
    EditText ed_refi_discount_points;
    @BindView(R.id.tv_new_mtge_pmnt)
    TextView tv_new_mtge_pmnt;
    @BindView(R.id.tv_refi_loan_amount)
    TextView tv_refi_loan_amount;
    @BindView(R.id.tv_cash_out_at_refi)
    TextView tv_cash_out_at_refi;
    @BindView(R.id.tv_profit_at_refi)
    TextView tv_profit_at_refi;
    @BindView(R.id.tv_roi_on_cash_inv)
    TextView tv_roi_on_cash_inv;
    @BindView(R.id.tv_original_money_tied_up)
    TextView tv_original_money_tied_up;
    @BindView(R.id.tv_equity_left_in_deal)
    TextView tv_equity_left_in_deal;
    @BindView(R.id.tv_cash_flow)
    TextView tv_cash_flow;
    @BindView(R.id.tv_cash_on_cash)
    TextView tv_cash_on_cash;
    @BindView(R.id.tv_property_dcr)
    TextView tv_property_dcr;
    @BindView(R.id.tv_payback_period)
    TextView tv_payback_period;
    @BindView(R.id.tv_cap_rate_property)
    TextView tv_cap_rate_property;
    @BindView(R.id.tv_cap_rate_of_property_based_on_cost)
    TextView tv_cap_rate_of_property_based_on_cost;



    private ES2OperatingIncome es2OperatingIncomeNDF;
    private ES2OperatingExpense es2OperatingExpenseNDF;

    private String[] aMonths=new String[25];
    private String[] aYesNo={"Yes","No"};
    private String[] aAmzInOnly=new String[25];

    private RadiantCalulator2 radiantCalulator2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for(int i=0;i<25;i++){
            aMonths[i]=(i)+"";
            aAmzInOnly[i]=i+"";
        }
        aAmzInOnly[0]="Interest Only";
        radiantCalulator2=((NewDealFragment)getParentFragment()).getCalculatorInstance().getRadiantCalulator2();

        if(es2OperatingIncomeNDF==null){
            es2OperatingIncomeNDF=new ES2OperatingIncome();
            ES2OperatingIncome.ItemValue itemValue=new ES2OperatingIncome.ItemValue();
            itemValue.setNoOfUnits("1");
            itemValue.setAnnualRent("14400");
            itemValue.setMonthlyRent("1200");
            itemValue.setSquareFt("1400");
            itemValue.setPercent("100");
            itemValue.setUnityType("3");
            es2OperatingIncomeNDF.addItemValue(itemValue);
            es2OperatingIncomeNDF.setGoiMontlyRent("1200");
            es2OperatingIncomeNDF.setGoiAnnualRent("14400");
            es2OperatingIncomeNDF.setGsiAnuualRent("14400");
            es2OperatingIncomeNDF.setGsiMonthlyRent("1200");
            for(int i=0;i<4;i++){
                ES2OperatingIncome.ItemValue itemValu=new ES2OperatingIncome.ItemValue();
                es2OperatingIncomeNDF.addItemValue(itemValu);
            }
        }
        generateOEData(1200);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView=inflater.inflate(R.layout.fragment_new_deal_5,container,false);
        ButterKnife.bind(this,mView);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> sMonthToRentAdapter = new ArrayAdapter<String>(getActivity(),R.layout.inflate_spinner_row, aMonths);
        sMonthToRentAdapter.setDropDownViewResource(R.layout.inflate_spinner_row);
        sMonthsToComplete.setAdapter(sMonthToRentAdapter);
        sMonthsToComplete.setSelection(2);
        sMonthsToComplete.setOnItemSelectedListener(sItemSelectList);

        ArrayAdapter<String> sRefineIPFArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.inflate_spinner_row, aYesNo);
        sRefineIPFArrayAdapter.setDropDownViewResource(R.layout.inflate_spinner_row);
        s_refinance_into_permanent.setAdapter(sRefineIPFArrayAdapter);
        s_refinance_into_permanent.setOnItemSelectedListener(sItemSelectList);

        ArrayAdapter<String> sNAmorOfYearAdapter = new ArrayAdapter<String>(getActivity(),R.layout.inflate_spinner_row, aAmzInOnly);
        sNAmorOfYearAdapter.setDropDownViewResource(R.layout.inflate_spinner_row);
        s_of_amo_year.setAdapter(sNAmorOfYearAdapter);
        s_of_amo_year.setSelection(20);
        s_of_amo_year.setOnItemSelectedListener(sItemSelectList);

        valueSet();
        ed_new_mortgage_rate.addTextChangedListener(textWatcher);
        ed_refi_discount_points.addTextChangedListener(textWatcher);
        ed_refi_per_of_app.addTextChangedListener(textWatcher);
        edAfterRepairValue.addTextChangedListener(textWatcher);
    }

    private AdapterView.OnItemSelectedListener sItemSelectList= new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            valueSet();
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

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

    @OnClick({ R.id.tv_q_after_repair_value,R.id.tv_que_months_to_complete,R.id.tv_que_total_capital_needed,R.id.tv_que_max_that_can_be,R.id.tv_que_actual_to_be_financed,R.id.tv_que_closing_holding_costs_interest,R.id.tv_que_total_loan_amount,
            R.id.tv_que_cah_required,R.id.tv_que_total_allin_costs,R.id.tv_que_per_of_arv,R.id.tv_que_projected_operation_income,R.id.tv_que_projected_operation_expenses,R.id.tv_que_net_operating_income,R.id.tv_que_refinance_into_permanent,R.id.tv_que_refi_per_of_app,
            R.id.tv_que_new_mortgage_rate,R.id.tv_que_of_amo_year,R.id.tv_que_refi_discount_points,R.id.tv_que_new_mtge_pmnt,R.id.tv_que_refi_loan_amount,R.id.tv_que_cash_out_at_refi,R.id.tv_que_profit_at_refi,R.id.tv_que_roi_on_cash_inv,R.id.tv_que_original_money_tied_up,
            R.id.tv_que_equity_left_in_deal,R.id.tv_que_cash_flow,R.id.tv_que_cash_on_cash,R.id.tv_que_property_dcr,R.id.tv_que_payback_period,R.id.tv_que_cap_rate_property,R.id.tv_que_cap_rate_of_property_based_on_cost})
    public void onHintShow(View v){
        ToolTipUtils.showToolTip(getActivity(),v,v.getTag().toString());
    }

    @OnClick(R.id.btn_save)
    public void onSave(){
        // TODO: 24/6/16 Save the data on the server side.
        ((NewDealFragment) getParentFragment()).saveDataOnServer();
    }


    private void valueSet(){
        String afterRepairValue=edAfterRepairValue.getText().toString().trim();
        String refiPerOfApp=ed_refi_per_of_app.getText().toString().trim();
        String newMortgageRate=ed_new_mortgage_rate.getText().toString().trim();
        String refiDiscountPoints=ed_refi_discount_points.getText().toString().trim();
        if(TextUtils.isEmpty(afterRepairValue)){
            afterRepairValue="0";
        }
        if(TextUtils.isEmpty(refiPerOfApp)){
            refiPerOfApp="0";
        }
        if(TextUtils.isEmpty(newMortgageRate)){
            newMortgageRate="0";
        }
        if(TextUtils.isEmpty(refiDiscountPoints)){
            refiDiscountPoints="0";
        }
        radiantCalulator2.initCurrentValue(afterRepairValue,sMonthsToComplete.getSelectedItem().toString(),tv_projected_operation_income.getText().toString().trim(),tv_projected_operation_expenses.getText().toString().trim(),
                s_refinance_into_permanent.getSelectedItem().toString(), refiPerOfApp,newMortgageRate,s_of_amo_year.getSelectedItem().toString(),refiDiscountPoints);
        radiantCalulator2.secondInitValue();
        setValuesOnViews();
    }

    private void setValuesOnViews(){
        tv_total_capital_needed.setText(Math.round(radiantCalulator2.f36_totalCapitalNeeded)+"");
        tv_max_that_can_be.setText(Math.round(radiantCalulator2.f37_maxCanBeFinanced)+"");
        tv_actual_to_be_financed.setText(Math.round(radiantCalulator2.f38_actualToBeFinanced)+"");
        tv_closing_holding_costs_interest.setText(Math.round(radiantCalulator2.f39_costsInterestAddedToLoan)+"");

        tv_total_loan_amount.setText(Math.round(radiantCalulator2.f40_totalLoanAmount)+"");

        tv_cash_required.setText(Math.round(radiantCalulator2.f41_cacheRequired)+"");
        tv_total_allin_costs.setText(Math.round(radiantCalulator2.f42_totalAllInCosts)+"");
        tv_per_of_arv.setText(radiantCalulator2.f41_pcOfArv);
        tv_net_operating_income.setText(Math.round(radiantCalulator2.f45_netOperatingIncome)+"");
        if(Math.round(radiantCalulator2.f51_mortgagePayment)<0){
            tv_new_mtge_pmnt.setTextColor(getResources().getColor(R.color.red));
        }else {
            tv_new_mtge_pmnt.setTextColor(getResources().getColor(R.color.grey_500));
        }
        tv_new_mtge_pmnt.setText(Math.round(radiantCalulator2.f51_mortgagePayment) + "");
        tv_refi_loan_amount.setText(Math.round(radiantCalulator2.f52_refiLoanAmount)+"");
        tv_cash_out_at_refi.setText(Math.round(radiantCalulator2.f53_cashOutAtRefi)+"");
        tv_profit_at_refi.setText(Math.round(radiantCalulator2.f54_profitAtRefi)+"");
        tv_roi_on_cash_inv.setText(radiantCalulator2.f55_ROIOnCashInvested);
        tv_original_money_tied_up.setText(Math.round(radiantCalulator2.f56_originalMoneyTied)+"");
        tv_equity_left_in_deal.setText(Math.round(radiantCalulator2.f57_equityInDeal)+"");
        tv_cash_flow.setText(Math.round(radiantCalulator2.f58_cashFlow)+"");
        tv_cash_on_cash.setText(radiantCalulator2.f59_cashOnCash);
        tv_property_dcr.setText(radiantCalulator2.f60_propertyDCR);
        tv_payback_period.setText(radiantCalulator2.f61_paybackPeriod);
        tv_cap_rate_of_property_based_on_cost.setText(radiantCalulator2.f62_capRateOfPropertyCost);
        tv_cap_rate_property.setText(radiantCalulator2.f63_capRateOfPropertyARV);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @OnClick(R.id.tv_title_que_projected_profit)
    public void onProjectOperIncome(){
        Intent mIntent = new Intent(getActivity(), EX2OperatingIncomeActivity.class);
        mIntent.putExtra("data",es2OperatingIncomeNDF);
        getActivity().startActivityForResult(mIntent, AppConstants.ND_EX2_OPER_PRO_INCOME);
    }

    @OnClick(R.id.tv_title_projected_operation_expenses)
    public void onProjectExpense(){
        Intent mIntent = new Intent(getActivity(), EX2OperatingExpenseActivity.class);
        mIntent.putExtra("data",es2OperatingExpenseNDF);
        mIntent.putExtra("add_item_position",posAddItem);
        mIntent.putExtra("op_income",es2OperatingIncomeNDF.getGoiMontlyRent());
        getActivity().startActivityForResult(mIntent, AppConstants.ND_EX2_OPER_PRO_EXPENSE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==getActivity().RESULT_OK){
            if(requestCode==AppConstants.ND_EX2_OPER_PRO_INCOME){
                es2OperatingIncomeNDF= (ES2OperatingIncome) data.getExtras().getSerializable("data");
                tv_projected_operation_income.setText(es2OperatingIncomeNDF.getGoiMontlyRent());
                if(!TextUtils.isEmpty(es2OperatingIncomeNDF.getGoiMontlyRent())){
                    ES2OperatingExpense.ItemExpenseValue itemValue=es2OperatingExpenseNDF.getItemByValue(0);
                    float pE=((Float.parseFloat(es2OperatingIncomeNDF.getGoiMontlyRent())/100)*Float.parseFloat(itemValue.getNoOfUnits()));
                    tv_projected_operation_expenses.setText((pE+Float.parseFloat(es2OperatingExpenseNDF.getToeRestIncome()))+"");
                    itemValue.setMonthlyRent(pE+"");
                    itemValue.setAnnualRent((pE*12)+"");
                    es2OperatingExpenseNDF.setToeMonthlyExpense(tv_projected_operation_expenses.getText().toString());
                }
                valueSet();
            }else if(requestCode ==AppConstants.ND_EX2_OPER_PRO_EXPENSE){
                es2OperatingExpenseNDF= (ES2OperatingExpense) data.getExtras().getSerializable("data");
                posAddItem=data.getExtras().getInt("add_item_position");
                tv_projected_operation_expenses.setText(es2OperatingExpenseNDF.getToeMonthlyExpense());
                valueSet();
            }
        }
    }

    private int posAddItem;
    private void generateOEData(float opIncome){
        if(es2OperatingExpenseNDF==null){
            String[] opeFirstSection=getResources().getStringArray(R.array.operating_expense);
            String[] opeSecondSection=getResources().getStringArray(R.array.operating_expenses_utilities);
            es2OperatingExpenseNDF=new ES2OperatingExpense();
            ES2OperatingExpense.ItemExpenseValue itemValue=new ES2OperatingExpense.ItemExpenseValue();
            itemValue.setNoOfUnits("10");
            itemValue.setTitle(opeFirstSection[0]);
            itemValue.setMonthlyRent((opIncome/100f)*10+"");
            itemValue.setEditable(false);
            itemValue.setAnnualRent((Float.parseFloat(itemValue.getMonthlyRent())*12)+"");
            itemValue.setPercent("100");
            es2OperatingExpenseNDF.setToeRestIncome("0");
            es2OperatingExpenseNDF.addItemValue(itemValue);
            for(int i=1;i<opeFirstSection.length;i++){
                ES2OperatingExpense.ItemExpenseValue itemValueObj=new ES2OperatingExpense.ItemExpenseValue();
                itemValueObj.setNoOfUnits("");
                itemValueObj.setTitle(opeFirstSection[i]);
                itemValueObj.setMonthlyRent("");
                itemValueObj.setAnnualRent("");
                itemValueObj.setEditable(true);
                itemValueObj.setPercent("");
                es2OperatingExpenseNDF.addItemValue(itemValueObj);
            }
            posAddItem=es2OperatingExpenseNDF.getItemValueSize();

            {
                ES2OperatingExpense.ItemExpenseValue itemValueObj = new ES2OperatingExpense.ItemExpenseValue();
                itemValueObj.setTitle("Add Item");
                itemValueObj.setNoOfUnits("new_item");
                es2OperatingExpenseNDF.addItemValue(itemValueObj);
            }

            for(int i=0;i<opeSecondSection.length;i++){
                ES2OperatingExpense.ItemExpenseValue itemValueObj=new ES2OperatingExpense.ItemExpenseValue();
                itemValueObj.setNoOfUnits("");
                itemValueObj.setTitle(opeSecondSection[i]);
                itemValueObj.setMonthlyRent("");
                itemValueObj.setAnnualRent("");
                itemValueObj.setEditable(false);
                itemValueObj.setPercent("");
                es2OperatingExpenseNDF.addItemValue(itemValueObj);
            }

        }
    }
}
