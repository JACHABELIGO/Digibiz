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

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeleteInvetoryDialogFragment extends DialogFragment {

    @BindView(R.id.delete_product_name)
    TextView mProductName;
    @BindView(R.id.delete_permanently_btn)
    MaterialButton mPermanent;
    @BindView(R.id.delete_transfer_btn)
    MaterialButton mTransfer;
    private InventoryModel resultInventory;

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
        return  view;
    }
}