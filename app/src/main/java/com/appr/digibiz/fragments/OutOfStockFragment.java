package com.appr.digibiz.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.appr.digibiz.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OutOfStockFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.outOfStockListRecyclerView)
    RecyclerView outOfStockListRecyclerView;
    @BindView(R.id.fab)
    FloatingActionButton mFab;


    public OutOfStockFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_out_of_stock, container, false);
        ButterKnife.bind(this, view);

        //click listeners
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