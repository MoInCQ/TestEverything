package com.mo.testeverything.shortcut;


import android.content.Context;
import android.content.Intent;

import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.mo.testeverything.MainActivity;
import com.mo.testeverything.R;
import com.mo.testeverything.rocker.RockerActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ShortCutActivity extends AppCompatActivity {

    private static final String TAG = "ShortCutActivity";

    private Context context = ShortCutActivity.this;

    private Button btnCreateList;
    private Button btnCreate;
    private Button btnDelete;

    /**
     * 通过自定义action构建快捷方式打开意图的Intent
     */
    private Intent intent = new Intent("com.mo.testeverything.action.SHORTCUT");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_cut);

        btnCreate = (Button) findViewById(R.id.btn_shortcut_create);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //构建创建快捷方式的Intent
                Intent shortcutintent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");

                // 不允许重复创建
                shortcutintent.putExtra("duplicate", false);
                // 快捷方式的名称
                shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "测试");
                // 快捷方式的icon
                Parcelable icon = Intent.ShortcutIconResource.fromContext(ShortCutActivity.this.getApplicationContext(), R.drawable.ic_launcher);
                shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
                // 点击快捷图片，运行的程序入口Intent
                shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);

                //发送广播调用
                sendBroadcast(shortcutintent);
                Log.d(TAG, "创建快捷方式成功");
            }
        });

        btnDelete = (Button) findViewById(R.id.btn_shortcut_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deleteIntent = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");

                // 名字
                deleteIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "测试");

                deleteIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);

                // 发送广播
                sendBroadcast(deleteIntent);
                Log.d(TAG, "删除快捷方式成功");
            }
        });


        btnCreateList = (Button) findViewById(R.id.btn_shortcut_list_create);
        btnCreateList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);
                List<ShortcutInfo> infos = new ArrayList<>();

                Intent intent = new Intent(context, MainActivity.class);
                intent.setAction(Intent.ACTION_VIEW);
                ShortcutInfo info = new ShortcutInfo.Builder(context, "1")
                        .setShortLabel("1111")
                        .setLongLabel("111111111111111")
                        .setIcon(Icon.createWithResource(context, R.mipmap.ic_launcher))
                        .setIntent(intent)
                        .build();
                infos.add(info);

                Intent intent2 = new Intent(context, RockerActivity.class);
                intent2.setAction(Intent.ACTION_VIEW);
                ShortcutInfo info2 = new ShortcutInfo.Builder(context, "2")
                        .setShortLabel("2222")
                        .setLongLabel("222222222222")
                        .setIcon(Icon.createWithResource(context, R.mipmap.ic_launcher))
                        .setIntent(intent2)
                        .build();
                infos.add(info2);

                shortcutManager.setDynamicShortcuts(infos);

            }
        });


    }
}
