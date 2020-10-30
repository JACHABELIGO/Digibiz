package com.appr.digibiz.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appr.digibiz.R;
import com.appr.digibiz.adapter.InventoryListAdapter;
import com.appr.digibiz.models.InventoryModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OutOfStockFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.outOfStockListRecyclerView)
    RecyclerView mOutOfStockListRecyclerView;
    @BindView(R.id.fabOutOfStock)
    FloatingActionButton mFab;
    @BindView(R.id.OutOfStockProgressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.emptyOutOfStockList)
    LinearLayout mEmptyView;

    private List<InventoryModel> mOutOfStockList;
    private InventoryListAdapter mAdapter;
    private DatabaseReference reference;
    private static final String TAG = "OutOfStockFragment";

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

        //initialize list
        mOutOfStockList = new ArrayList<>();
        getOutOfStockItems();

        //click listeners
        mFab.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == mFab) {
//            FragmentManager fm = getChildFragmentManager();
//            InventoryDialogFragment inventoryDialogFragment = new InventoryDialogFragment();
//            inventoryDialogFragment.show(fm, "Out of stock inventory");
        }
    }
}