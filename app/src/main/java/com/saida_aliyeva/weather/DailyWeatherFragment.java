package com.saida_aliyeva.weather;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_daily_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView txtCityName=view.findViewById(R.id.cityName);
        final RecyclerView recyclerView=view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        APIInit apiInit=new APIInit();
        apiInit.initRetrofit();
        APIInterface apiInterface=apiInit.getClient();
        apiInterface.getTempByLatAndLng("40.4093", "49.8671","7c313cd3bf3e1a279f21dda9cfe68e40")
                .enqueue(new Callback<POJO>() {
                    @Override
                    public void onResponse(Call<POJO> call, Response<POJO> response) {
                        POJO pojo = new POJO();
                        if (response.body().getCod().equals("200")) {
                            pojo = response.body();
                            cityName=pojo.getCity().getName();
                            txtCityName.setText(cityName);

                            Log.e("pojo", pojo.toString());
                            Log.e("cod", response.body().getCod());
                        }
                        adapter = new RVAdapter(pojo, getContext());
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<POJO> call, Throwable t) {
                           t.printStackTrace();
                    }
                });

//        apiInterface.getPojo("587084", "7c313cd3bf3e1a279f21dda9cfe68e40")
//                .enqueue(new Callback<POJO>() {
//                    @Override
//                    public void onResponse(Call<POJO> call, Response<POJO> response) {
//                        POJO pojo = new POJO();
//                        if (response.body().getCod().equals("200")) {
//                            pojo = response.body();
//                            Log.e("pojo", pojo.toString());
//                            Log.e("cod", response.body().getCod());
//                        }
//
//                        adapter = new RVAdapter(pojo, getContext());
//
//                        recyclerView.setAdapter(adapter);
//                    }
//
//                    @Override
//                    public void onFailure(Call<POJO> call, Throwable t) {
//
//                    }
//                });



    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


}
