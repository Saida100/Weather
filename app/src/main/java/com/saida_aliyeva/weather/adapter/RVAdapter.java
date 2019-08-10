package com.saida_aliyeva.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saida_aliyeva.weather.R;
import com.saida_aliyeva.weather.adapter.viewholder.RVViewHolder;
import com.saida_aliyeva.weather.model.POJO;


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
        holder.bind(pojo.getList().get(position));

    }

    @Override
    public int getItemCount() {
        return pojo.getList().size();
    }
}
