package com.appr.digibiz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appr.digibiz.R;
import com.appr.digibiz.fragments.InvoiceFragment;
import com.appr.digibiz.fragments.SMSDialogFragment;
import com.appr.digibiz.models.Active;
import com.appr.digibiz.utils.ActiveViewClickListener;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActiveListAdapter extends RecyclerView.Adapter<ActiveListAdapter.ActiveViewHolder> {

    List<Active> activeList;
    Context context;
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
        @BindView(R.id.sendMessage)
        ImageButton mSendMessage;

       // private String TAG = InventoryListAdapter.InventoryViewHolder.class.getSimpleName();
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
        }

        @Override
        public void onClick(View view) {
            if(view.getId() == mSendMessage.getId()) {
                Toast.makeText(view.getContext(), "ITEM PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }
            listenerWeakReference.get().onPositionClicked(getAdapterPosition());
        }
    }
}
