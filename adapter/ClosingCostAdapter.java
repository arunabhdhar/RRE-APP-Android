package org.rehab.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.rehab.app.R;
import org.rehab.app.interfaces.IASCommon;
import org.rehab.app.models.app.NDClosingCostRehab;
import org.rehab.app.utils.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 */
public class ClosingCostAdapter extends RecyclerView.Adapter<ClosingCostAdapter.CustomViewHolder>{

    private Context context;
    private NDClosingCostRehab ndClosingCostRehab;
    private IASCommon iasCommon;

    public ClosingCostAdapter(Context context, NDClosingCostRehab ndClosingCostRehab) {
        this.ndClosingCostRehab=ndClosingCostRehab;
        this.context = context;
        this.iasCommon= (IASCommon) context;

    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.inflate_holding_cost_adapter, parent,false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder viewHolder, int position) {
        NDClosingCostRehab.ClosingCostItem itemVal= ndClosingCostRehab.getListCostItem().get(position);
        viewHolder.updatePosition(position);

        if(itemVal.getViewType()==2){
            viewHolder.llOption.setVisibility(View.GONE);
            viewHolder.llAddItem.setVisibility(View.VISIBLE);
            viewHolder.tvAddItem.setTag(position);
        }else{
            viewHolder.llOption.setVisibility(View.VISIBLE);
            viewHolder.llAddItem.setVisibility(View.GONE);
            viewHolder.etTitle.setText(itemVal.getTitle());
            viewHolder.etValue.setText(itemVal.getValue());
        }
    }

    @Override
    public int getItemCount() {
        return ndClosingCostRehab.getListCostItem().size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.et_title)            EditText etTitle;
        @BindView(R.id.et_value)            EditText etValue;
        @BindView(R.id.tv_add_item)         TextView tvAddItem;
        @BindView(R.id.ll_add_item)         LinearLayout llAddItem;
        @BindView(R.id.ll_option)           LinearLayout llOption;
        private MyTextWatcher mTWTitle,mTWValue;
        public CustomViewHolder(View itemView) {
            super(itemView);
            try {
                ButterKnife.bind(this, itemView);
            }catch (Exception e){
                e.printStackTrace();
            }
            mTWTitle=new MyTextWatcher("title");
            etTitle.addTextChangedListener(mTWTitle);
            mTWValue=new MyTextWatcher("value");
            etValue.addTextChangedListener(mTWValue);


            tvAddItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos= Integer.parseInt(view.getTag().toString());
                    NDClosingCostRehab.ClosingCostItem newItem=new NDClosingCostRehab.ClosingCostItem();
                    newItem.setViewType(1);
                    newItem.setTitle("Others");
                    ndClosingCostRehab.getListCostItem().add(pos,newItem);
                    notifyDataSetChanged();

                }
            });
        }

        public void updatePosition(int pos){
            mTWTitle.updatePosition(pos);
            mTWValue.updatePosition(pos);
        }

    }


    class MyTextWatcher implements TextWatcher {
        private int position;
        private String type;
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
                ndClosingCostRehab.getListCostItem().get(position).setTitle(s.toString());
            }else if(type.equals("value")){
                ndClosingCostRehab.getListCostItem().get(position).setValue(s.toString());
                calculateData();
            }
        }
    }
    private void calculateData(){
        float budget = 0;
        for( int i=0;i<ndClosingCostRehab.getListCostItem().size();i++) {
            NDClosingCostRehab.ClosingCostItem itemValue = ndClosingCostRehab.getListCostItem().get(i);
            if(!TextUtils.isEmpty(itemValue.getValue())) {
                budget += Float.parseFloat(itemValue.getValue());
            }
        }
        ndClosingCostRehab.setTotalDICosts((int)budget);
        iasCommon.onResponse("update_value",budget);
    }

}
