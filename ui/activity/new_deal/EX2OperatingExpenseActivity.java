package org.rehab.app.ui.activity.new_deal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.rehab.app.R;
import org.rehab.app.interfaces.IASCommon;
import org.rehab.app.models.app.ES2OperatingExpense;
import org.rehab.app.utils.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 */
public class EX2OperatingExpenseActivity extends AppCompatActivity implements IASCommon{
    @BindView(R.id.s_input_type)
    Spinner sInputType;
    @BindView(R.id.ll_view_group)
    LinearLayout llViewGroup;

    private boolean isMonthly=true;
    private TextView tv_f_montly_rent,tv_f_annual_rent;

    private ES2OperatingExpense es2OperatingExpense;

    private String[] aInputType={"Monthly","Annual"};
    private View footerView;

    private float opIncome=1200;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_exit_cal2_epoe);
        es2OperatingExpense= (ES2OperatingExpense) getIntent().getExtras().getSerializable("data");
        opIncome=Float.parseFloat(getIntent().getExtras().getString("op_income"));
        posAddItem=getIntent().getExtras().getInt("add_item_position");
        ButterKnife.bind(this);
        footerView =  LayoutInflater.from(EX2OperatingExpenseActivity.this).inflate(R.layout.inflate_footer_exit2_io_expense,null);
        getFooterViews();
        initIntailView();
        ArrayAdapter<String> sInputTypeArrayAdapter = new ArrayAdapter<String>(this,R.layout.inflate_drop_down_view, aInputType);
        sInputTypeArrayAdapter.setDropDownViewResource(R.layout.inflate_spinner_row); // The drop down view
        sInputType.setAdapter(sInputTypeArrayAdapter);
        sInputType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    isMonthly=true;
                }else{
                    isMonthly=false;
                }
                initIntailView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }

    int posAddItem=0;
    private void initIntailView(){
        llViewGroup.removeAllViews();
        ShowDataInScrollView();
    }

    @OnClick(R.id.btn_cancel)
    public void onCancelClick(){

        finish();
    }

    @OnClick(R.id.btn_update)
    public void onUpdateClick(){
        Intent mIntent=new Intent();
        mIntent.putExtra("data",es2OperatingExpense);
        mIntent.putExtra("add_item_position",posAddItem);
        setResult(RESULT_OK,mIntent);
        finish();
    }

    private void getFooterViews(){
        tv_f_montly_rent=(TextView)footerView.findViewById(R.id.tv_f_montly_rent);
        tv_f_annual_rent=(TextView)footerView.findViewById(R.id.tv_f_annual_rent);
    }


    @Override
    public void onResponse(Object type, Object data) {
        if(type.toString().equals("et_montly_rent")){
            Logger.e("et_montly_rent:->"+data.toString());;
            es2OperatingExpense.setToeMonthlyExpense(data.toString());
            es2OperatingExpense.setToeRestIncome((Float.parseFloat(es2OperatingExpense.getToeMonthlyExpense())-((opIncome/100)*Float.parseFloat(es2OperatingExpense.getItemByValue(0).getNoOfUnits())))+"");
            es2OperatingExpense.setToeAnuualExpense((Float.parseFloat(data.toString())*12)+"");
            tv_f_montly_rent.setText(data.toString());
            tv_f_annual_rent.setText((Float.parseFloat(data.toString())*12)+"");
        }

    }
    private  List<ES2OperatingExpense.ItemExpenseValue> itemValueList;
    private void ShowDataInScrollView(){
        itemValueList=es2OperatingExpense.getItemValueList();
        for(int position=0;position<itemValueList.size();position++){
            if(posAddItem==position){
                View convertView = LayoutInflater.from(EX2OperatingExpenseActivity.this).inflate(R.layout.inflate_item_add, null);
                TextView tv_percentage = (TextView) convertView.findViewById(R.id.tv_add_item);
                tv_percentage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ES2OperatingExpense.ItemExpenseValue itemValueObj=new ES2OperatingExpense.ItemExpenseValue();
                        itemValueObj.setTitle("Others");
                        itemValueObj.setEditable(true);
                        itemValueObj.setNoOfUnits("");
                        itemValueObj.setMonthlyRent("");
                        itemValueObj.setAnnualRent("");
                        itemValueObj.setPercent("");
                        es2OperatingExpense.addItemValueAtPos(posAddItem,itemValueObj);
                        posAddItem++;
                        //Redraw View
                        llViewGroup.removeAllViews();
                        ShowDataInScrollView();
                    }
                });
                llViewGroup.addView(convertView);
            }else {
                View convertView = LayoutInflater.from(EX2OperatingExpenseActivity.this).inflate(R.layout.inflate_exit2_operating_expense, null);
                EditText et_title = (EditText) convertView.findViewById(R.id.et_title);
                EditText et_montly_rent = (EditText) convertView.findViewById(R.id.et_montly_rent);
                EditText et_annual_rent = (EditText) convertView.findViewById(R.id.et_annual_rent);
                TextView tv_percentage = (TextView) convertView.findViewById(R.id.tv_percentage);
                EditText et_unit=(EditText)convertView.findViewById(R.id.et_unit);

                et_unit.setText(itemValueList.get(position).getNoOfUnits());
                et_unit.addTextChangedListener(new MyTextWatcher(position, "et_unit"));
                et_montly_rent.setText(itemValueList.get(position).getMonthlyRent());
                et_annual_rent.setText(itemValueList.get(position).getAnnualRent());
                tv_percentage.setText(itemValueList.get(position).getPercent() == null ? "" : (itemValueList.get(position).getPercent() + "%"));
                et_title.addTextChangedListener(new MyTextWatcher(position, "et_title"));
                et_title.setEnabled(itemValueList.get(position).isEditable());
                et_title.setText(itemValueList.get(position).getTitle());
                if(position==0){
                    et_montly_rent.setEnabled(false);
                    et_annual_rent.setEnabled(false);
                    et_montly_rent.setBackgroundResource(R.drawable.tv_disable_bg);
                    et_annual_rent.setBackgroundResource(R.drawable.tv_disable_bg);
                }else {
                    if (isMonthly) {
                        et_montly_rent.setEnabled(true);
                        et_annual_rent.setEnabled(false);
                        et_montly_rent.setBackgroundResource(R.drawable.tv_edittext_bg);
                        et_annual_rent.setBackgroundResource(R.drawable.tv_disable_bg);
                        et_montly_rent.addTextChangedListener(new MyTextWatcher(position, "et_montly_rent"));
                    } else {
                        et_annual_rent.setEnabled(true);
                        et_montly_rent.setEnabled(false);
                        et_annual_rent.setBackgroundResource(R.drawable.tv_edittext_bg);
                        et_montly_rent.setBackgroundResource(R.drawable.tv_disable_bg);
                        et_annual_rent.addTextChangedListener(new MyTextWatcher(position, "et_annual_rent"));
                    }
                }
                llViewGroup.addView(convertView);
            }

        }
        llViewGroup.addView(footerView);
        calculationOfData("et_montly_rent");
    }


    class MyTextWatcher implements TextWatcher {
        private int position;
        private String type="et_no_of_units";
        public  MyTextWatcher(int position,String type){
            this.position=position;
            this.type=type;
        }
        public void updatePosition(int position) {
            this.position = position;
        }
        @Override
        public void afterTextChanged(Editable s) {}
        @Override
        public void beforeTextChanged(CharSequence s, int start,
                                      int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {
            if(type.equals("et_title")) {
                itemValueList.get(position).setTitle(s.toString());
            }else if(type.equals("et_unit")){
                if(position==0){
                    if(s.length()>0){
                        float Value=(opIncome/100f)*Integer.parseInt(s.toString());
                        itemValueList.get(position).setMonthlyRent(Value+"");
                        itemValueList.get(position).setAnnualRent((Value*12)+"");
                    }else{
                        itemValueList.get(position).setMonthlyRent("0");
                        itemValueList.get(position).setAnnualRent("0");
                    }
                    ((EditText)llViewGroup.getChildAt(position).findViewById(R.id.et_annual_rent)).setText(itemValueList.get(position).getAnnualRent());
                    ((EditText)llViewGroup.getChildAt(position).findViewById(R.id.et_montly_rent)).setText(itemValueList.get(position).getMonthlyRent());
                    calculationOfData("et_montly_rent");
                }else{
                    itemValueList.get(position).setNoOfUnits(s.toString());
                }

            }else if(type.equals("et_montly_rent")){
                if(s.length()>0) {
                    itemValueList.get(position).setMonthlyRent(s.toString()+"");
                    itemValueList.get(position).setAnnualRent((Float.parseFloat(itemValueList.get(position).getMonthlyRent())*12)+"");
                    ((EditText)llViewGroup.getChildAt(position).findViewById(R.id.et_annual_rent)).setText(itemValueList.get(position).getAnnualRent());
                }else{
                    itemValueList.get(position).setMonthlyRent("");
                    itemValueList.get(position).setAnnualRent("");
                }
            }else if(type.equals("et_annual_rent")){
                if(s.length()>0) {
                    itemValueList.get(position).setAnnualRent(s.toString());
                    itemValueList.get(position).setMonthlyRent((Float.parseFloat(s.toString())/12)+"");
                    ((EditText)llViewGroup.getChildAt(position).findViewById(R.id.et_montly_rent)).setText(itemValueList.get(position).getMonthlyRent());
                }else{
                    itemValueList.get(position).setMonthlyRent("");
                    itemValueList.get(position).setAnnualRent("");
                }
            }
            calculationOfData(type);
        }
    }


    private  void calculationOfData(String type){
        if(type.equals("et_montly_rent") || type.equals("et_annual_rent")){
            float value=0;
            for(int i=0;i<itemValueList.size();i++){
                if(!TextUtils.isEmpty(itemValueList.get(i).getMonthlyRent()) && !TextUtils.isEmpty(itemValueList.get(i).getAnnualRent())){
                    value+=Float.parseFloat(itemValueList.get(i).getMonthlyRent());
                }
            }
            for(int i=0;i<itemValueList.size();i++){
                if(isMonthly) {
                    if(itemValueList.get(i).getNoOfUnits().equals("new_item")){
                    }else {
                        if (TextUtils.isEmpty(itemValueList.get(i).getAnnualRent())) {
                            itemValueList.get(i).setPercent("");
                            ((TextView) llViewGroup.getChildAt(i).findViewById(R.id.tv_percentage)).setText("");
                        } else {
                            if (!TextUtils.isEmpty(itemValueList.get(i).getMonthlyRent())) {
                                float percentage = (Float.parseFloat(itemValueList.get(i).getMonthlyRent()) / value) * 100;
                                itemValueList.get(i).setPercent(percentage + "");
                                ((TextView) llViewGroup.getChildAt(i).findViewById(R.id.tv_percentage)).setText(itemValueList.get(i).getPercent() + "%");
                            }
                        }
                    }
                }else{
                    if(itemValueList.get(i).getNoOfUnits().equals("new_item")){
                    }else {
                        if (TextUtils.isEmpty(itemValueList.get(i).getMonthlyRent())) {
                            itemValueList.get(i).setPercent("");
                            ((TextView) llViewGroup.getChildAt(i).findViewById(R.id.tv_percentage)).setText("");
                        } else {
                            if (!TextUtils.isEmpty(itemValueList.get(i).getMonthlyRent())) {
                                float percentage = (Float.parseFloat(itemValueList.get(i).getMonthlyRent()) / value) * 100;
                                itemValueList.get(i).setPercent(percentage + "");
                                ((TextView) llViewGroup.getChildAt(i).findViewById(R.id.tv_percentage)).setText(itemValueList.get(i).getPercent() + "%");
                            }
                        }
                    }
                }

            }
            onResponse("et_montly_rent",value+"");
        }
    }
}
