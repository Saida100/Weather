package com.saida_aliyeva.weather;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class RVViewHolder extends RecyclerView.ViewHolder {


    TextView city1, dt_txt, speed, temp, weatherDescription, three_h, lat, lng;

    public RVViewHolder(@NonNull View itemView) {
        super(itemView);
        city1 = itemView.findViewById(R.id.cityName);
        dt_txt = itemView.findViewById(R.id.dt_txt);
        speed = itemView.findViewById(R.id.speed);
        temp = itemView.findViewById(R.id.temp);
        weatherDescription = itemView.findViewById(R.id.description);
        three_h = itemView.findViewById(R.id.three_h);
        lat = itemView.findViewById(R.id.lat);
        lng = itemView.findViewById(R.id.lng);
    }

    public void bind(List2 list2, City city) {
        try {
            city1.setText(city.getName());
            dt_txt.setText("date: " + list2.getDt_txt().toString());
            speed.setText("wind speed: " + list2.getWind().getSpeed().toString());
            temp.setText("temperature: " + list2.getMain().getTemp().toString());
            for (int i = 0; i < list2.getWeather().size(); i++) {
                weatherDescription.setText("description: " + list2.getWeather().get(i).getDescription());
            }
            lat.setText("latitude: " + city.getCoord().getLat().toString());
            lng.setText("longitude: " + city.getCoord().getLon().toString());
            three_h.setText("3h: " + list2.getRain().get3h().toString());


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
