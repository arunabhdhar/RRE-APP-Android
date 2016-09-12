package org.rehab.app.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;

import org.rehab.app.R;

import it.sephiroth.android.library.tooltip.Tooltip;

/**
 */
public class ToolTipUtils {

    private static Tooltip.TooltipView tooltip;
    private static final Tooltip.ClosePolicy mClosePolicy = Tooltip.ClosePolicy.TOUCH_ANYWHERE_CONSUME;
    private static DisplayMetrics metrics;

    /**
     * Method is used for showing the tool tip window with message.
     * @param context   Activity context
     * @param view      View for relation
     * @param message   Message for showing on the screen.
     */
    public static void showToolTip(Context context, View view, String message){
        if(metrics==null){
            metrics = context.getResources().getDisplayMetrics();
        }
        if(tooltip!=null){
            if(tooltip.isShown()){
                tooltip.hide();
                tooltip=null;
            }
        }
        tooltip = Tooltip.make(
                context,
                new Tooltip.Builder()
                        .anchor(view, Tooltip.Gravity.LEFT)
                        .closePolicy(mClosePolicy, 60000)
                        .text(message)
                        .withArrow(true)
                        .withOverlay(true)
                        .maxWidth((int)(metrics.widthPixels/1.5f))
                        .build()
        );
        tooltip.setTextColor(context.getResources().getColor(R.color.white));
        tooltip.show();
    }

    /**
     * Method is used for hide the tool tip window.
     */
    public static void hideToolTip(){
        try {
            if (tooltip != null) {
                tooltip.hide();
                tooltip = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
