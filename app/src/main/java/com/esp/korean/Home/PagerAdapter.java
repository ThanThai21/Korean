package com.esp.korean.Home;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.esp.korean.Home.Category.CategoryFragment;
import com.esp.korean.Home.Forum.ForumFragment;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragmentList;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentList = new ArrayList<>();
        fragmentList.add(new CategoryFragment());
        fragmentList.add(new ForumFragment());
        fragmentList.add(new ForumFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: return "Danh mục";
            case 1: return "Cộng đồng";
            case 2: return "Cộng đồng";
            default: return "Cộng đồng";
        }
    }
}
