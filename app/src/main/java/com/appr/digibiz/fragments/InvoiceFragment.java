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


public class InvoiceFragment extends DialogFragment {

    ImageButton close;
    EditText name;
    EditText date;
    EditText price;
    EditText transactionDetails;
    EditText quantity;
    EditText phoneNumber;
    CheckBox reminder;
    Button submit;

    DatabaseReference invoice;
    FirebaseDatabase firebaseDatabase;



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
        date = (EditText) view.findViewById(R.id.date) ;
        reminder =(CheckBox) view.findViewById(R.id.reminder);
        submit = (Button) view.findViewById(R.id.submit);

        firebaseDatabase = FirebaseDatabase.getInstance();
        invoice = firebaseDatabase.getReference("Invoice");

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveInvoiceToDatabase();
            }
        });



        // Inflate the layout for this fragment
        return view;
    }

    private void saveInvoiceToDatabase() {
        //Get values from user input

        String uid = FirebaseAuth.getInstance().getUid();
        String name_of_creditor = name.getText().toString();
        int quantityOfItems =Integer.parseInt(quantity.getText().toString());
        int price_per_item=Integer.parseInt(price.getText().toString());
        String due_date = date.getText().toString();
        String mobile_number= phoneNumber.getText().toString();
        String transaction_details = transactionDetails.getText().toString();
        int total_amount = quantityOfItems*price_per_item;
        String invoice_id=invoice.push().getKey();

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
            Active active = new Active(name_of_creditor,quantityOfItems,price_per_item,due_date,mobile_number,transaction_details,invoice_id,total_amount);
            invoice.child(uid).child("active").push().setValue(active);
            dismiss();
        }


    }
}