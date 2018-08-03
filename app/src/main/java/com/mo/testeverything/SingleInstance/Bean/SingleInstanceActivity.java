package com.mo.testeverything.SingleInstance.Bean;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mo.testeverything.R;

public class SingleInstanceActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnLazy;
    private Button btnHungry;
    private Button btnDCL;
    private Button btnStatic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_instance);

        initView();

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        switch (i) {
            case R.id.btn_si_lazy:
                if (LazySingleInstance.getLazySingleInstance() != null) {
                    Toast.makeText(SingleInstanceActivity.this, "第一次" + LazySingleInstance.getLazySingleInstance().toString(), Toast.LENGTH_SHORT).show();
                    if (LazySingleInstance.getLazySingleInstance() != null) {
                        Toast.makeText(SingleInstanceActivity.this, "第二次" + LazySingleInstance.getLazySingleInstance().toString() , Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.btn_si_hungry:
                if (HungrySingleInstance.getHungrySingleInstance() != null) {
                    Toast.makeText(SingleInstanceActivity.this,"第一次" + HungrySingleInstance.getHungrySingleInstance().toString(), Toast.LENGTH_SHORT).show();
                    if (HungrySingleInstance.getHungrySingleInstance() != null) {
                        Toast.makeText(SingleInstanceActivity.this,"第二次" + HungrySingleInstance.getHungrySingleInstance().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.btn_si_dcl:
                if (DCLSingleInstance.getDclSingleInstance() != null) {
                    Toast.makeText(SingleInstanceActivity.this,"第一次" + DCLSingleInstance.getDclSingleInstance().toString(), Toast.LENGTH_SHORT).show();
                    if (DCLSingleInstance.getDclSingleInstance() != null) {
                        Toast.makeText(SingleInstanceActivity.this,"第二次" + DCLSingleInstance.getDclSingleInstance().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.btn_si_static:
                if (StaticSingleInstance.getStaticSingleInstance() != null) {
                    Toast.makeText(SingleInstanceActivity.this,"第一次" + StaticSingleInstance.getStaticSingleInstance().toString(), Toast.LENGTH_SHORT).show();
                    if (StaticSingleInstance.getStaticSingleInstance() != null) {
                        Toast.makeText(SingleInstanceActivity.this,"第二次" + StaticSingleInstance.getStaticSingleInstance().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    private void initView(){

        btnLazy = (Button) findViewById(R.id.btn_si_lazy);
        btnLazy.setOnClickListener(this);
        btnHungry = (Button) findViewById(R.id.btn_si_hungry);
        btnHungry.setOnClickListener(this);
        btnDCL = (Button) findViewById(R.id.btn_si_dcl);
        btnDCL.setOnClickListener(this);
        btnStatic = (Button) findViewById(R.id.btn_si_static);
        btnStatic.setOnClickListener(this);

    }
}
