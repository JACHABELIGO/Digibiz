package com.appr.digibiz.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appr.digibiz.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AvailableFragment extends Fragment implements View.OnClickListener{
        @BindView(R.id.availableListRecyclerView) RecyclerView mAvailableListRecyclerView;
        @BindView(R.id.fab) FloatingActionButton mFab;

        public AvailableFragment() {
//                 Required empty public constructor
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
//                 Inflate the layout for this fragment
                View view = inflater.inflate(R.layout.fragment_available, container, false);
                ButterKnife.bind(this, view);

//                click listeners
                mFab.setOnClickListener(this);
                return view;
        }

        @Override
        public void onClick(View view) {

                if (view == mFab) {
                    InventoryDialogFragment inventoryDialogFragment = new InventoryDialogFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.drawer_layout_inventory, inventoryDialogFragment);
                    transaction.commit();
                }
        }
//            @NonNull
//            @Override
//            public FragmentTransaction beginTransaction() {
//                return null;
//            }
//
//            @Override
//            public boolean executePendingTransactions() {
//                return false;
//            }
//
//            @Nullable
//            @Override
//            public Fragment findFragmentById(int id) {
//                return null;
//            }
//
//            @Nullable
//            @Override
//            public Fragment findFragmentByTag(@Nullable String tag) {
//                return null;
//            }
//
//            @Override
//            public void popBackStack() {
//
//            }
//
//            @Override
//            public boolean popBackStackImmediate() {
//                return false;
//            }
//
//            @Override
//            public void popBackStack(@Nullable String name, int flags) {
//
//            }
//
//            @Override
//            public boolean popBackStackImmediate(@Nullable String name, int flags) {
//                return false;
//            }
//
//            @Override
//            public void popBackStack(int id, int flags) {
//
//            }
//
//            @Override
//            public boolean popBackStackImmediate(int id, int flags) {
//                return false;
//            }
//
//            @Override
//            public int getBackStackEntryCount() {
//                return 0;
//            }
//
//            @NonNull
//            @Override
//            public FragmentManager.BackStackEntry getBackStackEntryAt(int index) {
//                return null;
//            }
//
//            @Override
//            public void addOnBackStackChangedListener(@NonNull OnBackStackChangedListener listener) {
//
//            }
//
//            @Override
//            public void removeOnBackStackChangedListener(@NonNull OnBackStackChangedListener listener) {
//
//            }
//
//            @Override
//            public void putFragment(@NonNull Bundle bundle, @NonNull String key, @NonNull Fragment fragment) {
//
//            }
//
//            @Nullable
//            @Override
//            public Fragment getFragment(@NonNull Bundle bundle, @NonNull String key) {
//                return null;
//            }
//
//            @NonNull
//            @Override
//            public List<Fragment> getFragments() {
//                return null;
//            }
//
//            @Nullable
//            @Override
//            public Fragment.SavedState saveFragmentInstanceState(@NonNull Fragment f) {
//                return null;
//            }
//
//            @Override
//            public boolean isDestroyed() {
//                return false;
//            }
//
//            @Override
//            public void registerFragmentLifecycleCallbacks(@NonNull FragmentLifecycleCallbacks cb, boolean recursive) {
//
//            }
//
//            @Override
//            public void unregisterFragmentLifecycleCallbacks(@NonNull FragmentLifecycleCallbacks cb) {
//
//            }
//
//            @Nullable
//            @Override
//            public Fragment getPrimaryNavigationFragment() {
//                return null;
//            }
//
//            @Override
//            public void dump(@NonNull String prefix, @Nullable FileDescriptor fd, @NonNull PrintWriter writer, @Nullable String[] args) {
//
//            }
//
//            @Override
//            public boolean isStateSaved() {
//                return false;
//            }

}
