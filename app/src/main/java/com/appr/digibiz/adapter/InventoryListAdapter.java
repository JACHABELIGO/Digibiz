package com.appr.digibiz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appr.digibiz.R;
import com.appr.digibiz.models.InventoryModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InventoryListAdapter extends RecyclerView.Adapter<InventoryListAdapter.InventoryViewHolder > {
    //TODO change here
    private List<InventoryModel> availableList;
    private Context context;

    public InventoryListAdapter(List<InventoryModel> availableList, Context context) {
        //TODO change here
        this.availableList = availableList;
        this.context = context;
    }

    @NonNull
    @Override
    public InventoryListAdapter.InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //TODO change here
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.available_list_item, parent, false);
        InventoryListAdapter.InventoryViewHolder viewHolder = new InventoryListAdapter.InventoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryListAdapter.InventoryViewHolder holder, int position) {
        //TODO change here
        holder.bindAvailableList(availableList.get(position));
    }

    @Override
    public int getItemCount() {
        //TODO change here
        return availableList.size();
    }

    public class InventoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.product_name) TextView mProductName;
        @BindView(R.id.quantity) TextView mQuantity;
        @BindView(R.id.price_per_item) TextView mPricePerItem;

        private String TAG = InventoryViewHolder.class.getSimpleName();
        private Context context;

        public InventoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }

        public void bindAvailableList(InventoryModel inventoryModel) {
            mProductName.setText(inventoryModel.getProduct_name());
            mQuantity.setText(inventoryModel.getQuantity());
            mPricePerItem.setText(inventoryModel.getPrice_per_item());
        }

    }
}
