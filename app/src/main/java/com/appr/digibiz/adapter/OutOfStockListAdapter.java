package com.appr.digibiz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appr.digibiz.R;
import com.appr.digibiz.models.OutOfStockModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OutOfStockListAdapter extends RecyclerView.Adapter<OutOfStockListAdapter.OutOfStockViewHolder> {
    private List<OutOfStockModel> outOfStockList;
    private Context context;

    public OutOfStockListAdapter(List<OutOfStockModel> outOfStockList, Context context) {
        this.outOfStockList = outOfStockList;
        this.context = context;
    }

    @NonNull
    @Override
    public OutOfStockListAdapter.OutOfStockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.outofstock_list_item, parent, false);
        OutOfStockListAdapter.OutOfStockViewHolder viewHolder = new OutOfStockListAdapter.OutOfStockViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OutOfStockListAdapter.OutOfStockViewHolder holder, int position) {
        holder.bindOutOfStockList(outOfStockList.get(position));
    }

    @Override
    public int getItemCount() {
        return outOfStockList.size();
    }

    public class OutOfStockViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.product_name) TextView mProductName;
        @BindView(R.id.quantity) TextView mQuantity;
        @BindView(R.id.price_per_item) TextView mPricePerItem;

        private String TAG = OutOfStockListAdapter.OutOfStockViewHolder.class.getSimpleName();
        private Context context;
        private OutOfStockModel outOfStock;

        public OutOfStockViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
//            itemView.setOnClickListener(this);
        }

        public void bindOutOfStockList(OutOfStockModel outOfStockModel) {
            mProductName.setText(outOfStock.getProductName());
            mQuantity.setText(outOfStock.getQuantity());
            mPricePerItem.setText(outOfStock.getPricePerItem());
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
