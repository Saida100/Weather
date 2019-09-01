package com.saida_aliyeva.weather.adapter.viewholder;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saida_aliyeva.weather.R;
import com.saida_aliyeva.weather.model.List2;
import com.squareup.picasso.Picasso;

public class RVInnerViewHolder extends RecyclerView.ViewHolder {

    TextView timeTextView, speedTextView, tempTextView, humidityTextView, weatherDescriptionTextView, three_hTextView;
    ;
    ImageView iconImageView;

    public RVInnerViewHolder(@NonNull View itemView) {
        super(itemView);
        timeTextView = itemView.findViewById(R.id.dt_txt);
        speedTextView = itemView.findViewById(R.id.speed);
        tempTextView = itemView.findViewById(R.id.temp);
        weatherDescriptionTextView = itemView.findViewById(R.id.description);
        three_hTextView = itemView.findViewById(R.id.three_h);
        iconImageView = itemView.findViewById(R.id.iconWeather);
        humidityTextView = itemView.findViewById(R.id.humidity);
    }

    public void bind(List2 list2, Context context) {
        try {
            String t = list2.getDt_txt();
            String time = t.substring(t.lastIndexOf(" "), t.lastIndexOf(":"));
            timeTextView.setText(time);
            speedTextView.setText(list2.getWind().getSpeed().toString());
            int temp = (int) Math.round(list2.getMain().getTemp() - 273.15);
            tempTextView.setText(String.valueOf(temp));
            humidityTextView.setText(String.valueOf(list2.getMain().getHumidity()));

            for (int i = 0; i < list2.getWeather().size(); i++) {
                //  weatherDescriptionTextView.setText(list2.getWeather().get(i).getDescription());
                String icon = list2.getWeather().get(i).getIcon();
                Picasso.get().load("http://openweathermap.org/img/wn/" + icon + "@2x.png").into(iconImageView);
            }
            three_hTextView.setText(list2.getRain().get_3h().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
