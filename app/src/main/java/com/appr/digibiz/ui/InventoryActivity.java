package com.appr.digibiz.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;

import com.appr.digibiz.PageAdapter;
import com.appr.digibiz.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class InventoryActivity extends AppCompatActivity {
    TabLayout tab_layout;
    PageAdapter pageAdapter;
    TabItem tab_available;
    TabItem tab_outOfStock;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        tab_layout = findViewById(R.id.tab_layout);
        tab_available = findViewById(R.id.tab_available);
        tab_outOfStock = findViewById(R.id.tab_outOfStock);
        viewPager = findViewById(R.id.viewPager);

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