package com.mo.testeverything.fresco;

import android.graphics.Bitmap;
import android.util.Log;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.request.BasePostprocessor;

/**
 * 旋转并拼接原图四分之一至原图底部（eg：16:9 ---> 16:11.25）
 */
public class RotateAndJointProcessor extends BasePostprocessor {

  @Override
  public String getName() {
    return "RotateAndJointProcessor";
  }

  @Override
  public CloseableReference<Bitmap> process(
      Bitmap sourceBitmap,
      PlatformBitmapFactory bitmapFactory) {

    int sourceHeight = sourceBitmap.getHeight();
    int jointHeight = sourceHeight / 4;
    int totalHeight = sourceHeight + jointHeight;

    CloseableReference<Bitmap> bitmapRef = bitmapFactory.createBitmap(
        sourceBitmap.getWidth(),
        totalHeight);
    try {
      Bitmap destBitmap = bitmapRef.get();
      for (int x = 0; x < sourceBitmap.getWidth(); x++) {
        // 原图正常显示
        for (int m = 0; m < sourceHeight; m++) {
          destBitmap.setPixel(x, m, sourceBitmap.getPixel(x, m));
        }
        // 拼接原图的四分之一并翻转
        for (int y = 0; y < jointHeight; y++) {
          destBitmap.setPixel(x, y + sourceHeight, sourceBitmap.getPixel(x,
              sourceHeight - 1 - y));
        }
      }
      return CloseableReference.cloneOrNull(bitmapRef);
    } finally {
      CloseableReference.closeSafely(bitmapRef);
    }
  }
}
