package com.appr.digibiz.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.DialogFragment;

import com.appr.digibiz.R;

public class InventoryDialogFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_inventory_dialog, container, false);

        Button submitButton = (Button) rootView.findViewById(R.id.buttonA);
        ImageView cancelIcon = (ImageView) rootView.findViewById(R.id.cancel_action);

        cancelIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }

        });

        final ViewGroup checkedViewGroup = (ViewGroup) rootView.findViewById(R.id.baseLayout);
        int selectedId = checkedViewGroup.getId();
        final ViewGroup checkedviewGroup = (ViewGroup) rootView.findViewById(selectedId);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Log.d("testing", checkedviewGroup.toString());
                dismiss();

            }
        });
        // we'll check this later if it is not submitting
//        getDialog().setTitle("Inventory Tracker");
        return rootView;
    }

}
