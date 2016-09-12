package org.rehab.app.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.rehab.app.R;
import org.rehab.app.adapter.CalculatorAdapter;
import org.rehab.app.async.ASFileUploadAsync;
import org.rehab.app.constants.AppConstants;
import org.rehab.app.interfaces.IASCommon;
import org.rehab.app.models.request.ReqBase;
import org.rehab.app.models.response.ResBase;
import org.rehab.app.preferences.MySharedPreference;
import org.rehab.app.utils.Logger;
import org.rehab.app.utils.NetworkStatus;
import org.rehab.app.utils.ProgressDialogUtil;
import org.rehab.app.utils.RadiantCalulator;
import org.rehab.app.utils.TakeImageClass;
import org.rehab.app.utils.Toaster;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 */
public class NewDealFragment extends Fragment implements IASCommon{

    @BindView(R.id.vp_fragments)
    ViewPager mViewPager;
    @BindView(R.id.tv_first_fragment)
    TextView tvFirstFragment;
    @BindView(R.id.tv_second_fragment)
    TextView tvSecondFragment;
    @BindView(R.id.tv_third_fragment)
    TextView tvThirdFragment;
    @BindView(R.id.tv_fourth_fragment)
    TextView tvFourthFragment;
    @BindView(R.id.tv_fifth_fragment)
    TextView tvFifthFragment;

    private ArrayList<Fragment> mFragmentList=new ArrayList<>();

    private NewDealFirstFragment newDealFirstFragment;
    private NewDealSecondFragment newDealSecondFragment;
    private NewDealThirdFragment newDealThirdFragment;
    private NewDealFourthFragment newDealFourthFragment;
    private NewDealFifthFragment newDealFifthFragment;
    private CalculatorAdapter calculatorAdapter;

    private RadiantCalulator radiantCalulator;
    private  int previousTabSelected=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView=inflater.inflate(R.layout.fragment_new_deal,container,false);
        ButterKnife.bind(this,mView);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        newDealFirstFragment = new NewDealFirstFragment();
        newDealSecondFragment = new NewDealSecondFragment();
        newDealThirdFragment = new NewDealThirdFragment();
        newDealFourthFragment = new NewDealFourthFragment();
        newDealFifthFragment = new NewDealFifthFragment();

        mFragmentList.add(newDealFirstFragment);
        mFragmentList.add(newDealSecondFragment);
        mFragmentList.add(newDealThirdFragment);
        mFragmentList.add(newDealFourthFragment);
        mFragmentList.add(newDealFifthFragment);

        calculatorAdapter = new CalculatorAdapter(getActivity(), getChildFragmentManager(), mFragmentList);
        mViewPager.setAdapter(calculatorAdapter);

        tvFirstFragment.setSelected(true);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Logger.e("onPageScrolled:-->" + position);
            }

            @Override
            public void onPageSelected(int position) {
                tvFirstFragment.setSelected(false);
                tvSecondFragment.setSelected(false);
                tvThirdFragment.setSelected(false);
                tvFourthFragment.setSelected(false);
                tvFifthFragment.setSelected(false);
                if (position == 0) {
                    tvFirstFragment.setSelected(true);
                } else if (position == 1) {
                    tvSecondFragment.setSelected(true);
                } else if (position == 2) {
                    tvThirdFragment.setSelected(true);
                } else if (position == 3) {
                    tvFourthFragment.setSelected(true);
                } else if (position == 4) {
                    tvFifthFragment.setSelected(true);
                }
                Logger.e("onPageSelected:-->" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Logger.e("onPageScrollStateChanged:-->" + state);
            }
        });

        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    @OnClick(R.id.tv_first_fragment)
    public void onFirstClick(){
        mViewPager.setCurrentItem(0);
        previousTabSelected=0;
    }

    @OnClick(R.id.tv_second_fragment)
    public void onSecondClick(){
        if(previousTabSelected>0) {
            mViewPager.setCurrentItem(1);
            previousTabSelected=1;
        }
    }

    @OnClick(R.id.tv_third_fragment)
    public void onThirdClick(){
        if(previousTabSelected>1) {
            mViewPager.setCurrentItem(2);
            previousTabSelected=2;
        }
    }
    @OnClick(R.id.tv_fourth_fragment)
    public void onFourthClick(){
        if(previousTabSelected>2) {
            mViewPager.setCurrentItem(3);
            previousTabSelected=3;
        }
    }

    @OnClick(R.id.tv_fifth_fragment)
    public void onFifthClick(){
        if(previousTabSelected>3) {
            mViewPager.setCurrentItem(4);
            previousTabSelected=4;
        }
    }

    /**
     * Create the instance of the Calculator.
     */
    public RadiantCalulator getCalculatorInstance(){
        if(radiantCalulator==null){
            radiantCalulator=new RadiantCalulator();
        }
        return radiantCalulator;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== AppConstants.ND_CLOSING_COSTS || requestCode==AppConstants.ND_HOLDING_COSTS
                || requestCode==AppConstants.ND_RADIANT_COSTS){
            newDealSecondFragment.onActivityResult(requestCode,resultCode,data);
        }else if(requestCode==AppConstants.ND_EX2_OPER_PRO_INCOME || requestCode==AppConstants.ND_EX2_OPER_PRO_EXPENSE){
            newDealFifthFragment.onActivityResult(requestCode,resultCode,data);
        }else if(requestCode == TakeImageClass.REQUEST_CODE_TAKE_PICTURE || requestCode == TakeImageClass.REQUEST_CODE_GALLERY
                || requestCode == TakeImageClass.REQUEST_CODE_CROP_IMAGE){
            newDealFirstFragment.onActivityResult(requestCode,resultCode,data);
        }

    }


    public void onNewDeal1Response(){
        previousTabSelected=1;
        mViewPager.setCurrentItem(1);

    }
    public void onNewDeal2Response(){
        previousTabSelected=2;
        mViewPager.setCurrentItem(2);
    }

    public void onNewDeal3Response() {
        previousTabSelected=3;
        mViewPager.setCurrentItem(3);
        newDealFourthFragment.valueSet();
    }

    public void onNewDeal4Response() {
        previousTabSelected=4;
        mViewPager.setCurrentItem(4);
    }
    public HashMap<String,String> request() {
        HashMap<String, String> mValues = new HashMap<String, String>();
        mValues.put("user_device_type", "1");
        mValues.put("service_access_key",AppConstants.APP_KEY);
        mValues.put("user_id", MySharedPreference.getInstance(getActivity()).getUserId());
        mValues.put("property_name",newDealFirstFragment.propertyTitle);
        mValues.put("property_strt_address",newDealFirstFragment.location);
        mValues.put("property_sq_feet",newDealFirstFragment.area);
        mValues.put("agent_name",newDealFirstFragment.agentName);
        mValues.put("property_bedrooms",newDealFirstFragment.noOfBedroom);
        mValues.put("property_baths",newDealFirstFragment.noOfBathroom);
        mValues.put("property_built_year",newDealFirstFragment.buildYear);
        mValues.put("newdeal_image",newDealFirstFragment.mProfileImagePath);

        mValues.put("purchase_price",newDealSecondFragment.mPurchasePrice);
        mValues.put("closeing_cost",newDealSecondFragment.mClosingCost);
        mValues.put("holding_cost",newDealSecondFragment.mHoldingCost);
        mValues.put("include_closing_holding_cost",newDealSecondFragment.mIncludingCHInLoan);
        mValues.put("rehab_budget",newDealSecondFragment.mRadiantCosts);
        mValues.put("project_rehab_period",newDealSecondFragment.mProjectRPM);

        mValues.put("financing_cash",(newDealThirdFragment.sFinancingUsed.getSelectedItemPosition()+1)+"");
        mValues.put("arv_cost",(newDealThirdFragment.sLenderCaps.getSelectedItemPosition()+1)+"");
        mValues.put("financed_cost_max_per",newDealThirdFragment.maxPerCost);
        mValues.put("origination_discount_points",newDealThirdFragment.oriDisPoints);
        mValues.put("othr_cls_cost_paid_lander",newDealThirdFragment.otherClosingCosts);
        mValues.put("costs_upfront_backend",(newDealThirdFragment.sPointsClosingUpfront.getSelectedItemPosition()+1)+"");
        mValues.put("interest_rate",newDealThirdFragment.interestRate);
        mValues.put("interest_payment_during_rehab",(newDealThirdFragment.sInterestPaymentDurRad.getSelectedItemPosition()+1)+"");
        mValues.put("split_backend_profits_with_lender",(newDealThirdFragment.sSplitBackEndProfit.getSelectedItemPosition()+1)+"");
        mValues.put("pre_tax_profit_does_lender_get",newDealThirdFragment.whatPerPretaxProfit);

        mValues.put("arv_for_flip_strgy1",newDealFourthFragment.etAfterRepairValue.getText().toString());
        mValues.put("months_complete_sale_after_rehab",newDealFourthFragment.sMonthsCompleteSales.getSelectedItem().toString());
        mValues.put("total_capital_needed_strgy1",newDealFourthFragment.tvTotalCapitalNeeded.getText().toString());
        mValues.put("max_dollar_financed_strgy1",newDealFourthFragment.tvMaxThatCanBe.getText().toString());
        mValues.put("actual_financed_not_closhold_strgy1",newDealFourthFragment.tvActualToBeFinanced.getText().toString());
        mValues.put("clos_hold_interest_loan_strgy1",newDealFourthFragment.tvClosingHoldingCostsInterest.getText().toString());
        mValues.put("total_loan_amount_strgy1",newDealFourthFragment.tvTotalLoanAmount.getText().toString());
        mValues.put("cash_required_overlife_project_strgy1",newDealFourthFragment.tvCashRequired.getText().toString());
        mValues.put("total_allin_costs_end_rehab_strgy1",newDealFourthFragment.tvTotalAllinCosts.getText().toString());
        mValues.put("arv_per_strgy1",newDealFourthFragment.tvPerOfArv.getText().toString());
        mValues.put("projected_resale_price_strgy1",newDealFourthFragment.etProjectedResalePrice.getText().toString());
        mValues.put("projected_cost_sale_per_strgy1",newDealFourthFragment.etProjCostOfSale.getText().toString());
        mValues.put("projected_profit_after_lender_split_strgy1",newDealFourthFragment.tvProjectedProfit.getText().toString());
        mValues.put("roci_strgy1",newDealFourthFragment.tvReturnOfCash.getText().toString());
        mValues.put("roi_annualized_strgy1",newDealFourthFragment.tvRoiAnnualized.getText().toString());

        mValues.put("arv_rent_strgy2",newDealFifthFragment.edAfterRepairValue.getText().toString());
        mValues.put("months_rent_after_rehab_period_over_strgy2",newDealFifthFragment.sMonthsToComplete.getSelectedItem().toString());
        mValues.put("total_capital_needed_strgy2",newDealFifthFragment.tv_total_capital_needed.getText().toString());
        mValues.put("max_dollar_canbe_financed_strgy2",newDealFifthFragment.tv_max_that_can_be.getText().toString());
        mValues.put("actual_financed_not_closholding_strgy2",newDealFifthFragment.tv_actual_to_be_financed.getText().toString());
        mValues.put("closholding_costs_interest_added_loan_strgy2",newDealFifthFragment.tv_closing_holding_costs_interest.getText().toString());
        mValues.put("total_loan_amount_strgy2",newDealFifthFragment.tv_total_loan_amount.getText().toString());
        mValues.put("cash_required_strgy2",newDealFifthFragment.tv_cash_required.getText().toString());
        mValues.put("total_allin_costs_end_rehab_strgy2",newDealFifthFragment.tv_total_allin_costs.getText().toString());
        mValues.put("arv_per_strgy2",newDealFifthFragment.tv_per_of_arv.getText().toString());
        mValues.put("projected_operating_income_strgy2",newDealFifthFragment.tv_projected_operation_income.getText().toString());
        mValues.put("projected_operating_expenses_strgy2",newDealFifthFragment.tv_projected_operation_expenses.getText().toString());
        mValues.put("net_operating_income_monthly_strgy2",newDealFifthFragment.tv_net_operating_income.getText().toString());
        mValues.put("refinance_permanent_financing_strgy2",(newDealFifthFragment.s_refinance_into_permanent.getSelectedItemPosition()+1)+"");
        mValues.put("refinance_per_appraisal_arv_strgy2",newDealFifthFragment.ed_refi_per_of_app.getText().toString());
        mValues.put("new_mortgage_rate_strgy2",newDealFifthFragment.ed_new_mortgage_rate.getText().toString());
        mValues.put("amortization_years_interest_only_strgy2",newDealFifthFragment.s_of_amo_year.getSelectedItem().toString());
        mValues.put("refi_discount_points_misc_costs_strgy2",newDealFifthFragment.ed_refi_discount_points.getText().toString());
        mValues.put("new_mtge_pmnt_monthly_strgy2",newDealFifthFragment.tv_new_mtge_pmnt.getText().toString());
        mValues.put("refi_loan_amount_strgy2",newDealFifthFragment.tv_refi_loan_amount.getText().toString());
        mValues.put("cash_out_refi_strgy2",newDealFifthFragment.tv_cash_out_at_refi.getText().toString());
        mValues.put("profit_refi_after_lender_split_strgy2",newDealFifthFragment.tv_profit_at_refi.getText().toString());
        mValues.put("roi_cash_invested_annualized_strgy2",newDealFifthFragment.tv_roi_on_cash_inv.getText().toString());
        mValues.put("original_money_tiedup_after_refi_strgy2",newDealFifthFragment.tv_original_money_tied_up.getText().toString());
        mValues.put("equity_left_deal_after_refi_strgy2",newDealFifthFragment.tv_equity_left_in_deal.getText().toString());
        mValues.put("cash_flow_monthly_pretax_strgy2",newDealFifthFragment.tv_cash_flow.getText().toString());
        mValues.put("coc_annual_pretax_strgy2",newDealFifthFragment.tv_cash_on_cash.getText().toString());
        mValues.put("property_dcr_strgy2",newDealFifthFragment.tv_property_dcr.getText().toString());
        mValues.put("payback_period_strgy2",newDealFifthFragment.tv_payback_period.getText().toString());
        mValues.put("caprate_property_based_costbasis_strgy2",newDealFifthFragment.tv_cap_rate_property.getText().toString());
        mValues.put("caprate_property_based_arv_strgy2",newDealFifthFragment.tv_cap_rate_of_property_based_on_cost.getText().toString());

        return mValues;
    }

    public void saveDataOnServer(){
        if(NetworkStatus.isInternetOn(getActivity())) {
            ProgressDialogUtil.getInstance().showProgressDialog(getActivity());
            ASFileUploadAsync<ReqBase, ResBase> mReqRespAsync = new ASFileUploadAsync<ReqBase, ResBase>(getActivity(),NewDealFragment.this, AppConstants.NEW_DEAL_API,request(), ResBase.class, "new_deal");
            mReqRespAsync.execute();
        }else{
            Toaster.show(R.string.err_internet_connection_error);
        }
    }

    @Override
    public void onResponse(Object type, Object data) {
        if(type.toString().equals("new_deal")){
            if(data instanceof ResBase){
                ResBase obj= (ResBase) data;
                if(obj.getErrorCode()==0){
                    Toaster.show(obj.getMessage());
                    mViewPager.setCurrentItem(0);
                    newDealFirstFragment.clearAll();
                    newDealSecondFragment.clearAll();
                    ProgressDialogUtil.getInstance().dismissProgressDialog();
                }else{
                    ProgressDialogUtil.getInstance().dismissProgressDialog();
                    Toaster.show(obj.getMessage());
                }
            }else{
                ProgressDialogUtil.getInstance().dismissProgressDialog();
                Toaster.show(R.string.err_internet_connection_error);
            }
        }
    }
}

