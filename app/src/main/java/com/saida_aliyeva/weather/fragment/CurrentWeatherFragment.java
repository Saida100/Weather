package com.saida_aliyeva.weather.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.saida_aliyeva.weather.Util;
import com.saida_aliyeva.weather.api.APIInit;
import com.saida_aliyeva.weather.api.APIInterface;
import com.saida_aliyeva.weather.R;
import com.saida_aliyeva.weather.api.Constants;
import com.saida_aliyeva.weather.model.Example;

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

     TextView cityTextView,  speedTextView, tempTextView, weatherDescriptionTextView, latTextView, lngTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.list_items_current_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        callRequest();

    }

    private void callRequest() {
        if(Util.isNetworkAvailable(getActivity())){
            APIInit apiInit = new APIInit();
            apiInit.initRetrofit();
            APIInterface apiInterface = apiInit.getClient();
            apiInterface.getCurrentWeatherData("40.4093", "49.8671", Constants.APP_ID)
                    .enqueue(new Callback<Example>() {
                        @Override
                        public void onResponse(Call<Example> call, Response<Example> response) {
                            Example example = new Example();
                            if (response.body().getCod()==200) {
                                example = response.body();
                                Log.e("example", example.toString());
                                Log.e("cod", String.valueOf(response.body().getCod()));
                            }
                            cityTextView.setText(example.getName());
                            weatherDescriptionTextView.setText("description: "+example.getWeather().get(0).getDescription());
                            speedTextView.setText("speed:"+example.getWind().getSpeed()+" meter/sec");
                            latTextView.setText("latitude: "+example.getCoord().getLat());
                            lngTextView.setText("longitude: "+example.getCoord().getLon());
                            tempTextView.setText("temp: "+example.getMain().getTemp()+" Kelvin");
                        }
                      @Override
                        public void onFailure(Call<Example> call, Throwable t) {
                            t.printStackTrace();
                          Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

                      }
                    });

        } else {
            Toast.makeText(getContext(),"No internet connection",Toast.LENGTH_SHORT).show();

        }
    }


 public void initViews(View view){
     cityTextView=view.findViewById(R.id.cityName);
     speedTextView=view.findViewById(R.id.speed);
     tempTextView=view.findViewById(R.id.temp);
     weatherDescriptionTextView=view.findViewById(R.id.description);
     latTextView=view.findViewById(R.id.lat);
     lngTextView=view.findViewById(R.id.lng);
 }
}
