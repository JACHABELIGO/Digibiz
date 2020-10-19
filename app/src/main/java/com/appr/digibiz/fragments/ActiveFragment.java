package com.appr.digibiz.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.appr.digibiz.R;
import com.appr.digibiz.fragments.InvoiceFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActiveFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActiveFragment extends Fragment {
    FloatingActionButton addActive;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_active, container, false);

        addActive = view.findViewById(R.id.fab);
        addActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getChildFragmentManager();
                InvoiceFragment invoiceFragment = new InvoiceFragment();
                invoiceFragment.show(fm, "Sample Fragment");

            }
        });


        // Inflate the layout for this fragment
        return view;



    }
}