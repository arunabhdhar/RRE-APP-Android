package org.rehab.app.ui.fragment.details_property_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.rehab.app.R;
import org.rehab.app.models.response.DealItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 */
public class RadiantInfoFragment extends Fragment {


    @BindView(R.id.tv_add_financing)
    TextView tvAddFinancing;
    @BindView(R.id.tv_financing_cap)
    TextView tvFinancingCap;
    @BindView(R.id.tv_max_per_financed)
    TextView tvMaxPerFiananced;
    @BindView(R.id.tv_origination_points)
    TextView tvOriginationPoints;
    @BindView(R.id.tv_closing_costs)
    TextView tvClosingCosts;
    @BindView(R.id.tv_pcciil)
    TextView tvPCCIIL;
    @BindView(R.id.tv_interest_rate)
    TextView tvInterestRate;
    @BindView(R.id.tv_interest_payment_req)
    TextView tvInterestPaymentReq;
    @BindView(R.id.tv_split_profit_w_lender)
    TextView tvSplitProfitWLender;
    @BindView(R.id.tv_profit_per_to_lender)
    TextView tvProfitPerToLender;
    @BindView(R.id.ll_add_financing_yes)
    LinearLayout llAddFinancingYes;
    @BindView(R.id.ll_profit_per_lender)
    LinearLayout llProfitPerLender;

    private DealItem dealItem;

    public void setData(DealItem dealItem){
        this.dealItem=dealItem;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragement_radiant_info,container,false);
        ButterKnife.bind(this,view);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvAddFinancing.setText(dealItem.getFinancingCash().equals("1.00")?"Yes":"No");
        if(!dealItem.getFinancingCash().equals("1.00")){
            llAddFinancingYes.setVisibility(View.GONE);
        }
        if(!dealItem.getSplitBackendProfitsWithLender().equals("1.00")){
            llProfitPerLender.setVisibility(View.GONE);
        }
        tvFinancingCap.setText(dealItem.getArvCost().equals("1.00")?"ARV":"Cost");
        tvMaxPerFiananced.setText(dealItem.getFinancedCostMaxPer());
        tvOriginationPoints.setText(dealItem.getOriginationDiscountPoints());
        tvClosingCosts.setText(dealItem.getOthrClsCostPaidLander());
        tvPCCIIL.setText(dealItem.getCostsUpfrontBackend().equals("1.00")?"Yes":"No");
        tvInterestRate.setText(dealItem.getInterestRate());
        tvInterestPaymentReq.setText(dealItem.getInterestPaymentDuringRehab().equals("1.00")?"Yes":"No");
        tvSplitProfitWLender.setText(dealItem.getSplitBackendProfitsWithLender().equals("1.00")?"Yes":"No");
        tvProfitPerToLender.setText(dealItem.getPreTaxProfitDoesLenderGet());
    }

}
