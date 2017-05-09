package com.gq.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.util.TypedValue;

/**
 * 显示限制输入字数
 *  0/300
 */
public class MonitoringCountEditText extends AppCompatEditText {

    private Paint mPaint;
    private int mPaintColor;
    private float mPaintTextSize;
    private int mSubHeight;

    private int mLimitCount;

    public MonitoringCountEditText(Context context) {
        this(context, null);
    }

    public MonitoringCountEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public MonitoringCountEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainAttributes(attrs);
        init();
    }

    private void obtainAttributes(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.MonitoringCountEditText);
        try {
            mLimitCount = ta.getInt(R.styleable.MonitoringCountEditText_maxLength, -1);
            mPaintColor = ta.getColor(R.styleable.MonitoringCountEditText_limitTextColor, Color.parseColor("#999999"));
            mPaintTextSize = ta.getDimension(R.styleable.MonitoringCountEditText_limitTextSize, 14);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ta != null) {
                ta.recycle();
            }
        }
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(mPaintColor);
        mPaint.setTextSize(mPaintTextSize);
        if (mLimitCount > 0) {
            setFilters(new InputFilter[]{new InputFilter.LengthFilter(mLimitCount)});
        }
        mSubHeight = (int) (mPaintTextSize + TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mLimitCount > 0) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight() + mSubHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mLimitCount > 0) {
            String text = getText().length() + "/" + mLimitCount;
            canvas.drawText(text,
                    getMeasuredWidth() - mPaint.measureText(text) - getPaddingRight(),
                    getMeasuredHeight() - getPaddingBottom(), mPaint);
        }
    }
}
