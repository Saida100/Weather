package com.saida_aliyeva.weather.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saida_aliyeva.weather.R;
import com.saida_aliyeva.weather.model.List2;
import com.saida_aliyeva.weather.model.POJO;
import com.saida_aliyeva.weather.model.SectionDataModel;

import java.util.ArrayList;
import java.util.List;


public class RVOutAdapter extends RecyclerView.Adapter<RVOutAdapter.RVOutViewHolder> {

    private POJO pojo;
    private Context context;
    private RecyclerView.RecycledViewPool recycledViewPool;
    private List<SectionDataModel> sectionDataModelList;


    public RVOutAdapter(List<SectionDataModel> sectionDataModelList, Context context) {
        this.sectionDataModelList = sectionDataModelList;
        this.context = context;
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @NonNull
    @Override
    public RVOutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_items2_vertical, parent, false);
        return new RVOutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVOutViewHolder holder, int position) {
        RVInnerAdapter rvInnerAdapter = new RVInnerAdapter(context, sectionDataModelList.get(position));
        holder.recyclerView.setAdapter(rvInnerAdapter);

        holder.bind(sectionDataModelList.get(position));


    }

    @Override
    public int getItemCount() {
        return sectionDataModelList.size();
    }


    public class RVOutViewHolder extends RecyclerView.ViewHolder {

        TextView dt_txtTextView;
        RecyclerView recyclerView;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);


        public RVOutViewHolder(@NonNull View itemView) {
            super(itemView);
            dt_txtTextView = itemView.findViewById(R.id.dt_txt);
            recyclerView = itemView.findViewById(R.id.recyclerView);
            recyclerView.setRecycledViewPool(recycledViewPool);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setLayoutManager(linearLayoutManager);


        }

        public void bind(SectionDataModel sectionDataModel) {
            Log.e("outAdapterSize", String.valueOf(sectionDataModelList.size()));
            Log.e("calledBind", "bind Cll");

            try {
                    String d = sectionDataModel.getDate();
                    dt_txtTextView.setText(d.substring(0, d.lastIndexOf(" ")));
                    Log.e("d=", d);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }

}
