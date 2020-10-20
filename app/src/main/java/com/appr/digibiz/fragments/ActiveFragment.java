package com.appr.digibiz.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.appr.digibiz.R;
import com.appr.digibiz.fragments.InvoiceFragment;
import com.appr.digibiz.models.Active;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ActiveFragment extends Fragment {
    RecyclerView activeRecyclerView;
    ImageView clipboard;
    TextView textClipboard;
    ProgressBar progress;
    FloatingActionButton addActive;
    FirebaseRecyclerAdapter<Active, FirebaseActiveViewHolder> activeViewHolder;
    DatabaseReference invoice;
    FirebaseRecyclerAdapter firebaseRecyclerAdapter;
    LinearLayout empty;
    DataSnapshot data;


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

       displayInvoice();

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

    //Method to display invoice
    public void displayInvoice(){
        //invoice node
        invoice = FirebaseDatabase.getInstance().getReference("Invoice");

        FirebaseRecyclerOptions<Active> options = new FirebaseRecyclerOptions.Builder<Active>().setQuery(invoice,Active.class).build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Active,FirebaseActiveViewHolder>(options) {
            @NonNull
            @Override
            public FirebaseActiveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.active_list_item, parent, false);
                return new FirebaseActiveViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull FirebaseActiveViewHolder holder, int position, @NonNull Active active) {
                clipboard.setVisibility(View.INVISIBLE);
                textClipboard.setVisibility(View.INVISIBLE);
                progress.setVisibility(View.INVISIBLE);

                Query query = invoice.child(FirebaseAuth.getInstance().getUid()).child("active").orderByKey();
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot oneInvoice :snapshot.getChildren()){
                            System.out.println(oneInvoice.child("name_of_creditor").getValue().toString());
                            String name_of_creditor = oneInvoice.child("name_of_creditor").getValue().toString();
                            int total_amount = (int) oneInvoice.child("total_amount").getValue();
                            String due_date = oneInvoice.child("due_date").getValue().toString();
                            String transaction_details = oneInvoice.child("date").getValue().toString();

                            holder.creditorName.setText(name_of_creditor);
                            holder.amount.setText(total_amount);
                            holder.date.setText(due_date);
                            holder.transactionDetails.setText(transaction_details);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }


        };
        activeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        activeRecyclerView.setHasFixedSize(true);
        activeRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }



    //Active RecyclerViewHolder
    private static class FirebaseActiveViewHolder extends RecyclerView.ViewHolder {
        View mView;
        Context mContext;

        TextView creditorName;
        TextView amount;
        TextView transactionDetails;
        TextView date;


        public FirebaseActiveViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            mContext = itemView.getContext();
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        if (activeViewHolder != null) {
            activeViewHolder.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (activeViewHolder != null) {
            activeViewHolder.stopListening();
        }
    }
}