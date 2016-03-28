package com.meishengkangle.mskl.view;

import com.meishengkangle.mskl.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by lt on 2016/3/12.
 */
public class NumberInput extends LinearLayout {

    private int mHeight;
    private int mWidth;
    private EditText mEditText;
    private int mBorderColor;
    private float mBorderWidth;
    private int mMinusColor;
    private float mMinusWidth;
    private int mPlusColor;
    private float mPlusWidth;
    private int mInitialValue;
    private int mMinValue;
    private int mMaxValue;
    private TextWatcher mWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int value = Integer.valueOf(mEditText.getText().toString());
            if(value<=mMinValue){
                mMinusButton.setEnabled(false);
            }else{
                mMinusButton.setEnabled(true);
            }
            if(value>=mMaxValue){
                mPlusButton.setEnabled(false);
            }else{
                mPlusButton.setEnabled(true);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private View mPlusButton;
    private View mMinusButton;

    public NumberInput(Context context) {
        this(context, null);
    }

    public NumberInput(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("NewApi")
	public NumberInput(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // viewGroup必须设置背景，这样才会调用onDraw()方法
        setBackgroundColor(Color.TRANSPARENT);
        init(context,attrs);
        initView(context);
    }

    /**
     * 获取自定义属性值
     */
    private void init(Context context,AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.NumberInput);
        mBorderColor = typedArray.getColor(R.styleable.NumberInput_border_color, Color.GRAY);
        mBorderWidth = typedArray.getDimension(R.styleable.NumberInput_border_width, 1);
        mMinusColor = typedArray.getColor(R.styleable.NumberInput_minus_color, Color.GRAY);
        mMinusWidth = typedArray.getDimension(R.styleable.NumberInput_minus_width, 2);
        mPlusColor = typedArray.getColor(R.styleable.NumberInput_plus_color, Color.GRAY);
        mPlusWidth = typedArray.getDimension(R.styleable.NumberInput_plus_width, 2);
        mInitialValue = typedArray.getInteger(R.styleable.NumberInput_initial_value, 10);
        mMinValue = typedArray.getInteger(R.styleable.NumberInput_min_value, 0);
        mMaxValue = typedArray.getInteger(R.styleable.NumberInput_max_value, Integer.MAX_VALUE);
        // 回收资源
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {  
    	
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        Paint borderPaint = new Paint();
        borderPaint.setAntiAlias(true);
        borderPaint.setStrokeWidth(mBorderWidth);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setColor(mBorderColor);

        canvas.drawRect(0, 0, mWidth, mHeight, borderPaint);
        borderPaint.setStrokeWidth(mBorderWidth/2);
        canvas.drawLine(mWidth / 3 - mBorderWidth/2, 0, mWidth / 3 - mBorderWidth/2, mHeight, borderPaint);
        canvas.drawLine(mWidth * 2 / 3 - mBorderWidth/2, 0, mWidth * 2 / 3 - mBorderWidth/2, mHeight, borderPaint);
    }

    @SuppressLint("NewApi")
	private void initView(Context context) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        params.setMargins(10, 6, 10, 6);
        params.weight = 1;

        mMinusButton = new MinusView(context);
        mMinusButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentNum = Integer.valueOf(mEditText.getText().toString());
                currentNum--;
                mEditText.setText(currentNum + "");
            }
        });
        if(mInitialValue<=mMinValue){
            mMinusButton.setEnabled(false);
        }
        addView(mMinusButton, params);

        mEditText = new EditText(context);
        // 做个判断，如果给的初始值小于最小值，那么初始值就是最小值
        // 如果给的初始值大于最大值，那么初始值就是最大值
        if(mInitialValue<=mMinValue){
            mEditText.setText(mMinValue + "");
        }else if(mInitialValue>=mMaxValue){
            mEditText.setText(mMaxValue + "");
        }else if(mEditText.getText().equals(mMinValue+"")){
        	mEditText.setText(mMinValue + "");
        }else{
        	 mEditText.setText(mInitialValue + "");
        }
        mEditText.requestFocus();
        mEditText.setGravity(Gravity.CENTER);
        mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        // 去除默认的下划线
        mEditText.setBackground(null);
        mEditText.addTextChangedListener(mWatcher);
        addView(mEditText, params);

        mPlusButton = new PlusView(context);
        params.setMargins(25, 6, 10, 6);
        params.weight = 1;
        mPlusButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentNum = Integer.valueOf(mEditText.getText().toString());
                currentNum++;
                mEditText.setText(currentNum + "");
            }
        });
        if(mInitialValue>=mMaxValue){
            mPlusButton.setEnabled(false);
        }
        addView(mPlusButton,params);
    }

    class MinusView extends View{

        private Paint mMinusPaint;

        public MinusView(Context context) {
            this(context, null);
        }

        public MinusView(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public MinusView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            mMinusPaint = new Paint();
            mMinusPaint.setAntiAlias(true);
            mMinusPaint.setStrokeWidth(mMinusWidth);
            mMinusPaint.setStyle(Paint.Style.STROKE);
            mMinusPaint.setStrokeCap(Paint.Cap.ROUND);
            mMinusPaint.setColor(mMinusColor);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            int widthMode = MeasureSpec.getMode(widthMeasureSpec);
            int heightMode = MeasureSpec.getMode(heightMeasureSpec);
            int widthSize = MeasureSpec.getSize(widthMeasureSpec);
            int heightSize = MeasureSpec.getSize(heightMeasureSpec);

            if(widthMode == MeasureSpec.EXACTLY){
                widthSize = Math.min(widthSize,30);
            }
            if(heightMode == MeasureSpec.EXACTLY){
                heightSize = Math.min(heightSize,30);
            }
            setMeasuredDimension(widthSize, heightSize);

        }

        /**
         * 返回控件中的数值
         * @return 数值
         */
        public int getNumber(){
            return Integer.valueOf(mEditText.getText().toString());
        }


        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            mWidth = getMeasuredWidth();
            mHeight = getMeasuredHeight();
            
            System.out.println("宽====="+mWidth);
            System.out.println("高====="+mHeight);
            canvas.drawLine(mWidth/5,mHeight/2,mWidth-mWidth/5,mHeight/2,mMinusPaint);
        }
    }

    class PlusView extends View{

        private Paint mPlusPaint;

        public PlusView(Context context) {
            this(context, null);
        }

        public PlusView(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public PlusView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            mPlusPaint = new Paint();
            mPlusPaint.setAntiAlias(true);
            mPlusPaint.setStrokeWidth(mPlusWidth);
            mPlusPaint.setStyle(Paint.Style.STROKE);
            mPlusPaint.setStrokeCap(Paint.Cap.ROUND);
            mPlusPaint.setColor(mPlusColor);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            int widthMode = MeasureSpec.getMode(widthMeasureSpec);
            int heightMode = MeasureSpec.getMode(heightMeasureSpec);
            int widthSize = MeasureSpec.getSize(widthMeasureSpec);
            int heightSize = MeasureSpec.getSize(heightMeasureSpec);

            if(widthMode == MeasureSpec.EXACTLY){
                widthSize = Math.min(widthSize,30);
            }
            if(heightMode == MeasureSpec.EXACTLY){
                heightSize = Math.min(heightSize,30);
            }
            setMeasuredDimension(widthSize,heightSize);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            mWidth = getMeasuredWidth();
            mHeight = getMeasuredHeight();
            canvas.drawLine(mWidth - mWidth / 5, mHeight / 2, mWidth / 5, mHeight / 2, mPlusPaint);
            canvas.save();
            canvas.rotate(90, mWidth / 2, mHeight / 2);
            canvas.drawLine(mWidth / 5, mHeight / 2, mWidth - mWidth / 5, mHeight / 2, mPlusPaint);
            canvas.restore();
        }
    }
}