package org.rehab.app.ui.fragment;

import android.content.Intent;
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
import android.widget.Spinner;
import android.widget.TextView;

import org.rehab.app.R;
import org.rehab.app.constants.AppConstants;
import org.rehab.app.models.app.NDClosingCostRehab;
import org.rehab.app.models.app.NDHoldingCostRehab;
import org.rehab.app.models.app.RadiantBudgetSelOption;
import org.rehab.app.ui.activity.new_deal.ClosingCostsTPPActivity;
import org.rehab.app.ui.activity.new_deal.HoldingCostsTPPActivity;
import org.rehab.app.ui.activity.new_deal.RadiantBudgetTPPActivity;
import org.rehab.app.utils.RadiantCalulator;
import org.rehab.app.utils.Toaster;
import org.rehab.app.utils.ToolTipUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 */
public class NewDealSecondFragment extends Fragment {

    @BindView(R.id.ed_purchase_price)
    EditText edPurchasePrice;
    @BindView(R.id.s_inc_clos_holding_costs)
    Spinner sIncClosHoldingCosts;
    @BindView(R.id.s_radiant_period)
    Spinner sRadiantPeriod;
    @BindView(R.id.tv_closing_costs)
    TextView tvClosingCosts;
    @BindView(R.id.tv_holding_costs)
    TextView tvHoldingCosts;
    @BindView(R.id.tv_radiant_budget)
    TextView tvRadiantBudget;

    String[] aYesNo={"Yes","No"};
    String[] aRadiantPeriod=new String[25];
    private String mHoldingCostType="QLS";
    private RadiantBudgetSelOption radiantBudgetSelOption;

    private String closingCosts="1500",holdingCost="1500";
    private NDHoldingCostRehab ndHoldingCostRehab;
    protected String mPurchasePrice,mClosingCost,mHoldingCost,mRadiantCosts,mIncludingCHInLoan,mProjectRPM;
    private NDClosingCostRehab ndClosingCostRehab;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for(int i=0;i<25;i++){
            aRadiantPeriod[i]=i+"";
        }
        initClosingCosts();
        initHoldingCostValue();
        onSetInitDataOfDetailInput();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView=inflater.inflate(R.layout.fragment_new_deal_2,container,false);
        ButterKnife.bind(this,mView);
        return mView;
    }


    public void clearAll(){

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Application of the Array to the Spinner
        tvClosingCosts.setText(closingCosts);

        if(ndHoldingCostRehab.getHoldingCostsType()==0){
            holdingCost=ndHoldingCostRehab.getTotalHoldingCosts()+"";
            tvHoldingCosts.setText(ndHoldingCostRehab.getTotalHoldingCosts()+"");
        }else{
            holdingCost=ndHoldingCostRehab.getTotalCostItems()+"";
            tvHoldingCosts.setText(ndHoldingCostRehab.getTotalCostItems()+"");
        }

        if(radiantBudgetSelOption.getEnterBudget()==1){
            tvRadiantBudget.setText(radiantBudgetSelOption.getDetailInput().getTotalBudget()+"");
        }else{
            tvRadiantBudget.setText(radiantBudgetSelOption.getTotalAmount()+"");
        }


        ArrayAdapter<String> sIncClosArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.inflate_spinner_row, aYesNo);
        sIncClosArrayAdapter.setDropDownViewResource(R.layout.inflate_spinner_row); // The drop down view
        sIncClosHoldingCosts.setAdapter(sIncClosArrayAdapter);
        sIncClosHoldingCosts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter<String> sRadiantPeriodArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.inflate_spinner_row, aRadiantPeriod);
        sRadiantPeriodArrayAdapter.setDropDownViewResource(R.layout.inflate_spinner_row); // The drop down view
        sRadiantPeriod.setAdapter(sRadiantPeriodArrayAdapter);
        sRadiantPeriod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sRadiantPeriod.setSelection(2);
    }

    @OnClick({ R.id.tv_q_purchase_price, R.id.tv_que_closing_costs, R.id.tv_que_holding_costs,R.id.tv_que_inc_clos_holding_costs,R.id.tv_que_radiant_budget,R.id.tv_que_radiant_period })
    public void onClickQClick(View v){
        ToolTipUtils.showToolTip(getActivity(),v,v.getTag().toString());
    }

    @OnClick(R.id.tv_title_closing_costs)
    public void onClosingCostsClick(){
        String f1Value=edPurchasePrice.getText().toString().trim();
        if(TextUtils.isEmpty(f1Value)){
            Toaster.show(R.string.hint_enter_purchase_price);
        }else {
            Intent mIntent = new Intent(getActivity(), ClosingCostsTPPActivity.class);
            mIntent.putExtra("data",ndClosingCostRehab);
            mIntent.putExtra("current_value", tvClosingCosts.getText().toString().trim());
            mIntent.putExtra("f1_purchase_price", edPurchasePrice.getText().toString().trim());
            getActivity().startActivityForResult(mIntent, AppConstants.ND_CLOSING_COSTS);
        }
    }

    @OnClick(R.id.tv_title_holding_costs)
    public void onHoldingCostsClick(){
        String f1Value=edPurchasePrice.getText().toString().trim();
        if(TextUtils.isEmpty(f1Value)){
            Toaster.show(R.string.hint_enter_purchase_price);
        }else {
            Intent mIntent = new Intent(getActivity(), HoldingCostsTPPActivity.class);
            mIntent.putExtra("data",ndHoldingCostRehab);
            mIntent.putExtra("f1_purchase_price", edPurchasePrice.getText().toString().trim());
            mIntent.putExtra("current_value", tvHoldingCosts.getText().toString().trim());
            getActivity().startActivityForResult(mIntent, AppConstants.ND_HOLDING_COSTS);
        }
    }

    @OnClick(R.id.tv_title_radint_costs)
    public void onRadiantCostsClick(){
        String f1Value=edPurchasePrice.getText().toString().trim();
        if(TextUtils.isEmpty(f1Value)){
            Toaster.show(R.string.hint_enter_purchase_price);
        }else {
            Intent mIntent = new Intent(getActivity(), RadiantBudgetTPPActivity.class);
            mIntent.putExtra("redial_budget",radiantBudgetSelOption);
            mIntent.putExtra("current_value", tvRadiantBudget.getText().toString().trim());
            mIntent.putExtra("f1_purchase_price", edPurchasePrice.getText().toString().trim());
            getActivity().startActivityForResult(mIntent, AppConstants.ND_RADIANT_COSTS);
        }
    }


    @OnClick(R.id.btn_continue)
    public void onContinueClick(){
        mPurchasePrice=edPurchasePrice.getText().toString().trim();
        mClosingCost=tvClosingCosts.getText().toString().trim();
        mHoldingCost=tvHoldingCosts.getText().toString().trim();
        mRadiantCosts=tvRadiantBudget.getText().toString().trim();
        mIncludingCHInLoan=sIncClosHoldingCosts.getSelectedItem().toString();
        mProjectRPM=sRadiantPeriod.getSelectedItem().toString();
        RadiantCalulator radiantCalulator=((NewDealFragment)getParentFragment()).getCalculatorInstance();
        radiantCalulator.setValuesForND2(mPurchasePrice,mClosingCost,mHoldingCost,mHoldingCostType,mIncludingCHInLoan,mRadiantCosts,mProjectRPM,radiantBudgetSelOption);
        //// TODO: 21/6/16 Move to next Screen...
        ((NewDealFragment)getParentFragment()).onNewDeal2Response();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==getActivity().RESULT_OK){
            Bundle bundle=data.getExtras();
            if(requestCode==AppConstants.ND_CLOSING_COSTS){
                ndClosingCostRehab= (NDClosingCostRehab) bundle.getSerializable("data");
                if(ndClosingCostRehab.getClosingCostsOption()==0){
                    closingCosts=ndClosingCostRehab.getTotalClosingCosts()+"";
                }else if(ndClosingCostRehab.getClosingCostsOption()==1){
                    closingCosts=ndClosingCostRehab.getTotalDICosts()+"";
                }else if(ndClosingCostRehab.getClosingCostsOption()==2){
                    closingCosts=ndClosingCostRehab.getTotalPercentage()+"";
                }
//                closingCosts=bundle.getString("value");
                tvClosingCosts.setText(closingCosts);


            }else if(requestCode == AppConstants.ND_HOLDING_COSTS){
                ndHoldingCostRehab= (NDHoldingCostRehab) bundle.getSerializable("data");
                if(ndHoldingCostRehab.getHoldingCostsType()==0){
                    mHoldingCostType="QLS";
                    holdingCost=ndHoldingCostRehab.getTotalHoldingCosts()+"";
                }else{
                    holdingCost=ndHoldingCostRehab.getTotalCostItems()+"";
                    mHoldingCostType="DI";
                }
                tvHoldingCosts.setText(holdingCost);
            }else if(requestCode==AppConstants.ND_RADIANT_COSTS){
                radiantBudgetSelOption= (RadiantBudgetSelOption) data.getExtras().getSerializable("redial_budget");
                if(radiantBudgetSelOption.getEnterBudget()==1){
                    tvRadiantBudget.setText(radiantBudgetSelOption.getDetailInput().getTotalBudget()+"");
                }else{
                    tvRadiantBudget.setText(radiantBudgetSelOption.getTotalAmount()+"");
                }
//                radiantBudget=bundle.getString("value");
//                tvRadiantBudget.setText(radiantBudget);
//                radiantBudgetSelOption.setEnterBudget(bundle.getInt("budget_option"));
//                radiantBudgetSelOption.setOverrideOption(bundle.getInt("override_option"));
            }

        }
    }






    private void onSetInitDataOfDetailInput(){
        if (radiantBudgetSelOption == null) {
            radiantBudgetSelOption=new RadiantBudgetSelOption();
            radiantBudgetSelOption.setTotalAmount(20000);
            radiantBudgetSelOption.setEnterBudget(0);
            radiantBudgetSelOption.setOverrideOption(1);

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

    /**
     * Holding Cost Options
     */
    private void initHoldingCostValue(){
        if(ndHoldingCostRehab==null){
            ndHoldingCostRehab=new NDHoldingCostRehab();
            ndHoldingCostRehab.setHoldingCostsType(0);
            ndHoldingCostRehab.setTotalHoldingCosts(1500);
            String[] holdingItems=getResources().getStringArray(R.array.nd_holding_costs);

            List<NDHoldingCostRehab.CostItem> costItemList=new ArrayList<>();
            for(int i=0;i<holdingItems.length;i++){
                NDHoldingCostRehab.CostItem item=new NDHoldingCostRehab.CostItem();
                item.setTitle(holdingItems[i]);
                item.setViewType(1);
                costItemList.add(item);
            }
            NDHoldingCostRehab.CostItem item=new NDHoldingCostRehab.CostItem();
            item.setViewType(2);
            item.setTitle("+ Add Item");
            costItemList.add(item);
            ndHoldingCostRehab.setListCostItem(costItemList);
        }
    }


    private void initClosingCosts(){
        if(ndClosingCostRehab==null){
            ndClosingCostRehab=new NDClosingCostRehab();
            ndClosingCostRehab.setClosingCostsOption(0);
            ndClosingCostRehab.setTotalClosingCosts(1500);
            ndClosingCostRehab.setPerOfPurchase(0);
            ndClosingCostRehab.setTotalPercentage(0);

            String[] closingItems=getResources().getStringArray(R.array.nd_closing_costs);

            List<NDClosingCostRehab.ClosingCostItem> costItemList=new ArrayList<>();
            for(int i=0;i<closingItems.length;i++){
                NDClosingCostRehab.ClosingCostItem item=new NDClosingCostRehab.ClosingCostItem();
                item.setTitle(closingItems[i]);
                item.setViewType(1);
                costItemList.add(item);
            }
            NDClosingCostRehab.ClosingCostItem item=new NDClosingCostRehab.ClosingCostItem();
            item.setViewType(2);
            item.setTitle("+ Add Item");
            costItemList.add(item);
            ndClosingCostRehab.setListCostItem(costItemList);
        }
    }


}
