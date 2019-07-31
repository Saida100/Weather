package com.saida_aliyeva.weather;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RVViewHolderCurrentWeather extends RecyclerView.ViewHolder {

    TextView city1,  speed, temp, weatherDescription, lat, lng;

    public RVViewHolderCurrentWeather(@NonNull View itemView) {
        super(itemView);
        city1=itemView.findViewById(R.id.cityName);
        speed=itemView.findViewById(R.id.speed);
        temp=itemView.findViewById(R.id.temp);
        weatherDescription=itemView.findViewById(R.id.description);
        lat=itemView.findViewById(R.id.lat);
        lng=itemView.findViewById(R.id.lng);

    }

    public void bind(Example example){
        try{
            city1.setText(example.getName().toString());
            speed.setText("speed: "+example.getWind().getSpeed().toString());
            temp.setText("temp: "+example.getMain().getTemp().toString());
            for(int i=0;i<example.getWeather().size();i++){
                weatherDescription.setText("description: "+example.getWeather().get(i).getDescription());

            }
            lat.setText("latitude: "+example.getCoord().getLat().toString());
            lng.setText("longitude: "+example.getCoord().getLon().toString());

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
