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
public class PurchaseFragment extends Fragment {



    @BindView(R.id.tv_acquisition_price)
    TextView tvAcquisitionPrice;
    @BindView(R.id.tv_closing_costs)
    TextView tvClosingCosts;
    @BindView(R.id.tv_estimate_holding_costs)
    TextView tvEstimateHoldingCosts;
    @BindView(R.id.tv_closing_holding_costs)
    TextView tvClosingHoldingCosts;
    @BindView(R.id.tv_radiant_budget)
    TextView tvRadiantBudget;
    @BindView(R.id.tv_project_duration)
    TextView tvProjectDuration;





    private DealItem dealItem;

    public void setData(DealItem dealItem){
        this.dealItem=dealItem;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_purchase, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvAcquisitionPrice.setText(dealItem.getPurchasePrice());
        tvClosingCosts.setText(dealItem.getCloseingCost());
        tvEstimateHoldingCosts.setText(dealItem.getHoldingCost());
        tvClosingHoldingCosts.setText(dealItem.getIncludeClosingHoldingCost()+"");
        tvRadiantBudget.setText(dealItem.getRehabBudget());
        tvProjectDuration.setText(dealItem.getProjectRehabPeriod()+"");
    }
}