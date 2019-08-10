package com.saida_aliyeva.weather.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saida_aliyeva.weather.api.APIInit;
import com.saida_aliyeva.weather.api.APIInterface;
import com.saida_aliyeva.weather.R;
import com.saida_aliyeva.weather.adapter.RVAdapter;
import com.saida_aliyeva.weather.api.Constants;
import com.saida_aliyeva.weather.model.POJO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class DailyWeatherFragment extends Fragment {

    RVAdapter adapter;
    String cityName;
    TextView cityNameTextView;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cityNameTextView = view.findViewById(R.id.cityName);
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        callRequest();

    }

    private void callRequest() {
        APIInit apiInit = new APIInit();
        apiInit.initRetrofit();
        APIInterface apiInterface = apiInit.getClient();
        apiInterface.getTempByLatAndLng("40.4093", "49.8671", Constants.APP_ID)
                .enqueue(new Callback<POJO>() {
                    @Override
                    public void onResponse(Call<POJO> call, Response<POJO> response) {
                        POJO pojo = new POJO();
                        if (response.body().getCod().equals("200")) {
                            pojo = response.body();
                            cityName = pojo.getCity().getName();
                            cityNameTextView.append(cityName+"\n");
                            cityNameTextView.append("wind speed : meter/sec "+"\n");
                            cityNameTextView.append("temperature : Kelvin "+"\n");
                            cityNameTextView.append(" Rain volume : mm ");
                            Log.e("pojo", pojo.toString());
                            Log.e("cod", response.body().getCod());
                        }
                        adapter = new RVAdapter(pojo, getContext());
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<POJO> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
