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
public class PurchaseAssumptionsFragment extends Fragment {


    @BindView(R.id.tv_acquisition_price)
    TextView tvAcquisitionPrice;
    @BindView(R.id.tv_closing_cost)
    TextView tvClosingCost;
    @BindView(R.id.tv_estimate_holding_costs)
    TextView tvEstimateHoldingCosts;
    @BindView(R.id.tv_chctbiil)
    TextView tvCHCTBIIL;
    @BindView(R.id.tv_rbc)
    TextView tvRBC;
    @BindView(R.id.tv_project_duration)
    TextView tvProjectDuration;

    private DealItem dealItem;

    public void setData(DealItem dealItem){
        this.dealItem=dealItem;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragement_purchase_assumptions,container,false);
        ButterKnife.bind(this,view);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvAcquisitionPrice.setText(dealItem.getPurchasePrice());
        tvClosingCost.setText(dealItem.getCloseingCost());
        tvEstimateHoldingCosts.setText(dealItem.getHoldingCost());
        tvCHCTBIIL.setText(dealItem.getIncludeClosingHoldingCost().equals("0.00")?"Yes":"No");
        tvRBC.setText(dealItem.getRehabBudget());
        tvProjectDuration.setText(dealItem.getProjectRehabPeriod());
    }

}
