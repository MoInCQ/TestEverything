package com.example.testlibrary.utils;

import android.content.Context;

public class DestinyUtil {

  public static int getDP(Context context, int dp) {
    if (null != context) {
      return context.getResources().getDimensionPixelSize(dp);
    }
    return 0;
  }

}
