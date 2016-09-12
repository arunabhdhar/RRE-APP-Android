package org.rehab.app.ui.activity.new_deal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.rehab.app.R;
import org.rehab.app.adapter.HoldingCostAdapter;
import org.rehab.app.interfaces.IASCommon;
import org.rehab.app.models.app.NDHoldingCostRehab;
import org.rehab.app.utils.HideKeyboard;
import org.rehab.app.utils.Toaster;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 */
public class HoldingCostsTPPActivity extends AppCompatActivity implements IASCommon{

    @BindView(R.id.s_your_holding_costs)
    Spinner sYourHoldingCosts;
    @BindView(R.id.et_quick_lump_sum)
    EditText etQuickLumpSum;
    @BindView(R.id.tv_options_heading)
    TextView tvOptionHeading;
    @BindView(R.id.rl_quick_lump_sum)
    RelativeLayout rlQuickLumpSum;
    @BindView(R.id.rl_detail_input)
    RelativeLayout rlDetailInput;
    @BindView(R.id.rv_items)
    RecyclerView rvItems;
    @BindView(R.id.tv_total)
    TextView tvTotal;

    private NDHoldingCostRehab ndHoldingCostRehab;
    private HoldingCostAdapter holdingCostAdapter;

    private int selectedOption=0;

    String[] aYourClosingCosts={"Quick Lump Sum","Detailed Input"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_holding_costs_tpp);
        ButterKnife.bind(this);
        ndHoldingCostRehab= (NDHoldingCostRehab) getIntent().getExtras().getSerializable("data");
        initValue();
    }

    @OnClick(R.id.btn_cancel)
    void onCancelClick(){
        HideKeyboard.keyboardHide(HoldingCostsTPPActivity.this);
        finish();
    }

    @OnClick(R.id.btn_update)
    void onUpdateClick(){
        String calValue="0";
        if(selectedOption==0){
            if(TextUtils.isEmpty(etQuickLumpSum.getText().toString().trim())){
                Toaster.show(R.string.hint_enter_quick_lump_sum);
                return;
            }else{
                calValue=etQuickLumpSum.getText().toString().trim();
                ndHoldingCostRehab.setTotalHoldingCosts(Integer.parseInt(calValue));
            }
        }
        HideKeyboard.keyboardHide(HoldingCostsTPPActivity.this);
        Intent mIntent=new Intent();
        mIntent.putExtra("value",calValue);
        mIntent.putExtra("data",ndHoldingCostRehab);
        mIntent.putExtra("option_type",selectedOption);  //selectedOption =0 QLS, 1:-> DI
        setResult(RESULT_OK,mIntent);
        finish();

    }


    @Override
    protected void onStart() {
        super.onStart();
        ArrayAdapter<String> sIncClosArrayAdapter = new ArrayAdapter<String>(HoldingCostsTPPActivity.this,R.layout.inflate_spinner_row, aYourClosingCosts);
        sIncClosArrayAdapter.setDropDownViewResource(R.layout.inflate_spinner_row); // The drop down view
        sYourHoldingCosts.setAdapter(sIncClosArrayAdapter);
        sYourHoldingCosts.setOnItemSelectedListener(onItemSelectedListener);
        sYourHoldingCosts.setSelection(ndHoldingCostRehab.getHoldingCostsType());

        final LinearLayoutManager layoutManager = new LinearLayoutManager(HoldingCostsTPPActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvItems.setLayoutManager(layoutManager);
        holdingCostAdapter=new HoldingCostAdapter(HoldingCostsTPPActivity.this,ndHoldingCostRehab);
        rvItems.setAdapter(holdingCostAdapter);
        etQuickLumpSum.setText(ndHoldingCostRehab.getTotalHoldingCosts()+"");
    }

    private AdapterView.OnItemSelectedListener onItemSelectedListener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            setVisibility(i);
            selectedOption=i;
            ndHoldingCostRehab.setHoldingCostsType(i);
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };


    private void setVisibility(int pos){
        rlQuickLumpSum.setVisibility(View.GONE);
        rlDetailInput.setVisibility(View.GONE);
        switch (pos){
            case 0:
                tvOptionHeading.setText("Enter Total Holding Costs Here:");
                rlQuickLumpSum.setVisibility(View.VISIBLE);
                break;
            case 1:
                tvOptionHeading.setText("Enter Itemized Forecast Below:");
                rlDetailInput.setVisibility(View.VISIBLE);
                break;
        }
    }


    private void initValue(){
        if(ndHoldingCostRehab==null){
            ndHoldingCostRehab=new NDHoldingCostRehab();
            ndHoldingCostRehab.setHoldingCostsType(0);
            ndHoldingCostRehab.setTotalHoldingCosts(1500);
            String[] holdingItems=getResources().getStringArray(R.array.nd_holding_costs);

            List<NDHoldingCostRehab.CostItem> costItemList=new ArrayList<>();
            for(int i=0;i<holdingItems.length;i++){
                NDHoldingCostRehab.CostItem item=new NDHoldingCostRehab.CostItem();
                item.setTitle(holdingItems[i]);
                item.setViewType(1);
                costItemList.add(item);
            }
            NDHoldingCostRehab.CostItem item=new NDHoldingCostRehab.CostItem();
            item.setViewType(2);
            item.setTitle("+ Add Item");
            costItemList.add(item);
            ndHoldingCostRehab.setListCostItem(costItemList);
        }
    }

    @Override
    public void onResponse(Object type, Object data) {
        if(type.toString().equals("update_value")){
            tvTotal.setText(ndHoldingCostRehab.getTotalCostItems()+"");
        }
    }
}
