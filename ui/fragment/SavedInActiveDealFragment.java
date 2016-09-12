package org.rehab.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.rehab.app.R;
import org.rehab.app.adapter.SavedDealRVAdapter;
import org.rehab.app.async.ReqRespAsync;
import org.rehab.app.constants.AppConstants;
import org.rehab.app.interfaces.IASCommon;
import org.rehab.app.interfaces.IRecycleViewOnItemClickListener;
import org.rehab.app.models.request.ReqBase;
import org.rehab.app.models.response.DealItem;
import org.rehab.app.models.response.ResSavedDealList;
import org.rehab.app.preferences.MySharedPreference;
import org.rehab.app.utils.NetworkStatus;
import org.rehab.app.utils.ProgressDialogUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 */
public class SavedInActiveDealFragment extends Fragment implements IASCommon,IRecycleViewOnItemClickListener{


    @BindView(R.id.rv_deal_list)
    RecyclerView rvDealList;
    private SavedDealRVAdapter savedDealRVAdapter;
    private ArrayList<DealItem> dealList=new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        hitApiGetData(1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_saved_deal,container,false);
        ButterKnife.bind(this,view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvDealList.setLayoutManager(layoutManager);
        savedDealRVAdapter=new SavedDealRVAdapter(getActivity(),dealList,SavedInActiveDealFragment.this);
        rvDealList.setAdapter(savedDealRVAdapter);
    }

    private void hitApiGetData(int position){
        if (NetworkStatus.isInternetOn(getActivity())) {
            ProgressDialogUtil.getInstance().showProgressDialog(getActivity());
            String url=AppConstants.SAVED_DEAL_API+"?user_id="+ MySharedPreference.getInstance(getActivity()).getUserId()+"&page="+position;
            ReqRespAsync<ReqBase, ResSavedDealList> mReqRespAsync = new ReqRespAsync<ReqBase, ResSavedDealList>(getActivity(), SavedInActiveDealFragment.this,url, "get", null, ResSavedDealList.class, "get_deals");
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
                }
                savedDealRVAdapter.notifyDataSetChanged();;
            }
        }
    }

    @Override
    public void onItemClickListener(int position, Object bean) {

    }

    public void setData(ArrayList<DealItem> dealList){
        this.dealList.addAll(dealList);
        savedDealRVAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onItemLongClickListener(int position, Object bean) {
        return false;
    }
}
