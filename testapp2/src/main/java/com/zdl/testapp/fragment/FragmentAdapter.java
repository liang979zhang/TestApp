package com.zdl.testapp.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by zdl on 2017/10/18.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    private String[] titles = new String[]{"Tab1", "Tab2", "Tab3", "Tab4", "Tab5"};

    private Context context;

    public FragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;

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
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
