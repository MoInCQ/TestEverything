package com.mo.testeverything;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.mo.testeverything.IdCode.IdCodeActivity;
import com.mo.testeverything.RobotUI.RobotUiActivity;
import com.mo.testeverything.dragdot.DragDotActivity;
import com.mo.testeverything.fadeinandout.FadeInAndOutActivity;
import com.mo.testeverything.fresco.FrescoTestActivity;
import com.mo.testeverything.imageoverturn.ImageOverturnActivity;
import com.mo.testeverything.rocker.RockerActivity;
import com.mo.testeverything.SingleInstance.Bean.SingleInstanceActivity;
import com.mo.testeverything.TableLayout.TableLayoutActivity;
import com.mo.testeverything.shortcut.ShortCutActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_singleInstance;
    private Button btn_tableLayout;
    private Button btn_rocker;
    private Button btn_shortcut;
    private Button btn_robot_ui;
    private Button btn_id_code;
    private Button btn_image_overturn;
    private Button btn_fresco_test;
    private Button btn_fade_in_and_out;
    private Button btn_drag_dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        //隐藏标题栏
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        //初始化控件
        initView();

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        switch (i) {
            case R.id.btn_main_single_instance:
                Intent intent = new Intent(MainActivity.this, SingleInstanceActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_main_table_layout:
                Intent intent1 = new Intent(MainActivity.this, TableLayoutActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_main_rocker:
                Intent intent2 = new Intent(MainActivity.this, RockerActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_main_shotcut:
                Intent intent3 = new Intent(MainActivity.this, ShortCutActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_main_robot_ui:
                Intent intent4 = new Intent(MainActivity.this, RobotUiActivity.class);
                startActivity(intent4);
                break;
            case R.id.btn_main_id_code:
                Intent intent5 = new Intent(MainActivity.this, IdCodeActivity.class);
                startActivity(intent5);
                break;
            case R.id.btn_main_iamge_overturn:
                Intent intent6 = new Intent(MainActivity.this, ImageOverturnActivity.class);
                startActivity(intent6);
                break;
            case R.id.btn_main_fresco:
                Intent intent7 = new Intent(MainActivity.this, FrescoTestActivity.class);
                startActivity(intent7);
                break;
            case R.id.btn_main_fade_in_and_out:
                Intent intent8 = new Intent(MainActivity.this, FadeInAndOutActivity.class);
                startActivity(intent8);
                break;
            case R.id.btn_main_drag_dot:
                Intent intent9 = new Intent(MainActivity.this, DragDotActivity.class);
                startActivity(intent9);
                break;
            default:
        }
    }

    private void initView(){
        btn_singleInstance = (Button) findViewById(R.id.btn_main_single_instance);
        btn_singleInstance.setOnClickListener(this);

        btn_tableLayout = (Button) findViewById(R.id.btn_main_table_layout);
        btn_tableLayout.setOnClickListener(this);

        btn_rocker = (Button) findViewById(R.id.btn_main_rocker);
        btn_rocker.setOnClickListener(this);

        btn_shortcut = (Button) findViewById(R.id.btn_main_shotcut);
        btn_shortcut.setOnClickListener(this);

        btn_robot_ui = (Button) findViewById(R.id.btn_main_robot_ui);
        btn_robot_ui.setOnClickListener(this);

        btn_id_code = (Button) findViewById(R.id.btn_main_id_code);
        btn_id_code.setOnClickListener(this);

        btn_image_overturn = (Button) findViewById(R.id.btn_main_iamge_overturn);
        btn_image_overturn.setOnClickListener(this);

        btn_fresco_test = (Button) findViewById(R.id.btn_main_fresco);
        btn_fresco_test.setOnClickListener(this);

        btn_fade_in_and_out = (Button) findViewById(R.id.btn_main_fade_in_and_out);
        btn_fade_in_and_out.setOnClickListener(this);

        btn_drag_dot = (Button) findViewById(R.id.btn_main_drag_dot);
        btn_drag_dot.setOnClickListener(this);
    }
}
