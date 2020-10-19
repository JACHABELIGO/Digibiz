package com.appr.digibiz.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appr.digibiz.R;
import com.appr.digibiz.models.InventoryModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AvailableFragment extends Fragment implements View.OnClickListener{
        @BindView(R.id.availableListRecyclerView)
        RecyclerView mAvailableListRecyclerView;
        @BindView(R.id.fab)
        FloatingActionButton mFab;
        @BindView(R.id.availableProgressBar)
        ProgressBar mProgressBar;
        @BindView(R.id.availableListRecyclerView)
        RecyclerView mAvailableRecyclerView;
        @BindView(R.id.emptyAvailableList)
        LinearLayout mEmptyView;

        private List<InventoryModel> mAvailableList;



        public AvailableFragment() {
//                 Required empty public constructor
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
//                 Inflate the layout for this fragment
                View view = inflater.inflate(R.layout.fragment_available, container, false);
                ButterKnife.bind(this, view);

//                click listeners
                mFab.setOnClickListener(this);
                return view;
        }

        @Override
        public void onClick(View view) {
                if (view == mFab) {
                        FragmentManager fm = getChildFragmentManager();
                        InventoryDialogFragment inventoryDialogFragment = new InventoryDialogFragment();
                        inventoryDialogFragment.show(fm, "Available inventory");
                }
        }

        private void hideProgressBar() {
                mProgressBar.setVisibility(View.GONE);
        }

        private void showProgressBar() {
                mProgressBar.setVisibility(View.VISIBLE);
        }
        private void hideRecyclerView() {
                mAvailableRecyclerView.setVisibility(View.GONE);
        }

        private void showRecyclerView() {
                mAvailableRecyclerView.setVisibility(View.VISIBLE);
        }
        private void hideEmptyView() {
                mAvailableRecyclerView.setVisibility(View.GONE);
        }

        private void showEmpty() {
                mAvailableRecyclerView.setVisibility(View.VISIBLE);
        }

}
