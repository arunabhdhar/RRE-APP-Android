package org.rehab.app.ui.fragment.details_property_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.rehab.app.R;
import org.rehab.app.models.response.DealItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 */
public class FlipAnalysisFragment extends Fragment {


    @BindView(R.id.tv_q_after_repair_value)
    TextView tvAfterRepairValue;
    @BindView(R.id.tv_que_months_to_complete)
    TextView tvMonthToComplete;
    @BindView(R.id.tv_total_capital_needed)
    TextView tvTotalCapitalNeeded;
    @BindView(R.id.tv_max_per_financed)
    TextView tvMaxPerFinanced;
    @BindView(R.id.tv_actual_to_be_financed)
    TextView tvActualToBeFinanced;
    @BindView(R.id.tv_chciatl)
    TextView tvCHCIATL;
    @BindView(R.id.tv_total_loan_amount)
    TextView tvTotalLoanAmount;
    @BindView(R.id.tv_cash_required)
    TextView tvCashReq;
    @BindView(R.id.tv_total_allin_costs)
    TextView tvTotalAllInCosts;
    @BindView(R.id.tv_per_of_arv)
    TextView tvPerOfArv;
    @BindView(R.id.tv_projected_resale_price)
    TextView tvProjectedRescalePrice;
    @BindView(R.id.tv_project_cost_of_sale)
    TextView tvProjectCostOfSale;
    @BindView(R.id.tv_projected_profit)
    TextView tvProjectedProfit;
    @BindView(R.id.tv_roi_on_cash_inv)
    TextView tvRoiCashInv;
    @BindView(R.id.tv_roi_annualized)
    TextView tvRoiAnnunalized;
    @BindView(R.id.ll_finance_options)
    View llFinanceOptions;

    private DealItem dealItem;

    public void setData(DealItem dealItem){
        this.dealItem=dealItem;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragement_flip_analysis,container,false);
        ButterKnife.bind(this,view);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvAfterRepairValue.setText(dealItem.getArvForFlipStrgy1());
        tvMonthToComplete.setText(dealItem.getMonthsCompleteSaleAfterRehab());
        tvTotalCapitalNeeded.setText(dealItem.getTotalCapitalNeededStrgy1());
        tvMaxPerFinanced.setText(dealItem.getMaxDollarFinancedStrgy1());
        if(!dealItem.getFinancingCash().equals("1.00")){
            llFinanceOptions.setVisibility(View.GONE);
        }else {
            tvActualToBeFinanced.setText(dealItem.getActualFinancedNotClosholdStrgy1());
            tvCHCIATL.setText(dealItem.getClosHoldInterestLoanStrgy1());
            tvTotalLoanAmount.setText(dealItem.getTotalLoanAmountStrgy1());
        }
        tvCashReq.setText(dealItem.getCashRequiredOverlifeProjectStrgy1());
        tvTotalAllInCosts.setText(dealItem.getTotalAllinCostsEndRehabStrgy1());
        tvPerOfArv.setText(dealItem.getArvPerStrgy1());
        tvProjectedRescalePrice.setText(dealItem.getProjectedResalePriceStrgy1());
        tvProjectCostOfSale.setText(dealItem.getProjectedCostSalePerStrgy1());
        tvProjectedProfit.setText(dealItem.getProjectedProfitAfterLenderSplitStrgy1());
        tvRoiCashInv.setText(dealItem.getRociStrgy1());
        tvRoiAnnunalized.setText(dealItem.getRoiAnnualizedStrgy1());

    }

}
