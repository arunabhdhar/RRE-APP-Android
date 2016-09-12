package org.rehab.app.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.rehab.app.R;
import org.rehab.app.interfaces.IASCommon;
import org.rehab.app.models.app.ES2OperatingIncome;
import org.rehab.app.utils.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Adapter class for showing the left menu slider.
 *
 * @author and15031989
 */
public class ES2POIAdapter extends BaseAdapter {

    private Context context;
    private boolean isMonthly=true;

    private ES2OperatingIncome es2OperatingIncome;

    private List<ES2OperatingIncome.ItemValue> itemValueList;
    private String[] aUnitType={" ","Studio","1br","2br","3br","4br","5br+"};
    private ArrayAdapter<String> sUnitTypeAdapter;
    private IASCommon iasCommon;
    //    private List<ViewHolder> mViewMap=new ArrayList<>();
    private View mFooterView;

    public ES2POIAdapter(Context context, ES2OperatingIncome es2OperatingIncome,IASCommon iasCommon) {
        this.context = context;
        this.es2OperatingIncome=es2OperatingIncome;
        itemValueList=es2OperatingIncome.getItemValueList();
        sUnitTypeAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, aUnitType);
        sUnitTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        this.iasCommon=iasCommon;
//        for(int i=0;i<itemValueList.size();i++){
//            mViewMap.add(null);
//        }
    }

    public void setIsMonthly(boolean isMonthly){
        this.isMonthly=isMonthly;
    }
    @Override
    public int getCount() {
        return itemValueList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Logger.e("Positonl  :-->"+position);
        boolean isFooterView = false;
        ViewHolder viewHolder = null;
        if(convertView!=null){
            viewHolder= (ViewHolder) convertView.getTag();
            viewHolder.myCustomEditTextListener.updatePosition(position);
        }else{
            convertView = LayoutInflater.from(context).inflate(R.layout.inflate_exit2_operating_income, parent, false);
            viewHolder = new ViewHolder(convertView,new MyTextWatcher());
            viewHolder.myCustomEditTextListener.updatePosition(position);
//                viewHolder.et_no_of_units.addTextChangedListener(new MyTextWatcher(position, "et_no_of_units"));
//                viewHolder.et_sq_feet.addTextChangedListener(new MyTextWatcher(position, "et_sq_feet"));

            viewHolder.s_unit_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    itemValueList.get(Integer.parseInt(adapterView.getTag().toString())).setUnityType(i + "");
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
            if (isMonthly) {
                viewHolder.et_annual_rent.setEnabled(false);
                viewHolder.et_montly_rent.setEnabled(true);
                viewHolder.et_montly_rent.setBackgroundResource(R.drawable.tv_edittext_bg);
                viewHolder.et_annual_rent.setBackgroundResource(R.drawable.tv_disable_bg);
//                    viewHolder.et_montly_rent.addTextChangedListener(new MyTextWatcher(position, "et_montly_rent"));
            } else {
                viewHolder.et_annual_rent.setEnabled(true);
                viewHolder.et_montly_rent.setEnabled(false);
                viewHolder.et_annual_rent.setBackgroundResource(R.drawable.tv_edittext_bg);
                viewHolder.et_montly_rent.setBackgroundResource(R.drawable.tv_disable_bg);
//                viewHolder.et_annual_rent.addTextChangedListener(new MyTextWatcher(position,"et_annual_rent"));
            }
            viewHolder.s_unit_type.setAdapter(sUnitTypeAdapter);
            convertView.setTag(viewHolder);
        }





/*

        if(mViewMap.get(position)!=null){
            viewHolder=mViewMap.get(position);
        }else{
            if(position==itemValueList.size() && mFooterView==null){
                convertView= LayoutInflater.from(context).inflate(R.layout.inflate_footer_exit2_op_inc, parent, false);
                mFooterView=convertView;
                isFooterView=true;
            }else if(position==itemValueList.size()){
                convertView=mFooterView;
                isFooterView=true;
            }
            else {
                convertView = LayoutInflater.from(context).inflate(R.layout.inflate_exit2_operating_income, parent, false);
                viewHolder = new ViewHolder(convertView);
//                viewHolder.et_no_of_units.addTextChangedListener(new MyTextWatcher(position, "et_no_of_units"));
//                viewHolder.et_sq_feet.addTextChangedListener(new MyTextWatcher(position, "et_sq_feet"));

                viewHolder.s_unit_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        itemValueList.get(Integer.parseInt(adapterView.getTag().toString())).setUnityType(i + "");
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
                if (isMonthly) {
                    viewHolder.et_annual_rent.setEnabled(false);
                    viewHolder.et_montly_rent.setEnabled(true);
                    viewHolder.et_montly_rent.setBackgroundResource(R.drawable.tv_edittext_bg);
                    viewHolder.et_annual_rent.setBackgroundResource(R.drawable.tv_disable_bg);
//                    viewHolder.et_montly_rent.addTextChangedListener(new MyTextWatcher(position, "et_montly_rent"));
                } else {
                    viewHolder.et_annual_rent.setEnabled(true);
                    viewHolder.et_montly_rent.setEnabled(false);
                    viewHolder.et_annual_rent.setBackgroundResource(R.drawable.tv_edittext_bg);
                    viewHolder.et_montly_rent.setBackgroundResource(R.drawable.tv_disable_bg);
//                viewHolder.et_annual_rent.addTextChangedListener(new MyTextWatcher(position,"et_annual_rent"));
                }
                viewHolder.s_unit_type.setAdapter(sUnitTypeAdapter);
                mViewMap.add(position,viewHolder);
            }
        }
*/

        if(!isFooterView && viewHolder!=null)  {
            if (itemValueList.get(position).getUnityType() != null) {
                viewHolder.s_unit_type.setSelection(Integer.parseInt(itemValueList.get(position).getUnityType()));
            } else {
                viewHolder.s_unit_type.setSelection(0);
            }

            viewHolder.s_unit_type.setTag(position);
            viewHolder.et_no_of_units.setText(itemValueList.get(position).getNoOfUnits());
            viewHolder.et_sq_feet.setText(itemValueList.get(position).getSquareFt());
            viewHolder.et_montly_rent.setText(itemValueList.get(position).getMonthlyRent());
            viewHolder.et_annual_rent.setText(itemValueList.get(position).getAnnualRent());
            viewHolder.tv_percentage.setText(itemValueList.get(position).getPercent() == null ? "" : (itemValueList.get(position).getPercent() + "%"));



        }else{

        }
        return convertView;
    }


    class MyTextWatcher implements TextWatcher {
        private int position;
        private String type="et_no_of_units";

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
//            if(type.equals("et_no_of_units")) {
                itemValueList.get(position).setNoOfUnits(s.toString());
                if(s.length()>0){
                    if(isMonthly){
                        float val=Integer.parseInt(s.toString())*12*(TextUtils.isEmpty(itemValueList.get(position).getMonthlyRent())?0:Float.parseFloat(itemValueList.get(position).getMonthlyRent()));
                        itemValueList.get(position).setAnnualRent(val+"");
//                        mViewMap.get(position).et_annual_rent.setText(itemValueList.get(position).getAnnualRent());
                    }else{
                        float val=(TextUtils.isEmpty(itemValueList.get(position).getMonthlyRent())?0:Float.parseFloat(itemValueList.get(position).getMonthlyRent()))/Integer.parseInt(s.toString())/12;
                        itemValueList.get(position).setMonthlyRent(val+"");
//                        mViewMap.get(position).et_montly_rent.setText(itemValueList.get(position).getMonthlyRent());
                    }

                }else{
//                    if(isMonthly){
//                        itemValueList.get(position).setAnnualRent("");
//                        mViewMap.get(position).et_annual_rent.setText(itemValueList.get(position).getAnnualRent());
//                    }else{
//                        itemValueList.get(position).setMonthlyRent("");
//                        mViewMap.get(position).et_montly_rent.setText(itemValueList.get(position).getMonthlyRent());
//                    }
                }
            /*}else if(type.equals("et_sq_feet")){
                itemValueList.get(position).setSquareFt(s.toString());
            }else if(type.equals("et_montly_rent")){
                if(s.length()>0) {
                    int noOfUnit=TextUtils.isEmpty(itemValueList.get(position).getNoOfUnits())?0:Integer.parseInt(itemValueList.get(position).getNoOfUnits());
                    itemValueList.get(position).setMonthlyRent(s.toString()+"");
                    itemValueList.get(position).setAnnualRent((Float.parseFloat(itemValueList.get(position).getMonthlyRent())*12*noOfUnit)+"");
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
            }*/
            calculationOfData(type,position);
        }
    }

    class ViewHolder {
        @BindView(R.id.et_no_of_units)          EditText et_no_of_units;
        @BindView(R.id.s_unit_type)             Spinner s_unit_type;
        @BindView(R.id.et_sq_feet)              EditText et_sq_feet;
        @BindView(R.id.et_montly_rent)          EditText et_montly_rent;
        @BindView(R.id.et_annual_rent)          EditText et_annual_rent;
        @BindView(R.id.tv_percentage)           TextView tv_percentage;
        public MyTextWatcher myCustomEditTextListener;
        public ViewHolder(View view, MyTextWatcher myCustomEditTextListener) {
            ButterKnife.bind(this,view);
            this.myCustomEditTextListener=myCustomEditTextListener;
            this.et_no_of_units.addTextChangedListener(myCustomEditTextListener);

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
//            if(type.equals("et_montly_rent")) {
//                if (mViewMap.get(pos) != null) {
//                    mViewMap.get(pos).et_annual_rent.setText(itemValueList.get(pos).getAnnualRent());
//                }
//            }else{
//                if(mViewMap.get(pos)!=null) {
//                    mViewMap.get(pos).et_montly_rent.setText(itemValueList.get(pos).getMonthlyRent());
//                }
//            }
            iasCommon.onResponse("et_montly_rent",value+"");
        }
    }
}
