package org.rehab.app.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.rehab.app.R;
import org.rehab.app.adapter.SavedDealRVAdapter;
import org.rehab.app.interfaces.IRecycleViewOnItemClickListener;
import org.rehab.app.models.response.DealItem;
import org.rehab.app.ui.activity.PropertyDetailsActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 */
public class SavedActiveDealFragment extends Fragment implements IRecycleViewOnItemClickListener {


    @BindView(R.id.rv_deal_list)
    RecyclerView rvDealList;
    private SavedDealRVAdapter savedDealRVAdapter;
    private ArrayList<DealItem> dealList=new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        savedDealRVAdapter=new SavedDealRVAdapter(getActivity(),dealList,SavedActiveDealFragment.this);
        rvDealList.setAdapter(savedDealRVAdapter);




    }


    public void setData(ArrayList<DealItem> dealList){
        this.dealList.addAll(dealList);
        savedDealRVAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClickListener(int position, Object bean) {
        Intent mIntent=new Intent(getActivity(),PropertyDetailsActivity.class);
        mIntent.putExtra("data",((DealItem)bean));
        startActivity(mIntent);
    }

    @Override
    public boolean onItemLongClickListener(int position, Object bean) {
        return false;
    }
}
