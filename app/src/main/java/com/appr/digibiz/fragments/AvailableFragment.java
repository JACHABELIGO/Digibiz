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

}
