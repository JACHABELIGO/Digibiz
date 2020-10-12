package com.appr.digibiz.ui;


import android.content.Intent;
import android.os.Build;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import androidx.viewpager.widget.ViewPager;

import com.appr.digibiz.R;
import com.appr.digibiz.fragments.AvailableFragment;
import com.appr.digibiz.fragments.InventoryDialogFragment;
import com.appr.digibiz.fragments.OutOfStockFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class InventoryActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.tab_layout) TabLayout tab_layout;
    @BindView(R.id.view_pager) ViewPager viewPager;
//    @BindView(R.id.buttonA) Button mFindSubmitButton;




    private AvailableFragment availableFragment;
    private OutOfStockFragment outOfStockFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        ButterKnife.bind(this);

//        mFindSubmitButton.setOnClickListener(this);

        FragmentManager fm = getSupportFragmentManager();
        InventoryDialogFragment inventoryDialogFragment = new InventoryDialogFragment();
        inventoryDialogFragment.show(fm, "Sample fragment");

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

    @Override
    public void onClick(View v){

    }
}