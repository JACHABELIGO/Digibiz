package com.appr.digibiz.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.appr.digibiz.fragments.AvailableFragment;
import com.appr.digibiz.fragments.OutOfStockFragment;

public class PageAdapter extends FragmentPagerAdapter {
    private int numOfTabs;

    public PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AvailableFragment();
            case 1:
                return new OutOfStockFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
