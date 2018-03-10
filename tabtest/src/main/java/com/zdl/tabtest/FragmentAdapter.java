package com.zdl.tabtest;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zdl on 2017/10/18.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

//    private String[] titles = new String[]{"Tab1", "Tab2", "Tab3", "Tab4", "Tab5","Tab6","Tab7"};

    private List<String> titles = new ArrayList<>();


    private Context context;

    public FragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        for (int i = 0; i < 20; i++) {
            titles.add("Tab" + (i + 1));
        }

    }

    /**
     * 获取fragment
     *
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1);
    }

    /**
     * 标题长度
     *
     * @return
     */
    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
