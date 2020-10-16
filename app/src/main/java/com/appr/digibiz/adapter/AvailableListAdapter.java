package com.appr.digibiz.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AvailableListAdapter extends RecyclerView.Adapter<AvailableListAdapter.AvailableViewHolder> {
    @NonNull
    @Override
    public AvailableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AvailableViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class AvailableViewHolder extends RecyclerView.ViewHolder {
    }
}
