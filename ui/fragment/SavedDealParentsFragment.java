package org.rehab.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.rehab.app.R;
import org.rehab.app.adapter.CalculatorAdapter;
import org.rehab.app.async.ReqRespAsync;
import org.rehab.app.constants.AppConstants;
import org.rehab.app.interfaces.IASCommon;
import org.rehab.app.models.request.ReqBase;
import org.rehab.app.models.response.DealItem;
import org.rehab.app.models.response.ResSavedDealList;
import org.rehab.app.preferences.MySharedPreference;
import org.rehab.app.utils.Logger;
import org.rehab.app.utils.NetworkStatus;
import org.rehab.app.utils.ProgressDialogUtil;
import org.rehab.app.utils.Toaster;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 */
public class SavedDealParentsFragment extends Fragment implements IASCommon{

    @BindView(R.id.vp_fragments)
    ViewPager vpFragments;
    @BindView(R.id.v_inactive)
    View vInactive;
    @BindView(R.id.v_active)
    View vActive;

    private ArrayList<DealItem> dealList=new ArrayList<>();
    private ArrayList<Fragment> mFragmentList=new ArrayList<>();
    private CalculatorAdapter savedDealRVAdapter;
    private SavedActiveDealFragment savedActiveDealFragment;
    private SavedInActiveDealFragment savedInActiveDealFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hitApiGetData(1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_saved_deal_parent,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        savedActiveDealFragment=new SavedActiveDealFragment();
        savedInActiveDealFragment=new SavedInActiveDealFragment();
        mFragmentList.add(savedActiveDealFragment);
        mFragmentList.add(savedInActiveDealFragment);
        savedDealRVAdapter=new CalculatorAdapter(getActivity(),getChildFragmentManager(),mFragmentList);
        vpFragments.setAdapter(savedDealRVAdapter);

        vpFragments.setSelected(true);
        vpFragments.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Logger.e("onPageScrolled:-->"+position);
            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    vActive.setVisibility(View.VISIBLE);
                    vInactive.setVisibility(View.GONE);
                }else{
                    vActive.setVisibility(View.GONE);
                    vInactive.setVisibility(View.VISIBLE);
                }
                Logger.e("onPageSelected:-->"+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Logger.e("onPageScrollStateChanged:-->"+state);
            }
        });

    }

    @OnClick(R.id.tv_inactive)
    public void onInactiveClick(){
        vpFragments.setCurrentItem(1);
    }
    @OnClick(R.id.tv_active)
    public void onActiveClick(){
        vpFragments.setCurrentItem(0);
    }



    private void hitApiGetData(int position){
        if (NetworkStatus.isInternetOn(getActivity())) {
            ProgressDialogUtil.getInstance().showProgressDialog(getActivity());
//            String url=AppConstants.SAVED_DEAL_API+"?user_id="+ MySharedPreference.getInstance(getActivity()).getUserId()+"&page="+position;
            String url= AppConstants.SAVED_DEAL_API+"?user_id="+ MySharedPreference.getInstance(getActivity()).getUserId()+"&page="+position;
            ReqRespAsync<ReqBase, ResSavedDealList> mReqRespAsync = new ReqRespAsync<ReqBase, ResSavedDealList>(getActivity(), SavedDealParentsFragment.this,url, "get", null, ResSavedDealList.class, "get_deals");
            mReqRespAsync.execute();
        } else {
            Toast.makeText(getActivity(), R.string.err_internet_connection_error, Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onResponse(Object type, Object data) {
        if(type.toString().equalsIgnoreCase("get_deals")){
            if(data instanceof  ResSavedDealList){
                ResSavedDealList savedDealList= (ResSavedDealList) data;
                if(savedDealList.getErrorCode()==0){
                    dealList.addAll(savedDealList.getData());
                    ArrayList<DealItem> dealActiveList=new ArrayList<>();
                    ArrayList<DealItem> dealInactiveList=new ArrayList<>();
                    for(int i=0;i<dealList.size();i++){
                        if(dealList.get(i).getStatus()==1){
                            dealActiveList.add(dealList.get(i));
                        }else{
                            dealInactiveList.add(dealList.get(i));
                        }
                    }
                    savedActiveDealFragment.setData(dealActiveList);
                    savedInActiveDealFragment.setData(dealInactiveList);

                }
                ProgressDialogUtil.getInstance().dismissProgressDialog();;
            }else{
                ProgressDialogUtil.getInstance().dismissProgressDialog();;
                Toaster.show(R.string.err_internet_connection_error);
            }
        }
    }


}
