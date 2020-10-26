package com.appr.digibiz.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appr.digibiz.R;

public class DeleteInvetoryDialogFragment extends Fragment {

    public DeleteInvetoryDialogFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DeleteInvetoryDialogFragment newInstance(String param1, String param2) {
        DeleteInvetoryDialogFragment fragment = new DeleteInvetoryDialogFragment();

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
        return inflater.inflate(R.layout.fragment_delete_invetory_dialog, container, false);
    }
}