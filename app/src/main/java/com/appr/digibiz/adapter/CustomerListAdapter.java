package com.appr.digibiz.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appr.digibiz.R;
import com.appr.digibiz.models.Customers;
import com.appr.digibiz.models.Users;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.CustomerViewHolder>{
    List<Users> usersList;
    Context context;

    public CustomerListAdapter(List<Users> usersList, Context context) {
        this.usersList = usersList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custimer_individual_item,parent,false);
        CustomerListAdapter.CustomerViewHolder customerViewHolder = new CustomerListAdapter.CustomerViewHolder(view);
        return customerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        holder.bindCustomers(usersList.get(position));

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {
        Context context;

        @BindView(R.id.businessName)
        TextView businessName;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            ButterKnife.bind(this,itemView);
        }
        public  void  bindCustomers(Users users){
            businessName.setText(users.getBusiness_name());

        }


    }

}
