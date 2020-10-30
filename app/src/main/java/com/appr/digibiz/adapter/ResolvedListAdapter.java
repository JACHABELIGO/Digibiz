package com.appr.digibiz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appr.digibiz.R;
import com.appr.digibiz.models.Resolved;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResolvedListAdapter extends RecyclerView.Adapter<ResolvedListAdapter.ResolvedViewHolder> {
    List<Resolved> resolvedList;
    Context context;

    public ResolvedListAdapter(List<Resolved> resolvedList, Context context) {
        this.resolvedList = resolvedList;
        this.context = context;
    }

    @NonNull
    @Override
    public ResolvedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resolved_list_item,parent,false);
        ResolvedListAdapter.ResolvedViewHolder resolvedViewHolder = new ResolvedListAdapter.ResolvedViewHolder(view);
        return resolvedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ResolvedViewHolder holder, int position) {
        holder.bindResolved(resolvedList.get(position));
    }

    @Override
    public int getItemCount() {
        return resolvedList.size();
    }

    public class ResolvedViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.creditorName)
        TextView creditorName;
        @BindView(R.id.amount) TextView amount;
        @BindView(R.id.transactionDetails) TextView transactionDetails;
        @BindView(R.id.date) TextView date;
        @BindView(R.id.edit)
        ImageButton update;
        @BindView(R.id.delete) ImageButton delete;
        Context context;

        public ResolvedViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            context = itemView.getContext();
        }
        public void bindResolved(Resolved resolved){
            creditorName.setText(String.valueOf(resolved.getName_of_creditor()));
            amount.setText(String.valueOf(resolved.getTotal_amount()));
            transactionDetails.setText(String.valueOf(resolved.getTransaction_details()));
            date.setText(String.valueOf(resolved.getDue_date()));

        }

    }
}
