package com.mo.testeverything.dragdot;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.example.testlibrary.utils.DestinyUtil;
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

  /**
   * 背景色
   */
  private int mBackgroundColor;

  /**
   * 圆角大小
   */
  private int mCornerSize;

  /**
   * 绘制时控制文本绘制的范围
   */
  private Rect mTitleBound;

  /**
   * 边框宽度
   */
  private int mStockWidth;

  /**
   * 边框颜色
   */
  private int mStockColor;

  /**
   * 预期文字宽度
   */
  private int mDesiredWith;

  /**
   * 背景宽高
   */
  private int mBackgroundWidth;
  private int mBackgroundHeight;

  /**
   * 横向及纵向总padding（文字居中）（弃用Android：padding）
   */
  private int mVerticalTotalPadding;
  private int mHorizontalTotalPadding;

  /**
   * 实际宽高
   */
  private int mWidth;
  private int mHeight;

  private Paint mTitlePaint;
  private Paint mColorPaint;
  private Context mContext;

  /**
   * 默认属性
   */
  private final String DEFAULT_TITLE_TEXT = "";
  private final int DEFAULT_TEXT_COLOR = Color.WHITE;
  private final int DEFAULT_TEXT_SIZE = 12;
  private final int DEFAULT_BACKGROUND_COLOR = R.color.drag_dot_view_bg;
  private final int DEFAULT_RADIUS = Integer.MAX_VALUE;
  private final int DEFAULT_STOCK_WIDTH = R.dimen.dp1;
  private final int DEFAULT_STOCK_COLOR = R.color.rockerInnerColorDefault;
  private final int DEFAULT_VERTICAL_TOTAL_PADDING = R.dimen.dp2;
  private final int DEFAULT_HORIZONTAL_TOTAL_PADDING = R.dimen.dp7;


  /**
   * 背景及边框
   */
  private RectF recStock;
  private RectF rec;


  public DragDotView(Context context) {
    this(context, null);
  }

  public DragDotView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public DragDotView(Context context,
      @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    mContext = context;

    TypedArray a = context.getTheme()
        .obtainStyledAttributes(attrs, R.styleable.DragDotView, defStyleAttr, 0);

    // 文字内容
    if (TextUtils.isEmpty(a.getString(R.styleable.DragDotView_ddvText))) {
      mTitleText = DEFAULT_TITLE_TEXT;
    } else {
      mTitleText = a.getString(R.styleable.DragDotView_ddvText);
    }
    // 默认文字颜色设置为白色
    mTitleTextColor = a.getColor(R.styleable.DragDotView_ddvTextColor, DEFAULT_TEXT_COLOR);
    // 默认设置为12sp
    mTitleTextSize = a
        .getDimensionPixelSize(R.styleable.DragDotView_ddvTextSize, (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, DEFAULT_TEXT_SIZE, getResources().getDisplayMetrics()));
    // 默认背景为红色
    mBackgroundColor = a.getColor(R.styleable.DragDotView_ddvBackground,
        getResources().getColor(DEFAULT_BACKGROUND_COLOR));
    // 默认为小圆点
    mCornerSize = a.getInteger(R.styleable.DragDotView_ddvCornerSize, DEFAULT_RADIUS);
    // 默认边框大小为1dp
    mStockWidth = a.getDimensionPixelSize(R.styleable.DragDotView_ddvStockWidth,
        DestinyUtil.getDP(context, DEFAULT_STOCK_WIDTH));
    // 默认边框颜色为白色
    mStockColor = a.getColor(R.styleable.DragDotView_ddvStockColor,
        getResources().getColor(DEFAULT_STOCK_COLOR));
    // 默认Padding
    mVerticalTotalPadding = a.getDimensionPixelSize(R.styleable.DragDotView_ddvVerticalTotalPadding,
        DestinyUtil.getDP(context, DEFAULT_VERTICAL_TOTAL_PADDING));
    mHorizontalTotalPadding = a
        .getDimensionPixelSize(R.styleable.DragDotView_ddvHorizontalTotalPadding,
            DestinyUtil.getDP(context, DEFAULT_HORIZONTAL_TOTAL_PADDING));

    a.recycle();

    initPaint();
  }

  /**
   * 初始化画笔
   */
  private void initPaint() {
    // 文字画笔
    mTitlePaint = new Paint();
    mTitlePaint.setTextSize(mTitleTextSize);
    mTitleBound = new Rect();
    mTitlePaint.getTextBounds(mTitleText, 0, mTitleText.length(), mTitleBound);
    // 背景画笔
    mColorPaint = new Paint();
    mColorPaint.setAntiAlias(true);
    // 背景及边框
    recStock = new RectF();
    rec = new RectF();
  }

  /**
   * 设置宽高总Padding（文字居中）
   *
   * @param verticalTotalPadding
   */
  public void setVerticalTotalPadding(int verticalTotalPadding) {
    this.mVerticalTotalPadding = verticalTotalPadding;
  }

  public void setHorizontalTotalPadding(int horizontalTotalPadding) {
    this.mHorizontalTotalPadding = horizontalTotalPadding;
  }


  /**
   * 更新Title内容
   */
  public void setTitleText(String text) {
    mTitleText = text;
    requestLayout();
    postInvalidate();
  }


  /**
   * 更新可见性
   */
  public void setVisible(boolean isVisible) {
    if (isVisible) {
      this.setVisibility(VISIBLE);
    } else {
      this.setVisibility(GONE);
    }
  }

  /**
   * 绑定在某一个View上（margin距离）
   */
  public void bindView(final View targetView, int marginLeft, int marginTop, int marginRight,
      int marginBottom) {
    // 从原父布局中删除
    if (getParent() != null) {
      ((ViewGroup) getParent()).removeView(this);
    }

    // 获取targetView的父布局
    ViewGroup parent = (ViewGroup) targetView.getParent();
    if (parent == null) {
      return;
    }
    int index = parent.indexOfChild(targetView);
    parent.removeView(targetView);

    // 容器大小为targetView大小
    FrameLayout container = new FrameLayout(mContext);
    ViewGroup.LayoutParams containerLayoutParams = targetView.getLayoutParams();
    container.setLayoutParams(containerLayoutParams);

    // 原targetView大小与容器一样大
    ViewGroup.LayoutParams targetLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
        LayoutParams.MATCH_PARENT);
    targetView.setLayoutParams(targetLayoutParams);
    container.addView(targetView);
    parent.addView(container, index);

    // 确定红点位置
    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    layoutParams.setMargins(marginLeft, marginTop, marginRight, marginBottom);
    setLayoutParams(layoutParams);
    bringToFront();
    container.addView(this);
  }


  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    mTitlePaint.setTextSize(mTitleTextSize);
    mBackgroundHeight = mTitleTextSize + mVerticalTotalPadding ;
    mDesiredWith = (int) (mTitlePaint.measureText(mTitleText));
    // 宽度过小时默认宽高相同
    if (mDesiredWith + mHorizontalTotalPadding <= mBackgroundHeight) {
      mBackgroundWidth = mBackgroundHeight;
    } else {
      mBackgroundWidth = mDesiredWith + mHorizontalTotalPadding;
    }
    mWidth = mBackgroundWidth + mStockWidth;
    mHeight = mBackgroundHeight + mStockWidth;

    setMeasuredDimension(mWidth, mHeight);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    // 画边框
    mColorPaint.setColor(mStockColor);
    recStock.set(0, 0, mWidth, mHeight);
    canvas.drawRoundRect(recStock, mCornerSize, mCornerSize, mColorPaint);

    // 画背景
    mColorPaint.setColor(mBackgroundColor);
    rec.set(mStockWidth, mStockWidth, mWidth - mStockWidth, mHeight - mStockWidth);
    canvas.drawRoundRect(rec, mCornerSize, mCornerSize, mColorPaint);

    // 画文字
    mTitlePaint.setColor(mTitleTextColor);
    Paint.FontMetricsInt fontMetrics = mTitlePaint.getFontMetricsInt();
    int baseline =
        ((mHeight - (fontMetrics.bottom - fontMetrics.top)) >> 1) - fontMetrics.top;
    canvas.drawText(mTitleText, (mWidth - mDesiredWith) >> 1, baseline, mTitlePaint);
  }
}