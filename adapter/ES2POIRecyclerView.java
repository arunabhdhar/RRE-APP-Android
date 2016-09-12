package org.rehab.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.rehab.app.R;
import org.rehab.app.interfaces.IASCommon;
import org.rehab.app.models.app.ES2OperatingIncome;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 */
public class ES2POIRecyclerView extends RecyclerView.Adapter<ES2POIRecyclerView.CustomViewHolder> {

    private List<ES2OperatingIncome.ItemValue> itemValueList;
    private String[] aUnitType={" ","Studio","1br","2br","3br","4br","5br+"};
    private ArrayAdapter<String> sUnitTypeAdapter;
    private IASCommon iasCommon;
    private HashMap<Integer,CustomViewHolder> mViewMap=new HashMap<>();
    private Context context;
    private boolean isMonthly=true;
    private ES2OperatingIncome es2OperatingIncome;
    private RecyclerView rvData;


    public ES2POIRecyclerView(Context context, ES2OperatingIncome es2OperatingIncome, IASCommon iasCommon,RecyclerView rvData) {
        this.context = context;
        this.es2OperatingIncome=es2OperatingIncome;
        itemValueList=es2OperatingIncome.getItemValueList();
        sUnitTypeAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, aUnitType);
        sUnitTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        this.iasCommon=iasCommon;
        this.rvData=rvData;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inflate_exit2_operating_income, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view,new MyTextWatcher("et_no_of_units"),new MyTextWatcher("et_sq_feet"),new MyTextWatcher("et_montly_rent"));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder viewHolder, int position) {

        viewHolder.mtwNoOfUnits.updatePosition(position);
        viewHolder.mtwSquareFit.updatePosition(position);
        viewHolder.mtwRent.updatePosition(position);

        viewHolder.s_unit_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemValueList.get(Integer.parseInt(adapterView.getTag().toString())).setUnityType(i+"");
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        if(isMonthly){
            viewHolder.et_annual_rent.setEnabled(false);
            viewHolder.et_montly_rent.setEnabled(true);
            viewHolder.et_montly_rent.setBackgroundResource(R.drawable.tv_edittext_bg);
            viewHolder.et_annual_rent.setBackgroundResource(R.drawable.tv_disable_bg);
        }else{
            viewHolder.et_annual_rent.setEnabled(true);
            viewHolder.et_montly_rent.setEnabled(false);
            viewHolder.et_annual_rent.setBackgroundResource(R.drawable.tv_edittext_bg);
            viewHolder.et_montly_rent.setBackgroundResource(R.drawable.tv_disable_bg);
        }
        viewHolder.s_unit_type.setAdapter(sUnitTypeAdapter);

        if(itemValueList.get(position).getUnityType()!=null){
            viewHolder.s_unit_type.setSelection(Integer.parseInt(itemValueList.get(position).getUnityType()));
        }else{
            viewHolder.s_unit_type.setSelection(0);
        }

        viewHolder.s_unit_type.setTag(position);
        viewHolder.et_no_of_units.setText(itemValueList.get(position).getNoOfUnits());
        viewHolder.et_sq_feet.setText(itemValueList.get(position).getSquareFt());
        viewHolder.et_montly_rent.setText(itemValueList.get(position).getMonthlyRent());
        if(isMonthly) {
            viewHolder.tv_annual_rent_dummy.setText("");
            viewHolder.tv_annual_rent_dummy.setText(itemValueList.get(position).getAnnualRent());
            viewHolder.tv_annual_rent_dummy.invalidate();
        }
        viewHolder.tv_percentage.setText(itemValueList.get(position).getPercent()==null?"":(itemValueList.get(position).getPercent()+"%"));
    }

    @Override
    public int getItemCount() {
        return itemValueList.size();
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.et_no_of_units)          EditText et_no_of_units;
        @BindView(R.id.s_unit_type)             Spinner s_unit_type;
        @BindView(R.id.et_sq_feet)              EditText et_sq_feet;
        @BindView(R.id.et_montly_rent)          EditText et_montly_rent;
        @BindView(R.id.et_annual_rent)          EditText et_annual_rent;
        @BindView(R.id.tv_percentage)           TextView tv_percentage;
        @BindView(R.id.tv_annual_rent_dummy)    TextView tv_annual_rent_dummy;
        MyTextWatcher mtwNoOfUnits,mtwSquareFit,mtwRent;

        public CustomViewHolder(View view,MyTextWatcher mtwNoOfUnits,MyTextWatcher mtwSquareFit,MyTextWatcher mtwRent) {
            super(view);
            ButterKnife.bind(this,view);
            this.mtwNoOfUnits=mtwNoOfUnits;
            this.et_no_of_units.addTextChangedListener(mtwNoOfUnits);
            this.mtwSquareFit=mtwSquareFit;
            et_sq_feet.addTextChangedListener(mtwSquareFit);
            if(isMonthly){
                et_annual_rent.setVisibility(View.GONE);
                tv_annual_rent_dummy.setVisibility(View.VISIBLE);
                mtwRent.updateType("et_montly_rent");
                et_montly_rent.addTextChangedListener(mtwRent);
            }else{
                et_annual_rent.setVisibility(View.VISIBLE);
                tv_annual_rent_dummy.setVisibility(View.GONE);
                mtwRent.updateType("et_annual_rent");
                et_annual_rent.addTextChangedListener(mtwRent);
            }
            this.mtwRent=mtwRent;

        }
    }

    class MyTextWatcher implements TextWatcher {
        private int position;
        private String type;

        public void updatePosition(int position){
            this.position=position;
        }
        public MyTextWatcher(String type) {
            this.type=type;
        }
        public void updateType(String type){
            this.type=type;
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
//            Logger.e("Pos:-->"+position+"   Type:-->"+type);
            if(type.equals("et_no_of_units")) {
                itemValueList.get(position).setNoOfUnits(s.toString());
                if(s.length()>0){
                    if(isMonthly){
                        float val=Integer.parseInt(s.toString())*12*(TextUtils.isEmpty(itemValueList.get(position).getMonthlyRent())?0:Float.parseFloat(itemValueList.get(position).getMonthlyRent()));
                        itemValueList.get(position).setAnnualRent(val+"");
                        if(rvData.getChildAt(position)!=null) {
                            TextView view = (TextView) rvData.getChildAt(position).findViewById(R.id.ll_option2).findViewById(R.id.tv_annual_rent_dummy);
                            view.setText(itemValueList.get(position).getAnnualRent());
                        }
                    }else{
                        float val=(TextUtils.isEmpty(itemValueList.get(position).getMonthlyRent())?0:Float.parseFloat(itemValueList.get(position).getMonthlyRent()))/Integer.parseInt(s.toString())/12;
                        itemValueList.get(position).setMonthlyRent(val+"");
//                        mViewMap.get(position).et_montly_rent.setText(itemValueList.get(position).getMonthlyRent());
                    }

                }else{
                    if(isMonthly){
                        itemValueList.get(position).setAnnualRent("");
                        if( rvData.getChildAt(position)!=null) {
                            TextView view = (TextView) (rvData.getChildAt(position).findViewById(R.id.ll_option2).findViewById(R.id.tv_annual_rent_dummy));
                            view.setText("");
                        }
//                        mViewMap.get(position).et_annual_rent.setText(itemValueList.get(position).getAnnualRent());
                    }else{
                        itemValueList.get(position).setMonthlyRent("");
//                        mViewMap.get(position).et_montly_rent.setText(itemValueList.get(position).getMonthlyRent());
                    }
                }
            }else if(type.equals("et_sq_feet")){
                itemValueList.get(position).setSquareFt(s.toString());
            }else if(type.equals("et_montly_rent")){
                if(s.length()>0) {
                    int noOfUnit=TextUtils.isEmpty(itemValueList.get(position).getNoOfUnits())?0:Integer.parseInt(itemValueList.get(position).getNoOfUnits());
                    itemValueList.get(position).setMonthlyRent(s.toString());
                    itemValueList.get(position).setAnnualRent((Float.parseFloat(itemValueList.get(position).getMonthlyRent())*12*noOfUnit)+"");
                    if(rvData.getChildAt(position)!=null) {
                        TextView view = (TextView) rvData.getChildAt(position).findViewById(R.id.ll_option2).findViewById(R.id.tv_annual_rent_dummy);
                        view.setText(itemValueList.get(position).getAnnualRent());
                    }
                }else{
                    itemValueList.get(position).setMonthlyRent("");
                    itemValueList.get(position).setAnnualRent("");
                }
            }else if(type.equals("et_annual_rent")){
                if(s.length()>0) {
                    itemValueList.get(position).setAnnualRent(s.toString());
                    itemValueList.get(position).setMonthlyRent((Float.parseFloat(s.toString())/12)+"");
                }else{
                    itemValueList.get(position).setMonthlyRent("");
                    itemValueList.get(position).setAnnualRent("");
                }
            }
            calculationOfData(type,position);
        }
    }
    private  void calculationOfData(String type,int pos){
        if(type.equals("et_no_of_units")){
            int value=0;
            float monthRent=0;
            for(int i=0;i<itemValueList.size();i++){
                value+=TextUtils.isEmpty(itemValueList.get(i).getNoOfUnits())?0:Integer.parseInt(itemValueList.get(i).getNoOfUnits());
                if(!TextUtils.isEmpty(itemValueList.get(i).getNoOfUnits()) && !TextUtils.isEmpty(itemValueList.get(i).getMonthlyRent()) && !TextUtils.isEmpty(itemValueList.get(i).getAnnualRent())){
                    monthRent+=Integer.parseInt(itemValueList.get(i).getNoOfUnits())* Float.parseFloat(itemValueList.get(i).getMonthlyRent());
                }
            }
            iasCommon.onResponse("et_no_of_units",value+"@"+monthRent);
        }else if(type.equals("et_sq_feet")){
            int value=0;
            for(int i=0;i<itemValueList.size();i++){
                value+=TextUtils.isEmpty(itemValueList.get(i).getSquareFt())?0:Integer.parseInt(itemValueList.get(i).getSquareFt());
            }
            iasCommon.onResponse("et_sq_feet",value+"");
        }else if(type.equals("et_montly_rent") || type.equals("et_annual_rent")){
            float value=0;
            for(int i=0;i<itemValueList.size();i++){
                if(!TextUtils.isEmpty(itemValueList.get(i).getNoOfUnits()) && !TextUtils.isEmpty(itemValueList.get(i).getMonthlyRent()) && !TextUtils.isEmpty(itemValueList.get(i).getAnnualRent())){
                    value+=Integer.parseInt(itemValueList.get(i).getNoOfUnits())* Float.parseFloat(itemValueList.get(i).getMonthlyRent());
                }
            }
            if(type.equals("et_montly_rent")) {
//                if (mViewMap.get(pos) != null) {
//                    mViewMap.get(pos).et_annual_rent.setText(itemValueList.get(pos).getAnnualRent());
//                }
            }else{
//                if(mViewMap.get(pos)!=null) {
//                    mViewMap.get(pos).et_montly_rent.setText(itemValueList.get(pos).getMonthlyRent());
//                }
            }
            iasCommon.onResponse("et_montly_rent",value+"");
        }
    }
}
