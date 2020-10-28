package com.appr.digibiz.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.appr.digibiz.R;
import com.appr.digibiz.models.InventoryModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeleteInvetoryDialogFragment extends DialogFragment implements View.OnClickListener{

    @BindView(R.id.delete_product_name)
    TextView mProductName;
    @BindView(R.id.delete_permanently_btn)
    MaterialButton mPermanent;
    @BindView(R.id.delete_transfer_btn)
    MaterialButton mTransfer;
    private InventoryModel inventoryToBeDeleted;

    public DeleteInvetoryDialogFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DeleteInvetoryDialogFragment newInstance(InventoryModel inventoryItem) {
        DeleteInvetoryDialogFragment fragment = new DeleteInvetoryDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable("toDeleteDialog", Parcels.wrap(inventoryItem));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_delete_invetory_dialog, container, false);
        ButterKnife.bind(this, view);
        //we unwrap the parsed object and set it to a global variable
        inventoryToBeDeleted = Parcels.unwrap(getArguments().getParcelable("toDeleteDialog"));
        mProductName.setText(inventoryToBeDeleted.getProduct_name());
        //click listeners
        mPermanent.setOnClickListener(this);
        mTransfer.setOnClickListener(this);
        return  view;
    }

    @Override
    public void onClick(View view) {
        if(view == mPermanent) {
            permanentlyDeleteInventory();
        }
        if(view == mTransfer) {
            transferToOutOfStock();
        }
    }

    private void transferToOutOfStock() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        String inventoryId = inventoryToBeDeleted.getInventory_id();
        reference.child(getString(R.string.db_node_inventory))
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(getString(R.string.db_node_out_of_stock))
                .child(inventoryId)
                .setValue(inventoryToBeDeleted);
        dismiss();
    }

    private void permanentlyDeleteInventory() {
        String inventory_id = inventoryToBeDeleted.getInventory_id();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("inventory")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("available")
                .child(inventory_id)
                .removeValue();
        dismiss();
    }
}