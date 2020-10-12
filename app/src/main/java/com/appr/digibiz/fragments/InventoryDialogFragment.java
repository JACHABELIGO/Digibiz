package com.appr.digibiz.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
        getDialog().setTitle("Inventory Tracker");
        return rootView;
    }

//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("Dialog via Builder");
//        builder.setMessage("Would you like to add another Inventory?");
//
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dismiss();
//            }
//        });
//
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dismiss();
//            }
//        });
//        return builder.create();
//    }

}
