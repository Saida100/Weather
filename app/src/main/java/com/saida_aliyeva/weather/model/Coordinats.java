package com.saida_aliyeva.weather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coordinats {
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lon")
    @Expose
    private Double lon;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Coordinats{" +
                "lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
