package com.appr.digibiz.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.appr.digibiz.R;
import com.appr.digibiz.adapter.ActiveListAdapter;
import com.appr.digibiz.adapter.ResolvedListAdapter;
import com.appr.digibiz.models.Active;
import com.appr.digibiz.models.Resolved;
import com.appr.digibiz.utils.ActiveViewClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResolvedFragment extends Fragment {
    LinearLayout empty;
    ProgressBar progress;
    RecyclerView recyclerView;
    DatabaseReference invoice;

    List<Active> resolvedList;
    ResolvedListAdapter resolvedListAdapter;



    public ResolvedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_resolved, container, false);
        resolvedList = new ArrayList<>();
        empty =(LinearLayout) view.findViewById(R.id.empty);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        recyclerView = (RecyclerView) view.findViewById(R.id.resolvedRecyclerView);
        displayResolvedDetails();
        return view;
    }

    private void displayResolvedDetails() {
        invoice = FirebaseDatabase.getInstance().getReference();

        Query query = invoice.child("Invoice").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("resolved").orderByKey();
        query.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                showProgressBar();
                hideRecyclerView();
                resolvedList.clear();
                for (DataSnapshot singleSnapShot : snapshot.getChildren()) {
                    try {
                        if (singleSnapShot.exists()) {
                            Active resolved = new Active();
                            Map<String, Object> objectMap = (HashMap<String, Object>) singleSnapShot.getValue();
                            resolved.setName_of_creditor(objectMap.get(getString(R.string.field_name_of_creditor)).toString());
                            resolved.setDue_date(objectMap.get(getString(R.string.field_due_date)).toString());
                            resolved.setTotal_amount(Math.toIntExact((Long) objectMap.get(getString(R.string.field_total_amount))));
                            resolved.setTransaction_details(objectMap.get(getString(R.string.field_transaction_details)).toString());

                            resolvedList.add(resolved);
                        }
                    } catch (NullPointerException ex) {

                    }
                }
                setupResolvedListAdapter();
                hideProgressBar();
                if (resolvedList.isEmpty()) {
                    hideRecyclerView();
                    showEmpty();
                } else {
                    showRecyclerView();
                    hideEmptyView();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setupResolvedListAdapter() {
        resolvedListAdapter = new ResolvedListAdapter(resolvedList, getContext());
        recyclerView.setAdapter(resolvedListAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }

    private void hideProgressBar() {
        progress.setVisibility(View.GONE);
    }

    private void showProgressBar() {
        progress.setVisibility(View.VISIBLE);
    }
    private void hideRecyclerView() {
        recyclerView.setVisibility(View.GONE);
    }

    private void showRecyclerView() {
        recyclerView.setVisibility(View.VISIBLE);
    }
    private void hideEmptyView() {
        empty.setVisibility(View.GONE);
    }

    private void showEmpty() {
        empty.setVisibility(View.VISIBLE);
    }


}