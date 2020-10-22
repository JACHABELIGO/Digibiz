package com.appr.digibiz.fragments;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appr.digibiz.R;

public class SMSDialogFragment extends DialogFragment {


    public SMSDialogFragment() {
        // Required empty public constructor
    }

    public static SMSDialogFragment newInstance(String param1, String param2) {
        SMSDialogFragment fragment = new SMSDialogFragment();

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
        return inflater.inflate(R.layout.fragment_sms_dialog, container, false);
    }
}