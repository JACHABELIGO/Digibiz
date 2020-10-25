package com.appr.digibiz.adapter;

import android.app.AlertDialog;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appr.digibiz.R;
import com.appr.digibiz.fragments.InvoiceFragment;
import com.appr.digibiz.fragments.SMSDialogFragment;
import com.appr.digibiz.models.Active;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import com.appr.digibiz.utils.ActiveViewClickListener;


import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.FieldMap;

public class ActiveListAdapter extends RecyclerView.Adapter<ActiveListAdapter.ActiveViewHolder> {

    List<Active> activeList;
    Context context;

    Button updateButton;
    DatabaseReference invoice;
    EditText name;
    EditText date;
    EditText price;
    EditText transactionDetails;
    EditText quantity ;
    EditText phoneNumber;
    CheckBox reminder ;
    Active active ;
    Intent intent ;

    public Intent getIntent() {
       return Parcels.unwrap(getIntent().getExtras().getParcelable("push_id"));
    }





    private final ActiveViewClickListener listener;


    public ActiveListAdapter(List<Active> activeList, Context context, ActiveViewClickListener activeViewClickListener) {
        this.activeList = activeList;
        this.context = context;
        this.listener = activeViewClickListener;
    }

    @NonNull
    @Override
    public ActiveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.active_list_item, parent, false);
        ActiveListAdapter.ActiveViewHolder activeViewHolder = new ActiveListAdapter.ActiveViewHolder(view, listener);

        return activeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ActiveViewHolder holder, int position) {
        holder.bindActiveList(activeList.get(position));

    }

    @Override
    public int getItemCount() {
        return activeList.size();
    }

    public class ActiveViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.creditorName)
        TextView creditorName;
        @BindView(R.id.amount) TextView amount;
        @BindView(R.id.transactionDetails) TextView transactionDetails;
        @BindView(R.id.date) TextView date;

        @BindView(R.id.edit) ImageButton update;

        @BindView(R.id.sendMessage)
        ImageButton mSendMessage;


        private Context context;
        private WeakReference<ActiveViewClickListener> listenerWeakReference;


        public ActiveViewHolder(@NonNull View itemView, ActiveViewClickListener listener) {
            super(itemView);
            listenerWeakReference = new WeakReference<>(listener);
            ButterKnife.bind(this,itemView);
            context = itemView.getContext();
            mSendMessage.setOnClickListener(this);
        }

        public void bindActiveList(Active active) {
            creditorName.setText(String.valueOf(active.getName_of_creditor()));
            amount.setText(String.valueOf(active.getTotal_amount()));
            transactionDetails.setText(String.valueOf(active.getTransaction_details()));
            date.setText(String.valueOf(active.getDue_date()));



            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Active active = activeList.get(getLayoutPosition());
                    showUpdateDialog(active.getInvoice_id());
                }
            });
        }


        @Override
        public void onClick(View view) {
            if(view.getId() == mSendMessage.getId()) {
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                SMSDialogFragment smsDialogFragment = new SMSDialogFragment();
                smsDialogFragment.show(manager, "SMS Dialog Fragment");
            }
            listenerWeakReference.get().onPositionClicked(getAdapterPosition());
        }
    }
    private void showUpdateDialog(String invoice_id) {

        //  View view = LayoutInflater.from(getContext()).inflate(R.layout.active_list_item, parent, false);

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.update_dialog,null);
        dialog.setView(view);

        updateButton =(Button) view.findViewById(R.id.update);
        name= (EditText) view.findViewById(R.id.name);
        date= (EditText) view.findViewById(R.id.date);
        price=(EditText) view.findViewById(R.id.price);
        transactionDetails =(EditText) view.findViewById(R.id.transactionDetails);
        quantity =(EditText) view.findViewById(R.id.quantity);
        phoneNumber = (EditText)view.findViewById(R.id.phoneNumber);
        reminder =(CheckBox) view.findViewById(R.id.reminder);

        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
        String finalInvoice_id = invoice_id;
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                String name_of_creditor = name.getText().toString();
                int quantityOfItems=Integer.parseInt( quantity.getText().toString());
                int price_per_item= Integer.parseInt(price.getText().toString());
                String due_date = date.getText().toString();
                String mobile_number = phoneNumber.getText().toString();
                String transaction_details= transactionDetails.getText().toString();
                int total_amount = quantityOfItems*price_per_item;


                updateInvoice(name_of_creditor,quantityOfItems,price_per_item,due_date,mobile_number,transaction_details, finalInvoice_id,total_amount);
            }
        });

    }


    private Boolean updateInvoice(String name_of_creditor, int quantity, int price_per_item, String due_date, String mobile_number, String transaction_details, String invoice_id, int total_amount){
        invoice =  FirebaseDatabase.getInstance().getReference().child("Invoice").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("active").child(String.valueOf(getIntent()));
        Active active = new Active(name_of_creditor,quantity,price_per_item,due_date,mobile_number,transaction_details,invoice_id,total_amount);
        invoice.setValue(active);
        Toast.makeText(context,"Update Successful",Toast.LENGTH_LONG).show();
        return  true;



    }


}

