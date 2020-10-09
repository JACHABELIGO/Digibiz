package com.appr.digibiz.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.appr.digibiz.R;

public class InventoryDialogFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_inventory_dialog, container, false);

        Button submitButton = (Button) rootView.findViewById(R.id.buttonA);



        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {

            }
        });
        getDialog().setTitle("Simple Dialog");
        return rootView;
    }

}
