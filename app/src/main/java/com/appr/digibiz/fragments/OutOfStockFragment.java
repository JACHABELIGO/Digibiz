package com.appr.digibiz.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appr.digibiz.R;
import com.appr.digibiz.adapter.InventoryListAdapter;
import com.appr.digibiz.models.InventoryModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private void getOutOfStockItems() {
        reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child(getString(R.string.db_node_inventory))
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(getString(R.string.db_node_out_of_stock)).orderByKey();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                showProgressBar();
                hideRecyclerView();
                mOutOfStockList.clear();
                for (DataSnapshot singleSnapShot : snapshot.getChildren()){
                    try {
                        if(singleSnapShot.exists()) {
                            InventoryModel inventoryItem = new InventoryModel();
                            Map<String, Object> objectMap = (HashMap<String, Object>) singleSnapShot.getValue();
                            inventoryItem.setProduct_name(objectMap.get(getString(R.string.field_product_name)).toString());
                            inventoryItem.setPrice_per_item(objectMap.get(getString(R.string.field_price_per_item)).toString());
                            inventoryItem.setQuantity(objectMap.get(getString(R.string.field_quantity)).toString());
                            inventoryItem.setInventory_id(objectMap.get(getString(R.string.field_inventory_id)).toString());
                            //add the individual items on the list
                            mOutOfStockList.add(inventoryItem);
                        }

                    } catch (NullPointerException e) {
                        Log.d(TAG, "onDataChange : NullPointerException" + e.getMessage());
                    }
                }
                setupOutOfStockList();
                hideProgressBar();
                if(mOutOfStockList.isEmpty()) {
                    hideRecyclerView();
                    showEmpty();
                } else {
                    showRecyclerView();
                    hideEmptyView();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setupOutOfStockList() {
        mAdapter = new InventoryListAdapter(mOutOfStockList, getContext());
        mOutOfStockListRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mOutOfStockListRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onClick(View view) {
        if (view == mFab) {
//            FragmentManager fm = getChildFragmentManager();
//            InventoryDialogFragment inventoryDialogFragment = new InventoryDialogFragment();
//            inventoryDialogFragment.show(fm, "Out of stock inventory");
        }
    }

    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }
    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}