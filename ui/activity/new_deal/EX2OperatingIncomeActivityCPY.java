//package org.rehab.app.ui.activity.new_deal;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.RecyclerView;
//import android.text.Editable;
//import android.text.TextUtils;
//import android.text.TextWatcher;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.Spinner;
//import android.widget.TextView;
//
//import org.rehab.app.R;
//import org.rehab.app.adapter.ES2POIAdapter;
//import org.rehab.app.adapter.ES2POIRecyclerView;
//import org.rehab.app.interfaces.IASCommon;
//import org.rehab.app.models.app.ES2OperatingIncome;
//import org.rehab.app.utils.Logger;
//
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
///**
// */
//public class EX2OperatingIncomeActivityCPY extends AppCompatActivity implements IASCommon{
//    @BindView(R.id.s_input_type)
//    Spinner sInputType;
//    @BindView(R.id.lv_items)
//    ListView lvItems;
//    @BindView(R.id.cardList)
//    RecyclerView rvItems;
//    @BindView(R.id.ll_view_group)
//    LinearLayout llViewGroup;
//
//
//    private boolean isMonthly=true;
//    private TextView tv_f_no_of_units,et_f_sq_feet,tv_f_montly_rent,tv_f_annual_rent,tv_add_item;
//
//    private ES2POIAdapter es2POIAdapter;
//    private static ES2OperatingIncome es2OperatingIncome;
//    private String[] aInputType={"Monthly","Annual"};
//    private View footerView;
//    private ES2POIRecyclerView es2POIRecyclerView;
//    private EditText et_loss_percentage,et_foi_montly_rent;
//    private TextView tv_fvl_montly_rent,tv_fvl_annual_rent,tv_foi_annual_rent,tv_fgoi_montly_rent,tv_fgoi_annual_rent;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.view_exit_cal2_epoi);
//        ButterKnife.bind(this);
//        footerView =  LayoutInflater.from(EX2OperatingIncomeActivityCPY.this).inflate(R.layout.inflate_footer_exit2_op_inc,null);
//        getFooterViews();
//
//        initIntailView();
//        setFooterValue();
//        ArrayAdapter<String> sInputTypeArrayAdapter = new ArrayAdapter<String>(this,R.layout.inflate_drop_down_view, aInputType);
//        sInputTypeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
//        sInputType.setAdapter(sInputTypeArrayAdapter);
//        sInputType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if(i==0){
//                    isMonthly=true;
//                }else{
//                    isMonthly=false;
//                }
//                initIntailView();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//
//
//    }
//
//    private void initIntailView(){
//        if(es2OperatingIncome==null){
//            es2OperatingIncome=new ES2OperatingIncome();
//            ES2OperatingIncome.ItemValue itemValue=new ES2OperatingIncome.ItemValue();
//            itemValue.setNoOfUnits("1");
//            itemValue.setAnnualRent("14400");
//            itemValue.setMonthlyRent("1200");
//            itemValue.setSquareFt("1400");
//            itemValue.setPercent("100");
//            itemValue.setUnityType("3");
//            es2OperatingIncome.addItemValue(itemValue);
//            for(int i=0;i<3;i++){
//                ES2OperatingIncome.ItemValue itemValu=new ES2OperatingIncome.ItemValue();
//                es2OperatingIncome.addItemValue(itemValu);
//            }
//        }
//
//
////        rvItems.setHasFixedSize(true);
////        LinearLayoutManager llm = new LinearLayoutManager(this);
////        llm.setOrientation(LinearLayoutManager.VERTICAL);
////        rvItems.setLayoutManager(llm);
////        es2POIRecyclerView = new ES2POIRecyclerView(EX2OperatingIncomeActivity.this, es2OperatingIncome,EX2OperatingIncomeActivity.this,rvItems);
////        rvItems.setAdapter(es2POIRecyclerView);
//
//
//////        lvItems.addFooterView(footerView);
////        if(lvItems.getAdapter()!=null){
////        }
////        es2POIAdapter=new ES2POIAdapter(EX2OperatingIncomeActivity.this,es2OperatingIncome,EX2OperatingIncomeActivity.this);
////        lvItems.setAdapter(es2POIAdapter);
//        llViewGroup.removeAllViews();
//        ShowDataInScrollView();
//    }
//
//    @OnClick(R.id.btn_cancel)
//    public void onCancelClick(){
//        finish();
//    }
//
//    @OnClick(R.id.btn_update)
//    public void onUpdateClick(){
//        // TODO: 25/6/16 Calculate the data and update on the exit ext2
//        finish();
//    }
//
//    private void getFooterViews(){
//        tv_f_no_of_units= (TextView) footerView.findViewById(R.id.tv_f_no_of_units);
//        et_f_sq_feet=(TextView)footerView.findViewById(R.id.et_f_sq_feet);
//        tv_f_montly_rent=(TextView)footerView.findViewById(R.id.tv_f_montly_rent);
//        tv_f_annual_rent=(TextView)footerView.findViewById(R.id.tv_f_annual_rent);
//        et_loss_percentage=(EditText)footerView.findViewById(R.id.tv_loss_percentage);
//        tv_fvl_montly_rent=(TextView)footerView.findViewById(R.id.tv_fvl_montly_rent);
//        tv_fvl_annual_rent=(TextView)footerView.findViewById(R.id.tv_fvl_annual_rent);
//        et_foi_montly_rent=(EditText)footerView.findViewById(R.id.et_foi_montly_rent);
//        tv_foi_annual_rent=(TextView)footerView.findViewById(R.id.tv_foi_annual_rent);
//        tv_fgoi_montly_rent=(TextView)footerView.findViewById(R.id.tv_fgoi_montly_rent);
//        tv_fgoi_annual_rent=(TextView)footerView.findViewById(R.id.tv_fgoi_annual_rent);
//        tv_add_item=(TextView)footerView.findViewById(R.id.tv_add_item);
//
//
//        et_loss_percentage.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if(charSequence.length()>0){
//                    float monthlyloss=TextUtils.isEmpty(es2OperatingIncome.getGsiMonthlyRent())?0:Float.parseFloat(es2OperatingIncome.getGsiMonthlyRent())*(Float.parseFloat(charSequence.toString())/100);
//                    tv_fvl_montly_rent.setText(monthlyloss+"");
//                    tv_fvl_annual_rent.setText((monthlyloss*12)+"");
//                    es2OperatingIncome.setVlMonthlyRent(monthlyloss+"");
//                    es2OperatingIncome.setVlAnnumalERnt((monthlyloss*12)+"");
//                }else{
//                    es2OperatingIncome.setVlMonthlyRent("");
//                    es2OperatingIncome.setVlAnnumalERnt("");
//                    tv_fvl_montly_rent.setText("");
//                    tv_fvl_annual_rent.setText("");
//                }
//                tv_fgoi_montly_rent.setText((TextUtils.isEmpty(es2OperatingIncome.getGsiMonthlyRent())?0:Float.parseFloat(es2OperatingIncome.getGsiMonthlyRent())-(TextUtils.isEmpty(es2OperatingIncome.getVlMonthlyRent())?0:Float.parseFloat(es2OperatingIncome.getVlMonthlyRent()))+(TextUtils.isEmpty(es2OperatingIncome.getGoiMontlyRent())?0:Float.parseFloat(es2OperatingIncome.getGoiMontlyRent())))+"");
//                tv_fgoi_annual_rent.setText((Float.parseFloat(tv_fgoi_montly_rent.getText().toString())*12)+"");
//            }
//            @Override
//            public void afterTextChanged(Editable editable) {
//            }
//        });
//
//        et_foi_montly_rent.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if(charSequence.length()>0){
//                    float value=Float.parseFloat(charSequence.toString());
////                    es2OperatingIncome.setGoiMontlyRent(value+"");
////                    es2OperatingIncome.setGoiAnnualRent((value*12)+"");
//                    tv_foi_annual_rent.setText(es2OperatingIncome.getGoiAnnualRent());
//                }else{
////                    es2OperatingIncome.setGoiMontlyRent("");
////                    es2OperatingIncome.setGoiAnnualRent("");
//                    tv_foi_annual_rent.setText(es2OperatingIncome.getGoiAnnualRent());
//                }
//                tv_fgoi_montly_rent.setText((TextUtils.isEmpty(es2OperatingIncome.getGsiMonthlyRent())?0:Float.parseFloat(es2OperatingIncome.getGsiMonthlyRent())-(TextUtils.isEmpty(es2OperatingIncome.getVlMonthlyRent())?0:Float.parseFloat(es2OperatingIncome.getVlMonthlyRent()))+(TextUtils.isEmpty(es2OperatingIncome.getGoiMontlyRent())?0:Float.parseFloat(es2OperatingIncome.getGoiMontlyRent())))+"");
//                tv_fgoi_annual_rent.setText((Float.parseFloat(tv_fgoi_montly_rent.getText().toString())*12)+"");
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//
//        tv_add_item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ES2OperatingIncome.ItemValue itemValue=new ES2OperatingIncome.ItemValue();
//                es2OperatingIncome.addItemValue(itemValue);
//                llViewGroup.removeAllViews();
//                ShowDataInScrollView();
//            }
//        });
//    }
//
//    public void setFooterValue(){
//        tv_f_no_of_units.setText(es2OperatingIncome.getOiNoOfUnit());
//    }
//
//    @Override
//    public void onResponse(Object type, Object data) {
//        if(type.toString().equals("et_no_of_units")){
//            Logger.e("et_no_of_units:->"+data.toString());;
////            es2POIRecyclerView.notifyDataSetChanged();
//            String[] value=data.toString().split("@");
//            es2OperatingIncome.setOiNoOfUnit(value[0]);
//            tv_f_no_of_units.setText(value[0]);
//            es2OperatingIncome.setGsiMonthlyRent(value[1]);
//            es2OperatingIncome.setGsiAnuualRent((Float.parseFloat(value[1])*12)+"");
//            tv_f_montly_rent.setText(value[1]);
//            tv_f_annual_rent.setText((Float.parseFloat(value[1])*12)+"");
//
//            if(!TextUtils.isEmpty(et_loss_percentage.getText().toString())){
//                float monthlyloss=TextUtils.isEmpty(es2OperatingIncome.getGsiMonthlyRent())?0:Float.parseFloat(es2OperatingIncome.getGsiMonthlyRent())*(Float.parseFloat(et_loss_percentage.getText().toString())/100);
//                tv_fvl_montly_rent.setText(monthlyloss+"");
//                tv_fvl_annual_rent.setText((monthlyloss*12)+"");
//            }
//            tv_fgoi_montly_rent.setText((Float.parseFloat(value[1])-(TextUtils.isEmpty(es2OperatingIncome.getVlMonthlyRent())?0:Float.parseFloat(es2OperatingIncome.getVlMonthlyRent()))+(TextUtils.isEmpty(es2OperatingIncome.getGoiMontlyRent())?0:Float.parseFloat(es2OperatingIncome.getGoiMontlyRent())))+"");
//            tv_fgoi_annual_rent.setText((Float.parseFloat(tv_fgoi_montly_rent.getText().toString())*12)+"");
//
//
//
//
//        }else if(type.toString().equals("et_sq_feet")){
//            Logger.e("et_sq_feet:->"+data.toString());;
//            es2OperatingIncome.setOiSqFeet(data.toString());
//            et_f_sq_feet.setText(data.toString());
//        }else if(type.toString().equals("et_montly_rent")){
//            Logger.e("et_montly_rent:->"+data.toString());;
//            es2OperatingIncome.setGsiMonthlyRent(data.toString());
//            es2OperatingIncome.setGsiAnuualRent((Float.parseFloat(data.toString())*12)+"");
//            tv_f_montly_rent.setText(data.toString());
//            tv_f_annual_rent.setText((Float.parseFloat(data.toString())*12)+"");
//
//            if(!TextUtils.isEmpty(et_loss_percentage.getText().toString())){
//                float monthlyloss=TextUtils.isEmpty(es2OperatingIncome.getGsiMonthlyRent())?0:Float.parseFloat(es2OperatingIncome.getGsiMonthlyRent())*(Float.parseFloat(et_loss_percentage.getText().toString())/100);
//                tv_fvl_montly_rent.setText(monthlyloss+"");
//                tv_fvl_annual_rent.setText((monthlyloss*12)+"");
//            }
//
//            tv_fgoi_montly_rent.setText((Float.parseFloat(data.toString())-(TextUtils.isEmpty(es2OperatingIncome.getVlMonthlyRent())?0:Float.parseFloat(es2OperatingIncome.getVlMonthlyRent()))+(TextUtils.isEmpty(es2OperatingIncome.getGoiMontlyRent())?0:Float.parseFloat(es2OperatingIncome.getGoiMontlyRent())))+"");
//            tv_fgoi_annual_rent.setText((Float.parseFloat(tv_fgoi_montly_rent.getText().toString())*12)+"");
//
//        }
//
//    }
//    private  List<ES2OperatingIncome.ItemValue> itemValueList;
//    private String[] aUnitType={" ","Studio","1br","2br","3br","4br","5br+"};
//    private ArrayAdapter<String> sUnitTypeAdapter;
//    private void ShowDataInScrollView(){
//        if(sUnitTypeAdapter==null) {
//            sUnitTypeAdapter = new ArrayAdapter<String>(EX2OperatingIncomeActivityCPY.this, android.R.layout.simple_spinner_item, aUnitType);
//            sUnitTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
//        }
//        itemValueList=es2OperatingIncome.getItemValueList();
//        for(int position=0;position<itemValueList.size();position++){
//            View convertView = LayoutInflater.from(EX2OperatingIncomeActivityCPY.this).inflate(R.layout.inflate_exit2_operating_income,null);
//            EditText et_no_of_units= (EditText) convertView.findViewById(R.id.et_no_of_units);
//            Spinner s_unit_type=(Spinner) convertView.findViewById(R.id.s_unit_type);
//            EditText et_sq_feet=(EditText) convertView.findViewById(R.id.et_sq_feet);
//            EditText et_montly_rent=(EditText) convertView.findViewById(R.id.et_montly_rent);
//            EditText et_annual_rent=(EditText) convertView.findViewById(R.id.et_annual_rent);
//            TextView tv_percentage=(TextView) convertView.findViewById(R.id.tv_percentage);
//            s_unit_type.setAdapter(sUnitTypeAdapter);
//            s_unit_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                    itemValueList.get(Integer.parseInt(adapterView.getTag().toString())).setUnityType(i + "");
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> adapterView) {
//                }
//            });
//            if (itemValueList.get(position).getUnityType() != null) {
//                s_unit_type.setSelection(Integer.parseInt(itemValueList.get(position).getUnityType()));
//            } else {
//                s_unit_type.setSelection(0);
//            }
//            s_unit_type.setTag(position);
//            et_no_of_units.setText(itemValueList.get(position).getNoOfUnits());
//            et_sq_feet.setText(itemValueList.get(position).getSquareFt());
//            et_montly_rent.setText(itemValueList.get(position).getMonthlyRent());
//            et_annual_rent.setText(itemValueList.get(position).getAnnualRent());
//            tv_percentage.setText(itemValueList.get(position).getPercent() == null ? "" : (itemValueList.get(position).getPercent() + "%"));
//            et_no_of_units.addTextChangedListener(new MyTextWatcher(position, "et_no_of_units"));
//            et_sq_feet.addTextChangedListener(new MyTextWatcher(position, "et_sq_feet"));
//            if(isMonthly){
//                et_montly_rent.setEnabled(true);
//                et_annual_rent.setEnabled(false);
//                et_montly_rent.setBackgroundResource(R.drawable.tv_edittext_bg);
//                et_annual_rent.setBackgroundResource(R.drawable.tv_disable_bg);
//                et_montly_rent.addTextChangedListener(new MyTextWatcher(position, "et_montly_rent"));
//            }else{
//                et_annual_rent.setEnabled(true);
//                et_montly_rent.setEnabled(false);
//                et_annual_rent.setBackgroundResource(R.drawable.tv_edittext_bg);
//                et_montly_rent.setBackgroundResource(R.drawable.tv_disable_bg);
//                et_annual_rent.addTextChangedListener(new MyTextWatcher(position, "et_annual_rent"));
//            }
//            llViewGroup.addView(convertView);
//        }
//        llViewGroup.addView(footerView);
//        calculationOfData("et_no_of_units");
//        calculationOfData("et_sq_feet");
//        calculationOfData("et_montly_rent");
//    }
//
//
//    class MyTextWatcher implements TextWatcher {
//        private int position;
//        private String type="et_no_of_units";
//        public  MyTextWatcher(int position,String type){
//            this.position=position;
//            this.type=type;
//        }
//        public void updatePosition(int position) {
//            this.position = position;
//        }
//        @Override
//        public void afterTextChanged(Editable s) {}
//        @Override
//        public void beforeTextChanged(CharSequence s, int start,
//                                      int count, int after) {
//        }
//        @Override
//        public void onTextChanged(CharSequence s, int start,
//                                  int before, int count) {
//            if(type.equals("et_no_of_units")) {
//                itemValueList.get(position).setNoOfUnits(s.toString());
//                if(s.length()>0){
//                    if(isMonthly){
//                        float val=Integer.parseInt(s.toString())*12*(TextUtils.isEmpty(itemValueList.get(position).getMonthlyRent())?0:Float.parseFloat(itemValueList.get(position).getMonthlyRent()));
//                        itemValueList.get(position).setAnnualRent(val+"");
//                        ((EditText)llViewGroup.getChildAt(position).findViewById(R.id.et_annual_rent)).setText(itemValueList.get(position).getAnnualRent());
//                    }else{
//                        float val=(TextUtils.isEmpty(itemValueList.get(position).getAnnualRent())?0:Float.parseFloat(itemValueList.get(position).getAnnualRent()))/Integer.parseInt(s.toString())/12;
//                        itemValueList.get(position).setMonthlyRent(val+"");
//                        ((EditText)llViewGroup.getChildAt(position).findViewById(R.id.et_montly_rent)).setText(itemValueList.get(position).getMonthlyRent());
//                    }
//
//                }else{
//                    if(isMonthly){
//                        itemValueList.get(position).setAnnualRent("");
//                        ((EditText)llViewGroup.getChildAt(position).findViewById(R.id.et_annual_rent)).setText(itemValueList.get(position).getAnnualRent());
//                    }else{
//                        itemValueList.get(position).setMonthlyRent("");
//                        ((EditText)llViewGroup.getChildAt(position).findViewById(R.id.et_montly_rent)).setText(itemValueList.get(position).getMonthlyRent());
//                    }
//                }
//            }else if(type.equals("et_sq_feet")){
//                itemValueList.get(position).setSquareFt(s.toString());
//            }else if(type.equals("et_montly_rent")){
//                if(s.length()>0) {
//                    int noOfUnit=TextUtils.isEmpty(itemValueList.get(position).getNoOfUnits())?0:Integer.parseInt(itemValueList.get(position).getNoOfUnits());
//                    itemValueList.get(position).setMonthlyRent(s.toString()+"");
//                    itemValueList.get(position).setAnnualRent((Float.parseFloat(itemValueList.get(position).getMonthlyRent())*12*noOfUnit)+"");
//                    ((EditText)llViewGroup.getChildAt(position).findViewById(R.id.et_annual_rent)).setText(itemValueList.get(position).getAnnualRent());
//                }else{
//                    itemValueList.get(position).setMonthlyRent("");
//                    itemValueList.get(position).setAnnualRent("");
//                }
//            }else if(type.equals("et_annual_rent")){
//                if(s.length()>0) {
//                    itemValueList.get(position).setAnnualRent(s.toString());
//                    int noOfUnit=TextUtils.isEmpty(itemValueList.get(position).getNoOfUnits())?0:Integer.parseInt(itemValueList.get(position).getNoOfUnits());
//                    if(noOfUnit>0){
//                        itemValueList.get(position).setMonthlyRent((Float.parseFloat(s.toString())/12/noOfUnit)+"");
//                        ((EditText)llViewGroup.getChildAt(position).findViewById(R.id.et_montly_rent)).setText(itemValueList.get(position).getMonthlyRent());
//                    }else{
//                        itemValueList.get(position).setMonthlyRent("");
//                        ((EditText)llViewGroup.getChildAt(position).findViewById(R.id.et_montly_rent)).setText("");
//                    }
//                }else{
//                    itemValueList.get(position).setMonthlyRent("");
//                    itemValueList.get(position).setAnnualRent("");
//                }
//            }
//            calculationOfData(type);
//        }
//    }
//
//
//    private  void calculationOfData(String type){
//        if(type.equals("et_no_of_units")){
//            int value=0;
//            float monthRent=0;
//            for(int i=0;i<itemValueList.size();i++){
//                value+=TextUtils.isEmpty(itemValueList.get(i).getNoOfUnits())?0:Integer.parseInt(itemValueList.get(i).getNoOfUnits());
//                if(!TextUtils.isEmpty(itemValueList.get(i).getNoOfUnits()) && !TextUtils.isEmpty(itemValueList.get(i).getMonthlyRent()) && !TextUtils.isEmpty(itemValueList.get(i).getAnnualRent())){
//                    monthRent+=Integer.parseInt(itemValueList.get(i).getNoOfUnits())* Float.parseFloat(itemValueList.get(i).getMonthlyRent());
//                }
//            }
//
//            for(int i=0;i<itemValueList.size();i++){
//                if(isMonthly) {
//                    if (TextUtils.isEmpty(itemValueList.get(i).getAnnualRent())) {
//                        itemValueList.get(i).setPercent("");
//                        ((TextView) llViewGroup.getChildAt(i).findViewById(R.id.tv_percentage)).setText("");
//                    } else {
//                        if (!TextUtils.isEmpty(itemValueList.get(i).getMonthlyRent())) {
//                            float percentage = (Float.parseFloat(itemValueList.get(i).getMonthlyRent()) / monthRent) * 100 * (TextUtils.isEmpty(itemValueList.get(i).getNoOfUnits())?0:Integer.parseInt(itemValueList.get(i).getNoOfUnits()));
//                            itemValueList.get(i).setPercent(percentage + "");
//                            ((TextView) llViewGroup.getChildAt(i).findViewById(R.id.tv_percentage)).setText(itemValueList.get(i).getPercent() + "%");
//                        }
//                    }
//                }else{
//                    if (TextUtils.isEmpty(itemValueList.get(i).getMonthlyRent())) {
//                        itemValueList.get(i).setPercent("");
//                        ((TextView) llViewGroup.getChildAt(i).findViewById(R.id.tv_percentage)).setText("");
//                    } else {
//                        if (!TextUtils.isEmpty(itemValueList.get(i).getMonthlyRent())) {
//                            float percentage = (Float.parseFloat(itemValueList.get(i).getMonthlyRent()) / monthRent) * 100 * (TextUtils.isEmpty(itemValueList.get(i).getNoOfUnits())?0:Integer.parseInt(itemValueList.get(i).getNoOfUnits()));
//                            itemValueList.get(i).setPercent(percentage + "");
//                            ((TextView) llViewGroup.getChildAt(i).findViewById(R.id.tv_percentage)).setText(itemValueList.get(i).getPercent() + "%");
//                        }
//                    }
//                }
//
//            }
//
//            onResponse("et_no_of_units",value+"@"+monthRent);
//        }else if(type.equals("et_sq_feet")){
//            int value=0;
//            for(int i=0;i<itemValueList.size();i++){
//                value+=TextUtils.isEmpty(itemValueList.get(i).getSquareFt())?0:Integer.parseInt(itemValueList.get(i).getSquareFt());
//            }
//            onResponse("et_sq_feet",value+"");
//        }else if(type.equals("et_montly_rent") || type.equals("et_annual_rent")){
//            float value=0;
//            for(int i=0;i<itemValueList.size();i++){
//                if(!TextUtils.isEmpty(itemValueList.get(i).getNoOfUnits()) && !TextUtils.isEmpty(itemValueList.get(i).getMonthlyRent()) && !TextUtils.isEmpty(itemValueList.get(i).getAnnualRent())){
//                    value+=Integer.parseInt(itemValueList.get(i).getNoOfUnits())* Float.parseFloat(itemValueList.get(i).getMonthlyRent());
//                }
//            }
//            for(int i=0;i<itemValueList.size();i++){
//                if(isMonthly) {
//                    if (TextUtils.isEmpty(itemValueList.get(i).getAnnualRent())) {
//                        itemValueList.get(i).setPercent("");
//                        ((TextView) llViewGroup.getChildAt(i).findViewById(R.id.tv_percentage)).setText("");
//                    } else {
//                        if (!TextUtils.isEmpty(itemValueList.get(i).getMonthlyRent())) {
//                            float percentage = (Float.parseFloat(itemValueList.get(i).getMonthlyRent()) / value) * 100 * (TextUtils.isEmpty(itemValueList.get(i).getNoOfUnits()) ? 0 : Integer.parseInt(itemValueList.get(i).getNoOfUnits()));
//                            itemValueList.get(i).setPercent(percentage + "");
//                            ((TextView) llViewGroup.getChildAt(i).findViewById(R.id.tv_percentage)).setText(itemValueList.get(i).getPercent() + "%");
//                        }
//                    }
//                }else{
//                    if (TextUtils.isEmpty(itemValueList.get(i).getMonthlyRent())) {
//                        itemValueList.get(i).setPercent("");
//                        ((TextView) llViewGroup.getChildAt(i).findViewById(R.id.tv_percentage)).setText("");
//                    } else {
//                        if (!TextUtils.isEmpty(itemValueList.get(i).getMonthlyRent())) {
//                            float percentage = (Float.parseFloat(itemValueList.get(i).getMonthlyRent()) / value) * 100 * (TextUtils.isEmpty(itemValueList.get(i).getNoOfUnits()) ? 0 : Integer.parseInt(itemValueList.get(i).getNoOfUnits()));
//                            itemValueList.get(i).setPercent(percentage + "");
//                            ((TextView) llViewGroup.getChildAt(i).findViewById(R.id.tv_percentage)).setText(itemValueList.get(i).getPercent() + "%");
//                        }
//                    }
//                }
//
//            }
//            onResponse("et_montly_rent",value+"");
//        }else{
//
//        }
//    }
//}
