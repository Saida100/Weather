package com.saida_aliyeva.weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    // http://api.openweathermap.org/data/2.5/forecast?id=587084&APPID=7c313cd3bf3e1a279f21dda9cfe68e40

    @GET("forecast")//7c313cd3bf3e1a279f21dda9cfe68e40
    Call<POJO> getPojo(@Query("id") String id,@Query("APPID") String appId);




    // 7b21197dab762bb201b06c6ffc433bb0
    // https://api.openweathermap.org/data/2.5/forecast?lat=40.4093&lon=49.8671&appid=7c313cd3bf3e1a279f21dda9cfe68e40
    @GET("forecast")
    Call<POJO> getTemp(@Query("id") String id,@Query("APPID") String appId);


    //     // 5 days weather forecast 3 hours
    // https://api.openweathermap.org/data/2.5/weather?lat=40.4093&lon=49.8671&appid=7c313cd3bf3e1a279f21dda9cfe68e40
    @GET("forecast")
    Call<POJO> getTempByLatAndLng(@Query("lat") String lat,@Query("lon") String lon,@Query("appid") String appid);


    // current weather
    // https://api.openweathermap.org/data/2.5/weather?q=Baku&appid=7c313cd3bf3e1a279f21dda9cfe68e40
    @GET("weather")
    Call<Example> getCurrentWeatherData(@Query("lat")  String lat,@Query("lon") String lon,@Query("appid") String appid );
}
