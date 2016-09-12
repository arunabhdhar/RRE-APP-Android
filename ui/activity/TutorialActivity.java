package org.rehab.app.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;

import org.rehab.app.R;
import org.rehab.app.ui.views.expendable_rv.ChildView;
import org.rehab.app.ui.views.expendable_rv.ParentView;
import org.rehab.app.ui.views.expendable_rv.RecipeAdapter;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 */
public class TutorialActivity extends Activity {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private List<ParentView> mDataList;
    private RecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tutorial);
        ButterKnife.bind(this);

        ChildView beef = new ChildView("beef");
        ChildView cheese = new ChildView("cheese");
        ChildView salsa = new ChildView("salsa");
        ChildView tortilla = new ChildView("tortilla");
        ChildView ketchup = new ChildView("ketchup");
        ChildView bun = new ChildView("bun");

        ParentView taco = new ParentView("taco", Arrays.asList(beef, cheese, salsa, tortilla));
        ParentView quesadilla = new ParentView("quesadilla", Arrays.asList(cheese, tortilla));
        ParentView burger = new ParentView("burger", Arrays.asList(beef, cheese, ketchup, bun));

        mDataList= Arrays.asList(taco, quesadilla, burger);

        adapter = new RecipeAdapter(TutorialActivity.this, mDataList);
        adapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @Override
            public void onListItemExpanded(int position) {
            }

            @Override
            public void onListItemCollapsed(int position) {
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(TutorialActivity.this));
    }

}
