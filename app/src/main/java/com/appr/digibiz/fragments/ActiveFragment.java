package com.appr.digibiz.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.appr.digibiz.R;
import com.appr.digibiz.adapter.ActiveListAdapter;
import com.appr.digibiz.adapter.InventoryListAdapter;
import com.appr.digibiz.fragments.InvoiceFragment;
import com.appr.digibiz.models.Active;
import com.appr.digibiz.models.InventoryModel;
import com.appr.digibiz.ui.MainActivity;
import com.appr.digibiz.utils.ActiveViewClickListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcel;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ActiveFragment extends Fragment {
    RecyclerView activeRecyclerView;
    ImageView clipboard;
    TextView textClipboard;
    ProgressBar progress;
    FloatingActionButton addActive;
    DatabaseReference invoice;
    LinearLayout empty;

    private List<Active> activeList = new ArrayList<>();
    private ActiveListAdapter activeListAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_active, container, false);

        activeRecyclerView = view.findViewById(R.id.activeRecyclerView);
        clipboard = view.findViewById(R.id.clipboard);
        textClipboard = view.findViewById(R.id.textClipboard);
        progress = view.findViewById(R.id.progress);
        addActive = view.findViewById(R.id.fab);
        empty = view.findViewById(R.id.empty);


        displayInvoiceDetails();

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

    private void displayInvoiceDetails() {
        invoice = FirebaseDatabase.getInstance().getReference();

        Query query = invoice.child("Invoice").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("active").orderByKey();
        String push_id = query.getRef().getKey();
        Intent intent = new Intent(getContext(),ActiveListAdapter.class);
        Bundle bundle = new Bundle();
        bundle.putString("push_id", String.valueOf(Parcels.wrap(push_id)));
        intent.putExtras(bundle);

        query.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                showProgressBar();
                hideRecyclerView();
                activeList.clear();
                for (DataSnapshot singleSnapShot : snapshot.getChildren()) {
                    try {
                        if (singleSnapShot.exists()) {
                            Active active = new Active();
                            Map<String, Object> objectMap = (HashMap<String, Object>) singleSnapShot.getValue();
                            active.setName_of_creditor(objectMap.get(getString(R.string.field_name_of_creditor)).toString());
                            active.setDue_date(objectMap.get(getString(R.string.field_due_date)).toString());
                            active.setTotal_amount(Math.toIntExact((Long) objectMap.get(getString(R.string.field_total_amount))));
                            active.setTransaction_details(objectMap.get(getString(R.string.field_transaction_details)).toString());

                            activeList.add(active);
                        }
                    } catch (NullPointerException ex) {

                    }
                }
                    activeListAdapter = new ActiveListAdapter(activeList, getActivity(), new ActiveViewClickListener() {
                        @Override
                        public void onPositionClicked(int position) {

                        }

                        @Override
                        public void onLongClicked(int position) {

                        }
                    });
                    activeRecyclerView.setAdapter(activeListAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    activeRecyclerView.setLayoutManager(layoutManager);

                    hideProgressBar();
                    if(activeList.isEmpty()) {
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
                activeRecyclerView.setVisibility(View.GONE);
            }

            private void showRecyclerView() {
                activeRecyclerView.setVisibility(View.VISIBLE);
            }
            private void hideEmptyView() {
                empty.setVisibility(View.GONE);
            }

            private void showEmpty() {
                empty.setVisibility(View.VISIBLE);
            }





    }



