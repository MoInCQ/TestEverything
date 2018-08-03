package com.mo.testeverything.rocker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.mo.testeverything.R;

/**
 * Created by work on 2018/6/23.
 */

public class RockerView extends View {

    private final static String TAG = "RockerView";

    private int innerColor;
    private int outerColor;

    /**
     * 大圆宽高值
     */
    private int outerWidthSize;
    private int outerHeightSize;

    private Paint outerPaint;
    private Paint innerPaint;

    /**
     * 绘图使用的实际宽高
     */
    private int realWidth;
    private int realHeight;


    /**
     * 小圆中心点位置
     */
    private float innerCenterX;
    private float innerCenterY;

    /**
     * 内外圆半径
     */
    private float outerRadius;
    private float innerRadius;

    private OnNavAndSpeedListener mCallBack = null;

    public interface OnNavAndSpeedListener {
        void onNavAndSpeed(float nav, float speed);
    }


    public RockerView(Context context) {
        this(context, null);
    }

    public RockerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RockerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //获取设置的色彩
        TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.RockerView);
        innerColor = typedArray.getColor(R.styleable.RockerView_InnerColor, getResources().getColor(R.color.rockerInnerColorDefault));
        outerColor = typedArray.getColor(R.styleable.RockerView_OuterColor, getResources().getColor(R.color.rockerOuterColorDefault));
        //使对象能够重用
        typedArray.recycle();

        //设置大圆默认大小
        outerWidthSize = dip2px(context,125.0f);
        outerHeightSize = dip2px(context,125.0f);

        //初始化内外圆画笔并设置属性
        outerPaint = new Paint();
        innerPaint = new Paint();
        outerPaint.setColor(outerColor);
        outerPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        innerPaint.setColor(innerColor);
        innerPaint.setStyle(Paint.Style.FILL_AND_STROKE);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Log.d(TAG, "onMeasure()");

        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);

        //设置默认宽高解决wrap_content与match_parent效果一致的问题
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);

        Log.d(TAG, "onSizeChanged()");

        realWidth = width;
        realHeight = height;
        innerCenterX = realWidth/2;
        innerCenterY = realHeight/2;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d(TAG, "onDraw()");

        //画外部圆
        outerRadius = Math.min(Math.min(realWidth/2 - getPaddingLeft(), realWidth/2 - getPaddingRight()), Math.min(realHeight/2 - getPaddingTop(), realHeight/2 - getPaddingBottom()));
        canvas.drawCircle(realWidth/2, realHeight/2, outerRadius, outerPaint);

        //画内部圆
        innerRadius = outerRadius * 0.5f;
        canvas.drawCircle(innerCenterX, innerCenterY, innerRadius, innerPaint);
    }


    /**
     * 处理测量宽度
     *
     * @param widthMeasureSpec
     * @return
     */
    protected int measureWidth(int widthMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthValue = MeasureSpec.getSize(widthMeasureSpec);

        //处理三种模式
        if (widthMode == MeasureSpec.EXACTLY) {
            //返回父View给的值+padding
            return widthValue + getPaddingLeft() + getPaddingRight();
        } else if (widthMode == MeasureSpec.UNSPECIFIED) {
            //返回大圆宽度
            return outerWidthSize;
        }
            //widthMode == MeasureSpec.AT_MOST时
            else {
                //返回大圆宽高和父View允许的最大值之间的最小值
                return Math.min(outerWidthSize, widthValue);
        }
    }

    /**
     * 处理测量高度
     *
     * @param heightMeasureSpec
     * @return
     */
    private int measureHeight(int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightValue = MeasureSpec.getSize(heightMeasureSpec);

        //处理三种模式
        if (heightMode == MeasureSpec.EXACTLY){
            //返回父View给的值+padding
            return heightValue + getPaddingTop() + getPaddingBottom();
        } else if(heightMode == MeasureSpec.UNSPECIFIED){
            //返回大圆高度
            return outerHeightSize;
        }
            //widthMode == MeasureSpec.AT_MOST时
            else {
                //返回大圆宽高和父View允许的最大值之间的最小值
                return Math.min(outerHeightSize, heightValue);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.d(TAG, "onTOuchEvent()");

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            changeInnerCirclePosition(event);

        }
        if(event.getAction()==MotionEvent.ACTION_MOVE){
            changeInnerCirclePosition(event);
            Log.i("TAG","MOVED");
        }
        if(event.getAction()==MotionEvent.ACTION_UP){
            innerCenterX = realWidth/2;
            innerCenterY = realHeight/2;
            invalidate();
        }
        return true;
    }

    private void changeInnerCirclePosition(MotionEvent e) {
        //圆的方程：（x-realWidth/2）^2 +（y - realHeight/2）^2 <= outRadius^2
        //第一步，确定有效的触摸点集
        float X = e.getX();
        float Y = e.getY();
        if(mCallBack != null){
            mCallBack.onNavAndSpeed(X,Y);
        }
        boolean isPointInOutCircle = Math.pow(X - realWidth/2, 2) + Math.pow(Y - realHeight/2, 2) <= Math.pow(outerRadius, 2);
        if(isPointInOutCircle){
            Log.i("TAG","inCircle");
            //两种情况：小圆半径
            boolean isPointInFree = Math.pow(X - realWidth/2, 2) + Math.pow(Y - realHeight/2, 2) <= Math.pow(outerRadius - innerRadius, 2);
            if(isPointInFree){
                innerCenterX = X;
                innerCenterY = Y;
            } else {
                //处理限制区域，这部分使用触摸点与中心点与外圆方程交点作为内圆的中心点
                //使用近似三角形来确定这个点
                //求出触摸点，触摸点垂足和中心点构成的直角三角形（pointTri）的直角边长
                //横边
                float pointTriX = Math.abs(realWidth/2-X);
                //竖边
                float pointTriY = Math.abs(realHeight/2-Y);
                float pointTriZ = (float) Math.sqrt((Math.pow(pointTriX,2)+Math.pow(pointTriY,2)));
                float TriSin = pointTriY/pointTriZ;
                float TriCos = pointTriX/pointTriZ;
                //求出在圆环上的三角形的两个直角边的长度
                float limitCircleTriY = (outerRadius - innerRadius) * TriSin;
                float limitCircleTriX = (outerRadius - innerRadius) * TriCos;

                //确定内圆中心点的位置，分四种情况
                if(X >= realWidth/2 && Y >= realHeight/2){
                    innerCenterX = realWidth/2+limitCircleTriX;
                    innerCenterY = realHeight/2+limitCircleTriY;
                }else if(X < realWidth/2 && Y >= realHeight/2){
                    innerCenterX = realWidth/2-limitCircleTriX;
                    innerCenterY= realHeight/2+limitCircleTriY;
                }else if(X >= realWidth/2 && Y < realHeight/2){
                    innerCenterX = realWidth/2+limitCircleTriX;
                    innerCenterY= realHeight/2-limitCircleTriY;
                }else{
                    innerCenterX = realWidth/2-limitCircleTriX;
                    innerCenterY= realHeight/2-limitCircleTriY;
                }
                Log.i("TAG","inLimit");
            }
            invalidate();
        }else{
            Log.i("TAG","notInCircle");
        }
    }
    public void setOnNavAndSpeedListener(OnNavAndSpeedListener listener) {
        mCallBack = listener;
    }


    /**
     * 将dip转换为px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
