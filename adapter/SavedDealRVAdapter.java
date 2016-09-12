package org.rehab.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.rehab.app.R;
import org.rehab.app.constants.AppConstants;
import org.rehab.app.interfaces.IRecycleViewOnItemClickListener;
import org.rehab.app.models.response.DealItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 */
public class SavedDealRVAdapter extends RecyclerView.Adapter<SavedDealRVAdapter.CustomViewHolder> {

    private List<DealItem> itemValueList;
    private Context context;
    IRecycleViewOnItemClickListener mItemClickListener;


    public SavedDealRVAdapter(Context context, ArrayList<DealItem> itemValueList,IRecycleViewOnItemClickListener  mItemClickListener) {
        this.context = context;
        this.itemValueList=itemValueList;
        this.mItemClickListener=mItemClickListener;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.inflate_deal_item, viewGroup,false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder viewHolder, int position) {
        viewHolder.tvTitle.setText(itemValueList.get(position).getPropertyName());
        viewHolder.tvDesc.setText(itemValueList.get(position).getPropertyStrtAddress());

        viewHolder.tvProfit.setText("$"+itemValueList.get(position).getProjectedProfitAfterLenderSplitStrgy1() );
        viewHolder.tvRehabCost.setText("$"+itemValueList.get(position).getRehabBudget());
        viewHolder.tvPurchase.setText("$"+itemValueList.get(position).getPurchasePrice());
        if(!TextUtils.isEmpty(itemValueList.get(position).getPropertyImage())){
            Picasso.with(context).load(AppConstants.IMAGE_URL+itemValueList.get(position).getPropertyImage()).into(viewHolder.ivImage);
        }else{
            viewHolder.ivImage.setImageResource(R.drawable.image_banner);
        }
        viewHolder.setOnCLickListener(itemValueList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemValueList.size();
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)                TextView tvTitle;
        @BindView(R.id.tv_desc)                 TextView tvDesc;
        @BindView(R.id.iv_image)                ImageView ivImage;
        @BindView(R.id.tv_profit)               TextView tvProfit;
        @BindView(R.id.tv_rehab_cost)           TextView tvRehabCost;
        @BindView(R.id.tv_purchase)             TextView tvPurchase;
        private View view;
        public CustomViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            this.view=view;
        }


        public void setOnCLickListener(final Object bean) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null)
                        mItemClickListener.onItemClickListener(0, bean);
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (mItemClickListener != null)
                        mItemClickListener.onItemLongClickListener(0, bean);
                    return false;
                }
            });
        }

    }
}
