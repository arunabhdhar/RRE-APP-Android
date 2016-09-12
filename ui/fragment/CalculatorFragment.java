package org.rehab.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.rehab.app.R;
import org.rehab.app.adapter.CalculatorAdapter;
import org.rehab.app.async.ReqRespAsync;
import org.rehab.app.constants.AppConstants;
import org.rehab.app.interfaces.IASCommon;
import org.rehab.app.models.request.ReqBase;
import org.rehab.app.models.response.ResBase;
import org.rehab.app.preferences.MySharedPreference;
import org.rehab.app.utils.HideKeyboard;
import org.rehab.app.utils.Logger;
import org.rehab.app.utils.NetworkStatus;
import org.rehab.app.utils.ProgressDialogUtil;
import org.rehab.app.utils.Toaster;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 */
public class CalculatorFragment extends Fragment implements IASCommon{

    @BindView(R.id.vp_fragments)
    ViewPager mViewPager;
    @BindView(R.id.tv_first_fragment)
    TextView tvFirstFragment;
    @BindView(R.id.tv_second_fragment)
    TextView tvSecondFragment;
    @BindView(R.id.tv_third_fragment)
    TextView tvThirdFragment;

    private ArrayList<Fragment> mFragmentList=new ArrayList<>();
    private QuickCalculatorFirstFragment quickCalculatorFirstFragment;
    private QuickCalculatorSecondFragment quickCalculatorSecondFragment;
    private QuickCalculatorThirdFragment quickCalculatorThirdFragment;
    private CalculatorAdapter calculatorAdapter;
    private Float secondFragmentResult;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView=inflater.inflate(R.layout.fragment_quick_cal,container,false);
        ButterKnife.bind(this,mView);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        quickCalculatorFirstFragment=new QuickCalculatorFirstFragment();
        quickCalculatorSecondFragment=new QuickCalculatorSecondFragment();
        quickCalculatorThirdFragment=new QuickCalculatorThirdFragment();
        mFragmentList.add(quickCalculatorFirstFragment);
        mFragmentList.add(quickCalculatorSecondFragment);
        mFragmentList.add(quickCalculatorThirdFragment);
        calculatorAdapter=new CalculatorAdapter(getActivity(),getChildFragmentManager(),mFragmentList);
        mViewPager.setAdapter(calculatorAdapter);

        tvFirstFragment.setSelected(true);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Logger.e("onPageScrolled:-->"+position);
            }

            @Override
            public void onPageSelected(int position) {

                tvFirstFragment.setSelected(false);
                tvSecondFragment.setSelected(false);
                tvThirdFragment.setSelected(false);
                if(position==0){
                    tvFirstFragment.setSelected(true);
                }else if(position==1){
                    tvSecondFragment.setSelected(true);
                }else if(position==2){
                    tvThirdFragment.setSelected(true);
                }
                Logger.e("onPageSelected:-->"+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Logger.e("onPageScrollStateChanged:-->"+state);
            }
        });

        mViewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });
    }


    @OnClick(R.id.tv_first_fragment)
    public void onFirstClick(){
        mViewPager.setCurrentItem(0);
    }

    @OnClick(R.id.tv_second_fragment)
    public void onSecondClick(){
        if(quickCalculatorFirstFragment.calculateData(false)){
            mViewPager.setCurrentItem(1);
        }else{
            Toaster.show("Enter fields information.");
        }
    }

    @OnClick(R.id.tv_third_fragment)
    public void onThirdClick(){
        if(quickCalculatorSecondFragment.calculatedData(false)){
            mViewPager.setCurrentItem(2);
        }else{
            Toaster.show("Enter fields information.");
        }

    }

    /**
     * Callback from the first child fragment.
     */
    public void onFirstFragRes() {
        mViewPager.setCurrentItem(1);
    }

    /**
     * Set the AVR * max_per_avr Value
     * @param calculatedValue
     */
    public void changeSFCalVal(Float calculatedValue){
        this.secondFragmentResult=calculatedValue;
        quickCalculatorThirdFragment.setBuyerCost(secondFragmentResult);
    }
    /**
     * Callback from the second child fragment.
     */
    public void onSecondFragRes() {
        mViewPager.setCurrentItem(2);
        quickCalculatorThirdFragment.setBuyerCost(secondFragmentResult);
    }

    /**
     * Callback from the third child fragment.
     */
    public void onThirdFragRes() {
        // TODO: 6/6/16 Hit api and calculatedd....
        if(NetworkStatus.isInternetOn(getActivity())) {
            ProgressDialogUtil.getInstance().showProgressDialog(getActivity());
            HideKeyboard.keyboardHide(getActivity());
            List<NameValuePair> mList = new ArrayList<>();
            mList.add(new BasicNameValuePair("title", quickCalculatorFirstFragment.propertyTitle));
            mList.add(new BasicNameValuePair("address_name", quickCalculatorFirstFragment.location));
            mList.add(new BasicNameValuePair("sqr_feet", quickCalculatorFirstFragment.area));
            mList.add(new BasicNameValuePair("service_access_key", AppConstants.APP_KEY));

            mList.add(new BasicNameValuePair("after_repair_value", quickCalculatorSecondFragment.avr+""));
            mList.add(new BasicNameValuePair("max_after_repair_value_per", quickCalculatorSecondFragment.maxPerAvr+""));
            mList.add(new BasicNameValuePair("repair", quickCalculatorSecondFragment.repair+""));
            mList.add(new BasicNameValuePair("closing_costs_to_buy", quickCalculatorSecondFragment.closeCost4Buy+""));
            mList.add(new BasicNameValuePair("closing_costs_to_cell", quickCalculatorSecondFragment.closeCost4Sell+""));
            mList.add(new BasicNameValuePair("holding_costs", quickCalculatorSecondFragment.holdingCost+""));
            mList.add(new BasicNameValuePair("other_expenses", quickCalculatorSecondFragment.otherExpense+""));
            mList.add(new BasicNameValuePair("wholesale_profile", quickCalculatorSecondFragment.wholeSaleProfit+""));
            mList.add(new BasicNameValuePair("max_offer", quickCalculatorSecondFragment.maxOffer+""));

            mList.add(new BasicNameValuePair("end_buyer_cost_basis", quickCalculatorThirdFragment.buyerCost+""));
            mList.add(new BasicNameValuePair("projected_monthly_income", quickCalculatorThirdFragment.projectIcome+""));
            mList.add(new BasicNameValuePair("cap_rate_return", quickCalculatorThirdFragment.capRate+""));
            mList.add(new BasicNameValuePair("user_id", MySharedPreference.getInstance(getActivity()).getUserId()));

            ReqRespAsync<ReqBase, ResBase> mReqRespAsync = new ReqRespAsync<>(getActivity(), CalculatorFragment.this, AppConstants.QUICK_CALCULATOR_API, "post", mList, ResBase.class, "quick_cal");
            mReqRespAsync.execute();
        }else{
            Toaster.show(R.string.err_internet_connection_error);
        }

    }

    @Override
    public void onResponse(Object type, Object data) {
        if(type.toString().equals("quick_cal")){
            if(data instanceof ResBase){
                ResBase d= (ResBase) data;
                if(d.getErrorCode()==0){
                    quickCalculatorThirdFragment.clearAll();
                    quickCalculatorSecondFragment.clearAll();
                    mViewPager.setCurrentItem(0);
                    quickCalculatorFirstFragment.clearAll();
                    secondFragmentResult=0.0f;
                    Toaster.show("Data Record successfully.");
                    ProgressDialogUtil.getInstance().dismissProgressDialog();
                }else{
                    ProgressDialogUtil.getInstance().dismissProgressDialog();
                    Toaster.show(d.getMessage());
                }
            }else{
                ProgressDialogUtil.getInstance().dismissProgressDialog();
                Toaster.show(R.string.err_internet_connection_error);
            }
        }
    }

}
