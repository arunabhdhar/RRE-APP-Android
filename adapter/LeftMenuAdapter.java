package org.rehab.app.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.rehab.app.R;
import org.rehab.app.constants.AppConstants;
import org.rehab.app.interfaces.IASCommon;
import org.rehab.app.preferences.MySharedPreference;
import org.rehab.app.utils.transformation.RoundedTransformationBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Adapter class for showing the left menu slider.
 *
 * @author and15031989
 */
public class LeftMenuAdapter extends BaseAdapter {

    private Context context;
    private Transformation transformation;
    private IASCommon iasCommon;

    public LeftMenuAdapter(Context context,IASCommon iasCommon) {
        this.context = context;
        this.iasCommon=iasCommon;
        transformation = new RoundedTransformationBuilder()
                .scaleType(ImageView.ScaleType.FIT_XY)
                .cornerRadiusDp(5)
                .oval(false)
                .build();
    }

    @Override
    public int getCount() {
        return (1);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.side_menu, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(MySharedPreference.getInstance(context).getName());
        if (!TextUtils.isEmpty(MySharedPreference.getInstance(context).getUserProfileImage())) {
            Picasso.with(context).load(MySharedPreference.getInstance(context).getUserProfileImage()).placeholder(R.drawable.profile_pic_frame) .transform(transformation).fit().centerInside().into(viewHolder.imageView);
        }

        viewHolder.ivSetting.setTag(AppConstants.LM_SETTINGS);
        viewHolder.ivSetting.setOnClickListener(mClickListener);
        viewHolder.ivQuickCalc.setTag(AppConstants.LM_QUICK_CALCULATOR);
        viewHolder.ivQuickCalc.setOnClickListener(mClickListener);
        viewHolder.ivNewDeal.setTag(AppConstants.LM_NEW_DEAL);
        viewHolder.ivNewDeal.setOnClickListener(mClickListener);
        viewHolder.ivAboutUs.setTag(AppConstants.LM_ABOUT_US);
        viewHolder.ivAboutUs.setOnClickListener(mClickListener);
        viewHolder.ivContactUs.setTag(AppConstants.LM_CONTACT_US);
        viewHolder.ivContactUs.setOnClickListener(mClickListener);
        viewHolder.ivTutorial.setTag(AppConstants.LM_TUTORIALS);
        viewHolder.ivTutorial.setOnClickListener(mClickListener);
        viewHolder.vUserProfile.setTag(AppConstants.LM_USER_PROFILE);
        viewHolder.vUserProfile.setOnClickListener(mClickListener);
        viewHolder.ivSavedDeal.setTag(AppConstants.LM_SAVE_DEAL);
        viewHolder.ivSavedDeal.setOnClickListener(mClickListener);
        viewHolder.tvCompare.setTag(AppConstants.LM_COMPARE);
        viewHolder.tvCompare.setOnClickListener(mClickListener);
        return convertView;
    }

    private View.OnClickListener mClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            iasCommon.onResponse("left_menu",view.getTag().toString());
        }
    };


    static class ViewHolder {

        @BindView(R.id.iv_userimage)        ImageView imageView;
        @BindView(R.id.tv_name)             TextView tvName;
        @BindView(R.id.iv_settings)         ImageView ivSetting;
        @BindView(R.id.iv_quick_calc)       ImageView ivQuickCalc;
        @BindView(R.id.iv_new_deal)         ImageView ivNewDeal;
        @BindView(R.id.iv_save_deal)        ImageView ivSavedDeal;
        @BindView(R.id.iv_about_us)         ImageView ivAboutUs;
        @BindView(R.id.iv_contact_us)       ImageView ivContactUs;
        @BindView(R.id.iv_tutorial)         ImageView ivTutorial;
        @BindView(R.id.tv_compare)          View tvCompare;
        @BindView(R.id.ll_top_view)         View vUserProfile;

        public ViewHolder(View view) {
            ButterKnife.bind(this,view);

        }

    }
}
