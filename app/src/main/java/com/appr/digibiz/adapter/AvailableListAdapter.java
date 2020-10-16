package com.appr.digibiz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appr.digibiz.R;
import com.appr.digibiz.models.AvailableModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AvailableListAdapter extends RecyclerView.Adapter<AvailableListAdapter.AvailableViewHolder> {
    private List<AvailableModel> availableList;
    private Context context;

    public AvailableListAdapter(List<AvailableModel> availableList, Context context) {
        this.availableList = availableList;
        this.context = context;
    }

    @NonNull
    @Override
    public AvailableListAdapter.AvailableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.available_list_item, parent, false);
        AvailableListAdapter.AvailableViewHolder viewHolder = new AvailableListAdapter.AvailableViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AvailableListAdapter.AvailableViewHolder holder, int position) {
        holder.bindAvailableList(availableList.get(position));
    }

    @Override
    public int getItemCount() {
        return availableList.size();
    }

    public class AvailableViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.product_name) TextView mProductName;
        @BindView(R.id.quantity) TextView mQuantity;
        @BindView(R.id.price_per_item) TextView mPricePerItem;

        private String TAG = AvailableViewHolder.class.getSimpleName();
        private Context context;
        private AvailableModel available;

        public AvailableViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
//            itemView.setOnClickListener(this);
        }

        public void bindAvailableList(AvailableModel availableModel) {
            mProductName.setText(available.getProductName());
        }

//        @Override
//        public void onClick(View view) {
//            if (view == mAvailableCard) {
//                int itemPosition = getLayoutPosition();
//                Intent intent = new Intent(context, SessionDetailsActivity.class);
//                intent.putExtra("position", itemPosition);
//                intent.putExtra("sessions", Parcels.wrap(availableList));
//                Log.d(TAG, "card view clicked");
//                context.startActivity(intent);
//            }
//        }
    }
}
