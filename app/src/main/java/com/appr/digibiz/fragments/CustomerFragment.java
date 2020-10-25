package com.appr.digibiz.fragments;

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
import com.appr.digibiz.adapter.CustomerListAdapter;
import com.appr.digibiz.models.Active;
import com.appr.digibiz.models.Customers;
import com.appr.digibiz.models.Users;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CustomerFragment extends Fragment {

    FloatingActionButton addCustomers;
    RecyclerView customersRecyclerView;
    ProgressBar progress;
    LinearLayout empty;
    List<Users> usersList = new ArrayList<>();
    DatabaseReference users;
    CustomerListAdapter customerListAdapter;


    public CustomerFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_customers, container, false);
        addCustomers = (FloatingActionButton) view.findViewById(R.id.fab);
        customersRecyclerView = view.findViewById(R.id.customers);
        progress = view.findViewById(R.id.progress);
        empty = view.findViewById(R.id.empty);

        addCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // Inflate the layout for this fragment
        return view;
    }

  /*  private  void  displayUsers(){
        users = FirebaseDatabase.getInstance().getReference();
        Query query = users.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                showProgressBar();
                hideRecyclerView();
                usersList.clear();

                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    try {
                        if (dataSnapshot.exists()){
                            Users users = new Users();
                            Map<String,Object> map = (HashMap<String, Object>) dataSnapshot.getValue();
                            users.setBusiness_name(map.get("business_name").toString());
                            usersList.add(users);
                        }

                    }catch (NullPointerException ex){

                    }
                }
                customerListAdapter = new CustomerListAdapter(usersList,getContext());
                customersRecyclerView.setAdapter(customerListAdapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                customersRecyclerView.setLayoutManager(layoutManager);

                hideProgressBar();
                if(usersList.isEmpty()) {
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



    private void hideProgressBar() {
        progress.setVisibility(View.GONE);
    }

    private void showProgressBar() {
        progress.setVisibility(View.VISIBLE);
    }
    private void hideRecyclerView() {
        customersRecyclerView.setVisibility(View.GONE);
    }

    private void showRecyclerView() {
        customersRecyclerView.setVisibility(View.VISIBLE);
    }
    private void hideEmptyView() {
        empty.setVisibility(View.GONE);
    }

    private void showEmpty() {
        empty.setVisibility(View.VISIBLE);
    }*/
}