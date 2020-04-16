package com.mo.testeverything;

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;

public class TestApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Fresco.initialize(this);
  }
}
