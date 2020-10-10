package com.appr.digibiz.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.appr.digibiz.R;
import com.appr.digibiz.fragments.AvailableFragment;
import com.appr.digibiz.fragments.OutOfStockFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class InventoryActivity extends AppCompatActivity {
    TabLayout tab_layout;
    ViewPager viewPager;

    private AvailableFragment availableFragment;
    private OutOfStockFragment outOfStockFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        tab_layout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        availableFragment = new AvailableFragment();
        outOfStockFragment = new OutOfStockFragment();

        tab_layout.setupWithViewPager(viewPager);


        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),0);
        viewPagerAdapter.addFragment(availableFragment, "AVAILABLE");
        viewPagerAdapter.addFragment(outOfStockFragment, "OUT OF STOCK");

        viewPager.setAdapter(viewPagerAdapter);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList = new ArrayList<>();
        private List<String> fragmentTitle = new ArrayList<>();
        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment,String title){
            fragmentList.add(fragment);
            fragmentTitle.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}