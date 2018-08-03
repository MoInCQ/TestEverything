package com.mo.testeverything.RobotUI;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.CardView;

import java.util.List;

/**
 * Created by work on 2018/7/18.
 */

public class RobotViewPagerAdapter extends FragmentPagerAdapter {

    private List<RobotBaseFragment> mFragments;

    public RobotViewPagerAdapter(FragmentManager fm, List<RobotBaseFragment> mFragments) {
        super(fm);
        this.mFragments = mFragments;

    }

    public CardView getCardViewAt(int position) {
        return mFragments.get(position).getCardView();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

}
