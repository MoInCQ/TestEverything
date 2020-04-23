package com.mo.testeverything.dragdot;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.mo.testeverything.R;

public class DragDotView extends View {

  /**
   * 文本内容
   */
  private String mTitleText;
  /**
   * 文本的颜色
   */
  private int mTitleTextColor;
  /**
   * 文本的大小
   */
  private int mTitleTextSize;

  private int mBackgroundColor;

  /**
   * 圆角大小
   */
  private int mCornerSize;

  /**
   * 绘制时控制文本绘制的范围
   */
  private Rect mtitleBound;
  private Paint mtitlePaint;

  public DragDotView(Context context) {
    this(context, null);
  }

  public DragDotView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public DragDotView(Context context,
      @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DragDotView, defStyleAttr, 0);
    mTitleText = a.getString(R.styleable.DragDotView_ddvText);
    // 默认颜色设置为黑色
    mTitleTextColor = a.getColor(R.styleable.DragDotView_ddvTextColor, Color.BLACK);
    // 默认设置为16sp
    mTitleTextSize = a
        .getDimensionPixelSize(R.styleable.DragDotView_ddvTextSize, (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
    //默认为白色
    mBackgroundColor = a.getColor(R.styleable.DragDotView_ddvBackground, Color.WHITE);
    //默认圆角为0
    mCornerSize = a.getInteger(R.styleable.DragDotView_ddvCornerSize, 200);
    a.recycle();
    // 初始化画笔
    mtitlePaint = new Paint();
    mtitlePaint.setTextSize(mTitleTextSize);
    mtitleBound = new Rect();
    mtitlePaint.getTextBounds(mTitleText, 0, mTitleText.length(), mtitleBound);
  }



  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

    int widthMode = MeasureSpec.getMode(widthMeasureSpec);
    int widthSize = MeasureSpec.getSize(widthMeasureSpec);
    int heightMode = MeasureSpec.getMode(heightMeasureSpec);
    int heightSize = MeasureSpec.getSize(heightMeasureSpec);
    int width;
    int height;

    if (widthMode == MeasureSpec.EXACTLY) {
      width = widthSize;
    } else {
      mtitlePaint.setTextSize(mTitleTextSize);
      mtitlePaint.getTextBounds(mTitleText, 0, mTitleText.length(), mtitleBound);
      int desired = getPaddingLeft() + mtitleBound.width() + getPaddingRight();
      width = Math.min(desired, widthSize);
    }

    if (heightMode == MeasureSpec.EXACTLY) {
      height = heightSize;
    } else {
      mtitlePaint.setTextSize(mTitleTextSize);
      mtitlePaint.getTextBounds(mTitleText, 0, mTitleText.length(), mtitleBound);
      int desired = getPaddingTop() + mtitleBound.height() + getPaddingBottom();
      height = Math.min(desired, heightSize);
    }
    setMeasuredDimension(width, height);
  }

  @Override
  protected void onDraw(Canvas canvas) {

    Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
    paint.setAntiAlias(true);
    paint.setColor(mBackgroundColor);
    RectF rec = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());
    canvas.drawRoundRect(rec, mCornerSize, mCornerSize, paint);

    mtitlePaint.setColor(mTitleTextColor);
    Paint.FontMetricsInt fontMetrics = mtitlePaint.getFontMetricsInt();
    int baseline =
        (getMeasuredHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
    canvas.drawText(mTitleText, getPaddingLeft(), baseline, mtitlePaint);
  }
}

