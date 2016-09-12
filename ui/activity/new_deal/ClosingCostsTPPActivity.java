package org.rehab.app.ui.activity.new_deal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.rehab.app.R;
import org.rehab.app.adapter.ClosingCostAdapter;
import org.rehab.app.interfaces.IASCommon;
import org.rehab.app.models.app.NDClosingCostRehab;
import org.rehab.app.utils.HideKeyboard;
import org.rehab.app.utils.Toaster;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 */
public class ClosingCostsTPPActivity extends AppCompatActivity implements IASCommon {

    private float f1PurchasePrice;

    @BindView(R.id.s_your_closing_costs)
    Spinner sYourClosingCosts;
    @BindView(R.id.et_quick_lump_sum)
    EditText etQuickLumpSum;
    @BindView(R.id.tv_options_heading)
    TextView tvOptionHeading;
    @BindView(R.id.rl_quick_lump_sum)
    RelativeLayout rlQuickLumpSum;
    @BindView(R.id.rl_detail_input)
    RelativeLayout rlDetailInput;
    @BindView(R.id.ll_qper_of_purchase)
    LinearLayout llQperOfPurchase;
    @BindView(R.id.et_qper_of_purchase)
    EditText etQperOfPurchase;
    @BindView(R.id.tv_totoal_qper_of_purchase)
    TextView tvTotalQper;
    @BindView(R.id.rv_items)
    RecyclerView rvItems;
    @BindView(R.id.tv_di_total)
    TextView tvDITotal;

    private int selectedOption=0;
    private ClosingCostAdapter closingCostAdapter;

    String[] aYourClosingCosts={"Quick Lump Sum","Detailed Input","% of Purchase Price"};

    private NDClosingCostRehab ndClosingCostRehab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_closing_costs_tpp);
        ButterKnife.bind(this);
        f1PurchasePrice=Float.parseFloat(getIntent().getExtras().getString("f1_purchase_price"));
        ndClosingCostRehab= (NDClosingCostRehab) getIntent().getExtras().getSerializable("data");
        initClosingCosts();
    }

    @OnClick(R.id.btn_cancel)
    void onCancelClick(){
        HideKeyboard.keyboardHide(ClosingCostsTPPActivity.this);
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
                ndClosingCostRehab.setTotalClosingCosts(Float.parseFloat(calValue));
            }
        }else if(selectedOption==1){

        }else if(selectedOption==2){
            if(TextUtils.isEmpty(tvTotalQper.getText().toString().trim())){
                Toaster.show(R.string.hint_enter_percentage_value);
                return;
            }else{
                calValue=etQperOfPurchase.getText().toString().trim();
                if(calValue.equals("0")){
                    Toaster.show(R.string.hint_enter_percentage_value);
                    return;
                }
                float valtotalPer=(f1PurchasePrice / 100)*Float.parseFloat(calValue);
                ndClosingCostRehab.setPerOfPurchase(Float.parseFloat(calValue));
                ndClosingCostRehab.setTotalPercentage(valtotalPer);
            }
        }
        HideKeyboard.keyboardHide(ClosingCostsTPPActivity.this);
        Intent mIntent=new Intent();
        mIntent.putExtra("value",calValue);
        mIntent.putExtra("data",ndClosingCostRehab);
        mIntent.putExtra("option_type",selectedOption);  //selectedOption =0 QLS, 1:-> DI, 2:-> % value
        setResult(RESULT_OK,mIntent);
        finish();

    }

    private TextWatcher textWatcher=new TextWatcher() {

        @Override
        public void afterTextChanged(Editable s) {}

        @Override
        public void beforeTextChanged(CharSequence s, int start,
                                      int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {
            if(s.length()>0 ) {
                if(!s.equals("0")){
                    tvTotalQper.setText((f1PurchasePrice / 100)*Float.parseFloat(s.toString())+"");
                }else{
                    tvTotalQper.setText("0");
                }
            }else{
                tvTotalQper.setText("0");
            }
        }
    };


    @Override
    protected void onStart() {
        super.onStart();
        ArrayAdapter<String> sIncClosArrayAdapter = new ArrayAdapter<String>(ClosingCostsTPPActivity.this,R.layout.inflate_spinner_row, aYourClosingCosts);
        sIncClosArrayAdapter.setDropDownViewResource(R.layout.inflate_spinner_row); // The drop down view
        sYourClosingCosts.setAdapter(sIncClosArrayAdapter);
        sYourClosingCosts.setOnItemSelectedListener(onItemSelectedListener);
        etQperOfPurchase.addTextChangedListener(textWatcher);
        sYourClosingCosts.setSelection(ndClosingCostRehab.getClosingCostsOption());

        etQuickLumpSum.setText(ndClosingCostRehab.getTotalClosingCosts()+"");
        etQperOfPurchase.setText(ndClosingCostRehab.getPerOfPurchase()+"");

        final LinearLayoutManager layoutManager = new LinearLayoutManager(ClosingCostsTPPActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvItems.setLayoutManager(layoutManager);
        closingCostAdapter=new ClosingCostAdapter(ClosingCostsTPPActivity.this,ndClosingCostRehab);
        rvItems.setAdapter(closingCostAdapter);
    }

    private AdapterView.OnItemSelectedListener onItemSelectedListener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            setVisibility(i);
            selectedOption=i;
            ndClosingCostRehab.setClosingCostsOption(i);
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };


    private void setVisibility(int pos){
        rlQuickLumpSum.setVisibility(View.GONE);
        rlDetailInput.setVisibility(View.GONE);
        llQperOfPurchase.setVisibility(View.GONE);
        switch (pos){
            case 0:
                tvOptionHeading.setText("Enter Total Closing Costs:");
                rlQuickLumpSum.setVisibility(View.VISIBLE);
                break;
            case 1:
                tvOptionHeading.setText("Enter Itemized Forecast Below:");
                rlDetailInput.setVisibility(View.VISIBLE);
                break;
            case 2:
                tvOptionHeading.setText("Enter % of Purchase Price");
                llQperOfPurchase.setVisibility(View.VISIBLE);
                break;
        }
    }


    private void initClosingCosts(){
        if(ndClosingCostRehab==null){
            ndClosingCostRehab=new NDClosingCostRehab();
            ndClosingCostRehab.setClosingCostsOption(0);
            ndClosingCostRehab.setTotalClosingCosts(1500);
            ndClosingCostRehab.setPerOfPurchase(0);
            ndClosingCostRehab.setTotalPercentage(0);

            String[] closingItems=getResources().getStringArray(R.array.nd_closing_costs);

            List<NDClosingCostRehab.ClosingCostItem> costItemList=new ArrayList<>();
            for(int i=0;i<closingItems.length;i++){
                NDClosingCostRehab.ClosingCostItem item=new NDClosingCostRehab.ClosingCostItem();
                item.setTitle(closingItems[i]);
                item.setViewType(1);
                costItemList.add(item);
            }
            NDClosingCostRehab.ClosingCostItem item=new NDClosingCostRehab.ClosingCostItem();
            item.setViewType(2);
            item.setTitle("+ Add Item");
            costItemList.add(item);
            ndClosingCostRehab.setListCostItem(costItemList);
        }
    }

    @Override
    public void onResponse(Object type, Object data) {
        if(type.toString().equals("update_value")){
            tvDITotal.setText(ndClosingCostRehab.getTotalDICosts()+"");
        }
    }
}
