package com.mo.testeverything.RobotUI;


import android.os.Bundle;

import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mo.testeverything.R;

/**
 * Created by work on 2018/7/18.
 */

public class ChatFragment extends RobotBaseFragment {

    private CardView cardView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.fragment_robot_chat, container, false);
        cardView = (CardView) view.findViewById(R.id.cv_robot_chat);
        return view;
    }


    @Override
    public CardView getCardView() {
        return cardView;
    }
}
