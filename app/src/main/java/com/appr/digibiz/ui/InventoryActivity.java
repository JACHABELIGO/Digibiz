package com.appr.digibiz.ui;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.appr.digibiz.R;
import com.appr.digibiz.adapter.InventoryPagerAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;


public class InventoryActivity extends AppCompatActivity  implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    TabLayout tab_layout;
    ViewPager viewPager;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    InventoryPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        //view hooks
        drawerLayout = findViewById(R.id.drawer_layout_inventory);
        navigationView = findViewById(R.id.inventory_nav);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();
        tab_layout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewPager);

        setUpNavigationDrawer();
        setupViewPagerAdapter();

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

    private void setUpNavigationDrawer() {
        toggle = new ActionBarDrawerToggle(InventoryActivity.this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void setupViewPagerAdapter() {
        viewPagerAdapter = new InventoryPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tab_layout.setupWithViewPager(viewPager);
    }
}