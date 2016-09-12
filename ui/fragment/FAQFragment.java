package org.rehab.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;

import org.rehab.app.R;
import org.rehab.app.ui.views.expendable_rv.ChildView;
import org.rehab.app.ui.views.expendable_rv.ParentView;
import org.rehab.app.ui.views.expendable_rv.RecipeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 */
public class FAQFragment extends Fragment {

    @BindView(R.id.recyclerview)
    RecyclerView rvTutorial;

    private RecipeAdapter mAdapter;
    private List<ParentView> mDataList;
    private String[] faqTitle,faqDetails;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataList=new ArrayList<>();
        faqTitle=getResources().getStringArray(R.array.faq_title);
        faqDetails=getResources().getStringArray(R.array.faq_detail);

        for(int i=0;i<faqTitle.length;i++){
            ArrayList<ChildView> erChildListItems=new ArrayList<>();
            erChildListItems.add(new ChildView(faqDetails[i]));
            ParentView obj=new ParentView(faqTitle[i],erChildListItems);
            mDataList.add(obj);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_tutorial,container,false);
        ButterKnife.bind(this,mView);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




        mAdapter = new RecipeAdapter(getActivity(), mDataList);
        mAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @Override
            public void onListItemExpanded(int position) {
            }

            @Override
            public void onListItemCollapsed(int position) {
            }
        });

        rvTutorial.setAdapter(mAdapter);
        rvTutorial.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
