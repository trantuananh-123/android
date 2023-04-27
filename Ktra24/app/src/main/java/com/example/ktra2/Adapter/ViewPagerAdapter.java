package com.example.ktra2.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.ktra2.Fragment.FragmentInfo;
import com.example.ktra2.Fragment.FragmentList;
import com.example.ktra2.Fragment.FragmentSearch;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
            default:
                return new FragmentList();
            case 1:
                return new FragmentInfo();
            case 2:
                return new FragmentSearch();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
