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

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Adapter class for showing the left menu slider.
 *
 * @author and15031989
 */
public class ES2POIAdapterCPY extends BaseAdapter {

    private Context context;
    private boolean isMonthly=true;

    private ES2OperatingIncome es2OperatingIncome;

    private List<ES2OperatingIncome.ItemValue> itemValueList;
    private String[] aUnitType={" ","Studio","1br","2br","3br","4br","5br+"};
    private ArrayAdapter<String> sUnitTypeAdapter;
    private IASCommon iasCommon;
    private HashMap<Integer,List<TextWatcher>> mListTextWatcher=new HashMap<>();

    public ES2POIAdapterCPY(Context context, ES2OperatingIncome es2OperatingIncome, IASCommon iasCommon) {
        this.context = context;
        this.es2OperatingIncome=es2OperatingIncome;
        itemValueList=es2OperatingIncome.getItemValueList();
        sUnitTypeAdapter = new ArrayAdapter<String>(context,R.layout.inflate_spinner_row, aUnitType);
        sUnitTypeAdapter.setDropDownViewResource(R.layout.inflate_spinner_row); // The drop down view
        this.iasCommon=iasCommon;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.inflate_exit2_operating_income, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

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


        viewHolder.s_unit_type.setTag(position);
        viewHolder.s_unit_type.setAdapter(sUnitTypeAdapter);
        viewHolder.s_unit_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Logger.e("view:-->"+adapterView.getTag().toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        viewHolder.et_no_of_units.setText(itemValueList.get(position).getNoOfUnits());
        viewHolder.et_no_of_units.addTextChangedListener(new MyTextWatcher(position,"et_no_of_units"));

        viewHolder.et_sq_feet.addTextChangedListener(new MyTextWatcher(position,"et_sq_feet"));
        viewHolder.et_montly_rent.addTextChangedListener(new MyTextWatcher(position,"et_montly_rent"));
        viewHolder.et_annual_rent.addTextChangedListener(new MyTextWatcher(position,"et_annual_rent"));
        return convertView;
    }


    class MyTextWatcher implements TextWatcher {
        private int position;
        private String type;

        public MyTextWatcher(int pos,String type) {
            this.position = pos;
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
            Logger.e("Pos:-->"+position+"   Type:-->"+type);
            if(type.equals("et_no_of_units")) {
                itemValueList.get(position).setNoOfUnits(s.toString());
            }
            calculationOfData(type);
        }
    }

    static class ViewHolder {
        @BindView(R.id.et_no_of_units)          EditText et_no_of_units;
        @BindView(R.id.s_unit_type)             Spinner s_unit_type;
        @BindView(R.id.et_sq_feet)              EditText et_sq_feet;
        @BindView(R.id.et_montly_rent)          EditText et_montly_rent;
        @BindView(R.id.et_annual_rent)          EditText et_annual_rent;
        @BindView(R.id.tv_percentage)           TextView tv_percentage;
        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }

    }



    private  void calculationOfData(String type){
        if(type.equals("et_no_of_units")){
            int value=0;
            for(int i=0;i<itemValueList.size();i++){
                value+=TextUtils.isEmpty(itemValueList.get(i).getNoOfUnits())?0:Integer.parseInt(itemValueList.get(i).getNoOfUnits());
            }
            iasCommon.onResponse("et_no_of_units",value+"");
        }
    }
}
