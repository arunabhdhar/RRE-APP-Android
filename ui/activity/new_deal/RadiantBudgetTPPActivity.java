package org.rehab.app.ui.activity.new_deal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.rehab.app.R;
import org.rehab.app.adapter.DetailInputBudgetAdapter;
import org.rehab.app.interfaces.IASCommon;
import org.rehab.app.models.app.RadiantBudgetSelOption;
import org.rehab.app.utils.HideKeyboard;
import org.rehab.app.utils.Toaster;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 */
public class RadiantBudgetTPPActivity extends AppCompatActivity implements IASCommon{

    @BindView(R.id.s_budget_type)
    Spinner sBudgetType;
    @BindView(R.id.s_override)
    Spinner sOverride;
    @BindView(R.id.et_quick_lump_sum)
    EditText etQuickLumpSum;
    @BindView(R.id.rv_items)
    RecyclerView rvItems;
    @BindView(R.id.tv_bid1)
    TextView tvBid1;
    @BindView(R.id.tv_bid2)
    TextView tvBid2;
    @BindView(R.id.tv_bid3)
    TextView tvBid3;
    @BindView(R.id.tv_budget)
    TextView tvBudget;

    @BindView(R.id.rl_detail_input)
    RelativeLayout rlDetailInput;
    @BindView(R.id.rl_lump_sum_budget)
    RelativeLayout rlLumpSumBudget;
    @BindView(R.id.cb_show_bid)
    CheckBox cbShowBid;

    String[] aBudgetType={"Quick Lump Sum","Detailed Input"};
    String[] aOverride={"Fund Radiant in Draws(Default)","Fund Radiant at Closing"};

    private DetailInputBudgetAdapter detailInputBudgetAdapter;
    private int budgetOption=0,overrideOption=1;
    //    private String currentValue;
    private RadiantBudgetSelOption radiantBudgetSelOption;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_rehab_budget_tpp);
        ButterKnife.bind(this);
        radiantBudgetSelOption = (RadiantBudgetSelOption) getIntent().getExtras().getSerializable("redial_budget");
//        currentValue=getIntent().getExtras().getString("current_value");
//        etQuickLumpSum.setText(currentValue);
        onSetInitDataOfDetailInput();
    }

    @Override
    protected void onStart() {
        super.onStart();

        cbShowBid.setChecked(radiantBudgetSelOption.getDetailInput().isShowBid());
        ArrayAdapter<String> sBudgetArrayAdapter = new ArrayAdapter<String>(RadiantBudgetTPPActivity.this,R.layout.inflate_spinner_row, aBudgetType);
        sBudgetArrayAdapter.setDropDownViewResource(R.layout.inflate_spinner_row); // The drop down view
        sBudgetType.setAdapter(sBudgetArrayAdapter);
        sBudgetType.setOnItemSelectedListener(onBudgetItemSelectedListener);
        sBudgetType.setSelection(radiantBudgetSelOption.getEnterBudget());

        ArrayAdapter<String> sOverrideArrayAdapter = new ArrayAdapter<String>(RadiantBudgetTPPActivity.this,R.layout.inflate_spinner_row, aOverride);
        sOverrideArrayAdapter.setDropDownViewResource(R.layout.inflate_spinner_row); // The drop down view
        sOverride.setAdapter(sOverrideArrayAdapter);
        sOverride.setOnItemSelectedListener(onOverrideItemSelectedListener);
        sOverride.setSelection(radiantBudgetSelOption.getOverrideOption());

        showBidView(cbShowBid.isChecked());

        final LinearLayoutManager layoutManager = new LinearLayoutManager(RadiantBudgetTPPActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvItems.setLayoutManager(layoutManager);
        detailInputBudgetAdapter=new DetailInputBudgetAdapter(RadiantBudgetTPPActivity.this,radiantBudgetSelOption.getDetailInput(),RadiantBudgetTPPActivity.this);
        rvItems.setAdapter(detailInputBudgetAdapter);

        etQuickLumpSum.setText(radiantBudgetSelOption.getTotalAmount()+"");

    }


    @OnClick(R.id.cb_show_bid)
    public void onClickShowBid(){
        radiantBudgetSelOption.getDetailInput().setShowBid(cbShowBid.isChecked());
        detailInputBudgetAdapter.notifyDataSetChanged();
        showBidView(cbShowBid.isChecked());
    }


    private AdapterView.OnItemSelectedListener onBudgetItemSelectedListener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            budgetOption=i;
            radiantBudgetSelOption.setEnterBudget(i);
            if(i==0){
                rlLumpSumBudget.setVisibility(View.VISIBLE);
                rlDetailInput.setVisibility(View.GONE);
            }else if(i==1){
                rlDetailInput.setVisibility(View.VISIBLE);
                rlLumpSumBudget.setVisibility(View.GONE);
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    private AdapterView.OnItemSelectedListener onOverrideItemSelectedListener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            overrideOption=i;
            radiantBudgetSelOption.setOverrideOption(i);
            radiantBudgetSelOption.getDetailInput().setRehabBudInDraft(i==0?true:false);
            detailInputBudgetAdapter.notifyDataSetChanged();

        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    @OnClick(R.id.btn_cancel)
    void onCancelClick(){

        HideKeyboard.keyboardHide(RadiantBudgetTPPActivity.this);
        finish();
    }

    @OnClick(R.id.btn_update)
    void onUpdateClick(){
        String calValue="0";
        if(budgetOption==0){
            if(TextUtils.isEmpty(etQuickLumpSum.getText().toString().trim())){
                Toaster.show(R.string.hint_enter_quick_lump_sum);
                return;
            }else{
                calValue=etQuickLumpSum.getText().toString().trim();
            }
            radiantBudgetSelOption.setTotalAmount(Integer.parseInt(calValue));
        }else if(budgetOption==1){

        }

        HideKeyboard.keyboardHide(RadiantBudgetTPPActivity.this);
        Intent mIntent=new Intent();
        mIntent.putExtra("value",calValue);
        mIntent.putExtra("budget_option",budgetOption);  //selectedOption =0 QLS, 1:-> DI
        mIntent.putExtra("override_option",overrideOption); // overrideOption==0 Fund Radiant in Draws(Default), 1:- Fund Radiant at Closing"
        mIntent.putExtra("redial_budget",radiantBudgetSelOption);
        setResult(RESULT_OK,mIntent);
        finish();

    }



    private void onSetInitDataOfDetailInput(){
        if (radiantBudgetSelOption == null) {
            radiantBudgetSelOption=new RadiantBudgetSelOption();
            RadiantBudgetSelOption.DetailInputData diD =new RadiantBudgetSelOption.DetailInputData();

            List<RadiantBudgetSelOption.ItemValue> itemValueList=new ArrayList<>();
            {
                RadiantBudgetSelOption.ItemValue itemVal = new RadiantBudgetSelOption.ItemValue();
                itemVal.setItemName("Soft Costs");
                itemVal.setViewType(1);
                itemValueList.add(itemVal);
            }
            String[] diCost=getResources().getStringArray(R.array.di_soft_cost);
            for(int i=0;i<diCost.length;i++){
                RadiantBudgetSelOption.ItemValue itemVal = new RadiantBudgetSelOption.ItemValue();
                itemVal.setItemName(diCost[i]);
                itemVal.setViewType(2);
                itemValueList.add(itemVal);
            }
            {
                RadiantBudgetSelOption.ItemValue itemVal = new RadiantBudgetSelOption.ItemValue();
                itemVal.setItemName("+ Add Item");
                itemVal.setViewType(3);
                itemValueList.add(itemVal);
            }
            /////////////////////////////

            {
                RadiantBudgetSelOption.ItemValue itemVal = new RadiantBudgetSelOption.ItemValue();
                itemVal.setItemName("Trade permits");
                itemVal.setViewType(1);
                itemValueList.add(itemVal);
            }
            String[] diTreadPermit=getResources().getStringArray(R.array.di_trade_permits);
            for(int i=0;i<diTreadPermit.length;i++){
                RadiantBudgetSelOption.ItemValue itemVal = new RadiantBudgetSelOption.ItemValue();
                itemVal.setItemName(diTreadPermit[i]);
                itemVal.setViewType(2);
                itemValueList.add(itemVal);
            }
            {
                RadiantBudgetSelOption.ItemValue itemVal = new RadiantBudgetSelOption.ItemValue();
                itemVal.setItemName("+ Add Item");
                itemVal.setViewType(3);
                itemValueList.add(itemVal);
            }

            ////////////////////////////


            {
                RadiantBudgetSelOption.ItemValue itemVal = new RadiantBudgetSelOption.ItemValue();
                itemVal.setItemName("Hard Costs");
                itemVal.setViewType(1);
                itemValueList.add(itemVal);
            }
            String[] diHardCost=getResources().getStringArray(R.array.di_hard_costs);
            for(int i=0;i<diHardCost.length;i++){
                RadiantBudgetSelOption.ItemValue itemVal = new RadiantBudgetSelOption.ItemValue();
                itemVal.setItemName(diHardCost[i]);
                itemVal.setViewType(2);
                itemValueList.add(itemVal);
            }
            {
                RadiantBudgetSelOption.ItemValue itemVal = new RadiantBudgetSelOption.ItemValue();
                itemVal.setItemName("+ Add Item");
                itemVal.setViewType(3);
                itemValueList.add(itemVal);
            }

            /////////////////////
            {
                RadiantBudgetSelOption.ItemValue itemVal = new RadiantBudgetSelOption.ItemValue();
                itemVal.setItemName("Other");
                itemVal.setViewType(1);
                itemValueList.add(itemVal);
            }
            String[] diOthers=getResources().getStringArray(R.array.di_other);
            for(int i=0;i<diOthers.length;i++){
                RadiantBudgetSelOption.ItemValue itemVal = new RadiantBudgetSelOption.ItemValue();
                itemVal.setItemName(diOthers[i]);
                itemVal.setViewType(2);
                itemValueList.add(itemVal);
            }
            {
                RadiantBudgetSelOption.ItemValue itemVal = new RadiantBudgetSelOption.ItemValue();
                itemVal.setItemName("+ Add Item");
                itemVal.setViewType(3);
                itemValueList.add(itemVal);
            }

            diD.setItemValues(itemValueList);
            radiantBudgetSelOption.setDetailInput(diD);
        }
    }


    @Override
    public void onResponse(Object type, Object data) {
        tvBudget.setText(radiantBudgetSelOption.getDetailInput().getTotalBudget()+"");
        tvBid1.setText(radiantBudgetSelOption.getDetailInput().getTotalBid1()+"");
        tvBid2.setText(radiantBudgetSelOption.getDetailInput().getTotalBid2()+"");
        tvBid3.setText(radiantBudgetSelOption.getDetailInput().getTotalBid3()+"");
    }


    private void showBidView(boolean isShow){
        if(isShow) {
            tvBid1.setVisibility(View.VISIBLE);
            tvBid2.setVisibility(View.VISIBLE);
            tvBid3.setVisibility(View.VISIBLE);
        }else{
            tvBid1.setVisibility(View.GONE);
            tvBid2.setVisibility(View.GONE);
            tvBid3.setVisibility(View.GONE);
        }
    }
}
