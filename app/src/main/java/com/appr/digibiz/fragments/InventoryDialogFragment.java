package com.appr.digibiz.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.appr.digibiz.R;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InventoryDialogFragment extends DialogFragment implements View.OnClickListener {

    @BindView(R.id.buttonA)
    Button mSubmintButton;
    @BindView(R.id.product_name)
    TextInputLayout mProductName;
    @BindView(R.id.price)
    Button mPrice;
    @BindView(R.id.quantity)
    Button mQuantity;
    @BindView(R.id.cancel_action)
    Button mCancel;

    private static final String TAG = "InventoryDialogFragment";

    public InventoryDialogFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_inventory_dialog, container, false);
        ButterKnife.bind(this,rootView);

        final ViewGroup checkedViewGroup = (ViewGroup) rootView.findViewById(R.id.baseLayout);
        int selectedId = checkedViewGroup.getId();
        final ViewGroup checkedviewGroup = (ViewGroup) rootView.findViewById(selectedId);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        if(view == mCancel) {
            dismiss();
        }
    }
}
