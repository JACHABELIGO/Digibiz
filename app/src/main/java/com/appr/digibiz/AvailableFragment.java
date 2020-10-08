package com.appr.digibiz;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AvailableFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.homeProgressBar)
    ProgressBar mHomeProgressBar;
    @BindView(R.id.availableListRecyclerView)
    RecyclerView mAvailableListRecyclerView;
    @BindView(R.id.homeErrorText)
    TextView mErrorText;
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    public AvailableFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_available, container, false);
        ButterKnife.bind(this, view);

        //click listeners
        mFab.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == mFab) {
//            startActivity(new Intent(getActivity(),));
        }
    }
}