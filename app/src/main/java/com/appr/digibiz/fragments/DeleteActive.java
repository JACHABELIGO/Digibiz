package com.appr.digibiz.fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.appr.digibiz.R;
import com.appr.digibiz.models.Active;
import com.appr.digibiz.models.Resolved;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeleteActive extends DialogFragment implements View.OnClickListener {

    @BindView(R.id.delete_product_name)
    TextView mProductName;
    @BindView(R.id.delete_permanently_btn)
    MaterialButton mPermanent;
    @BindView(R.id.delete_transfer_btn)
    MaterialButton mTransfer;
    private  Active activeToDelete;


    public DeleteActive() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DeleteActive newInstance(Active active) {
        DeleteActive fragment = new DeleteActive();
        Bundle args = new Bundle();
        args.putParcelable("toDeleteDialog", Parcels.wrap(active));
        fragment.setArguments(args);
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
        View view = inflater.inflate(R.layout.fragment_delete_active, container, false);
        ButterKnife.bind( this,view);

       activeToDelete = Parcels.unwrap(getArguments().getParcelable("toDeleteDialog"));
       mProductName.setText(activeToDelete.getName_of_creditor());

       //click listeners
        mPermanent.setOnClickListener(this);
        mTransfer.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == mPermanent) {
            deletePermanently();
            dismiss();
        }
        if(view == mTransfer) {
            transfer();
            deletePermanently();
            dismiss();
        }
    }

    private void transfer() {
        DatabaseReference reference =FirebaseDatabase.getInstance().getReference();
        String invoice_id = activeToDelete.getInvoice_id();
        reference.child("Invoice")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("resolved")
                .child(invoice_id)
                .setValue(activeToDelete);
    }

    private void deletePermanently() {
        String invoice_id = activeToDelete.getInvoice_id();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Invoice")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("active")
                .child(invoice_id)
                .removeValue();
    }
}