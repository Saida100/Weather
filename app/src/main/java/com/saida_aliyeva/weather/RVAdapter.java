package com.saida_aliyeva.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class RVAdapter extends RecyclerView.Adapter<RVViewHolder> {

    POJO pojo;
    Context context;


    public RVAdapter(POJO pojo, Context context) {
        this.pojo = pojo;
        this.context = context;
    }

    @NonNull
    @Override
    public RVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_items, parent, false);
        return new RVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolder holder, int position) {
        holder.bind(pojo.getList().get(position), pojo.getCity());

    }

    @Override
    public int getItemCount() {
        return pojo.getList().size();
    }
}
