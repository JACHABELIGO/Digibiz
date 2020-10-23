package com.appr.digibiz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appr.digibiz.R;
import com.appr.digibiz.models.InventoryModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InventoryListAdapter extends RecyclerView.Adapter<InventoryListAdapter.InventoryViewHolder > {
    private List<InventoryModel> availableList;
    private Context context;

    public InventoryListAdapter(List<InventoryModel> availableList, Context context) {
        this.availableList = availableList;
        this.context = context;
    }

    @NonNull
    @Override
    public InventoryListAdapter.InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.available_list_item, parent, false);
        InventoryListAdapter.InventoryViewHolder viewHolder = new InventoryListAdapter.InventoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryListAdapter.InventoryViewHolder holder, int position) {
        holder.bindAvailableList(availableList.get(position));
    }

    @Override
    public int getItemCount() {
        return availableList.size();
    }

    public class InventoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.product_name) TextView mProductName;
        @BindView(R.id.price_per_item) TextView mPricePerItem;
        @BindView(R.id.delete_btn) ImageView mDeleteBtn;

        private String TAG = InventoryViewHolder.class.getSimpleName();
        private Context context;

        public InventoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
            mDeleteBtn.setOnClickListener(this);
        }

        public void bindAvailableList(InventoryModel inventoryModel) {
            mProductName.setText(inventoryModel.getProduct_name());
            String priceAndItems = String.format("%s pcs @ Ksh. %s", inventoryModel.quantity, inventoryModel.getPrice_per_item());
            mPricePerItem.setText(priceAndItems);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
