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
public class RehabBudgetFragment extends Fragment {

    @BindView(R.id.tv_add_financing)
    TextView tvAddFinancing;
    @BindView(R.id.tv_financing_cap)
    TextView tvFinancingCap;
    @BindView(R.id.tv_max_per_financed)
    TextView tvMaxPerFinanced;
    @BindView(R.id.tv_origination_points)
    TextView tvOriginationPoints;
    @BindView(R.id.tv_closing_cost)
    TextView tvClosingCosts;
    @BindView(R.id.tv_points_closing_costs_include)
    TextView tvPointsClosingCostsInclude;
    @BindView(R.id.tv_interest_rate)
    TextView tvInterestRate;
    @BindView(R.id.tv_interest_payment_req)
    TextView tvInterestPaymentReq;
    @BindView(R.id.tv_split_profit_w_lender)
    TextView tvSplitProfitWLender;
    @BindView(R.id.tv_profit_per_to_lender)
    TextView tvProfitPerToLender;

    private DealItem dealItem;

    public void setData(DealItem dealItem){
        this.dealItem=dealItem;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_rehab_budget, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvAddFinancing.setText(Float.parseFloat(dealItem.getFinancingCash())==1?"Yes":"No");
        tvMaxPerFinanced.setText(dealItem.getFinancedCostMaxPer());
        tvOriginationPoints.setText(dealItem.getOriginationDiscountPoints());
        tvInterestRate.setText(dealItem.getInterestRate()+"");
        tvInterestPaymentReq.setText(dealItem.getCashRequiredStrgy2()+"");
        tvSplitProfitWLender.setText(Float.parseFloat(dealItem.getSplitBackendProfitsWithLender())==1?"Yes":"No");
        tvProfitPerToLender.setText(dealItem.getProfitRefiAfterLenderSplitStrgy2());

    }
}