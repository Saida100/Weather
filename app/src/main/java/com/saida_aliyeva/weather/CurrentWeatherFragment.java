package com.saida_aliyeva.weather;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class CurrentWeatherFragment extends Fragment {
    RVAdapterCurrentWeather adapterCurrentWeather;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        APIInit apiInit = new APIInit();
        apiInit.initRetrofit();
        APIInterface apiInterface = apiInit.getClient();
        apiInterface.getCurrentWeatherData("40.4093", "49.8671", "7c313cd3bf3e1a279f21dda9cfe68e40")
                .enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {
                        Example example = new Example();
                        if (response.body().getCod()==200) {
                            example = response.body();
                            Log.e("example", example.toString());
                            Log.e("cod", String.valueOf(response.body().getCod()));
                        }
                        adapterCurrentWeather=new RVAdapterCurrentWeather(getContext(),example);
                        recyclerView.setAdapter(adapterCurrentWeather);
                    }



                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


}
