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

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import androidx.viewpager.widget.ViewPager;

import com.appr.digibiz.R;
import com.appr.digibiz.adapter.InventoryPagerAdapter;
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
    InventoryPagerAdapter viewPagerAdapter;
    TabItem tab_available;
    TabItem tab_outOfStock;

//    @BindView(R.id.tab_layout) TabLayout tab_layout;
//    @BindView(R.id.view_pager) ViewPager viewPager;

//    private AvailableFragment availableFragment;
//    private OutOfStockFragment outOfStockFragment;


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

//        mFindSubmitButton.setOnClickListener(this);

        AvailableFragment availableFragment = new AvailableFragment();
        OutOfStockFragment outOfStockFragment = new OutOfStockFragment();
        FragmentManager fm = getSupportFragmentManager();
//        InventoryDialogFragment inventoryDialogFragment = new InventoryDialogFragment();
//        inventoryDialogFragment.show(fm, "Sample fragment");

        fm.beginTransaction().add(R.id.drawer_layout_inventory, availableFragment).commit();



//        availableFragment = new AvailableFragment();
//        outOfStockFragment = new OutOfStockFragment();

//        tab_layout.setupWithViewPager(viewPager);


//        viewPagerAdapter = new InventoryPagerAdapter(getSupportFragmentManager(),0);
//        viewPagerAdapter.addFragment(availableFragment, "AVAILABLE");
//        viewPagerAdapter.addFragment(outOfStockFragment, "OUT OF STOCK");

        viewPagerAdapter = new InventoryPagerAdapter(getSupportFragmentManager(), tab_layout.getTabCount());
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


//    private class InventoryPagerAdapter extends FragmentPagerAdapter {
//        private List<Fragment> fragmentList = new ArrayList<>();
//        private List<String> fragmentTitle = new ArrayList<>();
//        public InventoryPagerAdapter(@NonNull FragmentManager fm, int behavior) {
//            super(fm, behavior);
//        }
//
//        public void addFragment(Fragment fragment,String title){
//            fragmentList.add(fragment);
//            fragmentTitle.add(title);
//        }
//
//        @Nullable
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return fragmentTitle.get(position);
//        }
//
//        @NonNull
//        @Override
//        public Fragment getItem(int position) {
//            return fragmentList.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return fragmentList.size();
//        }


}