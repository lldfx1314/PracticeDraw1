package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.model.PieChartData;

import java.util.ArrayList;
import java.util.List;

public class Practice11PieChartView extends View {

    private final String TAG = "Practice11PieChartView";

    private List<PieChartData> pieChartData = new ArrayList<>();
    /**
     * 起始角度
     */
    private float mStartAngle = 0F;
    /**
     * 控件除去Padding后的宽度
     */
    private int mAbsWidth;
    /**
     * 控件除去Padding后的高度
     */
    private int mAbsHeight;
    /**
     * 控件宽度
     */
    private int mWidth;
    /**
     * 控件高度
     */
    private int mHeight;
    /**
     * PaddingStart
     */
    private int paddingStart;
    /**
     * PaddingEnd
     */
    private int paddingEnd;
    /**
     * PaddingTop
     */
    private int paddingTop;
    /**
     * PaddingBottom
     */
    private int paddingBottom;

    private int mBottomTitleHeight;

    private RectF mRectF = new RectF();

    private int mCircleRadius;

    private Paint mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint mTitlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private float mCircleCenterX;
    private float mCircleCenterY;

    {
        pieChartData.add(new PieChartData("Gingerbread", Color.MAGENTA, 6));
        pieChartData.add(new PieChartData("IceCreamSandwich", Color.DKGRAY, 5));
        pieChartData.add(new PieChartData("Jelly Bean", Color.GREEN, 65));
        pieChartData.add(new PieChartData("KitKat", Color.BLUE, 99));
        pieChartData.add(new PieChartData("Lollipop", Color.RED, 123));
        pieChartData.add(new PieChartData("Marshmallow", Color.YELLOW, 60));
        pieChartData.add(new PieChartData("Froyo", Color.GRAY, 1));

        mArcPaint.setStyle(Paint.Style.FILL);

        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(2);
        mLinePaint.setColor(Color.WHITE);

        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(20);

        mTitlePaint.setStyle(Paint.Style.STROKE);
        mTitlePaint.setColor(Color.WHITE);
        mTitlePaint.setTextSize(45);
        mTitlePaint.setTextAlign(Paint.Align.CENTER);
    }

    public Practice11PieChartView(Context context) {
        super(context);
        init();
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    private int mRectPadding = 100;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = measureHandler(widthMeasureSpec);
        mHeight = measureHandler(heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
        paddingStart = getPaddingStart();
        paddingEnd = getPaddingEnd();
        paddingTop = getPaddingTop();
        paddingBottom = getPaddingBottom();
        mAbsWidth = mWidth - paddingStart - paddingEnd;
        mAbsHeight = mHeight - paddingTop - paddingBottom;
        mBottomTitleHeight = (int) (mAbsHeight * 0.1F);

        mCircleRadius = (mAbsHeight - mBottomTitleHeight) / 2 - mRectPadding;

        float mRectLeft = mAbsWidth * 0.5F - mCircleRadius;
        float mRectTop = (mAbsHeight - mBottomTitleHeight) * 0.5F - mCircleRadius;
        float mRectRight = mAbsWidth * 0.5F + mCircleRadius;
        float mRectBottom = (mAbsHeight - mBottomTitleHeight) * 0.5F + mCircleRadius;

        mRectF.set(mRectLeft, mRectTop, mRectRight, mRectBottom);

        //获取圆心
        mCircleCenterX = (mRectRight + mRectLeft) / 2;
        mCircleCenterY = (mRectBottom + mRectTop) / 2;
    }

    private int measureHandler(int measureSpec) {
        int result = 0;
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            result = Math.max(size, result);
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mStartAngle = 0F;
        for (int i = 0; i < pieChartData.size(); i++) {
            PieChartData pieChartData = this.pieChartData.get(i);
            int value = pieChartData.getValue();
            mArcPaint.setColor(pieChartData.getColor());
            canvas.drawArc(mRectF, mStartAngle, value, true, mArcPaint);
            float mStopAngle = mStartAngle + value;
            float mHalfAngle = (mStartAngle + mStopAngle) / 2;
            mStartAngle += value;

            Log.e(TAG, "onDraw mHalfAngle: " + mHalfAngle);

            float mPointX = (float) (mCircleCenterX + (mCircleRadius + 50) * Math.cos(mHalfAngle * Math.PI / 180));
            float mPointY = (float) (mCircleCenterY + (mCircleRadius + 50) * Math.sin(mHalfAngle * Math.PI / 180));
            Log.e(TAG, "mPointX: " + mPointX);
            Log.e(TAG, "mPointY: " + mPointY);

            canvas.drawLine(mCircleCenterX, mCircleCenterY, mPointX, mPointY, mLinePaint);
            float mStopXline = 0;
            if (mHalfAngle >= 270 || mHalfAngle < 90) {
                mStopXline = mPointX + 50;

                mTextPaint.setTextAlign(Paint.Align.LEFT);
                canvas.drawText(pieChartData.getTitle(), mStopXline, mPointY, mTextPaint);

                canvas.drawLine(mPointX, mPointY, mStopXline, mPointY, mLinePaint);
            } else if (mHalfAngle < 270) {
                mStopXline = mPointX - 50;

                mTextPaint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText(pieChartData.getTitle(), mStopXline, mPointY, mTextPaint);

                canvas.drawLine(mPointX, mPointY, mStopXline, mPointY, mLinePaint);
            }

        }
        canvas.drawText("饼图", mCircleCenterX,mAbsHeight - mBottomTitleHeight *0.5F, mTitlePaint);
    }
}
