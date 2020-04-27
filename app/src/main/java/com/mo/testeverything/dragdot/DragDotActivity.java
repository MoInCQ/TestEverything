package com.mo.testeverything.dragdot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.mo.testeverything.R;



public class DragDotActivity extends AppCompatActivity {

  private DragDotView bindDot;
  private TextView btnText;
  private Button btnVisible;
  private boolean isVisible = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_drag_dot);

    btnText = (TextView) findViewById(R.id.btn_ddv_text);
    bindDot = new DragDotView(this);
    bindDot.bindView(btnText, 42, 7, 0, 0);
    bindDot.setTitleText("1");
    btnText.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        bindDot.setTitleText(String.valueOf((int) (1 + Math.random() * (999 - 1))));
      }
    });

    btnVisible = (Button) findViewById(R.id.btn_ddv_visible);
    btnVisible.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        bindDot.setVisible(isVisible);
        isVisible = !isVisible;
      }
    });
    bindDot.setHorizontalTotalPadding(20);
    bindDot.setVerticalTotalPadding(20);



  }

}
