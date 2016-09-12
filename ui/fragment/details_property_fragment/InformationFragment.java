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
public class InformationFragment extends Fragment {


    @BindView(R.id.tv_property_title)
    TextView tvPropertyTitle;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_agent_name)
    TextView tvAgentName;
    @BindView(R.id.tv_no_of_bedroom)
    TextView tvNoOfBedRooms;
    @BindView(R.id.tv_no_of_bathrrom)
    TextView tvNoOfBathroom;
    @BindView(R.id.tv_year_of_build)
    TextView tvYearOfBuild;

    private DealItem dealItem;

    public void setData(DealItem dealItem){
        this.dealItem=dealItem;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_information, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvPropertyTitle.setText(dealItem.getPropertyName());
        tvLocation.setText(dealItem.getPropertyStrtAddress());
        tvArea.setText(dealItem.getPropertySqFeet());
        tvAgentName.setText(dealItem.getAgentName());
        tvNoOfBathroom.setText(dealItem.getPropertyBaths()+"");
        tvNoOfBedRooms.setText(dealItem.getPropertyBedrooms()+"");
        tvYearOfBuild.setText(dealItem.getPropertyBuiltYear()+"");
    }
}