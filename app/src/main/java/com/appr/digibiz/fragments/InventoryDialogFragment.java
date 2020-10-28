package com.appr.digibiz.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.appr.digibiz.R;
import com.appr.digibiz.models.InventoryModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InventoryDialogFragment extends DialogFragment implements View.OnClickListener {

    @BindView(R.id.buttonA)
    Button mSubmintButton;
    @BindView(R.id.product_name)
    TextInputLayout mProductName;
    @BindView(R.id.price)
    TextInputLayout mPrice;
    @BindView(R.id.quantity)
    TextInputLayout mQuantity;
    @BindView(R.id.cancel_action)
    ImageView mCancel;

    private View rootView;

    private static final String TAG = "InventoryDialogFragment";

    public InventoryDialogFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.fragment_inventory_dialog, container, false);
        ButterKnife.bind(this,rootView);

        //click listeners
        mSubmintButton.setOnClickListener(this);
        mCancel.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        if(view == mCancel) {
            dismiss();
        }
        if(view == mSubmintButton) {
            createInventory();
        }
    }

    private void createInventory() {
        String productName = mProductName.getEditText().getText().toString().trim();
        String price = mPrice.getEditText().getText().toString().trim();
        String quantity = mQuantity.getEditText().getText().toString().trim();

        boolean validProductName = isValidInput(mProductName, productName);
        boolean validPrice = isValidInput(mPrice, price);
        boolean validQuantity = isValidInput(mQuantity, quantity);
        if(!validProductName || !validPrice || !validQuantity) return;
        //we save the data after all requirements are met
        saveInventory(productName, price, quantity);
        dismiss();
    }

    private void saveInventory(String product_name, String price_per_item, String quantity) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        String inventoryId = reference.child(getString(R.string.db_node_inventory))
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(getString(R.string.db_node_available))
                .push().getKey();
        InventoryModel newInventory = new InventoryModel();
        newInventory.setProduct_name(product_name);
        newInventory.setPrice_per_item(price_per_item);
        newInventory.setQuantity(quantity);
        newInventory.setInventory_id(inventoryId);
        reference.child(getString(R.string.db_node_inventory))
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(getString(R.string.db_node_available))
                .child(inventoryId)
                .setValue(newInventory);
        //then we dismiss a dialog and show a snack bar for confirmation
        Snackbar.make(rootView, "Inventory added", Snackbar.LENGTH_LONG)
                .setBackgroundTint(getResources().getColor(R.color.colorSuccess))
                .setActionTextColor(getResources().getColor(R.color.colorSecondaryLight))
                .show();
    }


    //form validations
    private boolean isValidInput(TextInputLayout textInputLayout, String name) {
        if (name.equals("")) {
            textInputLayout.setError("Field can't be empty");
            return false;
        }
        return true;
    }
}
