package com.mo.testeverything.fresco;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;
import com.mo.testeverything.R;

public class FrescoTestActivity extends AppCompatActivity {

  private SimpleDraweeView mSimpleDraweeView;
  private Uri uri;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fresco_test);

    mSimpleDraweeView = (SimpleDraweeView) findViewById(R.id.iv_fresco_test);
    uri = new Uri.Builder().scheme(UriUtil.LOCAL_RESOURCE_SCHEME)
        .path(String.valueOf(R.drawable.img_test))
        .build();

    // 构建图片请求
    ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
        .setPostprocessor(new RotateAndJointProcessor())
        .build();

    // 构建Controller
    PipelineDraweeController controller = (PipelineDraweeController)
        Fresco.newDraweeControllerBuilder()
            .setImageRequest(request)
            .setOldController(mSimpleDraweeView.getController())
            .build();

    // 显示
    mSimpleDraweeView.setController(controller);
  }
}
