package com.saida_aliyeva.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saida_aliyeva.weather.R;
import com.saida_aliyeva.weather.adapter.viewholder.RVInnerViewHolder;
import com.saida_aliyeva.weather.model.List2;
import com.saida_aliyeva.weather.model.SectionDataModel;

import java.util.List;

public class RVInnerAdapter extends RecyclerView.Adapter<RVInnerViewHolder> {

    Context context;
    SectionDataModel sectionDataModel;


    public RVInnerAdapter(Context context,SectionDataModel sectionDataModel) {
        this.sectionDataModel = sectionDataModel;
        this.context = context;
    }

    @NonNull
    @Override
    public RVInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_items_horizontal,parent,false);
        return new RVInnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVInnerViewHolder holder, int position) {
        holder.bind(sectionDataModel.getList2List().get(position),context);
    }

    @Override
    public int getItemCount() {
        return sectionDataModel.getList2List().size();
    }
}
