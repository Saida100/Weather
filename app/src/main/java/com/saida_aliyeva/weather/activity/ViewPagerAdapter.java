package com.saida_aliyeva.weather.activity;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.saida_aliyeva.weather.fragment.CurrentWeatherFragment;
import com.saida_aliyeva.weather.fragment.DailyWeatherFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CurrentWeatherFragment();
            default:
                return new DailyWeatherFragment();
        }

    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Current Weather";
            default:
                return "Daily weather";
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
