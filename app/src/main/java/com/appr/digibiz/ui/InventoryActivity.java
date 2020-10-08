package com.appr.digibiz.ui;

import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.appr.digibiz.PageAdapter;
import com.appr.digibiz.R;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InventoryActivity extends AppCompatActivity {
    @BindView(R.id.tab_layout) TabLayout tab_layout;
    @BindView(R.id.view_pager) ViewPager viewPager;
    PageAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        ButterKnife.bind(this);

        pageAdapter = new PageAdapter(getSupportFragmentManager(), tab_layout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 1) {
                    tab_layout.setBackgroundColor(ContextCompat.getColor(InventoryActivity.this, R.color.colorTextIcons));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(InventoryActivity.this, R.color.colorDivider));
                    }
                } else if (tab.getPosition() == 2) {
                    tab_layout.setBackgroundColor(ContextCompat.getColor(InventoryActivity.this, R.color.colorTextIcons));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(InventoryActivity.this, R.color.colorDivider));
                    }
                } else {
                    tab_layout.setBackgroundColor(ContextCompat.getColor(InventoryActivity.this, R.color.colorTextIcons));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(InventoryActivity.this, R.color.colorDivider));
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_layout) );
    }
}