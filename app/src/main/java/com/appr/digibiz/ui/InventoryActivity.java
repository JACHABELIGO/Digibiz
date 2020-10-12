package com.appr.digibiz.ui;


import android.content.Intent;
import android.os.Build;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

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
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

import butterknife.ButterKnife;

public class InventoryActivity extends AppCompatActivity  implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{
    TabLayout tab_layout;
    ViewPager viewPager;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    
    @BindView(R.id.tab_layout) TabLayout tab_layout;
    @BindView(R.id.view_pager) ViewPager viewPager;

    private AvailableFragment availableFragment;
    private OutOfStockFragment outOfStockFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        ButterKnife.bind(this);

        drawerLayout = findViewById(R.id.drawer_layout_inventory);
        navigationView = findViewById(R.id.inventory_nav);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();

//        //navigation drawer menu
        toggle = new ActionBarDrawerToggle(InventoryActivity.this, drawerLayout, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)) {
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
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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