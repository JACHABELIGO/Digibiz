package com.appr.digibiz.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

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

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}