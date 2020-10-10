package com.appr.digibiz.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.appr.digibiz.R;
import com.appr.digibiz.fragments.AvailableFragment;
import com.appr.digibiz.fragments.OutOfStockFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InventoryActivity extends AppCompatActivity  implements View.OnClickListener{
    TabLayout tab_layout;
    ViewPager viewPager;
    Toolbar toolbar;

    private AvailableFragment availableFragment;
    private OutOfStockFragment outOfStockFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        ButterKnife.bind(this);

        //set the custom toolbar as the default
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //set custom title
        TextView mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.inventory));

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
    public void onClick(View view) {

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