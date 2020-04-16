package com.mo.testeverything.imageoverturn;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import com.mo.testeverything.R;


public class OverturnImageView extends LinearLayout {

  private ImageView originalImg;
  private ImageView overturnImg;
  private LayoutParams layoutParams;

  public OverturnImageView(Context context) {
    this(context, null);
  }

  public OverturnImageView(Context context,
      @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public OverturnImageView(Context context,
      @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.OverturnImageView);
    initView(context, array.getResourceId(R.styleable.OverturnImageView_imgRes, 0));
    array.recycle();
  }

  private void initView(Context context, int imageRes) {
    if (imageRes == 0) {
      return;
    }
    layoutParams = new LayoutParams(300, 200);

    // 原图正常显示
    originalImg = new ImageView(context);
    originalImg.setLayoutParams(layoutParams);
    originalImg.setImageResource(imageRes);
    addView(originalImg);


    // 翻转图下移
    overturnImg = new ImageView(context);
    Bitmap originBitmap = BitmapFactory.decodeResource(getResources(), imageRes);
    Matrix matrix = new Matrix();
    //缩放(以左上角为原点)当sy为-1时向上翻转 当sx为-1时向左翻转 sx、sy都为-1时相当于旋转180°
    matrix.postScale(1, -1);
    //因为向上翻转了所以y要向下平移一个bitmap的高度
    //matrix.postTranslate(0, originBitmap.getHeight());
    Bitmap overturnBitmap = Bitmap.createBitmap(originBitmap, 0, 0, originBitmap.getWidth(),
        originBitmap.getHeight() , matrix, true);
    overturnImg.setLayoutParams(layoutParams);
    overturnImg.setImageBitmap(overturnBitmap);
    addView(overturnImg);

  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int width = originalImg.getLayoutParams().width;
    int height = originalImg.getLayoutParams().height / 4 + originalImg.getLayoutParams().height;
    setMeasuredDimension(width, height);
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
    originalImg.layout(l, 0, r, 4 * b / 5);
    overturnImg.layout(l, 4 * b / 5, r, b);
  }
}
