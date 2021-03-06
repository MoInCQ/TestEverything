package com.mo.testeverything.RobotUI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mo.testeverything.R;

/**
 * Created by work on 2018/7/18.
 */

public class ClouldFragment extends RobotBaseFragment {

    private CardView cardView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clould, container, false);
        cardView = (CardView) view.findViewById(R.id.cv_robot_cloud);
        return view;
    }

    @Override
    public CardView getCardView() {
        return cardView;
    }
}
