package org.rehab.app.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 */
public class CalculatorAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mCalculatorFragment;
    public CalculatorAdapter( Context context,FragmentManager fragmentManager,ArrayList<Fragment> mCalculatorFragment){
        super(fragmentManager);
        this.mCalculatorFragment=mCalculatorFragment;

    }

    @Override
    public Fragment getItem(int position) {
        return mCalculatorFragment.get(position);
    }

    @Override
    public int getCount() {
        return mCalculatorFragment.size();
    }
}
