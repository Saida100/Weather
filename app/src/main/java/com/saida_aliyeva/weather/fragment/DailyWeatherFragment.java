package com.saida_aliyeva.weather.fragment;

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

import com.saida_aliyeva.weather.adapter.RVAdapter;
import com.saida_aliyeva.weather.adapter.RVOutAdapter;
import com.saida_aliyeva.weather.api.APIInit;
import com.saida_aliyeva.weather.api.APIInterface;
import com.saida_aliyeva.weather.R;
import com.saida_aliyeva.weather.api.Constants;
import com.saida_aliyeva.weather.model.List2;
import com.saida_aliyeva.weather.model.POJO;
import com.saida_aliyeva.weather.model.SectionDataModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    RVOutAdapter rvOutAdapter;
    List<SectionDataModel> sectionDataModelList;
    SectionDataModel sectionDataModel;

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
                            List<List2> list2List = new ArrayList<>();
                            List<List2> list=new ArrayList<>();
                            sectionDataModelList = new ArrayList<>();

                            String previousDate;
                            String currentDate = getCurrentDate();
                            cityName = pojo.getCity().getName();
                            cityNameTextView.setText(cityName);
//                            cityNameTextView.append(cityName + "\n");
//                            cityNameTextView.append("wind speed : meter/sec " + "\n");
//                            cityNameTextView.append("temperature : Kelvin " + "\n");
//                            cityNameTextView.append(" Rain volume : mm ");
                            Log.e("pojo", pojo.toString());
                            Log.e("cod", response.body().getCod());
                            int j = 0, count = 0;
                            String t = pojo.getList().get(0).getDt_txt();
                            previousDate = t.substring(0, t.lastIndexOf(" ")).trim();
                            while (currentDate.equals(previousDate)) {
                                sectionDataModel = new SectionDataModel();
                                t = pojo.getList().get(j).getDt_txt();
                                previousDate = t.substring(0, t.lastIndexOf(" ")).trim();
                                list2List.add(pojo.getList().get(j));
                                j++;
                                Log.e("j=", String.valueOf(j)+list2List.toString());
                            }
                            sectionDataModel.setDate(list2List.get(0).getDt_txt());
                            list.addAll(list2List.subList(0,j-1));
                            sectionDataModel.setList2List(list);
                            sectionDataModelList.add(sectionDataModel);

                            for (int z = j-1; z < pojo.getList().size(); z++) {
                                t = pojo.getList().get(z).getDt_txt();
                                previousDate = t.substring(0, t.lastIndexOf(" ")).trim();
                                if (!currentDate.equals(previousDate)) {
                                    list2List.add(pojo.getList().get(z));
                                    count++;
                                    if (count == 8) {
                                        List<List2>arrlist2=list2List.subList(z-6, z+2);
                                        list=new ArrayList<>();
                                        list.addAll(arrlist2);
                                        sectionDataModel = new SectionDataModel();
                                        sectionDataModel.setList2List(list);
                                        sectionDataModel.setDate(list2List.get(z).getDt_txt());
                                        sectionDataModelList.add(sectionDataModel);
                                        count = 0;
                                        Log.e("itemDataList", sectionDataModelList.toString());
                                    }

                                }
                            }

                            Log.e("sectionDataModelList", sectionDataModelList.toString());

                        }
                        rvOutAdapter = new RVOutAdapter(sectionDataModelList, getContext());
                        recyclerView.setAdapter(rvOutAdapter);
//                        adapter = new RVAdapter(pojo, getContext());
//                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<POJO> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = simpleDateFormat.format(date);
        return currentDate;
    }

//    public void createDummyData() {
//        for (int i = 1; i <= 5; i++) {
//
//            SectionDataModel dm = new SectionDataModel();
//
//
//            ArrayList<POJO> singleItem = new ArrayList<>();
//            for (int j = 0; j <= 5; j++) {
//                singleItem.add(new SingleItemModel("Item " + j, "URL " + j));
//            }
//
//            dm.setAllItemsInSection(singleItem);
//
//            allSampleData.add(dm);
//
//        }
//    }
}
