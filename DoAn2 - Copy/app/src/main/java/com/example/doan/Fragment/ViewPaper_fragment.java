package com.example.doan.Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPaper_fragment extends FragmentStatePagerAdapter {
    public ViewPaper_fragment(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new dsFood_fragment();
            case 1:
                return new Food_fragment();
            default:
                return new dsFood_fragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
