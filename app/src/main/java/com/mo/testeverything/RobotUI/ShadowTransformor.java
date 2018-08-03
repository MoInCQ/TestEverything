package com.mo.testeverything.RobotUI;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;

/**
 * Created by work on 2018/7/18.
 */

public class ShadowTransformor implements ViewPager.OnPageChangeListener, ViewPager.PageTransformer {

    private ViewPager viewPager;
    private RobotViewPagerAdapter adapter;
    private CardView cardView_0;
    private CardView cardView_1;
    private CardView cardView_2;

    public ShadowTransformor(ViewPager viewPager, RobotViewPagerAdapter adapter) {
        this.viewPager = viewPager;
        this.adapter = adapter;
        viewPager.addOnPageChangeListener(this);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        cardView_0 = adapter.getCardViewAt(0);
        cardView_1 = adapter.getCardViewAt(1);
        cardView_2 = adapter.getCardViewAt(2);

        switch (position) {
            case 0:
                cardView_0.animate().scaleX(1.05f).scaleY(1.05f).setDuration(500).start();
                cardView_1.animate().scaleX(1.00f).scaleY(1.00f).setDuration(200).start();
                cardView_2.animate().scaleX(1.00f).scaleY(1.00f).setDuration(200).start();
                break;
            case 1:
                cardView_1.animate().scaleX(1.05f).scaleY(1.05f).setDuration(500).start();
                cardView_0.animate().scaleX(1.00f).scaleY(1.00f).setDuration(200).start();
                cardView_2.animate().scaleX(1.00f).scaleY(1.00f).setDuration(200).start();
                break;
            case 2:
                cardView_2.animate().scaleX(1.05f).scaleY(1.05f).setDuration(500).start();
                cardView_1.animate().scaleX(1.00f).scaleY(1.00f).setDuration(200).start();
                cardView_0.animate().scaleX(1.00f).scaleY(1.00f).setDuration(200).start();
                break;
            default:
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void transformPage(View page, float position) {

    }
}
