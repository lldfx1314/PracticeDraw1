package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.model.PieChartData;

import java.util.ArrayList;
import java.util.List;

public class Practice11PieChartView extends View {

    private List<PieChartData> pieChartData = new ArrayList<>();

    private float mPieChartStartAngle = 0F;

    private int mAbsWidth;
    private int mAbsHeight;
    private int mWidth;
    private int mHeight;
    private int paddingStart;
    private int paddingEnd;
    private int paddingTop;
    private int paddingBottom;
    private int mPieChartWidth;
    private int mPieChartHeight;
    private int mBottomTitleHeight;

    private RectF mRectF = new RectF();

    private int mCircleRadius;

    private Paint mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    {
        pieChartData.add(new PieChartData("Froyo", Color.GRAY, 5));
        pieChartData.add(new PieChartData("Gingerbread", Color.MAGENTA, 10));
        pieChartData.add(new PieChartData("IceCreamSandwich", Color.DKGRAY, 8));
        pieChartData.add(new PieChartData("Jelly Bean", Color.GREEN, 55));
        pieChartData.add(new PieChartData("KitKat", Color.BLUE, 97));
        pieChartData.add(new PieChartData("Lollipop", Color.RED, 125));
        pieChartData.add(new PieChartData("Marshmallow", Color.YELLOW, 60));

        mArcPaint.setStyle(Paint.Style.FILL);

        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setColor(Color.WHITE);
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
        mPieChartWidth = mAbsWidth;
        mPieChartHeight = mAbsHeight - mBottomTitleHeight;
        mCircleRadius = mPieChartHeight / 2 - mRectPadding;
        mRectF.set(mPieChartWidth * 0.5F - mCircleRadius, mPieChartHeight * 0.5F - mCircleRadius,
                mPieChartWidth * 0.5F + mCircleRadius, mPieChartHeight * 0.5F + mCircleRadius);
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
        int mRandom = 1 + (int) Math.random() * pieChartData.size();

        for (int i = 0; i < pieChartData.size(); i++) {
            PieChartData pieChartData = this.pieChartData.get(i);
            int value = pieChartData.getValue();
            mArcPaint.setColor(pieChartData.getColor());
            canvas.drawArc(mRectF, mPieChartStartAngle, value, true, mArcPaint);
            mPieChartStartAngle += value;

            //获取圆心
            float mCircleCenterX = mRectF.right - mRectF.left;
            float mCircleCenterY = mRectF.bottom - mRectF.top;
        }
//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
    }
}
