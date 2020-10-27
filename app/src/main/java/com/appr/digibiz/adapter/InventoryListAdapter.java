package com.appr.digibiz.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.appr.digibiz.R;
import com.appr.digibiz.fragments.DeleteInvetoryDialogFragment;
import com.appr.digibiz.fragments.InventoryDialogFragment;
import com.appr.digibiz.models.InventoryModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InventoryListAdapter extends RecyclerView.Adapter<InventoryListAdapter.InventoryViewHolder > {
    private List<InventoryModel> availableList;
    private Context context;
    private View view;
    private static final String TAG = "InventoryListAdapter";

    public InventoryListAdapter(List<InventoryModel> availableList, Context context) {
        this.availableList = availableList;
        this.context = context;
    }

    @NonNull
    @Override
    public InventoryListAdapter.InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.available_list_item, parent, false);
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
            if (view == mDeleteBtn){
                createDeleteInventoryDialog(getAdapterPosition());
            }
        }
    }

    private void createDeleteInventoryDialog(int position) {
        //not entirely sure how this works
        InventoryModel inventoryToRemove =  availableList.get(position);
        DeleteInvetoryDialogFragment dialogFragment = DeleteInvetoryDialogFragment.newInstance(inventoryToRemove);
        FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
        dialogFragment.show(fragmentManager, "Delete Inventory Dialog");
//        String inventory_id = inventoryToRemove.getInventory_id();
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
//        reference.child("inventory")
//                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                .child("available")
//                .child(inventory_id)
//                .removeValue();
//
//        Snackbar.make(view, "Inventory deleted", Snackbar.LENGTH_LONG)
//                .setBackgroundTint(context.getResources().getColor(R.color.errorDarkRed))
//                .setActionTextColor(context.getResources().getColor(R.color.colorSecondaryLight))
//                .show();
//        notifyItemRemoved(position);
//        notifyItemRangeChanged(position, availableList.size());
    }
}
