package org.rehab.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.rehab.app.R;
import org.rehab.app.interfaces.IASCommon;
import org.rehab.app.models.app.RadiantBudgetSelOption;
import org.rehab.app.utils.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 */
public class DetailInputBudgetAdapter extends RecyclerView.Adapter<DetailInputBudgetAdapter.CustomViewHolder> {

    private Context context;
    private RadiantBudgetSelOption.DetailInputData detailInputData;
    private String[] aMonthCount={" ","1","2"};
    private ArrayAdapter<String> sMonthAdapter;
    private IASCommon iasCommon;
    public DetailInputBudgetAdapter(Context context, RadiantBudgetSelOption.DetailInputData detailInputData, IASCommon iasCommon){
        this.context=context;
        this.detailInputData=detailInputData;
        sMonthAdapter = new ArrayAdapter<String>(context,R.layout.inflate_spinner_row, aMonthCount);
        sMonthAdapter.setDropDownViewResource(R.layout.inflate_spinner_row); // The drop down view
        this.iasCommon=iasCommon;
    }



    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.inflate_budget_detail_input_item, viewGroup,false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder viewHolder, int position) {
        Logger.e("Postion :-->"+position);
        RadiantBudgetSelOption.ItemValue itemVal= detailInputData.getItemValues().get(position);
        viewHolder.updatePosition(position);
        if(itemVal.getViewType()==1){
            viewHolder.rlItems.setVisibility(View.GONE);
            viewHolder.llAddItems.setVisibility(View.GONE);
            viewHolder.etTitle.setVisibility(View.VISIBLE);
            viewHolder.etTitle.setText(itemVal.getItemName());
        }else if(itemVal.getViewType()==3){
            viewHolder.rlItems.setVisibility(View.GONE);
            viewHolder.llAddItems.setVisibility(View.VISIBLE);
            viewHolder.etTitle.setVisibility(View.GONE);
            viewHolder.tvAddView.setTag(position);

        }else{
            viewHolder.rlItems.setVisibility(View.VISIBLE);
            viewHolder.llAddItems.setVisibility(View.GONE);
            viewHolder.etTitle.setVisibility(View.VISIBLE);
            if(detailInputData.isShowBid()){
                viewHolder.llOptions2.setVisibility(View.VISIBLE);
            }else{
                viewHolder.llOptions2.setVisibility(View.GONE);
            }

            if(detailInputData.isRehabBudInDraft()){
                viewHolder.rlMonthToPay.setVisibility(View.VISIBLE);
            }else{
                viewHolder.rlMonthToPay.setVisibility(View.GONE);
            }
            viewHolder.etTitle.setText(itemVal.getItemName());
            viewHolder.etDesc.setText(itemVal.getDetails());
            viewHolder.etSqFt.setText(itemVal.getQty()+"");
            viewHolder.etRate.setText(itemVal.getRate()+"");
            viewHolder.etBudget.setText(itemVal.getBudget()+"");
            viewHolder.etBid1.setText(itemVal.getBid1()+"");
            viewHolder.etBid2.setText(itemVal.getBid2()+"");
            viewHolder.etBid3.setText(itemVal.getBid3()+"");
            viewHolder.spMonthToPay.setTag(position);
            viewHolder.spMonthToPay.setAdapter(sMonthAdapter);
            viewHolder.spMonthToPay.setSelection(itemVal.getMonthToPay());
        }

    }

    @Override
    public int getItemCount() {
        return detailInputData.getItemValues().size();
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.et_title)                EditText etTitle;
        @BindView(R.id.et_desc)                 EditText etDesc;
        @BindView(R.id.et_sq_ft)                EditText etSqFt;
        @BindView(R.id.et_rate)                 EditText etRate;
        @BindView(R.id.et_budget)               EditText etBudget;
        @BindView(R.id.et_bid1)                 EditText etBid1;
        @BindView(R.id.et_bid2)                 EditText etBid2;
        @BindView(R.id.et_bid3)                 EditText etBid3;
        @BindView(R.id.tv_add_item)             TextView tvAddView;
        @BindView(R.id.rl_item)                 LinearLayout rlItems;
        @BindView(R.id.ll_add_item)             LinearLayout llAddItems;
        @BindView(R.id.ll_option2)              LinearLayout llOptions2;
        @BindView(R.id.rl_month_to_pay)         RelativeLayout rlMonthToPay;
        @BindView(R.id.sp_month_to_pay)         Spinner spMonthToPay;

        MyTextWatcher mTWTitle,mTWDesc,mTWSqFt,mTWRate,mTWBid1,mTWBid2,mTWBid3,mTWBudget;
        public CustomViewHolder(View view) {
            super(view);
            try {
                ButterKnife.bind(this, view);
            }catch (Exception e){
                e.printStackTrace();
            }
            mTWTitle=new MyTextWatcher("title");
            etTitle.addTextChangedListener(mTWTitle);
            mTWDesc=new MyTextWatcher("desc");
            etDesc.addTextChangedListener(mTWDesc);
            mTWSqFt=new MyTextWatcher("sqft",etBudget);
            etSqFt.addTextChangedListener(mTWSqFt);
            mTWRate=new MyTextWatcher("rate",etBudget);
            etRate.addTextChangedListener(mTWRate);
            mTWBid1=new MyTextWatcher("bid1");
            etBid1.addTextChangedListener(mTWBid1);
            mTWBid2=new MyTextWatcher("bid2");
            etBid2.addTextChangedListener(mTWBid2);
            mTWBid3=new MyTextWatcher("bid3");
            etBid3.addTextChangedListener(mTWBid3);
            mTWBudget=new MyTextWatcher("budget");
            etBudget.addTextChangedListener(mTWBudget);



            tvAddView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos= Integer.parseInt(view.getTag().toString());
                    RadiantBudgetSelOption.ItemValue newItem=new RadiantBudgetSelOption.ItemValue();
                    newItem.setViewType(2);
                    newItem.setItemName("Others");
                    detailInputData.getItemValues().add(pos,newItem);
                    notifyDataSetChanged();

                }
            });

            spMonthToPay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    detailInputData.getItemValues().get(Integer.parseInt(adapterView.getTag().toString())).setMonthToPay(i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }

        public void updatePosition(int pos){
            mTWTitle.updatePosition(pos);
            mTWDesc.updatePosition(pos);
            mTWSqFt.updatePosition(pos);
            mTWRate.updatePosition(pos);
            mTWBid1.updatePosition(pos);
            mTWBid2.updatePosition(pos);
            mTWBid3.updatePosition(pos);
            mTWBudget.updatePosition(pos);
        }


    }


    class MyTextWatcher implements TextWatcher {
        private int position;
        private String type;
        private  EditText etBudget;
        MyTextWatcher(String type,EditText etBudget){
            this.type=type;
            this.etBudget=etBudget;
        }
        MyTextWatcher(String type){
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
            Logger.e("Position:--> "+position+"  type:-> "+type+ " Chars:->"+s.toString());
            if(type.equals("title")){
                detailInputData.getItemValues().get(position).setItemName(s.toString());
            }else if(type.equals("desc")){
                detailInputData.getItemValues().get(position).setDetails(s.toString());
            }else {
                float value=s.length()>0?Float.parseFloat(s.toString()):0;
                if(type.equals("sqft")){
                    detailInputData.getItemValues().get(position).setQty(value);
                    float budgetValue=value* detailInputData.getItemValues().get(position).getRate();
                    detailInputData.getItemValues().get(position).setBudget(budgetValue);
                    etBudget.setText(budgetValue+"");
                }else if(type.equals("rate")){
                    detailInputData.getItemValues().get(position).setRate(value);
                    float budgetValue=value* detailInputData.getItemValues().get(position).getQty();
                    detailInputData.getItemValues().get(position).setBudget(budgetValue);
                    etBudget.setText(budgetValue+"");
                }else if(type.equals("budget")){
                    detailInputData.getItemValues().get(position).setBudget(value);
                    calculatedValue();
                }else if(type.equals("bid1")){
                    detailInputData.getItemValues().get(position).setBid1(value);
                    calculatedValue();
                }else if(type.equals("bid2")){
                    detailInputData.getItemValues().get(position).setBid2(value);
                    calculatedValue();
                }else if(type.equals("bid3")){
                    detailInputData.getItemValues().get(position).setBid3(value);
                    calculatedValue();
                }

            }


        }
    }


    private void calculatedValue(){
        float budget = 0,bid1 = 0,bid2 = 0,bid3 = 0;
        for( int i=0;i<detailInputData.getItemValues().size();i++){
            RadiantBudgetSelOption.ItemValue itemValue=detailInputData.getItemValues().get(i);
            budget+=itemValue.getBudget();
            bid1+=itemValue.getBid1();
            bid2+=itemValue.getBid2();
            bid3+=itemValue.getBid3();
        }
        detailInputData.setTotalBid1(bid1);
        detailInputData.setTotalBid2(bid2);
        detailInputData.setTotalBid3(bid3);
        detailInputData.setTotalBudget(budget);
        iasCommon.onResponse("calculated_data","update");
    }
}
