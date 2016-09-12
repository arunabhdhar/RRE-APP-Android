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
public class HoldAndRentAnalysisFragment extends Fragment {


    @BindView(R.id.tv_q_after_repair_value_rent)
    TextView tvAfterRepairValueRent;
    @BindView(R.id.tv_que_months_to_rent)
    TextView tvMonthToRent;
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
    @BindView(R.id.tv_projected_operation_income)
    TextView tvProjectedRescalePrice;
    @BindView(R.id.tv_projected_operation_expenses)
    TextView tvProjectCostOfSale;
    @BindView(R.id.tv_net_operating_income)
    TextView tvProjectedProfit;
    @BindView(R.id.tv_que_refinance_into_permanent)
    TextView tvRefiIntoPermanent;
    @BindView(R.id.tv_que_refi_per_of_app)
    TextView tvRefiPerOfApp;
    @BindView(R.id.tv_new_mtge_rate)
    TextView tvNewMtgeRate;
    @BindView(R.id.tv_no_of_amort_year)
    TextView tvNoOfAmortYear;
    @BindView(R.id.tv_que_refi_discount_points)
    TextView tvRefiDiscountPoints;
    @BindView(R.id.tv_new_mtge_pmnt)
    TextView tvNewMtgePmnt;
    @BindView(R.id.tv_mtge_amnt)
    TextView tvMortageAmount;
    @BindView(R.id.tv_cash_out_at_refi)
    TextView tvCashOutAtRefi;
    @BindView(R.id.tv_profit_at_refi)
    TextView tvProfitAtRefi;
    @BindView(R.id.tv_roi_on_cash_inv)
    TextView tvRoiOnCashInv;
    @BindView(R.id.tv_original_money_tied_up)
    TextView tvOriginalMoneyTiedUp;
    @BindView(R.id.tv_equity_left_in_deal)
    TextView tvEquityLeftInDeal;
    @BindView(R.id.tv_cash_flow)
    TextView tvCashFlow;
    @BindView(R.id.tv_cash_on_cash_annual)
    TextView tvCashOnCash;
    @BindView(R.id.tv_property_dcr)
    TextView tvPropertyDCR;
    @BindView(R.id.tv_payback_period)
    TextView tvPaybackPeriod;
    @BindView(R.id.tv_cash_rate_of_prop)
    TextView tvCashRateOfProp;
    @BindView(R.id.tv_cash_rate_of_prop_based_on_arv)
    TextView tvCashRateOfPropOnARV;

    private DealItem dealItem;

    public void setData(DealItem dealItem){
        this.dealItem=dealItem;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragement_hold_and_rent_analysis,container,false);
        ButterKnife.bind(this,view);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvAfterRepairValueRent.setText(dealItem.getArvRentStrgy2());
        tvMonthToRent.setText(dealItem.getMonthsRentAfterRehabPeriodOverStrgy2());
        tvTotalCapitalNeeded.setText(dealItem.getTotalCapitalNeededStrgy2());
        tvMaxPerFinanced.setText(dealItem.getMaxDollarCanbeFinancedStrgy2());
        tvActualToBeFinanced.setText(dealItem.getActualFinancedNotClosholdingStrgy2());
        tvCHCIATL.setText(dealItem.getClosholdingCostsInterestAddedLoanStrgy2());
        tvTotalLoanAmount.setText(dealItem.getTotalLoanAmountStrgy2());
        tvCashReq.setText(dealItem.getCashRequiredStrgy2());
        tvTotalAllInCosts.setText(dealItem.getTotalAllinCostsEndRehabStrgy2());
        tvPerOfArv.setText(dealItem.getArvPerStrgy2());
        tvProjectedRescalePrice.setText(dealItem.getProjectedOperatingIncomeStrgy2());
        tvProjectCostOfSale.setText(dealItem.getProjectedOperatingExpensesStrgy2());
        tvProjectedProfit.setText(dealItem.getNetOperatingIncomeMonthlyStrgy2());
        tvRefiIntoPermanent.setText(dealItem.getRefinancePermanentFinancingStrgy2().equals("1.00")?"Yes":"No");
        tvRefiPerOfApp.setText(dealItem.getRefinancePerAppraisalArvStrgy2());
        tvNewMtgeRate.setText(dealItem.getNewMortgageRateStrgy2());
        tvNoOfAmortYear.setText(dealItem.getAmortizationYearsInterestOnlyStrgy2());
        tvRefiDiscountPoints.setText(dealItem.getRefiDiscountPointsMiscCostsStrgy2());
        tvNewMtgePmnt.setText(dealItem.getNewMtgePmntMonthlyStrgy2());
        tvMortageAmount.setText(dealItem.getRefiLoanAmountStrgy2());
        tvCashOutAtRefi.setText(dealItem.getCashOutRefiStrgy2());
        tvProfitAtRefi.setText(dealItem.getProfitRefiAfterLenderSplitStrgy2());
        tvRoiOnCashInv.setText(dealItem.getRoiCashInvestedAnnualizedStrgy2());
        tvOriginalMoneyTiedUp.setText(dealItem.getOriginalMoneyTiedupAfterRefiStrgy2());
        tvEquityLeftInDeal.setText(dealItem.getEquityLeftDealAfterRefiStrgy2());
        tvCashFlow.setText(dealItem.getCashFlowMonthlyPretaxStrgy2());
        tvCashOnCash.setText(dealItem.getCocAnnualPretaxStrgy2().equals("0.00")?"Infinite":dealItem.getCocAnnualPretaxStrgy2());
        tvPropertyDCR.setText(dealItem.getPropertyDcrStrgy2());
        tvPaybackPeriod.setText(dealItem.getPaybackPeriodStrgy2().equals("0.00")?"No Cash Tied Up to Pay Back!":dealItem.getPaybackPeriodStrgy2());
        tvCashRateOfProp.setText(dealItem.getCapratePropertyBasedCostbasisStrgy2());
        tvCashRateOfPropOnARV.setText(dealItem.getCapratePropertyBasedArvStrgy2());
       
    }

}
