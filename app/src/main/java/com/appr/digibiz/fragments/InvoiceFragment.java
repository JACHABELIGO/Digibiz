package com.appr.digibiz.fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.appr.digibiz.R;
import com.appr.digibiz.models.Active;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.internal.Utils;


public class InvoiceFragment extends DialogFragment implements  View.OnClickListener {

    ImageButton close;
    EditText name;
    EditText date;
    EditText price;
    EditText transactionDetails;
    EditText quantity;
    EditText phoneNumber;
    CheckBox reminder;
    Button submit;

    public InvoiceFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invoice, container, false);

        close = (ImageButton) view.findViewById(R.id.close);
        name = (EditText) view.findViewById(R.id.name);
        quantity = (EditText) view.findViewById(R.id.quantity);
        price = (EditText) view.findViewById(R.id.price);
        transactionDetails= (EditText) view.findViewById(R.id.transactionDetails);
        phoneNumber = (EditText) view.findViewById(R.id.phoneNumber);
        date = (EditText) view.findViewById(R.id.date);
        reminder =(CheckBox) view.findViewById(R.id.reminder);
        submit = (Button) view.findViewById(R.id.submit);

        //click listeners
        close.setOnClickListener(this);
        submit.setOnClickListener(this);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == close) {
            dismiss();
        }
        if (view == submit) {
            saveInvoiceToDatabase();
        }
    }

    private void saveInvoiceToDatabase() {
        //Get values from user input
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String name_of_creditor = name.getText().toString();
        int quantityOfItems = Integer.parseInt(quantity.getText().toString());
        int price_per_item = Integer.parseInt(price.getText().toString());
        String due_date = date.getText().toString();
        String mobile_number = phoneNumber.getText().toString();
        String transaction_details = transactionDetails.getText().toString();
        int total_amount = quantityOfItems*price_per_item;
        String invoice_id = reference.child("Invoice").child(uid).child("active").push().getKey();

        if (TextUtils.isEmpty(name_of_creditor)){
            name.setError("Enter name");
            return;
        }else if (TextUtils.isEmpty(due_date)){
            quantity.setError("Please fill");
            return;
        }
        else if (TextUtils.isEmpty(mobile_number)){
            phoneNumber.setError("Enter Phone Number");
            return;
        }
        else if(TextUtils.isEmpty(transaction_details)){
            transactionDetails.setError("If none please write None");
            return;
        }
        else {
            Active active = new Active();
            active.setName_of_creditor(name_of_creditor);
            active.setTransaction_details(transaction_details);
            active.setTotal_amount(total_amount);
            active.setDue_date(due_date);
            active.setInvoice_id(invoice_id);
            active.setMobile_number(mobile_number);
            active.setQuantity(quantityOfItems);
            active.setPrice_per_item(price_per_item);
            reference.child("Invoice").child(uid).child("active").child(invoice_id).setValue(active);
            dismiss();
        }


    }
}