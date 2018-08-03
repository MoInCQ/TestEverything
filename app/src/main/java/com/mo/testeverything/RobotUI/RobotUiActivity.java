package com.mo.testeverything.RobotUI;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.mo.testeverything.R;

import java.util.ArrayList;
import java.util.List;

public class RobotUiActivity extends AppCompatActivity {

    private List<RobotBaseFragment> fragments = new ArrayList<>();
    private ViewPager viewPager;
    private ShadowTransformor shadowTransformor;

    private AlphaAnimation appearAnimation;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_robot_ui);

        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        appearAnimation = new AlphaAnimation(0, 1);
        appearAnimation.setDuration(1000);
        textView = (TextView) findViewById(R.id.tv_robot_title);
        textView.startAnimation(appearAnimation);
        textView.setVisibility(View.VISIBLE);

        fragments.add(new ChatFragment());
        fragments.add(new ClouldFragment());
        fragments.add(new VoiceControlFragment());

        RobotViewPagerAdapter adapter = new RobotViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager = (ViewPager) findViewById(R.id.vp_robot);
        shadowTransformor = new ShadowTransformor(viewPager, adapter);
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(false, shadowTransformor);
    }
}
