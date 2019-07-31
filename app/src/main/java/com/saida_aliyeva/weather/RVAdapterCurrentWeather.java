package com.saida_aliyeva.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RVAdapterCurrentWeather extends RecyclerView.Adapter<RVViewHolderCurrentWeather> {
    Context context;
    Example example;

    public RVAdapterCurrentWeather(Context context, Example example) {
        this.context = context;
        this.example = example;
    }

    @NonNull
    @Override
    public RVViewHolderCurrentWeather onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_items_current_weather,parent,false);
        return new RVViewHolderCurrentWeather(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolderCurrentWeather holder, int position) {
        holder.bind(example);

    }



    @Override
    public int getItemCount() {
        // bir obyekt qayidir
        return 1;
    }
}
