package org.rehab.app.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.squareup.picasso.Picasso;

import org.rehab.app.R;
import org.rehab.app.adapter.PropertyDetailsPagerAdapter;
import org.rehab.app.constants.AppConstants;
import org.rehab.app.models.response.DealItem;
import org.rehab.app.ui.fragment.details_property_fragment.FlipAnalysisFragment;
import org.rehab.app.ui.fragment.details_property_fragment.HoldAndRentAnalysisFragment;
import org.rehab.app.ui.fragment.details_property_fragment.InformationFragment;
import org.rehab.app.ui.fragment.details_property_fragment.PurchaseAssumptionsFragment;
import org.rehab.app.ui.fragment.details_property_fragment.PurchaseFragment;
import org.rehab.app.ui.fragment.details_property_fragment.RadiantInfoFragment;
import org.rehab.app.ui.fragment.details_property_fragment.RehabBudgetFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 */
public class PropertyDetailsActivity  extends AppCompatActivity{

    @BindView(R.id.vp_property)
    ViewPager vbProperty;
    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabsStrip;
    @BindView(R.id.rl_header_bg)
    RelativeLayout rlHeaderBg;
    @BindView(R.id.tv_property_name)
    TextView tvPropertyName;
    @BindView(R.id.tv_property_desc)
    TextView tvPropertyDesc;
    @BindView(R.id.iv_image)
    ImageView ivImage;

    private List<Fragment> mFragmentList=new ArrayList<>();
    private PropertyDetailsPagerAdapter propertyDetailsPAdapter;
    private PurchaseAssumptionsFragment purchaseAssumptionsFragment;
    private RadiantInfoFragment radiantInfoFragment;
    private FlipAnalysisFragment flipAnalysisFragment;
    private HoldAndRentAnalysisFragment holdAndRentAnalysisFragment;
    private InformationFragment informationFragment;
    private PurchaseFragment purchaseFragment;
    private RehabBudgetFragment rehabBudgetFragment;

    private  DealItem dataItem;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_details);
        ButterKnife.bind(this);
        dataItem= (DealItem) getIntent().getExtras().getSerializable("data");

        informationFragment=new InformationFragment();
        informationFragment.setData(dataItem);
        purchaseAssumptionsFragment=new PurchaseAssumptionsFragment();
        purchaseAssumptionsFragment.setData(dataItem);
        radiantInfoFragment=new RadiantInfoFragment();
        radiantInfoFragment.setData(dataItem);
        flipAnalysisFragment=new FlipAnalysisFragment();
        flipAnalysisFragment.setData(dataItem);
        holdAndRentAnalysisFragment=new HoldAndRentAnalysisFragment();
        holdAndRentAnalysisFragment.setData(dataItem);

        purchaseFragment=new PurchaseFragment();
        purchaseFragment.setData(dataItem);
        rehabBudgetFragment=new RehabBudgetFragment();
        rehabBudgetFragment.setData(dataItem);

        mFragmentList.add(informationFragment);
        mFragmentList.add(purchaseAssumptionsFragment);
        mFragmentList.add(radiantInfoFragment);
        mFragmentList.add(flipAnalysisFragment);
        mFragmentList.add(holdAndRentAnalysisFragment);
        mFragmentList.add(purchaseFragment);
        mFragmentList.add(rehabBudgetFragment);
        propertyDetailsPAdapter=new PropertyDetailsPagerAdapter(getSupportFragmentManager(),mFragmentList);
        vbProperty.setAdapter(propertyDetailsPAdapter);
        tabsStrip.setViewPager(vbProperty);
    }

    @Override
    protected void onStart() {
        super.onStart();
        tvPropertyName.setText(dataItem.getPropertyName());
        tvPropertyDesc.setText(dataItem.getPropertyStrtAddress());
        if(!TextUtils.isEmpty(dataItem.getPropertyImage())){
            Picasso.with(PropertyDetailsActivity.this).load(AppConstants.IMAGE_URL+dataItem.getPropertyImage()).into(ivImage);
        }else{
            ivImage.setImageResource(R.drawable.image_banner);
        }
    }

    @OnClick(R.id.iv_back)
    public void onBackClick(){
        finish();
    }
}
