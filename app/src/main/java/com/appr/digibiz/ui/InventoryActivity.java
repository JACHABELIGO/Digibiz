package com.appr.digibiz.ui;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.appr.digibiz.R;
import com.appr.digibiz.adapter.ViewPagerAdapter;
import com.appr.digibiz.fragments.AvailableFragment;
import com.appr.digibiz.fragments.OutOfStockFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import butterknife.ButterKnife;

public class InventoryActivity extends AppCompatActivity  implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    TabLayout tab_layout;
    ViewPager viewPager;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ViewPagerAdapter viewPagerAdapter;
    TabItem tab_available;
    TabItem tab_outOfStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        ButterKnife.bind(this);

        drawerLayout = findViewById(R.id.drawer_layout_inventory);
        navigationView = findViewById(R.id.inventory_nav);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();
        tab_available =findViewById(R.id.tab_available);
        tab_outOfStock =findViewById(R.id.tab_outOfStock);
        tab_layout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewPager);

//        //navigation drawer menu
        toggle = new ActionBarDrawerToggle(InventoryActivity.this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        AvailableFragment availableFragment = new AvailableFragment();
        OutOfStockFragment outOfStockFragment = new OutOfStockFragment();
        FragmentManager fm = getSupportFragmentManager();

        fm.beginTransaction().add(R.id.drawer_layout_inventory, availableFragment).commit();
        fm.beginTransaction().add(R.id.drawer_layout_inventory, outOfStockFragment).commit();

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), tab_layout.getTabCount());
        viewPager.setAdapter(viewPagerAdapter);

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
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_layout));

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //TODO: place intents to the activities here
        return true;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onBackPressed() {
        //this enables user to go back to the activity from the nav drawer
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}