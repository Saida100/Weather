package com.saida_aliyeva.weather.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saida_aliyeva.weather.R;
import com.saida_aliyeva.weather.Util;
import com.saida_aliyeva.weather.model.City;
import com.saida_aliyeva.weather.model.List2;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;


public class RVViewHolder extends RecyclerView.ViewHolder {


    TextView dt_txtTextView, speedTextView, tempTextView, weatherDescriptionTextView, three_hTextView;
    ImageView weatherIconImageView;

    public RVViewHolder(@NonNull View itemView) {
        super(itemView);
        dt_txtTextView = itemView.findViewById(R.id.dt_txt);
        speedTextView = itemView.findViewById(R.id.speed);
        tempTextView = itemView.findViewById(R.id.temp);
        weatherDescriptionTextView = itemView.findViewById(R.id.description);
        three_hTextView = itemView.findViewById(R.id.three_h);
        weatherIconImageView=itemView.findViewById(R.id.iconWeather);

    }

    public void bind(List2 list2) {
        SimpleDateFormat informat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat outformat = new SimpleDateFormat("dd/MM/yyyy  HH:mm");


        try {
            Date d =informat.parse(list2.getDt_txt());
            dt_txtTextView.setText("date: " + outformat.format(d));
            speedTextView.setText("wind speed: " + list2.getWind().getSpeed());
            tempTextView.setText("temperature: " + list2.getMain().getTemp());
            for (int i = 0; i < list2.getWeather().size(); i++) {
                weatherDescriptionTextView.setText("description: " + list2.getWeather().get(i).getDescription());
                String icon=list2.getWeather().get(i).getIcon();
                Picasso.get().load("http://openweathermap.org/img/wn/"+icon+"@2x.png").into(weatherIconImageView);

            }
            three_hTextView.setText("rain volume: " + list2.getRain().get_3h());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
