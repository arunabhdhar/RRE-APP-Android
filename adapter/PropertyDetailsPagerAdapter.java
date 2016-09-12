package org.rehab.app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 */
public class PropertyDetailsPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 5;
    private String tabTitles[] = new String[] { "INFORMATION", "PURCHASE ASSUMPTION","REHAB","FLIP ANALYSIS","HOLD AND RENT ANALYSIS","PURCHASE", "REHAB BUDGET" };
    private List<Fragment> mListFragment;
    public PropertyDetailsPagerAdapter(FragmentManager fm,List<Fragment> mListFragment) {
        super(fm);
        this.mListFragment=mListFragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return mListFragment.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
